Kafka is a distributed, horizontally-scalable, fault-tolerant, commit log.

▼ Distributed
Distributed システムとは、複数のマシンが一つのクラスタを構成し、ユーザーからは一つのノードのように見えるシステム
そのメリット
スケーラブルで、fault-tolerance

▼ Horizontally-scalable

・vertical scalability
 →マシンの CPU, RAM, SSD などのスケールアップ
disadvantage
・ハードウェアの限界がある。
・ダウンタイムが発生する

・Horizontal scalability
マシンの数を増やす。vertical scale の欠点がない。

・クラスタとして動くようにデザインされていないシステムはこれはできない。

▼ Fault-tolerant
分散システムにはa single point of failure (SPoF) 単一障害点がある。

▼ Commit Log
A commit log (also referred to as write-ahead log, transaction log)  は、順番の一貫性が保証されたデータ号像であり、
append のみをサポートしている。
そのレコードを修正したり削除したりできない。

この構造が Kafka の肝であり、deterministic processing（アウトプットが決まっている）を実現している。
※ Stochastic process は Stock Price のごとく結果が予測できない

Kafka はメッセージをそのように格納するため、シークエンシャルにディスクを読み込むことができる。

読み書きが a constant time O(1) (knowing the record ID)
読み込み時に書き込みを制限しない。その逆も真。
上記特徴のため、データサイズがパフォーマンスから隔離されている。


How does it work?
Applications (producers) send messages (records) to a Kafka node (broker)
メッセージはconsumersと呼ばれる別のアプリによって処理される。
送られたメッセージは、Topic に格納され、Consumers はそのトピックにサブスクライブすることで、新しいメッセージを受け取ることができる。

トピックが大きくなると、小さいサイズのパーティションに分裂して、パフォーマンスとスケーラビリティを得ようとする。
※ ユーザーログインリクエストを、姓と名とで分けるなど。
Kafka はパーティション内のすべての順番が、入ってきた順であることを保証する。
特定のメッセージは、配列のインデックスによって指定できる。

▼ the principle of a dumb broker and smart consumer
Kafka は the principle of a dumb broker and smart consumerに従っている。

Kafka はどのレコードがconsumer から読まれたかを追跡せず、削除する。
一定期間またはサイズがしきい値を超えるまで保存するようなこともしない。

Consumer はKafkaに対して新規メッセージをポールして、読みたいレコードを指定する。
結果、メッセージ内のインデックスを上げたり下げたりして、イベントをリプレイしたり、リプロセスしたりする。

Conusmer は、実際は一つまたは複数のConsumer プロセスを内包するConsumer Group である。
2つのプロセスが同じメッセージを二度読み込むことを回避するため、書くパーティションはグループに付き一つのConsumerプロセスと紐付けられる。
Consumer は、あるトピックの、パーティション1の、Offset 2から読み込む、みたいなことをする。


▼ Persistence to Disk
Kafkaはすべてのレコードをディスクに格納し、RAMには保持しない。

1.Kafka はメッセージをグループ化するプロトコル持っている。
これによって、ネットワークリクエストはグループ単位で行われるためオーバーヘッドが減る。
サーバーはメッセージの塊を送り、ConsumerはLinearなメッセージの塊を一度に受け取る。

2.ディスクに対するリニアな読み書きは早い。ディスクの読み込みが遅い事象というものは、大量のディスク内検索が原因なのです。
リニアな操作はOSによって最適化されている。read-ahead (prefetch large block multiples) 
and write-behind (group small logical writes into big physical writes) techniques によって。
OSはディスクをRAMにキャッシュする。これを pagecache という。
Kafka はメッセージをバイナリフォーマットで格納し、(producer->broker->consumer)のフロー中加工することもないため、
the zero-copy optimization が可能。
結果、Kafkaはメッセージをネットワークスピードほとんどそのままで配信することができる。



続きは、実際に Kafka を使うことになったときに読み進めるとしよう。ß
https://hackernoon.com/thorough-introduction-to-apache-kafka-6fbf2989bbc1

Data Distribution & Replication
Let’s talk about how Kafka achieves fault-tolerance and how it distributes data between nodes.

Data Replication
Partition data is replicated across multiple brokers in order to preserve the data in case one broker dies.

At all times, one broker “owns” a partition and is the node through which applications write/read from the partition. This is called a partition leader. It replicates the data it receives to N other brokers, called followers. They store the data as well and are ready to be elected as leader in case the leader node dies.

This helps you configure the guarantee that any successfully published message will not be lost. Having the option to change the replication factor lets you trade performance for stronger durability guarantees, depending on the criticality of the data.


4 Kafka brokers with a replication factor of 3
In this way, if one leader ever fails, a follower can take his place.

You may be asking, though:

- How does a producer/consumer know who the leader of a partition is?

For a producer/consumer to write/read from a partition, they need to know its leader, right? This information needs to be available from somewhere.
Kafka stores such metadata in a service called Zookeeper.

What is Zookeeper?
Zookeeper is a distributed key-value store. It is highly-optimized for reads but writes are slower. It is most commonly used to store metadata and handle the mechanics of clustering (heartbeats, distributing updates/configurations, etc).

It allows clients of the service (the Kafka brokers) to subscribe and have changes sent to them once they happen. This is how brokers know when to switch partition leaders. Zookeeper is also extremely fault-tolerant and it ought to be, as Kafka heavily depends on it.

It is used for storing all sort of metadata, to mention some:

Consumer group‘s offset per partition (although modern clients store offsets in a separate Kafka topic)
ACL (Access Control Lists) — used for limiting access/authorization
Producer & Consumer Quotas —maximum message/sec boundaries
Partition Leaders and their health
How does a producer/consumer know who the leader of a partition is?
Producer and Consumers used to directly connect and talk to Zookeeper to get this (and other) information. Kafka has been moving away from this coupling and since versions 0.8 and 0.9 respectively, clients fetch metadata information from Kafka brokers directly, who themselves talk to Zookeeper.


Metadata Flow
Streaming
In Kafka, a stream processor is anything that takes continual streams of data from input topics, performs some processing on this input and produces a stream of data to output topics (or external services, databases, the trash bin, wherever really…)

It is possible to do simple processing directly with the producer/consumer APIs, however for more complex transformations like joining streams together, Kafka provides a integrated Streams API library.

This API is intended to be used within your own codebase, it is not running on a broker. It works similar to the consumer API and helps you scale out the stream processing work over multiple applications (similar to consumer groups).

Stateless Processing
A stateless processing of a stream is deterministic processing that does not depend on anything external. You know that for any given data you will always produce the same output independent of anything else. An example for that would be simple data transformation — appending something to a string "Hello" -> "Hello, World!".


Stream-Table Duality
It is important to recognize that streams and tables are essentially the same. A stream can be interpreted as a table and a table can be interpreted as a stream.

Stream as a Table
A stream can be interpreted as a series of updates for data, in which the aggregate is the final result of the table. This technique is called Event Sourcing.

If you look at how synchronous database replication is achieved, you’ll see that it is through the so-called streaming replication, where each change in a table is sent to a replica server. Another example of event sourcing is Blockchain ledgers — a ledger is a series of changes as well.

A Kafka stream can be interpreted in the same way —events which when accumulated form the final state. Such stream aggregations get saved in a local RocksDB (by default) and are called a KTable.


Each record increments the aggregated count
Table as a Stream
A table can be looked at as a snapshot of the latest value for each key in a stream. In the same way stream records can produce a table, table updates can produce a changelog stream.


Each update produces a snapshot record in the stream
Stateful Processing
Some simple operations like map() or filter() are stateless and do not require you to keep any data regarding the processing. However, in real life, most operations you’ll do will be stateful (e.g count()) and as such will require you to store the currently accumulated state.

The problem with maintaining state on stream processors is that the stream processors can fail! Where would you need to keep this state in order to be fault-tolerant?

A naive approach is to simply store all state in a remote database and join over the network to that store. The problem with this is that there is no locality of data and lots of network round-trips, both of which will significantly slow down your application. A more subtle but important problem is that your stream processing job’s uptime would be tightly coupled to the remote database and the job will not be self-contained (a change in the database from another team might break your processing).

So what’s a better approach?
Recall the duality of tables and streams. This allows us to convert streams into tables that are co-located with our processing. It also provides us with a mechanism for handling fault tolerance — by storing the streams in a Kafka broker.

A stream processor can keep its state in a local table (e.g RocksDB), which will be updated from an input stream (after perhaps some arbitrary transformation). When the process fails, it can restore its data by replaying the stream.

You could even have a remote database be the producer of the stream, effectively broadcasting a changelog with which you rebuild the table locally.


Stateful processing, joining a KStream with a KTable
KSQL
Normally, you’d be forced to write your stream processing in a JVM language, as that is where the only official Kafka Streams API client is.


Sample KSQL setup
Released in April 2018, KSQL is a feature which allows you to write your simple streaming jobs in a familiar SQL-like language.

You set up a KSQL server and interactively query it through a CLI to manage the processing. It works with the same abstractions (KStream & KTable), guarantees the same benefits of the Streams API (scalability, fault-tolerance) and greatly simplifies work with streams.

This might not sound as a lot but in practice is way more useful for testing out stuff and even allows people outside of development (e.g product owners) to play around with stream processing. I encourage you to take a look at the quick-start video and see how simple it is.

Streaming alternatives
Kafka streams is a perfect mix of power and simplicity. It arguably has the best capabilities for stream jobs on the market and it integrates with Kafka way easier than other stream processing alternatives (Storm, Samza, Spark, Wallaroo).

The problem with most other stream processing frameworks is that they are complex to work with and deploy. A batch processing framework like Spark needs to:

Control a large number of jobs over a pool of machines and efficiently distribute them across the cluster.
To achieve this it has to dynamically package up your code and physically deploy it to the nodes that will execute it. (along with configuration, libraries, etc.)
Unfortunately tackling these problems makes the frameworks pretty invasive. They want to control many aspects of how code is deployed, configured, monitored, and packaged.

Kafka Streams let you roll out your own deployment strategy when you need it, be it Kubernetes, Mesos, Nomad, Docker Swarm or others.

The underlying motivation of Kafka Streams is to enable all your applications to do stream processing without the operational complexity of running and maintaining yet another cluster. The only potential downside is that it is tightly coupled with Kafka, but in the modern world where most if not all real-time processing is powered by Kafka that may not be a big disadvantage.

When would you use Kafka?
As we already covered, Kafka allows you to have a huge amount of messages go through a centralized medium and store them without worrying about things like performance or data loss.

This means it is perfect for use as the heart of your system’s architecture, acting as a centralized medium that connects different applications. Kafka can be the center piece of an event-driven architecture and allows you to truly decouple applications from one another.


Kafka allows you to easily decouple communication between different (micro)services. With the Streams API, it is now easier than ever to write business logic which enriches Kafka topic data for service consumption. The possibilities are huge and I urge you to explore how companies are using Kafka.

Why has it seen so much use?
High performance, availability and scalability alone are not strong enough reasons for a company to adopt a new technology. There are other systems which boast similar properties, but none have become so widely used. Why is that?

The reason Kafka has grown in popularity (and continues to do so) is one key thing — businesses nowadays benefit greatly from event-driven architecture. This is because the world has changed — an enormous (and ever-growing) amount of data is being produced and consumed by many different services (Internet of Things, Machine Learning, Mobile, Microservices).

A single real-time event broadcasting platform with durable storage is the cleanest way to achieve such an architecture. Imagine what kind of a mess it would be if streaming data to/from each service used a different technology specifically catered to it.

This, paired with the fact that Kafka provides the appropriate characteristics for such a generalized system (durable storage, event broadcast, table and stream primitives, abstraction via KSQL, open-source, actively developed) make it an obvious choice for companies.

Summary
Apache Kafka is a distributed streaming platform capable of handling trillions of events a day. Kafka provides low-latency, high-throughput, fault-tolerant publish and subscribe pipelines and is able to process streams of events.

We went over its basic semantics (producer, broker, consumer, topic), learned about some of its optimizations (pagecache), learned how it’s fault-tolerant by replicating data and were introduced to its ever-growing powerful streaming abilities.

Kafka has seen large adoption at thousands of companies worldwide, including a third of the Fortune 500. With the active development of Kafka and the recently released first major version 1.0 (1st November, 2017), there are predictions that this Streaming Platform is going to be as big and central of a data platform as relational databases are.

I hope that this introduction helped familiarize you with Apache Kafka and its potential.

Further Reading Resources & Things I did not mention
The rabbit hole goes deeper than this article was able to cover. Here are some features I did not get the chance to mention but are nevertheless important to know:

Controller Broker, in-sync replicas — The way in which Kafka keeps the cluster healthy and ensures adequate consistency and durability.

Connector API — API helping you connect various services to Kafka as a source or sink (PostgreSQL, Redis, ElasticSearch)

Log Compaction — An optimization which reduces log size. Extremely useful in changelog streams

Exactly-once Message Semantics — Guarantee that messages are received exactly once. This is a big deal, as it is difficult to achieve.

Resources
Apache Kafka’s Distributed Systems Firefighter — the Controller Broker — Another blog post of mine where I dive into how coordination between the broker works and much more.

Confluent Blog — a wealth of information regarding Apache Kafka

Kafka Documentation — Great, extensive, high-quality documentation

Kafka Summit 2017 videos

Thank you for taking the time to read this.

If you think this information was helpful, please consider giving it a hefty amount of claps to increase its visibility and help new people find it!

~Stanislav Kozlovski

Update
This article opened the door for me to join Confluent. I am immensely grateful for the opportunity they have given me — I currently work on Kafka itself, which is beyond awesome! Confluent is a big data company founded by the creators of Apache Kafka themselves! We currently work on the whole Kafka ecosystem, including a managed Kafka-as-a-service cloud offering.

We are hiring for a lot of positions (especially SRE/Software Engineers) in Europe and the USA! If you are interested in working on Kafka itself, looking for new opportunities or just plain curious — make sure to message me on Twitter and I will share all the great perks that come from working in a bay area company.