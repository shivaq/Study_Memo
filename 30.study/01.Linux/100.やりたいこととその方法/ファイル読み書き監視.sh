■■■■■■■■■■■■■■■■■■■■■■■■■■ inotify とは
ディスクへの読み書きイベントを通知する機能

▼ インストール
yum install inotify-tools

▼ 起動
# /var/www/html/以下で
# ”/admin/”を含むディレクトリ以外で
# ディレクトリ・ファイルアクセスが発生したら
# 日時とともにログファイルへ保存する
# 詳細は --help
inotifywait -rdm -o /var/log/inotify.log --format '%T %w%f %e' --timefmt '%F %T' --exclude '/|admin\/' /var/www/html/ &

▼ ログ出力
2017-09-dd hh:mm:ss /var/www/html/ CREATE test.txt
2017-09-dd hh:mm:ss /var/www/html/ MODIFY test.txt
2017-09-dd hh:mm:ss /var/www/html/ DELETE test.txt

▼ あとは、ログを監視する
