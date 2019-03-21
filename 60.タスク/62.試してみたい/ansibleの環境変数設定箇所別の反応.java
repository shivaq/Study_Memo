1 かつ 2 かつ 3 を試す
2 かつ 3 を試す
1 かつ 3 を試す

-------------------------------------------------
1.対象サーバの yum.conf 等にProxy設定を記述しないバージョン
2.task の 下記を設定するバージョン
- hosts: all
  environment:
    HTTP_PROXY: http://proxy.my.local:8080
    HTTPS_PROXY: http://proxy.my.local:8080
    NO_PROXY: 169.254.169.254
3.Ansible サーバの yum.conf 等にProxy設定を記述しないバージョン
-------------------------------------------------
