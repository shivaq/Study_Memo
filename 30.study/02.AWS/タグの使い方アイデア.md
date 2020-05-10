■■■■■■■■■■■■■■■■■■■■■■■■■■ ベストプラクティス
キャメルケースを使いましょう

ネームスペースを使いましょう
Owner:department
Owner:domain
Owner:application

■■■■■■■■■■■■■■■■■■■■■■■■■■ コストエクスプローラーで利用
Owner:Yamada
Environment:Test
Application:KMC


IAM ユーザーにメアドタグ
Key=EmailID,Value=john@example.com

■■■■■■■■■■■■■■■■■■■■■■■■■■ タグ付けを習慣づける方法
https://docs.aws.amazon.com/ja_jp/aws-technical-content/latest/cost-optimization-laying-the-foundation/tagging.html
強制しない限り、タグの質は落ちる。
報告は手動となり、時間がかかる。

タギングの強制にはソフトな方法とハードな方法とがある。
ソフト：タギングポリシーを遵守していないユーザーに通知する
ハード：遵守していないリソースを削除する。(ローンチ後数時間で)成熟した企業はハードな方法こそが
質の良いタギングを維持する最良の方法だということを理解している。

■■■■■■■■■■■■■■■■■■■■■■■■■■ タギングツール
▼ Config
タグポリシーを遵守していないリソースを特定する。

マネージドルールを使用することで、 EBSが暗号化されているかどうか、
または 特定のタグがリソースに適用されているかどうかをすばやく評価できます。
