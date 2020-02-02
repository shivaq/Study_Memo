# NamedTupe の定義とデフォルト値とインスタンス化


```py
from typing import NamedTuple

class PathContainer(NamedTuple):
    root: str = "default_root_path"
    document: str = "default_document_path"
    exe: str = "default_exe_path"

path_container = PathContainer("root_path","document_path","exe_path")
```
