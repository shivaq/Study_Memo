# reduce の挙動
1. 関数が呼ばれる
2. シークエンスの 第一、第二アイテム が取得される
2. 関数の処理がなされる
3. 返り値が返される
4. 関数が呼ばれる(再び)
5. 返り値と、次のアイテムが取得される
6. 関数の処理がなされる
7. 上記が、シークエンスのアイテムが無くなるまで繰り返される






# syntax
`reduce(function, sequence[, initial]) -> value`


```py
from functools import reduce

def do_sum(x1, x2): return x1 + x2

    # (((1 + 2) + 3) + 4) => 10 を行っている
reduce(do_sum, [1, 2, 3, 4]) # 10
```
