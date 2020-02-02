# マッチした文字列を置換

$1: この値にすり替える, $2 target value
sub()

```py

>>> namesRegex = re.compile(r'Agent \w+')



>>> namesRegex.sub('CENSORED', 'Agent Alice gave the secret documents to Agent Bob.') 'CENSORED gave the secret documents to CENSORED.'
```










# マッチした値の一部を、置換後の値に流用



マッチしたグループの1つ目をすり替え後の値とする それ以降は aiueo

\1aiueo

マッチしたグループの2つ目を‥

\2aiueo

```py
# Group1 は (\w), Group2 は \w*
>>> agentNamesRegex = re.compile(r'Agent (\w)\w*')




>>> agentNamesRegex.sub(r'\1****', 'Agent Alice told Agent Carol that Agent Eve knew Agent Bob was a double agent.')
A**** told C**** that E**** knew B**** was a double agent.'
```
