# Persistence: Database, NoSQL, and so on
* A microservices architecture completely isolates each microservice from the rest.

* 各サービスは、data persistence に関しては自身でコントロールすることになる
* とはいえ、個々が独自のやり方を勝手に追求するようにならないよう、
the macro-architecture should provide guidance (sometimes heavy-handed).

## Data persistence のためのオプション
#### １
* One or more data storage rservices including an SQL based relational database and a NoSQL storage system. These provided data storage services should include built-in backups.
* A microservice がアクセスできるのは、そのマイクロサービスのデータに限定されたスキーマのみであり、ユニークなクレデンシャルによってアクセスを制御する。
* In this scheme, the operations team providing the storage service are responsible for its operation.

#### 2
* もしマイクロサービスが bring their own persistence を許されているならば、you should have strict policy requirements for backups and disaster recovery.
* Think about off-site backups, recovery time, fail-over time, and so on.
* In this model, the development team is responsible for the operation of the storage service.

### 一つのDBですべてを賄う伝統的なやり方は断固拒否せよ
You should absolutely, without a doubt, refuse to permit the traditional “open access, one database to rule them all” mentality which permeates the world of monolith development.

### DB を経由した交信はダメダメ
* If your disparate services are able to communicate through the database, unexpected coupling will occur.

### サービスは、自身のデータストアにだけアクセスすべし
Services must only have access to its own data stores,

### サービス間コミュニケーションはネットワークインターフェイス経由で交信せよ
* and cross-service communication must be maintained through their well-defined network interfaces.
