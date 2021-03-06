# テスト

```plantuml
@startuml
skinparam componentStyle uml2

() "Router" as r
() "RestfulEndpoint" as Rep

node "eventservice"{

package "mainp"{
    [main]
}
package "configurationP"{
  [configuration]
}

package "rest"{
    [eventServiceHandler]
    [ServerAPI]
}
}




node "lib"{

package "dblayer"{
    [DBHandlerFactory]
}
    package "persistence"{
    () "DatabaseHandler" as Dbh
}

package "mongolayer"{
    [MongoDBLayer]
}
}


[configuration] -> [main]
[main] <-- [ServerAPI]
[main] <-- [eventServiceHandler]
[eventServiceHandler] -> [ServerAPI]
[ServerAPI] --> r

r --> Rep:HTTP

[main] <-- [DBHandlerFactory]
[ServerAPI] <-- Dbh
[eventServiceHandler] <-- Dbh

Dbh -> [DBHandlerFactory]
[DBHandlerFactory] <-- [MongoDBLayer]


note top of [DBHandlerFactory] : Factory
note top of Dbh : インターフェイス
note top of [main] : DBハンドラ実装コンストラクタを起動
note top of [eventServiceHandler] : DBハンドラを実装
@enduml

note left of HTTP : Web Service only
```
