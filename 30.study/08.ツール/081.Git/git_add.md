
## Add したものの 取り消し
# インデックスのすべてのファイルを取り消す
```bash
git reset HEAD .
# または
git reset .
```

# git add したファイル（index.php）をインデックスから削除
```bash
$ git reset index.php
# または
$ git reset HEAD index.php
```


# ディレクトリ(images)を指定して、配下のファイル群の git add を取り消し
```bash
$ git reset /images/
$ git reset HEAD /images/
```






## 部分的 add

```bash
git add -p
```

* 追加ブロック分割 s
* 追加ブロック細かく分割 e
* + 部分を排除するには、その行を削除
* - 部分を排除するには。。。？


## インデックスの差分を確認
```bash
git diff --cached
```
