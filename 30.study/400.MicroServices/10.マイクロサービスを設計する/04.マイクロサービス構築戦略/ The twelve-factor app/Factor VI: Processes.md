# Factor VI: Processes 
## Execute the app as one or more stateless processes
* Running application instances should be stateless
*  any kind of data that should persist beyond a single request/transaction needs to be stored in an external persistence service.

### web アプリのユーザーセッション(これじゃだめ)
* ユーザーセッションのデータはよくプロセスのメモリに格納される
* またはローカルのファイルシステムに
* 上記は、同じユーザーが同じインスタンスにリクエストしてくるとの想定

### web アプリのユーザーセッション(こうしましょう)
* ユーザーのセッションはステートレスにする
* セッション状態を外部のデータストアに移す such as Redis or Memcached.
