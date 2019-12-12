■■■■■■■■■■■■■■■■■■■■■■■■■■ Conflict resolution and replication
▼ レプリケートされた DB では、更新ロストを防ぐのは別の次元で行われる
In replicated databases,
preventing lost updates takes on another dimension:




▼ そのノードをチェックしたところで、別のノードで同時に更新がなされているかもしれん
 since they have copies of the data on multiple nodes,
 and the data can potentially be modified concurrently on different nodes,
 some additional steps need to be taken to prevent lost updates.





▼ UpToDate なコピーがひとつだけとは保証できない
Locks and compare-and-set operations assume that
there is a single up-to-date copy of the data.
However,
databases with multi-leader or leaderless replication
usually allow several writes to happen concurrently
and replicate them asynchronously,
so they cannot guarantee that
there is a single up-to-date copy of the data.


Thus, techniques based on locks or compare-and-set
do not apply in this context.
(We will revisit this issue in more detail in “Linearizability” on page 324.)




▼ 同時書き込みを許可して、値に複数のコンフリクトバージョンを用意する。
で、アプリコードや特殊なデータ構造によって、コンフリクトバージョン間で、解決してマージする。

Instead, as discussed in “Detecting Concurrent Writes” on page 184,
a common approach in such replicated databases is
to allow concurrent writes to create several conflicting versions of a value
(also known as siblings),
and to use application code
or special data structures
to resolve and merge these versions after the fact.




▼ 可換 な場合は特に、レプリケーションコンテキスト下での Atomic オペレーションがうまく機能する
Atomic operations can work well in a replicated context,
especially
if they are commutative (a * b = b * a みたいなこと。可換。i.e., you can apply them in a different order on different replicas, and still get the same result).

For example,
incrementing a counter
or adding an element to a set
are commutative operations.



▼ Riak もそうしてる
That is the idea behind Riak 2.0 datatypes,
which prevent lost updates across replicas.

When a value is concurrently updated by different clients,
Riak automatically merges together the updates
in such a way that no updates are lost [39].




▼ LWW は更新ロストが発生しうる。にもかかわらず、おおくの replicated databases のデフォルトが LWW
On the other hand,
the last write wins (LWW) conflict resolution method is prone to lost updates,
as discussed in “Last write wins (discarding concurrent writes)” on page 186. Unfortunately,
LWW is the default in many replicated databases.
