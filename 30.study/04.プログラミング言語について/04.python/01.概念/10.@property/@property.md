# 設計者が意図しないプロパティ変更をされないようにするために使う @property
* properties の正体は Descriptor です





# getter, setter, deleter

```py
class MyProperty(object):
    def __init__(self, x):
        self._x = x

    @property # propertyの時は　x.getterと同義
    def x(self):
        return self._x

    @x.setter
    def x(self, v):
        self._x = abs(v) # 更新前に何らかの処理をはさめる

    @x.deleter
    def x(self):
        self._x = None
```

```py
mypro = Myoperty(100)
print(mypro.x) # 100

mypro.x = -200
print(mypro.x) # 200

del mypro.x
print(mypro.x) # None
```







# property で定義されていない set, delete はエラーになる

```py
class MyProperty(object):
    def __init__(self, x):
        self._x = x

    @property
    def x(self):
        return self._x
```



```py
mypro = Myoperty(100)
print(mypro.x) # 100

mypro.x = -200
AttributeError # 定義されていないのでエラー
```
