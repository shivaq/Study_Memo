# %%writefile は、ipython のマジックコマンド。セルのコンテンツをファイルに書き込む。

# 0 から 引数の値までの数値のうち、偶数の数値を生成
def createEvenNum(x):
    return [num for num in range(x) if num%2==0]

# 生成された数値は List 形式になる
evenNumList = createEvenNum(15)
