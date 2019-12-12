■■■■■■■■■■■■■■■■■■■■■■■■■■ Request Routing
複数のマシン上の複数のノードをまたいで、データセットをパーティショニングする方法を紹介した。

ところで、
クライアントがリクエストを行ったとき、
どうやってどのノードに接続すればいいかがわかるのだ？

パーティションがリバランスされれば、ノードへのパーティションの割当も変わる。
だれかが、
それら変更を追跡する役割を担わなければならない。
"foo" というキーを読み書きしたいとして、どのIPアドレス、どのポート番号に接続すればいい？？

▼ サービスディスカバリー
これはサービスディスカバリーと呼ばれる、DBに限定されないより一般的な問題

ネットワーク越しにアクセスを行う経路があるソフトウェアはすべてこの問題を抱えている。
とくに、
可用性のため、冗長構成にしてたりしたら。

多くの会社が社内サービスディスカバリーツールを構築しており、
そのいくつかはオープンソースとしてリリースされている。

▼ この問題への異なるアプローチ
1.
クライアントはどのノードにもアクセスしうる(LB によるラウンドロビンなどによって)
・もしそのノードがたまたまリクエスト対象のパーティションを含んでいた場合
 →直接リクエストをハンドルする。
・含んでいない場合
 →リクエストは適切なノードに転送され、リプライを受け取り、クライアントにリプライを渡す。// 最初のノードを常に経由するってこと？

2.
全てのリクエストは、はじめに一つのルーティング層に送られる。
そこが書くリクエストをハンドルするノードを判断し、転送する。
ルーティング層自身はリクエストをさばかない。
ただ、
パーティション情報を知っているLBとして振る舞うだけ。

3.
クライアントが、パーティショニングとノードへのパーティション割当をしっている。
この場合、
クライアントは仲介なしに直接適切なノードに接続できる。
-------------------------------------------------

いずれの場合も問題はこうだ。
どうやってコンポーネントがルーティングを決めるのか。
どうやってノードへのパーティション割当の変更を学習するのか。
むずかしいのは、
全ての participants が合意しなければならず、
さもないと
リクエストが別のノードに送られてうまく処理されなかったりするから。

上記のような、分散システムにおける合意形成のためのプロトコルはいくつかある。
しかし正しく実装するのは難しい。

▼ ZooKeeper
多くのデータシステムは、クラスタのメタデータを追跡するために、 ZooKeeper のような coordination service を使っている。

各ノードは ZooKeeper に自身を登録し、
ZooKeeper はパーティションとノードのマッピングをメインテインする。

the routing tier or the partitioning-aware client can subscribe to this information in ZooKeeper.

パーティションがオーナーシップを変更したり、
ノードの追加/削除がなされた時も、ズーキーパーは ルーティング tier に通知して、
ルーティングの情報を UpToDate にする。

▼ Zookeeper 的なサービスを使うデータシステム
・LinkedIn’s Espresso uses Helix for cluster management (which in turn relies on ZooKeeper),
implementing a routing tier as shown in Figure 6-8.

・HBase, SolrCloud, and Kafka also use ZooKeeper to track partition assignment.

・MongoDB も似たアーキテクチャを持っているが、
but it relies on its own config server implementation and mongos daemons as the routing tier.

▼ Cassandra and Riak はまた違ったアプローチを取る。
ノード間で gossip protocol を使い、クラスタの状態変化を広める。

リクエストはあらゆるノードに送られ、
そのノードはリクエストを適切なパーティションのノードに転送する。
(approach 1 in Figure 6-7)

結果、データベースノードに複雑さを持ち込むことになるが、
外部サービスへの依存を回避することができる。

▼ Couchbase の場合
リバランスを自動的には行わない。
通常は moxi という routing tier でそれを設定しており、
ルーティング変更をクラスターのノードたちから学習する。

ところで、routing tier を使う場合にしろ、ランダムなノードにリクエストを送るときにしろ、
クライアントは接続するためにIPアドレスを探さなければならない。
IP アドレスはノードへのパーティション割当ほど早くは変わらないため、
だいたいが、この目的のためにDNSを使う、という方法で十分だったりする。


■■■■■■■■■■■■■■■■■■■■■■■■■■ Parallel Query Execution
ところで、
ここまでは、単一のキーに対する読み書きのクエリに焦点を当ててきた。
これは about the level of access supported by most NoSQL distributed datastores.

しかしながら、
massively parallel processing (MPP)relational database products, often used for analytics,
では、サポートするクエリのタイプはずっと洗練されている。

・典型的なデータウェアハウスのクエリには、
several join, filtering, grouping, and aggregation operations
が含まれている。

massively parallel processing (MPP) クエリ・オプティマイザーは、
こういった複雑なクエリを a number of execution stages and partitions に分解して、
その多くはデータベースクラスタの異なるノードたちにパラレルで実行可能

データセットの大きなパーツに対するスキャンが含まれるクエリの場合特に、
パラレル実行の利益を得ることができる。

データ分析がビジネス上に重きを置かれる場合、
データウェアハウスにおける並行実行は商業的な利益を得るためにも、注目に値する。
