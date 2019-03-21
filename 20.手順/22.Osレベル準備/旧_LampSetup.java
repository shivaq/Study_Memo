■ Linux の基礎
-------------------------------------------------

目次
-------------------------------------------------
▼ Ubuntu 初期設定
▼ SSH でアクセスできるようにする
▼ 公開鍵認証を設定する
▼ アプリケーション使用許可を Firewall で設定
▼ udev とは
▼ ifconfig -a
▼ /etc/network/interfaces について
-------------------------------------------------










▼ VirtualBox 初期設定
-------------------------------------------------
やること
・Ubuntu ISO 取得
・一般 →高度 →クリップボードの共有とか
-------------------------------------------------


▼ Ubuntu 初期設定
-------------------------------------------------
イーサネットとは // →ローカルエリア接続
-------------------------------------------------
新規 →ubunto-16.04.3_20180210 みたく名前をつける
メモリは ２GB ないと遅い場合がある
HD は 10GB。 固定サイズのが速い。

マウスとキーボードは、共有可能にしておきたい

インストール中アップデートもやるをチェック
サードパーティのソフトはインストール不要
あとは表示内容見ながら進めれば、問題は起きない
-------------------------------------------------
セキュリティファイルなどを最新にする
// sudo apt update
// update →利用可能なパッケージのリストをアップデート。パッケージマネージャが、アップグレード可能なパッケージが何かを認識する。
// upgrade →最新バージョンをインストール。
// 最初に update 次に upgrade スべし。
システムをアップグレード
// sudo apt upgrade
vim の設定をいい感じにする
// 適当にググって下記にコピー
// vi ~/.vimrc

Linux に openssh-server をインストール
// sudo apt-get install openssh-server openssh-client
VBOX →設定 →Network →Adapter1 の NAT を、高度な設定から PortForwarding させる。
// ホストの ループバックアドレス の SSH のポートを、VirtualBox の ゲストIP の 22 に転送する、みたいな設定
// プロトコル TCP ホストIP 127.0.1.1 ホストポート 20022 ゲストIP 10.0.2.15 22

ポートフォワーディングで 22 以外を設定した場合の ssh
// ssh yasuaki@127.0.1.1 -p 10022
公開鍵を使った SSH 接続設定
// ssh-keygen
// 表示されたパスに rsa 保存で問題なければEnter
// パスフレーズは、パスワード代わりみたいなもの。Key/Value の Key みたいなもん？
サーバに公開鍵をコピー
// ssh-copy-id yasuaki@127.0.1.1
公開鍵によるログインしかできないようにする
// ポート番号を指定する場合、うまく公開鍵を強いるようにできないっぽい
// sudo vi /etc/ssh/ssh_config
// 書きをコメントアウトした上で no にする
// PasswordAuthentication no
// sudo systemctl reload sshd
タイムゾーンを合わせる
// sudo timedatectl set-timezone Asia/Tokyo
Upuntu で su になる
// sudo su

ユーザーを追加
// adduser guest

アップデートなどに使ったキャッシュファイルをクリーン
// sudo apt autoremove
// sudo apt clean
-------------------------------------------------



SSL がうまくいかなくて頓挫したので、
SSL 周りは別途テストしたい
つまりは、勉強用であれば、SSL はなしで行こうと思う。



▼ LAMP 環境構築
-------------------------------------------------
▼ Apache
-------------------------------------------------

Apache をインストール
// sudo apt-get install apache2

ステータス確認
// sudo systemctl status apache2


Apache サーバにブラウザからアクセスできるようにする
-------------------------------------------------
VirtualBox の典型的なネットワークセットアップ
-------------------------------------------------
2つの NIC をセット。
1.NAT
// Box がホストを経由して外部と交信するのに使用。
// ホストからゲストへの交信はできない
2.Host-Only
// ホスト と ゲスト とが交信するのに使用。ホスト間の交信も可能。
// ただし、ホストはゲストにアクセスできるが、外部ネットワークからはアクセスできない。
-------------------------------------------------

Host-only networking を設定する
-------------------------------------------------
HostOnly は、ゲストホスト間だけの通信を想定。外部には通信しない。だからホストオンリー。
-------------------------------------------------
VirtualBox にて
Windows側の ネットワークアダプタを作成
// VirtualBox →ファイル →ホストNetwork Manager
//  →作成// DHCP 無効
// 設定 →ネットワーク →アダプタ2 →ホストオンリーアダプター
出来上がったアダプタの IPアドレスを確認
// 192.168.107.1
-------------------------------------------------
GuestOs にて
新しく インターフェイスが登録されていることを確認
// ifconfig
// route -n
// enp0s8 にはアドレス未割り当てのため、 Routing テーブルにももちろん何もない
新しいインターフェイスに IP アドレスを割り当てる
// 192.168.107.1 // Windows 側がこのアドレス
// 192.168.107.56 // Linux 側は 例えばこのアドレスにする

// /etc/network/interfaces に下記を記載
-------------------------------------------------
// # The host-only network interface
// auto enp0s8
// iface enp0s8 inet static
// address 192.168.107.56
-------------------------------------------------
※ 注意！ デフォルトゲートウェイはホストオンリーのインターフェイスに設定しちゃ駄目！
// ここで、デフォルトゲートウェイも設定していた。結果、デフォルトゲートウェイが2つになり、
// 外部へのアクセスができなくなってしまっていた。
この段階のPING の結果
// デフォルトゲートウェイ問題を起こしていなければ大丈夫かも。
// OK:Windows  →enp0s8, Upuntu →HostOnly
// NG:Windows →enp0s3, Ubuntu →Windows 外側イーサネットIP
-------------------------------------------------


ブラウザから アパッチサーバにアクセスできる事を確認
// 再起動 →
// Windows の ブラウザで 192.168.107.56

躓いたところ
// ・enp0s3 にセットされている、NAT のポートフォワーディングにある、ゲストIP 10.0.2.15 にブラウザでアクセスしていた。
// ・auto と iface 部分のインターフェイス名を違って入れてた
// ・address に HostOnly ネットワークのネットワーク・アドレス？を設定していた。
-------------------------------------------------

▼ Apache 関係のファイル解説
-------------------------------------------------
// sudo apache2ctl configtest で、apache2.conf の構文エラーチェックができる
// また、ServerName を設定していない場合は、エラーが吐かれる。

service が起動していることを確認
// sudo systemctl status apache2

apache2 のコンフィグ修正後、接続を維持したまま更新するコマンド
// sudo systemctl reload apache2

・ファイル達
web コンテンツの格納場所
// /var/www/html
apache2 設定ファイル・ディレクトリ
// /etc/apache2
メイン設定ファイル
// /etc/apache2/apache2.conf
// グローバル configuration を修正すると自動更新される。他の設定ファイルのロードも責務のうち。
リッスンするポートの設定ファイル
// /etc/apache2/ports.conf
モジュールのファイルが格納されている
// /etc/apache2/mods-available/
// *.load →特定のモジュールをロードするための Fragment
// *.conf →特定のモジュールの設定
// a2enmod で有効化
サイトへのすべてのアクセスログ
// /var/log/apache2/access.log
エラーログ
// /var/log/apache2/error.log
-------------------------------------------------














▼ Virtual Host の設定
-------------------------------------------------
一つのサーバが、複数のサイトを保持する際の、個々のサイトの単位
-------------------------------------------------
ディレクトリを作成
// sudo mkdir -p /var/www/mytest.com/public_html;sudo mkdir -p /var/www/mytest2.com/public_html
上記ディレクトリのオーナー修正
// sudo chown -R $USER:$USER /var/www/mytest.com/public_html
// sudo chown -R $USER:$USER /var/www/mytest2.com/public_html
// chown -R リカーシブ
/var/www 配下に読み込み権限のみ付与
// sudo chmod -R 755 /var/www
テスト html ファイル作成
// sudo vi /var/www/mytest.com/public_html/index.html

// <html>
//   <head>
//     <title>テスト１</title>
//   </head>
//   <body>
//     <h1>Success!</h1>
//   </body>
// </html>
上記をコピー
// cp /var/www/mytest.com/public_html/index.html /var/www/mytest2.com/public_html/index.html

VirtualHost の設定デフォルトファイルをコピー
// sudo cp /etc/apache2/sites-available/000-default.conf /etc/apache2/sites-available/mytest.com.conf
VirtualHost の設定ファイル
// 000-default.conf →デフォルトのファイル
コピーしたファイルを修正 （Virtual Host の数だけ作成）
-------------------------------------------------
アドミン用メアド
// ServerAdmin findemotionsinmyhead@gmail.com
ベースドメイン
// ServerName mytest.com
// ポート番号の指定もできる。ServerAlias にはできない。
ドメインのエイリアス。www をつけてもベースドメインの方にリダイレクトする
// ServerAlias www.mytest.com
// ワイルドカードを使える。ServerName にはできない。
対象ホストのドキュメント格納先をセット
// DocumentRoot /var/www/mytest.com/public_html

// ErrorLog /var/log/apache2/mytest.error.log
// CustomLog /var/log/apache2/mytest.access.log combined
// LogLevel warn

必要に応じて修正したファイルをコピー
// sudo cp /etc/apache2/sites-available/mytest.com.conf /etc/apache2/sites-available/mytest2.com.conf
-------------------------------------------------

作成したファイルの有効化
// sudo a2ensite mytest.com.conf
デフォルトファイルの無効化
// sudo a2dissite 000-default.conf
アパッチの再起動
// sudo systemctl restart apache2
ドメインネームが正しく翻訳されるかどうかをテスト
// ホストOS 側で、ホストファイルを、テストのために 一時的に 修正
ノートパッドを管理者で開く
ファイルを開く。
// .txt を開く、担っている場合表示されない。拡張子はないため、すべてのファイルを表示にしておく
// C:\Windows\System32\Drivers\etc\hosts

// # localhost name resolution is handled within DNS itself.
// #   127.0.0.1       localhost
// #   ::1             localhost
// 192.168.107.56 www.example.com    この2行を追記
// 192.168.107.56 www.test.com
ブラウザからドメインネームでアクセス →それぞれIPアドレスは一緒だけれど、ドメインネームを変えることで別々のサイトが立ち上がるはず。
テストできたら、追記行を消す
-------------------------------------------------

▼ VirtualHost のファイル解説
-------------------------------------------------
サイトごとの Virtual Hosts が格納されている。
// /etc/apache2/sites-available/
// *-enabled ディレクトリとリンクされて初めて、Apache がここのファイルを使用する
// a2ensite コマンドでリンクされる。
サイトごとの Virtual Hosts のうち、有効化された ファイルが格納されている。
// /etc/apache2/sites-enabled/
Virtual Hosts に属さない設定ファイルを格納
// /etc/apache2/conf-available/
// a2enconf コマンドで有効化// sites-available と同じように
-------------------------------------------------


Apache に SSL をセットする。
ドメインネームを取得しているかどうかで、方法が分岐
// 安全に通信をするためのセキュリティプロトコル
// SSL（Secure Sockets Layer) 脆弱性があり廃止され、今は TLS（Transport Layer Security
// 公開証明書 と 秘密鍵のペアによって機能する


ドメインネームなし// ユーザーに対して露出していないサーバ、個人で使うだけ、テストに使うだけの場合
-------------------------------------------------
self-signed SSL 証明書
// 信頼できる 証明書 authority にサインしてもらっていないため、
// ユーザーは このサーバの identity を 検証できない事に注意
-------------------------------------------------
self-signed な 鍵と証明書のペアを生成
// sudo openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout /etc/ssl/private/apache-selfsigned.key -out /etc/ssl/certs/apache-selfsigned.crt

プロンプトに適切に答える
Country Name →JP
// Kyoto Kyoto City Yasuaki Shibata Yasuaki Shibata 192.168.107.56 shivaq777@gmail.com
Common Name について
// サーバの public IP アドレスを入力// ドメインネームがないため

openssl
// OpenSSL証明書、鍵などを扱うためのコマンドラインツール。

req
// certificate signing request (CSR) を指定するための サブコマンド

-x509
// X.509 は 公開鍵インフラの標準。SSL/TLS はこれに従っている。

-nodes
// パスフレーズで証明書を セキュアにするオプションをスキップするよう OpenSSL に告げるための引数
// サーバが起動した時に、Apache がパスフレーズ入力無しでファイルを読めるようにするためにこうする。

-days 365
// 証明書の有効期間

-newkey rsa:2048
// 新しい証明書を発行する際に、新しい鍵も同時に発行する。  // 証明書にサインするための鍵を事前に作っていないため。
// 2048 bits の RSA キーが生成される

-keyout
// 生成された秘密鍵の格納場所を指定

-out
// 証明書の格納場所を指定

クライアントとの間で PFS をネゴシエートするために使う Diffie-Hellman group を生成
// sudo openssl dhparam -out /etc/ssl/certs/dhparam.pem 2048

Diffie-Hellman
// 暗号化プロトコル

perfect forward secrecy (PFS)// 前方秘匿性
// セキュアな通信プロトコルの属性
// 長期的な鍵の対からセッションキーを生成した際、長期鍵が破られてもセッションキーの安全性を保つためのプロトコル。
// ・データを暗号化するための鍵から、別の鍵を生成してはならない。
// ・データを暗号化する鍵の素材は、一度だけの使い捨てにしなければならない。

Apache が SSL を使えるように設定していく

Appache に ssl 用 conf を作成
// sudo vi /etc/apache2/conf-available/ssl-params.conf

// ※コメントアウト部分の改行が代わってペーストされていないかチェックすること
-------------------------------------------------
// # from https://cipherli.st/
// # and https://raymii.org/s/tutorials/Strong_SSL_Security_On_Apache2.html

// SSLCipherSuite EECDH+AESGCM:EDH+AESGCM:AES256+EECDH:AES256+EDH
// SSLProtocol All -SSLv2 -SSLv3
// SSLHonorCipherOrder On
// # Disable preloading HSTS for now.  You can use the commented out header line that includes
// # the "preload" directive if you understand the implications.
// #Header always set Strict-Transport-Security "max-age=63072000; includeSubdomains; preload"
// Header always set Strict-Transport-Security "max-age=63072000; includeSubdomains"
// Header always set X-Frame-Options DENY
// Header always set X-Content-Type-Options nosniff
// # Requires Apache >= 2.4
// SSLCompression off
// SSLSessionTickets Off
// SSLUseStapling on
// SSLStaplingCache "shmcb:logs/stapling-cache(150000)"

// SSLOpenSSLConfCmd DHParameters "/etc/ssl/certs/dhparam.pem"
-------------------------------------------------


default-ssl.conf のバックアップを作成
// sudo cp /etc/apache2/sites-available/default-ssl.conf /etc/apache2/sites-available/default-ssl.conf.bak

default-ssl.conf を編集
// sudo vi /etc/apache2/sites-available/default-ssl.conf

-------------------------------------------------
下記部分を編集
// <IfModule mod_ssl.c>
//         <VirtualHost _default_:443>
                ServerAdmin shivaq777@gmail.com
                ServerName 192.168.107.56

                // DocumentRoot /var/www/html

                // ErrorLog ${APACHE_LOG_DIR}/error.log
                // CustomLog ${APACHE_LOG_DIR}/access.log combined

                // SSLEngine on

                SSLCertificateFile      /etc/ssl/certs/apache-selfsigned.crt
                SSLCertificateKeyFile /etc/ssl/private/apache-selfsigned.key

                // <FilesMatch "\.(cgi|shtml|phtml|php)$">
                //                 SSLOptions +StdEnvVars
                // </FilesMatch>
                // <Directory /usr/lib/cgi-bin>
                //                 SSLOptions +StdEnvVars
                // </Directory>

                BrowserMatch "MSIE [2-6]" \
                               nokeepalive ssl-unclean-shutdown \
                               downgrade-1.0 force-response-1.0

//         </VirtualHost>
// </IfModule>
-------------------------------------------------



暗号化されてない VirtualHost のファイルを HTTPS でリダイレクトされるようにする

rewrite はあまり使わないように、との Apache Docs の記述
// http://httpd.apache.org/docs/2.4/rewrite/avoid.html

直接ファイルに書き込む場合
// sudo vi /etc/apache2/sites-available/example.com.conf

-------------------------------------------------
下記部分を追記
// <VirtualHost *:80>
//         . . .

        Redirect "/" "https://example.com/"

//         . . .
// </VirtualHost>

<VirtualHost *:443>
    ServerName example.com
    # ... SSL configuration goes here
</VirtualHost>
-------------------------------------------------


Apache の SSL 設定を有効化
// a2enmod で、右記を有効化 mod_ssl（アパッチ SSL取得 モジュール）、mod_headers(SSL スニペットの設定で使うもの)
// sudo a2enmod ssl
// sudo a2enmod headers

Virtual Host の SSL を有効化
// sudo a2ensite default-ssl

セットした値を読み込むことができるよう、ssl-params.conf を有効化
// sudo a2enconf ssl-params

Apache の設定変更にシンタックスエラーがないことを確認
// sudo apache2ctl configtest

アパッチを再起動
// sudo systemctl restart apache2

HTTPS が通るかブラウザで確認。// 証明書が Authority から保証されてないとでるけど、詳細設定をクリックして進む
// https://192.168.107.56

HTTP がリダイレクトされる事を確認
// http://192.168.107.56

Redirect を 永久的に反映させるよう設定
// sudo vi /etc/apache2/sites-available/000-default.conf
-------------------------------------------------
// <VirtualHost *:80>
//         . . .

        Redirect permanent "/" "https://192.168.107.56/"

//         . . .
// </VirtualHost>
-------------------------------------------------

シンタックスエラーがないかチェック
// sudo apache2ctl configtest

アパッチ再起動
// sudo systemctl restart apache2

ドメインネームあり
"Let's Encrypt" を使って TLS/SSL certificate を設定
-------------------------------------------------
// Let's Encrypt certificates はサーバ上のクライアントソフトウェア経由で取得される。
// その名は Certbot。
レポジトリを追加
// sudo add-apt-repository ppa:certbot/certbot
// PPA →Personal Package Archive
// Ubuntuユーザーのチームや個人がそれぞれ管理している非公式のApp Storeのようなもの
// Let's Encrypt は、最新のセキュリティ情報を反映させるため、公式レポジトリの公開サイクルには合わない。よって、PPA 経由。
レポジトリをアップデート
// sudo apt-get update
Certbot をインストール
// sudo apt-get install python-certbot-apache
ドメインを指定して、SSL 証明書を取得
// sudo certbot --apache -d example.com -d www.example.com
// 有効なドメインネームを VirtualHost として登録しておかないとエラーになる
// example.com とか、どう証明書をとれっていうの？って感じ。
証明書ファイル生成成功を確認
// /etc/letsencrypt/live にある。有効なドメインネームで登録してない場合、生成されない
SSL 証明書のステータスを確認
// https://www.ssllabs.com/ssltest/analyze.html?d=example.com&latest
certbot 更新スクリプトが、1日2回実行されるよう systemd もしくは /etc/cron.d に記述
// certbot renew       これが更新スクリプト
更新できているか確認
// sudo certbot renew --dry-run
-------------------------------------------------





▼ MySQL
-------------------------------------------------
インストール
// sudo apt-get update
// sudo apt-get install mysql-server
セキュリティ強化
// mysql_secure_installation
MySQL ステータス確認
// systemctl status mysql.service
バージョン確認
// mysqladmin -p -u root version
// 下記がないと、deny される
// -p パスワードプロンプト表示 -u root ルートで。
5.7.21
-------------------------------------------------
▼ PHP
-------------------------------------------------
PHP インストール
// sudo apt-get install php libapache2-mod-php php-mcrypt php-mysql
Apache が index.html の前に index.php を探すように設定する
// sudo vi /etc/apache2/mods-enabled/dir.conf
// <IfModule mod_dir.c>
//     DirectoryIndex index.php index.html index.cgi index.pl index.xhtml index.htm
// </IfModule>
アパッチ再起動
// sudo systemctl status apache2
// sudo systemctl restart apache2
web root に info.php を格納してテスト
// upuntu 16.04 での web root
// sudo vi /var/www/mytest.com/info.php

// <?php
// phpinfo();
// ?>

ブラウザから PHP が稼働している事を確認
// 192.168.108.56/info.php
info.php はもう不要なので削除
// sudo rm /var/www/html/info.php
バージョン
7.0.22
-------------------------------------------------







▼ 設定時のコマンド
-------------------------------------------------
systemctl
systemd の状態を操作したり、確認したりするのに使う。

-------------------------------------------------
















▼ アプリケーション使用許可を Firewall で設定
-------------------------------------------------
Set Up a Basic Firewall
UbuntuWithFW にのみセットして実験
// https://www.digitalocean.com/community/tutorials/initial-server-setup-with-ubuntu-16-04
-------------------------------------------------
対象アプリを表示
// sudo ufw app list
対象アプリから、使用可能アプリを指定
// sudo ufw allow OpenSSH

ファイアウォール 自体を有効化
// sudo ufw enable
使用許可済みアプリを表示
// sudo ufw status

該当アプリの許可済みポートを表示
// sudo ufw app info "Apache Full"


その他 ファイアウォール で困ったら下記確認
https://www.digitalocean.com/community/tutorials/ufw-essentials-common-firewall-rules-and-commands
-------------------------------------------------
▼ Firewall の設定たち
-------------------------------------------------
Windows →セキュリティが強化された Windows Defender ファイアウォール
"本当に必要かどうか、ステップごとに通信を確認しながらすすめること！！！！！！！"
 // →真ん中ペイン、Windows Defender ファイアウォールのプロパティ
 //  →パブリックプロファイルタブ →保護されたネットワーク接続 →カスタマイズ →HostOnly のチェックを外す

Windows 側に自動セットされた HostOnlyNetwork に対する FW 設定を修正
// コンパネ →ファイアウォール設定 →詳細設定 →Inbound Rules
//  →新しい規則 →すべてのプログラム →任意
// 適用するローカルIPアドレスに Windows 側の HostOnly のアドレスを追加
// 192.168.107.1 // Windows 側がこのアドレス
// 適用するリモート IP アドレスに、enp0s8 のアドレスを追加
// address 192.168.107.56
//  →接続を許可する →ドメイン、プライベート、パブリックともにチェック →
Firewall を http, https が通れるようにする
// usdo ufw app list

Firewall の状態を確認
// sudo ufw status
-------------------------------------------------
























▼ udev とは// userspace device management
-------------------------------------------------
カーネルがパソコンへの接続を検出したデバイスに対して，動的に「デバイス・ファイル」を作成して割り当てるための仕組み

デバイス・ファイルとは，周辺機器などのデバイスをファイルとして扱えるようにしたもの
-------------------------------------------------


▼ ifconfig -a
-------------------------------------------------
カーネルのネットワークインターフェイス。起動時に使われる。
コマンド入力 →アクティブなインターフェイス を表示

enp2s0 などの命名規則// →ハードウェア上のコネクタの位置から決まる インターフェイス名
lo// ローカルループバック。ネットワークインターフェイスがなくてもテストなどに使えるよう用意された、仮想インターフェイス。
-------------------------------------------------


▼ /etc/network/interfaces について
-------------------------------------------------
▼ レイヤー2 のオプション// OSI 第二層。隣接ネットワークとのデータ送受信のためのプロトコルを扱う。
auto [interface]// 起動時にアクティブになるインターフェイス
allow-auto [interface]// 起動時にアクティブになるインターフェイス
allow-hotplug [interface]// HotPlug 時にアクティブになるインターフェイス
// 実際は他の二つとだいたい同じように使われているが、udev がホットプラグを検知するのを待ってからアクティブになる

▼ レイヤー3 のオプション
inet static// 静的IPアドレス
inet dhcp
inet6 static// IPv6
-------------------------------------------------
