####################################################
# event から値を取得。空なら or None
####################################################
def handler(event, context):
    # logger.info("Received event: %s" % json.dumps(event))
    source_bucket = event['ResourceProperties'].get('SourceBucket') or None
    source_prefix = event['ResourceProperties'].get('SourcePrefix') or ''
    bucket = event['ResourceProperties'].get('Bucket') or None
    prefix = event['ResourceProperties'].get('Prefix') or ''
    request_type = event.get('RequestType') or None
