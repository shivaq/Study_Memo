■■■■■■■■■■■■■■■■■■■■■■■■■■ VOLUME について
使いみち
・ソースコードの追加
・データベース
・その他、ImageをコミットすることなくImageに反映できるコンテンツ

備考
・Union File System をバイパスする
・コンテナ間で共有、再利用が可能
・コンテナが動いていなくても、ボリュームを共有可能
・ボリュームへの変更は直接行える
・ボリュームへの変更は、Image アップデートに影響しない


-------------------------------------------------

■■■■■■■■■■■■■■■■■■■■■■■■■■ 使い方
▼ ボリュームを作る
-------------------------------------------------
・Dockerfile で指定

・コマンドで作成
// redis のコンテナが止まっても、値が保持されるように
// コンテナはイミュータブルなので
docker volume create web2_redis

▼ ボリューム確認
docker volume ls

▼ 作ったボリュームを利用する
// -v web2_redis:/data
// /data は、redis Docker Image が、コンテナ起動時にデフォルトでデータを探す場所
docker container run -itd -p 6379:6379 --net mynetwork -v web2_redis:/data --rm --name redis redis:5.0-alpine


▼ ボリュームのマウントポイント確認
-------------------------------------------------
docker volume inspect web2_redis
[
    {
        "CreatedAt": "2019-01-20T22:46:23Z",
        "Driver": "local",
        "Labels": {},
        "Mountpoint": "/var/lib/docker/volumes/web2_redis/_data",
        "Name": "web2_redis",
        "Options": {},
        "Scope": "local"
    }
]
-------------------------------------------------
