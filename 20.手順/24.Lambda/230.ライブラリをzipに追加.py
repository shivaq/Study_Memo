▼ zip 追加がなされてしまうと、ファイルサイズが大きすぎて Lambda コンソールでの編集ができない。
よって
・ライブラリを追加せずに編集
・出来上がったら、ライブラリを追加して改めてアップロード


■■■■■■■■■■■■■■■■■■■■■■■■■■ ライブラリを zip に追加していく
▼ 作業サーバにログイン
▼ 仮想環境作成
参照:
# C:\Users\Yasuaki\Dropbox\01.study\MyAws\20.手順\24.python系\virtualenv.java

▼ 使用するライブラリをインストール
# 例
pip install Pillow
pip install boto3

▼ lib および lib64 サイトパッケージの内容を .zip ファイルに追加
# ・仮想環境の site-packages に移動
cd name_of_the_virtualenv/lib/python3.6/site-packages

# ・サイトパッケージ入り zip ファイルを作成
# -r 再帰的にディレクトリ配下も zip
# 9 圧縮率最高。
# ライブラリの中身を詰め込むので、ファイルサイズを抑えたい。
# Lambda実行時ではなく、Lambda 生成時に展開されるため、処理時間は気にしなくて良い
zip -r9 ~/CreateThumbnail.zip .

# ・zip ファイルに python コードを追加
# -g 既存の zip ファイルにファイルを追加する
cd ~
zip -g CreateThumbnail.zip CreateThumbnail.py
-------------------------------------------------
