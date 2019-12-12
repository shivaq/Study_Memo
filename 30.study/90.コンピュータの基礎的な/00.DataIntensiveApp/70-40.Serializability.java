■■■■■■■■■■■■■■■■■■■■■■■■■■ Serializability

▼ トランザクション の競合状態の解決は難しい
In this chapter
we have seen several examples of transactions that are prone to race conditions.
Some race conditions are prevented
by the read committed and snapshot isolation levels,
but others are not.
We encountered some particularly tricky examples with write skew and phantoms.
 It’s a sad situation:
• Isolation levels are hard to understand,
and inconsistently implemented in different databases (e.g., the meaning of “repeatable read” varies significantly).
• If you look at your application code, it’s difficult to tell
whether it is safe to run at a particular isolation level
—especially in a large application,
where you might not be aware of all the things that may be happening concurrently.
• There are no good tools to help us detect race conditions.
In principle, static analysis may help, but research techniques have not yet found their way into practical use.
Testing for concurrency issues is hard,
because they are usually nondeterministic—problems only occur if you get unlucky with the timing.

■■■■■■■■■■■■■■■■■■■■■■■■■■ serializable isolation つこたらええがな！
This is not a new problem
—it has been like this since the 1970s,
]when weak isolation levels were first introduced
All along, the answer from researchers has been simple: use serializable isolation!


▼ 最も強い Isolation レベル
Serializable isolation is usually regarded as the strongest isolation level.

It guarantees that even though transactions may execute in parallel,
the end result is the same
as if they had executed one at a time, serially, without any concurrency.

Thus, the database guarantees that
if the transactions behave correctly when run individually,
they continue to be correct when run concurrently
—in other words,
the database prevents all possible race conditions.


■■■■■■■■■■■■■■■■■■■■■■■■■■ どうして 全員が serializable isolation を使わないのだ？それには理由がある。もちろん。
But if serializable isolation is so much better than the mess of weak isolation levels,
then why isn’t everyone using it?
To answer this question, we need to look at the options for implementing serializability,
and how they perform.
 Most databases that provide serializability today
 use one of three techniques, which we will explore in the rest of this chapter:

 ▼ serializability が使っている主なテクニック
 ・トランザクションを実際にシリアルな順番で実行する
 ・Two-phase locking
 ・ Optimistic concurrency control techniques such as
 serializable snapshot isolation
