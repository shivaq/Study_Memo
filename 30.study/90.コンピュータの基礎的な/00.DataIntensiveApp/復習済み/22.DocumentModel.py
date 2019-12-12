▼ ドキュメントモデルが向いている
アプリのデータがドキュメントみたいな構造だったら、ドキュメントモデルを使うのはよい考えだと思う。
※一対多のツリー構造。ツリー全体を一度のロードできる。
・RDBの、複数のテーブルに構造をシュレッディング(ドキュメントを分割)していく手法は、めんどくさくて意味もなく複雑なアプリコードを生み出しがちである。
・a self-contained document(レジュメなど)

▼ ドキュメントモデルの制約
・ドキュメント内でネストされているアイテムを直接参照することができない。
どう参照するかというと、ユーザーリストの251の、二番目のアイテム、みたいな感じで参照する。
※ 階層パスのアクセスモデルのように
※ でも、よっぽど階層が深くなってない限り、大した問題じゃない
・JOIN のサポートが弱いことが問題になるかどうかはアプリによる。
多対多関係は、分析アプリでは決して使われないかも知れない。
デモ、多対多を多様するアプリには、利点は少ないかも知れない。

・非正規化によってJOINの必要性を減ずることは可能である。けどそうすると、非正規化されたデータの一貫性を担保するためのタスクが必要になる。
・アプリ側で、DBに対して複数回リクエストを行うことで、JOINをエミュレートすることはできる
しかし、アプリ側に複雑さを仕込むことになるし、DBのJOIN機構と比べて遅い。
そういう場合、ドキュメントモデルを採用することで、アプリは複雑になるし、パフォーマンスも悪化する。
・相互関係がたくさんあるデータにとっては、ドキュメントモデルはめんどい、RDBはまあいけるかな、で、グラフモデルが一番向いてる。




■■■■■■■■■■■■■■■■■■■■■■■■■■ ドキュメント オリエンテッド データベース
・非リレーショナルデータベースの一種
・データをJSON のようなドキュメントとして保存し、クエリするために設計されている。
・ドキュメントモデルは、カタログ、ユーザープロファイル、コンテンツ管理システムなど、各ドキュメント固有のもの
・時間の経過とともに進化するユースケースでうまく機能
・ブログプラットフォームや動画プラットフォームなどのコンテンツ管理アプリケーションに好んで用いられる
・データモデルを変更する必要がある場合に更新する必要があるのは、影響を受けるドキュメントのみです。スキーマの更新は不要で、変更を加えるためにデータベースのダウンタイムは必要になりません。
・semi-structured data
・NoSQL の主なカテゴリーのうちの一つ
・Grapu database もDODに似ているが、関係性レイヤーを追加して、ドキュメント同士をすばやくリンクさせることができる
・key value store の subclass
・KVSとDODとの違いは、key value store はDBから不透明に見えるが、DODは、ドキュメントの内部構造のメタデータを、DBエンジンが最適化のために使う
・RDBは、データを別々のテーブルに格納し、一つのOBJは複数のテーブルに拡散している。
DODはすべての情報をDBの一つのインスタンスに格納し、各OBJは他のOBJと異なる構造を持ちうる。結果、ORMが不要。

▼ ドキュメントという概念
・DoD によって定義は若干異なるが、一般的に言って、
Document というものは、データを標準フォーマットまたはエンコードによってエンコードして、カプセル化している。
・格納されたドキュメントは、プログラミングのコンセプトでいうところのOBJと同じといえなくもない。ざっくりいうと。
・標準スキーマや同じセクション、スロット、部品、キーをもつことにこだわらなくていい。
・プログラムというものは、異なるOBJを扱うものだし、フィールドもたくさんあり、同じクラスのものであっても異なる様相だったりする。

▼ エンコード
XML, YAML, JSON, and BSON, as well as binary forms like PDF and Microsoft Office documents

▼ からのフィールドなどない
RDBと違って、ドキュメントにはからのフィールドなどない。
よって、他のレコードが同じ構造かどうかなどを気にすることなく、新しい情報を追加することができる。

▼ ドキュメントとメタデータ
ドキュメントDBは、だいたいコンテンツと一緒に追加のメタデータが提供されている。
メタデータはデータストアが提供するものだったりする。何のためかって言うと、ドキュメントを整理するため、セキュリティのため、他の特別な機能のため。

▼ Key
キーは、ユニークなキーによって識別される。
キーは、IDや文字列やURIやパスだったりする。
キーを使ってDBからドキュメントが取得される。
DBはたいていキーにインデックスをつけており、しばしばDBにドキュメントを作成/挿入する際にキーが必要となる。

▼ Retrieval
DODのもう一つの特徴として、データの取得方法に、キーによる取得だけでなく、
API やクエリ言語を提供することで、ユーザーがコンテンツやメタデータに基づいてドキュメントを取得できるようにしている

例)
あるフィールドの値が一致するすべてのドキュメントを取得するようクエリする。

▼ ただの key/value と異なるところ
・DODの実装によって、利用可能なクエリAPIや、クエリのパフォーマンスは大きく異なる。
インデックスのオプションや、可能な設定も大きく異なる。
・理論上、key/value ストアの値は、ストアからは不明瞭であり、本質的にブラックボックスである。
ドキュメントストアに似た検索システムもあるが、コンテンツの構造についてはあまり理解できないようである。
・ドキュメントストアはコンテンツを分類するために、ドキュメントのメタデータを利用する。
それによって、ある数値が電話番号なのか郵便番号なのかを理解する。
この能力によって、555を含む全ての電話番号を検索する際に、郵便番号を無視したりできる。

▼ Editing
DDはドキュメントのコンテンツやメタデータを更新する方法として、ドキュメント全体やドキュメントの構造体の個々の部分を入れ替える方法を提供している。

▼ ドキュメントをオーガナイズする方法
Collections:
実装によって異なるが、ドキュメントが一つのコレクションのみに属するような成約があったり、複数のコレクションに属することが許されていたりする。

Tags and non-visible metadata:
ドキュメントのコンテンツの外側の、追加データ

▼ Directory hierarchies:
ツリー構造のようなものでオーガナイズされたドキュメントのグループ。
パスやURIやによってオーガナイズされることが多い。




■■■■■■■■■■■■■■■■■■■■■■■■■■ NoSQL
▼ なぜ使われるか
・RDBができることよりも、さらなるスケーラビリティが求められるから。大きなデータセット、より高い書き込みスループットなど。
・商用のDB製品より、無料でオープンソースなソフトウェアが好まれるようになった
・Reational モデルではうまく対処できない特定のクエリ操作
・Relationalスキーマの制約へのフラストレーションと、より動的で表現豊かなデータモデルの需要




■■■■■■■■■■■■■■■■■■■■■■■■■■ データモデルとアプリのOBJとの翻訳
・データをRDBに格納していると、アプリのコードとデータベースモデルのテーブル、行、列などとの間で翻訳をするレイヤーが必要になる
・ORM(Object-relational mapping) フレームワークがボイラープレートをへらすのに役立つが、万能ではない。

▼ Linkedin データの例
・ユーザーテーブルには user_id, first_name, last_name などがユーザーごとに存在する。
・1 to N relation: 学歴、連絡先、役職

▼ 1 to N の表現方法
・ポジション、教育、連絡先を、別のテーブルにおいて、外部キーでユーザーテーブルを参照する
・構造化されたデータタイプやXMLデータをサポートする SQL を使う。
 →結果、各業に複数の値を持つデータを格納できる
  →さらに、それらドキュメントを対象として、クエリやインデックス化ができる。
  JSON もデータタイプとしてサポートされていたりする。

▼ JSON or XML document にエンコードする
ジョブや教育などのデータをJSON or XML document にエンコードして、列に格納する。
で、アプリにその構造とコンテンツを翻訳させる。
欠点は、DBは大概こういった情報に対してクエリできないってこと。

■■■■■■■■■■■■■■■■■■■■■■■■■■ JSON があっているデータ

・JSON はXML よりずっとシンプル。
・対応しているDBエンジン
Document-oriented databases like
・MongoDB、RethinkDB、CouchDB, and Espresso

{
"user_id": 251,
"first_name": "Bill",
"last_name": "Gates",
"summary": "Co-chair of the Bill & Melinda Gates... Active blogger.", "region_id": "us:91",
"industry_id": 131,
"photo_url": "/p/7/000/253/05b/308dd6e.jpg",
"positions": [
{"job_title": "Co-chair", "organization": "Bill & Melinda Gates Foundation"}, {"job_title": "Co-founder, Chairman", "organization": "Microsoft"}
], "education": [
{"school_name": "Harvard University", "start": 1973, "end": 1975},
{"school_name": "Lakeside School, Seattle", "start": null, "end": null} ],
"contact_info": {
"blog": "http://thegatesnotes.com", "twitter": "http://twitter.com/BillGates"
} }

・JSON はアプリのコードとストレージレイヤーとのミスマッチを減ずると感じる人もいるかもしれない。しかし JSON にもデータフォーマットとしての欠点がある。

・RDB でリレーショナルなデータを取得したい時、複数回のクエリを行うか、JOIN を繰り返すかをする必要がある。
・JSON の場合は、一度のクエリで十分。すべての関連する情報が一つの場所に集まっている。
・ユーザープロファイルから、学歴や連絡先といった情報をたどっていくと、情報の関係性がツリー構造であることがよくわかる。


■■■■■■■■■■■■■■■■■■■■■■■■■■ スキーマが柔軟？
▼ Schema-on-read ※ スキーマレスとか言われるけど、こっちのほうがより適していると思う
これは、プログラミング言語における動的(runtime) 型チェックに似ている。
・データの構造が、コントロール不可能な外部のシステムによって決まり、いつでも変わりうるような場合、Schema on read が向いている。

▼ schema-on-write は静的(compile-time)型チェックに似ている。
双方のアプローチの違いは、データのフォーマットを変える時によく分かる。

▼データ構造が変わったときの対処法
例)
氏名を一つのフィールドで扱っている状態から、個別のフィールドに分ける方針となった時

・Schema-on-read の場合
新しいフィールドをドキュメントに反映させて、アプリのコードが古いフォームのドキュメントを読むことができるようにする

if (user && user.name && !user.first_name) {
// Documents written before Dec 8, 2013 don't have first_name
user.first_name = user.name.split(" ")[0];
}

・schema-on-write の場合
 →マイグレーションを行う

ALTER TABLE users ADD COLUMN first_name text;
UPDATE users SET first_name = split_part(name, ' ', 1); -- PostgreSQL
UPDATE users SET first_name = substring_index(name, ' ', 1); -- MySQL

※ スキーマの変更は、時間が掛かるし、ダウンタイムが発生すると言われている
しかし、そうとも言えない。ほとんどのRDBシステムは、ALTER TABLE ステートメントをin a few milliseconds で終わらせられる。
ただし、MySQL は別だ。あれは数分から数時間のダウンタイムが発生する。ALTER TABLE でテーブル全体をコピーするから。
回避策としてもツールは存在しているけれども。
