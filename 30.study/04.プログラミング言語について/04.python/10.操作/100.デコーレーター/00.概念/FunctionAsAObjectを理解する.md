
<!-- TOC -->

- [1. 関数も オブジェクト。](#1-関数も-オブジェクト)
    - [1.1. 関数を引数として渡す](#11-関数を引数として渡す)
- [2. 関数を返り値として返す](#2-関数を返り値として返す)
    - [2.1. 親関数の状態を子関数が扱う](#21-親関数の状態を子関数が扱う)
    - [2.2. pre-configured behaviors factory](#22-pre-configured-behaviors-factory)

<!-- /TOC -->

# 1. 関数も オブジェクト。

## 1.1. 関数を引数として渡す

```py
def yell(text):
    return text.upper() + "!"


def whisper(text):
    return text.lower() + '...'


def greet(func):
    """関数はOBJ。引数として渡せる"""
    greeting = func('Hi, I am a Python program')
    print(greeting)


if __name__ == "__main__":
    """
    アウトプット
    HI, I AM A PYTHON PROGRAM!
    hi, i am a python program...
    """
    # pass func as an argument
    greet(yell)
    greet(whisper)
```






# 2. 関数を返り値として返す

```py
def greet(func):
    greeting = func('Hi, I am a Python program')
    print(greeting)


def get_speak_func(volume):
    """
    Nest してある関数を 返り値として返す
    """
    def whisper(text):
        return text.lower() + "..."

    def yell(text):
        return text.upper() + "!"
    if volume > 0.5:
        return yell
    else:
        return whisper


if __name__ == "__main__":
    """
    アウトプット

    HI, I AM A PYTHON PROGRAM!
    hi, i am a python program...
    """
    # function to get a function
    greet(get_speak_func(0.7))
    greet(get_speak_func(0.2))
```





## 2.1. 親関数の状態を子関数が扱う

```py
def get_speak_func2(text, volume):
    """
    子関数が親関数の状態を扱うことができる
    """
    def whisper():
        return text.lower() + "..."

    def yell():
        return text.upper() + "!"

    if volume > 0.5:
        # ここで yell が返される段階で、
        # yell 定義時に参照した text の状態を保持している
        return yell
    else:
        return whisper


if __name__ == "__main__":
    """
    アウトプット

    papipupepo...
    PAPIPUPEPO!
    """
    # papipupepo を子関数が変更を加えて返す£
    print(get_speak_func2("papipupepo", 0.3)())
    print(get_speak_func2("papipupepo", 0.9)())

```



## 2.2. pre-configured behaviors factory

```py
def make_adder(n):
    """
    pre-configured behaviors factory function

    「どのように振る舞うか」を設定できる関数
    Product としての 振る舞い設定済みの関数を オブジェクト として扱うことで

    なんか色々使えそう

    例) 引数に n(例えば3) を足して返す関数を Product として生成する
    """
    def add(x):
        return x + n
    return add


class Adder:
    """
    pre-configured behaviors factory class
    """

    # Adder(3) →self.n = 3
    def __init__(self, n):
        self.n = n

    # plus_3 = Adder(3)
    # plus_3(7)
    #  →7 が x にあたる
    # 3 + 7 を返す
    def __call__(self, x):
        return self.n + x

if __name__ == "__main__":
    """
    アウトプット

   10
   90
    """
    plus_9 = make_adder(9)
    plus_3 = Adder(3)

    print(plus_3(7))
    print(plus_9(81))
```
