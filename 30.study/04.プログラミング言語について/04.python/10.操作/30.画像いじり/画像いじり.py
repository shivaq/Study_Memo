■■■■■■■■■■■■■■■■■■■■■■■■■■ Pillow ライブラリ
PIL(Python Image Library)からフォークされた画像処理ライブラリ
・OpenCVみたいな高度な画像処理（顔認識とかオプティカルフローとか）はできない・リサイズ（拡大・縮小）や回転、トリミング（部分切り出し）はできる
-------------------------------------------------
▼ インポート
# X import Pillow
上記ではなく、下記で
import PIL

▼ インストール
# X pip install PIL
上記ではなく、下記で
pip install Pillow
-------------------------------------------------

# PIL.Image.size は、(width, height) のtuple として生成される引数
# thumbnail は tuple(width, height) を引数として受け取る
image.thumbnail(tuple(x / 2 for x in image.size))
Lambda/Python/CreateThumbnail.zip
