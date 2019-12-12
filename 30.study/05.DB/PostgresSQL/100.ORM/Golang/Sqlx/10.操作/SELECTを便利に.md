

```go
func GetPost(id int) (post Post, err error) {
	post = Post{}



  // StructScan で Scan がシンプルになる
  // err = Db.QueryRow("select id, content, author from posts where id = $1", id).Scan(&post.Id, &post.Content, &post.Author)
	err = Db.QueryRowx("select id, content, author from posts where id = $1", id).StructScan(&post)












	if err != nil {
		return
	}
	return
}
```
