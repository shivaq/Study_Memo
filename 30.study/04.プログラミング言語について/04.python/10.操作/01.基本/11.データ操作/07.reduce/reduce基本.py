■■■■■■■■■■■■■■■■■■■■■■■■■■
Initially, the function is called with the first two items from the sequence and the result is returned.
The function is then called again with the result obtained in step 1 and the next value in the sequence. This process keeps repeating until there are items in the sequence.

1.シークエンスの第一、第二要素を引数に、 function が処理される
# sequence は、値が連なったかたちの データ型？
2.function が再度呼ばれ、ステップ1の返り値と、シークエンスの次の要素を引数に、処理を実行する
・上記プロセスはシークエンスの要素がなくなるまで続けられる

-------------------------------------------------

syntax
reduce(function, sequence[, initial]) -> value

例
-------------------------------------------------
>>> from functools import reduce

>>> def do_sum(x1, x2): return x1 + x2

    # (((1 + 2) + 3) + 4) => 10 を行っている
>>> reduce(do_sum, [1, 2, 3, 4])
10
>>>
