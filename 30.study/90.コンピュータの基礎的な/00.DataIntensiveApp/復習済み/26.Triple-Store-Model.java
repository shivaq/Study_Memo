■■■■■■■■■■■■■■■■■■■■■■■■■■ The triple-store model
Property Graph とだいたい同じで、同じ概念を違う言葉で表現している。

triple-store は、すべての情報が、3つの構成要素(subject, predicate, object)によって表現されている。
例)
(Jim, likes, bananas)
// Jim is the subject, likes is the predicate (verb), and bananas is the object.

▼ Subject
サブジェクトは、グラフにおける vertex に相当する。



▼ Object
OBJは下記のいずれかに相当する。

1. 値が String や 数値など、プリミティブなデータタイプの場合
-------------------------------------------------
predicate と OBJ は、 subject vertex の keyとvalue に相当する
例)
(lucy, age, 33)
は、
lucy という vertex の {"age":33} という属性に当たる
-------------------------------------------------

2. Another vertex in the graph の場合
-------------------------------------------------
predicate は グラフにおけるエッジに相当する
subject は tail vertexに相当する。
object は head vertex に相当する。
例)
(lucy, marriedTo, alain)
lucy も alain もともに vertex。
marriedTo は両者をつなぐエッジのラベル


▼ Turtle triple の表現例
-------------------------------------------------
@prefix : <urn:example:>.
_:lucy     a       :Person.
_:lucy     :name   "Lucy".
_:lucy     :bornIn _:idaho.
_:idaho    a       :Location.
_:idaho    :name   "Idaho".
_:idaho    :type   "state".
_:idaho    :within _:usa.
_:usa      a       :Location.
_:usa      :name   "United States".
_:usa      :type   "country".
_:usa      :within _:namerica.
_:namerica a       :Location.
_:namerica :name   "North America".
_:namerica :type   "continent".
-------------------------------------------------
_:someName →グラフにおける vertex
// ・vertex 名前自体は、このファイル内で、同一のvertex であることを知るための識別子でしかなく、ファイルの外のスコープでは無意味となる。
_:idaho :within _:usa
// こういう書き方の場合は、OBJ は vertex
n _:usa :name "United States"
// こういう書き方の場合は、predicate は プロパティ、OBJは文字列そのもの

▼ より簡素な書き方
-------------------------------------------------
@prefix : <urn:example:>.
_:lucy     a :Person;   :name "Lucy";          :bornIn _:idaho.
_:idaho    a :Location; :name "Idaho";         :type "state";   :within _:usa.
_:usa      a :Location; :name "United States"; :type "country"; :within _:namerica.
_:namerica a :Location; :name "North America"; :type "continent".



-------------------------------------------------
