▼ REST and SOAP
・正反対の哲学

■■■■■■■■■■■■■■■■■■■■■■■■■■ REST
REST はプロトコルではない。
HTTP のプリンシプルに基づいた、設計哲学といったほうが適している。

シンプルなデータフォーマットが特徴。
URLを使ってリソースを同定したり、
HTTPの下記機能を使ったりする。
cache control, authentication, and content type negotiation.

REST は in the context of cross-organizational service integration で人気。
マイクロサービスと紐づけて語られたりする。

RESTのプリンシプルに基づいて設計された API は RESTful と呼ばれる、。

A definition format
such as OpenAPI, also known as Swagger ,
can be used
to describe RESTful APIs
and produce documentation.

RESTful API は、実験とデバッグが得意。
Web ブラウザや Curl を使ってリクエストができる。コード生成やソフトウェアのインストールも不要。


▼ RESTful API の互換性
RESTful APIs most commonly use JSON (without a formally specified schema) for responses,
and JSON or URI-encoded/form-encoded request parameters for requests.
Adding optional request parameters
and adding new fields to response objects are usually considered changes
that maintain compatibility.

▼ RESTful APIs がサービスの互換性を保つために
URL や HTTP アクセプトヘッダにバージョン番号を入れるケースが多い。

API キーを特定のクライアントを同定するために使うサービスの場合、
別のオプションとして、クライアントのリクエストしたAPIのバージョンをサーバに格納しておき、
バージョンをアップデートすることを、隔離したアドミニストレータインターフェースでできるようにしている。



■■■■■■■■■■■■■■■■■■■■■■■■■■ SOAP
SOAP はXML-based protocolである。
ネットワークAPIリクエストを行うのに使われる。

HTTP を利用するのが一般的だが、
HTTP に依存しないようにしている。
HTTPのほとんどの特徴を使わないようにしている。
そのかわりに、
the web service framework, known as WS-*
といった、広範囲で複雑な関連 standards がある。

the Web Services Description Language, or WSDL
という
an XML-based language
によって、SOAP ウェブサービスの API は記述される。

WSDL は、コード生成ができる
 →クライアントはリモートのサービスに、ローカルのクラスやメソッドを使ってアクセスできる
(which are encoded to XML messages
and decoded again by the framework).
※ これは、静的型チェック言語には有用だが、動的型チェック言語にはあまり向いていない

WSDL は human-readable になるように設計されていない。
そして、
SOAP のメッセージは手動で作るには複雑すぎる。
だから、SOAP をよく使うユーザーは、
ツールや、コード生成や、IDE を頼みの綱にしている。
※ SOAP ベンダーがサポートしていない言語を使う場合、SOAP考える サービスの統合は難しい

などなどの理由から、
大きな会社は利用しているところがまだまだ多いが、
小さな会社の殆どは使いたがらない。


▼ SOAP の互換性
requests and responses are specified with XML schemas.
These can be evolved, but there are some subtle pitfalls [47].
