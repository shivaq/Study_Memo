# Factor IV  Backing Services 
## Treat backing services as attached resources
* アプリが依存するサービス(such as databases, messaging systems, or external APIs)は、設定で簡単にスイッチできるようにしておくこと。

## 例 環境変数を使う
* DATABASE_URL
* ローカルでの開発
```
mysql://root:root@localhost/test
```
* 本番環境
```
mysql://root:XXXXprod.XXXX.eu-central-1.rds.amazonaws.com
```
