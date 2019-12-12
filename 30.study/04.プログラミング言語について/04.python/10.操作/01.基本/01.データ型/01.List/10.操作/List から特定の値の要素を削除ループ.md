# for ループは、List の要素の 追跡 ができない

* List 要素の処理は、while ループを使いましょう








# List から特定の値の要素を削除ループ


```py
pets = ['dog', 'cat', 'dog', 'goldfish', 'cat', 'rabbit', 'cat']
print(pets)

while 'cat' in pets:
    pets.remove('cat')

print(pets)
```
