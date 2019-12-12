■■■■■■■■■■■■■■■■■■■■■■■■■■ 関数の特性を知る
▼ 関数は変数に格納可能
-------------------------------------------------
def say_hello():
  print("Hello")
-------------------------------------------------
say_hello2 = say_hello

say_hello2() # Output: Hello

# Check if say_hello and say_hello2 are same
print(say_hello2 is say_hello) # Output: True which means they point at the same location
-------------------------------------------------


▼ 関数は他の関数の引数に渡すことが可能
-------------------------------------------------
def say_hello(say_hi_func):
  print("Hello")
  say_hi_func()

def say_hi():
  print("Hi")

say_hello(say_hi)

#Output:
  # Hello
  # Hi
-------------------------------------------------



▼ 関数内で関数を定義可能
-------------------------------------------------

def say_hello():
  print("Hello")

  def say_hi():
    print("Hi")

  say_hi()

say_hello()
# Output: Hello
#         Hi

# say_hi not available outside the scope of say_hello
say_hi() # Gives error
-------------------------------------------------


▼ 他の関数への参照を、返り値として返す
-------------------------------------------------
def say_hello():
  print("Hello")
  def say_hi():
    print("Hi")
  return say_hi

# say_hi() への参照が、変数に格納される
say_hi_func = say_hello()

say_hi_func を呼び出すと、say_hi() を呼び出す。
-------------------------------------------------

▼ closure
-------------------------------------------------
hello_var は say_hello 関数の外の say_hi からアクセス可能。
なぜなら、say_hi 関数が say_hello の内部で定義されているため、
say_hi は say_hello のすべての変数にアクセスできるから。
-------------------------------------------------
# hello_var を出力した上で、say_hi関数への参照を返り値として返す
def say_hello(hello_var):
  print(hello_var)

  def say_hi(hi_var):
    print(hello_var + " " + hi_var)

  return say_hi

# Print Hello and returns say_hi function which gets stored in say_hi_func variable
# 格納時にprint() が実行される。Hello が hello_var として格納されている
say_hi_func = say_hello("Hello")

# say_hi 関数への参照が格納されているため、"Hello Hi"と出力される
say_hi_func("Hi")
-------------------------------------------------
