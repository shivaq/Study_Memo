### 【概念】Flags パッケージの使い方
* コマンドラインのフラグをパースすることができる。
* flag を使って、ただ値を取得するだけ








####### Flag の定義
```go
// $1 フラグ名 $2 値 $3 ヘルプ時のメッセージ
flag.Int("flagname", 1234, "help message for flagname")
```














```go
package main

import (
    "flag"
    "fmt"
)

func main() {
    var (
      // フラグを定義
      // (フラグ名、デフォルト値、ヘルプメッセージ)で定義
        i = flag.Int("int", 0, "int flag")
        s = flag.String("str", "default", "string flag")
        b = flag.Bool("bool", false, "bool flag")
    )
    // flag からそれぞれの変数を取得
    flag.Parse()
    // それぞれの型のポインタを出力
    fmt.Println(*i, *s, *b)
}
```





```sh
$ go run sample2_1.go -int 2 -str hello -bool true
2 hello true
```








### flag を使って、変数に渡す



```go
package main

import (
    "flag"
    "fmt"
    "time"
)

func main() {
    var (
        duration time.Duration
        floatNum float64
    )
    // 型名Var() という書き方 KatameiVar()
    // (フラグ名、デフォルト値、ヘルプメッセージ)で定義
    flag.DurationVar(&duration, "dur", 1 * time.Second, "duration flag")
    flag.Float64Var(&floatNum, "float", 0.1, "float flag")

    flag.Parse()
    fmt.Println(duration, floatNum)
}
```



```sh
$ go run sample2_2.go -dur 1h -float 2.3
1h0m0s 2.3
```
