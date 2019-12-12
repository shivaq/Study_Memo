■■■■■■■■■■■■■■■■■■■■■■■■■■ 組み込み関数 sorted
sorted([5, 2, 3, 1, 4]) --> [1, 2, 3, 4, 5]

# sorted はlist 以外のiterator にも対応
>>> sorted({1: 'D', 2: 'B', 3: 'B', 4: 'E', 5: 'A'})
[1, 2, 3, 4, 5]

■■■■■■■■■■■■■■■■■■■■■■■■■■ list.sort()
>>> a = [5, 2, 3, 1, 4]
>>> a.sort()
>>> a
[1, 2, 3, 4, 5]


■■■■■■■■■■■■■■■■■■■■■■■■■■ イテレータの特定の要素をキーにソート
# 比較を行う前にリストの各要素に対して呼び出される関数を指定するパラメータ

>>> student_tuples = [
...     ('john', 'A', 15),
...     ('jane', 'B', 12),
...     ('dave', 'B', 10),
# lambda -->引数の３つ目の要素をキーとする
# キーを基準にソートする
>>> sorted(student_tuples, key=lambda student: student[2])   # sort by age
[('dave', 'B', 10), ('jane', 'B', 12), ('john', 'A', 15)]

■■■■■■■■■■■■■■■■■■■■■■■■■■ オブジェクトの特定の属性をキーにソート
>>> class Student:
...     def __init__(self, name, grade, age):
...         self.name = name
...         self.grade = grade
...         self.age = age
...     def __repr__(self):
...         return repr((self.name, self.grade, self.age))

>>> student_objects = [
...     Student('john', 'A', 15),
...     Student('jane', 'B', 12),
...     Student('dave', 'B', 10),
... ]


>>> sorted(student_objects, key=lambda student: student.age)   # sort by age
[('dave', 'B', 10), ('jane', 'B', 12), ('john', 'A', 15)]

■■■■■■■■■■■■■■■■■■■■■■■■■■ Operator モジュールで更に高速化
>>> from operator import itemgetter, attrgetter

>>> sorted(student_tuples, key=itemgetter(2))
[('dave', 'B', 10), ('jane', 'B', 12), ('john', 'A', 15)]

>>> sorted(student_objects, key=attrgetter('age'))
[('dave', 'B', 10), ('jane', 'B', 12), ('john', 'A', 15)]

■■■■■■■■■■■■■■■■■■■■■■■■■■ 二段階ソート
>>> sorted(student_tuples, key=itemgetter(1,2))
[('john', 'A', 15), ('dave', 'B', 10), ('jane', 'B', 12)]
>>> sorted(student_objects, key=attrgetter('grade', 'age'))
[('john', 'A', 15), ('dave', 'B', 10), ('jane', 'B', 12)]
