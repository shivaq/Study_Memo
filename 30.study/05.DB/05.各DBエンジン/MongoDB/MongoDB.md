# MongoDB とは
* NoSQL DBエンジン
* Document ストア

## mongod コマンド
* DBをrunするために実行するコマンド
* mongod を通じて MongoDB のデータにアクセスする

## mongo コマンド
* MonogDB上のデータをテストするために使う、クライアントツール
* monogo コマンドで mongod とコミュニケートする

## Collection
* 商用 MongoDB は通常複数のコレクションからなっている
* 各コレクションは、異なるデータを表す
* ▼ 例 booking サービスは booking コレクション、events サービスは events データコレクションを扱う

## DataModel
* Golang では struct を使う

## BSON
* データ交換フォーマット
* 主にMongoDBのデータストレージ及びネットワーク転送フォーマットとして利用されている
* 単純なデータ構造や連想配列（MongoDBではオブジェクトまたはドキュメントと表す）を示すバイナリ構造
* 「バイナリ型JSON」の略語
*  binary-encoded serialization of JSON-like documents. 
