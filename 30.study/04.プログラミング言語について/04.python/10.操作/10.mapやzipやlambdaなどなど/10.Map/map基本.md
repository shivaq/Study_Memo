# map でできること
* for loop を書かなくても iterate 処理ができる


## Syntax
`map(function, iterable, ...)`







# 関数は OBJ

```py

def fahrenheit(celsius):
    """引数を別の数値に変換して返す"""
    return (9/5)*celsius + 32

temps = [0, 22.5, 40, 100]

# 第一引数の関数に、第二引数のiterator の要素が一つ一つ渡され、
# 処理された結果が一つ一つ要素となって変数の代入される
F_temps = map(fahrenheit, temps)

#Show
list(F_temps)
```


## 受け取るIterable の長さが異なる場合
* 最短インデックスがなくなった段階で処理が止まる
```py
a = [1,2,3,4]
b = [5,6,7,8]
c = [9,10,11]

x_y_z = map(lambda x, y, z: x + y + z, a, b, c)
list(x_y_z)
```






* dictionary の キーのみを抽出
```py
dict_a = [{'name': 'python', 'points': 10}, {'name': 'java', 'points': 8}]

map(lambda x : x['name'], dict_a) # Output: ['python', 'java']

map(lambda x : x['points']*10,  dict_a) # Output: [100, 80]

map(lambda x : x['name'] == "python", dict_a) # Output: [True, False]
```
