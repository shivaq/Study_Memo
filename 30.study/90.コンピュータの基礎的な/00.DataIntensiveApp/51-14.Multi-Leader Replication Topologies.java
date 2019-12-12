■■■■■■■■■■■■■■■■■■■■■■■■■■ Multi-Leader Replication Topologies
▼ レプリケーショントポロジー
・書き込みが、あるノードから別のノードにどのように伝播するかを表現するもの

・リーダーが2つだけの場合
取りうるトポロジーはひとつだけ。
リーダー1は全ての書き込みをリーダー2に送る。逆もしかり。
・リーダーが 3つ以上の場合、複数のトポロジーパターンをとりうる。

▼ all-to-all
・いちばん一般的
全てのリーダーは、その書き込みを他の全てのリーダーに送る

▼ circular topology
MySQLは、デフォルトではこいつだけをサポートしている。
・各ノードは、一つのノードからのみ書き込みを受け取る。
 →受け取った書き込みと、自身への書き込みを、別の一つの書き込みに送る
  →このリレーがリーダー同士で循環している

▼ star
・一つの指定されたルートノードが他の全てのノードに書き込み転送をする。
・ツリー構造でもある。

■■■■■■■■■■■■■■■■■■■■■■■■■■ circular and star topologies
書き込みは、全てのレプリカに到達する前に、いくつかのノードを通過することがある。
よって、
ノードたちは他のノードから受け取ったデータ変更を、転送する必要がある。

▼ 無限レプリケーションループをふせぐために
各ノードにはIDが付与されている。
レプリケーションログには、
各書き込みにタグが付いている。
どんなタグかって言うと、
その書き込みが通過した全てのノードのタグ
で、
自分のタグが付いたデータ変更を受け取ったノードは、
それを無視する。


■■■■■■■■■■■■■■■■■■■■■■■■■■ A problem with circular and star topologies
あるノードが fail したとする
結果、
他のノードたちのレプリケーションフローが妨げられる。
結果、
そのノードが復帰するまで更新できなくなったりする。

このときトポロジーを再設定することで、失敗したノードをワークアラウンドすることも可能だが、
ほとんどのデプロイでは、そういった再設定は手動で行われる。

▼ fault tolerance
に関しては、all-to-all のような、より密に接続されたトポロジーのほうがよい。
なぜか
メッセージの経路が複数取りうるから。
そうして、単一障害点を回避できる。

■■■■■■■■■■■■■■■■■■■■■■■■■■ all-to-all の問題点
▼ 因果が逆転問題
あるネットワークが、他のネットワークより早いことがある。
結果、
ある書き込みが二重で取得される可能性がある。
たとえば
A.クライアントA がリーダー1 のテーブルに行を挿入する
B.クライアントB がリーダー3 の上記行を更新する
だがその時
リーダー2 は違った順番で書き込みを受信していることだってありうる。
リーダー2は最初にBを受け取る。次にAを受け取る。
つまりは、
いまだそのDBに存在していない行に対する更新を先に受け取る。

・Consistent Prefix Reads と似た因果関係問題
▼ 解決策
・全てのノードは、挿入を先に処理して、次に更新を処理することを保証する

・全ての書き込みにタイムスタンプを付与する
 →不十分です！
なぜならば、
正しい順番でイベントが並んだ状態にするための同期処理に使うには、
clocks は信頼するに不十分だから。

・バージョンベクター
この方法は使える。詳細は別のチャプターで。

▼ しかし、
コンフリクト検知システムというものは、多くのマルチリーダーレプリケーションシステムで正しく実装されていない。
たとえば、
PostgreSQL BDR
 →書き込みの順番ちゃんとやるシステムが実装されていない
Tungsten Replicator for MySQL
 →コンフリクトの検知を試みてさえいない

■■■■■■■■■■■■■■■■■■■■■■■■■■ Trust no one1
マルチリーダーレプリケーションのシステムを使うときは、
ここまでに上げたイシューを認識しておいたほうがいい。
で、
ドキュメントをちゃんと読んで、
データベースを一通りテストして、
そのDBが、あなたが信頼している通りの「保証」を提供しているのかどうかを確認したほうがいい。
