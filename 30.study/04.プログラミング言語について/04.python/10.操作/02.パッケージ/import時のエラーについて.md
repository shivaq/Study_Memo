## attempted relative import with no known parent package

### 解決策1

1. 下記のようなディレクトリ構造にする
```
 new_project
 ├── main.py
 └── project
     ├── __init__.py
     ├── config.py
     └── package
         ├── __init__.py
         └── demo.py
```

2. main.py を下記のようにする
```py
 import project.package.demo
```

3. `python3 main.py` でパッケージ情報が得られる

### 原因解説

1. 下記のようなディレクトリ構成で
```py
 project
 ├── config.py
 └── package
     ├── __init__.py
     └── demo.py
```


2. config.py を project/package/demo.py から下記のように参照しているとする
```py
from .. import config
 print("The value of config.count is {0}".format(config.count))
```

3. 上記スクリプトを実行すると、下記エラーとなる

```py
Y:/project>python package/demo.py
 Traceback (most recent call last):
   File "package/demo.py", line 1, in <module>
     from .. import config
 ImportError: attempted relative import with no known parent package
```



### そもそも、Python インタープリタはどのように relative module を解決するのか
- モジュールの __name__ によって、パッケージ hierarchy における位置を判断する
- __name__ と __package__ を使う。


### __package__ 情報がない場合
- モジュール名がパッケージの情報を含んでいない場合(__main__ とセットされている場合など)

-  →relative import はモジュールがトップレベルのモジュールだと判断する(実際の位置は関係なく)


- モジュールの冒頭に、下記を書いておくと、**package 情報がない**ことがわかる
- package 情報がないので、parent package を見つけられずに文句を言う
```py
print('__file__={0:<35} | __name__={1:<20} | __package__={2:<20}'.format(__file__,__name__,str(__package__)))
```

__file__=set_aws_mfa/__main__.py             | __name__=__main__             | __package__=


