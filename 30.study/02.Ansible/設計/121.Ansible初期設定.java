※ 20.手順 > 21.端末側準備 > 212.インストールいろいろ
上記を参考に

■■■■■■■■■■■■■■■■■■■■■■■■■■  ca-certificate パッケージをアップデート

■■■■■■■■■■■■■■■■■■■■■■■■■■ pip など python 系環境
▼ pip をインストール


▼ python3 をインストール

▼ Python 開発ツール群をインストール

▼ virtualenv インストール

▼ git をインストール

■■■■■■■■■■■■■■■■■■■■■■■■■■ Python 仮想環境構築
※ 20.手順 > 24.python系 > virtualenv.java
上記を参考に

▼ virtualenv 内の python バージョン指定(2系か 3系か)

▼ virtualenv の有効化

▼ virtualenv の無効化




■■■■■■■■■■■■■■■■■■■■■■■■■■ Ansible の初期設定
▼ virtualenv へ ansible インストール
pip install ansible
ansible --version



▼ ansible.cfg の作成
-------------------------------------------------
1.~/.ansible.cfg に作成
02.Ansible/home/ec2-user/ansible.cfg.sh
-------------------------------------------------

■■■■■■■■■■■■■■■■■■■■■■■■■■ Playbook 等のディレクトリ構造を作成
▼ ansible の各ファイルを get repository 化
playbooks
上記で、「git init」

▼ inventory ファイルを作成
playbooks > inventories> staging.ini を作成

// 参照:https://docs.ansible.com/ansible/latest/user_guide/intro_inventory.html

▼ 各ロールやvarsディレクトリ等を展開
playbooks > group_vars > all
playbooks > host_vars
playbooks > inventories
playbooks > roles > common > files
playbooks > roles > common > locale
playbooks > roles > common > packages
playbooks > roles > common >
