■■■■■■■■■■■■■■■■■■■■■■■■■■ Dataflow Through Databases
・DBでは、DBに書き込むプロセスがデータをエンコードして、
DBから読み込むプロセスが、データをデコードする

・単一のプロセスだけがDBにアクセスするケースもあるかもしれない
その場合
読み手は、同じプロセスのa later versionで、
こう考えることもできる
DBに何かを格納するということは、未来の自分にメッセージを送るようなものだ、と。

・後方互換性は必須。
ちゃんとできてないと、未来の自分が、過去の自分が書き込んだ内容を読めないことになる。

・だいたいが、複数の異なるプロセスが、一度に一つのDBにアクセスする。
この、異なるプロセスたち、というのは
異なるアプリやサービスだったり、
同じサービスの異なるインスタンスたちだったりする
どちらにせよ
アプリケーションが常に変わり続けるような環境では、
こういうことが起こりうる
DBにアクセスするいくつかのプロセスは新しいコードが使われており、
他のプロセスは古いコードが使われている。
たとえば
新バージョンは現在ローリングアップグレードでデプロイ中で、
一部は新しいが、一部はまだ古いコードのままって状況とか。

・前方互換性も必須
DBの値が新しいバージョンのコードで記載され、
続いて古いバージョンのコードによってそれが読み込まれるってことが起こりうるから。

・別の懸念点
レコードのスキーマに、新しいフィールドを追加したとする。
 →新しいコードがその新しいフィールドをDBに書き込んだとする
  →古いコードがそのレコードを読んで、更新して、DBに書き戻すとする
この場合、望ましい状況は
古いコードは新しいフィールドを手付かずのままにする ※ たとえそのフィールドが翻訳できなかったとしても

エンコーディングフォーマットによっては、未知のフィールドの扱いをうまいことしてくれたりするけれど、
アプリケーションレベルでなんとかせんといかん、ということもある
たとえば
DBの値を アプリのOBJ にデコードする
 →そのモデルを再度エンコードする
  →その時、未知のフィールドが翻訳プロセス時にロストする可能性もある。
  だから、気をつけてね。


■■■■■■■■■■■■■■■■■■■■■■■■■■ Different values written at different times
・DBの中には、数秒前に書かれた値と、数年前に書かれた値とが共存しうる。
・アプリの場合は、新しいバージョンのアプリをデプロイする際に、
古いバージョン全体を新しいバージョンに数分でリプレースすることもある。
DBにはそういうことはない
数年前のデータはそのままそこにあり、明示的に書き直したりしない限り、そのままのエンコーディングだったりする。
This observation is sometimes summed up as data outlives code.

データを新しいスキーマに移行することは可能である。
しかし
データセットが大きい場合は高価な処理となる
よって
ほとんどDBは可能な限りそれを回避しようとする

ほとんどのRDBはシンプルなスキーマ変更ができる。
既存のデータを書き直すことなく、新しいコラムをデフォルト値 null で追加したり
で、
古い行が読み込まれた場合、DBはディスク上のエンコードされたデータにはない列をnullで埋める。

LinkedIn のドキュメントDB(Espresso)はAvroを使って、スキーマのevolutionを可能にしている。
結果、
DB全体が単一のスキーマでエンコードされたかのように扱うことができる。
たとえ
過去の様々なバージョンのスキーマでエンコードされたレコードが存在していたとしても

■■■■■■■■■■■■■■■■■■■■■■■■■■ Archival storage
DBのスナップショットを取得することがある。
バックアップ目的にしろ、
データウェアハウスにロードする目的にしろ。
こういった場合
だいたいデータdumpは最新のスキーマでエンコードされる。
たとえ、
オリジナルのエンコーディングが複数のスキーマバージョンを含んでいたとしても。

データdumpは、(これから書き込まれるものではないため)イミュータブルである。
なので、
formats like Avro object container files are a good fit.

さらに、
スナップショットをとったタイミングは、データを分析しやすいフォーマット(Parquet のような列指向フォーマット)にエンコードするのによい機会だと言える。
This is also a good opportunity to encode the data
