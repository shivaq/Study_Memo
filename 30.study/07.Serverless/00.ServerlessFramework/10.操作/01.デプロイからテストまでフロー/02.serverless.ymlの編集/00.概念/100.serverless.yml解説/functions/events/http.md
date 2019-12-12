# API Gateway event syntax




# 読み方
* GET リクエストで、 API Gateway の hello に アクセスがあった場合
* hello function が実行される




```yaml
functions:
  index:
    handler: handler.hello
    events:
      - http: GET hello
```





```yml
functions:
  webhook:
    handler: handler.webhook
    events:
      - http: POST /

  set_webhook:
    handler: handler.set_webhook
    events:
      - http: POST /set_webhook

```


# events は lambda-proxy がおすすめ
* HTTP リクエストを自動的に Lambda Function に渡す(headers, body, etc.)

* コードによってレスポンス設定もできる(headers, status code, body)
