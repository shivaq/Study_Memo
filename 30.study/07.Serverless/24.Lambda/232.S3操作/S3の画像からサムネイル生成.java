

-------------------------------------------------
■■■■■■■■■■■■■■■■■■■■■■■■■■ 設計
▼ 前提条件
デベロッパ：２つのバケットを作成しておく
・オリジナル画像の格納先
・処理後画像の格納先

▼ フロー
ユーザー：S3 に オブジェクトアップロード

S3：オブジェクト作成イベントを検知

S3：Lambda を呼び出す
 ・イベントデータを渡す(ソースバケット名、オブジェクトキー名)
 ・s3:ObjectCreated:* イベントを AWS Lambda に発行

Lambda：サムネイルをターゲットバケットに保存
 ・グラフィックスライブラリを使用
-------------------------------------------------
■■■■■■■■■■■■■■■■■■■■■■■■■■ python 準備
▼ Pythonコード準備
参照：
// C:\Users\Yasuaki\Dropbox\01.study\MyAws\80.Project\801.Python\8011.Lambda\ImageResize\venv\base_code\CreateThumbnail.py

▼ ライブラリを ZIP に追加
参照:
// C:\Users\Yasuaki\Dropbox\01.study\MyAws\20.手順\23.Lambda\230.ライブラリをzipに追加.py





▼ S3
source-shivaq
target-shivaq
・通知機能追加
https://docs.aws.amazon.com/ja_jp/AmazonS3/latest/dev/NotificationHowTo.html

▼ IAM
