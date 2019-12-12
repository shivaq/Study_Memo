■■■■■■■■■■■■■■■■■■■■■■■■■■ 列指向ストレージは、圧縮に適している
・必要な列だけをディスクからクエリする ＋ データを圧縮する
 →ディスクスループットの需要がさらに減る

・同じ値が連続している事が多い

▼ bitmap encoding
・値が異なる列の数は、行そのものの数よりしばしば小さい
※ 何億ものセールストランザクションはあるけど、製品は10万種類だけとか

・take a column with n distinct values and turn it into n separate bitmaps:
・異なる値ごとに一つのビットマップ。
各行に一つのビット
1 は、その行がその値を持っていることを表し、0 は持っていないことを表す。
・n がすごく小さい場合、一行1ビットで格納が可能 ※ 国名コラムはおおよそ 200
・n が大きい場合、ほとんどのビットマップにはたくさんの0が入る(we say that they are sparse).
 →In that case, the bitmaps can additionally be run-length encoded
  →コラムのエンコードがすごくコンパクトになる

・上記のようなビットマップインデックスは、データウェアハウスによくあるクエリにとてもよくあっている
例)
WHERE product_sk IN (30, 68, 69)
 →Load the three bitmaps for product_sk = 30, product_sk = 68, and product_sk = 69
  →bitwise OR of the three bitmaps を計算する

例)
WHERE product_sk = 31 AND store_sk = 3
 →Load the bitmaps for product_sk = 31 and store_sk = 3
  →calculate the bitwise AND
  ※各コラムの行は、同じ順番で並んでいる。
   →あるコラムのビットマップの K 番目のビットは、他のコラムのK番目のビットマップに対応している

▼ Column-oriented storage and column families
・Bigtable を継承したCassandra and HBase には、コラムファミリーというコンセプトがある。
しかし
そいつらをコラム指向と呼ぶのはミスリーディングだ
なぜか
各コラムファミリーはある行の全コラムを、a row key と一緒に格納している
そして
コラム圧縮をしない
よって、Bigtable モデルはおおよそ行指向といっていい


■■■■■■■■■■■■■■■■■■■■■■■■■■ Memory bandwidth and vectorized processing
▼ データウェアハウスのクエリのボトルネック
・ディスク to メモリの帯域
・メインメモリ to CPU キャッシュの帯域
・CPU パイプラインのバブル
・branch mispredictions(条件分岐予測の失敗？)
・single-instruction-multi-data (SIMD) をモダンなCPU で使うこと



▼ 列指向ストレージは、CPUサイクルを効率的に扱うことにも長けている
・クエリエンジンは、圧縮されたコラムデータのまとまりを取得できる。そしてそのまとまりは、CPU の L1 キャッシュにフィットする。
 →そして、タイトループでそれをイテレートする(関数呼び出しがないループ)
・CPUが関数呼び出しがないループを実行する時、処理対象の各レコードで関数呼び出しや条件分岐がある場合と比べてずっと早く実行できる。
・列圧縮によって、一つの列から、L1キャッシュに対して、より多くの行をフィットさせられる
・「bitwise AND と ORなどの演算子」は圧縮された列データの塊を、直接操作できるよう設計されている。
※ このやり方を、vectorized processing という。
