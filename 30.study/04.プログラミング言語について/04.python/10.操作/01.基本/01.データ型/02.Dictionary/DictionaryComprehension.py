▼ List comprehension
-------------------------------------------------
list_a = [1, 2, 3, 4]
list_b = [2, 3, 4, 5]
# list_a の for ループを先に回す、ネストされた for ループ
# list_a から要素を取り出し →list_b から要素を取り出し →2つの取り出し値が一致したら、リストに格納
common_num = [a for a in list_a for b in list_b if a == b]

print(common_num) # Output: [2, 3, 4]
-------------------------------------------------


▼ Dict Comprehension
-------------------------------------------------
# 0 を取り出す →条件式に一致したら、辞書に加工して格納
newdict = {x: x**3 for x in range(10) if x**3 % 4 == 0}
print(newdict)
-------------------------------------------------
{0: 0, 8: 512, 2: 8, 4: 64, 6: 216}
-------------------------------------------------


▼ ある値とその異なるデータ型とでリストを作る
-------------------------------------------------
>{str(i):i for i in [1,2,3,4,5]}
{'1': 1, '3': 3, '2': 2, '5': 5, '4': 4}
-------------------------------------------------


▼ リストの値とその長さで辞書を作る
-------------------------------------------------
>fruits = ['apple', 'mango', 'banana','cherry']
# dict comprehension to create dict with fruit name as keys
>{f:len(f) for f in fruits}
{'cherry': 6, 'mango': 5, 'apple': 5, 'banana': 6}
-------------------------------------------------


▼
-------------------------------------------------
>{f:f.capitalize() for f in fruits}
{'cherry': 'Cherry', 'mango': 'Mango', 'apple': 'Apple', 'banana': 'Banana'}
-------------------------------------------------


▼ リストの値とリスト内インデックスとのキーバリュー
-------------------------------------------------
# dict comprehension example using enumerate function
>{f:i for i,f in enumerate(fruits)}
{'cherry': 3, 'mango': 1, 'apple': 0, 'banana': 2}
-------------------------------------------------


▼ key:value pair をひっくり返す
-------------------------------------------------
# dict comprehension example to reverse key:value pair in a dictionary
>f_dict = {f:i for i,f in enumerate(fruits)}
>f_dict
{'apple': 0, 'banana': 2, 'cherry': 3, 'mango': 1}
# dict comprehension to reverse key:value pair in a dictionary
>{v:k for k,v in f_dict.items()}
{0: 'apple', 1: 'mango', 2: 'banana', 3: 'cherry'}
-------------------------------------------------


▼
-------------------------------------------------
fruits = ['apple', 'mango', 'banana','cherry']
f_d1 ={f:f.capitalize() for f in fruits}
# keys to be removed
>remove_this = {'apple','cherry'}
# 該当辞書のキーを使って、remove_this を引き算 →結果の要素から、要素:要素をキーとする値、のキーペアを抽出
>{key:f_d1[key] for key in f_d1.keys() - remove_this}
{'banana': 'Banana', 'mango': 'Mango'}
-------------------------------------------------


▼
-------------------------------------------------

-------------------------------------------------
