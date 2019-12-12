aws lambda invoke \
--invocation-type Event \
--function-name FunctionTest1 \
--region ap-northeast-1 \
--profile default \
outputfile.txt

ap-northeast-1
 The Lambda function will return the name of the Amazon S3 bucket as "Test". Next, run the following Lambda CLI update-function-configuration command to update the Amazon S3 environment variable by pointing it to the Prod bucket. 
 
aws lambda update-function-configuration
--function-name FunctionTest1 \
--region ap-northeast-1 \
--environment Variables={S3_BUCKET=Prod}

aws lambda invoke \
--function-name FunctionTest1 \
--region ap-northeast-1 \
--profile default \
outputfile.txt