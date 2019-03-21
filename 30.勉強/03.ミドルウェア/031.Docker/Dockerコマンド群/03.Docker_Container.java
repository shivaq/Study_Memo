■■■■■■■■■■■■■■■■■■■■■■■■■■ コンテナ停止
// コンテナ内で動いているプロセスに SIGTERM シグナルを送る。
docker container stop web1

■■■■■■■■■■■■■■■■■■■■■■■■■■ いらないコンテナを削除
// affectionate_wilson は、ls で表示される NAMES
docker container rm affectionate_wilson
// container 引数はいらないんじゃないか？
docker rm 80430f8d0921

▼ すべてのコンテナを削除するハッキーな方法
// -a がすべてのコンテナをリストし、 -q がコンテナIDのみを返す。そのリストが docker rm コマンドに渡される。 -f が動いているコンテナさえも削除する
docker rm -f `sudo docker ps -a -q`





■■■■■■■■■■■■■■■■■■■■■■■■■■ シンプルDocker 実行
▼ インタラクティブモードで ubuntu を実行して、/bin/bash を走らせる
// タグを指定して、どのソースを使っているかを把握すべし
docker run -it --rm ubuntu:16.04 /bin/bash




■■■■■■■■■■■■■■■■■■■■■■■■■■ ポートを指定して、名前もつけてコンテナ作成
▼ Dockerを バックグラウンドで起動
// もしくは、Daemonized コンテナと呼ぶ場合もある
// -d →デタッチモード。別ターミナルを起動してチェックとかしなくていい
▼ ポートを指定
// -p →ホストのポート 8080 が、コンテナのポート 80 にマップされる
▼ 任意の名前をつける
// --name で任意の名前もつけられる
docker run -d -p 8080:80 --name static_web shivaq/static_web nginx -g "daemon off;"


■■■■■■■■■■■■■■■■■■■■■■■■■■ ENTRYPOINT と CMD を Dockerfileで設定している
// ENTRYPOINT ["/usr/sbin/nginx"]
// CMD ["-h"]
// 上記に引数が渡され、nginx は foreground で動く
docker run -t -i shivaq/static_web:1.1 -g "daemon off;"


■■■■■■■■■■■■■■■■■■■■■■■■■■ てんこもりコンテナ実行
▼ インタラクティブモード
// -it →--interactive + --tty
// -interactive → コンテナがユーザーのインプットを待つ。これがない場合は、すぐに exit する。
// --tty →インプットを送るための pseudo-tty

▼ コンテナ停止後、自動削除されるようにセットして起動
// --rm フラグをつける
▼ コンテナが意図せぬ失敗担ったとき、自動起動するようにセット
//  --restart=on-failure:5
// 例えば、service docker restart しても、起動する。再起動は5回試みられる。
// -- restart=always で、常に再起動
▼ 環境変数を指定
// -e →コンテナに環境変数を設定

▼ ベースImageを指定
// ここでは、自前のImage web1 を指定
docker container run -itd --rm --restart=on-failure:5 -p 5000:5000 -e FLASK_APP=app.py --name web1 web1





▼ 常に実行させておくアプリやサービスに、Damemonized モード。
-------------------------------------------------
sudo docker run --name daemon_dave --rm -d ubuntu /bin/sh -c "while true; do echo hello world; sleep 1; done"
-------------------------------------------------




■■■■■■■■■■■■■■■■■■■■■■■■■■ 開発時のやり方
▼ 開発時に、ボリュームを使ってコード変更が反映されるように仕込んで起動
// コンテナにボリュームをマウントする
// -v $PWD:/app 現在のディレクトリの、/app ディレクトリ ※ DockerFile 内で mkdir してる
// プロダクションバージョンは、コード変更は頻繁ではない想定のため、ボリュームは使わない。
▼ デバッグモードで起動
// FLASKS の機能ではあるが、-e FLASK_DEBUG=1
docker container run -itd --rm --restart on-failure -v $PWD:/app -p 5000:5000 -e FLASK_APP=app.py -e FLASK_DEBUG=1 --name web1 web1
-------------------------------------------------
▼ Fileを更新すると、ログにそれが出力される
* Serving Flask app "app.app"
* Forcing debug mode on
* Running on http://0.0.0.0:5000/ (Press CTRL+C to quit)
* Restarting with stat
* Debugger is active!
* Debugger PIN: 279-394-901
// 下記で、変更を検知し、リロードがなされる
* Detected change in '/app/app.py', reloading
* Restarting with stat
* Debugger is active!
* Debugger PIN: 279-394-901
-------------------------------------------------









■■■■■■■■■■■■■■■■■■■■■■■■■■ ボリューム について
▼ ホストのディレクトリを、コンテナにマウント
-v <host_path>:<container_path>
ホスト上の指定したディレクトリ <host_path> を <container_path> の位置に、 Data Volume としてマウント
// 例） -v $PWD:/app
・使用例)動的にファイルをコンテナ内部で使いたい場合
・使用例)逆にホスト側のファイルを操作したい場合



▼ ホストディレクトリ未指定で、コンテナにマウント
-v <container_path>
・コンテナを起動するタイミングでホスト上にディレクトリが作られる
・コンテナを削除するとそのボリュームが消える(正しくは参照が切れる)
・ホストのディレクトリは「/var/lib/docker」配下に隔離されている
 →ホストに干渉しない

▼ --volumes-from <container>
<container> で指定したコンテナの Data Volume を全部マウントしてコンテナを起動





▼ 他のコンテナのボリュームを参照
・同じ物理ホスト上にいる必要あり
-------------------------------------------------
▼ コンテナ1 FLASK
docker container run -itd -p 5000:5000 --net mynetwork -v $PWD:/app --rm --name web2 -e FLASK_APP=app.py -e FLASK_DEBUG=1 web2

▼ コンテナ2 redis
// -v ボリューム付き
// --volumes-from  →web2 のボリュームを使わせてもらう
docker container run -itd -p 6379:6379 --net mynetwork -v web2_redis:/data --volumes-from web2 --rm --name redis redis:5.0-alpine





■■■■■■■■■■■■■■■■■■■■■■■■■■ ボリューム状態を確認
// コンテナに接続？して、ls とかが打てるようにする
// sh →シェルを起動
// cd /app/public →ls で、「--volumes-from web2」で定義した、web2 のパスが見えることを確認
// /app/public 配下のFileは、
docker container exec -it redis sh



■■■■■■■■■■■■■■■■■■■■■■■■■■ ポート指定いろいろ
▼ 確認方法
▼ ポートアサイン状態を確認
docker ps
// 0.0.0.0:32768->80/tcp みたいな感じで表示される
-------------------------------------------------


▼ ランダムにポートをマップ
// -p →Dockerが動いてるときに公開するポートを指定。
// ランダムなホストのポートを、コンテナのポート 5000 にマップする
docker container run -it -p 5000 -e FLASK_APP=app.py web1_2


▼ ホストのポートとコンテナのポートとをマップ
docker container run -it -p 5000:5000 -e FLASK_APP=app.py web1

※ 注意
-------------------------------------------------
複数のコンテナが一つのホストのポートを共有することはできない。
よって、80:80 みたく、ホストの 80 を指定してしまうより、8080 みたく、
デフォルトの HTTP ポートとは違う番号を指定してやるのがよい
-------------------------------------------------

▼ 特定のインターフェイスとポートとを紐づけ
// 127.0.0.1 のランダムポートを、コンテナの 80 ポートと紐づけ
docker run -d -p 127.0.0.1::80 --name static_web_lb shivaq/static_web nginx -g "daemon off;"

▼ ローカルホストと紐づけた web コンテンツを確認
1. ポートアサイン状態を確認
2. curl で見てみる
curl localhost:32768







▼ Dockerfile で EXPOSE したすべてのポートを publish
// -P 大文字Pで、パブリッシュ
docker run -d -P --name static_web_all shivaq/static_web
nginx -g "daemon off;"
