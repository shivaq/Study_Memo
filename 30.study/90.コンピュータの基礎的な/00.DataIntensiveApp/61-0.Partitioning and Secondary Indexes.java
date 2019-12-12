■■■■■■■■■■■■■■■■■■■■■■■■■■ Partitioning and Secondary Indexes
ここまで紹介してきたパーティショニングスキーマは、キーバリューデータモデルに依存している。

レコードが、プライマリーキーでのみアクセスされるのであれば、
そのキーからパーティションを判断できる。
そのキーを使って、読み書きリクエストを、そのキーのパーティションにルートすることができる。

だが、セカンダリインデックスが絡んでくると、状況は複雑になる。

▼ セカンダリインデックスというものは
・セカンダリインデックスはレコードをユニークに同定しない、
 but rather
 is a way of searching for occurrences of a particular value:
 find all actions by user 123,
 find all articles containing the word hogwash,
 find all cars whose color is red, and so on.

・セカンダリインデックスはRDBにおけるパンとバターのようなもの// どういう意味だ？
ドキュメントデータベースにとっても一般的だ。

HBase and Voldemort などの キーバリューストアは、
セカンダリインデックスを避ける。
なぜならば、
implementation complexity が加わるから。
Riak などは、
セカンダリインデックスを採用し始めた。
なぜならば、
データモデリングのために使い勝手がいいから。
Solr and Elasticsearch みたいな検索サーバにとっては、
セカンダリインデックスは レゾンデートルだとも言える。

▼ セカンダリインデックスの問題
あいつら、パーティションとうまくマップできないんだ。。。

▼ セカンダリインデックスつきデータベースをパーティショニングする2つのアプローチ
document-based partitioning
and term-based partitioning.

■■■■■■■■■■■■■■■■■■■■■■■■■■ Partitioning Secondary Indexes by Document
▼ document-based partitioning を使うデータベース
MongoDB, Riak , Cassandra , Elasticsearch , SolrCloud , and VoltDB

・中古車を取り扱うウェブサイトのケース。
各リストにはユニークな ドキュメントIDのがある。
データベースは、そのドキュメントIDに基づいてパーティショニングされている。
(for example, IDs 0 to 499 in partition 0, IDs 500 to 999 in partition 1, etc.).

ユーザーが車を検索する際に、色とメーカーでフィルタリングできるとする。
 →セカンダリインデックスを color and make にたいして作成する
 ※ ドキュメントDBではフィールド
 ※ RDB ではコラム

・インデックスを作成するときは、DBではひゃ自動でインデックス化してくれる。
たとえば、
DBに赤い車を追加すると、
the database partition automatically adds it
to the list of document IDs
for the index entry color:red.

上記インデックス化アプローチだと、
各パーティションは完全に別々。
各パーティションは、自身のセカンダリインデックスを保守し、
そのパーティションのドキュメントのみをカバーする。

他のパーティションに何のデータが格納されているか気にしない。
DBへの書き込みが必要なときは、今書き込んでいるドキュメントIDのパーティションだけに向き合っていればいい。
そんなわけで、a document-partitioned index は ローカルインデックスでもある。

▼ a document-partitioned index からの読み込みは慎重に
ドキュメントIDに特別ななにか// なんのことだ？
をしない限り、特定の色または特定のメーカーの車が、同じパーティションに格納されるとは限らない。
よって、
赤い車を検索する場合は、
クエリを全てのパーティションに送り、全ての結果を結合する必要がある。
この、パーティション化されたDBへのクエリ方法は、
sometimes known as scatter/ gather
セカンダリインデックスの読み込みが高コストになる。
たとえ、
このクエリがパラレルに行われたとしても、
tail latency amplification を起こしやすい。

▼ ほとんどのDBベンダーの推奨
セカンダリインデックスのクエリが単一のパーティションからなされるように、
パーティショニングスキーマを構造化すること。
しかし、いつもそれが可能だとは限らない。
とくに、
単一クエリで複数のセカンダリインデックスを使うような場合// 色とメーカーでフィルタリングとか

■■■■■■■■■■■■■■■■■■■■■■■■■■ Partitioning Secondary Indexes by Term
term-based partitioning または term-partitioned

各パーティションごとにセカンダリインデックスを持つのではなく、
全パーティションをカバーする、グローバルなインデックスを作ればいいじゃない。

どうやって？
一つのノードにそれらインデックスを格納するだけじゃだめだ。
結局ボトルネックになってｍ，パーティショニング自体の意味がなくなる。
グローバルインデックスもパーティション化されなければならない。
だが、その方法はプライマリキーインデックスとは異なる方法でだ。

全てのパーティションの赤い車は、
appear under color:red in the index
そしてそのインデックスもパーティション化されている。

色の名前が a to r のインデックスは、パーティション0
色の名前が s to z のインデックスは、パーティション1 に格納される。
メーカーのインデックスも同様にパーティション化される。

対象の term が、インデックスのパーティションを決めるので、
term-partitioned
と呼ばれる。

term-based partitioning は、レンジスキャンにも有用。
(e.g., on a numeric property, such as the asking price of the car),
whereas
term のハッシュによってパーティショニングすると、負荷もより均等に分散される。

▼ グローバルインデックスがローカルインデックスより勝っている点
読み込みがより効率的
ほしい term が含まれるパーティションにのみクエリすればいい。

▼ グローバルインデックスの欠点
書き込みが遅くて複雑。
一つのドキュメントへの書き込みが、複数のパーティションのインデックスに影響を与えるから。
そのドキュメントの各 term は異なるノードの異なるパーティションにあるかもしれん

▼ 理想的には、
インデックスは常に UpToDate で、
DBに書かれた各ドキュメントは、直ちにインデックスに反映されてほしい。
しかし、
a term- partitioned index は、
書き込みに影響を受ける全てのパーティションにまたがった分散トランザクションが必要で、
それは全てのDBでサポートされているわけではない。

▼ 実際には、
グローバルセカンダリインデックスの更新は、たいてい非同期だ。
// 書き込み直後にインデックスを読みに行っても、変更はインデックスに未反映だったりする。
たとえば、
Amazon DynamoDB の場合、通常一秒未満で反映されるが、
インフラに fault が合った場合などは遅延が発生する場合があると表明している。

Riak’s search feature や、the Oracle data warehouse なんかは、ローカルとグローバルインデックスとを選択可能。
