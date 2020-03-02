# 投票システム フローチャート

```plantuml
@startuml Two Modes - Technical View

!define AWSPuml https://raw.githubusercontent.com/awslabs/aws-icons-for-plantuml/master/dist
!includeurl AWSPuml/AWSCommon.puml

' Uncomment the following line to create simplified view
' !includeurl AWSPuml/AWSSimplified.puml

!includeurl AWSPuml/General/Users.puml
!includeurl AWSPuml/Mobile/APIGateway.puml
!includeurl AWSPuml/SecurityIdentityAndCompliance/Cognito.puml
!includeurl AWSPuml/Compute/Lambda.puml
!includeurl AWSPuml/Database/DynamoDB.puml

left to right direction

' リソース定義 '
Users(sources, "Events", "millions of users")

APIGateway(votingAPI, "Voting API", "user votes")

Cognito(userAuth, "User Authentication", "jwt to submit votes")

Lambda(generateToken, "User Credentials", "return jwt")

Lambda(recordVote, "Record Vote", "enter or update vote per user")

DynamoDB(voteDb, "Vote Database", "one entry per user")

' 関係性定義'
sources --> userAuth
sources --> votingAPI
userAuth <--> generateToken
votingAPI --> recordVote
recordVote --> voteDb

@enduml
```
