▼ ImageをDockerHub にプッシュ
-------------------------------------------------
docker push jamtur01/static_web

備考
・命名規則は youruser/yourimage
-------------------------------------------------

▼ Automated Builds
Dockerfile がある GitHub レポジトリと DockerHub とを接続する
上記レポジトリにプッシュすると、Imageのビルドがトリガーされ、新しいImageが出来上がる。

・やり方
DockerハブアカウントとGitHubアカウントとを接続する
あとはググりましょう。
