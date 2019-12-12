▼ key/valur を格納する手作りデータベース
-------------------------------------------------
#!/bin/bash
    db_set () {
        echo "$1,$2" >> database
}
    db_get () {
        grep "^$1," database | sed -e "s/^$1,//" | tail -n 1
}
-------------------------------------------------
▼ 使い方
-------------------------------------------------
$ db_set 123456 '{"name":"London","attractions":["Big Ben","London Eye"]}'
$ db_set 42 '{"name":"San Francisco","attractions":["Golden Gate Bridge"]}'
$ db_get 42
{"name":"San Francisco","attractions":["Golden Gate Bridge"]}


$ cat database
123456,{"name":"London","attractions":["Big Ben","London Eye"]}
42,{"name":"San Francisco","attractions":["Golden Gate Bridge"]}
42,{"name":"San Francisco","attractions":["Exploratorium"]}
-------------------------------------------------


■■■■■■■■■■■■■■■■■■■■■■■■■■ log を使うということ
Similarly to what db_set does, many databases internally use a log, which is an append-only data file.

▼ この本における "log" が何を意味するか
アプリログ、などのログよりも、より一般的な意味合いで使う。
"an append-only sequence of records. "
人間に読みやすくなくったっていい。
バイナリで書かれていて、他のプログラムが読むことだけを意図して作られている。



■■■■■■■■■■■■■■■■■■■■■■■■■■ 手作りDBについて
db_set のパフォーマンスは極めていい。最後の行に、レコードを追記するだけ。
一方
db_get のパフォーマンスは、レコードが巨大になると、とたんにひどくなる。
キーを探すたびに、DB全体を始めから最後までスキャンしなければならない。

コストは O(n)
 →レコード数が二倍になれば、検索にかかる時間も二倍の長さになる。
