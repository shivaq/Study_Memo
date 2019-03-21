# FROM →ベースimageを 定義。 Official なDocker imageを使うのがおすすめ
# Docker Hub のオフィシャル Python の Simple Tags から選択
# コロンの左がほしいDockerimage、右がタグ。タグなしだと。$Latest が選択される
FROM python:2.7-alpine

###################### 下準備 ######################
# ビルド時に走らせるスクリプトを記載
RUN mkdir /app

# 以降のパスは、 WORKDIR からの相対パスとなる
WORKDIR /app


###################### 依存するソフトのインストール ######################
# ソースのファイルを → 送信先にコピー。下記は、WORKDIR からの相対パス
COPY requirements.txt requirements.txt

# 設定ファイルに従って一括インストール
RUN pip install -r requirements.txt

###################### ソースコードをアップ ######################
# 最初の .  →この Docker ファイルがいるのと同じ階層の、すべてのファイルを再帰的に。。。
# 次の . WORKDIR の場所にコピー
COPY . .

###################### Docker File の司令順について  ######################

# 依存するソフトのインストール前にソースコードアップの司令を記述すると、
# ソースコードを変更してリビルドする際、以降の処理を再度実行することになる
# つまり、改めてインストールが実行されてします
# なので、インストールとかを先に勧めておいたほうがいい


###################### Docker Image とキャッシュ  ######################

# 複数のDocker Image が、このDocker File と同じ FROM python:2.7-alpine の場合、
# この Python ベースImageは、Docker Image をまたがって共有される
# 結果、ファイルサイズはとても小さくなる


###################### メタ情報 ######################

# Key:Value タグみたいなもの。これを使って、外部からコンテナをフィルタリングできたりする
LABEL maintainer="Shibata"\
      version="1.0"


# 他のコンテナがアクセスできる、パブリックなボリューム情報
VOLUME ["/app/public"]

###################### Docker Image 起動時の処理 ######################
# Docker Image RUN 時に走る処理
CMD flask run --host=0.0.0.0 --port=5000
