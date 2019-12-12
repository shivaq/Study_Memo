## Closure とは

### 普通、変数にはスコープがある
```js
function() {
  var a = 1;
  console.log(a); // works
}    
console.log(a); // fails
```


* 殆どの言語は、変数を探すときに、現在のスコープから探し始め →その変数の宣言が見つかるまで、ひとつずつ親のスコープに上がっていく


```js
var a = 1;
function() {
  console.log(a); // works
}    
console.log(a); // works
```


* ブロックや関数が役割を終えると、ローカル変数は不要となり、メモリから消える



### closure は persistent なローカル変数スコープ
* closure はスコープである。
* コードの execution がブロックの外に行ってもほじされる、persistent スコープである


* 変数が宣言されたブロックの実行が終わっても

* 親のスコープも含めて

* 参照が維持される

* スコープOBJ とローカル変数は、関数に紐付けられる

* 関数が持続する限り、上記も持続する

### closure を使うと関数 portable になる
* その関数を全く別の文脈から呼んでも、スコープとローカル変数とが持続する



### closure の例
```js
// 変数に関数を格納する
outer = function() {
  var a = 1;
  // ローカル変数に関数を格納する
  var inner = function() {
    console.log(a);
  }
  // ローカル変数に格納された関数を返す
  return inner; // this returns a function
}

// 変数に格納された変数を変数に格納する
var fnc = outer(); // execute outer to get inner
// 上記変数に格納された変数を実行する
fnc();
```
* fnc は inner のポインタを格納する変数
* a は fnc が維持する限り維持する
* a は closure 内に存在する

* 関数内関数を定義する

* 内部関数は、その関数の外部にある全ての関数のローカル変数にアクセスできる

* inner 関数の親が持つローカル変数も、inner 関数のスコープとなる

* inner のスコープは、outer のスコープに対する親ポインタをもつ。

* 通常、関数の外に出ると、そのローカル変数はメモリから消え去る

* しかし、inner 関数を返して fnc 変数に割り当てると、 outer 関数を抜けても、outer 関数のローカル変数にアクセスできる

* inner 関数が定義された段階で、inner 関数のスコープにあった変数は、全て持続する

* このスコープが、closure

* closure の使えない関数の場合、outer 内の変数はGCされて、fnc() を実行するとエラーになる

* JavaScript は closure があるため、ある関数を定義した段階でそのスコープ内にある変数は、その関数が持続する限り持続する
