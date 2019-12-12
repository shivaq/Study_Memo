▼ filter()
# 引数を処理して true/false を返す関数に、イテレーターの要素を渡す。
# 結果 →True の要素だけを返す
-------------------------------------------------
nums = [0,1,2,3,4,5,6,7,8,9,10]

def check_even(num):
    return num % 2 == 0


filter(check_even,nums)

list(filter(check_even,nums))
-------------------------------------------------
