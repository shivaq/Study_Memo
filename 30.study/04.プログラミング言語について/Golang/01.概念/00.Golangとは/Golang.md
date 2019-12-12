## Why Go is good for scripting?

*
While python and bash are popular scripting languages,
*
C, C++ and Java are not used for scripts at all

Go はいろんな用途で使える。
Webサーバ、プロセスマネジメントなど

###### What makes Go good for scripts?
*
シンプルでリーダブル。冗長すぎない →メンテ簡単で、比較的短い
*
ライブラリが多いから、スクリプトが短くて堅牢にできる
*
コードで　GO を使っていたら、スクリプトでも GO を使うと便利じゃね？


## Go is 99% There Already
`my-script.go` というスクリプトを書く
 →`go run my-script.go` で実行


### bash や python と Go との違い
*
bash や python は interpreters。読みながらスクリプトを実行する

*
Go compiles the Go program, and then runs it.
コンパイル時間がすごく短いので、interpreted のように感じる

## 例)a simple script that has two interactions with the shell:
it gets an input from the command line, and sets the exit code.

The script writes “Hello”, and the first argument in the command line, and exits with the code 42:
```go
package main

import (
    "fmt"
    "os"
)

func main() {
    fmt.Println("Hello", os.Args[1])
    os.Exit(42)
}
```
上記 スクリプトを `go run example.go`すると下記のようになる
```bash
$ go run example.go world
Hello world
exit status 42
$ echo $?
1
```
同じスクリプトを `go build` してから実行する場合、下記のようになる
```bash
$ go build
$ ./example world
Hello world
$ echo $?
42
```
Current workflow with this script looks like this:
```bash
$ vim ./example.go
$ go build
$ ./example.go world
Hi world
$ vim ./example.go
$ go build
$ ./example.go world
Bye world
```
