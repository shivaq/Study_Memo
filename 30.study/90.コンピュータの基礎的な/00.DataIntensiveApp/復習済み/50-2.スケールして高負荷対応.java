■■■■■■■■■■■■■■■■■■■■■■■■■■ Scaling to Higher Load
▼ shared-memory architecture
高負荷対応だけが気がかりならばスケールアップすればいい。
一つのOSの下で、CPU や RAM やたくさんのディスクを積んだマシンを使う。
一つのマシン内だから、交信も早い。

▼ shared-memory architecture の問題点
・CPUにしろRAMにしろディスク容量にしろ、2倍のものを搭載するには2倍以上のコストがかかる。
どこかにボトルネックがあれば、
マシン性能が二倍になっても、二倍の負荷に耐えられるわけではない。

・fault tolerance に限界がある。
ホットスワップができたりすることが多いが、地理的に単一になるのは避けられない。


▼ shared-disk architecture
複数のマシンが、ネットワークファイルシステムを共有している形。
データウェアハウスでよく使われる
しかし
but contention and the overhead of locking が
このアプローチのスケーラビリティの制約となっている。



■■■■■■■■■■■■■■■■■■■■■■■■■■ Shared-Nothing Architectures
▼ shared-nothing architectures
・スケールアウト
・データベースソフトウェアを走らせている各マシーンやVMはノードと呼ばれる。
・各ノードは固有のCPU、RAM、Disk を持つ
・ノード間の連携は、従来のネットワークを使ったソフトウェアレベルで行われる。

・特別なHWは不要で、コストやパフォーマンスの兼ね合いで自由にマシンを使用できる。
・複数のリージョン間でデータを分散できるため、ユーザーのレイテンシーを下げられるし、
データセンターの障害を生き抜くこともできる。

・小さな会社でも、マルチリージョンアーキテクチャは feasible

・shared-nothing architectures に焦点を当てるのは、
あらゆるユースケースにとってベストだから、ではなく、
アプリケーションデベロッパーとして注意すべき点が多数あるから。

・データを複数のノード間に分散させる場合の制約やトレードオフを考慮にいれなければならない。
・shared-nothing architectures には多数アドバンテージがあるが、
アプリは複雑になるし、使えるデータモデルにも制限がある。
・場合によっては、シングルスレッドのプログラムのほうが、100CPU のクラスタ構成よりもずっとよいパフォーマンスが出せたりする。
・それでも、shared-nothing architectures はパワフルだ。
