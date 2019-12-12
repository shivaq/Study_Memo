■■■■■■■■■■■■■■■■■■■■■■■■■■ Characterizing write skew

▼ これは汚い書き込みでも、更新ロストでもない。
This anomaly is called write skew .
It is neither a dirty write nor a lost update, because the two transactions are updating two different objects
(Alice’s and Bob’s on- call records, respectively).
It is less obvious that a conflict occurred here,
but it’s definitely a race condition:

▼ 同時に起きなければ起きなかった問題なのです
if the two transactions had run one after another,
the second doctor would have been prevented from going off call.
The anomalous behavior was only possible
because the transactions ran concurrently.

▼ 特定の値を同時更新ではなく、別々のレコードの値を同時更新した結果、その列のカウントが制約をすり抜けて変わってしまった
You can think of write skew as a generalization of the lost update problem.
Write skew can occur
if two transactions read the same objects,
and then update some of those objects (different transactions may update different objects).
In the special case
where different transactions update the same object,
you get a dirty write or lost update anomaly (depending on the timing).
We saw that
there are various different ways of preventing lost updates.
With write skew, our options are more restricted:

■■■■■■■■■■■■■■■■■■■■■■■■■■ write skew を防ぐ方法が難しい理由
・複数のOBJ が絡んでくるため、Atomic single-object operations don’t help

・write skew の自動検知は真の serializable isolation が必要なため、
The automatic detection of lost updates
that you find in some implementations of snapshot isolation
は機能しない。
下記はいずれも検知できない
PostgreSQL’s repeatable read, MySQL/InnoDB’s repeatable read, Oracle’s serializable, or SQL Server’s snapshot isolation level.


▼ DB によっては制約を設定できる。しかし、write skew は複数のOBJ が絡んでくる。ほとんどのDBはそんなケースをビルトインではサポートしていない。
• Some databases allow you to configure constraints,
which are then enforced by the database
 (e.g., uniqueness, foreign key constraints, or restrictions on a partic‐ ular value).
 However, in order to specify that
 at least one doctor must be on call,
 you would need a constraint that involves multiple objects.
 Most databases do not have built-in support for such constraints,
 but you may be able to implement them with
 triggers or materialized views, depending on the database


▼ serializable isolation level がサポートされていないなら、トランザクションが依存する行を明示的にロックするとか
• If you can’t use a serializable isolation level,
the second-best option in this case is
probably to explicitly lock the rows
that the transaction depends on.
In the doctors example, you could write something like the following:

■■■■■■■■■■■■■■■■■■■■■■■■■■ FOR UPDATE で、このクエリで返る行をロックするようDBに指定する
-------------------------------------------------
BEGIN TRANSACTION;

SELECT * FROM doctors
WHERE on_call = true
AND shift_id = 1234 FOR UPDATE;

UPDATE doctors
SET on_call = false WHERE name = 'Alice' AND shift_id = 1234;

COMMIT;
-------------------------------------------------
