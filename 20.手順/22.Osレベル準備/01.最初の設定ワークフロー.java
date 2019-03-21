■■■■■■■■■■■■■■■■■■■■■■■■■■ Proxy 設定
▼ Proxy サーバ
1. squid インストール
2. squid.conf 及び whitelist を設定
※ 00.Git > 03.squid を参照

■■■■■■■■■■■■■■■■■■■■■■■■■■ hostname
hostnamectl set-hostname myhostname

/etc/sysconfig/network を修正
HOSTNAME=myhostname

/etc/hosts の 127.0.0.1 にホスト名追加

■■■■■■■■■■■■■■■■■■■■■■■■■■ Ansible 設定
▼ Ansible サーバ
1.
212.端末からサーバへの準備 > KEY Pair をインスタンスに持っていく。

2.
113.プロキシ準備-クライアント

3.
121.Ansible初期設定

■■■■■■■■■■■■■■■■■■■■■■■■■■ Ansible で共通設定を流す
1.
122.共通設定をサーバに流す.java
