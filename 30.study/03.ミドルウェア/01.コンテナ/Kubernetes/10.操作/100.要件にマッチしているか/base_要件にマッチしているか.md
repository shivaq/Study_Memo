
# Conformance(適合、準拠) Checking
- 自前で作ったクラスタの場合、これを最初に行ったほうが良い
[sonobuoy](https://github.com/vmware-tanzu/sonobuoy)
- Kubernetes がどのクラウドプロバイダでも、どうように動作すること。それを確認したい
- Kubernetes テストスイートを使って、対象となる Kubernetes のバージョンが、コアとなる要件を満たすかどうかを確認する
- 確認ができたら、その、Kubernetes ように設計したアプリは他の場所でも機能すると、確信が持てる




# ▼ [K8Guard](https://k8guard.github.io/)
- 問題を検知し、やるべきアクションを教えてくれる
- ポリシーをカスタマイズできる
- コンテナのイメージが指定したサイズより大きい場合
- インバウンドルールがゆるい場合
- K8Guard をクラスタ上で running 状態にしておき、ポリシーハズレを検知したときにASAP通知するようにするのがおすすめ
## メトリクスをエクスポート
- プロメテウスなどにエクスポートできる
- Policy から外れている Deployment の数
- Kubernetes API レスポンスのパフォーマンスなど



# ▼ [Copper](https://copper.sh/)
- デプロイ前にManifest をチェックするツール
- よくある問題にフラグをつける
- カスタムポリシーを強いることができる
- Domain- Specific Language (DSL) で、validation ルールとポリシーを表現する


It includes a Domain- Specific Language (DSL) for expressing validation rules and policies.
For example, here is a rule expressed in the Copper language that 



blocks any con‐ tainer using the latest tag (see “The latest Tag” on page 138 for why this is a bad idea):

## latest タグがついたコンテナの使用をブロックする場合

- Copper ファイル (no_latest.cop)を用意
```js
rule NoLatest ensure {
    fetch("$.spec.template.spec.containers..image")
        .as(:image)
        .pick(:tag)
        .contains("latest") == false
}
```
- チェック対象 マニフェストに対してチェックを実行
- コミット時にチェックが実行されるように仕込むとよい

```sh
copper check --rules  --files deployment.yml
# latest がある場合、FAIL となる
Validating part 0
NoLatest - FAIL
```

# ▼ [kube-bench](https://github.com/aquasecurity/kube-bench)
- the Center for Internet Security (CIS) のセキュリティベストプラクティスに従って、クラスターを監査するツール
- kube-bench を走らせるテストを設定することも可能



# ▼ [Kubernetes Audit Logging](https://kubernetes.io/docs/tasks/debug-application-cluster/audit/)
- 問題のあるクラスタを発見したとする。知らないPodがあるとか。どこから来たPodなのか知りたいとする。
- 誰がいつそれを作ったのか、ログ出力してくれる
- クラウドサービスに、こういったロギング機能がない場合、使うと良い


