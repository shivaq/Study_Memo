■■■■■■■■■■■■■■■■■■■■■■■■■■ デーモン化したコンテナ
▼ 備考
・動きつづけているアプリやサービスに最適。
・インタラクティブなセッションは持たない

■■■■■■■■■■■■■■■■■■■■■■■■■■ コマンド
-d
// コンテナをバックグラウンドにデタッチ

例
▼ コンテナが止まるか、プロセスが止まるまで while loop が続く
// docker run --name daemon_dave -d ubuntu /bin/sh -c "while true; do echo hello world; sleep 1; done"
