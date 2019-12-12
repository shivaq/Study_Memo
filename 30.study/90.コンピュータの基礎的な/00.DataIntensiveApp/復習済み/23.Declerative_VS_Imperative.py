▼ 多くのプログラミング言語は Imperative だ。
・Imperative な言語は、コンピュータに対して決まった操作を決まった順番で実行するよう伝える。
コードの行ごとに、条件分岐して、変数を更新して、ループを回すかどうかを判断して。。。


動物リストから、サメだけを抜き出す場合は下記のようになる
-------------------------------------------------
function getSharks() { var sharks = [];
for (var i = 0; i < animals.length; i++) {
    if (animals[i].family === "Sharks") {
                sharks.push(animals[i]);
            }
}
return sharks; }
-------------------------------------------------
・並列処理に向いていない



■■■■■■■■■■■■■■■■■■■■■■■■■■ Declerative
▼ SQL は Declerative なクエリ言語だ。
ほしいデータのパターンを伝えるだけ。どんな条件の結果がほしくて、どういうふうにトランスフォームしてほしいか(ソート、グループ、aggregate)
でも、どのようにそのゴールを達成するかは、DBシステムのクエリオプティマイザーにまかせている。
-------------------------------------------------
SELECT * FROM animals WHERE family = 'Sharks';
-------------------------------------------------
・簡潔
・DBの実装の詳細を抽象化している
・並列処理に向いてる

▼ Web での Declarative クエリ言語
ユーザーがサメのページを見ているとする。
ナビゲーションで、サメが選択されている状態にしたい場合
-------------------------------------------------
<ul>
<li class="selected">
<p>Sharks</p> <ul>
<li>Great White Shark</li> <li>Tiger Shark</li> <li>Hammerhead Shark</li>
</ul> </li>
<li> <p>Whales</p>
<ul>
<li>Blue Whale</li> <li>Humpback Whale</li> <li>Fin Whale</li>
</ul> </li>
</ul>
-------------------------------------------------
選択されたアイテムを、CSS の"selected" クラスでマークする。
結果、<p>Sharks</p> が選択されたページのタイトルになる。

選択されたページの背景を変えたいときも、CSSを使えば簡単
-------------------------------------------------
li.selected > p { background-color: blue;
}
-------------------------------------------------


▼ JavaScript で Imperative に設定するとしたらこうなる
-------------------------------------------------
var liElements = document.getElementsByTagName("li");
for (var i = 0; i < liElements.length; i++) {
if (liElements[i].className === "selected") {
    var children = liElements[i].childNodes; f
    or (var j = 0; j < children.length; j++) {
var child = children[j];

if (child.nodeType === Node.ELEMENT_NODE && child.tagName === "P") {
                    child.setAttribute("style", "background-color: blue");
                }
} }
}
-------------------------------------------------
・長くて読みづらい
・selected クラスが削除されたら(ユーザーが別のページをクリックするとか)、青い背景は削除されない。
ページがリロードされるまで。
※ CSSの場合、ブラウザが変化を検知して背景の変化はすぐに反映される。
・新しいAPIを使いたい場合、コードを書き直さないといけない。
※ブラウザの場合、互換性を気にせずにCSSのパフォーマンスを改善できる。
• If you want to take advantage of a new API, such as document.getElementsBy ClassName("selected")
or even document.evaluate()—which may improve performance—you have to rewrite the code.
