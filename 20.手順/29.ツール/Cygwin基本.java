■■■■■■■■■■■■■■■■■■■■■■■■■■ 見た目設定

-------------------------------------------------
・vi でバックスペースが聞かない場合
「~/.vimrc」に「set bs=2」を追記する
-------------------------------------------------
・見た目カスタマイズ
 /home/username/.minttyrc を作成し、設定を追記する
 ※ .minttyrc を参照
-------------------------------------------------

▼ Select Package でインストールするアプリ
-------------------------------------------------
gcc-core
* libncurses-devel
* make
* python3
* python3-pygments
* vim
* wget
* git
openssh  → Net で、チェックボックス2つともチェック
tmux
-------------------------------------------------



▼ apt-cyg
-------------------------------------------------
$ wget https://raw.githubusercontent.com/transcode-open/apt-cyg/master/apt-cyg
$ chmod 755 apt-cyg
$ mv apt-cyg /usr/local/bin/


パッケージを検索
$ apt-cyg searchall <package_name>
インストール済みのパッケージを検索
$ apt-cyg search <package_name>
パッケージをインストール
$ apt-cyg install <package_name>
-------------------------------------------------




その他 下記を参照したい
http://hiroki-sawano.hatenablog.com/entry/2018/02/20/014239
