SELECTED_PROFILE=$(grep "^$AWS_CLI_PROFILE" mfa.cfg | cut -d '=' -f 2- | tr -d '""')






# 何列目を出力するか指定
* `cut -b バイト数 ファイル名`
* `cut -b 2 cutdata.txt`

* サンプロデータの２列めの値のみが出力される


### サンプルデータ
```bash
01:orange:250:Tokyo
02:apple:230:Nagoya
03:apple:130:Nagoya
04:grape:450:Tokyo
05:orange:150:Osaka
```




# 区切り文字を指定して、必要な項目を出力

* `cut -f 項目数 -d 区切り文字 ファイル名`
* `cut -d '=' -f 2-`
* = を区切り文字として、２項目め以降を抽出






# 指定した文字列を delete するよう translate
* `SELECTED_PROFILE=$(grep "^$AWS_CLI_PROFILE" mfa.cfg | cut -d '=' -f 2- | tr -d '""')`




# 小文字を大文字に translate
* `cat domains.txt | tr [:lower:] [:upper:]`
