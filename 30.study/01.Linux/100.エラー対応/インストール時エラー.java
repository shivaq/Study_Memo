flask 1.0.2 has requirement Jinja2>=2.10, but you'll have jinja2 2.7.2 which is incompatible.

awscli 1.15.80 has requirement botocore==1.10.79, but you'll have botocore 1.12.67 which is incompatible.

Cannot uninstall 'requests'. It is a distutils installed project and thus we cannot accurately determine which files belong to it which would lead to only a partial uninstall.

バージョン等確認
-------------------------------------------------
rpm -qa
yum list
pip list
pip show
-------------------------------------------------


■■■■■■■■■■■■■■■■■■■■■■■■■■ yum と rpm との違い
yum はパッケージマネージャ

rpm は実際のパッケージ

yum を使うと、 rpm を使ってソフトウェアのインストール、削除が行われる
-------------------------------------------------


■■■■■■■■■■■■■■■■■■■■■■■■■■ アップデートがスキップされる
// 文言
packages excluded due to repository priority protections
-------------------------------------------------
▼ どういう意味か
・priority によってアップデートがなされない。


▼ どう対応すればいいか
・priority 無効化で対応可能

/etc/yum/pluginconf.d/priorities.conf
// 上記ファイルに下記を設定
enabled = 0
-------------------------------------------------
