// インデックスのすべてのファイルを取り消す
git reset HEAD .
または
git reset .

// git add したファイル（index.php）をインデックスから削除
$ git reset index.php
または
$ git reset HEAD index.php

// ディレクトリ(images)を指定して、配下のファイル群の git add を取り消し
$ git reset /images/
$ git reset HEAD /images/
