


■■■■■■■■■■■■■■■■■■■■■■■■■■ Decorator
▼ Decoratorとは
・関数やクラスを modify することができるCallable object
・Callable object とは、 引数を受けて、オブジェクトを返すオブジェクト。
・関数と Class はCallable object

▼ Function decorators とは
・関数の参照を引数として受ける
・受けた関数の参照に、wrapper を付与する
・wrapper 付きの関数を返り値として返す。
-------------------------------------------------
import inspect

# 関数をパラメータとして受け付ける
def decorator_func(some_func):

  # 関数内で定義する関数にて、引数関数を modify する
  def wrapper_func():
    print("Wrapper function started")

    some_func()

    print("Wrapper function ended")

  # 結果、引数を modify し、返す
  return wrapper_func

# 適当な関数
def say_hello():
  print ("Hello")

# 引数は関数。decorator_func でラップし、変数に格納
say_hello = decorator_func(say_hello)

print inspect.getsource(say_hello)


# say_hello をコールすると、結果、同名変数に格納されたラップ関数がコールされる。
# つまり、デコレータは既存の関数 say_hello を modify したことになる。
say_hello()

# Output:
#  Wrapper function started
#  Hello
#  Wrapper function ended
-------------------------------------------------


▼ デコレータの Syntax
-------------------------------------------------
# decorator_func という関数の引数に、デコレートされた側の関数を渡す。
# 結果、該当関数がデコレータによってmodifyされたかたちで返り値を返す
@decorator_func
def say_hell():
    print 'Hello'
-------------------------------------------------
# 実際やっていることは下記のとおり
def say_hello():
    print 'Hello'

say_hello = deocrator_func(say_hello)
-------------------------------------------------




▼ デコレータがパラメータを受け入れる場合、引数が同じでなければならない
-------------------------------------------------
import inspect

# 引数を最大2つ受けて、それをもとにデコレート
def decorator_func(say_hello_func):
  def wrapper_func(hello_var, world_var):
    hello = "Hello, "
    world = "World"

    if not hello_var:
      hello_var = hello

    if not world_var:
      world_var = world

    return say_hello_func(hello_var, world_var)

  return wrapper_func

@decorator_func
def say_hello(hello_var, world_var):
  print hello_var + " " + world_var

# デコレートした結果、どんなソースになっているかを出力
print inspect.getsource(say_hello)

# デコレーターのラッパー関数とパラメータの数が同じでないといかん
say_hello("Hello", "")
-------------------------------------------------



▼
-------------------------------------------------
import inspect

def decorator_wrapper(parameter):
    print parameter

    def decorator(func):
        def wrapper(message):
            print "Wrapper start"
            func(message)
            print "Wrapper end"

        return wrapper

    return decorator


# Here, instead of having the decorator function object as in prevision cases,
# we are executing the decorator_wrapper function using the round brackets which returns the
# decorator function. So ultimately the code changes to
'''
decorator = decorator_wrapper("Decorator paramerter")
@decorator
def say_hello(message):
    print message
'''
@decorator_wrapper("Decorator parameter")
def say_hello(message):
    print message


print inspect.getsource(say_hello)
'''
    def wrapper(message):
        print "Wrapper start"
        func(message)
        print "Wrapper end"
'''

say_hello("Hello, world")
-------------------------------------------------
