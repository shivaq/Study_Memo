■■■■■■■■■■■■■■■■■■■■■■■■■■ トランザクション？？
かつて、DBへの書き込みは、商用取引(トランザクション)が起きる場で行われていた。
時を経て、DBが金銭のやり取りを伴わない領域でもつかわれるようになる。
 →トランザクションは、一連の読み書きの人まとまりとしてのロジカルな単位を指すようになった。

・トランザクションはACID (atomicity, consistency, isolation, and durability) properties を持たなくてもいい。
・トランザクション処理は、クライアントがレイテンシの低い読み書きを行わせることを意味する。
定期的に実施されるバッチ処理とは違って。


■■■■■■■■■■■■■■■■■■■■■■■■■■ 用途別データアクセスパターン：トランザクションとデータ分析
■■■■■■■■■■■■■■■■■■■■■■■■■■ online transaction processing (OLTP)
ブログやゲームなどなど、DB はユーザーのインプットに基づいてレコードの挿入、更新がおこなwれる。
これらアプリはインタラクティブなので、そのアクセスパターンはOLTPとして知られるようになった。

■■■■■■■■■■■■■■■■■■■■■■■■■■ online analytic processing (OLAP)
・DBが、トランザクションだけじゃなく、分析でも使われ始めた。
・データアナリシスは、トランザクションとはアクセスパターンが大きく異なる。
 →アナリティックスキャンは、大量のレコードをスキャンする
  →読み込むのは一レコードで少しのコラムだけ
   →で、calculates aggregate statistics (such as count, sum, or average)

例)
営業取引のテーブルが対象データだとする。その時のクエリは
・1月の、各店舗の総利益はいくらだ？
・前回の販促時、バナナの売上は通常と比べてどれくらいだった？
・Xブランドのおむつと一緒に買われたベビーフードは、何ブランドの物が多い？

▼ かつて、OLTP と OLAP は、同じDBが利用されていた
・やがて、分析は隔離されたDBに対して行われる様になった
この、隔離されたDBが、"Data Warehouse" と呼ばれる。


■■■■■■■■■■■■■■■■■■■■■■■■■■ Data Warehousing


▼ 企業が使うトランザクションシステム例
systems powering the customer-facing website,
controlling point of sale (checkout) systems in physical stores,
tracking inventory in warehouses,
planning routes for vehicles,
managing suppliers, administering employees, etc.

・上記、たくさんの人が自律的に取り組んでおり、レイテンシーも低いものが求められる。
それが、ビジネスにとってクリティカルだから。
 →だから、DB管理者は、分析にOLTP DBを使うのを嫌がる。負荷が高いから。

▼ Extract–Transform–Load (ETL) は何のために、どうやって？
・Data Warehouse は、隔離されたDB。
・あらゆる OLTP システムの、リードオンリーコピー
・OLTP DBからデータは抽出される ※ 定期dumpやストリームによる継続的更新によって
 →分析しやすいスキーマに整形され、Data ウェアハウスにロードされる

▼ 小さい企業ではどうなのだろう？
・大企業ではだいたい存在している。小さい企業ではあまりきかない。
 →OLTPシステム自体の数が少ないから？
  →普通のSQLDBのクエリで十分な程度データ量が小さいから？
   →エクセルとかで分析してるから？

   ▼ OLAP に最適化されている
   ・Data Warehouse は、analytic アクセスパターンに最適化できる
   ・OLTP でのインデックスアルゴリズムは、分析クエリに答えるのには向いていない



■■■■■■■■■■■■■■■■■■■■■■■■■■ OLTP DB と Data ウェアハウス
・Data ウェアハウスとOLTPデータベースは、ぱっと見似ている
・しかし、システム内部では、クエリパターンが異なるため、ずいぶん違っている。
・DBベンダは、OLTPとDataウェアハウス用のどちらかに焦点を当てたサービスを展開している。
・両方サポートするのは、Microsoft SQL と SAP HANA くらい。
・ParAccel は高い。Amazon RedShift は、ホストバージョンの ParAccel。
・最近はオープンソースの SQL-on-Hadoop プロジェクトがたくさん出てきて、商用 Dateウェアハウスシステムを越えようと頑張っている。
 Apache Hive, Spark SQL, Cloudera Impala, Facebook Presto, Apache Tajo, and Apache Drill
Some of them are based on ideas from Google’s Dremel
