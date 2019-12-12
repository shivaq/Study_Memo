■■■■■■■■■■■■■■■■■■■■■■■■■■ More examples of write skew

▼ write skew は、稀なケースと思うかもだが、結構ある
Write skew may seem like an esoteric issue at first,
but once you’re aware of it,
you may notice more situations in which it can occur.
Here are some more examples:

▼ Meeting room booking system

Say you want to enforce that
there cannot be two bookings for the same meeting room at the same time
When someone wants to make a booking,
you first check for any conflicting bookings (i.e., bookings for the same room with an overlapping time range),
and if none are found,
you create the meeting (see Example 7-2).ix

Example 7-2. A meeting room booking system tries to avoid double-booking (not safe under snapshot isolation)

-------------------------------------------------

BEGIN TRANSACTION;

    -- Check for any existing bookings that overlap with the period of noon-1pm
SELECT COUNT(*) FROM bookings WHERE room_id = 123 AND
end_time > '2015-01-01 12:00' AND start_time < '2015-01-01 13:00';

    -- If the previous query returned zero:
INSERT INTO bookings
(room_id, start_time, end_time, user_id)
VALUES (123, '2015-01-01 12:00', '2015-01-01 13:00', 666);
COMMIT;
-------------------------------------------------

▼ snapshot isolation も同時ブッキングを防げない
▼ serializable isolation が必要だ。
Unfortunately, snapshot isolation does not prevent another user from concurrently inserting a conflicting meeting.

In order to guarantee you won’t get scheduling conflicts,
 you once again need serializable isolation.

▼ Multiplayer game
In Example 7-1, の例では、
同じ ブツ を別のプレイヤーが同時に動かすことはできないよう、ロックできる。
異なる ブツ を別のプレイヤーが同じ維持に動かすことは防げない。

we used a lock to prevent lost updates
(that is, making sure that two players can’t move the same figure at the same time).
However,
the lock doesn’t prevent players from
moving two different figures to the same position on the board
or potentially making some other move
that violates the rules of the game.

Depending on the kind of rule you are enforcing,
you might be able to use a unique constraint,
but otherwise you’re vulnerable to write skew.

▼ Claiming a username
・同じユーザー名を別のユーザーが同時に設定することを防ぐ
On a website
where each user has a unique username,
two users may try to create accounts with the same username at the same time.
You may use a transaction to check
whether a name is taken and, if not,
create an account with that name.

However, like in the previous examples,
that is not safe under snapshot isolation.

Fortunately,
a unique constraint is a simple solution here
・下記で解決できる
(the second transaction
that tries to register the username will be aborted
due to violating the constraint).

▼ Preventing double-spending
A service that allows users to spend money or points
needs to check that a user doesn’t spend more than they have.

You might implement this by
inserting a tentative spending item into a user’s account,
listing all the items in the account,
and checking that the sum is positive.

With write skew, it could happen that
two spending items are inserted concurrently that together
cause the balance to go negative,
but that neither transaction notices the other.
