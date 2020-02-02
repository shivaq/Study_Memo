# 1 To N 関係の struct があるとする


* Post 1 に対して複数の Comment



```go
type Post struct {
	Id       int
	Content  string
	Author   string
  // a pointer to a slice
	Comments []Comment
}

type Comment struct {
	Id      int
	Content string
	Author  string
  // a pointer to a Post
	Post    *Post
}
```






# 1 の方のテーブルのレコードで、N の方のレコードとの関係を確立する瞬間

* 1 のレコードを READ する時に確立させる

```go
func GetPost(id int) (post Post, err error) {
	post = Post{}

	// 空の Slice を用意
	post.Comments = []Comment{}

	// Read 対象の post を取得
	err = Db.QueryRow("select id, content, author from posts where id = $1", id).Scan(&post.Id, &post.Content, &post.Author)

	// 該当Post の Comment を取得
	rows, err := Db.Query("select id, content, author from comments where post_id = $1", id)
	if err != nil {
		return
	}

	// 取得したレコードを for loop
	for rows.Next() {
		// Post フィールドが、Select 対象の post である Comment インスタンスを作成
		comment := Comment{Post: &post}

		// 現在 loop 対象である Comment の各 カラムを 該当 Comment インスタンスに格納
		err = rows.Scan(&comment.Id, &comment.Content, &comment.Author)
		if err != nil {
			return
		}
		// select 対象の post の Comment 配列フィールドに、select 対象の Comment を append
		post.Comments = append(post.Comments, comment)
	}
	rows.Close()
	return
}
```
-------------------------------------------------











# 1 の方を Insert する場合



* まずは struct を生成しておいて、インサート

```go
post := Post{Content: "Hello, World!", Author: "横山ノック"}
post.Create()
```





* Post struct には、最初は Comment はない
*  →Many である Comment は Insert していない

```go
func (post *Post) Create() (err error) {




	err = Db.QueryRow("insert into posts (content, author) values ($1, $2) returning id", post.Content, post.Author).Scan(&post.Id)
	fmt.Println(err)
	return
}
}
```
 -------------------------------------------------











# N の方を Insert する場合



* まずは struct を生成しておいて、インサート

```go
comment := Comment{Content: "Good post!", Author: "椎名林檎", Post: &post}
comment.Create()
```




* Post に対する Comment という関係性のため、Comment struct に Post がないとエラー。

```go
func (comment *Comment) Create() (err error) {
	if comment.Post == nil {
		err = errors.New("Post not found")
		return
	}


	err = Db.QueryRow("insert into comments (content, author, post_id) values($1, $2, $3) returning id", comment.Content, comment.Author, comment.Post.Id).Scan(&comment.Id)
	return
}
```
-------------------------------------------------
