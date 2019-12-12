
bash ファイル
# .bash_profile →Win,Macと、Linux のログイン時のみ適用
# .bashrc →Linux の、ずっと有効な定義ファイル

プロンプトを修飾する PS1 用 スクリプトを生成するサイト
# http://bashrcgenerator.com/
# PS1="\u@\[$(tput sgr0)\]\[\033[38;5;118m\]\h\[$(tput sgr0)\]\[\033[38;5;15m\]:\w\[$(tput sgr0)\]\[\033[38;5;118m\]\\$\[$(tput sgr0)\]\[\033[38;5;15m\] \[$(tput sgr0)\]"

エイリアスを設定
# vi ~/.bash_profile
# alias ll='ls -la'

Vim の設定ファイル
# ~/.vimrc
