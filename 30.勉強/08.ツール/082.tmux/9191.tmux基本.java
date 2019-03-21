■■■■■■■■■■■■■■■■■■■■■■■■■■ 通常操作
▼ ペイン間移動
 - プリフィックス + o (+ 番号)
 - prx で、手を離して o
 - prx + {   →前のペインにスイッチ
 - prx + q + 表示された番号

▼ スクロールモード
prx + [

▼ コピーモード
prx + ]
-------------------------------------------------


■■■■■■■■■■■■■■■■■■■■■■■■■■ 最初の生成
起動
tmux

▼ ウィンドウ新規作成
プリフィックス + c

水平分割
プリフィックス + ""

垂直分割
プリフィックス + %

ペインを解除してウインドウ化
プリフィックス + !

ウインドウの一覧選択
プリフィックス + w

-------------------------------------------------
■■■■■■■■■■■■■■■■■■■■■■■■■■ 試してみたいこと
砦サーバに tmux をインストール
yum install http://dl.fedoraproject.org/pub/epel/6/x86_64/tmux-1.6-3.el6.x86_64.rpm


自端末から砦への ssh 接続が切れても、砦とその先との接続は切れない
-------------------------------------------------


■■■■■■■■■■■■■■■■■■■■■■■■■■ 画面構成要素
▼ セッション(Session)
tmuxに管理される仮想端末全体を指す
各セッションは1つ以上のウィンドウを持つ

▼ ウィンドウ(Window)
tmuxセッション内に開かれている1つの仮想端末の画面全体を指す
ウィンドウは1つ以上のペインを持つ

▼ ペイン(Pane)
分割されたウィンドウの単位を指す
ペインそれぞれが独立した擬似端末となる
-------------------------------------------------




プリフィックス
Ctrl B

セッションの確認
tmux ls


▼ 設定ファイル
~/.tmux.conf




▼ セッションの削除
tmux kill-session

// 全セッションを削除する場合
tmux kill-server
-------------------------------------------------

■■■■■■■■■■■■■■■■■■■■■■■■■■ セッションのアタッチデタッチ // サーバセッションから
▼ デタッチ
プリフィックス ＋ｄ


サーバセッションで実行中処理を継続させたままクライアントセッションを終了できる。
-------------------------------------------------
▼ アタッチ

プリフィックス + a
-------------------------------------------------
■■■■■■■■■■■■■■■■■■■■■■■■■■ ウィンドウ/ペイン操作
▼ ウィンドウ一覧表示
プリフィックス + w

▼ ウィンドウ切り替え
プリフィックス + ウィンドウ番号

▼ ウィンドウ削除
プリフィックス + &

▼ ペイン番号表示
プリフィックス +q

▼ ペイン分割解除
プリフィックス + x
-------------------------------------------------
