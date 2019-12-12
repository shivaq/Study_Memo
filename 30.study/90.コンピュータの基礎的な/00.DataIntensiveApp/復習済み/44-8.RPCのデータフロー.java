■■■■■■■■■■■■■■■■■■■■■■■■■■ The problems with remote procedure calls (RPCs)
▼ a remote procedure call (RPC) から派生したものども
Enterprise JavaBeans (EJB)
Remote Method Invocation (RMI) // limited to Java
The Distributed Component Object Model (DCOM) is limited to Microsoft platforms.
The Common Object Request Broker Architecture (CORBA) is excessively complex,
and does not provide backward or forward compatibility

▼ RPC とは
・RPC モデルがなそうとしていること
リモートにあるネットワークのサービスにリクエストを行う
あたかも
同じプロセス内のプログラミング言語のメソッドなどを呼び出すのと同じように
※ (this abstraction is called location transparency).

▼ A network request is very different from a local function call:
▼ ネットワークリクエストは、失敗した時に、その先で何が起きてるかわからないことがある
• "A local function call" は予測可能で、
コントロール下にあるパラメータにのみ依存して、成功するか失敗するかのどちらか。

"A network request" is unpredictable:
the request or response may be lost due to a network problem,
or the remote machine may be slow or unavailable,
そして、上記はコントロールすることができない。
 →ネットワークの問題はよくあることなので、リクエストに失敗したときはリトライするなど、失敗に対応しておかなければならない。

▼ ネットワークリクエストは、タイムアウトの結果、何の結果もないまま return されたりする。
結果、何が起こっていたのかわからない


▼ リトライしたものの、リクエストは到達していて、レスポンスのみ失敗していた、といったことが起こりうる
 →その場合、リトライの結果のアクションが複数回なされることになるため、冪等性のメカニズムが必要になる。

▼ ネットワークリクエストの場合、ネットワーク状況によって同じ処理が早かったり遅かったり不安定

▼ ローカルならメモリに参照(ポインタ)を効率的に渡せるが、ネットワークリクエスつの場合はエンコードしなければならず、複雑なOBJを扱う場合問題になりうる

• クライアントとサーバとは異なるプログラミング言語を扱っている可能性があり、結果翻訳が必要となり、一方は long が扱えない場合など、汚くなる。


■■■■■■■■■■■■■■■■■■■■■■■■■■ Current directions for RPC
問題はたくさんあるが、RPC は消え去ってはいない。
RPC が使われるのは、同じ組織の、同じデータセンター内のサービス間だったりが多い。

Avro、Thrift、Protocol Buffers なども、対応する RPC フレームワークが存在する。

gRPC はストリーム(一つのリクエストと一つのレスポンスだけで構成されるのではなく、一連のリクエストレスポンスが続いていく)
をサポートしている。

いくつかのフレームワークは、service discovery ができる。
 →クライアントが、どのIPとポート番号で、特定のサービスを見つけられるかを探ることができる


 ■■■■■■■■■■■■■■■■■■■■■■■■■■ Data encoding and evolution for RPC
evolvability のためにも、RPC クライアントとサーバとが、独自に変更されたりデプロイできたりすることが重要

DB経由のデータフローと比べて、サービス経由のデータフローはシンプル。
サーバ側が常に最初に更新されて、
クライアント側はその次に更新されると推測できる
よって、
リクエスト時にのみ後方互換性が必要で、
前方互換性はレスポンス時にのみ必要。


▼ RPC スキーマ の前方後方互換性の属性は、それが使うエンコーディングから継承される
 The backward and forward compatibility properties of an RPC scheme are inherited from whatever encoding it uses:

 • Thrift, gRPC (Protocol Buffers), and Avro RPC can be evolved
 according to the compatibility rules of the respective encoding format.






▼ サービスの互換性は難しい
なぜなら、RPC は組織の境界線を超えて交信する際に使われたりするから。
なので、サービスプロバイダは、クライアントに対するコントロールができないし、
クライアント側にアップグレードを強いることもできない。

結果、
互換性については長期間(または無期限に)メンテナンスし続ける必要がある。

互換性が損なわれるような変更がなされる場合は、
サービスプロバイダは複数のバージョンのサービスAPIを同時にメンテナンスすることになる。
