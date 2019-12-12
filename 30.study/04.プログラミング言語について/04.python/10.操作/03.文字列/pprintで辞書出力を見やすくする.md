# 辞書の値が、改行されて出力される

```py
import pprint

message = 'It was a bright cold day in April, and the clocks were striking thirteen.'
count = {}


for character in message:
  count.setdefault(character, 0)
  count[character] = count[character] + 1



pprint.pprint(count)
```




```py
# 出力

{' ': 13,
 ',': 1,
  '.': 1}

```




# pformat  → pprint の値をファイルに格納


```py
pprint.pprint(someDictionaryValue)
print(pprint.pformat(someDictionaryValue))
```
