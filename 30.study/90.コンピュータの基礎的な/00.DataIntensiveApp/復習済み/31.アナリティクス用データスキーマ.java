■■■■■■■■■■■■■■■■■■■■■■■■■■ Stars and Snowflakes: Schemas for Analytics
・アナリティクス用は、トランザクション用と比べ、データモデルの数が少ない


■■■■■■■■■■■■■■■■■■■■■■■■■■ Star schema (also known as dimensional modeling )
・ファクトテーブルを中心に、ディメンションテーブルが星型に散らばっている

■■■■■■■■■■■■■■■■■■■■■■■■■■ fact table
・小売のデータの場合、ファクトテーブルの各行は、ある時間に起きたイベントを表す。※ 顧客がある製品を購入した、というイベント
・ウェブサイトのトラフィックの場合、各行はページビューや、ユーザーのクリックを指すとか。

▼ ファクトテーブルの扱い方
・ファクトは通常、個々のイベントとして取得される。
 →のちの分析時に、柔軟性が高いから
・しかし
 →ファクトテーブルがすごくおおきくなる
 →Apple, Walmart, or eBayみたいな大企業は、ペタバイト規模のトランザクション履歴がデータウェアハウスにあると想像される。
 で、そのほとんどが ファクトテーブルだろう

▼ ファクトテーブルの列
・the price at which the product was sold and
・the cost of buying it from the supplier (allowing the profit margin to be calculated).

■■■■■■■■■■■■■■■■■■■■■■■■■■ ディメンションテーブルを参照する外部キー
・Other columns in the fact table are foreign key references to other tables, called dimension tables.
・the dimensions represent the who, what, where, when, how, and why of the event.
例)
・dim_product テーブルのレコード項目
one type of product that is for sale, including
its stock-keeping unit (SKU), description, brand name, category, fat content, package size, etc.

・fact_sales table
が使用している外部キー：どの製品が、その特定のトランザクションで売られたか

・dim 日付テーブル
ファクトテーブル化の過程で、日付に、祝日情報を追加している
 →クエリ時に、休日の売上かどうか、といった使い分けができる

▼ スノーフレークスキーマ
・スタースキーマのディメンションが、さらにサブ・ディメンションにブレークダウンされている
例)
製品項目のブランド列を、ブランドディメンションテーブルにブレークダウンして、外部キーとして参照したり
・スターより正規化が進んではいる。だが、スタースキーマのほうがシンプルで分析しやすいということで、好まれている。

■■■■■■■■■■■■■■■■■■■■■■■■■■ データウェアハウスのテーブルの特徴
・テーブルの列が多い。100列を超えるくらいはざらで、数百に達することも。
・ディメンションテーブルも列が多い。分析時に関係してくるかも知れないメタデータを入れ込んでいる
例)the dim_store table
may include details of which services are offered at each store,
whether it has an in-store bakery, the square footage, the date when the store was first opened,
when it was last remodeled, how far it is from the nearest highway, etc.
