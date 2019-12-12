# ループのスコープから抜け出す break


```py
while True:
  print('Hi!')
  name = input()
  if name == 'your name':
    break

print('Thanks')
```


# 正しい入力があるまでなんどでも最初から continue

```py
while True:
  print('Hi!')
  name = input()
  if name != 'Joe':
    continue

  print('Hi, Joe! Enter password.')

  password = input()

  if password == 'fish':
    break
print('Thanks')
```
