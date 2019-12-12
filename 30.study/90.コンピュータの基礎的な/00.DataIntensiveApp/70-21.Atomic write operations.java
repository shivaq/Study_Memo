■■■■■■■■■■■■■■■■■■■■■■■■■■ Atomic write operations

▼ アプリ側での read-modify-write cycles は不要
Many databases provide atomic update operations,
which remove the need to implement read-modify-write cycles in application code.

They are usually the best solution if your code can be expressed in terms of those operations.
For example,
the following instruction is concurrency-safe in most relational databases:

▼ ほとんどのRDBでは下記は concurrency セーフ
UPDATE counters SET value = value + 1 WHERE key = 'foo';

▼ Document DB の場合
Similarly,
document databases such as
MongoDB provide atomic operations
for making local modifications to a part of a JSON document,

and Redis provides atomic operations
for modifying data structures such as priority queues.

▼ アトミック オペレーションではできない書き込みだってある
Not all writes can easily be expressed in terms of atomic operations—
for example,
updates to a wiki page involve arbitrary text editing
—but in situations
where atomic operations can be used, they are usually the best choice.

▼ アトミック オペレーション AKA cursor stability の実装
Atomic operations are usually implemented by
taking an exclusive lock on the object
when it is read
so that
no other transaction can read it until the update has been applied.

This technique is sometimes known as cursor stability [36, 37].

▼ シングルスレッドでのオペ強制方法
Another option is
to simply force all atomic operations to be executed on a single thread.
▼ ORM で安全じゃない埋め込みがなされかねない
Unfortunately, object-relational mapping frameworks
make it easy to accidentally write code
that performs unsafe read-modify-write cycles
instead of using atomic operations provided by the database.

That’s not a problem if you know what you are doing,
but it is potentially a source of subtle bugs that are difficult to find by testing.
