# new(T) メモリ割り当てをする。
* 新しく割り当てられたT型のゼロ値のポインタを返す
* 初期化不要ですぐ使える。






### Name なし変数を生成する関数。その名も new。あまり使われない。
* 代わりに struct の方が柔軟だからそっちが使われる
* new(T)
* T 型のName なし変数 を作成する。
* T のゼロバリュー で初期化され、ポインタを返す。
* その値は `*T`




```go
p := new(int)   // p, of type *int, points to an unnamed int variable
fmt.Println(*p) // "0"
*p = 2          // sets the unnamed int to 2
fmt.Println(*p) // "2"
```

```go
func newInt() *int {
    return new(int)
}

// 上と下とは同じ働き

func newInt() *int {
    var dummy int
    return &dummy
}
```
