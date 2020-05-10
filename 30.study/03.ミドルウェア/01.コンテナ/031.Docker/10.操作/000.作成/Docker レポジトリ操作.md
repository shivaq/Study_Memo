# レポジトリ

## レポジトリログイン
docker login


## ローカルレポジトリ作成
docker image tag myhello YOUR_DOCKER_ID/myhello





## リモートの Docker Hub にプッシュ
docker image push shivaq/myhello





## Docker Hub のにあるイメージをソースに、コンテナ実行
docker container run -p 9999:8888 shivaq/myhello







## DockerHub アカウントとGitHubアカウントとを接続
* 接続方法は、ググりましょう。

### Automated Builds
* GitHub レポジトリにプッシュすると、Imageのビルドがトリガーされ、新しいImageが出来上がる。
