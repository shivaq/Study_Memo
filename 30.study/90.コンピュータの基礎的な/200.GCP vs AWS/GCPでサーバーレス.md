### サーバーレス使用上の注意
アプリのアーキテクチャが、サーバーレスコンピューティングに影響を与える

###### 例)
* VM-based database backend solution を使っているとする。
* 例えば Cloud SQL instance
* 結果、its scalability will be heavily limited by the performance of the SQL instance

#### サーバーレスの便益を享受できるケース
* Effectively transforming the backend into an API service
 →all the benefits of serverless computing (little to none server management, scalability, low cost)

### チームのコラボがやりやすくなる
* UI design, is now decoupled;
* team members may each work on their own functions
as opposed to a full app

### サーバーレス の落とし穴
* サーバーレスは全てのユースケースにマッチしているわけではない
* 断片化を許容し、うまく対処しないといかん
分析、監視、メンテ、オーケストレート、全体像俯瞰が困難。難解なジグゾーパズル
* Serverless and FaaS are not necessarily the same。チームの経験値によって、ゼロからサーバーレスにしたり、少しずつサーバーレス化していったりするとよい。

## Event-driven computing
* アプリのコードはシークエンシャルに実行されていく
* そのシークエンスは  a contract crystalized in the deployment

#### 一般的なアプリのコードは密結合
1. input (request) がトリガーとなる
2. an output (response) として終わる


* インプット、アウトプットとそのシークエンスは密結合な感じでまとめられている
一度デプロイされたら、修正はできない。修正したい場合は、コードを修正して、再度デプロイする必要がある

#### Event-driven computing にブレークダウン
コントラクトをブレークダウン →シークエンス内の各部分を疎結合にしていく
1. 関数が終了時にイベントを emit(a piece of message with execution contexts)
2. イベント emit 後に何がなされるかは気にしない →イベントをどう扱うかはつぎのステップの裁量にまかせる
3. Google Cloud Pub/Sub and Apache Kafka などの a data/event streaming solution が使われる。
4. イベントの送り手は event publishers
イベントの受け手は event subscribers

### publisher/subscriber pattern
* grants greater development freedom
* easier team collaboration
* It is now possible to hot plug/swap blocks of code without redeployment
* developers can easily add multiple subscribers to the same event stream

### ユースケース
One of the most prominent use cases of such structures is real-time data analytics

![alt リアルタイムデータ分析](/Users/yasuakishibata/Google_Drive/Referenced_by_markdown/dskfjas;lkdfja.png)

#### 昔ながらのやり方
* 下記がバッチで動く
* アプリが詳細を logging agents に書き込む
* データ分析チームがその情報から insight を抽出する

#### イベントドリブンなやり方
* イベントストリームに、subscriber として接続する
*  →データ分析チームはリアルタイムにデータを取得できる
* リアルタイムデータ処理 と ウェアハウジングを、Google Cloud Dataflow
and Google BigQueryとつなげる
*  →データ分析もリアルタイムになされる
developers can have analysis done in real-time as well.

* クラウドはスケーラビリティがめちゃあるから、メッセージキューがイベントをすばやく渡すことが可能


#### アプリ開発に The data/event streaming solutions を使う
##### Google Cloud Pub/Sub
* Google Cloud Pub/Sub を使う。
* subscriber が一時的に失敗する
*  →Google Cloud Pub/Sub が配信をリトライする

#### Google Cloud Pub/Sub のスナップショット利用
* イベントをリプレイできる。テストに有用。

# Synchronous operations
* the dissociation of inputs (requests) and outputs (responses) が複雑さを呼び込む
* リクエストを送っても、レスポンスが受け取れるかどうかは保証されない

#### asynchronous operations が許されれば問題ない
SNS に写真を投稿した時に、それが友人のフィードに即反映されなくても、問題にはならない

#### Synchronous operations が必要なときの問題
例)
* ユーザーがWebサイトを開く
* ページはすぐにロードされる事が期待される
* ページがロードされないと、つぎのアクションを起こせない

#### Event-driven computing はすばやくアクションを起こせない
* イベントが発行される
* そこから時間がかかる？
as the request is considered served
when the event is published;
developers have to manually retrieve the response.

#### 解決策
* using statically generated pages throughout the app
* イベントが発行される
*  →直後に、結果をポーリングする
*  →setting up dedicated asynchronous routines for synchronous operations

#### 例)
* when people request a webpage,
* prepare it synchronously first,
*  →  then publish an event to the message queue
* so that event subscribers can track the action
