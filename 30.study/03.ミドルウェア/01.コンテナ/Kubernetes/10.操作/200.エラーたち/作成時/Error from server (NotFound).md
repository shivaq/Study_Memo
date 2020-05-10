
## Error from server (NotFound)
`Error from server (NotFound): replicationcontrollers "kubia" not found`
### 原因

- テキストにあったこのコマンドは、Replication Controller を公開するためのものだが、現在は Deployment を使うことが推奨されているからか、クラスタ作成時に、Deployment のほうが作成されていた。replicationcontrollers は文字通り、作成されていなかったから見つからなかったということ
```
kubectl expose rc kubia --type=LoadBalancer --name kubia-http
```