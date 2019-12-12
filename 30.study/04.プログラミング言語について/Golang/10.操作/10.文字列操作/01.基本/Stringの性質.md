### String は immutable
* ある String をコピーしても、同じメモリをシェアしている
* →どんな長さの String も、copy が cheap にできる
* string s と sustring s[7:] は同じデータを安全にシェアできている
*  →substring も cheap。新しいメモリ割り当ては起こっていない



```go
s := "left foot"
// s の参照が格納される
t := s
// s の値は別のアドレスに新規に格納される
s += ", right foot"
```






## string を byte slice に変換した上で処理すれば、低コストで文字列処理できる
```go
s := "abc"
// 「s の bytes のコピー」を保持する新 byte array を割り当てる
b := []byte(s)
s2 := string(b)
```
* []byte(s) 変換：
*  →配列全体を参照する slice を生成する
* コンパイラの最適化によって、割当とコピーを回避することがかのうかもしれない
* しかし、一般的に、コピーは必要になる。なぜなら、b が変更されても、s が不変のままであることを保証する必要があるから。
* byte slice から string への再変換も、結果の string が immutable であることを保証するために、コピーが必要
* 不要なメモリ割当や変換を回避するために、bytes パッケージは string パッケージとパラレルな関数をたくさんもっている







### String Literals
* String の値は string literal として記述できる。つまり、ダブルクオートでエンクローズされた一連のbytes `HEllo, 世界`
* Go のソースファイルは常に UTF-8 もエンコードされる





-------------------------------------------------




### 四大String標準パッケージ bytes, strings, strconv, unicode
* strings パッケージ →検索、置換、比較、トリム、スプリット、ジョインなどの関数提供



## Buffer タイプ
* bytes スライスを効率的に操作するために使える
* Buffer は空から始まって、string, byte, []byte が書き込みをするごとに大きくなっていく
* bytes  →string は immutable なので、incrementally にStringを構築したりしたらメモリ割り当てやコピーなどがどんどんふえる
*  →よって、String 操作のときは bytes.Buffer type 等を使ったほうがずっと効率的だったりする





* strconv  →boolean, integer, float をString状態から変換するString をクオートしたり、クオート外したりする





* unicode  パッケージ →IsDigit, IsLetter, IsUpper, IsLower といった分類のためのルールを提供
* →ToUpper、ToLower 等も提供
