■■■■■■■■■■■■■■■■■■■■■■■■■■ Read Committed
一番基本レベルの トランザクションIsolation
read committed

2つのことを保証する
1.DBから読み込むときは、コミット済みのデータだけを見ることになる(no dirty reads)
2.DBに書き込むときは、コミット済みのデータだけを上書きすることになる(no dirty writes)


■■■■■■■■■■■■■■■■■■■■■■■■■■ No dirty reads
例えば、
あるトランザクションがDBに書き込みをして、
かつ
そのトランザクションはまだコミットもアボートもしていないとする。
他のトランザクションがそのコミットされていないデータを見ることができたとしたら、
それは dirty read と呼ばれる。

the read committed isolation level で走るトランザクションは、dirty reads を防がなくてはならない。
このことが意味するのは、
This means that
any writes by a transaction only become visible to others
when that transaction commits
その後、その際の書き込みは全部一度に visible になる。

▼ dirty reads を防ぐとよい理由
・もしトランザクションが複数の OBJ を更新する場合、
dirty reads は、他のトランザクションが、更新の一部だけをみて、ほかは見えない、といった事を生じさせる。
新メールがあることがユーザーから見えているけど、未読カウンターは増えていないとか。
DBのデータが一部しか既往心反映されていない場合、他のトランザクションが誤って行われることもあり得る。

・もしトランザクションが破棄され、その間の書き込みがロールバックされなければならない場合、
dirty reads ができる状態だと、
ロールバックされることが決まっていて、DBには決してコミットされることのないデータを、トランザクションが目撃することになる。

■■■■■■■■■■■■■■■■■■■■■■■■■■ No dirty writes
もし、
2つのトランザクションが同時に同じDBの同じOBJ を更新しようとしたらどうなる？
どんな順番で書き込みが起きるか予測できない。
しかし、まあ、普通はあとの書き込みが、前の書き込みを上書きすると考えられるだろう。
しかし、
先の書き込みがまだコミットされていないトランザクションの一部で、
あとの書き込みが、コミットされていない値を上書きしたら？
これが、 dirty writes と呼ばれる。

Transactions running at the read committed isolation level
must prevent dirty writes,
usually by delaying the second write until the first write’s transaction has committed or aborted.

▼ dirty writes が引き起こす問題
・中古車販売サイトで、ボブとアリスが同じ車を同時に購入しようとしたとする。
車を買うには2つのDB書き込みが必要だとする。
Webサイト上で、購入者情報を反映させる書き込み
購入者に送られる sales invoice の情報の書き込み
dirty writes が発生すると、
購入者情報テーブルはボブが表示され、インボイスはアリスに送られる、ということが起こりうる。

 Read committed はそういった問題を防ぎます。

・だが、 Read committed は次の問題を防げない
一つのカウンターを2つのクライアントがインクリメントする問題
二度目の書き込みが、最初の書き込みがコミットされたあとで起きる。よって、dirty writes ではない。
それでもその値は正確ではないままだ。in Figure 7-1
この問題は後述する

■■■■■■■■■■■■■■■■■■■■■■■■■■ Implementing read committed
Read committed は Isolation レベルとしてとても人気？だ。
Oracle 11g, PostgreSQL, SQL Server 2012, MemSQL, and many other databases ではデフォルト。

▼ dirty writes はどのように防がれるか
殆どの場合、DBは dirty writes は row-level locks によって防がれる。

when a transaction wants to modify a particular object (row or document),
it must first acquire a lock on that object.

It must then hold that lock until the transaction is committed or aborted.

Only one transaction can hold the lock for any given object;
if another transaction wants to write to the same object,
it must wait until the first transaction is committed
or aborted before it can acquire the lock and continue.

上記ロックは、read committed が有効状態のDBでは、自動でなされる。

▼ dirty reads はどのように防がれるか
One option would be to use the same lock,
and to require any transaction that wants to read an object
to briefly acquire the lock and then release it again immediately after reading.

This would ensure that a read couldn’t happen while an object has a dirty, uncommitted value
(because during that time the lock would be held by the transaction that has made the write).

▼ read locks を使うアプローチはうまくいかない
時間のかかる書き込みトランザクションが、読み込みだけのトランザクションを長い時間待たせることになる

However, the approach of requiring read locks does not work well in practice,
because
one long-running write transaction can force many read-only transactions to wait until the long-running transaction has completed.
This harms the response time of read-only transactions
and is bad for operability:
a slowdown in one part of an application can have a knock-on effect
in a completely different part of the application, due to waiting for locks.

▼ dirty reads を防ぐ一般的な方法
for every object that is written,
the database remembers both
the old committed value
and the new value set by the transaction that currently holds the write lock.

While the transaction is ongoing,
any other transactions that read the object are simply given the old value.

Only when the new value is committed do transactions switch over to reading the new value.
