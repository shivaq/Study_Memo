■■■■■■■■■■■■■■■■■■■■■■■■■■ RDS の人
ドワンゴ
オンプレ

クックパッド
フルAWS

S3 に入れた csv からいろいろできる。

MySQL と比べ、ロックを外すのが早い

Shared Storage をマスターとレプリカが共有
レプリカに反映されるまで 20 ミリセカンド

各セグメントごとにS3 に継続増分バックアップをストリーミングでしている。


クラッシュシミュレートなどのテストが、SQL でできる。


▼ Customm Endpoints
スレーブによって、分析用、かすサポ用と分けたい時に、
オンラインクエリ用のリ0-どレプリカと分析クエリ用のそれとを分離可能

フェイルオーバーでmスタに昇格しても大丈夫。


▼ Databse cloning
データコピーではない。
クローン作成は即座
※ マスキングをどうするか、を調べる
コピーだけでは追加課金されない

▼ Aurora サーバーレス
クエリを投げると立ち上がる。

オーロラサーバーレス New Data API

▼ Global Database
異なるリージョンでレプリケーションを1,2秒で行える
DR の際も、1,2秒のデータロスでリカバリできる。


▼ OOM Avoidance
メモリ不足時に、パラメータグループで指定したアクションを実行できる

■■■■■■■■■■■■■■■■■■■■■■■■■■ DynamoDB の人 成田山
アマゾンは ダイナモ
全部NoSQL？

HIPPA にも対応
You build it, you run it.
あんたが作ったものはあんたが運用しなさい。

パーティションキーは分散していくことを推奨

単一キーへのアクセス過多は不得手
LSI とは？
Local Secondary Index


Global Secondary Index

単純なキーバリューだけでなく、LSI、GSIを利用して、複雑な探索が実現できる。

何度も作ったり

やり直しをどれだけはやくできるか、を意識して作っていくと、
勘所がわかってくる。

NoSQL のデザインパターンを理解すること
レビューリピートレビューを繰り返すべし。


▼ 書き込み分散
カキコっ身分さん
MySQL は垂直分散水平分散といったルールが必要になる。

▼ DynamoDB on demand
リクエスト課金

▼ Instant adaptive Capacity

▼ Dynamic   partitioning


■■■■■■■■■■■■■■■■■■■■■■■■■■ Line の北川さん
MySQL Redis OracleDB

MySQL 84%
Oracle DB
Microsoft DB

-------------------------------------------------
NoSQL
Redis 77%
mongoDB

Elastic
HBASE

DBA ってなんだ？
OpenStack 基盤のプライベートクラウド

Redis
OpenStack Trove

MySQL 5.7が 51% 以上

CommunityEdition 95％
3000位上のインスタンス

基本全てオンプレのVMだった
Kubernetis を使ってスケールアップできるようにするプロジェクトがある



MySQL8.0 は本番運用まだしてない
すべてベンチマークを取っている
ベンチマーク取得ツール作った
MySQL8.0の話ばっかり

-------------------------------------------------
Redis Cluster と Redis Sentinel
メッセージング はRedis と
RedisあっとLine でググる

全部オンプレ。AWSに移行計画中。


■■■■■■■■■■■■■■■■■■■■■■■■■■ Yahoo 岩瀬さん
NoSQL は Cassandra がほとんどと少しRedis

カサンドラ
170クラスタ4800サーバが現在の数

DynamoDB 担当者の人は、以前会社でCassandra担当だったが、
大変だった。やっぱDynamoだねって。
