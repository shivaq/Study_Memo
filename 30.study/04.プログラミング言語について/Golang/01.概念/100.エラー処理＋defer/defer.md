# defer とは
* 関数呼び出しをスタックにプッシュする
* defer のいるスコープの関数が終わったら、実行される
* それを呼び出した関数が終了する際に実行すべき処理を記述
* ペアとなるオペが行われるときによく使われる
* open close, connect disconnect, lock unlock
* リソースをリリースするのに使われる

## defer の使用箇所
* リソースが取得された直後

```go
func title(url string) error{
  resp, err := http.Get(url)
  if err != nil{
    return err
  }
  defer resp.Body.Close()
}
```
