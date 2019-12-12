■■■■■■■■■■■■■■■■■■■■■■■■■■ 困難な点
・サーバーレスアーキテクチャは、個別にまたは一緒にテストしなければならない、分散されたサービスを統合したものである。
・サーバーレスアーキテクチャは、インターネット/クラウド に依存したサービスであり、ローカルでエミュレートすることが難しい
・サーバーレスアーキテクチャは、イベントドリブン、非同期ワークフローが可能だが、それをエミュレートすることは難しい。

■■■■■■■■■■■■■■■■■■■■■■■■■■ 困難な点を克服するために
・ビジネスロジックをFaasプロバイダに依存しないかたちで、再利用ができるかたちで、テストが容易なかたちで記述すること。
・Faas プロバイダに依存しない記述ができれば、伝統的ユニットテストが記述できる。
・他のサービスとの正しく統合して働くことを確認できるよう、統合テストを書くこと。
