■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ Proxy 設定をする
みんな proxy を使う前提で SG を組んだ場合
・squid インストール 及び 設定
・各種サーバに プロキシ設定
// ・SgForAll アウトバウンドで、443, 80 ポート 0.0.0.0/0 を許可しない

■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ Ansible のアプリ側も CFN で構築する
■■ やりたいこと
▼ 各サーバの役割に必要な設定
▼ public
ansible install
squid install

▼ private
httpd install
mysql install

■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ Proxy 設定をする
Ansible 経由
・squid インストール 及び 設定
・各種サーバに プロキシ設定
・SgForAll にプロキシ経由の制限

■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ Common AMI を作成する
yum update -y
yum install bind-utils
▼ 入っていないけど使いたいアプリ
dig // bind-utils に含まれる
telnet

■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ Web server AMI を作成する

■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ EC2 を定期停止させる Lambda を作成
下記をもとに SAM を使ってみる
https://docs.aws.amazon.com/ja_jp/lambda/latest/dg/deploying-lambda-apps.html
https://docs.aws.amazon.com/ja_jp/AWSCloudFormation/latest/UserGuide/transform-aws-serverless.html


■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ Aws Cli を使う


■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ Cygwin と あれをインストール

■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ DB に接続してみる


■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ Code Deploy を使う






■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ DynamoDb を使う

■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ S3 の、操作ログ料金を追跡し、ログ出力中止を検討



■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ AutoScaling を試す
cfn-init とかを仕込む



■■■■■■■■■■■■■■■■■■■■■■■■■■ Done ■■■■■■■■■■■■■■■■■■■■■■■■■■
