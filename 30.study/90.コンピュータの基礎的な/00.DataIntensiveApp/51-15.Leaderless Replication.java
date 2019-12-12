■■■■■■■■■■■■■■■■■■■■■■■■■■ Leaderless Replication
▼ ここまで見てきたアプローチの前提
Single-leader and multi-leader replication は、ともに下記アイデアに立脚している
・クライアントが書き込みリクエストをリーダーに送る
 →リーダーのDBシステムが、その書き込みを他のレプリカにコピーする
・一つのリーダーが、どの書き込みがどの順番で処理されるかを判断し、
 →フォロワーはリーダーの書き込みと同じ順番を適用する

▼ リーダーという概念を撤廃
・データスシステムによっては、こうしたアプローチが容易されている
・どのレプリカも、クライアントから直接書き込みを受け付ける。
・最初期のレプリケートデータシステムたちは、リーダーレスだった
しかしRDB時代がやってきて、リーダーレスのアイデアは忘れられていった。

▼ リーダーレス時代が再発見された
流行のDBアーキテクチャ。
AWS が Dynamo システムで使用
・Riak, Cassandra, and Voldemort がそれに続いた
 →結果、Dynamo スタイルとも呼ばれるようになっている
※ Dynamo システムは、DynamoDB は使っていない。DynamoDBはシングルリーダー。

■■■■■■■■■■■■■■■■■■■■■■■■■■ リーダーレスの実装
▼ クライアントが直接書き込みを複数のレプリカに送るパターン

▼ コーディネーターノードが、クライアントに代わって書き込みを送るパターン
・コーディネーターは、
リーダーアーキテクチャと違って、
書き込みの順番を強制しない
結果、
DBの使われ方が大きく異なる
