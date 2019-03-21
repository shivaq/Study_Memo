■■■■■■■■■■■■■■■■■■■■■■■■■■ 環境変数に PROXY 系追加
1. HTTP_PROXY、HTTPS_PROXY、NO_PROXY を追加
// C:\Users\Yasuaki\Dropbox\01.study\MyAws\10.設計\11.詳細設計\13.起動時読み込みファイル\bash_profileに追加.sh を参照

// source ~/.bash_profile で反映

2. /etc/profile も更新
// C:\Users\Yasuaki\Dropbox\01.study\MyAws\10.設計\11.詳細設計\13.起動時読み込みファイル\etc_profile_に追加.sh を参照
■■■■■■■■■■■■■■■■■■■■■■■■■■ 環境変数にPROXY を設定していても、設定必要なものども
▼ yum
vi /etc/yum.conf

// /etc/yum.conf に下記追記
#proxy settings
proxy=http://ec2-user@proxy.my.local:8080

▼ wget
vi /etc/wgetrc
// /etc/wgetrc に下記追記

#proxy settings
https_proxy = http://ec2-user@proxy.my.local:8080
http_proxy = http://ec2-user@proxy.my.local:8080
ftp_proxy = http://ec2-user@proxy.my.local:8080
-------------------------------------------------

下記コマンドそれぞれ使えるかどうかテスト
 →使えなかったら、▼ curl をセット
ec2-metadata
curl http://169.254.169.254/latest/meta-data/

▼ curl
ホームディレクトリに .curlrc を作成し、下記追記
proxy="http://ec2-user@proxy.my.local:8080"
noproxy = "127.0.0.1,localhost,169.254.169.254"


-------------------------------------------------

// ▼ apt-get
// // /etc/apt/apt.conf.d/80proxy みたいなファイル作成 ※ファイル名は自由
// Acquire::ftp::proxy "ftp://172.16.1.100:3128";
// Acquire::http::proxy "http://172.16.1.100:3128";
// Acquire::https::proxy "https://172.16.1.100:3128";

▼ yum アップデート
yum -y update
