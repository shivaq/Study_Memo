# Dockerfile を使ってビルド

1. アプリのコードとDockerfile があるディレクトリ(ビルドコンテキスト)で実行する



cd src/from_scratch_a/



```sh
docker image build -t test:latest .
```




2. 生成された image を使って run

```sh
docker container run docker_go:test
```
