■■■■■■■■■■■■■■■■■■■■■■■■■■ EACCES permissions errors 
▼ 解決策1：(推奨)
再インストール npm with a node version manager

▼ 解決策2：
npm のデフォルトディレクトリを手動で変える

// -------------------------------------------------
// ▼ ~/.npm-global
// ・Linuxでは、グローバルインストールしたときにパーミッションエラーが起こる場合がある
// ・sudo で解決できることもあるが、あまり推奨できない。
// ・より良い回避策は、npmのデフォルトディレクトリを自分の権限があるディレクトリに変えること
$ mkdir ~/.npm-global

// npm が 新しいディレクトリのパスを使うよう設定
$ npm config set prefix '~/.npm-global'
// 
$ echo ' export PATH=~/.npm-global/bin:$PATH' >> ~/.bash_profile
$ source ~/.bash_profile


▼ npm config set prefix '~/.npm-global'
Configure npm to use the new directory path: