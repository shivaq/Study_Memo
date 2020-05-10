
# multi-stage builds を行っている
# 1st ステージで、オフィシャルな golang イメージを使用
# OS は Alpine Linux
# これで、Golang がインストールされている環境が提供される
FROM golang:1.14-alpine AS build

WORKDIR /src/
COPY main.go go.* /src/
# 上記にて、Dockerfile のあるコンテキストから /src/ にファイルがコピーされ、
# Workディレクトリである /src にて、下記コマンドが実行され、
# /bin/ に、 demo という名のexecutable バイナリファイルが生成される
RUN CGO_ENABLED=0 go build -o /bin/demo

# 2nd ステージで、空っぽコンテナイメージからコンテナ作成
FROM scratch
# 1st ステージの バイナリファイルをコピー
COPY --from=build /bin/demo /bin/demo
# 2nd ステージで作られたコンテナのみが、デプロイされる。
# よって、1st ステージで 350MB だったのが、2nd ステージのみだから 6MBになる
ENTRYPOINT ["/bin/demo"]
