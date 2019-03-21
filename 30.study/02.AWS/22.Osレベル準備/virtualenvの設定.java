▼ 前提条件
・virtualenv をインストールしておく
参照:
// C:\Users\Yasuaki\Dropbox\01.study\MyAws\20.手順\21.端末側準備\212.インストールいろいろ.java


▼ 仮想環境作成
・環境作成する場所に cd
virtualenv name_of_the_virtualenv




▼ virtualenv 内の python バージョン指定(2系か 3系か)をして作成
virtualenv --python=python name_of_the_virtualenv
virtualenv --python=python3 name_of_the_virtualenv

▼ virtualenv の有効化
source name_of_the_virtualenv/bin/activate

▼ virtualenv の無効化
deactivate

▼ 仮想環境の削除
rm -rf name_of_the_virtualenv/
