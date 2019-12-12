▼ ZIP
 # →2つのリストの要素を、tuple ペアのリストに変換
-------------------------------------------------
list_a = [1, 2, 3]
list_b = [4, 5, 6]

zipped = zip(a, b) # Output: Zip Object. <zip at 0x4c10a30>

len(zipped) # TypeError: object of type 'zip' has no len()

zipped[0] # TypeError: 'zip' object is not subscriptable

list_c = list(zipped) #Output: [(1, 4), (2, 5), (3, 6)]

list_d = list(zipped) # Output []... Output is empty list becuase by the above statement zip got exhausted.
-------------------------------------------------



■■■■■■■■■■■■■■■■■■■■■■■■■■ unzip
zipper_list = [(1, 'a'), (2, 'b'), (3, 'c')]

 list_a, list_b = zip(*zipper_list)
 print list_a # (1, 2, 3)
 print list_b # ('a', 'b', 'c')
