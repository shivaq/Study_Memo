■■■■■■■■■■■■■■■■■■■■■■■■■■ squid インストールと起動設定
▼ アップデート
yum -y update
▼ インストール
yum -y install squid

▼ 起動
// キャッシュクリアしてから
squid -z
systemctl status squid
systemctl start squid
// service squid start

// Initializes cache, or swap, directories. You must use this option when running Squid for the first time or whenever you add a new cache directory.

▼ 特定のサービスの起動設定を全て有効にする
chkconfig squid on

▼ バージョン確認
rpm -qa squid
-------------------------------------------------

■■■■■■■■■■■■■■■■■■■■■■■■■■ squid 設定ファイルを配置
1.squid.conf, whitelist を /etc/squid/に配置
※ 00.Git > 03.squid を参照
