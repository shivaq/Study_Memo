■■■■■■■■■■■■■■■■■■■■■■■■■■
▼ 作業ディレクトリを作成
mkdir basic

■■■■■■■■■■■■■■■■■■■■■■■■■■ hostnameを変える
▼ Amazon Linux の場合
sudo hostnamectl set-hostname ansible

▼ Amazon Linux 以外の場合は下記を使用
// ▼ /etc/sysconfig/network
// -------------------------------------------------
// /etc/sysconfig/network
// 上記に、下記を追記
// HOSTNAME=bastion.my.local
// -------------------------------------------------
//
// ▼ 環境変数 hostname 書き換えコマンド
// hostname proxy
//
// ▼ network 再起動
// /etc/init.d/network restart

■■■■■■■■■■■■■■■■■■■■■■■■■■ proxy 設定
