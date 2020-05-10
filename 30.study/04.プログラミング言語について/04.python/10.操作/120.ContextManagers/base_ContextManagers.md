# Context manager とはどんなものか

こんなもの
```py
with open('file.txt') as f:
    contents = f.read()
```


## カスタマイズした context manager を作る

- ロジックが複雑になりそうな場合は、クラスを作るのが良かろう

```py
class CustomOpen(object):
    def __init__(self, filename):
        self.file = open(filename)

    def __enter__(self):
        return self.file

    def __exit__(self, ctx_type, ctx_value, ctx_traceback):
        self.file.close()

with CustomOpen('file') as f:
    contents = f.read()
```

- ロジックがシンプルな場合はこちらがよかろう

```py
from contextlib import contextmanager

@contextmanager
def custom_open(filename):
    f = open(filename)
    try:
        # enter時に下記を行い。。。
        yield f
    finally:
        # exit 時に下記を行う。
        f.close()

with custom_open('file') as f:
    contents = f.read()
```
