■■■■■■■■■■■■■■■■■■■■■■■■■■ List Comprehensions
▼ Syntax
new_list = [expression for_loop_one_or_more condtions]

[expression for item in list]

▼ ネストされたリストの list comprehension
-------------------------------------------------
# for ループの上位階層から順に書いていく
# ネストの最深部から取り出した要素を、左端の 関数なりなんなりで処理し、それの集まりがリストになる
# l リストから x を取り出し、x リストから y を取り出し、その y を処理する
[float(y) for x in l for y in x]
-------------------------------------------------


▼ list comprehension 内で 処理の連なり
-------------------------------------------------
>>> strings = [ ['foo', 'bar'], ['baz', 'taz'], ['w', 'koko'] ]
>>> [ (letter, idx) for idx, lst in enumerate(strings) for word in lst if len(word)>2 for letter in word]
[('f', 0), ('o', 0), ('o', 0), ('b', 0), ('a', 0), ('r', 0), ('b', 1), ('a', 1), ('z', 1), ('t', 1), ('a', 1), ('z', 1), ('k', 2), ('o', 2), ('k', 2), ('o', 2)]
-------------------------------------------------


-------------------------------------------------
# 使わない場合
squares = []
for value in range(1,11):
  squares.append(value**2)
-------------------------------------------------
# 使った場合
#  for ループの各要素に対して、value**2を実行し、それぞれリストの要素として左辺に代入
squares = [value**2 for value in range(1, 11)]
-------------------------------------------------

▼ ２つのリストの共通する値を抽出
-------------------------------------------------
# 使わない場合
list_a = [1, 2, 3, 4]
list_b = [2, 3, 4, 5]

common_num = []

for a in list_a:
  for b in list_b:
    if a == b:
      common_num.append(a)

print(common_num)  # Output [2, 3, 4]
-------------------------------------------------
# 使う場合
list_a = [1, 2, 3, 4]
list_b = [2, 3, 4, 5]
# list_a の for ループを先に回す、ネストされた for ループ
# list_a から要素を取り出し →list_b から要素を取り出し →2つの取り出し値が一致したら、リストに格納common_num = [a for a in list_a for b in list_b if a == b]
common_num = [a for a in list_a for b in list_b if a == b]

print(common_num) # Output: [2, 3, 4]
-------------------------------------------------


▼
-------------------------------------------------
list_a = [1, 2, 3]

square_cube_list = [ [a**2, a**3] for a in list_a]

print(square_cube_list) # Output: [[1, 1], [4, 8], [9, 27]]
-------------------------------------------------


▼ 多重配列の、第一列のみを抽出してリストを作成
-------------------------------------------------
# Let's make three lists
lst_1=[1,2,3]
lst_2=[4,5,6]
lst_3=[7,8,9]

# Make a list of lists to form a matrix
matrix = [lst_1,lst_2,lst_3]

first_col = [row[0] for row in matrix]
-------------------------------------------------
