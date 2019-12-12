■■■■■■■■■■■■■■■■■■■■■■■■■■ SPARQL
クエリ言語
triple store
RDF データモデルを使用
Cypher は、パターンマッチングを SPARQL から拝借してる。

-------------------------------------------------
PREFIX : <urn:example:>
SELECT ?personName WHERE {
  ?person :name ?personName.
  ?person :bornIn  / :within* / :name "United States".
  ?person :livesIn / :within* / :name "Europe".
}
-------------------------------------------------

▼ Cypherとの比較
-------------------------------------------------
(person) -[:BORN_IN]-> () -[:WITHIN*0..]-> (location)   # Cypher
?person :bornIn / :within* ?location.                   # SPARQL
-------------------------------------------------


(usa {name:'United States'})   # Cypher
?usa :name "United States".    # SPARQL
