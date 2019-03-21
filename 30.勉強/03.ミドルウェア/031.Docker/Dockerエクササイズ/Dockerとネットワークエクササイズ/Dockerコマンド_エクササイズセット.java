■■■■■■■■■■■■■■■■■■■■■■■■■■ 使用モジュールと DockerFile セット
Docker_And_NwLink ディレクトリの中身を、Dockerをインストールしたサーバに持っていって実行



■■■■■■■■■■■■■■■■■■■■■■■■■■ 異なるコンテナを、ネットワークでリンクさせる
▼ FLASKS と Redis を使うアプリのImageをビルド
docker image build -t web1:1.0 . --build-arg HTTP_PROXY=http://ec2-user@proxy.my.local:8080 --build-arg HTTPS_PROXY=http://ec2-user@proxy.my.local:8080



▼ redis のImageを DockerHub から pull
docker image pull redis:5.0-alpine

▼ redis を使うコンテナで、バックグラウンドで redis を走らせる
docker container run -itd -p 6379:6379 --rm --name redis redis:5.0-alpine

▼ ネットワークを確認
docker network inspect bridge
-------------------------------------------------
[
        "Name": "bridge",
        "Id": "111043360de1a72260742d98478e17d75ffff8ef7b691c4746edc420639dd806",
        "Created": "2019-01-16T16:19:10.341934028Z",
        "Scope": "local",
        "Driver": "bridge",

        ~~~~~~~~~~~~~~~ snip ~~~~~~~~~~~~~~~
        "Containers": {
            "b8db737e17eb3e02f1440ee5e58ccc98e1dc56df267d13753336134f313b04cc": {
                "Name": "redis",
                "EndpointID":  "bfa24f7eacbba504e0e8e0edc412e02647d2537c41c28b08ef3542fcddaf23e7",
                "MacAddress": "02:42:ac:11:00:02",
                "IPv4Address": "172.17.0.2/16",
                "IPv6Address": ""
        ~~~~~~~~~~~~~~~ snip ~~~~~~~~~~~~~~~
]
-------------------------------------------------

▼ FLaskアプリのコンテナを走らせる
docker container run -itd -p 5000:5000 -v $PWD:/app --rm --name web2 -e FLASK_APP=app.py -e FLASK_DEBUG=1 web2

▼ redis の ネットワークを確認
// コンテナのネットワークに割り当てられた ip アドレスがわかる
docker exec redis ifconfig
-------------------------------------------------
(py2_env) [ec2-user@ip-10-0-100-224 09-linking-containers-with-docker-networks]$ docker exec redis ifconfig
eth0      Link encap:Ethernet  HWaddr 02:42:AC:11:00:02
          inet addr:172.17.0.2  Bcast:172.17.255.255  Mask:255.255.0.0
-------------------------------------------------



▼ FLASK のコンテナから redis のコンテナに ping
 docker exec web2 ping 172.17.0.2



 ▼ redis の hosts を見てみる
 docker exec redis cat /etc/hosts
  →redis のローカル IP が、マップされた値は、redis のコンテナIDだとわかる
docker container ls
 -------------------------------------------------
 127.0.0.1       localhost
 ::1     localhost ip6-localhost ip6-loopback
 fe00::0 ip6-localnet
 ff00::0 ip6-mcastprefix
 ff02::1 ip6-allnodes
 ff02::2 ip6-allrouters
 172.17.0.2      ad8582e8accd
 -------------------------------------------------


■■■■■■■■■■■■■■■■■■■■■■■■■■  新規ネットワークを作成 →自動的に名前解決できるようにする
▼ mynetwork ネットワーク作成
-------------------------------------------------
docker network create --driver bridge mynetwork
-------------------------------------------------



// ネットワークを見てみると。。。
docker network inspect mynetwork
-------------------------------------------------
・コンテナがない
"Containers": {},
-------------------------------------------------




▼ 起動した コンテナを一旦止める
-------------------------------------------------
docker container stop web2
docker container stop redis
-------------------------------------------------


▼ ネットワークを指定して、コンテナを実行してみる
-------------------------------------------------
docker container run -itd -p 6379:6379 --net mynetwork --rm --name redis redis:5.0-alpine

docker container run -itd -p 5000:5000 --net mynetwork -v $PWD:/app --rm --name web2 -e FLASK_APP=app.py -e FLASK_DEBUG=1 web2
-------------------------------------------------






▼ もう一度、作成したネットワークを確認
 docker network inspect mynetwork
 -------------------------------------------------
 [
     {
         "Name": "mynetwork",
         // "Id": "ad7abdea7203d7932d979c46afe22a60958685c915aa1b75a4a2e8d776c2be4f",
         // "Created": "2019-01-20T03:00:27.797143418Z",
         // "Scope": "local",
         "Driver": "bridge",
         // "EnableIPv6": false,
         // "IPAM": {
         //     "Driver": "default",
         //     "Options": {},
         //     "Config": [
                 {
                     "Subnet": "172.19.0.0/16",
                     "Gateway": "172.19.0.1"
                 }
             ]
         },
         // "Internal": false,
         // "Attachable": false,
         // "Ingress": false,
         // "ConfigFrom": {
         //     "Network": ""
         // },
         // "ConfigOnly": false,
         "Containers": {
             "d901fd8ccca54890647cbd48cb639b27547038e848c1e558aaafa15f26e640a0": {
                 "Name": "redis",
                 // "EndpointID": "9f660e046466f52687710b34a94aaf685671b41844c5971cf7c9a146f7d4278d",
                 // "MacAddress": "02:42:ac:13:00:02",
                 "IPv4Address": "172.19.0.2/16",
                 "IPv6Address": ""
             },
             "e160d6d77e6bc0666fa541f90a2cbfe9c51d042ca3bbe6e47f70755f8bc2f847": {
                 "Name": "web2",
                 // "EndpointID": "5a0701fc04eedb008592c2f723478f22804adb04b9b34c5eed2f13f64b68dfd3",
                 // "MacAddress": "02:42:ac:13:00:03",
                 "IPv4Address": "172.19.0.3/16",
                 "IPv6Address": ""
             }
         },
         "Options": {},
         "Labels": {}
     }
 ]

-------------------------------------------------





▼ ipアドレスが名前解決できるようになっている
-------------------------------------------------
docker exec web2 ping redis
-------------------------------------------------
PING redis (172.19.0.2): 56 data bytes
64 bytes from 172.19.0.2: seq=0 ttl=255 time=0.084 ms
64 bytes from 172.19.0.2: seq=1 ttl=255 time=0.071 ms
64 bytes from 172.19.0.2: seq=2 ttl=255 time=0.070 ms
-------------------------------------------------


▼ redis を使った挙動が機能していることを確認
curl http://localhost:5000
// ・Docker_And_NwLink の app.py の定義により、 redis を使いつつ インクリメントしていることが確認できる
▼ 上記挙動の解説
-------------------------------------------------
// redis は名前解決されて、ip アドレスがわかる
app.config['REDIS_URL'] = 'redis://redis:6379/0'
-------------------------------------------------

▼ redis にログインして試してみる
-------------------------------------------------
// mysql を使ったログイン的なコマンド
docker exec -it redis redis-cli

// すべてのキーを確認
127.0.0.1:6379> KEYS *
1) "web2_counter"
-------------------------------------------------
// 上記、python モジュールで下記のとおり定義したキー
@app.route('/')
def counter():
    return str(redis.incr('web2_counter'))
-------------------------------------------------



▼ ボリュームを作る
-------------------------------------------------
// redis のコンテナが止まっても、値が保持されるように
// コンテナはイミュータブルなので
docker volume create web2_redis


docker volume ls

▼ ボリュームのマウントポイント確認
-------------------------------------------------
docker volume inspect web2_redis
[
    {
        "CreatedAt": "2019-01-20T22:46:23Z",
        "Driver": "local",
        "Labels": {},
        "Mountpoint": "/var/lib/docker/volumes/web2_redis/_data",
        "Name": "web2_redis",
        "Options": {},
        "Scope": "local"
    }
]
-------------------------------------------------


▼ ボリュームつきで コンテナを走らせる
// -v web2_redis:/data
// /data は、redis Docker Image が、コンテナ起動時にデフォルトでデータを探す場所
docker container run -itd -p 6379:6379 --net mynetwork -v web2_redis:/data --rm --name redis redis:5.0-alpine

// そこにはまだなにもない
ls -la /var/lib/docker/volumes/web2_redis/_data

// カウンターを走らせてみて。。。
curl http://localhost:5000

// redis はメモリにデータ保持。30秒に一度ボリュームに保存。
// 下記で、手動でボリュームに保存してやる
docker exec redis redis-cli SAVE

// dump.rdb が生成された
sudo ls -la /var/lib/docker/volumes/web2_redis/_data
-------------------------------------------------
total 4
drwxr-xr-x 2  100  101  22 Jan 20 22:59 .
drwxr-xr-x 3 root root  19 Jan 20 22:46 ..
-rw-r--r-- 1  100  101 113 Jan 20 22:59 dump.rdb
-------------------------------------------------
