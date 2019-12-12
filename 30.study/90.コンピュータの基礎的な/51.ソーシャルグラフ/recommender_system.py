▼ recommender system を作るやり方
・Content based
・community based
・Collaborative Filtering

▼ Personalization
・機械学習における大きなトピック
・おすすめシステムをつくるには、まずどのタイプの recommendation を提供するかを決める
※ Content based、 community based など
・recommendation タイプに基づいて、scoring model をデザインする
※ 各ユーザーの interest にマッチした、recommend したいモノにどう点数をつけるか、といったモデル


■■■■■■■■■■■■■■■■■■■■■■■■■■ Collaborative filtering(CF)
▼ CF のベーシックアプローチ
1.user-based collaborative filtering
似た興味を持つ人達を探す
彼等の振る舞いを分析する
ユーザーに、彼等が好むのと同じアイテムをレコメンドする。

2.item-based collaborative filtering
そのユーザーが以前に買ったのと似たアイテムを見つけてレコメンドする

▼ 上記はともに次のステップを踏んでレコメンドする
1.そのユーザー/アイテム に似た ユーザー/アイテム がDBにいくつあるかを探る。
2.すべての似通った ユーザー/アイテム たちと照らし合わせて、この ユーザー/アイテムにどれくらいのグレードを与えるかを評価する。


■■■■■■■■■■■■■■■■■■■■■■■■■■ Pearson correlation
・CF でよく使われる
・2つのユーザー間の Similarity と彼等の属性(どんなブログを読むことを選んだか、など)を計算できる。
・2つの変数(またはユーザー)の the linear dependenceを、彼等の属性の関数として計測するアルゴリズム
・すべてのユーザーに対して、計測されるわけではない。
・より行為の類似性メトリック(似たブログを読んでいるなど)である程度類似性のある群にフィルタリングされた上で計測される。
