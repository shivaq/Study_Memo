■■■■■■■■■■■■■■■■■■■■■■■■■■ Leaders and Followers
▼ レプリカ
DBののコピーを保持する各ノードのことをレプリカと呼ぶ
・複数のレプリカがある場合、
どうやってすべてのデータが、すべてのレプリカに格納されていると保証するのだ？

▼ leader-based replication (also known as active/passive or master–slave replication)
1. One of the replicas is designated the leader (also known as master or primary)
クライアントがDBに書き込みを試みる
 →leader にリクエストが送られる
  →leader は新しいデータをローカルストレージに書き込む

2. The other replicas are known as followers (read replicas, slaves, secondaries, or hot standbys)
・leader が書き込むたびに、データの変更がすべてのフォロワーに送られる
どのような形式で送られるか。
レプリケーションログの一部として
または
チェンジ ストリームとして

各フォロワーがリーダーからのログを受け取る
 →自身のローカルコピーDBを更新する
 この時、すべての書き込みを、リーダーが処理したのと同じ順番で更新する

3. クライアントがDBから読み込みたいときは、リーダーかフォロワーのいずれかにクエリがなされる。
しかし、書き込みは
リーダーのみが受け付ける。

-------------------------------------------------
・上記モードのレプリケーションは、多くのRDB や一部の NonRDB が採用している
including MongoDB, RethinkDB, and Espresso
・分散メッセージブローカーも採用している
Kafka [5] and RabbitMQ
・ネットワークファイルシステムや replicated block devices such as DRBD も対応している
