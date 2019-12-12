
ベーシック 体に覚えさえろ
-------------------------------------------------
一文字ずつ移動
# Ctrl + B/F
一単語ずつ移動
# Alt + B/F
行頭/行末移動
# Ctrl + a/essh ubuntu@172.31.34.154 -i ansibleWS.pem
カーソルポジション移動後、前のポジションに戻る
# Ctrl + XX

左右の文字を削除
# Ctrl + D/H
カーソル右左単語削除
# Alt d
# Ctrl + Alt + h
カーソルポジション直前の単語をカット
# Ctrl + W
カーソルポジションから 最後まで/先頭までのテキストをカット
# Ctr + u/k

ペースト
# Ctrl + Y

文字の大文字小文字化
# Alt + u/l

Ctrl 使って Enter と同じ効果
# Ctrl + j
# Ctrl + m

Ctrl 使って ヒストリ↕
# Ctrl + p
# Ctrl + n

前にいたディレクトリに移動
# cd -
兄弟ディレクトリに移動
# cd ../Play

ファイルサイズでソート
# ls -S
ソートを逆転
# ls -r
作成時間でソート
# ls -t
-------------------------------------------------

ファイルの中身を新規ファイルに流し込む# コピーではない
# cat dd.txt >> test.txt
ファイルの作成と書き出しを同時に
# cat > inventory.txt
# target1 ansible_host=192.168.100.9 ansible_ssh_pass=password
# ^C
ls の内容を直接ファイルに書き込み
# ls -l > somefile.txt
ファイルの中身をgrepして、別ファイルに流し込み
# cat somefile.txt | grep keyword > filefound.list
これをあそこへコピー
# cp dd.txt /home/yasuaki/test_2018020
ファイル名の最後に文字列追加したコピー作成
#  cp /etc/rc.conf{,-old}
ファイル名の一部のみ変更
# mv tes{t,tttttttttttttttt}.txt
複数のフォルダを一度に作る
# mkdir myfolder{1,2,3}

curl でファイルのソースコードを表示
# curl google.com

URL を指定して その中身を名前をつけて保存
# curl -o -L dictionary.txt  https:#tinyurl.com/zeyq9vc
# このファイルを、このディレクトリから取得

ファイルの中身をグレップ
# grep she dictionary.txt

指定した単語の数を表示
# grep she dictionary.txt | wc -l

.tar アーカイブ作成
# mkdir arch
# touch arch/unk{1,2}
# tar -cvf tarファイル名 アーカイブ対象ディレクトリ
gzip 作成
# tar -czvf tgzファイル名 圧縮対象ディレクトリ
アーカイブされているファイルの一覧表示
# tar  -tf tarファイル名
圧縮してアーカイブされているファイルの一覧表示
# tar  -tzf tgzファイル名

tar を untar
# tar -xvf tarファイル名
gzip を展開
# tar  -xvzf tgzファイル名
特定のファイルのみ解凍して展開
# tar  -xvzf tgzファイル名 ファイルパス

コマンド履歴検索
Ctrl + r ⇒コマンドの一部を打つ
Ctrl + r を繰り返す ⇒更に検索してくれる
遡り過ぎたら Ctrl + c でリセット
これに Ctrl + a/e を組み合わせて、さらにコマンドを修正できる
-------------------------------------------------
 ログに使うコマンド
 # date, cal, w,

 ユーザー情報
 # uname -a
 ユーザーネーム表示
 # hostname
 IP アドレス表示
 # hostname -I
-------------------------------------------------
-------------------------------------------------
直前のラインを実行
# !!
直前の引数をコピー
# !$       cd !$ みたく使う

番号指定でヒストリコマンド実行
# !499

ファイルパスはドラッグ・アンド・ドロップで表示できる
#
