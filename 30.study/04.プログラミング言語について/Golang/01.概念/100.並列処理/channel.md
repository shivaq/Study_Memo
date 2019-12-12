# channel とは
* goroutine 同士が交信するために使われる communication オブジェクト
* channel はデータを渡したり、読み込んだりするのに使える data transfer pipe
* ある goroutine がデータを channel に送ると、他の goroutine は同じ channel からそのデータを読むことができる



## データを channel で送る
* channel が転送できるのは一つのデータ型だけ。



## channel の宣言
* `chan` キーワードで、channel を生成できる。
* transport data type が int の channel c を宣言   
```go
var c chan int
```
* channel のゼロ値は nil だが、それでは機能しない

## channel を有効化
* channel を make する。
```go
c := make(chan int)
```
* channel はポインタのため、c にはメモリアドレスが格納されている
* 上記のような channel を 関数やメソッドに引数として渡すことで、
* goroutine がそれを使い、再参照することなくその channel からデータをpush/pull できる




## <- で、channel からデータを読み書き
* `c <- data`  →データを channel に push する
* `data := <- c` →channel c から データを読み込んで、データを変数に push する




## 実際のプログラムを例に解説
```go
package Matching
import "fmt"

func greet(c chan string){
  fmt.Println("Hello " + <-c + "!")
}

// main の goroutine がアクティブになる
func main(){
  fmt.Println("main() started")
  c := make(chan string)

  // go キーワードを使って、(追加の)goroutine として greet 関数を実行
  go greet(c)

  // 値を channel c  に push
  // goroutine はブロックされる
  // Go scheduler が greet goroutine をスケジュールし、実行が開始される
  //  →main goroutine がアクティブになり、続く命令が実行される
  c <- "John"
  fmt.Println("main() stopped")
}
```





### channel のブロック →ブロック解除
* あるデータが channel に書き込まれた時
* デフォルトで、goroutine はブロックされる。
* (他の goroutine がその channel から読み込みを行うと、ブロックは解除される)
* だが、その時同時に channel のオペレーションが、scheduler に、他の goroutine をスケジュールしている
* →結果、そのプログラム同じ goroutine でブロックされ続けることは回避される
* 上記特徴により、手動でのロックやロック解除をしなくて済む


### ブロック解除のための別 goroutine がないとどうなる？  Deadlock
* ▼ 読む時
* channel から読み込もうとしており、かつその channel は利用可能な値を保持していないとする
* 現在の goroutine がブロックされる
* 他の goroutine をアンブロックする(この時、他のgoroutine がchannel に値をpushすることが期待される)
* だが、push されるべき値がないため、ブロックされる。
* ▼ 書く時
* channel にデータを送る →現在のchannelがブロックされる
* →他のgoroutine がそのchannel からデータ読み込みされるまで、ブロックされ続ける
* 読み込みをしてくれる channel が他にない場合、ブロックされ続ける

### Deadlock 例 channel 操作を行うのが main goroutine だけの場合
```go
package main
import "fmt"

func main(){
  fmt.Println("main() started")

  c := make(chan string)
  c <- "John"

  fmt.Println("main() stopped")
}
```



### channel を閉じる
* 閉じるとどうなる？ →データがその channel を通して送られなくなる
`close(c)`


## channel の バッファーサイズを指定
* デフォルトでは buffer サイズ 0 unbuffered
* バッファーがある場合、一杯になるまでは goroutine がブロックされない
* バッファーがいっぱいの状態で、値が push されると、バッファーにある最後の値が破棄される

* 読みこみオペが開始されると、バッファーにある値が空っぽになるまで読み込み続けられる
* つまり、buffer channel を読みここんでいる channel は、buffer が空っぽになるまでブロックされない
