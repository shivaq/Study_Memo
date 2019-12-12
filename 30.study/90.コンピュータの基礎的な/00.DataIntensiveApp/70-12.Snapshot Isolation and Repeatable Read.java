■■■■■■■■■■■■■■■■■■■■■■■■■■ Snapshot Isolation and Repeatable Read

▼ read committed isolation は効果的ですよ、まあ。
If you look superficially at read committed isolation,
you could be forgiven for thinking that
it does everything that a transaction needs to do:
it allows aborts (required for atomicity),
it prevents reading the incomplete results of transactions, and it prevents concurrent writes from getting intermingled.

Indeed, those are useful features, and much stronger guarantees than you can get from a system that has no transactions.

▼ read committed を使っても、防げないバグはたくさんある
However, there are still plenty of ways
in which you can have concurrency bugs when using this isolation level.


▼ a nonrepeatable read or read skew
Say Alice has $1,000 of savings at a bank,
split across two accounts with $500 each.

Now a transaction transfers $100 from one of her accounts to the other.
If she is unlucky enough
to look at her list of account balances in the same moment
as that transaction is being processed,

アリスは入金が到着する前に一方のアカウントの残高を見るかもしれない// 残高500$
で、
もう一方のアカウントの残高を出金がなされたあとに見るかもしれない// 残高 $500

To Alice it now appears as though she only has a total of $900 in her accounts
—it seems that $100 has vanished into thin air.

この問題は、a nonrepeatable read or read skew と呼ばれる。
もしアリスがアカウント 1 のバランスを、トランザクションの最後に再度読み込んだら、
前回のクエリ時とは異なる値を目にするだろう。

▼ Read skew はread committed isolation がなされている場合であれば、許容できると思われる。
アリスが見た残高は、実際にはちゃんとコミットされているわけだから

アリスのケースでは、この問題は一時的なもので、数秒後にリロードすれば解決している。
だが、
こうした一時的 inconsistency が許容できないケースも有る

■■■■■■■■■■■■■■■■■■■■■■■■■■ 一時的な Read skew が許容できないケース
▼ Backups
バックアップでDB全体のコピーを取得するとき、何時間もかかる場合がある。
その時間中も、DBに書き込みはし続けられる。
こうして、
バックアップの一部は古いデータのママになったりする。
そのバックアップから復元する場合、
inconsistency は永続的なものになる。

▼ Analytic queries and integrity checks
DBの大きな部分をスキャンするようなクエリをしたい場合、
異なる期間のデータをスキャンすることになり、無意味な結果が帰ってくることになる。

■■■■■■■■■■■■■■■■■■■■■■■■■■ Snapshot isolation
Snapshot isolation は、上記のようなケースにとられる一般的な解決策
どういう方法かというと、
各トランザクションが、consistent snapshot of the database から読み込むようにする、というもの。

▼ Snapshot isolation は下記がサポートしている
it is supported by PostgreSQL, MySQL with the InnoDB storage engine, Oracle, SQL Server, and others


■■■■■■■■■■■■■■■■■■■■■■■■■■ Implementing snapshot isolation

▼ snapshot isolation 時は、write locks が使われる。よって、他の書き込みはブロックされる
Like read committed isolation,
implementations of snapshot isolation typically use write locks
to prevent dirty writes,
which means that a transaction that makes a write
can block the progress of another transaction that writes to the same object.




▼ 読み込みは書き込みをブロックしてはならない、書き込みは読み込みをブロックしてはならない、の規律
However, reads do not require any locks.
From a performance point of view, a key principle of snapshot isolation is
readers never block writers,
and writers never block readers.




▼ 書き込みを通常通りにおこないながら、時間のかかる読み込みクエリができる
This allows a database to handle long-running read queries on a consistent snapshot at the same time
as processing writes normally,
without any lock contention between the two.




▼ multi- version concurrency control (MVCC)
To implement snapshot isolation,
databases use a generalization of the mechanism we saw for preventing dirty reads in Figure 7-4.

The database must potentially keep
several different committed versions of an object,
because
various in-progress transactions
may need to see the state of the database
at different points in time.

Because it maintains several versions of an object side by side,
this technique is known as multi- version concurrency control (MVCC).



▼ スナップショットIsolation はいらない、ならば OBJ のバージョンは2つだけキープしとけば十分
If a database only needed to provide read committed isolation,
but not snapshot isolation, it would be sufficient to keep two versions of an object:

・コミットされたバージョン
・上書きされたけど、まだコミットされていないバージョン

▼ スナップショットIsolation をサポートするストレージエンジンは、通常 read committed Isolation レベルにも MVCC を使う
However, storage engines that support snapshot isolation typically use MVCC
for their read committed isolation level as well.
よくあるパターンは、
read committed は各クエリで別のスナップショットを使う
Snapshot Isolation は、トランザクション全体で同じスナップショットを使う


Figure 7-7 illustrates how MVCC-based snapshot isolation is implemented in PostgreSQL
・トランザクションが始まると、ユニークな transaction ID (txid) が付与される。

トランザクションがDBに書き込みをするときはいつだって、
書き込みデータにトランザクションIDがタグ付けされる。
テーブルの各行には created_by フィールドがあり、
その行をテーブルに追加したトランザクションの IDがその値に含まれる。

deleted_by フィールドもある。
あるトランザクションが Delete を行うと、
その行は実際には削除されず、deleted_by フィールドを txid で埋めることで、削除されたマーキングがなされる。
その後、
もうどんなトランザクションも削除マークのデータにアクセスしないだろうと思われるくらいあとで、
DB 内のGCプロセスが 削除マークの行を削除する。

更新時は、
内部では 削除 →作成 に変換される

For example, in Figure 7-7,
transaction 13 deducts $100 from account 2,
changing the balance from $500 to $400.

The accounts table now actually contains two rows for account 2:
a row with a balance of $500 which was marked as deleted by transaction 13,
and a row with a balance of $400 which was created by transaction 13.

■■■■■■■■■■■■■■■■■■■■■■■■■■ Visibility rules for observing a consistent snapshot
When a transaction reads from the database,
transaction IDs are used to decide
which objects it can see
and which are invisible.

▼ visibility ルール
を慎重に定義することで、
DBはアプリケーションに、DB のconsistent なスナップショットを提供することができる

▼ その動き
1. At the start of each transaction,
the database makes a list of all the other transactions
that are in progress (not yet committed or aborted) at that time.
Any writes that those transactions have made are ignored,
even if the transactions subsequently commit.


2. Any writes made by aborted transactions are ignored.


3. Any writes made by transactions with a later transaction ID
(i.e., which started after the current transaction started) are ignored,
regardless of
whether those transactions have committed.


4. All other writes are visible to the application’s queries.

-------------------------------------------------
これらのルールは、OBJの作成時、削除時ともに適用される

In Figure 7-7
トランザクション12 が アカウント2 から読み込みをする。
残高 $500
なぜなら、
$500 残高の削除作業は、トランザクション13 によって行われたから。// ルール3 より、トランザクション12 はトランザクション13 が行った削除を見ることができない
また、
残高 $400 の作成はまだ見えない // 同じルールによって

▼ 下記双方ともに true の場合は、OBJ は可視状態
・読み手のトランザクションが開始した時点で、OBJ を作成したトランザクションはコミット済みである
・OBJは削除マークされていない。または、マークされていたとしても、読み手のトランザクションが開始された時点で、削除をリクエストしたトランザクションはまだコミットされていない

A long-running transaction
may continue using a snapshot for a long time,
continuing to read values
that (from other transactions’ point of view) have long been overwritten or deleted.

値をその場で更新せず、値が変更されるたび、新しいバージョンを作成することで、
DBは小さなオーバーヘッドだけで、consistent snapshot を提供できる。



■■■■■■■■■■■■■■■■■■■■■■■■■■ Indexes and snapshot isolation
▼ マルチバージョンDBで、インデックスはどう機能するか
One option is to have the index
simply point to all versions of an object
and require an index query to filter out any object versions that are not visible to the current transaction.

When garbage collection removes old object versions that are no longer visible to any transaction,
the corresponding index entries can also be removed.

▼ よくあるアプローチ
In practice,
many implementation details determine
the performance of multi-version concurrency control.
For example,
PostgreSQL has optimizations for avoiding index updates
if different versions of the same object can fit on the same page.

▼ B-trees を使う人達の別のアプローチ
Another approach is used in CouchDB, Datomic, and LMDB.
Although they also use B-trees ,
they use an append-only/copy-on-write variant
that does not overwrite pages of the tree
when they are updated,
but instead creates a new copy of each modified page.

 Parent pages, up to the root of the tree,
 are copied and updated to point to the new versions of their child pages.
 Any pages that are not affected by a write
 do not need to be copied, and remain immutable

With append-only B-trees,
every write transaction (or batch of transactions) creates a new B-tree root,
and a particular root is a consistent snapshot of the database
at the point in time
when it was created.
There is no need to filter out objects based on transaction IDs
because subsequent writes cannot modify an existing B-tree;
they can only create new tree roots.
However, this approach also requires a background process for compaction and garbage collection.



■■■■■■■■■■■■■■■■■■■■■■■■■■ Repeatable read and naming confusion
Snapshot Isolation はリードオンリーのトランザクションでは特に、
有効な Isolation レベル





▼ Snapshot Isolation の呼び名はエンジンによって異なる
However, many databases that implement it call it by different names.
In Oracle it is called serializable,
and in PostgreSQL and MySQL it is called repeatable read [23].




▼ repeatable read の名称はよくない
Unfortunately, the SQL standard’s definition of isolation levels is flawed—
it is ambiguous, imprecise, and not as implementation-independent as a standard should be.

Even though several databases implement repeatable read,
there are big differences in the guarantees they actually provide,
despite being ostensibly standardized [23].
There has been a formal definition of repeatable read in the research literature [29, 30],
but most implementations don’t satisfy that formal definition.
And to top it off, IBM DB2 uses “repeatable read” to refer to serializability [8].

As a result, nobody really knows what repeatable read means.
