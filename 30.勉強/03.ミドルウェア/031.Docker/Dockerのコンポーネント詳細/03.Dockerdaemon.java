・daemonプロセスは、root権限を持つ
・やってくるDockerリクエストを、下記パスの Unix ソケットで リッスンする
・/var/run/docker.sock
・docker というグループがシステム上に存在している場合、ソケットのオーナーはdocker グループに設定される
 →結果、docker グループに所属するユーザーは、 sudo なしでDockerを run できる
・コマンドを実行する。ホストマシーンの上で走る。
・Docker Client は 異なるマシンから、Docker Daemon と Communicate することができる。
