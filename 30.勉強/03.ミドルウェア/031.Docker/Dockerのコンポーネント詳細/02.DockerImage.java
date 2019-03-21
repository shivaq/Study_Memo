■■■■■■■■■■■■ Docker Image
・状態を持たない。immutable
・リードオンリーのテンプレート
・ほしいパッケージ化されたアプリを定義
・上記の依存関係を定義
・Docker ファイルに記述されたステップを run した結果のもの
・起動時に走らせたいプロセスを定義
・ダウンロードされ、それからビルドし、run する
・Docker Image にはレイヤーがある
・Docker image はレイヤーをスタックしたような状態
・レポジトリが更新されたときは、変更のあるレイヤーのみがリビルドされる

▼ Docker Image を構成する2つのFile
・Manifest File
Imageのメタデータを持つ JSON File
・レイヤーFile
各レイヤーのディレクトリツリーを含んだ tar.gz File
