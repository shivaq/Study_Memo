■■■■■■■■■■■■■■■■■■■■■■■■■■ MapReduce とは
・大量のデータを、複数のマシンをつかってまとめて処理するプログラミングモデル
・宣言的でも命令的でもない、その中間のクエリ言語/API
・クエリのロジックは、コードスニペットによって表現される。
・上記スニペットは、処理を行うフレームワークによって繰り返し呼び出される。
・the map (also known as collect) and reduce (also known as fold or inject) functions に基づいている。
・low level なプログラミングモデル
・マシンのクラスタ上で分散的に実行される
・SQLのように、高度なクエリ言語を、MapReduce のパイプラインとして実装することができる。

▼ MapReduce の制約
map and reduce は関数に徹していなければならない。
渡された値のみを使い、追加のクエリをすることはできない。
その他副作用があってはならない。
▼ 上記制約の効果
・DBは、MapReduce をどこでどんな順番で実行してもいいし、失敗したっていい。
・制約があるにもかかわらず、いろいろできる。
they can parse strings, call library functions, perform calculations, and more.

▼ MapReduce の欠点
・JavaScript の2つの関数を組み合わせて使わなければならない
※ 単一のクエリを書くよりずっと難しかったりする
・宣言的クエリ言語だったら、クエリのパフォーマンスを改善するための optimizer を使うことができたりする



■■■■■■■■■■■■■■■■■■■■■■■■■■ 例
例)海洋学者が、海で見かけた動物の観察記録をDBに追加していっていると想像してほしい。
で、ひと月あたり何匹のサメを見かけたか、というレポートを生成したいとする。


■■■■■■■■■■■■■■■■■■■■■■■■■■ PostgreSQL の場合
-------------------------------------------------
SELECT date_trunc('month', observation_timestamp) AS observation_month,
sum(num_animals) AS total_animals
FROM observations
WHERE family = 'Sharks' GROUP BY observation_month;
-------------------------------------------------
// ※date_trunc('month', timestamp)
// ・タイムスタンプを含む、月を判断し、月の始まりにあたるタイムスタンプを返す。
// つまり、タイムスタンプを直近の月にまるめてくれる。
・やっていること
 → observations をSharks ファミリーの種だけにフィルタリングする。
  →observations を、月でグルーピングする
   →その月に目撃したすべての動物の数を追加する。



■■■■■■■■■■■■■■■■■■■■■■■■■■ MongoDB の MapReduce 機能を使った場合
-------------------------------------------------
db.observations.mapReduce(
// ②JavaScript の 　map が、クエリにマッチした各ドキュメントに対して呼ばれる呼ばれる
function map() {
var year = this.observationTimestamp.getFullYear();
var month = this.observationTimestamp.getMonth() + 1;
// ③キー (年と月からなる文字列)と、バリュー(この観察で発見した動物の数) を emit する
emit(year + "-" + month, this.numAnimals);
},
// ④map が emit した key/value がキーでグループ化される。
// すべての key/value のうち、同じキーのもについては、reduce は一度だけ呼び出される。
function reduce(key, values) {
  // ⑤すべてのobservtions から動物の数を月ごとに集計していく
return Array.sum(values);
},
        {
            // ①declarative に、Shark だけをフィルタリング。ここが、MongoDB特有のMapReduce extension らしい
            query: { family: "Sharks" },
            // ⑥ 下記名前の collection にアウトプットが格納される
            out: "monthlySharkReport"
} );
-------------------------------------------------
下記オブジェクトが observations だったとして、
-------------------------------------------------
// {
//        observationTimestamp: Date.parse("Mon, 25 Dec 1995 12:34:56 GMT"),
//        family:     "Sharks",
//        species:    "Carcharodon carcharias",
//        numAnimals: 3
// }
// }
//            observationTimestamp: Date.parse("Tue, 12 Dec 1995 16:17:18 GMT"),
// family:     "Sharks",
// species:    "Carcharias taurus",
// numAnimals: 4
// }
-------------------------------------------------
map 関数は、各ドキュメントごとに一度コールされる。
 →emit("1995-12", 3) and emit("1995-12", 4)
  →reduce が呼ばれる reduce("1995-12", [3, 4])
   →7 を返す
-------------------------------------------------


■■■■■■■■■■■■■■■■■■■■■■■■■■ MongoDB 2.2 ※「the aggregation pipeline」という宣言的クエリ言語のサポートを追加
db.observations.aggregate([
    { $match: { family: "Sharks" } },
    { $group: {
        _id: {
            year:  { $year:  "$observationTimestamp" },
            month: { $month: "$observationTimestamp" }
},
totalAnimals: { $sum: "$numAnimals" } }}
]);
