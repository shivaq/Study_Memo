


```py
try: # 例外処理が起こり得るコードを try ブロックに配置
    f = open('testfile','w')
    f.write('Test write this')
except IOError: # 例外処理は except ブロックに配置
    # IOError のみチェック
    print("Error: Could not find file or read data")
else:
    print("Content written successfully")
    f.close()
```







# 例外発生時も必ず走らせたいコードを配置



* finally がある場合、break, continue は finally 評価後にしか評価されない

* 下記は、finally で実行してほしいコードを配置しているとは思えない

```py
def askint():
    # while ループを使わないと、チェックは一度だけになる
    while True:
        try:
            val = int(input("Please enter an integer: "))
        except:
            print("Looks like you did not enter an integer!")
            # continue のおかげで、このブロックに来たら、あらためて try ブロックに戻る
            continue



        else:
            print("Yep that's an integer!")
            break




        finally: # finally がある場合、break, continue は finally 評価後にしか評価されない
            print("Finally, I executed!")
        print(val) # この print は決して評価されない
```
