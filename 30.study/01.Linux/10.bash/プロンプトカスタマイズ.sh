■■■■■■■■■■■■■■■■■■■■■■■■■■ 変更の更新方法
. ~/.bashrc

■■■■■■■■■■■■■■■■■■■■■■■■■■ mac の hostname は変更可能


▼ git の branch を表示
-------------------------------------------------
source /usr/local/etc/bash_completion.d/git-prompt.sh
source /usr/local/etc/bash_completion.d/git-completion.bash
GIT_PS1_SHOWDIRTYSTATE=1
GIT_PS1_SHOWUNTRACKEDFILES=1
GIT_PS1_SHOWCOLORHINTS=true
GIT_PS1_SHOWSTASHSTATE=true

export PS1="\[\e[0m\]\u@\[\e[32m\]\h \[\e[33m\]\W\[\e[0m\] \[\e[31m\]$(__git_ps1 "[%s]")\[\e[0m\]\$ "
-------------------------------------------------


■■■■■■■■■■■■■■■■■■■■■■■■■■ 解説
▼ 下記はすべてエスケープを表す
\e
ESC
^[
033
0x1b
https://en.wikipedia.org/wiki/ASCII

▼ PS1
The Primary prompt string

▼ $TERM
ユーザが利用している端末の種類を格納している。 echo $TERM で確認可能

▼ ANSI escape sequences
ターミナル上のカーソルの位置や色などをコントロールするためのもの。
\[ で始まり、] で終わる。

▼ 特殊文字
\u	ユーザ名
\h	ホスト名
\W	カレントディレクトリ
\w	カレントディレクトリ(フルパス)
$	'$'という文字(一般ユーザ)。rootユーザの場合は'#'。UIDが0かそうでないか。



■■■■■■■■■■■■■■■■■■■■■■■■■■ グラフィックモードをセット
Esc[値;...;値mn

▼ Text attributes
0	All attributes off
1	Bold on
4	Underscore (on monochrome display adapter only)
5	Blink on
7	Reverse video on
8	Concealed on

▼ Foreground colors
30	Black
31	Red
32	Green
33	Yellow
34	Blue
35	Magenta
36	Cyan
37	White