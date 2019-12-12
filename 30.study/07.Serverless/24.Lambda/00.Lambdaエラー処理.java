Lambdaはエラーが発生すると、JSON を返す
▼ 下記でエラー発生
-------------------------------------------------
def always_failed_handler(event, context):
    raise Exception('I failed!')

-------------------------------------------------
▼ 返り値
-------------------------------------------------
{
  "errorMessage": "I failed!",
  "stackTrace": [
    [
      "/var/task/lambda_function.py",
      3,
      "my_always_fails_handler",
      "raise Exception('I failed!')"
      ]
        ],
        "errorType": "Exception"
      }
-------------------------------------------------


▼ エラー時のリトライ
-------------------------------------------------
・イベントソースによっては、失敗したLambdaをリトライする
・Kinesis
 →成功するか、レコードストリームが失効するまでリトライ


-------------------------------------------------



▼ エラー処理例
def hello(event, context):
    body = {
        "message": "Go Serverless v1.0! Your function executed successfully!",
        "input": event
    }

    # AWS SNSを呼び出し
    sns = boto3.client('sns')
    try:
        sns.publish(
            TopicArn="arn:aws:sns:us-west-2:471043657237:hello",
            Subject=body['message'],
            Message=json.dumps(body['input'])
        )
    except Exception:
        raise

    response = {
        "statusCode": 200,
        "body": json.dumps(body)
    }

    return response
-------------------------------------------------
# AWS SNSで受け取った情報をDynamoDBに保存するLambdaスクリプト
def put_hello(event, context):
    sns = event['Records'][0]['Sns']

    dynamodb = boto3.resource('dynamodb')
    table = dynamodb.Table('hello_log')

    try:
        table.put_item(
            Item={
                "timestamp": sns['Timestamp'],
                "id": sns['MessageId'],
                "subject": sns['Subject'],
                "message": sns['Message']
            })

    except Exception:
        raise
