# Go と conurrency
* concurrent programming をサポートしている

## goroutine
* 関数を同時実行する
* thread の軽い版。並列化を行うために使う。
* main 関数が gorouitne 内で動いている


## channel
* goroutine と goroutine とが、指定したタイプの値をやりとりするメカニズム

* String のチャンネルを作成 `ch := make(chan string)`

一つの goroutine が 一つの channel に対して 送信または受信 を試みたとき、
goroutine は他の goroutine が同じ送信/受信を試みるまで(何を？)ブロックする
<!-- it blocks until another goroutine attempts the corresponding receive or send operation, -->

### 関数たちを別々の goroutine で実行する
* 通常、関数は、現在のgoroutine を、エラーが発生するまでブロックする。
* IOW,同じ goroutine 内で、複数の goroutine を呼ぶことはできない。
* →解決策は、各関数をべつの goroutine で呼び出す。
* →どうやるか。関数名の前に、"go" と書くだけ。

### 複数 goroutine がエラーを返したときに対応方法
* 複数の goroutine が同時に動いているとする
* それぞれが同時に error OBJ を返したとする
* それぞれの goroutine が返した error をキャプチャーできるか？
* →channel を使って、goroutine 間の通信を行う。


### 複数 goroutine を使うと、プログラムが抜けてしまいかねない
```go
// select -> 現在の goroutine をブロックして、複数のチャンネルを待つ。
// 最初に返ってきた channel が、それに対応する channel を立ち上げる。
//ここで、ブロックされるということが重要。
// ブロックされないと、error が起きないとき、プログラムが exit してしまう。
// 単一の goroutine で http.ListenAndServe() が実行されていたときは、この関数が main goroutine をブロックして、exit されることはなかった。
select {
case err := <-httpErrChan:
  log.Fatal("HTTP Error: ", err)
case err := <-httptlsErrChan:
  log.Fatal("HTTPS Error: ", err)
}
```


## buffered channels
* 通常の channel とは異なる別の channel
* 現在の goroutine をブロックせずに値を渡すことができる。


## channel は スレッドセーフではない
* 同じ channel に、複数の goroutine からアクセスしてはいけない
