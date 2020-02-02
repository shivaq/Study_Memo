## どの DB を使うかスイッチできる関数

```py
_tasksdb = None

def start_tasks_db(db_path, db_type):  # type: (str, str) -> None
    """Connect API functions to a db."""

    if not isinstance(db_path, string_types):
        raise TypeError('db_path must be a string')
    global _tasksdb

    # DB タイプによって分岐
    if db_type == 'tiny':
        import tasks.tasksdb_tinydb
        _tasksdb = tasks.tasksdb_tinydb.start_tasks_db(db_path)

    elif db_type == 'mongo':
        import tasks.tasksdb_pymongo
        _tasksdb = tasks.tasksdb_pymongo.start_tasks_db(db_path)
    else:
        raise ValueError("db_type must be a 'tiny' or 'mongo'")

```


## 上記関数のテスト
* setup として、リストで渡した DB タイプのそれぞれを用意


```py
# セッションスコープ。パラメータは直書き指定？
#@pytest.fixture(scope='session', params=['tiny',])
@pytest.fixture(scope='session', params=['tiny', 'mongo'])
def tasks_db_session(tmpdir_factory, request):
    """Connect to db before tests, disconnect after."""

    temp_dir = tmpdir_factory.mktemp('temp')
    # start_tasks_db を呼び出して、パスは  ビルトイン fixture。
    # パラメータは、tiny だとか mongo だとか指定せずに、リストを渡す
    tasks.start_tasks_db(str(temp_dir), request.param)
    yield  # this is where the testing happens
    tasks.stop_tasks_db()


@pytest.fixture()
def tasks_db(tasks_db_session):
    """An empty tasks db."""
    tasks.delete_all()
```
