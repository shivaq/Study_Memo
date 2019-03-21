■■■■■■■■■■■■■■■■■■■■■■■■■■ Amazon Linux の場合
// カーネルのバージョンが 3.1.0 以降であることを確認
uname -a


sudo yum update

sudo yum install docker

sudo systemctl status docker
sudo systemctl start docker

// 自動起動有効化
systemctl enable docker

// ユーザーアカウント情報修正 -a グループにユーザーを追加 -G グループ指定
sudo usermod -a -G docker ec2-user

// ログアウト →再ログイン後、sudo なしで Docker コマンド実行可能であることを確認
docker info
