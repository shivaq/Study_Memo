■■■■■■■■■■■■■■■■■■■■■■■■■■ Phantoms causing write skew
 ▼ write skew 共通パターン
-------------------------------------------------
1. SELECT クエリがある要件を満たしているかどうかをチェックする
A SELECT query checks whether some requirement is satisfied
by searching for rows that match some search condition
(there are at least two doctors on call,
there are no existing bookings for that room at that time,
the position on the board doesn’t already have another figure on it,
the username isn’t already taken, there is still money in the account).


2. 最初のクエリの結果に基づいて、アプリがそのまま進めるか、エラーと報告するかを判断する
Depending on the result of the first query,
the application code decides how to continue
(perhaps to go ahead with the operation,
or perhaps to report an error to the user and abort).


3. アプリがそのまま進めると判断したら、DBへの書き込みをして、トランザクションをコミットする。
If the application decides to go ahead,
makes a write (INSERT, UPDATE, or DELETE) to the database and commits the transaction.
-------------------------------------------------

上記１，２，３を行った結果、
次に SELECT クエリを行うとき、Step ２ の precondition が変化する。

The effect of this write
changes the precondition of the decision of step 2.
In other words,
if you were to repeat the SELECT query from step 1 after commiting the write,
you would get a different result,
 because the write changed the set of rows matching the search condition
 (there is now one fewer doctor on call,
 the meeting room is now booked for that time,
 the position on the board is now taken by the figure that was moved,
 the username is now taken,
 there is now less money in the account).

上記 １．２．３ は別の順番でなされる場合もある。
まず書き込んで、SELECT クエリして、トランザクションを破棄するかコミットするかを判断する

▼ ドクターのオンコールの例の場合は、１．２．３ の変形で問題解決できる
In the case of the doctor on call example,
the row being modified in step 3 was one of the rows returned in step 1,
so we could make the transaction safe and avoid write skew
 by locking the rows in step 1 (SELECT FOR UPDATE).

▼ 他の例はドクターのオンコールみたいにはいかない
However,
the other four examples are different:
・検索結果にマッチする行がないことをチェック
・上記マッチ条件と同じ行を追加する
・ステップ1 が行を返さなかった場合、SELECT FOR UPDATE がロックをする対象もないからロックできない

▼ ファントム！
あるトランザクションの書き込みが、他のトランザクションのサーチクエリ結果を返る効果のことを、
ファントムと呼ぶ

▼ リードオンリーならいいが、リードライトトランザクションの場合、ファントムがトリッキーな 書き込み歪みをしょうじさせる
. Snapshot isolation avoids phantoms in read-only queries,
 but in read-write transactions like the examples we discussed,
  phantoms can lead to particularly tricky cases of write skew.




■■■■■■■■■■■■■■■■■■■■■■■■■■ Materializing conflicts
ファントムの引き起こす問題が、ロックをアタッチするOBJが存在しないことならば、
人為的にロックOBJ をDBに追加できないの？

▼ 会議室予約の場合
For example, in the meeting room booking case
you could imagine creating a table of time slots and rooms.
Each row in this table corresponds to a particular room for a particular time period
(say, 15 minutes).
You create rows for all possible combinations of rooms
and time periods ahead of time, e.g. for the next six months.

Now a transaction that wants to create a booking
can lock (SELECT FOR UPDATE)
the rows in the table that correspond to the desired room and time period.
After it has acquired the locks,
it can check for overlapping bookings
and insert a new booking as before.
Note that the additional table isn’t used to store information about the booking—
it’s purely a collection of locks which is used to prevent bookings on the same room
and time range from being modified concurrently.

▼ (ファントム)コンフリクトを物質化！
This approach is called materializing conflicts,
because it takes a phantom and
turns it into a lock conflict on a concrete set of rows
that exist in the database.

▼ 物質化はエラープローンで、アプリ側への実装は醜い
Unfortunately,
it can be hard and error-prone to figure out
how to materialize conflicts,
and it’s ugly to let a concurrency control mechanism leak into the application data model.

▼ コンフリクト物質化はラストリゾート
For those reasons,
materializing conflicts should be considered a last resort
if no alternative is possible.
A serializable isolation level is much preferable in most cases.
