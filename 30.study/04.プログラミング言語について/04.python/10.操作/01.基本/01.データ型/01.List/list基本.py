-------------------------------------------------
# 追加
motorcycles = ['honda', 'yamaha', 'suzuki']
motorcycles.append('ducati')

# 挿入
motorcycles.insert(0, 'ducati')
print(motorcycles)

# 削除
del motorcycles[0]
motorcycles.remove('ducati')

# pop
popped_motorcycle = motorcycles.pop()
print(popped_motorcycle)
suzuki
first_owned = motorcycles.pop(0)

# ソート
cars = ['bmw', 'audi', 'toyota', 'subaru']
cars.sort()
cars.sort(reverse=True)
# 一時的にソートするけどもとの順序は維持
print(sorted(cars))

# 逆順にする
cars.reverse()

# 長さ
len(cars)

# List の最後の要素（インデックスエラーを回避）
motorcycles[-1]
-------------------------------------------------

# List 連結
# -------------------------------------------------
spam = [1, 2, 3]
spam = spam + [a, b, c]
# -------------------------------------------------

# List から要素削除
# -------------------------------------------------
spam = ["cat", "bat", "rat", "elephant"]
del spam[2]
# -------------------------------------------------


# List と Pop
# -------------------------------------------------
# Create a new list
list1 = [1,2,3]

# Append
list1.append('append me!')

# Pop off the 0 indexed item
list1.pop(0)

# ポップした要素を割り当て。デフォルトインデックスは -1
popped_item = list1.pop()
-------------------------------------------------







# List ネストしてマトリックス作成
# -------------------------------------------------
# Let's make three lists
lst_1=[1,2,3]
lst_2=[4,5,6]
lst_3=[7,8,9]

# Show
matrix

# Make a list of lists to form a matrix
matrix = [lst_1,lst_2,lst_3]

# Grab first item in matrix object
matrix[0]

# Grab first item of the first item in the matrix object
matrix[0][0]
# -------------------------------------------------










# リストのスライス作成
-------------------------------------------------
players = ['charles', 'martina', 'michael', 'florence', 'eli']
print(players[0:3])
# インデックス 0 から 2 までの計 3つの要素を出力
print(players[1:4])
# インデックス 1 から 3 までの計 3つの要素を出力
print(players[:4])
# インデックス 0 から 3 までの計 4つの要素を出力

# for ループに使用
for player in players[:3]:
print(player.title())
-------------------------------------------------




▼ List のコピー
-------------------------------------------------
my_foods = ['pizza', 'falafel', 'carrot cake']

# こうすると、参照がこぴーされるだけ
friend_foods = my_foods
# もとのListの最初から最後までの要素をコピー
friend_foods = my_foods[:]
-------------------------------------------------
