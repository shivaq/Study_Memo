


# ハンドラーオブジェクトを削除

```py
# Logging is cool!
logger = logging.getLogger()
if logger.handlers:
    for handler in logger.handlers:
        # remove handler object
        logger.removeHandler(handler)
```

## ハンドラーオブジェクトとは

* 適切なログメッセージを (ログメッセージの深刻度に基づいて) ハンドラの指定された出力先に振り分けることに責任を持ちます。

* Logger オブジェクトには addHandler() メソッドで 0 個以上のハンドラを追加することができます。

* 例
あるアプリケーションが
すべてのログメッセージをログファイルに、
error 以上のすべてのログメッセージを標準出力に、
critical のメッセージはすべてメールアドレスに、それぞれ送りたいとします。

* この場合、 3 つの個別のハンドラがそれぞれの深刻度と宛先に応じて必要になります。











# log レベルを設定

```py
# Set log level
logging.basicConfig(level=logging.INFO)
```
