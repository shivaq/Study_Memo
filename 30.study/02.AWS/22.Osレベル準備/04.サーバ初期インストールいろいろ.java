■■■■■■■■■■■■■■■■■■■■■■■■■■ epel をインストール
▼ RHEL 7 および Amazon Linux 2 で EPEL rpmパッケージをインストールして有効にする
yum install -y https://dl.fedoraproject.org/pub/epel/epel-release-latest-7.noarch.rpm

▼ EPEL が有効であることを確認
yum repolist


▼ python3 をインストール
-------------------------------------------------
yum install python3
-------------------------------------------------


▼ ca-certificate パッケージをアップデート
-------------------------------------------------
それってなに？
 →サードパーティのIDを検証するためのデジタル証明書。
 これをつかうことで、通信を暗号化し、のぞき見できない。
・certificate authority (CA)
-------------------------------------------------
yum --disablerepo=epel update ca-certificates
yum update
-------------------------------------------------


▼ Python 開発ツール群をインストール
-------------------------------------------------
yum groupinstall -y "Development Tools"
// 下記、EPEL を使用
yum install -y python-devel sshpass
-------------------------------------------------


■■■■■■■■■■■■■■■■■■■■■■■■■■ pip をインストール
▼ 下記で問題なさそう
yum install -y python-pip
// ■■■■■■■■■■■■■■■■■■■■■■■■■■ pip など python 系環境
// ▼ pip をインストール後、pip でインストール
// -------------------------------------------------
// // インストールスクリプトダウンロード
// curl -O https://bootstrap.pypa.io/get-pip.py
// // スクリプトを実行
// python get-pip.py --user
// // PATH に実行可能パス ~/.local/bin を追加
// 参照：
// // C:\Users\Yasuaki\Dropbox\01.study\MyAws\10.設計\11.詳細設計\13.起動時読み込みファイル\bash_profileに追加.sh
//
// // 現在のセッションにプロファイルをロード
// source ~/.bash_profile
// // pip が正しくインストールされたことを確認
// pip --version
//
// // 詳しくは、下記公式ドキュメント参照
// // https://docs.aws.amazon.com/ja_jp/cli/latest/userguide/awscli-install-linux.html
// -------------------------------------------------


▼ virtualenv インストール


■■■■■■■■■■■■■■■■■■■■■■■■■■ Docker
// C:\Users\Yasuaki\Dropbox\01.study\MyAws\20.手順\001.Docker\インストール.java


// ■■■■■■■■■■■■■■■■■■■■■■■■■■ git をインストール
// git --version
//
// sudo yum -y update
//
// sudo yum install git
//
// git config --global user.name "Yasuaki Shibata"
// git config --global user.email "shivaq777@gmail.com"
//
//
// git config --list
-------------------------------------------------
