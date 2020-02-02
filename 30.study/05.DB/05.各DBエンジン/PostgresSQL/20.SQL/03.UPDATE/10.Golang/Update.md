## 更新


```go
func (post *Post) Update() (err error) {
	// Exec -> query without returning any rows
	// 返り値である Result は、更新した行の数と、IDとを返すだけ
	_, err = Db.Exec("update posts set content = $2, author = $3 where id = $1", post.Id, post.Content, post.Author)
	return
```
