■■■■■■■■■■■■■■■■■■■■■■■■■■ Automatically detecting lost updates

▼ Atomic operations and locks  は、read- modify-write cycles をシークエンシャルになされるよう強いるための方法
Atomic operations and locks
are ways of preventing lost updates
by forcing the read- modify-write cycles to happen sequentially.

▼ パラレルを許容して、更新ロスト検知時に、トランザクション破棄 →サイクルリトライ
An alternative is to
allow them to execute in parallel
and,
if the transaction manager detects a lost update,
 →abort the transaction and
  →force it to retry its read-modify-write cycle.

▼ Snapshot Isolation と組み合わせて効率的に実施可能
An advantage of this approach is that
databases can perform this check efficiently
in conjunction with snapshot isolation.
▼ 例えばこのエンジンたち
Indeed,
PostgreSQL’s repeatable read, Oracle’s serializable, and SQL Server’s snapshot isolation levels
automatically detect
when a lost update has occurred
and abort the offending transaction.

However,
MySQL/ InnoDB’s repeatable read does not detect lost updates [23].
Some authors [28, 30] argue that
a database must prevent lost updates
in order to qualify as providing snapshot isolation,
so MySQL does not provide snapshot isolation under this definition.

▼ ロストアップデート検知は、アプリコードいらずで、自動でなされるからエラーも入り込みにくい
Lost update detection is a great feature,
because
it doesn’t require application code to use any special database features—
you may forget to use a lock or an atomic operation
and thus introduce a bug,
but lost update detection happens automatically and is thus less error-prone.
