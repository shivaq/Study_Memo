


```py
def spam():
  global eggs
  eggs = 'spam'

eggs = 'global'
spam()
print(eggs)
```

<!-- 出力 は global ではなく。。。-->
spam
