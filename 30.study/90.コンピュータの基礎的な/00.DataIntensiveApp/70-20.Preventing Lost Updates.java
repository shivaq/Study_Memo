■■■■■■■■■■■■■■■■■■■■■■■■■■ Preventing Lost Updates
The read committed and snapshot isolation levels が保証するのは、
read-only transaction が、
in the presence of concurrent writes
の時に何を見ることができるか、ということ。


▼ concurrently writing transactionsのコンフリクトの主な例
▼ the lost update problem
・illusrated in Figure 7-1 with the example of two concurrent counter increments.

▼ アップデート欠損問題が起こる条件
if an application reads some value from the database,
modifies it,
and writes back the modified value
(a read-modify-write cycle).

If two transactions do this concurrently,
one of the modifications can be lost,
because the second write does not include the first modification.
(We sometimes say that the later write clobbers the earlier write.)


▼ アップデート欠損問題が起こる例

• カウンターをインクリメント、残高更新
Incrementing a counter or updating an account balance
(requires reading the current value, calculating the new value, and writing back the updated value)

• ローカルで複雑な値の変更を行う
e.g., adding an element to a list within a JSON document
(requires parsing the document, making the change, and writing back the modified document)

• 二人のユーザーがWIKIページを同時に編集して、一方がページ全体を上書き
Two users editing a wiki page at the same time,
where each user saves their changes by sending the entire page contents to the server,
overwriting whatever is currently in the database
