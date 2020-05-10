▼ fbthrift とは
Facebook's branch of Apache Thrift, including a new C++ server.


▼ RPC とは
RPC (Remote Procedure Call) は
別のサーバのサービスとして、リモートにのみ存在しているような関数を呼ぶためのもの。

サービスはクライアントにたくさんの関数や手続きをexpose している。
クライアントはそのサービスが expose している関数/手続きそしてそのパラメータが何なのか、を知る必要がある。

▼ Apache Thrift とは
cross-language support で、RPCを実装するフレームワーク。
独自の "Interface Definition Language" (IDL) をもつ。
IDL にて、関数はなにか、パラメータはなにかを定義する。そして、Thrift コンパイラで選択した言語に対応したコードを生成する。
そうすると、関数をJavaで実装し、サーバーでホストして、リモートからPythonで呼び出すこととかができる。

▼ Thrift のようなフレームワークの重要な仕事
・言語不可知IDLの提供
・IDLをコンパイルして、クライアントとサーバーに対し、コードを生成するコンパイラ。

▼ marshaling
・コンパイラが生成したクライアントのコードが、関数のスタブインターフェイスを露出する。
 →スタブコードは関数に渡されたパラメータを、バイナリフォーマットに変換して、ネットワーク越しに遅れるようにする。
 ※ このプロセスを marshaling という。生成されたクライアントのコードは、関数の実際の実装ではない。よって、スタブと呼ぶ。

▼ unmarshaling
サーバにて、開発者は関数の実装にコンパイラが生成したコードを使う。
生成されたサーバサイドのコードは、クライアントから暗号化されたバイナリコードを受信し、
 →対応する言語のOBJに変換する。そして開発者が実装した関数に渡す。
 ※このプロセスをunmarshaling と呼ぶ。
例えばJavaであれば、コンパイラが生成したサーバのコードは開発者が実装するインターフェイスとなる。

▼ IDLのデータ型
関数のパラメータはIDLは固有のデータタイプ(List, Map, Struct, Classes)を定義する。
そしてそれはInt、Stringなどのネイティブなデータ型とは別に定義される。
そしてそれらは対応する言語の実装にマップされる。

▼ SOAP、CORBA
Thirst と SOAP、CORBA はともにRPCのために使用され、独自のIDLをもっている。
SOAP、CORBAは service discovery broker を 関数をクライアントに露出するためのミドルウェアとして使用する。
Thirstは通常Service discovery に Zookeeperを使う。

▼ REST
REST はIDLを持たず、GET、PUTやリモートの関数を呼び、パラメータを渡すURLパターンなどのHTTPメソッドを使用する。
それらを使用することで、RESTを言語不可知(language agnostic)にしている。

▼ メッセージキュー
RESTの場合 →Publish/Subscribe に使用。
RPCの場合 →Client/Server モデル。

Publish/Subscribe の場合、複数の publisher がシリアル化されたメッセージをキューに送る。
メッセージのフォーマットはpublisherによって定義されている。
その定義は意味論的にキューに関連付けられているが、構造が厳密にチェックされることはない。

Subscriberは、キューの持つメッセージの種類を知り、メッセージをサブスクライブする。
Publisher はクライアントのことを知らない。
Subscriber はメッセージの Producer のことを知らない。
彼らが知っているのは、どんな種類のメッセージがキューによって publish or consume されるかということ。
彼らが責任をもつのは、正しいシリアライザー、ディシリアライザーを知っていること。

これが、Client/Server RPC と異なる部分。
クライアントは、何を渡せばいいかを知っているし、
サーバはそれを定義している。誰に渡すのかも知っている。
