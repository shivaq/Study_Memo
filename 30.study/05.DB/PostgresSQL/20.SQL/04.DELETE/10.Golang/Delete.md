# 削除





```go
func (post *Post) Delete() (err error) {





// Exec -> query without returning any rows
// You don't need to process returned result.
// So assign them to _.
	_, err = Db.Exec("delete from posts where id = $1", post.Id)
	return
}
```
