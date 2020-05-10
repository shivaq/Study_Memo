#### Representational State Transfer
* 異なるサービスが交信して、データを交換する方法
* a client and a serve によって、そのコアは構成される
* モダンな Web アプリでは、The RESTful client would be an HTTP client, and the RESTful server would be the HTTP server.
* RESTful applications can also be called web applications
* The communication layer of the RESTful applications is often simply referred as RESTful APIs.

## HTTP クライアントリクエストがやること
1. サーバからのリソースをリクエストする
1. サーバにリソースを追加/更新するリクエストをする

## REST API のクライアントが情報を得るために送る必要があるもの
#### URL
* 今日の警句 を返す API サービス
```
http://quotes.rest/qod.json
```
* MyEvent で、イベントID 1345 の情報を得る場合
```
http://10.12.13.14:5500/events/id/1345
```
1. リクエストメソッド
* the type of operation that we would like to execute.

#### GET
* this is how we request a resource from our HTTP web servers

#### POST
* update or create a resource.
* 更新する場合はID指定`../events/id/1346`
* 新規追加の場合はIDの指定しない`../events`

#### PUT
* create or overwrite a resource
* 新規追加の場合も、まだ存在しないIDの指定ができる`../events/id/1346`
* 更新時は完全上書き。部分更新には PUT を使うべきじゃない

#### DELETE
* delete a resource
* ID 指定で削除する `../events/id/1346`
