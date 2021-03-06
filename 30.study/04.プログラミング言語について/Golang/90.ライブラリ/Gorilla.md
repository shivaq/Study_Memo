# Gorilla web toolkit
* consists of a collection of Go packages that together helps build powerful web applications quickly and efficiently.

## gorilla/mux
* Gorilla のキーパッケージ
* a request router and dispatcher
* →accepts an incoming HTTP request, then decides what to do based on the nature of the request



### 例
* クライアントがHTTP リクエストをWebサーバに送る
* Web サーバの HTTP router dispatcher component が、下記を検知する
* やってくるリクエストは、GET メソッドと、相対URL`../events/id/1345`を含んでいるということ
* サーバはイベントID 1345 の情報を取得してクライアントに返す

## パッケージの使い方
* インストール
```go
go get github.com/gorilla/mux
```
* コードにインポート

```go
                              import "github.com/gorilla/mux"
```

* ルータOBJの作成

```go
r := mux.NewRouter()
```

## サブルーターの作成が可能
* 相対URL`/events`のサブルータ
* /eventsから始まる相対URL 宛のHTTPリクエストを引き受ける OBJ

```go
eventsrouter := r.PathPrefix("/events").Subrouter()
```
* 例)イベント情報を取得する
```go
eventsrouter.Method("GET").Path("/{SearchCriteria}/{search}").HandlerFunc(handler.findEventHandler)
```
* 例) 全てのイベントの情報を取得する
```go
eventsrouter.Method("GET").Path("").HandlerFunc(handler.allEventHandler)
```
* 例)新規イベントを作成
```go
eventsrouter.Method("POST").Path("").HandlerFunc(handler.newEventHandler)
```

### 使用例
* イベント名 または ID をもとに イベントを検索
* 一度に全てのイベントを取得
* 新規イベント作成

### ▼ イベント名 または ID をもとに イベントを検索
#### ID
* 相対URL →`/events/id/3434`
* メソッド →GET
* HTTP ボディはデータなし
#### Name
* 相対URL →`/events/name/jazz_concert`
* メソッド →GET
* HTTP ボディはデータなし

## ▼ 一度に全てのイベントを取得
* 相対URL →`/events`
* メソッド →GET
* HTTP ボディはデータなし

## ▼ 新規イベント作成
* 相対URL →`/events`
* メソッド →POST
* HTTP ボディは下記のような感じ
![](/Users/yasuakishibata/Google_Drive/Referenced_by_markdown/sdfjasdfj.png)
