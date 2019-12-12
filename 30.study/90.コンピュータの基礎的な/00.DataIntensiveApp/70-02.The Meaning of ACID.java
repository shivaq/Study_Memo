■■■■■■■■■■■■■■■■■■■■■■■■■■ The Meaning of ACID

トランザクションが提供する guarantees は ACID として表現されたりする。

▼ ACID
Atomicity, Consistency, Isolation, and Durability.
・DB の fault-tolorance なメカニズムを

しかし、
実際は あるDBのACID 実装は、他のDBの実装とイコールではない。
たとえば、
isolation の意味は曖昧。ハイレベルではもっともらしく聞こえるかもだが、
but the devil is in the details.

あるシステムが “ACID compliant” だと言うとき、何が保証されていると期待すればよいのかわからない。
ACID という用語は、残念ながらマーケティング用語とかしているのだ。

▼ BASE
ちなみに、ACID クライテリアに合致しないシステムは BASE と呼ばれたりする。
which stands for Basically Available, Soft state, and Eventual consistency .
これは ACID よりずっと曖昧で、せいぜい not ACID” を意味するだけだ。

▼ Atomicity
▼ atomicity より abortability の方がその本質をより表現できている。

一般的に、
Atomic が意味するのは、それ以上小さなパーツにブレークダウンできない何者かをさす。
とはいえ、
異なるブランチでは微妙に異なる意味合いがあったりする。

たとえば、
マルチスレッドプログラミングでは、
あるスレッドがアトミックなオペを実行したというとき、
他のスレッドはそのオペを途中で覗いたりできない、ということを意味する。
システムの状態は、そのオペ前、そのオペ後のどちらかだけになる。中間はない。

一方、ACID の文脈では、
atomicity は concurrency にまつわる用語ではない。
// 複数のプロセスが同じデータに同じ時間アクセスしようとしたときのこと
// については isolation の領域のため、何も言及しない。
では何についての用語なのか。
もしクライアントが複数の書き込みを行い、そのいくつかの書き込みの途中でfault が発生したとき、
何が起こるかについて言及したものだ。
for example, a process crashes, a network connection is interrupted, a disk becomes full,
or some integrity constraint is violated.

もし、書き込みがアトミックなトランザクションにグループ化されたとして、
トランザクションがfaultのためコミットできなかったとしたら、
トランザクションは破棄され、データベースはそのトランザクション内で行われたすべての書き込みを破棄またはアンドゥする。

もしもアトミシティがなかったら、
複数の変更を行う途中でエラーが発生した場合、
どの変更がなされて、どの変更が失敗したかを見分けることは難しい。
リトライをアプリが行ったとしても、同じ変更を二度行ったり、データの重複、誤ったデータのリスクがある。
アトミシティがこの問題をシンプルにする。
トランザクションが破棄されたら、
アプリは何も変更がなされていないと革新できるし、安全にリトライができる。

▼ これこそが、 ACID の アトミシティの特徴だ
エラー時にトランザクションを破棄できること
そのトランザクションからの書き込みを破棄できること


■■■■■■■■■■■■■■■■■■■■■■■■■■ Consistency
consistency という用語は、文脈によっていろんな意味が込められている

・レプリカの コンシステンシー、eventual consistency
・Consistent hashing：パーティショニング のリバランシングに使われるアプローチ
・the CAP theorem 世界：consistency は linearizability を意味する。// あとのチャプターに出てくる
・ACID 世界：an application-specific notion における、DBが良い状態であることを指す用語

▼ ACID 世界の consistency
you have certain statements about your data (invariants) that must always be true
例)
会計システムでは、全アカウントにまたがった貸方と借方 は常にバランスが取れていなくてはならない。

invariants であるべきものごとが 有効な状態のまま、あるDBのトランザクションが開始され、
そのトランザクション中の書き込みがその 有効性を維持したままであれば、
その invariants が常に満たされているといえる。
If a transaction starts with a database
that is valid according to these invariants,
and any writes during the transaction
preserve the validity,
then you can be sure that
the invariants are always satisfied.

▼ ACID の concistency はアプリ側の プロパティ// A,I,DはDB側
しかし、
この consistency はアプリ側の invariants の概念に依存する
そして、consistency を維持するためにトランザクションを正しく設定する責任は、アプリ側にある。
そして、
DBが保証できることではない。
あなたが invariants が崩れる悪いデータを書き込んだら、DBはそれを止められない。

ある種の invariants はDBでチェックできる。
たとえば、
外部キーの制約や、ユニークネスの制約
とはいえ、一般的には
アプリ側で、どんなデータが 有効で、どんなデータが有効でないかを定義する。
DBはただそれを格納するだけ。

アプリケーションは、concistency を実現するために、DBの アトミシティとアイソレーションに頼っているかもしれんが、
DBだけでは実現できない。

■■■■■■■■■■■■■■■■■■■■■■■■■■ Isolation
ほとんどのDBは複数のクライアントから同時にアクセスすることができる。
もし、
各クライアントが同じDBのレコードにアクセスする場合、
 you can run into concurrency problems (race conditions).

例)
2つのクライアントがあるとする。
あるDBに格納されたカウンタを、両クライアントがインクリメントするとする。
各クライアントは
現在の値を読み込み、1 追加し、その新しい値を戻す。
元の値が 42 だった場合、
それは 44 になるはずだが、
競合状態の場合、43 にしかならない

▼ ACID における Isolation が意味すること
同時に実行されたトランザクションは、互いに隔離されていること。

よくある説明の仕方では、
isolation を serializability としている。
どういう意味か。
各トランザクションは、そのトランザクションがDB全体の中で唯一のトランザクションである、
かのように振る舞うことができる。
DBが保証するのは、
トランザクションがコミットされたとき、
結果はそのトランザクションが順番に(serially)なされたときと同じであること。
// たとえそれらが同時になされたとしても。

だがしかし、実際は、
serializable isolation はめったに使われない。
パフォーマンス 上のコストがかかるから。
Oracle なんかは実装すらしていない。
Oracle にはアイソレーションレベルとして "“serializable" というのがあるが、
実際の実装は、
snapshot isolation と呼ばれる方法をとっている。
serializability と比べると、guarantee は弱い。// 後述

■■■■■■■■■■■■■■■■■■■■■■■■■■ Durability
DBシステムの目的は、データが消えることなく格納できる安全な場所を提供することだ。

Durability が約束するのは、トランザクションが正しくコミットされれば、
HWの fault が起きても、DBがクラッシュしても、書き込まれたデータはなくならないこと。

▼ シングルノードのDBでは、
durability は通常データが揮発しないストレージ(HD や SSD)に書き込まれること、を意味する。
で、たいてい
a write-ahead log やそれに似たものも一緒に使われる。
そうすることで、データ構造がおかしくなっても復元ができる。

▼ レプリケートされたDBでは
durability が意味するのは、データの複数のノードにコピーが成功したこと。

いずれにせよ、トランザクションが成功裏にコミットされたとするには、
データベースは書き込みやレプリケーションが完了するまで待つ必要がある。

しかし、まあ、完璧な durability は存在しない。
