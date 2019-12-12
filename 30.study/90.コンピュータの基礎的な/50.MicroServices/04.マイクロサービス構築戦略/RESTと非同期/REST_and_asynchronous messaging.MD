* マイクロサービスでは、個々のサービスは互いに交信せねばならん。
* 交信に一番よく使われるのは RESTful web services
* RESTful は 通常 HTTP に基づいて作られる
* RESTful は通常 the **client/server** model with a r**equest/reply** communication model. に従っている
* 実装もメンテも簡単
* the synchronous request/reply pattern は、マイクロサービスが複雑になると限界を迎える

![alt 非同期](/Users/yasuakishibata/Google_Drive/Referenced_by_markdown/skdjfaksdjfak;sd.png)

## Synchronous request/reply コミュニケーション
* アプリのユーザーDBを管理するユーザーサービスがあるとする
* ユーザーが作られると、いつでも、このシステム内の他のサービスがユーザー生成に気づく必要がある
* RESTful HTTP を使う場合、ユーザーサービス は他のサービスに、**REST calls** を使って通知する
* これはコンポーネント間の密結合につながる。
* なぜなら、ユーザーサービスは(ユーザー管理ドメインに影響を受ける)他のサービスについて知っていないといけないから

## publish/subscribe pattern
* サービスは、他のサービスがリッスンできるイベントを emit する
* サービスを emit する側は、誰がサブスクライブしているか気にしなくていい
* サブスクライバーも、イベントがどこから来たのか知らない
* 非同期アーキテクチャはスケールしやすい
#### 図の例
* ユーザーサービスは、新規ユーザーが作成されたことを伝えるイベントを発行する
* 他のサービスは、このイベントにサブスクライブできる
*  →新規ユーザーが生成されるとサウスクライバーに通知される

### the message broker---上記アーキテクチャに必要なコンポーネント
* This component accepts published messages
* and routes them to their subscribers
* 通常、キューを中間ストレージとして使う

## publish/subscribe pattern の欠点
* 複雑さが加わる
* デバッグが難しくなる。単一のトランザクションが複数のサービスをまたいで行われる
