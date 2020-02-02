# 行を返す


```go
func GetPosts(limit int) (posts []Post, err error) {
	// Rows interface is an iterator
	rows, err := Db.Query("select id, content, author from posts limit $1", limit)
	// if err != nil {
	// 	return
	// }
  //
	// // iterate until it runs out of rows, when it returns io.EOF
	// for rows.Next() {
	// 	post := Post{}
	// 	// Scan copies the columns from the matched row into the values pointed at by dest.
	// 	err = rows.Scan(&post.Id, &post.Content, &post.Author)
	// 	if err != nil {
	// 		return
	// 	}
	// 	posts = append(posts, post)
	// }
	// rows.Close()
	return
}
```

-------------------------------------------------






# 読み取った row の column を OBJ に格納



```go
post := Post{}


// Scan は、row から column を読み取って、それを引数に格納する
err = rows.Scan(&post.Id, &post.Content, &post.Author)
```
-------------------------------------------------




# 一行のみを読み込む


```go

func GetPost(id int) (post Post, err error) {
	post = Post{}






  // QueryRow は、一行だけ読み込んで、もしほかのレコードもヒットしても、そいつらは廃棄する
	err = Db.QueryRow("select id, content, author from posts where id = $1", id).Scan(&post.Id, &post.Content, &post.Author)
	return
}
```


-------------------------------------------------
