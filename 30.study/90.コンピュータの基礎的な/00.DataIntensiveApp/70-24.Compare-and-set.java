■■■■■■■■■■■■■■■■■■■■■■■■■■ Compare-and-set
▼ compare- and-set operation
・更新を許可するのは下記条件下のみ
前回その値を読み込んでから、変更がなされていない値であること
 →これで、ロストアップデートを回避する

In databases that don’t provide transactions,
 you sometimes find an atomic compare- and-set operation
(previously mentioned in “Single-object writes” on page 230).

The purpose of this operation is
to avoid lost updates
by allowing an update to happen
only if the value has not changed since you last read it.

・もし前回呼んだときの値とマッチしなかったら、更新はなされず、
 →read- modify-write cyclesがリトライされる
If the current value does not match
what you previously read,
the update has no effect,
 cycle must be retried.

・二人のユーザーが同じWikiを同時更新することを避けたい場合
For example,
to prevent two users concurrently updating the same wiki page,
you might try something like this,
・編集開始時から、値が変更されていない場合に限り、更新がなされる
expecting the update to occur
only if
the content of the page hasn’t changed since the user started editing it:


▼ -- This may or may not be safe, depending on the database implementation
-------------------------------------------------

UPDATE wiki_pages SET content = 'new content' WHERE id = 1234 AND content = 'old content';
-------------------------------------------------
▼ 更新されないケースに備え、更新がなされたかどうか確認し、必要に応じてリトライするロジックが必要
If the content has changed
and no longer matches 'old content',
this update will have no effect,
so you need to check
whether the update took effect and retry if necessary.

▼ compare-and-set が安全じゃないケース
・DBが古いスナップショットからの読み込み時に WHERE 句を許可している場合
 →ロストアップデートは防げないかもしれない
なぜか
他の同時書き込みが起こっている時も、
条件式の結果が true となる場合がある。
よって、
リトライ前に
そのDBの compare-and-set が安全かどうかをチェックする必要がある。

the condition may be true even though another concurrent write is occurring.
Check whether your database’s compare-and-set operation is safe
before relying on it.
