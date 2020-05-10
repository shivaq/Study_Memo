# プロジェクトの構造は超大事

## サンプルレポジトリ

[Structuring Your Project](https://docs.python-guide.org/writing/structure/)
[ベースとなるレポジトリ](https://github.com/navdeep-G/samplemod)

## ライセンス
[どのライセンスが適切かな？](choosealicense.com.)


# アンダースコア _ をモジュール名に入れるのはよくない

- _ は使わず、submodule を使うのが良い

```py
# OK
import library.plugin.foo
# not OK
import library.foo_plugin
```