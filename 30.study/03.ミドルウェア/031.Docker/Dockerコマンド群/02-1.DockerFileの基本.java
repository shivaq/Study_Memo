■■■■■■■■■■■■ Dockerファイル
・Docker Image をビルドするための指示を設定するファイル
・Dockerファイルの書く指示が、DockerImageに新たなレイヤーを追加する。

docker build docker_file で、イメージをビルドできる

▼ ビルドコンテキスト？
-------------------------------------------------
・現在のフォルダ
例
// docker image build -t genx:1.0 .
・GitHub などのURL
例
// docker image build -t genx:1.0 git@gitlab.com:lucj/genx.git
-------------------------------------------------


▼ 各指示が、Image に新規レイヤーをレイヤーが追加し、Image がコミットされる
-------------------------------------------------
// 既存のImageをベースImageとして使ってコンテナを動かす
FROM ubuntu:16.04
// MAINTAINER Image のオーナーを設定
// 各指示がコンテナに変更を加える →新規レイヤーに対し docker commit に類する処理をする
MAINTAINER Yasuaki Shibata "shivaq777@gmail.com"
// 上記指示の結果としての新規コンテナに対し、変更 →コミット →新規コンテナを run が繰り返される
RUN apt-get update; apt-get install -y nginx
RUN echo 'Hi, I am in your container'\
>/var/www/html/index.html

EXPOSE 80
-------------------------------------------------











■■■■■■■■■■■■■■■■■■■■■■■■■■ 使い方
1.build 環境として用意したディレクトリ内に、Dockerfile を作成
2.Dockerfile から Image をビルドする
docker build -t shivaq/static_web:1.0 .







■■■■■■■■■■■■■■■■■■■■■■■■■■ 命令
RUN
-------------------------------------------------
コンテナビルド時に動かすコマンドを指定
// 内部では /bin/sh -c を実行している
-------------------------------------------------

CMD
-------------------------------------------------
コンテナ起動時に動かすコマンドを指定
例
// CMD ["/bin/true"]
// パラメータを指定
// CMD ["/bin/bash", "-l"]
引数で代替する場合
// docker run -i -t shivaq/static_web /bin/true
備考
・CMD と 引数を併用した場合、引数が優越する
・CMD は一つのみ指定できる。複数のプロセスやコマンドを動かしたい場合は、Supervisor などのサービスマネジメントツールを使用する必要がある
http://supervisord.org/introduction.html
-------------------------------------------------

ENTRYPOINT
-------------------------------------------------
コンテナを "executable" として動くよう設定する
docker run の引数を、引数として受け取るコマンドを指定。
例
// ENTRYPOINT ["/usr/sbin/nginx"]
// パラメータを指定
// ENTRYPOINT ["/usr/sbin/nginx", "-g", "daemon off;"]
// CMD との併用で、run コマンドの引数を指定するとそれが CMD を上書きし、指定しないと CMD が ENTRYPOINT に渡される
// ENTRYPOINT ["/usr/sbin/nginx"]
// CMD ["-h"]
実行例
// docker run -t -i shivaq/static_web -g "daemon off;"
備考
・CMD と異なり、引数が override しない

エラー
// docker run -it shivaq/static_web -g "daemon off;"
// 上記の結果、下記
starting container process caused "exec: \"-g\": executable file not found in $PATH": unknown.
// 実行対象の DockerImage のタグが未指定だったため、ENTRYPOIN を設定していないImageを対象に実行してエラーになってた。
-------------------------------------------------



EXPOSE 8080
コンテナ内のアプリが使用するポートを指定



WORKDIR
-------------------------------------------------
コンテナと、ENTRYPOINT と、CMD が実行される "working directory" を指定
例
// RUN コマンド実行時と、ENTRYPOINT とで、ワーキングディレクトリを使い分けている
// WORKDIR /opt/webapp/db
// RUN bundle install
// WORKDIR /opt/webapp
// ENTRYPOINT [ "rackup" ]
-------------------------------------------------

ENV
-------------------------------------------------
Image ビルド時及びコンテナ内で使われる 環境変数を設定
例
// Ruby のなんかのパスを指定する環境変数を指定することで、
// コマンドがその環境変数を使う
// ENV RVM_PATH=/home/rvm RVM_ARCHFLAGS="-arch i386"
// RUN gem install unicorn
例２
// 自作変数を定義し、Dockerfile 内で 参照する
// ENV TARGET_DIR /opt/app
// WORKDIR $TARGET_DIR
コマンド引数の場合
// docker run -ti -e "WEB_PORT=8080" ubuntu env
備考
・環境変数複数指定時は、一行指定にする。結果、
-------------------------------------------------

USER
-------------------------------------------------
DockerImageを 何ユーザーで動かすかを指定
例
// このImageで生成されたコンテナが nginx ユーザーで動かされる
// USER nginx
ユーザー指定方法例
// USER user
// USER user:group
// USER uid
// USER uid:gid
// USER user:gid
// USER uid:group
備考
・未指定の場合、デフォルトのユーザーは root
-------------------------------------------------

VOLUME
-------------------------------------------------
該当Imageから生成されるすべてのコンテナに Volume を追加する
例
// VOLUME ["/opt/project"]
複数ボリューム指定例
// VOLUME ["/opt/project", "/data" ]
-------------------------------------------------

ADD
-------------------------------------------------
fileやディレクトリを、ビルド環境からDockerImage に追加する
例
// ADD software.lic /opt/application/software.lic
例２
// ADD http://wordpress.org/latest.zip /root/wordpress.zip
例３
// ソースが tar, gzip, bzip2, xz の場合、自動的に unpack される
// 展開先の同名fileがあっても、上書きされない
// ADD latest.tar.gz /var/www/wordpress/
備考
・ADD 対象のfileやディレクトリが変更されると、ビルド時のキャッシュが無効化される
・ビルドディレクトリ または コンテキスト 外から ADD することは不可能
・ソースがディレクトリと判断される条件 →末尾に / がついている場合
・展開先パスがない場合、ディレクトリ等が自動生成される
・自動生成されるfileやディレクトリは 755 で作られ、uidとGIDは 0
▼ COPY との違い
・ソースに URL が使える
・tar fileがソースの場合、展開もしてくれる
・COPY より多くのことができる
-------------------------------------------------

COPY
-------------------------------------------------
(ビルドコンテキスト内の)ローカルファイルをコピーする。
例
// COPY conf.d/ /etc/apache2/
備考
・展開等はできない
・該当ディレクトリ外のものはコピーできない。
・Dockerdaemonにアップロードされたビルドコンテキストにて、コピーが実行される。
・ソースがディレクトリの場合は、ディレクトリ全体がコピーされる
・コピー先は、コンテナ内の絶対パス
・コピー先が存在しない場合は、自動生成される
▼ ADD との違い
ソースに URL は使えない
・tar fileがソースの場合、展開できない
-------------------------------------------------

LABEL
-------------------------------------------------
DockerImageにメタデータを追加できる
例
// LABEL version="1.0"
// LABEL location="New York" type="Data Center" role="Web Server"
LABEL確認方法
// docker inspect jamtur01/apache2
// "Labels": {
// "version": "1.0",
// "location": "New York",
// "type": "Data Center",
備考
・key/value で書く
・一行で書くことがおすすめ。じゃないとレイヤーがその分増える
-------------------------------------------------


STOPSIGNAL
-------------------------------------------------
コンテナに停止命令を出したときにコンテナに送られるシステムコール信号を設定する。
-------------------------------------------------

ARG
-------------------------------------------------
ビルド時に、docker build --build-arg フラグ経由で渡される変数を定義する。
例
// ARG build
// ARG webapp_user=user // 2つ目の ARG はデフォルト値の定義
docker build 例
// docker build --build-arg build=1234 -t jamtur01/webapp .
 // →build という変数には1234が格納され、webapp_user という変数は、指定されていないためデフォ値を格納
備考
ここで credentials を渡すと、build プロセス時に露出してしまうので、やってはいけない
デフォルト定義 ARG
// HTTP_PROXY
// http_proxy
// HTTPS_PROXY
// https_proxy
// FTP_PROXY
// ftp_proxy
// NO_PROXY
// no_proxy
-------------------------------------------------

SHELL
-------------------------------------------------
デフォルトのシェルを上書きする

・Linux のデフォルト
["/bin/sh", "-c"]

・Windows のデフォルト
["cmd", "/S", "/C"]
他の選択肢 →powershell

備考
・Windows のように、複数のシェルがあるプラットフォームの場合に有用
・Dockerfile 内で複数回指定できるため、走らせるコマンドごとに使い分けが可能
-------------------------------------------------

HEALTHCHECK
-------------------------------------------------
コンテナに対するヘルスチェック設定
例
// HEALTHCHECK --interval=10s --timeout=1m --retries=5 CMD curl http://localhost || exit 1
timeout →これを超えたら failed と識別される値

CMD
ヘルスチェック時に実行するコマンド。これが失敗するかどうかが、ヘルスチェックの結果となる
exit 0 はヘルシー exit 1 はあんへるしー

ヘルスチェック結果確認方法
// docker inspect --format '{{.State.Health.Status}}' static_web
healthy

ヘルスチェック時のログ確認方法
// docker inspect --format '{{range .State.Health.Log}} {{.ExitCode}} {{.Output}} {{end}}' static
0 Hi, I am in your container
-------------------------------------------------

ONBUILD
-------------------------------------------------
image が他のImageのベースに使用されたときに発動するトリガーを追加する。
使いたい場面
// ・Image 自身が構築する環境特有のビルドスクリプトを実行するとき
// ・ある場所から追加するソースコードが必要だけれども、まだそれを利用できないImageを取り扱うとき
例
// ONBUILD ADD . /app/src
// ONBUILD RUN cd /app/src; make
例
・apache_base_Dockerfile を元にImageをビルド →shibaq/apache2
・上記Imageを元にfrom_apache_base_Dockerfile をビルド →webapp
 →webapp のビルド時に、FROM インストラクションの直後に ONBUILD の指令が実行される

備考
・命令は、ビルドプロセスの FROM 命令の直後に挿入される
・孫Imageには ONBUILD は継承されない

-------------------------------------------------
