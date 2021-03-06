■■■■■■■■■■■■■■■■■■■■■■■■■■ プロキシはなんのためのもの？
パケットを変更して、外部に出る際のIPアドレスがすべてプロキシサーバのアドレスになる。

1.Network 構成の要件として必要
家で複数のPCを使っている場合、ネットに出ていく場合、ルーターがプロキシ的な役割を果たす。

1.Access Control
インターネットトラフィックのログを生成する。
・ユーザーのネットでのActivityを記録する。
・ユーザーネームとパスワードが要求されていれば、よりよくわかる。

インターネットトラフィックをフィルタリングする。
※ 仕事とは関係ないサイトや、不適切なサイト。

よくアクセスする情報をキャッシュする。

1.アクセスを匿名化する
外部のプロキスを経由すると、サードパーティが、誰がアクセスしてきたかという情報を見つけるのが困難になる。
そのプロキシのオーナーは、どこから来た情報なのかわかる。
よって、セキュアじゃないプロキシを使うと、リスクが有る。
また、目的のサーバに達する前にプロキシを経由することで、レイテンシーは高くなる。

1.インターナショナル proxy
ユーザーの母国によって、サイトのコンテンツを変えるサイトがある。
そういう場合、インターナルプロキシを使うことで、度の国からのアクセスなのかを隠蔽して、
制限を突破できる。







■■■■■■■■■■■■■■■■■■■■■■■■■■ Windows サーバでの設定
Internet Explorerを起動し、右上のツールボタンを押下します。
インターネットオプション -> 接続タブ -> LANの設定を押下しプロキシサーバを指定

プロキシサーバ ＞ LAN にプロキシサーバを使用する をチェック
アドレス:"プロキシサーバの private IP"
ポート:8080
