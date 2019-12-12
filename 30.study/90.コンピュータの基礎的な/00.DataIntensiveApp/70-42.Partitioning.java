■■■■■■■■■■■■■■■■■■■■■■■■■■ Partitioning

▼ 同時トランザクションをシリアルにする →単一マシン、1CPU と同じスピードになってしまう
Executing all transactions serially makes concurrency control much simpler,
but limits the transaction throughput of the database to the speed of a single CPU core on a single machine.

▼ リードオンリトランザクションなら、スナップショット Isolation を使って別のとこでできる
Read-only transactions may execute elsewhere, using snapshot isolation,

▼ 高い書き込みスループットが要求される場合、シングルスレッドトランザクションはキツイ
but for applications with high write throughput,
the single-threaded transaction processor can become a serious bottleneck.





▼ データセットをパーティショニングすれば、各パーティションで独立してシングルスレッドでのプロセスができる
各パーティションに、CPU コアを割り当てられる
 →トランザクションスループットはCPUの数とリニアにスケールできる
In order to scale to multiple CPU cores, and multiple nodes,
you can potentially partition your data (see Chapter 6), which is supported in VoltDB.
If you can find a way of partitioning your dataset
so that each transaction only needs to read and write data within a single partition,
then each partition can have its own transaction processing thread
running independently from the others.

In this case, you can give each CPU core its own partition,
which allows your transaction throughput to scale linearly with the number of CPU cores.





▼ 複数のパーティションをまたいで行われるトランザクションでは、各パーティションをロックしないといかん
However, for any transaction that needs to access multiple partitions,
the database must coordinate the transaction across all the partitions that it touches.
The stored procedure needs to be performed in lock-step
across all partitions to ensure serializability across the whole system.





▼ VoltDB の場合クロスパーティショントランザクションは、シングルパーティションのそれより桁違いに遅く、
マシンを増やしても効果はない
Since cross-partition transactions have additional coordination overhead,
they are vastly slower than single-partition transactions.
VoltDB reports a throughput of about 1,000 cross-partition writes per second,
which is orders of magnitude below its single-partition throughput
and cannot be increased by adding more machines [49].





▼ トランザクションが single-partition にできるかどうかはアプリが扱うデータ構造に依存する
Whether transactions can be single-partition
depends very much on the structure of the data
used by the application.

▼ シンプルなキーバリューだと、パーティションは簡単。シングルでいける。だが、セカンダリインデックスを使う場合、
複数のクロスパーティション coordination が必要

■■■■■■■■■■■■■■■■■■■■■■■■■■ Summary of serial execution
トランザクションをシリアルに実行する →(下記制約下では)serializable isolation を実現する有効な方法となる
• Every transaction must be small and fast, because 一つのトランザクションが遅いと、トランザクション全体が遅くなるから

• ユースケースは、アクティブなデータセットがメモリに収まる場合に限る。
めったにアクセスされないデータはDisk に移せるが、シングルスレッドトランザクションでアクセスされる場合、システムがすごく遅くなる
※ メモリに収まらない場合の解決策：anti-caching
トランザクションを破棄 →他のトランザクションを処理する →非同期でデータをメモリに持ってくる
 →メモリにロードされたデータを使ってトランザクションを再開

・書き込みスループットは一つのCPUコアでなんとかなるくらい低いものでなくてはならない。
または
クロスパーティションなしで、パーティションされてなくてはならない。


・クロスパーティショントランザクションは、不可能ではないが制約が多い
