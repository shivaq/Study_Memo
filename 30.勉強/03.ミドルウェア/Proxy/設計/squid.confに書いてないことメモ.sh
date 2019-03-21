■■■■■■■■■■■■■■■■■■■■■■■■■■ 参考情報 リンク
公式のDirective説明
# http://www.squid-cache.org/Doc/config/

openSUSE のドキュメント。各 directive の解説がわかりやすい
# https://www.pks.mpg.de/~mueller/docs/suse10.2/html/opensuse-manual_en/manual/sec.squid.configfile.html

acl や method などなどのタイプの説明とその使用例
# http://www.visolve.com/squid/squid24s1/access_controls.php

なんかすごそうな squid.conf サンプル
# https://gist.github.com/kipyegonmark/ef54ea4fb7a11f4d0470
-------------------------------------------------


■■■■■■■■■■■■■■■■■■■■■■■■■■ squid.conf が、チェックされていく順番
# acl は、上から順に読まれてく。
# マッチする条件に出会った時、読み込みを終える。

# http_access として、manager AND localhost の場合、allow
http_access allow manager localhost
# 上記ルール OR 下記ルール OR その下のルール。。。と、条件マッチするまで読み進む
http_access deny manager
http_access deny !Safe_ports
http_access deny CONNECT !SSL_ports
http_access allow localnet
http_access allow localhost
# どのルールにもマッチしない場合でも、下記はマッチする
http_access deny all
-------------------------------------------------

▼ 自環境を許可
-------------------------------------------------
acl myVpcCidr src 10.0.0.0/16
http_access allow myVpcCidr
-------------------------------------------------


▼ 禁止リスト
-------------------------------------------------
# url に cooking を含む ACL
acl Cooking1 url_regex cooking
# acl Recipe1 url_regex recipe
# acl myclients src 172.16.5.0/24
# Cooking1 ACL を deny するルール
http_access deny Cooking1
# http_access deny Recipe1
# http_access allow myclients
http_access deny all
-------------------------------------------------

▼ みんなOK でもお前はだめだリスト
-------------------------------------------------
acl Cooking2 dstdomain www.gourmet-chef.com
http_access deny Cooking2
http_access allow all
-------------------------------------------------



▼ AND と OR
-------------------------------------------------
acl ME src 10.0.0.1
acl YOU src 10.0.0.2
http_access allow ME YOU # これはAND
-------------------------------------------------
acl ME src 10.0.0.1
acl YOU src 10.0.0.2
http_access allow ME #これで
http_access allow YOU # OR になる
-------------------------------------------------









▼ IP アドレスが URL に使われてると Squid は逆引きせんといかん
-------------------------------------------------
ドメイン名の代わりに、IP アドレスがURLに使われていた場合、
Squid は ACL とマッチするかどうか知るために DNS lookup しなければならない。

Squid の FQDN キャッシュにすでにいる場合すぐに判断される。
そうでない場合、非同期でリバースDNSルックアップを行う。
-------------------------------------------------

▼ プロキシに username password を要求
-------------------------------------------------

-------------------------------------------------









■■■■■■■■■■■■■■■■■■■■■■■■■■ キャッシュ
# ▼ 特定のサーバにキャッシュを許可しない場合
acl someserver dstdomain .someserver.com
cache deny someserver
-------------------------------------------------












cache_peer hostname type proxy-port icp-port
-------------------------------------------------
ISP のプロキシを使いたい場合などに使う。
hostname →使用する proxy の名前と IPアドレス
type →"parent" にする
proxy-port →
-------------------------------------------------
