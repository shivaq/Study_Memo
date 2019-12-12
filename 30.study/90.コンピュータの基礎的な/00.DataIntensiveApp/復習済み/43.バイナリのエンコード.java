■■■■■■■■■■■■■■■■■■■■■■■■■■ Binary encoding
・その組織内でだけ取り扱うデータの場合、よく使われているエンコーディングフォーマットを使わなくたって構わない。
・もっとコンパクトでパースが早いフォーマットを選択すればいい
・データセットが小さきゃいいけど、テラバイトとかになると、データフォーマットの選択は重大な選択だ。

▼ データ容量として無駄が多い
JSON はXMLデータを よりコンパクトだが、バイナリと比べたら無駄なスペースがすごく多い
 →JSON 用、XML 用のバイナリエンコーディングがいくつか発明されたが、JSON、XML自身と比べたらずっとニッチ。
for JSON
(MessagePack, BSON, BJSON, UBJSON, BISON, and Smile, to name a few)
and for XML
(WBXML and Fast Infoset, for example).
・上記、データモデルは JSON/XML と変わらないので、スキーマは事前に指定できない
 →OBJのフィールド名を、エンコードデータに入れ込まなければならん
■■■■■■■■■■■■■■■■■■■■■■■■■■ 例
// MessagePack, a binary encoding for JSON
{
    "userName": "Martin",
    "favoriteNumber": 1337,
    "interests": ["daydreaming", "hacking"]
}
-------------------------------------------------
上記を MessagePack でエンコードすると、Figure 4-1 のような byte シークエンスになる


1. The first byte, 0x83
 →what follows is an object (top four bits = 0x80) with three fields (bottom four bits = 0x03).

2. The second byte, 0xa8,
 →what follows is a string (top four bits = 0xa0) that is eight bytes long (bottom four bits = 0x08)

3. The next eight bytes
 →the field name userName in ASCII.
※ Since the length was indicated previously, there’s no need for any marker to tell us where the string ends (or any escaping).

4. The next seven bytes encode the six-letter string value Martin with a prefix 0xa6, and so on.
