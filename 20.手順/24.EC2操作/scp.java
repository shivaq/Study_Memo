▼ SCP
// ファイルを持っていく
// タイム・アウトする場合、家のIPが変わっている可能性がある。確認くんで確認
scp -i ~/.ssh/MyKey.pem /path/to/my_code.py ec2-user@13.114.245.162:~/MyKey.pem

▼ SSH
ssh -i ~/.ssh/MyKey.pem ec2-user@13.114.245.162

web01.my.local
-------------------------------------------------
▼ リモートのファイル確認
ssh -i ~/.ssh/MyKey.pem ec2-user@web01.my.local ls -l
