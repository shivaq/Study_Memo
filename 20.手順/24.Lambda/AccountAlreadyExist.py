class AccountAlreadyExistsException(Exception): pass

def create_account(event, context):
    raise AccountAlreadyExistsException('Account is in use!')

# ▼ StepFunction でエラーキャッチ
# Catch ルールを使う
{
   "StartAt": "CreateAccount",
   "States": {
      "CreateAccount": {
         "Type": "Task",
         "Resource": "arn:aws:lambda:us-east-1:123456789012:function:CreateAccount",
         "Next": "SendWelcomeEmail",
         "Catch": [
            {
               "ErrorEquals": ["AccountAlreadyExistsException"],
               "Next": "SuggestAccountName"
            }
         ]
      },
      …
   }
}
