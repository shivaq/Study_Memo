■■■■■■■■■■■■■■■■■■■■■■■■■■ Docker ネットワーク

・自動的に名前解決できるようにDNS設定がなされる
・ip アドレスではなくネットワーク名でping できる
-------------------------------------------------
▼ 新規ネットワーク作成
docker network create -d bridge mynetwork
-------------------------------------------------
▼ ネットワークを指定してコンテナを run
docker container run -itd -p 6379:6379 --net mynetwork --rm --name redis redis:5.0-alpine
-------------------------------------------------
▼ ipアドレスが名前解決できるようになっている
-------------------------------------------------
docker exec web2 ping redis
-------------------------------------------------






■■■■■■■■■■■■■■■■■■■■■■■■■■ コンテナのネットワーク情報

▼ ネットワークドライバとは
-------------------------------------------------
・ドライバとは、
OSがデバイスを使用するためのプログラム
例えば、Windows OSのインストールされたPCに、USBのプリンタなどを接続した場合、
OSはこのプリンタの使い方を知らなければ使うことはできません。
そこで必要となるのがドライバ、と呼ばれるプログラムです。

・ネットワークドライバとは、
ネットワークに接続するための装置に必要なドライバ、ということ
-------------------------------------------------

▼ ネットワークドライバ とコンテナ
-------------------------------------------------
bridge・host いずれもインターネット経由でコンテナへのアクセスが可能

bridge はホストの任意のポートをコンテナのポートにマップすることが出来る

host はコンテナで expose されたポートをホストでも利用する。その為、一つのホストで同じポートを使うコンテナは利用できない
-------------------------------------------------


▼ bridge
-------------------------------------------------
・bridge はデフォルトでは docker0という仮想ブリッジを通じて通信を行う
・コンテナ内部と仮想ブリッジ(docker0)は Virtual Ethernet Tunnel(veth)によって接続されている
・コンテナ内ではeth0を通じてネットワーク通信が出来る
-------------------------------------------------
