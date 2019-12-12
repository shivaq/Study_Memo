# docker build コマンド

* 引数として、GO のバージョンを指定できる

docker build --build-arg GO_VERSION=1.11.2


```sh
# 引数でバージョン指定されない場合、使用されるデフォルト値
ARG GO_VERSION=1.14

# First stage: build the executable.
FROM golang:${GO_VERSION}-alpine AS builder

# 実行中のコンテナ内で使われ、非特権ユーザーとしてプロセスを実行するユーザーとグループファイルを生成する
# root で executable を実行するのはよろしくないとのこと
RUN mkdir /user && \
    echo 'nobody:x:65534:65534:nobody:/:' > /user/passwd && \
    echo 'nobody:x:65534:' > /user/group

# アプリがHTTPS エンドポイントへのアクセスに使う CA証明書をインストールする。 git によって必要な dependencies が取得される。
# アプリが使わなければ、不要な命令
RUN apk add --no-cache ca-certificates git

# Set the working directory outside $GOPATH to enable the support for modules.
WORKDIR /src

# Code の前に dependencies を取得しておく
COPY ./go.mod ./go.sum ./
RUN go mod download

# Import the code from the context.
COPY ./ ./

# Build the executable to `/app`. Mark the build as statically linked.
RUN CGO_ENABLED=0 go build \
    -installsuffix 'static' \
    -o /app .

# Final stage: the running container.
FROM scratch AS final

# Import the user and group files from the first stage.
COPY --from=builder /user/group /user/passwd /etc/

# Import the Certificate-Authority certificates for enabling HTTPS.
COPY --from=builder /etc/ssl/certs/ca-certificates.crt /etc/ssl/certs/

# Import the compiled executable from the first stage.
COPY --from=builder /app /app

# Declare the port on which the webserver will be exposed.
# As we're going to run the executable as an unprivileged user, we can't bind
# to ports below 1024.
EXPOSE 8080

# Perform any further action as an unprivileged user.
USER nobody:nobody

# Run the compiled binary.
ENTRYPOINT ["/app"]
```
