# ▼ レポジトリ
## ローカルレポジトリ作成
* 命名規則は youruser/yourimage





```sh
docker image tag web1 shivaq/web1:latest
```





```sh
[ec2-user@ip-10-0-100-224 03-creating-a-dockerfile-part-1]$ docker image ls
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
web1                latest              bc5d967e2c36        15 hours ago        69.4MB
shivaq/web1         latest              bc5d967e2c36        15 hours ago        69.4MB
```





## リモートの Docker Hub にプッシュ
```sh
docker image push shivaq/web1:latest
```
```sh
docker push jamtur01/static_web
```







## Pull
* virtualenv を使っている場合、activate した上でないと失敗した
```sh
docker image pull shivaq/web1:latest
```





## DockerHub アカウントとGitHubアカウントとを接続
* 接続方法は、ググりましょう。

### Automated Builds
* GitHub レポジトリにプッシュすると、Imageのビルドがトリガーされ、新しいImageが出来上がる。
