## MyEvents について
* SaaS application called MyEvents.
* MyEvents is an event management platform that allows users to book tickets for events all over the world.
* With MyEvents, you will be able to book tickets for yourself and your peers for concerts, carnivals, circuses, and more.
* MyEvents will keep a record of the bookings, the users, and the different locations where the events are taking place.

## MyEvents の主なタスク
### Process bookings
* For example, a user makes a booking for a concert next month.
* We will need to store this reservation
* ensure that there are seats available for this event
* confirm no prior reservations were made with the same name, among other things.

### Handle events
* Our application needs to be aware of all the concerts, plays, and other types of events that we're expecting to support.
* We need to know the event addresses, the total number of seats, their duration, and so on.

### Handle search
Our application needs to be capable of performing efficient searches to retrieve our bookings and events.

![](/Users/yasuakishibata/Google_Drive/Referenced_by_markdown/sdkfjaskdfj.png)

## データモデル図？っていうんだっけ？
![alt データの図](/Users/yasuakishibata/Google_Drive/Referenced_by_markdown/ksdjflksadfj.png)

* ユーザーが複数いる場合
* 各ユーザーは複数のイベント予約を持ちうる
* 各ユーザーは一つのイベントと対応する
* 各イベントは、一つのイベントが行われるロケーションと対応する
* ロケーションは、イベントが行われるホールまたはルームと対応する

![alt architecture](/Users/yasuakishibata/Google_Drive/Referenced_by_markdown/kdsjflkasdf.png)

# フロントエンド
* ReactaJS
* アプリのユーザーとの間のインターフェイス
* アプリ本体を構成する他のマイクロサービスとの交信にAPI ゲートウェイを使う

# ビジネスロジックを担うサービス
## Event Service
* 下記を取り扱う
* イベント、ロケーション、それらに対する更新

## Booking Service
* ユーザーが行ったブッキングを取り扱う





All our services will be integrated using a

# サービスを統合するもの
* publish/subscribe architecture based on message queues
* 複数のメッセージキューを使う
* Kafka, RabbitMQ, and SQS from AWS.

# The persistence layer
* 複数のDBエンジンを使う
* MongoDB, and DynamoDB.

# モニタリング
* All of our services will support metrics APIs
* monitor the statistics of our services via Prometheus.
