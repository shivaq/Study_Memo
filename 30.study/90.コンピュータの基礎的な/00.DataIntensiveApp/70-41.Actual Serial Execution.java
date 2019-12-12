■■■■■■■■■■■■■■■■■■■■■■■■■■ Actual Serial Execution
▼ トランザクションを、単一スレッドで、ひとつずつ、順番にじっこうする。
The simplest way of avoiding concurrency problems is
to remove the concurrency entirely:
to execute only one transaction at a time, in serial order, on a single thread.


By doing so, we completely sidestep the problem of detecting and preventing conflicts between transactions:
the resulting isolation is by definition serializable.

▼ 2007 年まで、シリアライザブルは導入されなかった。
Even though this seems like an obvious idea,
database designers only fairly recently— around 2007—decided that a single-threaded loop for executing transactions was feasible
If multi-threaded concurrency was considered essential for getting good performance during the previous 30 years,
what changed to make single-threaded execution possible?
Two developments caused this rethink:

▼ いままでシリアライザブルできなかったのができるようになった理由

• RAM が安くなって、アクティブなデータセット全体をメモリに格納することが feasible になってきた
RAM became cheap enough
that for many use cases is now feasible to keep the entire active dataset in memory
When all data that a transaction needs to access is in memory,
transactions can execute much faster than if they have to wait for data to be loaded from disk.


• DBの設計者たちが、OLTPトランザクションはたいてい短くで読み書きの数も少ないことに気づいた
Database designers realized that OLTP transactions are usually short
and only make a small number of reads and writes
By contrast, long-running analytic queries are typically read- only,
so they can be run on a consistent snapshot (using snapshot isolation) outside
of the serial execution loop.

▼ トランザクションがシリアルに行われるアプローチが実装されているやつら
The approach of executing transactions serially
is implemented in VoltDB/H-Store, Redis, and Datomic [46, 47, 48].


 A system designed for single-threaded execution
 can sometimes perform better than a system that supports concurrency,
 because it can avoid the coordination overhead of locking.
 However, its throughput is limited to that of a single CPU core.
 In order to make the most of that single thread, transactions need to be structured differently from their traditional form.

 ■■■■■■■■■■■■■■■■■■■■■■■■■■ Encapsulating transactions in stored procedures

▼ DB黎明期、トランザクションはユーザーのアクティビティのフロー全体を一つのトランザクションに入れ込もうとしていた
In the early days of databases,
the intention was that a database transaction could encompass an entire flow of user activity.

For example,
booking an airline ticket is a multi-stage process
(searching for routes, fares, and available seats; deciding on an itinerary; booking seats on each of the flights of the itinerary;
entering passenger details; making payment).

Database designers thought that
it would be neat
if that entire process was one transaction so that it could be committed atomically.

▼ 人の入力操作はとても遅いので、下手したら大量の同時トランザクション対応が必要になる
Unfortunately, humans are very slow to make up their minds and respond.
If a database transaction needs to wait for input from a user,
the database needs to support a potentially huge number of concurrent transactions,
most of them idle.


Most databases cannot do that efficiently,
and so almost all OLTP applications keep transactions short
by avoiding interactively waiting for a user within a transaction.




▼ HTTP ではトランザクションは単一リクエスト内にしか収まらないようにする。複数のリクエストに跨がらせない
 On the web, this means that
  a transaction is committed within the same HTTP request—a transaction does not span multiple requests.
  A new HTTP request starts a new transaction.




▼ 人間の入力が終わったあとでも、トランザクションは interactive client/server style を続けている
Even though the human has been taken out of the critical path,
transactions have continued to be executed in an interactive client/server style,
one statement at a time.



▼ クエリはアプリのコードとDBサーバとの間を何度も行き来する
An application makes a query, reads the result, perhaps makes another query depending on the result of the first query, and so on.
The queries and results are sent back and forth between the application code
(running on one machine) and the database server (on another machine).


▼ こういうインタラクティブスタイルのトランザクションでは、アプリDB間のネットワーク更新に多くの時間が割かれている
In this interactive style of transaction,
a lot of time is spent in network communication between the application and the database.

▼ インタラクティブスタイルで、かつDBが同時処理を許可しない場合、スループットは恐ろしいことになる
If you were to disallow concurrency in the database
and only process one transaction at a time,
the throughput would be dreadful
なぜならば
because the database would spend most of its time waiting for the application to issue the next query for the current transaction.


▼ 競争力のあるパフォーマンスを得る多面井は、DBは複数トランザクション同時処理ができないと困る
In this kind of database, it’s necessary to process multiple transactions concurrently in order to get reasonable performance.

For this reason, systems with single-threaded serial transaction processing
don’t allow interactive multi-statement transactions.

▼ どうするか
アプリはトランザクションコード全体を、(a stored procedure)として事前にサブミットする
Instead,
the application must submit the entire transaction code to the database ahead of time,
as a stored procedure.

The differences between these approaches is illustrated in Figure 7-9.
Provided that
トランザクションに必要な全てのデータをメモリに格納することで、
the stored procedure をすばやく終わらせる
all data required by a transaction is in memory,
the stored procedure can execute very fast, without waiting for any network or disk I/O.



■■■■■■■■■■■■■■■■■■■■■■■■■■ Pros and cons of stored procedures
Stored procedures have existed for some time in relational databases,
 and they have been part of the SQL standard (SQL/PSM) since 1999.
 They have gained a somewhat bad reputation, for various reasons:

▼ Stored procedures の評判が悪い理由

▼ 各DBエンジンの自家製言語のStorede procedure は、汚い。流行遅れ。
• Each database vendor has its own language for stored procedures
(Oracle has PL/ SQL, SQL Server has T-SQL, PostgreSQL has PL/pgSQL, etc.).
These languages haven’t kept up with developments in general-purpose programming languages,
so they look quite ugly and archaic from today’s point of view,
and they lack the ecosystem of libraries that you find with most programming languages.



▼ DB 内で走るコードの管理は困難。バージョンコントロールとか、モニタリングとか、どうすんだ？
Code running in a database is difficult to manage:
compared to an application server, it’s harder to debug,
more awkward to keep in version control and deploy,
trickier to test,
and difficult to integrate with a metrics collection system for monitoring.




▼ DBは複数のアプリサーバに共有されたりしてるので、アプリのコードがひどい場合よりも、ひどい結果を、ひどいDBコードはもたらす
• A database is often much more performance-sensitive than an application server,
because a single database instance is often shared by many application servers.
A badly written stored procedure (e.g., using a lot of memory or CPU time)
in a database can cause much more trouble than equivalent badly written code in an application server.


■■■■■■■■■■■■■■■■■■■■■■■■■■ 上記悪評は克服されつつある
However, those issues can be overcome.
 Modern implementations of stored procedures have abandoned PL/SQL
 and use existing general-purpose programming languages instead:

 VoltDB uses Java or Groovy, Datomic uses Java or Clojure, and Redis uses Lua.


With stored procedures and in-memory data,
executing all transactions on a single thread becomes feasible.
As they don’t need to wait for I/O
and they avoid the overhead of other concurrency control mechanisms,
they can achieve quite good throughput on a single thread.


▼ VoltDB also uses stored procedures for replication:
トランザクションの書き込みを、あるノードから別のノードにコピーする
代わりに、
各ノードで同じ stored procedure を実行する
VoltDB therefore requires that stored procedures are deterministic
 (when run on different nodes, they must produce the same result).
 If a transaction needs to use the current date and time,
 for example,
 it must do so through special deterministic APIs.
