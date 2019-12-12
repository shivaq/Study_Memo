# 引数の位置

* 引数は、関数におけるその位置によって identified される


 random.randint(1, 10) と random.randint(10, 1) とは違う。


# keyword arguments

* 関数の、その引数の前にあるキーワードによって identify される

* オプショナルなパラメータとして使われることが多い







## end

* 引数の最後に何を出力するかを指定

```py
print("You, ", end='')
print("Die!!")
```
<!-- アウトプット 通常 print は最後に改行を追加するが。。。-->
You, Die!!




## sep


* 引数と引数との間の セパレートする値として、何を出力するかを指定


```py
print('cats', 'dogs', 'mice', sep=',')
```
<!-- アウトプット。通常値と値の間は シングルスペース だが。。。 -->
cats,dogs,mice
