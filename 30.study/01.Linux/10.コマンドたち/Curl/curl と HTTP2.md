## curl が HTTP/2 サポート開始したのは Ver 7.43.0 から。






* --http2 フラグ →HTTP/2 を使用する








## cURL で HTTP2 のウェブアプリをチェックする


* https を使用すること！


* cURL はデフォルトで、証明書の検証をすすめようとするため、自作 証明書を使う場合は失敗する

```sh
# --http2 HTTP/2 を使用する
# --insecure   証明書の検証失敗でもOKにする
curl -I --http2 --insecure https://localhost:8080/
```



* アウトプット

```sh
HTTP/2.0 200
content-type:text/plain; charset=utf-8
content-length:12
date:Mon, 15 Feb 2016 05:33:01 GMT
```
