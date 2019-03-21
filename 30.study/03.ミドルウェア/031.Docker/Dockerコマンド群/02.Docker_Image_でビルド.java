
■■■■■■■■■■■■■■■■■■■■■■■■■■ ビルド
▼ Docker Image をビルド
// -t タグを付ける
// タグにバージョンをつけてビルド
// 末尾のドット . →ローカルディレクトリから Dockerfile を探す
docker build -t shivaq/static_web:1.0 .
-------------------------------------------------


▼ Github のDockerfileを使用する場合。
-------------------------------------------------
// Dockerは、レポジトリのルートに Dockerfileがある前提でビルドを行う
docker build -t shivaq/static_web:1.0 \
github.com/turnbullpress/docker-static_web
-------------------------------------------------


▼ 普通にリビルド
-------------------------------------------------
docker build -t shivaq/static_web:1.1 .
-------------------------------------------------



▼ キャッシュを使わずにリビルド// 更新日を環境変数として設定
-------------------------------------------------
FROM ubuntu:16.04
MAINTAINER Yasuaki Shibata "shivaq777@gmail.com"
// 下記日付を更新すれば、以降はリビルドされる。
ENV REFRESHED_AT 2016-07-01
RUN apt-get -qq update
-------------------------------------------------
▼ キャッシュを使わずにビルドをする
// apt-get update みたいな指令を実行したいとき。キャッシュされるとアップデートされない。
docker build --no-cache -t="jamtur01/static_web" .
-------------------------------------------------

▼ ビルド途中で失敗したところからビルドを再開する
・下記、Step4 で失敗したとする
-------------------------------------------------
Step 3 : RUN apt-get update
---> Running in 85130977028d
---> 997485f46ec4
Step 4 : RUN apt-get install -y ngin
-------------------------------------------------
・失敗した、Step4 からビルドを再開する場合
// 997485f46ec4 →失敗したステップの直前のImage Id
docker run -t -i 997485f46ec4 /bin/bash
・Dockerは、直前のレイヤーをキャッシュとして扱う
-------------------------------------------------


▼ どうしても proxy 設定がうまくいかないときは引数を使って
// DockerFile内で pip するときなどは、環境変数を読み込んでくれないっぽいので、引数を使用
// docker image build -t web1 . --build-arg HTTP_PROXY=http://ec2-user@proxy.my.local:8080 --build-arg HTTPS_PROXY=http://ec2-user@proxy.my.local:8080
-------------------------------------------------






▼ タグにバージョンをつけてビルド
// タグ web1 の バージョン 1.0

docker image build -t web1:1.0 .
-------------------------------------------------

▼ コンテナの場合、コードを変えたらリビルド必要
ビルドコマンドをもう一度叩く



■■■■■■■■■■■■■■■■■■■■■■■■■■ Image にタグをつける
▼ タグをリネーム
// 同じ中身の別タグを作成し、
docker image tag shivaq/web1 web1

// いらないタグを rm
docker image rm web1



■■■■■■■■■■■■■■■■■■■■■■■■■■ ビルド時のアウトプット
-------------------------------------------------
レイヤーごとにハッシュ値が決まる
・Fileの中身を変えると、変更がなされたレイヤー以降のみ、リビルドされる
-------------------------------------------------
// File内の下記部分を変えると
Step 7/8 : LABEL maintainer="Shibata"      version="1.0"
 ---> Using cache
 ---> 11974fff6755

-------------------------------------------------

Step 7/8 : LABEL maintainer="Shibata da"      version="1.0"
// 古い部分が Remove されて
 ---> Running in e300c8ecbe5e
Removing intermediate container e300c8ecbe5e
// 以降の部分がリビルドされる
 ---> 41648fab7383
Step 8/8 : CMD flask run --host=0.0.0.0 --port=5000
 ---> Running in 2687968c3168
Removing intermediate container 2687968c3168
 ---> bc5d967e2c36
Successfully built bc5d967e2c36
Successfully tagged web1:latest
-------------------------------------------------






■■■■■■■■■■■■■■■■■■■■■■■■■■ Image を削除
▼ タグとレポジトリ指定で Image から タグを削除
docker image rm web1:1.0

▼ Image ID 指定で 削除
docker image rm -f bc5d967e2c36

▼ または
docker rmi web1:1.0





■■■■■■■■■■■■■■■■■■■■■■■■■■ レポジトリ
▼ ローカルレポジトリ作成
docker image tag web1 shivaq/web1:latest
-------------------------------------------------
[ec2-user@ip-10-0-100-224 03-creating-a-dockerfile-part-1]$ docker image ls
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
web1                latest              bc5d967e2c36        15 hours ago        69.4MB
shivaq/web1         latest              bc5d967e2c36        15 hours ago        69.4MB
-------------------------------------------------

▼ リモートの Docker Hub にプッシュ
docker image push shivaq/web1:latest

▼ Pull
-------------------------------------------------
// virtualenv を使っている場合、activate した上でないと失敗した
docker image pull shivaq/web1:latest
