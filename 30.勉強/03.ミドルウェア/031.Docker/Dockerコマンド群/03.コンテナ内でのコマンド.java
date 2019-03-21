■■■■■■■■■■■■■■■■■■■■■■■■■■ コンテナ内で追加のプロセスを 動かす
■■■■■■■■■■■■■■■■■■■■■■■■■■ docker exec
-------------------------------------------------
▼ バックグラウンドで Fileを作成
docker exec -d daemon_dave touch /etc/new_config_file

▼ exec と -it と /bin/bash でインタラクティブモード
// 違いは、対象コンテナを指定しているところか？
// 新規 bash セッションが生成される
docker exec -t -i daemon_dave /bin/bash




■■■■■■■■■■■■■■■■■■■■■■■■■■ いつものコンテナとのインタラクティブやりとり
docker run -it --rm ubuntu /bin/bash
▼ ホスト名確認
hostname
▼ ネットワーク情報確認
hostname -I

▼ プロセス確認
ps -aux
