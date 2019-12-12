■■■■■■■■■■■■■■■■■■■■■■■■■■ Sloppy Quorums and Hinted Handoff
Sloppy：ずさんな

▼ フェイルオーバーなしで個々のノードの fail を耐えられる
適切に quorums が設定されたDBは、フェイルオーバーなしで個々のノードの fail を耐えられる。
さらに、
個々のノードが遅くなっても大丈夫。
なぜならば、
n 個のノードがレスポンドするのを待つ必要はなく、
w or r ノードが応答したタイミングで返すことができるから。

上記特徴があるため、リーダーレスレプリケーションは、下記ユースケースに向いている
高可用性と低レイテンシーが必要で、かつたまに読み込んだ値が古いものでも許容できるアプリ

▼ quorums は fault-tolerant になりきれない
だがしかし、
quorums は fault-tolerant になりきれない部分がある。
ネットワーク中断は、クライアントとDB群とを簡単に切り離してしまう。

それらノードが生きていたとしても、他のクライアントがそれらと接続できたとしても、
DBノードから切り離されたクライアントは死んだままだから。

上記状況だと w or r より少ないノードしか到達可能なのは残っておらず、
結果、
クライアントは quorum に到達できない。

▼
ノード の数が n より多いたくさんのクラスタの場合、
クライアントはネットワーク中断の最中でも、いくつかのDBノードにアクセスすることができる。
そのアクセス先は、
特定の値のために、quorum を集めるためのノードだけではない。

上記状況では、トレードオフが発生する。

- Is it better to return errors to all requests for which
we cannot reach a quorum of w or r nodes?
はい。失敗しましたから！とみんなに発表する？

// a sloppy quorum
- Or should we accept writes anyway,
and write them to some nodes
that are reachable but aren’t among the n nodes on which
the value usually lives?
とりあえず、空いてるノードに書いておいて。
で、あとで正しいノードに書き写してね。

▼ a sloppy quorum
読み書きは w と r の成功レスポンスを必要としている。
だが、
それらレスポンスは、その値のために指定された n 個の"home"ノードには含まれないノードかも知れない。

アナロジー：
if you lock yourself out of your house
隣の家のドアを叩いて、一時的にカウチにいさせてもらえないか頼むみたいな感じ。

▼ hinted handoff
ネットワーク中断が治ったら、
一時的に(他のノードの代わりに)あるノードが受け付けていたあらゆる書き込みを、
適切な"home"ノードに送ってやること

アナロジー：
家の鍵をあなたが見つけたとする。
親切な隣人は、丁寧にあなたに告げるのだ。
カウチからで家に帰ったら？と。

▼ Sloppy quorums のいいところ
書き込み可能性が高くなる。
w 個のノードが利用可能な限り、DBは書き込みを受け付けられる。

▼ Sloppy quorums の残念なところ
たとえ
w + r > n
だったとしても、
ある書き込みがそのキーに対する最新の値を保持していることが保証できない。
最新の書き込みは、n ではない別のノードに一時的に書き込まれている可能性がある。

・伝統的な感覚から言うと、Sloppy quorums は全然 quorum じゃない。
耐久性を確保するためだけのもので、
データはw ノードのどこかに格納されてんじゃね？ってくらい。
r ノードの読み込みが、最新の値を返すことが保証できるのは、
hinted handoff が完了してからになる。


▼ Sloppy quorums の採用度合い
・全ての Dynamo 実装ではオプション扱い
・Riak ではデフォルトで有効化されている
・Cassandra and Voldemort では、デフォルトでは無効化されている

■■■■■■■■■■■■■■■■■■■■■■■■■■ Multi-datacenter operation
cross-datacenter replication は、マルチリーダーレプリケーションだけでなく、
リーダーレスレプリケーションも向いている。
なぜか。
同時書き込みのコンフリクト、ネットワーク中断、レイテンシーの急騰も許容できるから。


▼ Cassandra and Voldemort
 →multi-datacenter support を標準リーダーレスモデルに実装している
・n 個のレプリカには、全てのDCのノードが含まれている。
・設定で、各DCにいくつの n レプリカがほしいか指定できる。

あるクライアントからの各書き込みは、DCがどこかにかかわらず、全てのレプリカに送られる。
しかし、クライアントが待つ acknowledgment については、
ローカルDC内の quorum ノードからのみ待ち受ける
よって、
クロスDCリンクの中断や遅延からは影響を受けない。

他のDCリンクのへの高レイテンシーな書き込みは、たいてい 非同期でなされるよう設定されている。

▼ Riak の場合
クライアントとDBノード間、
ローカルとあるDCとの間
それぞれの通信を キープする。
よって、
n はすなわち、あるDC内のレプリカの数と同数。
Riak keeps all communication
between clients and database nodes local
to one data‐ center,
so n describes the number of replicas within one datacenter.

DBクラスター間の Cross-datacenter replication は、バックグラウンドで非同期でなされるため、
マルチリーダーレプリケーションのやり方に似ている。
