・アプリは、変化し続けることが避けられない
・アプリの特徴が変化する時
 →格納されるデータも変化が要求される。
perhaps a new field or record type needs to be captured,
or perhaps existing data needs to be presented in a new way.

▼ Relational databases
although that schema can be changed (through schema migrations; i.e., ALTER statements),
there is exactly one schema in force at any one point in time.

▼ schema-on-read (“schemaless”) databases
don’t enforce a schema, so the database can contain a mixture of older and newer data formats written at different times

▼ データフォーマットが変われば、アプリのコードも変更が必要になる
When a data format or schema changes,
a corresponding change to application code often needs to happen
(for example, you add a new field to a record, and the application code starts reading and writing that field).

■■■■■■■■■■■■■■■■■■■■■■■■■■ でかいアプリのコード変更
▼ ローリングアップグレード
With server-side applications
you may want to perform a rolling upgrade (also known as a staged rollout),
deploying the new version to a few nodes at a time,
checking whether the new version is running smoothly, and gradually working your way through all the nodes.
・サービスダウンタイムなしでデプロイできる
and thus encourages more frequent releases and better evolvability.

▼ 新旧バージョンが同時期に存在するシステム
クライアントサイドアプリは、ユーザーはすぐに更新しなかったりする
This means that old and new versions of the code,
and old and new data formats, may potentially all coexist in the system at the same time.
In order for the system to continue running smoothly, we need to maintain compatibility in both directions:

■■■■■■■■■■■■■■■■■■■■■■■■■■ 互換性
▼ Backward compatibility
Newer code can read data that was written by older code.
※ こっちは古いコードがどんななのかをわかった上で互換性を仕込める

▼ Forward compatibility
Older code can read data that was written by newer code.
・旧コードが、新コードが追加した部分を無視しなければならない。





■■■■■■■■■■■■■■■■■■■■■■■■■■ スキーマとは
スキーマ（schema）とは、データベースの構造
例)
関係データベースでは、スキーマは関係 (表) と関係内の属性 (フィールド) 、属性や関係の関連の定義

Oracle、SQLServer、PostgreSQLには、スキーマと呼ばれる概念があります。
MySQL、Microsoft Accessには、スキーマと呼ばれる概念はありません。




■■■■■■■■■■■■■■■■■■■■■■■■■■ 三層スキーマ（Three schema approach）
概念-論理-物理　方式
▼ 概念スキーマ - 概念と概念間の関係の定義
・データベースに必要な情報をデータモデルによって抽象化し、その抽象化した概念と概念間の関係を定義した記述
・組織（実体クラス）の有意なものと、
  それに付随する情報、特性（属性）、それらのものの間の関連を説明する。

・組織の意味論を表すものであってデータベース設計ではない。よって、その抽象レベルは様々
ANSIの三層スキーマアーキテクチャでは、各利用者独自の観点を反映した概念スキーマを「外部スキーマ」と呼ぶ。
逆にそれらを集約したものが「概念スキーマ」である。

★データモデルは各利用者の観点では固定的であり、それだけでは柔軟性に欠ける傾向がある。
その個人の世界観が変われば、モデルも変更を余儀なくされる。
概念スキーマはより抽象的で、本質的である。

・概念スキーマには、オブジェクト指向で言う継承に相当するものもある。
ある実体クラスのインスタンスの集合は、適当な下位の実体クラスに分類できる。
従って、ある下位の実体クラスのインスタンスは、上位の実体クラスのインスタンスでもある。
・上位の実体クラスのインスタンスは、何らかの下位の実体クラスのインスタンスでもある。

基本型/派生型関係は「排他的関係」の場合もある。
方法論的に、ある基本型のインスタンスがどれか1つの派生型のインスタンスでなければならない場合もある。

同様に網羅的関係の場合もある。
網羅的な場合、ある基本型のインスタンスは必ず何らかの派生型のインスタンスでなければならない。


▼ 論理スキーマ - 実体とその属性、実体間の関係の定義
対象領域について特定のデータ管理技術を前提として記述されるデータモデルである。
ただし、特定のデータベース管理製品に依存することはない。

例)関係モデル
関係 (表) と組 (タプル、行)

例)オブジェクトモデル
クラス、あるいはXMLタグなどを用いて記述

概念スキーマは、実装技術を全く考慮せずに組織の意味論を記述する。
それと比べ、論理スキーマは対照的である。

データベースを作成する場合、論理スキーマを作成した後は、そこから物理スキーマを作成する。



▼ 物理スキーマ - 論理スキーマの具体的実装
特定のデータ管理技術の観点で記述されるデータモデルである。
ANSIの四層スキーマアーキテクチャでは「内部スキーマ」と呼ぶ。
論理スキーマはデータの論理的表現を定義するが、この段階では具体的にストレージ (補助記憶装置) にデータがどのように格納されるかといった部分は考慮されていない。
物理スキーマは、個別のデータベース管理製品におけるデータ格納を具体的に定義する。




■■■■■■■■■■■■■■■■■■■■■■■■■■ スキーマ言語とは
「このXMLファイル（とかSGMLファイルとか）は、こんな構造になっていますよ」が書いてあるファイルの、書き方ルール
「XMLファイルとかの構造を定義する際は、こんな書き方をしなさい」が示されています。

・本来、文書は構成要素の集合体である。
とはいえ、一定の構造を持たなければ単なる要素の寄せ集めでしかなく、規則性や体裁といった構造を得て初めて意味のある有用な文書となる。

・XMLやSGMLは文書の各要素を作成するメタ言語であり、文書構造自体を定義する事はできない。
よって、このままでは扱いにくい。
 →そこで構造を定義する言語が必要となり、開発されたのがスキーマ言語である。
