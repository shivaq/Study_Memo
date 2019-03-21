▼ pip
-------------------------------------------------
// インストールスクリプトをダウンロード
curl -O https://bootstrap.pypa.io/get-pip.py
python get-pip.py --user
// PATH 変数に実行可能パスを追加
// 下記はbash_prifile に追加される
export PATH=~/.local/bin:$PATH
-------------------------------------------------
▼ python3
yum install python3
-------------------------------------------------



▼ ca-certificate パッケージをアップデート
-------------------------------------------------
yum --disablerepo=epel update ca-certificates
yum update
-------------------------------------------------
