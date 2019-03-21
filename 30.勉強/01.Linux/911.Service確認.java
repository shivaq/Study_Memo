▼ インストールされているか確認
yum list installed squid

▼ Serviceの状態確認
systemctl status squid

▼ 特定のサービスの起動設定を全て有効にする
chkconfig squid on

▼ バージョン確認
rpm -qa squid
-------------------------------------------------
