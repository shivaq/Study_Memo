▼ CPU の負荷が高い処理が増えてきたため、Javaでほとんど実装してたのを C++ に変更していった
Back in 2015, the majority of Pinterest’s backend systems were implemented in Java,
and we hadn’t built any systems in C++.
As we can see from the previous sections, the new systems must achieve low long-tail latency with big fanout (sometimes all shard fanout),
and some systems are CPU intensive (e.g., Scorpion, Pixie and RealPin).
We chose to adopt C++11, FBThrift, Folly and RocksDB to build these systems.

▼
RocksDB is an embedded storage engine. To build sharded and replicated distributed systems on top of it,

▼ master-slave replication. を採用
data replication was the first problem we needed to solve.
We started with the write-to-all-replica approach and later moved to master-slave replication.

▼ AZ をまたいだ処理はカネがかかるので、回避できるシステムを構築
Our systems are running on AWS, which models its network into Regions, Availability Zones and Placement Groups.
Notably, the bill for cross-AZ network traffic is significant.
We built a prefix-based AZ-aware traffic routing library that minimizes cross-AZ traffic and supports all possible routing patterns
(e.g., single shard, multiple shard and all shard fanout).

▼ TCP コネクションを監視する機能も搭載
The library also monitors TCP connection health and gracefully fails over requests among replicas.
One thing to note is we needed to leverage the TCP_USER_TIMEOUT socket options to fail fast when the OS on remote peer crashed.
It is not uncommon for a VM instance to become unreachable on AWS for various reasons without shutting down TCP connections.
If TCP_USER_TIMEOUT is not set, a typical TCP implementation could take over 10 minutes to report the issue to user space applications.
(More details about data replication and traffic routing can be found in the Rocksplicator Github repo and blog post Open-sourcing Rocksplicator,
a real-time RocksDB data replicator.)
