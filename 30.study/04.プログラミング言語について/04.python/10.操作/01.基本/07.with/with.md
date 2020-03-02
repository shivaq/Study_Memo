# with のメリット
* withブロックを抜けると、自動でclose()がコールされる

* withブロック内で例外が起きた場合も自動でclose()がコールされる

* withの行で例外が発生した場合は、close()はコールされない




# with が使えるクラスの実装
* with構文で使えるクラスには、2つのメソッドが実装されている

```py
__enter__(self)
__exit__(self, exception_type, exception_value, traceback)
```

```py
with # call __enter__() as var_name:
    Your logic
    # call __exit__()
```
