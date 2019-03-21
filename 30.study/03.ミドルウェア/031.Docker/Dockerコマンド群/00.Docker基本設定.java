▼ 下記の要否はまだわからん 2019/02/11
// ■■■■■■■■■■■■■■■■■■■■■■■■■■ Docker daemon の現在の設定確認
// sudo systemctl cat docker | grep ExecStart
//
//
// ■■■■■■■■■■■■■■■■■■■■■■■■■■ Docker daemon の都度設定
// ▼ Dockerdaemon のインターフェイスやポート設定を指定する場合
// // Dockerdaemon と、ホストのすべてのインターフェイスとを紐付ける場合
// // ホストの、すべてのインターフェイスの 2375 ポートにきたアクセスを受け付ける。
// sudo dockerd -H tcp://0.0.0.0:2375
//
// ▼ -H を指定しない場合、環境変数の値が参照される
// export DOCKER_HOST="tcp://0.0.0.0:2375"
//
//
// ■■■■■■■■■■■■■■■■■■■■■■■■■■ Docker daemon の永続設定
// // /etc/systemd/system/docker.service.d/ 配下に、必要なFileが生成される
// // 下記コマンドで編集なら、設定がロードされる。vi で編集とかだと、reload が必要になる。
// sudo systemctl edit docker
//
// /etc/systemd/system/docker.service.d/override.conf
// -------------------------------------------------
// [Service]
// # clear out ExecStart
// ExecStart=
// ExecStart=/usr/bin/dockerd -H ...
// -------------------------------------------------



■■■■■■■■■■■■■■■■■■■■■■■■■■ プロキシ設定
▼ 1.Dockerクライアント →DockerDaemon プロキシ
// サーバが通常使うプロキシ設定でまかなえているはず
・環境変数にプロキシ設定
-------------------------------------------------



▼ 2.DockerDaemon →インターネット プロキシ
mkdir -p /etc/systemd/system/docker.service.d

cat > /etc/systemd/system/docker.service.d/http-proxy.conf << EOF
[Service]
Environment="HTTP_PROXY=http://proxy.my.local:8080"
Environment="HTTPS_PROXY=http://proxy.my.local:8080"
Environment="NO_PROXY=169.254.169.254,127.0.0.1,localhost"
EOF

systemctl daemon-reload
systemctl restart docker
-------------------------------------------------

▼ 3.コンテナのビルド時、run-time のプロキシ
-------------------------------------------------
// DockerFile で 環境変数定義 すると、他環境で使えなくなるので駄目！

■■ ビルド時
試してみたい。config.json が設定してあれば、引数が不要かどうか。
// ビルド時のプロキシ設定は、引数でやるのが現状ベスト。
// docker image build -t web1:1.0 . --build-arg HTTP_PROXY=http://ec2-user@proxy.my.local:8080 --build-arg HTTPS_PROXY=http://ec2-user@proxy.my.local:8080

■■ ランタイム


▼ docker バージョン 17.07 以上
▼ ログイン
docker login
 →~/.docker/config.json に、認証情報が追記される
・コンテナを使うユーザーに、下記設定情報を記載
// sudo するときは、root ユーザーもこれを作っておかないといかん
~/.docker/config.json
-------------------------------------------------
{
        "auths": {
                "https://index.docker.io/v1/": {
                        "auth": "xxxxxxxxxxxxxxxxxxxxxxxx"
                }
        },
        "HttpHeaders": {
                "User-Agent": "Docker-Client/18.06.1-ce (linux)"
        },
        "proxies":{
        "default":{
               "httpProxy": "http://proxy.my.local:8080",
               "httpsProxy": "http://proxy.my.local:8080",
               "noProxy": "169.254.169.254,127.0.0.1,localhost"
        }
        }
}

-------------------------------------------------

▼ プロキシ設定できてるかどうか確認
-------------------------------------------------
docker info | grep roxy

// 下記で環境変数が設定されているかどうか
env | grep roxy

apt-get install vim
-------------------------------------------------

▼ 下記の要否はまだわからん
// ■■■■■■■■■■■■■■■■■■■■■■■■■■ プロキシ設定
// ※ Systemd も Init も両方やらないと、docker info に Proxy 情報でなかった。
//
// ▼ Init
// cat <<EOF > /etc/sysconfig/docker
// [Service]
// Environment="HTTP_PROXY=http://proxy.my.local:8080/" "HTTPS_PROXY=http://proxy.my.local:8080/" "NO_PROXY=169.254.169.254,/var/run/docker.sock"
// EOF
//
// // Docker再起動
// systemctl restart docker
//
// // Dockerのプロキシ設定確認
// docker info| grep -i proxy









▼ 下記の要否はまだわからん
// ■■■■■■■■■■■■■■■■■■■■■■■■■■ Docker Compose インストール
// ▼ 最新バージョンをダウンロード
// ※ 下記Github のリリースノートで、最新バージョンを確認しておく
// https://github.com/docker/compose/releases
//
// // ダウンロード
// sudo curl -L "https://github.com/docker/compose/releases/download/1.23.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
//
// // バイナリに実行権限を適用
// sudo chmod +x /usr/local/bin/docker-compose
//
// // パスを通す
// sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
//
// // command completion をインストール
// sudo curl -L https://raw.githubusercontent.com/docker/compose/1.23.2/contrib/completion/bash/docker-compose -o /etc/bash_completion.d/docker-compose
//
//
