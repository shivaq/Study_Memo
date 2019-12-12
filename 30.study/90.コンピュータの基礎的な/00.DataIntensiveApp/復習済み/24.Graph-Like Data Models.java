▼ 多対多 をうまく扱いたい
・一対多(ツリー構造データ)や、レコード間に関係性がない場合は、ドキュメントモデルが適切。
・シンプルな多対多であれば、リレーショナルモデルでもいい。
しかし、データ間のコネクションが複雑になっていったら、データをグラフとしてモデリングするのが自然ではないでしょうか。


▼ グラフの構成要素
vertices (also known as nodes or entities)
edges (also known as relationships or arcs).

▼ グラフとしてモデルできるデータたち例
・Social graphs
Vertices are people, and edges indicate which people know each other.

▼ The web graph
Vertices are web pages, and edges indicate HTML links to other pages.

▼ Road or rail networks
Vertices are junctions, and edges represent the roads or railway lines between them.

▼ グラフを使っているアルゴリズム例
・カーナビが最短の経路を検索する時
・ページランクがWebGraphを利用して、Web ページの人気度を判断し、検索結果に反省する

■■■■■■■■■■■■■■■■■■■■■■■■■■ 単一のデータストアに、完全に異なるタイプのOBJを格納する、一貫した方法
・例にあげたグラフ内のノードはすべて同じ種類のモノを表している(people, web pages, or road junctions, respectively).
・単一のデータストアに、完全に異なるタイプのOBJを格納する、一貫した方法を提供している

例)Facebook
▼ vertices
people, locations, events, checkins, and comments made by users;

▼ edges
which people are friends with each other,
which checkin happened in which location,
who commented on which post,
who attended which event, and so on

▼ AWS Neptune がサポートするもの
グラフモデル：Property Graph と W3C の RDF
クエリ言語：Apache TinkerPop Gremlin と SPARQL
