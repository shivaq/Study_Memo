■■■■■■■■■■■■■■■■■■■■■■■■■■ Eventual vs Strong Consistency in Distributed Databases
▼ アナロジー
私には Tech Notes と読んでいる習慣がある。
ラップトップに、自分が学んだ技術的コンセプトの要約を記述するのだ。
これで私はほしい時にそういった情報に再アクセスしやすい環境を作っている。

▼ 不安
でもラップトップが盗まれたり、壊れたりしないか、といった不安がある。
なので、私は外部ハードディスクに Tech Notes をバックアップすることにしている。
さらに、DropBoxも利用している。

▼ どんな頻度で？
2週間に一度、Tech Notes の改訂版と新しい書き込みを、外部HDに対してアップデートを行っている。
DropBox は私がラップトップを インターネットに繋げるとすぐにアップデートされる。

▼ (Master-Slave Model)
こうして、私は外部HDとDropBox を読み込みソースに、
ラップトップを読み込みと書き込みのためのストレージとして使っている。

冗長化すれば、信頼性も上がる。


■■■■■■■■■■■■■■■■■■■■■■■■■■ Case 1: Eventual Consistency
複数のDBのレプリカを、データ格納のために使うときはいつでも、
書き込みリクエストはレプリカのうちの一つに行うとする。
そんな状態では、
あるレプリカへの書き込みリクエストを、他のレプリカたちにも到達させることで、、
その他レプリカたちが全てその書き込みを反映させて、一貫した状態になるような戦略が必要になる。

▼ Consistency が意味すること
そのデータベースのどのノードに対する読み込みリクエストも、同じデータを返さなければならないということ

▼ Eventual consistency の意味すること
そのデータベースの各ノードが、最終的に一貫した状態になることを保証する。

データが consistent eventually ということが意味するのは、
データの更新が他のレプリカに到達するまでに時間がかかるということ。
So what?
もしまだ更新されていないレプリカから読み込んだ場合、// なぜかというと、レプリカは最終的には更新される、ということが保証されているから。
古くなったデータが返ってくるかも知れないですよ。

....

私の外部HDは古いデータを15日間保持していることになる。
で、
私の友人ジョンが、更新の数日後に、私の外付けHDのデータがほしいと言ってきたとする。
-------------------------------------------------
John: I want your hard disk to read your Tech Notes.
I: Sure, why not. But it hasn’t been updated since last few days.
John: I am fine with it.
-------------------------------------------------

こうして、私の外付けHDはジョンに即座に提供されることになった// レイテンシーが低い
ただし、そこにはリスクが有る。
その中の情報は古い情報かも知れないよ、というリスクが。
だが私は確信している。
次の2週間が始まるときには、その外付けHDのデータが最新状態に更新されることを。。。

"Eventual consistency offers low latency at the risk of returning stale data"


■■■■■■■■■■■■■■■■■■■■■■■■■■ Case 2: Strong Consistency
・DBのレプリカのうちの一つに書き込みがなされたら
すぐに
全てのレプリカにデータが渡される。

しかし
レプリカたちが新しいデータで更新されている間、
どのレプリカからの読み書きリクエストのレスポンスも遅延が生じる。
なぜなら
レプリカたちは互いが consistent であることを維持する処理をしているから。

レプリカたちが consistent になるとすぐに
リクエストの処理を再開する。

▼ アナロジー
ベロニカ：あなたの最新 Tech Notes が見たいんだけど。
私：いいよ。DropBox のリンクを渡すから。
でも、私が Tech Note を書いてから、動機が完了するまで 2,3分かかるから、
その後でアクセスしなよ。



■■■■■■■■■■■■■■■■■■■■■■■■■■ 結論
Eventual Consistency はレイテンシーが低いが、データが最新ではない可能性がある

Strong Consistency はレイテンシーが高い。だが、最新のデータを取得できることが保証されている。
