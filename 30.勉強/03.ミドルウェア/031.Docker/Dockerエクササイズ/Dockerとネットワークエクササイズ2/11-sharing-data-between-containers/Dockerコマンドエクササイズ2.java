■■■■■■■■■■■■■■■■■■■■■■■■■■ VOLUME ["/app/public"]
・他のコンテナがアクセスできる、パブリックなボリューム情報を DockerFile で定義
・/app/public は、Dockerfile と同じ階層に作成済み


■■■■■■■■■■■■■■■■■■■■■■■■■■ Docker File の内容がエクササイズ1 から変わっているので、改めて build
docker image build -t web2 . --build-arg HTTP_PROXY=http://ec2-user@proxy.my.local:8080 --build-arg HTTPS_PROXY=http://ec2-user@proxy.my.local:8080





■■■■■■■■■■■■■■■■■■■■■■■■■■ コンテナ実行時に、他のボリュームのコンテナを参照
・同じ物理ホスト上にいる必要あり
-------------------------------------------------
▼ コンテナ1 FLASK
docker container run -itd -p 5000:5000 --net mynetwork -v $PWD:/app --rm --name web2 -e FLASK_APP=app.py -e FLASK_DEBUG=1 web2

▼ コンテナ2 redis
// -v ボリューム付き
// --volumes-from  →web2 のボリュームを使わせてもらう
docker container run -itd -p 6379:6379 --net mynetwork -v web2_redis:/data --volumes-from web2 --rm --name redis redis:5.0-alpine

▼ ボリューム状態を確認
// コンテナに接続？して、ls とかが打てるようにする
// sh →シェルを起動
// cd /app/public →ls で、「--volumes-from web2」で定義した、web2 のパスが見えることを確認
// /app/public 配下のFileは、
docker container exec -it redis sh
