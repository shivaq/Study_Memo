▼ with のメリット
with構文を使うと、close()の呼び出しが不要です。withブロックを抜けると、自動でclose()を呼び出してくれます。withブロック内で例外が起きた場合も同様です。ただし、withの行で例外が発生した場合は、close()は呼ばれません。


▼ with を使う場合、使わない場合
-------------------------------------------------
# with構文を使っていないファイル書き込み例
wfp = open('msg.log', 'w')
wfp.write('need call close, if do not use with statement.')
wfp.close()

# with構文を使ったファイル書き込み例
with open('msg.log', 'w') as wfp:
    wfp.write('it is example for with statement.')
    wfp.write('do not need call close().')
-------------------------------------------------


▼ with が使えるクラスの実装
-------------------------------------------------
# with構文で使えるクラスには、2つのメソッドが実装されています。

__enter__(self)
__exit__(self, exception_type, exception_value, traceback)
# この2つを実装したクラスをwithに渡すと次のタイミングで呼ばれます。*1

with # call __enter__() as var_name:
    Your logic
    # call __exit__()
-------------------------------------------------
