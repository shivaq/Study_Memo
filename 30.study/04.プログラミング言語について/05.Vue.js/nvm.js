■■■■■■■■■■■■■■■■■■■■■■■■■■ なぜ NVM が必要なのか
Node.js は動きが早いので、バージョンを指定して実行したい、ということが発生する。


▼ 利用可能な Node.js バージョンを確認
nvm ls - remote

# chose desired node version
VERSION = "8.12.0"

▼ バージョン指定でインストール
// # install node
nvm install ${ VERSION }

▼ 利用するバージョンを指定
nvm use ${ VERSION }

▼ デフォルトバージョンを指定
nvm alias default ${ VERSION }

