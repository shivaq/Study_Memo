# dep とは
- Go の 依存関係管理ツール
[dep ドキュメント](https://golang.github.io/dep/docs/introduction.html)

## vendor ディレクトリの取り扱い

* vendor ディレクトリは gitignore などでコミットされていない可能性がある
* よって、dep で dependencies を取得しておく





## vendor が .gitignore にあるならば、 .dockerignore にも入れておく
