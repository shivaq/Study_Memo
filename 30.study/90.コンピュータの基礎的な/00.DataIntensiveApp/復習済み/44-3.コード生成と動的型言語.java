■■■■■■■■■■■■■■■■■■■■■■■■■■ Thrift and Protocol Buffers は コード生成に依存している
Thrift and Protocol Buffers rely on code generation:
・スキーマを定義する
 →そのスキーマを実装したコードを生成できる(選択した言語に対応したもの)

▼ 静的型チェック言語には有用// such as Java, C++, or C#,
・効率的なメモリ内構造にできるから
・IDE で、型チェックとオートコンプリートが使える
※ そのデータ構造にアクセスするプログラムを書いている時

▼ 動的型チェック言語には不向き // JavaScript, Ruby, or Python
・コンパイル時の型チェックがなされない
 →コード生成するポイントがない

▼ コード生成そのものが邪魔くさくなるケース
・動的に生成されるスキーマ(DBテーブルから生成されるAvroなど)の場合、コード生成は邪魔
・コード生成はデータにアクセスする上で、不必要なお荷物
・Avro は静的型言語のためのコード生成もできるが、他のコード生成が使われない場合に限る


■■■■■■■■■■■■■■■■■■■■■■■■■■ Avro は 動的型チェック言語に適している
・object conainer file(which embeds the writer’s schema) を使う場合、Avro ライブラリを使ってシンプルに開く事ができる
 →JSON を見るのと同じような要領でデータを見ることができる
 →The file is self-describing since it includes all the necessary metadata.
・Apache Pig のような、動的型チェック言語には特に適している
 →Avro をただ開くだけ
  →分析開始
   →取り出したデータセットを Avro フォーマットのファイルに書き込む // スキーマについて考えなくていい


■■■■■■■■■■■■■■■■■■■■■■■■■■ The Merits of Schemas
Protocol Buffers, Thrift, and Avro はXML や JSON より実装も使い方もシンプル
 →いろんなプログラミング言語をサポートするようになった。

しかし、
XML や JSON はより詳細な validation ルールが有る
 (e.g., “the string value of this field must match this regular expression” or
 “the integer value of this field must be between 0 and 100”).

▼ 多くのデータシステムは、独自のバイナリエンコーディングを実装している
・ほとんどのRDB はネットワークプロトコルを使って、DBにクエリを投げている
そういうプロトコルは、通常DB特有のもので、DBはドライバーを提供することで、DBのネットワークプロトコルがデコードできるようにしてる。

ここで言いたいのは、
JSON、XML、CSVといったテキストデータフォーマットがよく使われているけれど、
スキーマに基づいたバイナリエンコーディングも有力な選択肢だということ。

▼ スキーマに基づいたバイナリエンコーディング の利点
・JSON 型と比べてずっとコンパクト。エンコードされたデータからフィールド名を取り除いているから。
・スキーマがすなわちよいドキュメントにもなる。
・そして、スキーマはデコードされる必要があるため、 it is up to date
・DBスキーマをキープすると、デプロイ前にスキーマ変更による前方後方互換性がチェックできる
・静的型チェック言語を使う場合、スキーマからコードを生成できる機能が有用。コンパイル時に型チェックができる。
