■■■■■■■■■■■■■■■■■■■■■■■■■■ gitignore が反映されない？？
一度 git にあっぷしたものを gitignore に追加するとこうなる
キャッシュにインデックスが残っている
キャッシュが残ってるから消す
git rm -r --cached 無視対象ファイル

■■■■■■■■■■■■■■■■■■■■■■■■■■ PyCharm の場合
▼ gitignore 生成
プロジェクトディレクトリ上で右クリック →New .ignore file
 →.gitignore を選択 ※これで生成される

▼ gitignore に追加
・下記のように、無視したいファイルを追記するだけ
.idea/vcs.xml
.idea/workspace.xml

■■■■■■■■■■■■■■■■■■■■■■■■■■ ターミナルの場合
▼ git レポジトリに .gitignore を作成し、追跡不要ファイルを記載する


■■■■■■■■■■■■■■■■■■■■■■■■■■ .gitignore_global
・グローバルに適用される gitignore
▼ ファイル構築
vi ~/.gitignore_global

▼ ファイルに無視したいファイルを追加


▼ 反映
git config --global core.excludesfile ~/.gitignore_global

