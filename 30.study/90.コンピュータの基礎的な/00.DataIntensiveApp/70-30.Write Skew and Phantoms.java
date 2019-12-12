■■■■■■■■■■■■■■■■■■■■■■■■■■ Write Skew and Phantoms


In the previous sections we saw dirty writes and lost updates,
two kinds of race conditions that can occur
when different transactions concurrently try to write to the same objects.

In order to avoid data corruption,
those race conditions need to be prevented
—either automatically by the database,
or by manual safeguards such as using locks or atomic write operations.


▼ 競合状態はまだまだいろんなケースがあります
However, that is not the end of the list of potential race conditions
that can occur between concurrent writes.
In this section we will see some subtler examples of conflicts.

■■■■■■■■■■■■■■■■■■■■■■■■■■ write skew
▼ 例：少なくとも一人がオンコールでなきゃいけないのに、二人同時でオンコールを抜ける更新をしたため、
オンコールが0 となってしまった
病院の on-call shifts 電話がなったら夜でも昼でも対応必要シフト の管理アプリ
To begin, imagine this example:
you are writing an application for doctors
to manage their on-call shifts at a hospital.

▼ 通常二人。少なくとも一人はオンコールシフトじゃないといけない。 // 一人が病気になっても大丈夫なように
The hospital usually tries to have several doctors on call at any one time,
but it absolutely must have at least one doctor on call.
Doctors can give up their shifts
(e.g., if they are sick themselves),
provided that at least one colleague remains on call in that shift [40, 41].

Now imagine that Alice and Bob are the two on-call doctors for a particular shift.
Both are feeling unwell,
so they both decide to request leave.
Unfortunately, they happen to click the button to go off call at approximately the same time.
What happens next is illustrated in Figure 7-8.

In each transaction,
your application first checks that two or more doctors are currently on call;
if yes, it assumes it’s safe for one doctor to go off call.
Since the database is using snapshot isolation, both checks return 2,
so both transactions proceed to the next stage.

Alice updates her own record to take herself off call,
and Bob updates his own record likewise.
Both transactions commit, and now no doctor is on call.
Your requirement of having at least one doctor on call has been violated.
