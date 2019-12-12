■■■■■■■■■■■■■■■■■■■■■■■■■■ Cypher Query Language
・declarative なクエリ言語。
・Neo4j 用に作られた。
・Matrix からつけられた名前であり、暗号化などとは無関係


▼ Cypher query に(図2−5の)データを Insert する例
-------------------------------------------------
CREATE
  (NAmerica:Location {name:'North America', type:'continent'}),
  (USA:Location      {name:'United States', type:'country'  }),
  (Idaho:Location    {name:'Idaho',         type:'state'    }),
  (Lucy:Person       {name:'Lucy' }),
  (Idaho) -[:WITHIN]->  (USA)  -[:WITHIN]-> (NAmerica),
  (Lucy)  -[:BORN_IN]-> (Idaho)
-------------------------------------------------
・各 vertex はシンボリックネーム(USA、Idaho) が付与されている。
・シンボリックな名前を使って、 vertex 間のエッジを作成できる
// Idaho をテイルに、USAをヘッドとする、WITHIN というラベルを作成


▼ Cypher クエリ：アメリカからヨーロッパへ移民したすべての人々の名前を探す
-------------------------------------------------
MATCH
  (person) -[:BORN_IN]->  () -[:WITHIN*0..]-> (us:Location {name:'United States'}),
  (person) -[:LIVES_IN]-> () -[:WITHIN*0..]-> (eu:Location {name:'Europe'})
RETURN person.name
-------------------------------------------------
翻訳
1.(person) -[:BORN_IN]-> ()
// ・tail vertex は person という変数に紐付いている。head vertex は名指しされないままのこされている
// BORN_IN ラベルが付いたエッジに紐付いた、両端の vertex をすべて返す
// ・別の言い方：person はなんらかの vertex に対する外向きの BORN_IN エッジをもっている。
-[:WITHIN*0..]-> (us:Location {name:'United States'}),
// ・その vertex から外向け WITHIN エッジをたどる。。。(どこまで？)Location というタイプの、name 属性が "United States "のvertexまで。
// WITHIN*0.. →WITHIN エッジを0回〜複数回たどっていく
2. , (person) -[:LIVES_IN]-> ()
// 同じ person vertex はなんらかの vertex に対する外向きの LIVES_IN エッジをもっている。
-[:WITHIN*0..]-> (eu:Location {name:'Europe'})
// ・その vertex から外向け WITHIN エッジをたどる。。。(どこまで？)Location というタイプの、name 属性が "Europe"のvertexまで。
3. RETURN person.name
上記条件にマッチした person vertex の name プロパティを返す

★★★★ 逆方向の検索もできる！
・name 属性に インデックスがあったなら、
効率的に US と Europe の vertex を探し出すことができる。
そのvertex の WITHIN エッジをたどることで、すべてのlocations (states, regions, cities, etc.) を見つけ出す。
最後に、見つかった locationの中から、来るエッジが BORN_IN or LIVES_IN のものを見つけ出し、それをたどって people を見つけ出す。



▼
LIVES_IN には、どんなロケーション(県や国、町)を指す可能性もある。
町は WITHIN 県かもしれんし、WITHIN 州である可能性だってある。
LIVES_IN エッジは探している場所と直接つながっている可能性もあるし、いくつもの vertex を渡った先でつながっている可能性もある。
