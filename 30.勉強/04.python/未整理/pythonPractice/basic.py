■■ 集中トレーニング
-------------------------------------------------
# sentence = "The quick brown fox jumped over the lazy dog."

リスト内に含まれているかチェック
# list_test = [2,3,4,5,6]
# 1 in list_test

文字列を デリミタ を境にリスト化
# list_test = sentence.split(" ")

数値を デリミタ を境にリスト化
# num_list = "32:59:44:44:356".split(":")

リスト要素を Join して出力
 # "-".join(colors)

 リストの要素の最後の一つを カットして出力
 # colors.pop()
 # In [106]: while colors:
 #      ...:     print("Welcome Mr." + colors.pop())
 リストの最後に要素を追加
 # colors = ["Red", "Orange", "Yellow", "Green"]
 # colors.append("Purple")
 特定の要素をリストから削除
 # colors.remove("Yellow")
 リスト(の参照先)をコピー
 # color2 = colors.copy()
 # = でアサインしても、参照先がコピーされてしまう

リストを引数として渡して、数値をソートする
 # nums = [2,6,4,0,5]
 # sorted(nums)
 # [0, 2, 4, 5, 6]
 # nums
 # [2, 6, 4, 0, 5]

リスト自体をソートする
# nums.sort()
# nums
# [0, 2, 4, 5, 6]

 文字列の特定の箇所に後付文字列挿入
 # "Hello, {0}, and welcome to {1}".format("John","Jungle")

 dictionary の Key/value を for ループ
 #  colors = { "Red": (255, 0, 0), "Yellow": (255, 255, 0), "Purple": (255, 0, 255)}
 # for (name, rgb) in colors.items(): print(name, rgb)
 リストなどの iterator を enumerate する for ループ
 # for (i, c) in enumerate(vegetable):print(i, c)
 二つの iterable をまとめて出力 for ループ
 # for (f, v) in zip(colors, vegetable):print(f, v)
 # どちらかの要素がなくなったらループが止まる

 range
 # powers = []
 # for i in range(1, 21):
 #     powers.append(2 ** i)
 上記を "List conprehension" する
 # powers = [ 2 ** i for i in range(1, 21) ]
 # for ループをしたら、(左辺？側にある処理を) i に対して実行

ネストループ
# colors = [ "Red", "Yellow", "Blue", "Pink"]
# modifiers = ["Dark", "Light", "Brownish"]
# for c in colors:
#     for m in modifiers:
#         print(m + " " + c)

while ループ
# In [86]: countdown = 10
# In [85]: while countdown >= 0:
#     ...:     print(str(countdown))
#     ...:     countdown -=1

Dictionary に Key/Value 追加
# cats = {}
# cats["Robin"] = 5
# cats["David"] = 4
# cats
# {'David': 4, 'Robin': 5}

Dictionary 初期要素定義
# cats = {"Robin":5, "Paul":3, "John":9}

値取得
# cats.get("David")
Map 内 値有無確認
# "David" in cats
Key 出力
# for name in cats:print(name)
Value 出力
# for num in cats.values():print(num)
Key と value を組み合わせた文章を出力
# for name in cats:print("{0} has {1} cat(s)".format(name, cats[name]))
Key:Value なフォーマットで出力
# for (person, num) in cats.items():print(person + ":" + str(num))
Key をもとに要素を削除
# del cats["David"]
Key をもとに要素を Pop
# cats.pop("Robin")
# cats.pop("NoExistence","defaultStr")

Dictionary を使って、ポップアップリストの html を作成
# conuntries = {"AF": "Afganistan", "JP": "Japan"}
# In [288]: for (code, country) in conuntries.items():
#      ...:     print('<option value="{0}">{1}</option>'.format(code,country))

Tuple 内の Mutable は 修正可能
# リストに似てる
# 定義後に要素を追加/修正できない
# 修正できないことを明示するため
# 固定された数の要素がある場合に使用 ex)purple = (255, 0, 255)
# パフォーマンスがいい
# grades = ([90,33],[20,32])
# grades[0][1] = 3
# ([90, 3], [20, 32])

Sets
# 順序を保持しないので、vegetable[0] とかやったらエラー
# List と違ってキーは同じデータタイプじゃないと駄目。
# vegetable = {"Asparagus", "Onion", "Carrot"}
# vegetable.pop()
# vegetable.remove("Onion")
# vegetable.add("Radish")
重複は無視される
# vegetable.add("Radish")
これはなに？
vegetable.intersection(fruits)
Set を List に変換
# list(tuple(vegetable))
frozenset に変換
 # frozen = frozenset(vegetable)
# immutable で 順序を保持しない

コマンドラインユーザー入力
# name = input("Enter your name: ")



例外処理あり
In [51]: try:
    # ...:     inp = input("Enter a number: ")
    # ...:     inp_int = int(inp)
    # 対象エラーを指定
    ...: except ValueError as e:
    ...:     inp_int = None
    ...:     print("That' not a number!")
    # エラーログも出力
    ...:     print(e)
    # ...: except NameError as e:
    # ...:     print("Something went wrong with my code, might be typo...")
    # ...:     print(e)
    ...: except:
    # ...:     print("I have no idea what went wrong...")
    ...: else:
    # ...:     print("Somehow, everything went according to plan...")
    ...: finally:
    # ...:     print("All done")
    ...:


カスタム 例外 作成
-------------------------------------------------
# In [52]: name = input("Name? ")
# Name? Robin

In [53]: if name[0] == "R": raise ValueError("I don't like you!")
-------------------------------------------------

KeyError 回避策: 対象キーがない場合はスキップする場合
# for meteor in meteor_data:
#     if not ( 'reclat' in meteor and 'reclong' in meteor): continue
#     meteor['distance'] = calc_dist(float(meteor['reclat']), float(meteor['reclong']), my_loc[0], my_loc[1])


dict_compre = list({"Robin":5, "Paul":3, "John":9})

tags = { t['Key']: t['Value'] for t in i.tags or []}
-------------------------------------------------




▼ インストール
-------------------------------------------------
https://chocolatey.org/

PowerShell を アドミンで開く

Chocolately をインストール
# Get-ExecutionPolicy
# Set-ExecutionPolicy Bypass -Scope Process
# # a で全部 YES!!
# Set-ExecutionPolicy Bypass -Scope Process -Force; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))

PowerShell をアドミンで立ち上げ直して
Python をインストール
# choco install python3 -y

choco install awscli -y

choco install git -y /GitAndUnixToolsOnPath

choco install openssh -y -param "/SSHAgentFeature"

Windows 再起動

pip install boto3

pip install ipython

インストール確認
ipython

git bash で python コマンド実行できるようにするために
# ~/.bashrc に下記追記
# alias python='winpty python.exe'

UnicodeEncodeError が .py 実行時に出る場合
CLI 上で下記実行
# export PYTHONIOENCODING=euc-jp:backslashreplace
-------------------------------------------------






Pipenv          Python 実行環境を設定する
-------------------------------------------------
# 実行環境を 追跡しつつ コードを書くことができる
#  →import するたびに、Pipfile にパッケージなどが記述される
# これをやっておけば、下記を回避できる
# パッケージを システムにインストールせずに import しようとしてエラー
# パッケージのバージョン違いで不具合
pipenv のインストール
# pip3 install pipenv

pipenv で Python3 の環境に設定
# pipenv --three
#  →Pipfile が生成される

指定した pipenv で shell /bin/bash に入る
# pipenv shell

元の環境から、pipenv 環境で python コードを走らせる
# pipenv run "python find_meteors.py"

pipenv 環境に requests をインストール
# pipenv install requests
# Pipfile が更新される

Pipfile.lock を git add 候補にならないよう設定
# .gitignore に Pipfile.lock を追記

pipenv に、開発時にのみ使うパッケージをインストール
# pipenv install -d ipython


meteors ディレクトリの find_meteors.py を import
# from meteors import find_meteors

find_meteors の calc_dist ファンクションをコール
# find_meteors.calc_dist(0,0,5,5)

-------------------------------------------------






基本性質
-------------------------------------------------

複数行入力
# ''' いくらでも
# そして終わり'''

単純出力
# print("sometext")

シングルクォーテーション 文字列を表示させる
# "" で囲む
# ''' で囲む
# エスケープ

コンストラクタ達
str()
int()
float()
list()

数字文字列 連結 出力有効化
 # print("I have " + str(5) + " apples.")
# str() で数値を囲まないと エラー

文字列の各文字をリストに格納
# list("Hello")

2 の 2 乗
# 2 ** 2

文字列をつなげる
# unko + age
文字列と掛け算
# unko * 5 + age

文字列 辞書順比較
 # "apple" < "tea"

文字列が含まれているか チェック
# "apple" in "pineapple"
-------------------------------------------------










▼ 条件式
-------------------------------------------------
list 内要素テスト
# if 3 in list_test: print("yes")

if elif else
# In [25]: if name < "Hood":
#     ...:     print("Go")
#     ...: elif name == "Robin":
#     ...:     print("Same name")
#     ...: else:
#     ...:     print("Stay")
#     ...:

String が false を返す
# name の中身が ""
# In [29]: if name:
#     ...:     print("Hi! " + name)
#     ...: else:
#     ...:     print("Who are You?")
#     ...:

リストが False
# []

not and or
# 下記は  false を返す
# not True
# True and False
# False or False
-------------------------------------------------




▼ リスト
-------------------------------------------------
リスト表示
# [1, 2, 3, 4, 5]
# [20.1, 39,2,"うんこしたい"]


リストに掛け算で複数文字列格納
# [ "one list"] + ["many"] * 4 + ["strings"]

リスト内に含まれているかチェック
# list_test = [2,3,4,5,6]
# 1 in list_test


文字列 から ３ 番目の文字を出力
# sentence = "The quick brown fox jumped over the lazy dog."
# sentence[0]

文字列の 後ろから ３ 番目を出力
 sentence[-3]

リストの要素2つめから 10 個目
 # colors[1:9]
 # ['Orange', 'Yellow', 'Green']


ただの文字列の 5 文字目から 9 文字目
 # sentence[4:9]
 # 'quick'
5 文字目以降
sentence[4:]
リスト要素 0 個め から 4 個目まで 1 つ飛ばしで抽出
 # colors[0:4:2]
 # ['Red', 'Yellow']
# colors[::2]

3 つめ以降を 全部 "Black" にする
# colors[3:] = "Black" →これだと、一文字ずつ格納される
# colors[3:] = ["Black"] * 4
String は immutable なので、上記のような変数内の文字の書き換えはできない

文字列を デリミタ を境にリスト化
# list_test = sentence.split(" ")

数値を デリミタ を境にリスト化
# num_list = "32:59:44:44:356".split(":")

リスト要素を Join して出力
 # "-".join(colors)

 リストの要素の最後の一つを カットして出力
 # colors.pop()
 # In [106]: while colors:
 #      ...:     print("Welcome Mr." + colors.pop())
 リストの最後に要素を追加
 # colors = ["Red", "Orange", "Yellow", "Green"]
 # colors.append("Purple")
 特定の要素をリストから削除
 # colors.remove("Yellow")
 リストをコピー
 # color2 = colors.copy()
 # = でアサインしても、参照先がコピーされてしまう

 文字列から特定の文字列の位置を探す
 # sentence.find("fo")

文字列をソートして、逆順にする →そして、リスト表示させる
# list(reversed(sorted("abracadabra")))
# リバース時は、list() で囲まないと、リスト表示してくれない。


リストを引数として渡して、数値をソートする
 # nums = [2,6,4,0,5]
 # sorted(nums)
 # [0, 2, 4, 5, 6]
 # nums
 # [2, 6, 4, 0, 5]

リスト自体をソートする
# nums.sort()
# nums
# [0, 2, 4, 5, 6]
-------------------------------------------------




応用
-------------------------------------------------
 文字列の特定の箇所に後付文字列挿入
 # "Hello, {0}, and welcome to {1}".format("John","Jungle")

 None の使いみち
 # true でもない、false でもない。
 # カート内の数が 0 でさえなく、 None である場合、こうする: みたいな処理がしたい時に使う
 # if my_data is None:print("Not set!")
-------------------------------------------------















▼ for ループ
-------------------------------------------------
# for color in colors:print("Is " + color + " your favorite?")

文字列、リスト、ディクショナリを for ループ
# for i in "aiueo":print(i)
# for i in ["anko", "inko", "unko", "enko"]:print(i)
# for i in {"this":3, "is":5, "a":9, "dict":9}:print(i)

dictionary の Key/value を for ループ
#  colors = { "Red": (255, 0, 0), "Yellow": (255, 255, 0), "Purple": (255, 0, 255)}
# for (name, rgb) in colors.items(): print(name, rgb)
リストなどの iterator を enumerate する for ループ
# for (i, c) in enumerate(vegetable):print(i, c)
二つの iterable をまとめて出力 for ループ
# for (f, v) in zip(colors, vegetable):print(f, v)
# どちらかの要素がなくなったらループが止まる


条件式で、break, continue, else
break: for ループを抜ける
continue: 現在の ループラウンドの、以降の処理をスキップ
# haystack = [ "hey" ] * 20
# haystack[6] = "needle"
# idx = None
# In [24]: for (i, v) in enumerate(haystack):
#     ...:     print("Looking...")
#     ...:     if v == "needle":
#     ...:       idx = i
#     ...:       print("FOUND IT!!")
    ...:       break

# In [26]: for (i, v) in enumerate(haystack):
#     ...:     print("Lookin...")
#     ...:     if v == "hey":
    ...:         continue
#     ...:     idx = i
#     ...:     print("Find it!")
#     ...:     break

# In [33]: for (i, v) in enumerate(haystack):
#     ...:     print("Looking...")
#     ...:     if v == "hey":
#     ...:         continue
#     ...:     idx = i
#     ...:     print("Found it")
#     ...:     break
    ...: else:
#     ...:     print("Couldn't find it...")

range
# powers = []
# for i in range(1, 21):
#     powers.append(2 ** i)
上記を "List conprehension" する
# powers = [ 2 ** i for i in range(1, 21) ]
# for ループをしたら、(左辺？側にある処理を) i に対して実行

ネストループ
# colors = [ "Red", "Yellow", "Blue", "Pink"]
# modifiers = ["Dark", "Light", "Brownish"]
# for c in colors:
#     for m in modifiers:
#         print(m + " " + c)

while ループ
# In [86]: countdown = 10
# In [85]: while countdown >= 0:
#     ...:     print(str(countdown))
#     ...:     countdown -=1

-------------------------------------------------















▼ Dictionary   Map 形式
-------------------------------------------------
Dictionary に Key/Value 追加
# cats = {}
# cats["Robin"] = 5
# cats["David"] = 4
# cats
# {'David': 4, 'Robin': 5}

Dictionary 初期要素定義
# cats = {"Robin":5, "Paul":3, "John":9}

値取得
# cats.get("David")
Map 内 値有無確認
# "David" in cats
Key 出力
# for name in cats:print(name)
Value 出力
# for num in cats.values():print(num)
Key と value を組み合わせた文章を出力
# for name in cats:print("{0} has {1} cat(s)".format(name, cats[name]))
Key:Value なフォーマットで出力
# for (person, num) in cats.items():print(person + ":" + str(num))
Key をもとに要素を削除
# del cats["David"]
Key をもとに要素を Pop
# cats.pop("Robin")
# cats.pop("NoExistence","defaultStr")

Dictionary を使って、ポップアップリストの html を作成
# conuntries = {"AF": "Afganistan", "JP": "Japan"}
# In [288]: for (code, country) in conuntries.items():
#      ...:     print('<option value="{0}">{1}</option>'.format(code,country))

天気文字列と画像との辞書を作成しておいて、天気文字列で画像を出力する感じ
# weather = {"Rainy": "rain.png", "Cloudy": "clouds.png"}
# cond = "Cloudy"
# weather[cond]
# thumbnail = weather.get(cond)
# thumbnail
ユーザーID をキーに、{名前Key/value と Email Key/value} をネスト
# users = {1:{"name": "Joe", "email": "joe@example.com"}, 2:{"name":"Sarah","email": "sara@example.com"}}
# In [306]: users
# Out[306]:
# {1: {'email': 'joe@example.com', 'name': 'Joe'},
#  2: {'email': 'sara@example.com', 'name': 'Sarah'}}



バイナリデータ。主に映像、画像に使われる
バイナリデータとしての文字列を、変数にアサイン
# greeting = b"unko"
# print(greeting)
変数内の各文字を、バイナリデータとして出力
# for c in greeting:print(c
バイトアレイを宣言
# data = bytearray()
バイトアレイにバイナリデータの文字列を格納
 # data += b"Hi!, how are you?"
 # bytearray(b'Hi!, how are you?')
 バイナリアレイのバイナリデータとしての一文字を変更
 # data[0] = 120
 # bytes() でこれをやるとエラー
 # bytearray(b'xi!, how are you?')


Tupol
-------------------------------------------------
# リストに似てる
# 定義後に要素を追加/修正できない
# 修正できないことを明示するため
# 固定された数の要素がある場合に使用 ex)purple = (255, 0, 255)
# パフォーマンスがいい
# fruits = ("Apple", "Orange", "Lemon", "Tomoto")
# fruits[0]
# for f in fruits: print("Would you like a nice " + f + "?")
Tuple をリスト化
# list(fruits)
Tuple 内の Mutable は 修正可能
# grades = ([90,33],[20,32])
# grades[0][1] = 3
# ([90, 3], [20, 32])
-------------------------------------------------






Sets
# 順序を保持しないので、vegetable[0] とかやったらエラー
# List と違ってキーは同じデータタイプじゃないと駄目。
# vegetable = {"Asparagus", "Onion", "Carrot"}
# vegetable.pop()
# vegetable.remove("Onion")
# vegetable.add("Radish")
重複は無視される
# vegetable.add("Radish")
これはなに？
vegetable.intersection(fruits)
Set を List に変換
# list(tuple(vegetable))
frozenset に変換
 # frozen = frozenset(vegetable)
# immutable で 順序を保持しない

コマンドラインユーザー入力
# name = input("Enter your name: ")

変数型表示
# type(name)

例外処理
-------------------------------------------------
例外処理なし
isdigit() は " 4 " を数値として認識しない。 int(" 4 ") は数値になるのに。
で、下記はその例外をそのまま受け入れてしまっている
# inp = input("Enter a num: ")
#
# In [30]: if not inp.isdigit():
#     ...:     print("Give me a number...")
#     ...: else:
#     ...:     product = int(inp) * 7
#     ...:     print("Seven times {0} is {1}".format(inp,product))
#     ...:
# Seven times 3 is 21

例外処理あり
In [51]: try:
    # ...:     inp = input("Enter a number: ")
    # ...:     inp_int = int(inp)
    # 対象エラーを指定
    ...: except ValueError as e:
    ...:     inp_int = None
    ...:     print("That' not a number!")
    # エラーログも出力
    ...:     print(e)
    # ...: except NameError as e:
    # ...:     print("Something went wrong with my code, might be typo...")
    # ...:     print(e)
    ...: except:
    # ...:     print("I have no idea what went wrong...")
    ...: else:
    # ...:     print("Somehow, everything went according to plan...")
    ...: finally:
    # ...:     print("All done")
    ...:


カスタム 例外 作成
-------------------------------------------------
# In [52]: name = input("Name? ")
# Name? Robin

In [53]: if name[0] == "R": raise ValueError("I don't like you!")
-------------------------------------------------


ファイルの読み込み
-------------------------------------------------ー
with
# コンテキスト マネージャ
# 他のブロックと組み合わせて使う。
# open ブロックと組み合わせた場合、現状 close 状態であることを確認した上で
# open ブロックを走らせる

In [97]: with open("words.txt") as input_file:
    ...:     for line in input_file:
    ...:         line = line.strip()
    ...:         if len(line) < 4: continue
    ...:         if line[0] == "M" and line[3] == "r" :print(line)
-------------------------------------------------

行末の改行やホワイトスペースを削除
# .strip()

ファイルの書き込み
-------------------------------------------------
# open() に "w" フラグ
# .write() ファンクション

# In [107]: with open("a.txt", "w") as output_file:
#      ...:     for word in words:
#      ...:         output_file.write(word + "\n")
-------------------------------------------------
ファイルに追記
-------------------------------------------------
# open() に a フラグ
# In [107]: with open("a.txt", "a") as output_file:
#      ...:     for word in words:
#      ...:         output_file.write(word + "\n")
-------------------------------------------------

ファイルからバイナリ読み込み
# with open("somefile.zip", "rb") as input_file:
#     data = input_file.read()

ファイルにバイナリ書き込み
# with open("other.zip", "wb") as output_file:
#     output_file.write(data)
-------------------------------------------------


Module を import するということ
-------------------------------------------------
random モジュールをインポート
# import random

random でトランプづくり
要素と入れ物準備
# In [128]: suits = ('hearts', 'diamonds', 'clubs', 'spades')
# In [129]: values = range(1, 14)
# In [131]: cards = []
カード組み合わせ
# In [138]: for s in suits:
#      ...:     for v in values:
#      ...:         cards.append("{0} of {1}".format(v, s))
random.choice(cards)
random.shuffle(cards)

random の shuffle だけ import して使う
# from random import shuffle
# shuffle(cards)

import したモジュールに エイリアス をつけて呼び出す
# import itertools as iter

二つのリストを一緒に出力
# for num in iter.chain([1, 2, 3], [4, 5, 6]):
#     print(num)

3つの要素の組み合わせを出力
# for (a, b, c) in iter.combinations(("Peanut butter", "Jelly", "Mayo", "ketchup", "lettuce"), 3):
#     print("How about a nice {0}, {1}, and {2} sandwich?".format(a, b, c))





▼ Python インタープリタが ソースファイル(AKA モジュール)を読み込む時の流れ
-------------------------------------------------
1.モジュール内のすべてのコードを実行する
2.インタープリターがコードを実行する前に、特別な変数を定義する
# ・対象モジュールをメインプログラムとして実行する時
#  →変数 __name__ に __main__ の値を格納する。
# ・このモジュールが他のモジュールから import された時、
# __name__ はモジュール名としてセットされる
3.import を実行し、モジュールをロード
4.レベル 0 でインデントされたコードを実行
5.def ブロックを評価
 # →ファンクション OBJ を作成 →変数 some_function を生成（ファンクション OBJ をポイント）
-------------------------------------------------




Python における main() ファンクション
# インデント トップレベル のすべてのコード

__name__
# 自身のモジュール名を格納する特別な変数。
# 外部から import された時は、import 時のモジュール名(iow somefile.py のファイル名 somefile)が格納され、
# 直接モジュールを実行した時は、__main__ がモジュール名として格納される

if __name__ == '__main__':   メインチェック のやってること
-------------------------------------------------
# このモジュールが直接実行か、インポートされたものかが判断される
# で、直接実行時のみ、続くブロックを実行





■■ ipython
-------------------------------------------------

変数一覧
# In [35]: %who

変数の詳細一覧
# In [34]: %whos

変数をリストとして出力
# In [36]: %who_ls

名前空間リセット
# In [37]: %reset
-------------------------------------------------
変数をストアに格納
# a = 'stored string'
# store a
ストア内容表示
# store

変数変更後、ストアから内容復元
# In [8]: a = 'uwagaki'
# In [10]: store -r a

ストアからファイルに書き込み
# In [19]: store a > a.txt

ファイルに追記する
# In [21]: store a >> a.txt

保存しているデータを指定削除
# In [25]: store -d a

保存しているデータを全て削除する
# store -z

-------------------------------------------------
変数/オブジェクト/コマンド の情報を Doc つきで出力
# someObjOrVariable?

ファイルの中身を表示
# pfile sometext.txt

チートシート的なものが見れる
# quickref
-------------------------------------------------



実行時間を計測
# 関数やらモジュールやら
-------------------------------------------------
# In [1]: l = range(100000)
#
# In [2]: %timeit [i for i in l]
# 100 loops, best of 3: 8.28 ms per loop

ループ回数指定。指定無しだと適当な回数を割り当てられる。
# -n
-nで指定したループを何回繰り返すか。デフォルト3回。
# -r
時間の表示桁数。デフォルト3桁。
# -p<P>
# In [3]: %timeit -n200 -r5 -p5 [i for i in l]
# 200 loops, best of 5: 8.3864 ms per loop
-------------------------------------------------
# かっこ省略で実行
In [23]: sample 1, 2
-------> sample(1, 2)

# さらに引数の「,」省略で実行
In [24]: /sample 1 2
-------> sample(1,2)

# 引数を文字列に変換して実行
In [25]: ,sample 1 2
-------> sample("1", "2")
-------------------------------------------------







iPython を起動中にモジュールを再読み込み
-------------------------------------------------
import Sample っぽいことをした場合
# In [1]: l = range(2000)
#
# In [2]: %timeit [i for i in l]
# 54.7 µs ± 1.14 µs per loop (mean ± std. dev. of 7 runs, 10000 loops each)
µs ± が git だと文字化けしてしまう

import 下モジュールの中でさらに import してるモジュールも reload
# In [18]: dreload Sample
# -------> dreload(Sample)
# Reloading Sample
# Reloading random
-------------------------------------------------



pip や Python3 が認識されない時
-------------------------------------------------
easy_install pip

easy_install --upgrade pip

環境変数設定場所
# コンパネー＞システムー＞システム詳細設定ー＞環境変数

変数 Path を編集する
-------------------------------------------------
