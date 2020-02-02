# Script vs. Module
-------------------------------------------------
## Python ファイルを直接呼ぶのと、他のファイルからインポートするのとでは大きな違いがある

「ファイルがどのディレクトリにあるのか」の情報だけでは、Python は、そのファイルがどのパッケージに入っているかを判断できない。
それは、
そのファイルを Python にどのようにロードしたかによる。
※ 実行したのか、import したのか。




Just knowing what directory a file is in
does not determine
what package Python thinks it is in.
That depends, additionally, on
how you load the file into Python (by running or by importing).


# The top-level script として Python ファイルをロードする場合
* 直接実行した場合



# モジュールとして Python ファイルをロードする場合
* python -m myfile を実行した場合
* import した場合




# Naming
-------------------------------------------------
file がロードされると、名前が与えられる。
与えられた名前は __name__ attribute に格納される

## The top-level script として Python ファイルをロードする場合
* 名前は __main__ になる


## モジュールとして Python ファイルをロードする場合
* 名前は ドットで結ばれたファイル名になる。

* 下記で、 moduleX をインポートする場合、その名前は package.subpackage1.moduleX になる。
* 下記で、 moduleA をインポートする場合、その名前は package.moduleA になる。


```py
package/
    __init__.py
    subpackage1/
        __init__.py
        moduleX.py
    moduleA.py
```

* だが、moduleA をコマンドラインで実行すれば、その名前は __main__ になる。



# Accessing a module NOT through its containing package
-------------------------------------------------
* モジュール名は、そのモジュールが入っているディレクトリ(or a subdirectory of it)から import される場合と、パッケージ経由で import される場合とでも異なる。

* Python Interpreter を package/subpackage1 内で開始し、 import moduleX する場合
 →moduleX の名前は moduleX。 package.subpackage1.moduleX にはならない。
  →なぜなら、Python が起動時に現在のディレクトリをサーチパスに追加するから。
   →ディレクトリがパッケージの一部かどうかを知ることはない
    →パッケージ情報がモジュール名の一部になることはない

* 例外。Interpreter をインタラクティブモードで開始した場合、そのインタラクティブなセッションの名前は __main__ になる。

* モジュール名に ドットがなかったとする
 →パッケージの一部であるとは考慮されない
 →ファイルが実際にどこにあるのか、は関係ない
 →モジュールの名前がすべて。


Now look at the quote you included in your question:

Relative imports use a module's name attribute
to determine that module's position in the package hierarchy.

If the module's name does not contain any package information (e.g. it is set to 'main')
then
relative imports are resolved as if the module were a top level module,
regardless of
where the module is actually located on the file system.



# Relative imports...
-------------------------------------------------
* Relative imports はモジュール名によって、そのモジュールがパッケージのどこにあるかを判断する。
* `from .. import foo` のような名前であれば、そのドットは現在のパッケージ階層から、何階層か上がることを示す。
* `package.subpackage1.moduleX` が現在のモジュール名だとすると、..moduleA は`package.moduleA`を意味する。



# ... are only relative in a package
-------------------------------------------------
* モジュールの名前が __main__ の場合、それはパッケージだと判断されない。
 →名前にドットはつかないので from .. import は使えない。



# Scripts can't import relative
-------------------------------------------------
* moduleX をコマンドラインから実行する
 →名前は __main__ になる
  → relative import は失敗する
   →なぜならそれはパッケージじゃないから。

* モジュールのある同じディレクトリから Python を実行したとする
 →そのモジュールを import しようとしたとする
  →Pythonは現在のディレクトリからモジュールを"あまりにも早く"見つけてしまうため、それがパッケージの一部だと認識できない

* インタラクティブ インタープリタで実行した場合は、そのインタラクティブセッションの名前は常に __main__ となる。
 →インタラクティブ セッションからは相対 インポートはできない。

* 相対インポートは、モジュールファイルたちの中でのみ、機能する。


# 解決策1
-------------------------------------------------
python -m package.subpackage1.moduleX

-m で、the top-level script ではなくモジュールとしてロードできる


# 解決策2
-------------------------------------------------
 myfile.py を実行して、その中で moduleX を使いたい、みたいな場合

myfile.py をパッケージの中ではなく、別の場所に置いて、実行する
そうすれば、myfile.py 内で、下記のような書き方ができる

from package.moduleA import spam
