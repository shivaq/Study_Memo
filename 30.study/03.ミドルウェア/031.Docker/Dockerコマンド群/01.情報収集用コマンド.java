■■■■■■■■■■■■■■■■■■■■■■■■■■ 情報取得
docker info

docker-compose --version

▼ Docker Image Fileを ls
docer image ls
-------------------------------------------------
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
web1                1.0                 bc5d967e2c36        15 hours ago        69.4MB
web1                latest              bc5d967e2c36        15 hours ago        69.4MB
<none>              <none>              1c7b44a39236        16 hours ago        69.4MB
<none>              <none>              9cd4a41cb1a1        3 days ago          58.3MB
hello-world         latest              fce289e99eb9        12 days ago         1.84kB
python              2.7-alpine          66c225e226f9        3 weeks ago         58.3MB
-------------------------------------------------





■■■■■■■■■■■■■■■■■■■■■■■■■■ コンテナ情報取得
▼ 上記、実行中のコンテナにアクセスしてみる
// 自分自身の、ポート番号 5000 にアクセス
curl http://localhost:5000


▼ 実行中の コンテナを ls
-------------------------------------------------
// docker container ps と同じ結果が出力される
[ec2-user@ip-10-0-100-224 ~]$ docker container ls
// CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
// 592c5c1970fe        web1                "/bin/sh -c 'flask r…"   23 minutes ago      Up 23 minutes       0.0.0.0:5000->5000/tcp   affectionate_wilson
-------------------------------------------------


▼ 最後に実行中だったコンテナを表示 →-l フラグ
[ec2-user@ip-10-0-100-224 ~]$ docker container ps -l
// CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                       PORTS               NAMES
// 7f12315a73b9        ubuntu              "/bin/bash"         38 minutes ago      Exited (100) 8 seconds ago




▼ コンテナのログを見る
// affectionate_wilson は、ls で表示される NAMES
// -f をつけると tail -f 見たくなる
docker container logs affectionate_wilson
// container つけなくても見られる
// t でタイムスタンプも出力される。
docker logs -ft daemon_dave
-------------------------------------------------
(py2_env) [ec2-user@ip-10-0-100-224 03-creating-a-dockerfile-part-1]$ docker container logs mystifying_keller
 * Serving Flask app "app.app"
 * Running on http://0.0.0.0:5000/ (Press CTRL+C to quit)
172.17.0.1 - - [14/Jan/2019 03:09:09] "GET / HTTP/1.1" 200 -
172.17.0.1 - - [14/Jan/2019 03:09:26] "GET / HTTP/1.1" 200 -
172.17.0.1 - - [14/Jan/2019 03:11:07] "HEAD / HTTP/1.1" 200 -
172.17.0.1 - - [14/Jan/2019 14:36:56] "GET / HTTP/1.1" 200 -
-------------------------------------------------





▼ Docker版 top みたいなのを見る
▼ どんなImage 使っているか
docker ps
-------------------------------------------------
// CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS               NAMES
// c39f72179529        ubuntu              "/bin/sh -c 'while t…"   4 minutes ago       Up 4 minutes                            daemon_dave
-------------------------------------------------



▼ 複数のコンテナの CPU やメモリ使用率確認
// container という引数はいらないんじゃないか？
docker container stats
docker stats some_container another_container
-------------------------------------------------
// CONTAINER ID        NAME                CPU %               MEM USAGE / LIMIT   MEM %               NET I/O             BLOCK I/O           PIDS
// c39f72179529        daemon_dave         0.07%               776KiB / 985.7MiB   0.08%               1.59kB / 0B         2.39MB / 0B         2
-------------------------------------------------

▼ プロセスとか。
docker top daemon_dave
-------------------------------------------------
// UID                 PID                 PPID                C                   STIME               TTY                 TIME                CMD
// root                5265                5241                0                   16:28               ?                   00:00:00            /bin/sh -c while true; do echo hello world; sleep 1; done
// root                5515                5265                0                   16:30               ?                   00:00:00            sleep 1
-------------------------------------------------










▼ コンテナの設定みたいなのを見る
docker container inspect web1

// マウント情報もわかる
"Mounts": [
    {
        "Type": "bind",
        "Source": "/home/ec2-user/py2_env/03-creating-a-dockerfile-part-1",
        "Destination": "/app",
        "Mode": "",
        "RW": true,
        "Propagation": "rprivate"
    }
],
-------------------------------------------------
▼ running 状態が true か false かを問い合わせる
docker inspect --format='{{ .State.Running }}' daemon_alice

▼ IPアドレスを問い合わせる
docker inspect --format '{{ .NetworkSettings.IPAddress }}' daemon_alice

▼ 複数のコンテナへ問い合わせて、それぞれの値を出力
docker inspect --format '{{.Name}} {{.State.Running}}' daemon_dave daemon_alice
// 返り値
/daemon_dave true
/daemon_alice true



▼ Image がレイヤーごとにどうコミットされたかのヒストリが見られる
docker history 22d47c8cb6e5
-------------------------------------------------
IMAGE CREATED CREATED BY SIZE
22d47c8cb6e5 6 minutes ago /bin/sh -c #(nop) EXPOSE map[80/tcp :{}] 0 B
b584f4ac1def 6 minutes ago /bin/sh -c echo 'Hi, I am in your container' 27 B
93fb180f3bc9 6 minutes ago /bin/sh -c apt-get install -y nginx 18.46 MB
9d938b9e0090 6 minutes ago /bin/sh -c apt-get update 20.02 MB
4c66c9dcee35 6 minutes ago /bin/sh -c #(nop) MAINTAINER James Turnbull " 0 B
-------------------------------------------------



■■■■■■■■■■■■■■■■■■■■■■■■■■ ネットワーク情報


▼ docker network ls
-------------------------------------------------
NETWORK ID          NAME                DRIVER              SCOPE
47a0a80715bd        bridge              bridge              local
43017b83d84a        host                host                local
5ce3ac29ed4a        none                null                local
-------------------------------------------------





▼ ifconfig // コンテナの、ではなく
-------------------------------------------------
docker0: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 172.17.0.1  netmask 255.255.0.0  broadcast 172.17.255.255
        inet6 fe80::42:d4ff:fe8c:183d  prefixlen 64  scopeid 0x20<link>
        ether 02:42:d4:8c:18:3d  txqueuelen 0  (Ethernet)
        RX packets 48887  bytes 4031692 (3.8 MiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 1363  bytes 58126 (56.7 KiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

eth0: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 9001
        inet 10.0.100.224  netmask 255.255.255.0  broadcast 10.0.100.255
        inet6 fe80::494:9eff:fec8:bd56  prefixlen 64  scopeid 0x20<link>
        ether 06:94:9e:c8:bd:56  txqueuelen 1000  (Ethernet)
        RX packets 17226  bytes 17090086 (16.2 MiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 68509  bytes 6632886 (6.3 MiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0
-------------------------------------------------

▼ docker exec web2 ifconfig
-------------------------------------------------
eth0      Link encap:Ethernet  HWaddr 02:42:AC:11:00:03
          inet addr:172.17.0.3  Bcast:172.17.255.255  Mask:255.255.0.0
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:51578 errors:0 dropped:0 overruns:0 frame:0
          TX packets:99045 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:0
          RX bytes:4827548 (4.6 MiB)  TX bytes:9480506 (9.0 MiB)

lo        Link encap:Local Loopback
          inet addr:127.0.0.1  Mask:255.0.0.0
          UP LOOPBACK RUNNING  MTU:65536  Metric:1
          RX packets:0 errors:0 dropped:0 overruns:0 frame:0
          TX packets:0 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000
          RX bytes:0 (0.0 B)  TX bytes:0 (0.0 B)
-------------------------------------------------



▼ docker network inspect bridge
「docker network ls」 で表示される NAME の値を引数に、ネットワークの詳細が見られる
-------------------------------------------------
[
    {
        "Name": "bridge",
        "Id": "47a0a80715bd208f6cf7769960d108746dcc742935fcd6756fa7e3ccd80ee1ef",
        "Created": "2019-01-15T22:43:35.262294379Z",
        "Scope": "local",
        "Driver": "bridge",
        "EnableIPv6": false,
        "IPAM": {
            "Driver": "default",
            "Options": null,
            "Config": [
                {
                    "Subnet": "172.17.0.0/16",
                    "Gateway": "172.17.0.1"
                }
            ]
        },
        "Internal": false,
        "Attachable": false,
        "Ingress": false,
        "ConfigFrom": {
            "Network": ""
        },
        "ConfigOnly": false,

        // このネットワークではコンテナは走っていない
        "Containers": {},

        "Options": {
            "com.docker.network.bridge.default_bridge": "true",
            "com.docker.network.bridge.enable_icc": "true",
            "com.docker.network.bridge.enable_ip_masquerade": "true",
            "com.docker.network.bridge.host_binding_ipv4": "0.0.0.0",
            "com.docker.network.bridge.name": "docker0",
            "com.docker.network.driver.mtu": "1500"
        },
        "Labels": {}
    }
]
-------------------------------------------------











■■■■■■■■■■■■■■■■■■■■■■■■■■ デバッグ
// コンテナに接続？して、ls とかが打てるようにする
// DockerFile 内の Pythonが alpine の場合は sh で、slim の場合は bash
docker container exec -it web1 sh

// exit と入力するのと同じ結果になる
Ctrl + d

// 使ってるFlask のバージョンが帰ってくる。それだけ。
docker container exec -it web1 flask --version

// ユーザーとグループを指定してFileを作成。こうしないと root でFileが作られる
docker container exec -it --user "$(id -u):$(id -g)" web1 touch test.txt

// python など、好きな言語を簡単にいじることができる
docker container run -it --rm --name testingpython python:2.7-alpine python
