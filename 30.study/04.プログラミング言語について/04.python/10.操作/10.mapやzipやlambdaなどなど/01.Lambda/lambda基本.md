# Lambda にできること
* 関数をいちいち定義せず、アノニマス関数を作成できる。
* ただし、単一の expression にのみ使用するもの。
* ブロック内で複数のタスクを走らせる場合は、関数を定義しましょう。

# syntax
* lambda 引数: 返り値

```py
add = lambda x, y : x + y

print add(2, 3) # Output: 5
```







# Function wrapper
```py
def transform(n):
    return lambda x: x + n

f = transform(3)

f(4) # 7
```


■■■■■■■■■■■■■■■■■■■■■■■■■■ シークエンスの要素を、reduce() を使って連結させる
# 1, 2 →1,2,3 →1,2,3,4.... -->1,2,3,4,5,6,7,8,9
>>> reduce(lambda a, b: '{}, {}'.format(a, b), [1, 2, 3, 4, 5, 6, 7, 8, 9])
'1, 2, 3, 4, 5, 6, 7, 8, 9'


>>> sorted([1, 2, 3, 4, 5, 6, 7, 8, 9], key=lambda x: abs(5-x))
[5, 4, 6, 3, 7, 2, 8, 1, 9]
