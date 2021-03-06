■■■■■■■■■■■■■■■■■■■■■■■■■■ Monotonic Reads
▼ 異常事態例：moving backward in time
非同期フォロワーからの読み込み時に起こる。
ある読み込みの次の読んだものが、前の読み込みより古いステータスになるみたいな。
// it’s possible for a user to see things moving backward in time.
どういう場合に起こるか
ユーザーが、複数のレプリカから読み込みをする場合。
例えば
ユーザAが同じクエリを2度行う。
一度は遅延の少ないフォロワーに、
二度目は遅延の大きいフォロワーに。
// Webページをリフレッシュし、かつリクエストがランダムなサーバにルートされる時に起こりがち
最初のクエリは ユーザーBが最近追加したコメントが返されて、
二度目のクエリは何も返さない。
2つ目のクエリは、1つ目のクエリと比べて、システムのより早い時点のものを見に行っている。
最初のクエリが何も返さない場合は、そんなにわるいことじゃない
なぜなら
ユーザーA はユーザーBのが最近コメントを追加したことをおそらくしらないから。
しかし、
ユーザーAが最初にユーザーBのコメントが見られたのに、次は見られなかったらおかしな状況となる。

▼ 解決策：Monotonic reads
上記と同様の元戻りが発生しないことを保証する方法。
Strong consistency ほどではないが、eventual consistency よりは保証されている。

データを読み込んだ時、
古い値を読むかも知れない。
monotonic reads が保証するのは、
あるユーザーが読み込みを連続で行った場合、元戻りが発生しないこと、新しいデータを読んだあとに古いデータを読み込むことがないことだ。

monotonic reads にするための一つの方法は、
各ユーザーは、常に同じレプリカからしか読み込まないようすることだ。
※ 他のユーザーは、他のレプリカから読むことができる。もちろん。
どう実現するか。
レプリカは、ユーザーのIDEのハッシュ値に基づいて選ばれる。
そして、そのレプリカが fail したら、ユーザーのクエリは他のレプリカにルートされる。
