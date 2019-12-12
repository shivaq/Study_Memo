■■■■■■■■■■■■■■■■■■■■■■■■■■ Property Graphs
・グラフストアは2つのRelationalテーブル(ノードとエッジ)によって構成されていると考えることができる。
・すべてのエッジには、ヘッドバーテクスと、テイルバーテクスとが格納されている。


▼ each vertex consists of:
• A unique identifier
• A set of outgoing edges
• A set of incoming edges
• A collection of properties (key-value pairs

▼ Each edge consists of:
• A unique identifier
• The vertex at which the edge starts (the tail vertex)
• The vertex at which the edge ends (the head vertex)
• A label to describe the kind of relationship between the two vertices
• A collection of properties (key-value pairs)




■■■■■■■■■■■■■■■■■■■■■■■■■■ 使用方法
例)
ある vertex の incoming や outgoing エッジがほしいときは、
エッジテーブルに対し、head_vertex or tail_vertex でクエリをかけられる




▼ Relational スキーマを使って Property Graph を表現する
-------------------------------------------------
CREATE TABLE vertices (
vertex_id integer PRIMARYKEY,
properties json
);
-------------------------------------------------
CREATE TABLE edges (
edge_id integer PRIMARY KEY,
tail_vertex integer REFERENCES vertices (vertex_id),
head_vertex integer REFERENCES vertices (vertex_id),
label text,
properties json
);
-------------------------------------------------
CREATE INDEX edges_tails ON edges (tail_vertex);
CREATE INDEX edges_heads ON edges (head_vertex);
-------------------------------------------------




■■■■■■■■■■■■■■■■■■■■■■■■■■ グラフモデルの重要なポイント
1. あらゆる vertex は他の vertex とつながっているエッジを持つことができる。
どんなものが関連付けられるか、または関連付けられないか、といった制約をもつスキーマはない。

2. いかなる vertex も、効率的に 来るエッジ、行くエッジを見つけることができる。
グラフを横断的に探すことができる。
vertex のチェインをどちらの方向にもたどっていくことができる。

3. 異なる種類の関係性に異なるラベリングをすることで、一つのグラフにたくさんの異なる情報を格納できる。
かつ、データモデルをクリーンに保つ事ができる
-------------------------------------------------
・上記特徴により、グラフモデルは柔軟性がすごく高い
・リレーショナルなスキーマでは表現が難しかったことができる
例)異なる国の異なる地域構造 ※(France has départements and régions, whereas the US has counties and states)
・国の中に国があった、といったような歴史
・データの様々な粒度
(Lucy’s current residence is specified as a city, whereas her place of birth is specified only at the level of a state).
・Evolvability!：グラフを拡張して、ある vertex の様々な情報をどんどん追加していくことができる。

例)どんな食べ物に対するアレルギーが有るか
アレルゲンをvertexに、人とアレルゲンの間にエッジをつなげて、アレルギーとラベリングする。
アレルゲンと、別のvertexのセット(どの食べ物がどんな成分からなっているか)をつなげる
 →誰が、どんな食べ物を食べても平気なのか、といったクエリが書ける
 ※アプリにフィーチャーを追加した際に、グラフはアプリのデータ構造の変化を簡単に吸収できる
