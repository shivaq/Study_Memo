# Test web-app to use with Pluralsight courses and Docker Deep Dive book
# Linux x64
#
# ベースレイヤーは alpine image
FROM alpine


# メインテナーはこの人
LABEL maintainer="nigelpoulton@hotmail.com"




# ベースレイヤーに乗っかる
# Install Node and NPM
RUN apk add --update nodejs nodejs-npm




# 第三レイヤー
# ビルドコンテストのファイル(アプリのコード)を /src にコピー
COPY . /src



# IMAGE のメタデータなので、新規レイヤーは追加されない
# WorkingDirectory はこちら
WORKDIR /src




# 第四レイヤー
# WORKDIR 上で実行される
# npm を使って package.json に記載された dependencies をインストール
RUN  npm install



# メタデータ
EXPOSE 8080

# メタデータ
# デフォルトのアプリとして、app.js を run
ENTRYPOINT ["node", "./app.js"]
