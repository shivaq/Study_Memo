■■■■■■■■■■■■■■■■■■■■■■■■■■ Dataflow Through Services: REST and RPC
・ネットワーク経由でコミュニケートするプロセスがある場合、
それらコミュニケーションを行う方法がいくつかある。
一般的なのは
クライアント・サーバシステム。

サーバはネットワーク越しにAPIを expose させる
クライアントはAPIにリクエストを行うことで、サーバとつながる。
さて、
サーバがexposeしたAPIのことを、「サービス」と呼ぶ。

▼ The web works this way:
clients (web browsers) make requests to web servers,
making GET requests to download HTML, CSS, JavaScript, images, etc.,
and making POST requests to submit data to the server.

The API consists of a standardized set of protocols
and data formats (HTTP, URLs, SSL/TLS, HTML, etc.).

Because web browsers, web servers, and website authors mostly agree on these standards,
you can use any web browser to access any website (at least in theory!).

▼ Web browsers are not the only type of client.
たとえば
モバイルやデスクトップコンピュータ上のネイティブアプリも、サーバにネットワークリクエストを行うことができる。
ウェブブラウザ内で走っている、クライアントサイドの JavaScript アプリは
XMLHttpRequest を使って an HTTP client になることができる
(this technique is known as Ajax).
この場合
サーバのレスポンスはHTMLではなく、
エンコードされたデータであることが多い。
そうすると、
クライアントサイドのアプリのコードがそれを処理しやすい。

HTTP はトランスポート層として使われる。
けれども、
その上に実装されるAPIは アプリケーション特有のもので、
クライアントとサーバは、そのAPIはの詳細について agree している必要がある。

さらに、
サーバー自身が他のサービスのクライアントになることもある。
※ ウェブアプリのサーバは、DBにとってのクライアントでもある
例えば
大きなアプリを、機能別の小さなサービスに分解する。
そして、
あるサービスが、
ある機能やデータが必要な時に、
別のサービスにリクエストを行う
こういったアプリの構築方法は
伝統的には
service- oriented architecture (SOA)
と呼ばれていたが、最近は
microservices architecture
と呼ばれる


▼ サービスは、DBに似ているところがある
ともに、
クライアントがデータを渡したり、クエリしたりすることができる。

しかしながら、
DBが、クエリ言語を使った任意のクエリができるのに対し、

サービスは
アプリ特有の API をexposeする。
そのAPIは、サービスのビジネスロジック（アプリコード）によって事前に定義された inputs and outputs のみを許可する。

この制約が、ある程度のカプセル化を提供することになる。
サービスはクライアントができること、できないことを強制できる。

▼ A key design goal of a service-oriented/microservices architecture
それは、
サービスたちが独自にデプロイ/進化できるようにすることで、
アプリケーションの変更と保守を容易にすること。

たとえば
各サービスは、一つのチームが own することにする。
そして、
そのチームは（他のチームと調整することなしに）そのサービスの新バージョンを頻繁にリリースできるようにする

In other words,
古いバージョンと新しいバージョンの クライアント・サーバたちが、同時に存在できるようにする。
そして
クライアントとサーバが使うデータエンコーディングは、
異なるバージョンのサービスAPI間で互換性を持たせなければならない。





■■■■■■■■■■■■■■■■■■■■■■■■■■ Web services
HTTP をプロトコルとして使ってサービスと交信する時、
それはWebサービスと呼ばれる。

しかし、Web サービスという呼び名にはちょっと語弊があるといえなくもない。
なぜならば、
WebサービスはWeb上でのみ使われるわけではなく、
いろんなコンテキストで使われるから。


▼ Webサービス が使われる context
1.
ユーザーのデバイス上で走るクライアントアプリは、HTTPを使ってリクエストを行う
(e.g., a native app on a mobile device, or JavaScript web app using Ajax)
これらリクエストは、パブリック インターネットを使って行われる事が多い

2.
あるサービスが、同じ組織が所有する別のサービスに対してリクエストを行う
そしてそれらはしばしば
同じデータセンター内にあったりする
それらは、
a service-oriented/microservices architecture.
の一部だったりする。
こういうユースケースをサポートするソフトウェアのことを、ミドルウェアとよんだりする

3.
あるサービスが、他の組織のサービスにリクエストを行う。
通常これはインターネット経由で行われる。
この時、異なる組織のバックエンドシステム間で、
データが交換される。

オンラインサービスが提供する
パブリックAPI
もこのカテゴリー。
たとえば
クレジットカードの処理システムや、OAuth for shared access to user data.
