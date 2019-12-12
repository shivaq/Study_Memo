## Docker run オプション
### インタラクティブモード
* -it →--interactive + --tty
* -interactive → コンテナがユーザーのインプットを待つ。これがない場合は、すぐに exit する。
* --tty →インプットを送るための pseudo-tty
* --rm  →exit 時にコンテナを自動削除



```sh
docker run -it --rm ubuntu:16.04 /bin/bash
```





### Daemonized コンテナ作成
* Dockerを バックグラウンドで起動
* インタラクティブなセッションは持たない

* ▼ デーモン化したコンテナのつかいみち
* 動きつづけているアプリやサービスに最適。



* -d →コンテナをバックグラウンドにデタッチモード。





## アプリをコンテナ化
* test:latest というアプリの image を、バックグラウンドで実行

```sh
docker container run -d --name web1 -p 8080:8080 test:latest
```
* →ブラウザで 127.0.0.1:8080 にアクセスすると、アクセスできる







### コンテナに任意の名前をつける
* --name で任意の名前もつけられる。つけない場合はコンテナ指定はIDで行う

```sh
# コンテナが止まるか、プロセスが止まるまで while loop が続く
docker run --name daemon_dave -d ubuntu /bin/sh -c "while true; do echo hello world; sleep 1; done"
```










### ポートを指定
* 複数のコンテナが一つのホストのポートを共有することはできない。

* -p →ホストのポート 8080 が、コンテナのポート 80 にマップされる

```sh
docker run -d -p 8080:80 --name static_web shivaq/static_web nginx -g "daemon off;"
```




#### ランダムにポートをマップ
* ランダムなホストのポートを、コンテナのポート 5000 にマップする
```sh
docker container run -it -p 5000 -e FLASK_APP=app.py web1_2
```




#### 特定のインターフェイスとポートとを紐づけ
* 127.0.0.1 のランダムポートを、コンテナの 80 ポートと紐づけ
```sh
docker run -d -p 127.0.0.1::80 --name static_web_lb shivaq/static_web nginx -g "daemon off;"
```





#### Dockerfile で EXPOSE したすべてのポートを publish
* -P 大文字Pで、パブリッシュ
```sh
docker run -d -P --name static_web_all shivaq/static_web nginx -g "daemon off;"
```
