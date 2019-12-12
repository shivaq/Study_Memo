


# Insert を並列実行





```go
func (post *Post) Create() (err error) {
	statement := "insert into posts (content, author) values ($1, $2) returning id"







  // Prepare //
  // あとで実行、クエリするためのステートメントを準備しておく
  // 複数のクエリ や実行 を並列実行できる
	stmt, err := Db.Prepare(statement)
	if err != nil {
		return
	}


  // クローズ必要
	defer stmt.Close()



  // Stmt のメソッド
  // prepare で用意されたステートメントを、引数と一緒に実行する
	err = stmt.QueryRow(post.Content, post.Author).Scan(&post.Id)
	fmt.Println(err)
	return
}
```
-------------------------------------------------
