▼ エラー出力
START RequestId: a821457b-ede0-11e8-ad14-158839d38fa8 Version: $LATEST
module initialization error: [Errno 13] Permission denied: '/var/task/CreateThumbnail.py'
-------------------------------------------------
▼ エラーの原因
Lambda に使う python を作成する際、サーバ上で、ライブラリごと zip に圧縮している。
その際、ファイル権限がきついため、Lambdaが読み込めない

▼ 解決策
ファイル権限を明示的に 777 に設定する
-------------------------------------------------
zinfo = zipfile.ZipInfo(file_name)
zinfo.external_attr = 0o777 << 16  # give full access to included file
ziph.writestr(zinfo, my_data)
-------------------------------------------------
