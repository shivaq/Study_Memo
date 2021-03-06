■■■■■■■■■■■■■■■■■■■■■■■■■■ Sort Order in Column Storage
・コラムストアでは、行がどんな順番で格納されているか、ということは大した問題じゃない
・挿入された順で格納するのが一番簡単。
 →結果、新規行の追加は、各コラムファイルにアペンドする形になる。
・Index のメカニズムを使ってソートすることもできる。

▼ コラムストレージをソートする
各列の行を、一度にソートする必要がある。ばらばらになると、行がばらばらになるから。

DB のアドミンなら、通常のクエリの知識から、ソートすべき列を選ぶことができるだろう。
例)
クエリが日時の範囲をよくクエリしている場合、
date_key を第一ソートキーに選ぶ、といった判断ができるだろう。
↓
そして、クエリオプティマイザーがターゲットの月の行だけをスキャンする。
 →全行をスキャンするよりずっと早い

例)
date_key が第一ソートキーだったとする。
 →product_sk が第二ソートキーに最適だとわかるだろう。
なぜか
同じ日の、同じ製品のすべての売上は、ストレージ内でグループ化されているだろうから。
結果
 →特定の日時の売上を、製品別でクエリするのに便利

▼ コラムがソートしているとこれがいい
列の圧縮がやりやすい


▼ ソートオーダー色々
ソートオーダーによって、利益を受けるクエリも異なる。
であればだ、
いろんなソートがなされている同じデータを格納したらいいのではないか？
 →で、クエリパターンにフィットしたソートパターンのデータを必要に応じて使う。

コラム指向ストアで、複数のソートオーダーを保持することは、
 →行指向ストアが複数のセカンダリインデックスを保持するのと少し似てる。
違いは？
行指向：すべての行を、ヒープファイルやクラスター化したインデックスなど、一箇所に格納しており、
 →セカンダリインデックスは、合致する行へのポインタを保持しているだけ
列指向：ポインタはない。値を含んだ列たちがいるだけ。
