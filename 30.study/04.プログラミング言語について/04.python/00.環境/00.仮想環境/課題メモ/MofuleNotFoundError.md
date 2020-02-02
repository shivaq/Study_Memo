#

```py
ImportError while importing test module '/Users/yasuakishibata/PycharmProjects/operate_cfn/operate_cfn/tests/unit/test_util_string.py'.
Hint: make sure your test modules/packages have valid Python names.
Traceback:
tests/unit/test_util_string.py:1: in <module>
    from tests.context import util_string
tests/context.py:1: in <module>
    from src import util_string, set_mfa
src/set_mfa.py:8: in <module>
    import util_string
E   ModuleNotFoundError: No module named 'util_string'
```



# context.py

```py
from src import util_string, set_mfa
import os
import sys
sys.path.insert(0, os.path.abspath(
    os.path.join(os.path.dirname(__file__), '..')))

```






# __init__.py は各ディレクトリにある









## setup.py

```py
from setuptools import setup, find_packages

setup(
    name="set_mfa",
    packages=find_packages()
)

```





## test ファイル

```py
from tests.context import util_string

def test_single_quoted_string_strip_them():
    single_quoted = util_string.remove_quotes("'Yes?")
    assert single_quoted == "Yes?"


def test_double_quoted_string_strip_them():
    double_quoted = util_string.remove_quotes("\"Yes?\"")
    assert double_quoted == "Yes?"

```







## 試してみたこと
-------------------------------------------------
# 対象モジュールから、見つけられないモジュールの import をコメントアウト
 →うまくいった

# 関数では使わないが、モジュールインポートだけしてみる
 →ModuleNotFoundError

# context.py に見つけられないモジュール を追記
 →→ModuleNotFoundError


## うまくいった
-------------------------------------------------
### 同一ディレクトリのモジュールインポート方法を変えた
from . import refferenced_mod

結果、成功。
しかし、下記 pylint メッセージが表示される
{
	"resource": "/Users/yasuakishibata/PycharmProjects/pipenv_test/pytest_pkg/src/sub_dir/my_function.py",
	"owner": "python",
	"code": "relative-beyond-top-level",
	"severity": 8,
	"message": "Attempted relative import beyond top-level package",
	"source": "pylint",
	"startLineNumber": 5,
	"startColumn": 1,
	"endLineNumber": 5,
	"endColumn": 1
}
