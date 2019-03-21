#!/bin/bash

変数を定義
# X="変数"
# 値にスペースがければ、””しなくても大丈夫
# echo $X

echo を改行させない
# echo -n '$USER=' # -n option stops echo from breaking the line
# echo "$USER"
# 上記、''で囲っているため、変数の値ではなく、$サイン付きの文字列として出力される

変数の値が スペースを含んでいる もしくは 空文字 の場合の注意点
# 変数名を""で囲う →そうしないと、スペース含んで辺り、空文字だったりと認識しない
# X=""
# if [ -n "$X" ]; then 	# -n tests to see if the argument is non empty
# 	echo "the variable X is not the empty string"
# fi

変数内の文字列と、String とをスペースなしでつなげたい場合
# X=ABC
# echo "${X}abc"

オペランドとオペレーターの間にスペースがない、、、どういう意味だ？
# オペランドは変数や値。
# 1=2 は一塊のオペランドと見なされて、演算子は認識されない。
# そして、[] 内は常に true/false のどちらかが返る。
# 下記の場合は、true が返る
# if [ 1=2 ]; then
# 	echo "hello"
# fi

条件式
# if condition
# then
# 	statement1
# 	statement2
# 	..........
# else
# 	statement3
# fi
# -------------------------------------------------
# if condition1
# then
# 	statement1
# 	statement2
# 	..........
# elif condition2
# then
# 	statement3
# 	statement4
# 	........
# elif condition3
# then
# 	statement5
# 	statement6
# 	........
# fi

複雑条件式 →要反復練習
#!/bin/bash
# X=3
# Y=4
# empty_string=""
# if [ $X -lt $Y ]	# is $X less than $Y ?
# then
# 	echo "\$X=${X}, which is smaller than \$Y=${Y}"
# fi
#
# if [ -n "$empty_string" ]; then
# 	echo "empty string is non_empty"
# fi
#
# if [ -e "${HOME}/.fvwmrc" ]; then 			# test to see if ~/.fvwmrc exists
# 	echo "you have a .fvwmrc file"
# 	if [ -L "${HOME}/.fvwmrc" ]; then 		# is it a symlink ?
# 		echo "it's a symbolic link
# 	elif [ -f "${HOME}/.fvwmrc" ]; then 	# is it a regular file ?
# 		echo "it's a regular file"
# 	fi
# else
# 	echo "you have no .fvwmrc file"
# fi

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

数式を計算
# expr が必要
# X=`expr 3 + 4`
# echo $X


コマンド substitution
# files="$(ls)" →ls の出力を files 変数に格納
# web_files=`ls public_html` →ls public_html の出力を以下同文
# echo "$files" →"" で囲んでないと、スペースが出力される

解読せよ
# files="$(ls)"
# web_files=`ls public_html`
# echo "$files"      # we need the quotes to preserve embedded newlines in $files
# echo "$web_files"  # we need the quotes to preserve newlines
# X=`expr 3 \* 2 + 4` # expr evaluate arithmatic expressions. man expr for details.
# echo "$X"



環境変数を表示
# export -p

環境変数をセット
# export TEST="hoge"
# export -p |grep TEST
環境変数を削除
# export -n TEST
# export -p |grep TEST
