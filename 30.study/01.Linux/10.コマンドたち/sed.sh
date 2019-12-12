
▼ sed
-------------------------------------------------
s は substitute の s
# echo Sunday | sed 's/day/night/'
#  →Sunnight
空文字に置換
# echo 192.168.56.2/24 | sed 's/\/.*$#'
-------------------------------------------------


▼ 各行のすべての文字列を置換
$sed 's/unix/linux/g' geekfile.txt

▼ 置換を実施した行のみを出力
# /p 置換が行われた行をもう一行出力して同じ行が2行出力される
# -n 同じ行二行出力を抑止する
$sed -n 's/unix/linux/p' geekfile.txt


■■■■■■■■■■■■■■■■■■■■■■■■■■ 位置指定置換
▼ 各行の1つ目の文字列を置換
$sed 's/unix/linux/' geekfile.txt

▼ 各行で何回目に出現する文字列を置換するかを指定
$sed 's/unix/linux/2' geekfile.txt

▼ 各行の何番目以降に出現する文字列をすべて置換
$sed 's/unix/linux/3g' geekfile.txt

▼ 指定した行のすべての文字列を置換
$sed '3 s/unix/linux/' geekfile.txt
▼ 指定した範囲の行の文字列をすべて置換
$sed '1,3 s/unix/linux/' geekfile.txt
$sed '2,$ s/unix/linux/' geekfile.txt

■■■■■■■■■■■■■■■■■■■■■■■■■■ 追加と削除

▼ 各単語の最初の文字を() で囲う
$ echo "Welcome To The Geek Stuff" | sed 's/\(\b[A-Z]\)/\(\1\)/g'

▼ 特定の行を削除する
Syntax:
$ sed 'nd' filename.txt
Example:
$ sed '5d' filename.txt
$ sed '$d' filename.txt
$ sed '12,$d' filename.txt

▼ パターンにマッチした行を削除する
Syntax:
$ sed '/pattern/d' filename.txt
Example:
$ sed '/abc/d' filename.txt

▼ # で始まる行を削除
sed '/^#/ d'

▼ # の文字からその行の最後までを削除 ＋ ^$ は、すべての空業を削除
sed -e 's/#.*//' -e '/^$/ d'
