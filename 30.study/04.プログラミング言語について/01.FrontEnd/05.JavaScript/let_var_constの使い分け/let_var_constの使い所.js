■■■■■■■■■■■■■■■■■■■■■■■■■■ 結論
できるだけ let を使う。
※ スコープ関係の問題を回避するために


-------------------------------------------------

▼ var
function の内部で定義された var の場合、
スコープは function 内

for(var i = 0; i<10;i++){...}みたく、
function が絡まない場合は{} がスコープの境界となることもなく、ずっと有効

▼ let
{} がスコープの境界となる。
for(var i = 0; i<10;i++){...}の場合、
{} で i を使おうとすると undefined エラー。

▼ const
immutable。一度値を割り当てらてたら、再度割り当てようとするとエラーになる。

▼ const の immutable 判定
const dog={
  age: 3// age はimmutable ではない
}
dog.age = 5// ここはエラーにならない
dog = { name: 'biko'} // ここはエラー
