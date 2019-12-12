■■■■■■■■■■■■■■■■■■■■■■■■■■ Synchronous Versus Asynchronous Replication
・Synchronous Versus Asynchronous Replication
 →RDB では変更可能。他のDBシステムではハードコードされている。

Figure 5-2,
▼ 同期処理のダイアグラム
the replication to follower 1 is synchronous:
the leader waits until follower 1 has confirmed that it received the write
before reporting success to the user,
and before making the write visible to other clients.

▼ 非同期処理のダイアグラム
The replication to follower 2 is asynchronous:
the leader sends the message, but doesn’t wait for a response from the follower.
The diagram shows that there is a substantial delay before follower 2 processes the message.

▼ 通常レプリケーションは1秒以内に完了する
Normally, replication is quite fast:
most database systems apply changes to followers in less than a second.
▼ 数分以上の場合もある
However,
there is no guarantee of how long it might take.
There are circumstances when followers might fall behind the leader by several minutes or more;
▼ フォロワーの不良、システムの負荷、ネットワークの問題
for example, if a follower is recovering from a failure,
if the system is operating near maximum capacity,
or if there are network problems between the nodes.

▼ 同期処理の利点
・フォロワーのデータが最新版であることが保証されている。
・リーダーが fail しても、フォロワーのデータが利用できることが保証されている。

▼ 同期処理の欠点
・フォロワーが 応答しなかった場合
(because it has crashed, or there is a network fault, or for any other reason),
書き込み処理が止まってしまう。
リーダーは同期のレプリカが復活するまですべての書き込みをブロックしなければならない。

▼ 同期処理の practical な使い方
・全フォロワーが同期はよくない
 →ノードが一つでも 応答しない場合、全システムが停止する

▼ practical 非同期処理：semi-synchronous
・フォロワーのうちの一つのみ同期
 →他のフォロワーは非同期にする

・同期中フォロワーが応答しない/遅い
 →非同期フォロワーのうちの一つが同期フォロワーになる
結果下記が保証される
少なく少なくとも リーダーと同期フォロワーの2つが最新状態のデータであること

▼ 完全非同期レプリケーションもある
・しばしばある。
リーダーが失敗し、復旧不可能となった場合
 →フォロワーにレプリケートされなかったすべての書き込みはロストする。
 →書き込みの耐久性が保証されていないことを意味する

▼ 完全非同期レプリケーションの利点
・すべてのフォロワーが fail したとしても、
リーダーは書き込みを継続できる
・耐久性を犠牲にすることは、悪いトレードオフのように聞こえる
しかしそれでも
フォロワーが多い場合や地理的に分散されているケースでは広く使われている

■■■■■■■■■■■■■■■■■■■■■■■■■■ Research on Replication
▼ 非同期レプリケーションの問題
・リーダーが fail したらデータがロストする

▼ チェーンレプリケーション
・同期レプリケーションの一種
Azure ストレージなどに実装されている

・下記 の間には強いつながりがある
consistency of replication
and
consensus(複数のノードが、一つの値に合意する)
