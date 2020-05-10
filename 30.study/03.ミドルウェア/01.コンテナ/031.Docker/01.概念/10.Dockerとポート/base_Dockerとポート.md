# コンテナはマシンのリソースに「直接は」アクセスできない
- コンテナ内で動いているプログラムは、同じマシン上の他のプログラムから隔離されている
- コンテナ内で動いているプログラムは、ネットワークのポートなどのリソースに**直接は**アクセスできない


# Port Forwarding
- -p でポートフォワーディング
`docker container run -p 9999:8888 --name hello cloudnatived/demo:hello`
`docker container run -p HOST_PORT:CONTAINER_PORT ...`
- 上記で構築されるコンテナのアプリの場合、コンテナのプライベートなポート 8888 をリッスンしている
- ローカルマシンのポートが、コンテナのポートに forward されるよう設定している
- ローカルマシンの HOST_PORT は、コンテナの CONTAINER_PORT に転送される
- 何番のポートでもよいが、わかりやすくするために 9999 にしている





# コンテナ実行
```sh
# コンテナの 8888 ポートに、ローカルマシンの9999 ポートをトランスポートしてアクセスする コンテナを、cloudnatived/demo というレポジトリの hello ブランチのイメージから実行する
# コンテナ実行中は、(ローカルコンピュータの)ホストのポートへのリクエストは、コンテナのポートに自動転送される
docker container run -p 9999:8888 --name hello cloudnatived/demo:hello
```

## syntax
docker container run -p HOST_PORT:CONTAINER_PORT ...

# イメージをビルド


```sh
# 現在のディレクトリから、(Dockerfile をもとに) ソースを取得し、新規イメージをビルドする
di build -t myhello .
```

# コンテナ実行
```sh
# コンテナの 8888 ポートに、ローカルマシンの9999 ポートをトランスポートしてアクセスする コンテナを、myhello というイメージから実行する
dc run -p 9999:8888 myhello
```

# go スクリプトに変更を加え、イメージをリビルド
di build -t myhello .