# Mac にインストール

```sh
brew install postgresql@9.6
```







# Golang が使う PostgerSQL のドライバをインストール
```sh
go get github.com/lib/pq
```








# コードにインポート
```go
import(
  _ "github.com/lib/pq"
)
```
