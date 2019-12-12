from flask import Flask
from flask_redis import FlaskRedis

# Flask() の引数は、アプリにどのモジュールを使うかを渡す。
# Flask はそれを、ファイルシステム内のリソースを探すのに利用したりする
# モジュールが一つの場合、__name__ を使う。
app = Flask(__name__)
app.config['REDIS_URL'] = 'redis://redis:6379/0'

redis = FlaskRedis(app)

# @app.route('/') →ルートのアドレスに、以下のものを設置する
@app.route('/')
def counter():
    return str(redis.incr('web2_counter'))
