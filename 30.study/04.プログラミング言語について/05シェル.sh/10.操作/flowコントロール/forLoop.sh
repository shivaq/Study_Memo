#!/bin/bash

https://qiita.com/zayarwinttun/items/0dae4cb66d8f4bd2a337

▼ はまった
-------------------------------------------------
・スペース問題
# if[$even = 1] →if [ $even = 1 ] それぞれスペースがないとエラー
-------------------------------------------------


▼ １ から ９９ の間の奇数を出力
-------------------------------------------------
for x in {1..99..2}
do
  echo $x
done
-------------------------------------------------

#!/bin/bash
for X in {1..99}
do
  even=$(expr $X % 2)
  # even=`expr $X % 2`
  if [ $even = 1 ]
  then
    echo $X
  fi
done
-------------------------------------------------

seq 1 2 99
-------------------------------------------------





ループ
例1)
# for X in red green blue
# do
# 	echo $X
# done
例2)
# colour1="red"
# colour2="light blue"
# colour3="dark green"
# for X in "$colour1" $colour2" $colour3"
# do
# 	echo $X
# done

例3)
# 1 から 99 まで 2 ステップずつ
# for number in {1..99..2}
# do
#     echo $number
# done

# seq 1 2 99

# for NUM in {1..99}
# do
#     evenNUM=$(expr $NUM % 2)
#     if [ $evenNUM = 1 ]
#     then
#         echo $NUM
#     fi
# done

現在のファイル内のすべての *.html をグレップして。。。
# for X in *.html
# do
# 		grep -L '<UL>' "$X"
# done

while ループ
# X=0
# while [ $X -le 20 ]
# do
# 	echo $X
# 	X=$((X+1))
# done
