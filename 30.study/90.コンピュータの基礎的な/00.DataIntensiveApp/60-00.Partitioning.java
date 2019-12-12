巨大なデータセット、非常に高いクエリスループットのためには、レプリケーションだけでは足りない。
データを複数のパーティションに分割する必要がある。
それは、シャーディングとも呼ばれる。

▼ ターミノロジー いろいろありすぎだが、パーティショニングが一番知られている呼び方
パーティションは下記では シャード と呼ばれる
MongoDB, Elasticsearch, and SolrCloud
下記ではリージョンとも呼ばれる
HBase
a tablet in Bigtable
a vnode in Cassandra and Riak
a vBucket in Couchbase

▼ パーティションの定義
each piece of data (each record, row, or document) belongs to exactly one partition.
In effect, each partition is a small database of its own,
although the database may support operations that touch multiple partitions at the same time.

▼ パーティションが望まれる主な理由
それはスケーラビリティ
異なるパーティションは、「shared-nothing cluster」の異なるノードに格納できる。

巨大なデータセットは多くのディスクにまたがって distribute される。
クエリの負荷は複数のプロセッサーにまたがって distribute される。

単一のパーティションに行われるクエリは、
各ノードは自身のパーティションに対して、独立してクエリを行うことができる。
よって、
ノードを増やせば増やしただけ、クエリのスループットはスケールする。

大きくて複雑なクエリは、
複数のノードにまたがって、並列で行われる。そしてそれは、すごく難しいことなのだ。

パーティショニング は1980年台に開発され、最近 NoSQL データベースと、Hadoop-based data warehouses によって再発見された。
あるシステムは トランザクションのワークロードのためにデザインされており、
他の者共はアナリティクス用にデザインされている。
