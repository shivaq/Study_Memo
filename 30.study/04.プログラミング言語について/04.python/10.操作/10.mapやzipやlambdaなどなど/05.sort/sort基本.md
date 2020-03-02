# 組み込み関数 sorted
* sorted([5, 2, 3, 1, 4]) --> [1, 2, 3, 4, 5]

* sorted はlist 以外のiterator にも対応
>>> sorted({1: 'D', 2: 'B', 3: 'B', 4: 'E', 5: 'A'})
[1, 2, 3, 4, 5]





# list.sort()

```py
a = [5, 2, 3, 1, 4]
a.sort()
a # [1, 2, 3, 4, 5]
```




# インスタンスの値をキーにソートする

* ソートに使うクラスの定義はこちら
```py

class Student:
    def __init__(self, name, grade, age):
        self.name = name
        self.grade = grade
        self.age = age
    def __repr__(self):
        return repr((self.name, self.grade, self.age))
```


*  ソート対象のリストはこちら

```py
student_objects = [
    Student('john', 'A', 15),
    Student('jane', 'B', 12),
    Student('dave', 'B', 10),
]
```


# lambda を使ってソート

```py
sorted(student_objects, key=lambda student: student.age)   # [('dave', 'B', 10), ('jane', 'B', 12), ('john', 'A', 15)]
```


# Operator モジュールで更に高速化

```py
from operator import itemgetter, attrgetter

sorted(student_objects, key=attrgetter('age')) # [('dave', 'B', 10), ('jane', 'B', 12), ('john', 'A', 15)]
```

# 二段階ソート

```py
sorted(student_objects, key=attrgetter('grade', 'age')) # [('john', 'A', 15), ('dave', 'B', 10), ('jane', 'B', 12)]
```
-------------------------------------------------







# list のインデックスを使って、ソートのキーを指定

*  ソート対象のリストはこちら
```py
student_tuples = [
('john', 'A', 15),
('jane', 'B', 12),
('dave', 'B', 10)
]
```




# lambda を使ってソート
```py
# iterable の要素を、lambda `lambda student: student[2]` で処理し、その返り値をキーに、ソートを行う
sorted(student_tuples, key=lambda student: student[2]) # [('dave', 'B', 10), ('jane', 'B', 12), ('john', 'A', 15)]
```





# Operator モジュールで更に高速化

```py
from operator import itemgetter, attrgetter

sorted(student_tuples, key=itemgetter(2)) # [('dave', 'B', 10), ('jane', 'B', 12), ('john', 'A', 15)]
```

# 二段階ソート

```py
sorted(student_tuples, key=itemgetter(1,2)) # [('john', 'A', 15), ('dave', 'B', 10), ('jane', 'B', 12)]
```
-------------------------------------------------
