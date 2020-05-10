# スイッチロールを aws CLI で行うために必要なこと


## AWS_PROFILE 環境変数が無視される
https://github.com/aws/aws-cli/issues/3304

```
@pikeas While it is common in many tools for environment variables that map to CLI arguments to have exactly the same behavior, this isn't the pattern the AWS CLI has. In general, we treat explicit arguments to the CLI with a higher priority than settings in the environment. In this case, setting credentials via environment variables results in the profile set in the environment being ignored entirely (not just the credentials from it).

This interaction between AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY, and AWS_PROFILE was defined before assume role configuration was added. For backwards compatibility reasons we can't change the way those three environment variables interact.

This is unfortunate as @SunMar's use case seems reasonable to me, it just isn't possible for legacy reasons.
```

### 解決策
`alias aws='aws --profile ${AWS_PROFILE:-""}'`
または
AWS_ACCESS_KEY_ID
AWS_SECRET_ACCESS_KEY
をセットしない

## ~/.aws/config ファイルでロールのプロファイルを定義

```ini
[profile marketingadmin]
role_arn = arn:aws:iam::123456789012:role/marketingadminrole
# source_profile の認証情報を使ってロールを使う
source_profile = user1
```

## assume-role をするために必須

- ソースプロファイルのユーザーは、指定されたプロファイルのロール用の sts:assume-role を呼び出すアクセス許可を持っている必要があり

- ソースプロファイルのユーザーがこのロールを使用できる信頼関係


## ロールの使い方
- 現在のアクセス権限で特定の IAM ロールを引き受けることができるなら、AWS CLI 設定ファイルの「プロファイル」でそのロールを特定できます。
- `--profile marketingadmin` みたく、引数で指定
```bash
aws s3 ls --profile marketingadmin
```
- 環境変数 `AWS_PROFILE` でデフォルトのプロファイルを指定
```bash
export AWS_PROFILE=marketingadmin
```

## どのようにロールが認証を行っているか
- CLI は、sts:AssumeRole オペレーションをバックグラウンドで使用して、一時的な認証情報を取得する

## EC2 の認証情報
- 環境変数や引数でロールを指定しない限り、インスタンスプロファイルやコンテナにアタッチされたロールを使用

### EC2 や ECS がどの認証を使用するか設定

▼ credential_source の値に従う
- Environment – 環境変数からソース認証情報を取得します。

- Ec2InstanceMetadata – Amazon EC2 インスタンスプロファイルにアタッチされた IAM ロールを使用します。

- EcsContainer – Amazon ECS コンテナにアタッチされた IAM ロールを使用します。

```ini
[profile marketingadmin]
role_arn = arn:aws:iam::123456789012:role/marketingadminrole
credential_source = Ec2InstanceMetadata
```


## ロールを使う上での IAM 設定

### アカウント 123456789012 の任意の IAM ユーザーが、この信頼ポリシーをアタッチしたロールを引き受けることができる

- そのユーザーは `sts:AssumeRole` の権限が必要

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "AWS": "arn:aws:iam::123456789012:root"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
```


### IAM ユーザーにアタッチして、ユーザーが marketingadminrole ロールのみを引き受けることを許可するポリシー

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": "sts:AssumeRole",
      "Resource": "arn:aws:iam::123456789012:role/marketingadminrole"
    }
  ]
}
```

## ロール使用時に、監査に使用ユーザーがわかるセッション名を追加する

```ini
[profile namedsessionrole]
role_arn = arn:aws:iam::234567890123:role/SomeRole
source_profile = default
# 下記を追加
role_session_name = Session_Maria_Garcia
```

ロールセッションに下記情報が付与されるようになる
```bash
arn:aws:iam::234567890123:assumed-role/SomeRole/Session_Maria_Garcia
```


## いつスイッチロールできる？
- IAM ユーザーとしてサインインしている場合、ロールを使用して AWS CLI コマンドを実行できます。
- また、外部で認証されたユーザー (SAML または OIDC) としてサインインしている場合にも、ロールを使用して AWS CLI コマンドを実行できます。
- また、インスタンスプロファイルを経由して、ロールにアタッチされた Amazon EC2 インスタンス内部から AWS CLI コマンドを実行するロールを使用できます。
- ロールを使用して 2 つ目のロールを引き受ける、ロールの連鎖を使用することもできます。

## こんなときスイッチロールできない
- AWS アカウントのルートユーザー としてサインインしているときに、ロールを引き受けることはできません。