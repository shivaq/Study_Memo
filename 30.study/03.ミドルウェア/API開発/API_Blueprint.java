■■■■■■■■■■■■■■■■■■■■■■■■■■ API Blueprint

API Blueprint is a “Hight level API description language for web API’s”


★ API Blueprint は、
・APIのドキュメントと仕様の記載に使える
・dredd と連携して、API のバックエンドの実装をチェックするテストのセットとして使える

API 仕様の記述例
-------------------------------------------------
FORMAT: 1A
HOST: http://127.0.0.1:8000
# Basic ACME Blog API
Welcome to the **ACME Blog** API. This API provides access to the **ACME Blog** service.
# Group Blog Posts
## Posts [GET /api/post]
+ Response 200 (application/json)

    + Body
            [
                {
                    "id": 1,
                    "title": "foo title",
                    "body": "bar body",
                    "author": 1
                }
            ]
-------------------------------------------------

■■■■■■■■■■■■■■■■■■■■■■■■■■ Dredd
Dredd is a language-agnostic command-line tool for validating API description document
against backend implementation of the API.




▼ インストール
// Globally install
npm install -g dredd
// Local install
npm install — save-dev dredd




▼ APIの仕様作成
ファイル名：API-description.apib
※ API BluePrint の拡張子
プロジェクトの root に作成
-------------------------------------------------
FORMAT: 1A
HOST: http://127.0.0.1:8000
# Basic ACME Blog API
Welcome to the **ACME Blog** API. This API provides access to the **ACME Blog** service.
# Group Blog Posts
## Posts [GET /api/post]
+ Response 200 (application/json)

    + Body
            [
                {
                    "id": 1,
                    "title": "foo title",
                    "body": "bar body",
                    "author": 1
                }
            ]
# Group Authors
Resources in this groups are related to **ACME Blog** authors.
## Authors [GET /api/author]
+ Response 200 (application/json)

    + Body
            [
                {
                    "id": 1,
                    "name": "jhon",
                    "email": "jhon@mail.com"
                }
            ]
-------------------------------------------------


▼ CLI から dredd.init を作成
-------------------------------------------------
$ dredd init
? Location of the API description document api-description.apib
? Command to start API backend server e.g. (bundle exec rails server)
? URL of tested API endpoint http://127.0.0.1:8000
? Programming language of hooks php
? Do you want to use Apiary test inspector? No
? Dredd is best served with Continuous Integration. Create CircleCI config for Dredd? No
Configuration saved to dredd.yml
Install hooks handler and run Dredd test with:
$ composer require ddelnano/dredd-hooks-phpdev
$ dredd
-------------------------------------------------
 → dred.yml ファイルが生成される
