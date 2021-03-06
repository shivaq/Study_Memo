■■■■■■■■■■■■■■■■■■■■■■■■■■ Handling Write Conflicts
▼ マルチリーダーレプリケーション最大の問題
書き込みコンフリクト →コンフリクト解決が必要

たとえば、
Wikiページを複数ユーザーが同時に書き込もうとしているとする。
ユーザーAとBとが同時にそのページのタイトルを変更し、
それぞれのローカルリーダーで、変更が正しく反映されたとする。
しかし、
変更が非同期でレプリケートされるタイミングで、
コンフリクトが検知される。

■■■■■■■■■■■■■■■■■■■■■■■■■■ Synchronous versus asynchronous conflict detection
▼ single-leader database,
二番目の書き手は、ブロックされるか、最初の書き手の書き込みが完了するのを待つことになる
もしくは
書き込みトランザクションが破棄されて、書き込みのリトライを要求される。

▼ multi-leader setup
双方の書き込みが成功し、コンフリクトが検知されるのは、あとになってから。
そしてそのタイミングでは、
ユーザーにコンフリクト解決を要求するのはおそすぎたりする。

・コンフリクトを同期的に検知する
可能ではある。
書き込みが成功したとユーザーに伝える前に、
書き込みが全てのレプリカにレプリケートされるのを待つ。
だがしかし、
そうすると、
各レプリカで、書き込みを独立的に行えるという、マルチリーダーレプリケーションの利点が損なわれる。

コンフリクトを同期的に検知したいのであれば、
シングルリーダーレプリケーションを採用したほうがよいかもしれない。


■■■■■■■■■■■■■■■■■■■■■■■■■■ Conflict avoidance
・コンフリクトに対する最もシンプルな戦略 →コンフリクトの発生を回避すること
もし、アプリが、「あるレコードへのあらゆる書き込みは同じリーダーを通じて行われること」を保証するならば、
コンフリクトは起こりえない。
ほとんどのマルチリーダーレプリケーション実装の、コンフリクト対応は貧弱なので、
コンフリクト自体の回避はおすすめのアプローチである。

たとえば
ユーザーが自身のデータを編集するようなアプリの場合、
あるユーザーからのリクエストは、必ず同じDCへルートされることを保証するようにする。
で、
そのDCのリーダーを、読み込みにも書き込みにも使う。

異なるユーザーは、例えば地理的近接さから、異なるホームDCを持つ。
しかし、
一人のユーザーの視点からは、シングルリーダー設定と本質的に同一となる。

▼ コンフリクト回避が破綻するケース
あるレコードの読み書きのために指定されたDCを変更したいこともある
・そのDCがfailしたので、別のDCに、トラフィックをリルートする必要がある
・ユーザーが移動して、他のDCが近接するDCになった
 →こなると、コンフリクト回避は破綻する
  →結果、異なるリーダーに対する同時書き込みに対処しなければならなくなる可能性が生じる

■■■■■■■■■■■■■■■■■■■■■■■■■■ Converging toward a consistent state // 一貫した状態に収束させる
・シングルリーダーDBは、書き込みをシークエンシャルに適用する
複数の更新が同じフィールドに対して行われた場合、最後の書き込みがそこに書き込まれる値を決定する。

・マルチリーダーの場合
書き込み順を定義することはない。
よって
最終的値がどうなるか、明確ではない。
リーダーAでのユーザーAの書き込みと、リーダーBでのユーザーBの書き込み、どちらが採用されるか決定できない。
状態が一貫していない。
こんな状態、受け入れられません。
あらゆるレプリケーションスキーマは、
全てのレプリカのデータが、最終的に同じ値になることを保証しなければならない。
なので、
DBは、コンフリクトを、a convergent way で解決しなければならない。

▼ convergent conflict resolution のアプローチ
▼ 書き込みIDが一番大きい書き込みを勝者とする
全ての書き込みにユニークなIDを付与する
(e.g., a timestamp, a long random number, a UUID, or a hash of the key and value)
IDが一番大きい書き込みを勝者とする。
そしてその他の書き込みを破棄する。

IDにタイムスタンプが使われる場合、last write wins (LWW)と呼ばれる
このアプローチはポピュラーだが、データロスの可能性が高い。

▼ レプリカIDが一番大きい書き込みを勝者とする
各レプリカのユニークなIDを付与する。
より大きい値のレプリカIDからの書き込みが、より低いレプリカIDからの書き込みに勝つ。
このアプローチもデータロスが発生しうる。

▼ 値をマージする
例えば、
アルファベティカルオーダーで連結して、“B/C” みたいな値になる。

▼ コンフリクトを別途記録して、ユーザーに提示する
コンフリクトを、明示的なデータストラクチャで記録する
そうして
全ての情報を保持する
コンフリクトを解決するためのアプリコードを用意しておいて、
あとで解決する
※ ユーザーに提示するなどの方法で。

■■■■■■■■■■■■■■■■■■■■■■■■■■ Custom conflict resolution logic
最も適切なコンフリクト解決方法は、アプリケーションによって異なる。

▼ コンフリクト解決はトランザクションレベルでは行われない
コンフリクト解決は通常、
個々の行またはドキュメントレベルで行われる。
トランザクション全体レベルでは行われない
よって、
異なる書き込みを atomically に行うトランザクションがある場合、
各書き込みは、コンフリクト解決の際には個別に扱う必要がある。


ほとんどのマルチリーダーレプリケーションツールは、
コンフリクト解決ロジックを、アプリのコードを使って書くことができる。
そして、そのコードは、書き込み時、または読み込み時に実行される

▼ On write
DBのシステムが、レプリケート変更ログからコンフリウトを検知するとすぐに、
コンフリクトハンドラーがコールされる。
たとえば
Bucardo
 →Perl でスニペットを書くことができる。

これらハンドラーは、だいたいがユーザーに対するプロンプトができない。
バックグラウンドプロセスが走って、ただちに実行される。されなければならない。

▼ On read
コンフリクトが検知される
 →コンフリクトしている全ての書き込みが格納される
そのデータが読み込まれる
 →複数のバージョンのデータがアプリに返される
  →アプリはユーザーにプロンプトを出す
  または
  自動的にコンフリクトを解決する
   →その結果を書き込みとしてDBに返す
たとえば CouchDB はそうしてる。


■■■■■■■■■■■■■■■■■■■■■■■■■■ Automatic Conflict Resolution
・コンフリクト解決のルールはすぐに複雑化する。
よって、カスタムコードにはエラーが混入しやすい。

Amazon の例
ショッピングカートのコンフリクト解決のロジックでは、
カートに追加されたアイテムを保存する。
カートから削除されたアイテムは保存しない。
だから(?)たま〜に削除したはずのアイテムがカートに再度表示されたりすることがある。

▼ 自動コンフリクト解決についてのいくつかのリサーチ
• Conflict-free replicated datatypes (CRDTs)
sets, maps, ordered lists, counters といったデータ構造のファミリー。
複数のユーザーから同時に編集できる。
自動的にコンフリクトを解決できる。
Some CRDTs have been implemented in Riak 2.0

・Mergeable persistent data structures
ヒストリーを明示的に追跡する。
Git のバージョンコントロールのように。
use a three-way merge function (whereas CRDTs use two-way merges).

・Operational transformation
コンフリクト解決アルゴリズム
共同編集アプリで使われる。Google Docsとか。
an ordered list of items の同時編集に特化して設計されている。
such as the list of characters that constitute a text document.

・上記アルゴリズムたちの実装はまだはじまったばかりだが、
将来的に、多くのレプリケーションシステムに統合されていくかも知れない。
自動コンフリクト解決は、
マルチリーダーデータ同期を、アプリが扱うことがよりシンプルになる。


■■■■■■■■■■■■■■■■■■■■■■■■■■ What is a conflict?
・わかりやすいコンフリクト
二人のユーザーが同じレコードの同じフィールドを同時に、異なる値で更新する

・わかりづらいコンフリクト
たとえば
・会議室予約システム
いつ、どの部屋が、どのグループのだれによって予約されたか
を追跡する。
・このシステムが保証しなければならないこと
各部屋が、ある時点で、ある一つのグループの人によってのみ予約されているということ
・いつコンフリクトが起きるか
同じ部屋に対し、同じ時間に、2つの異なる予約がなされたとき。
予約を許可する前に、アプリが 空き状況をチェックしたとしても、
2つの予約が2つの異なるリーダにて行われると、コンフリクトが起こりうる。

// どこがどう subtle conflict だというのか。わからん。
