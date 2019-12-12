■■■■■■■■■■■■■■■■■■■■■■■■■■ Explicit locking
▼ DB の atomic operations がいけてないなら、アプリ側でなんとかする
Another option for preventing lost updates,
if the database’s built-in atomic operations don’t provide the necessary functionality,
is for the application
to explicitly lock objects that are going to be updated.

Then the application can perform a read- modify-write cycle,
and if any other transaction tries to concurrently read the same object,
it is forced to wait until the first read-modify-write cycle has completed.

▼ マルチプレイヤーゲームの例
For example,
consider a multiplayer game
in which several players can move the same figure concurrently.

In this case,
an atomic operation may not be sufficient,
 because the application also needs to ensure that
 a player’s move abides by
 the rules of the game,
 which involves some logic
 that you cannot sensibly implement as a database query.

 Instead,
 you may use a lock to prevent two players
 from concurrently moving the same piece, as illustrated in Example 7-1.


Example 7-1. Explicitly locking rows to prevent lost updates
-------------------------------------------------
BEGIN TRANSACTION;

SELECT * FROM figures WHERE name = 'robot' AND game_id = 222 FOR UPDATE;

-- Check whether move is valid, then update the position -- of the piece
that was returned by the previous SELECT.

UPDATE figures SET position = 'c4' WHERE id = 1234;

COMMIT;
-------------------------------------------------
▼ FOR UPDATE 句
The FOR UPDATE clause indicates that

the database should take a lock on all rows
returned by this query.
This works,
but to get it right,
you need to carefully think about your application logic.
It’s easy to forget to add a necessary lock
somewhere in the code, and thus introduce a race condition.
