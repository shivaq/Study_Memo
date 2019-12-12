# init 関数
* 主な仕事は、グローバルコンテキストでは初期化できないグローバル変数を初期化すること

* パッケージが初期化されるときに Golang によって呼ばれる



* 引数はとらない。
* 値を返さない
* どこからも参照できない




# パッケージ内で複数宣言されている場合
* 複数宣言できる
* 同一パッケージの複数ファイル内で init() が定義されている場合、ファイルのアルファベット順に、パッケージ初期化時に呼ばれる

* init 関数は実行される。
* main 関数は call される









##  パッケージスコープでは for ループはできない →init() 内で実行

```go
package main

import "fmt"

var integers [10]int

func init(){
  fmt.Println("app/entry.go ==> init()")

  for i:=0; i < 10; i++{
    integers[i] = i
  }

  func main(){
    fmt.Println("app/entry.go ==> main()")
    fmt.Println(integers)
  }
}
```
