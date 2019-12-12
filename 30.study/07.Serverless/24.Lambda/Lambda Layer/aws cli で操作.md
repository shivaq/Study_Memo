■■■■■■■■■■■■■■■■■■■■■■■■■ 既存の関数に、CLI でレイヤーを追加する
・使用するのは update-function-configuration コマンド
・レイヤーが追加されると、既存のレイヤーリストが更新される
-------------------------------------------------
aws lambda update-function-configuration --function-name my-function \
--layers arn:aws:lambda:us-east-2:123456789012:layer:my-layer:3 \
arn:aws:lambda:us-east-2:210987654321:layer:their-layer:2
-------------------------------------------------

■■■■■■■■■■■■■■■■■■■■■■■■■■ すべてのレイヤーを削除する
・空のリストを渡す
aws lambda update-function-configuration --function-name my-function --layers []

■■■■■■■■■■■■■■■■■■■■■■■■■■ レイヤーをCLIで作成する
・publish-layer-version を使う
・--compatible-runtimes でランタイムのリストを指定すると、レイヤーを検出しやすくなる
aws lambda publish-layer-version --layer-name my-layer --description "My layer" --license-info "MIT" \
--content S3Bucket=lambda-layers-us-east-2-123456789012,S3Key=layer.zip --compatible-runtimes python3.6 python3.7
