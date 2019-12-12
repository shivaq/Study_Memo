# cfnresponse →CFNにコード直書きの場合のみ機能する
import cfnresponse

import boto3
# boto3 を使っていても、例外処理には下記を使う
from botocore.exceptions import ClientError

##################################
# ClientError →error_code = e.response['Error']['Code'] で、
# EntityAlreadyExists だったり、404(対象のリソースがない場合)とかがわかる。
##################################
def handler(event, context):

    # S3 トリガーのevent から RequestType を取得
    request_type = event.get('RequestType') or None

    # result = cfnresponse.SUCCESS

    if request_type == None:
        # cfnresponse.send(event, context, result, {})

    try:
        if request_type == 'Create' or request_type == 'Update':
            # result = copy_objects(source_bucket, source_prefix, bucket, prefix)
        elif request_type == 'Delete':
            # result = delete_objects(bucket, prefix)
    except ClientError as e:
        logger.error('Error: %s', e)
        # result = cfnresponse.FAILED

    # cfnresponse.send(event, context, result, {})
##################################


##################################
# S3 の対象File(Key)をコピーしようとして、KeyError になったら、とりあえずエラー内容を出力
##################################
def copy_objects(source_bucket, source_prefix, bucket, prefix):
    # if source_bucket == None or bucket == None:
    #     return cfnresponse.SUCCESS
    #
    # paginator = client.get_paginator('list_objects_v2')
    # page_iterator = paginator.paginate(Bucket=source_bucket, Prefix=source_prefix)
    try:
        for key in {x['Key'] for page in page_iterator for x in page['Contents']}:
            # source_key = key
            # dest_key = os.path.join(prefix, os.path.relpath(key, source_prefix))
            # print 'copy {} to {}'.format(key, dest_key)
            # client.copy_object(CopySource={'Bucket': source_bucket, 'Key': key}, Bucket=bucket, Key=dest_key)
    except KeyError as e:
        logger.error('Error: %s', e)
    #
    # return cfnresponse.SUCCESS

##################################
# S3 の対象File(Key)を消そうとして、KeyError になったら、
# 対象Fileは None だと判断。何もしない。出力もしない。
# objects が None ではなければ、削除処理を行う
##################################
def delete_objects(bucket, prefix):
    # if bucket == None:
    #     return cfnresponse.SUCCESS
    #
    # versioning = client.get_bucket_versioning(Bucket=bucket)
    # versioning_status = versioning.get('Status') or 'Disabled'
    if versioning_status == 'Enabled':
        # paginator = client.get_paginator('list_object_versions')
        # page_iterator = paginator.paginate(Bucket=bucket, Prefix=prefix)
        try: objects = [{'Key': x['Key'],'VersionId': x['VersionId']} for page in page_iterator for x in page['Versions']]
        except KeyError: objects = None

    else:
        # paginator = client.get_paginator('list_objects_v2')
        # page_iterator = paginator.paginate(Bucket=bucket, Prefix=prefix)
        try: objects = [{'Key': x['Key']} for page in page_iterator for x in page['Contents']]
        except KeyError: objects = None

    if objects != None:
        client.delete_objects(Bucket=bucket, Delete={'Objects': objects})

    return cfnresponse.SUCCESS
