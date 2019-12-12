■■■■■■■■■■■■■■■■■■■■■■■■■■ プログラムが取り扱うデータの、様々な側面
1. objects, structs, lists, arrays, hash tables, trees などの形式
・メモリ上では、データは上記形式で保持されている
・CPU からの(ポインタなどを使った)効率的なアクセスと操作に最適化されたデータ構造


2. self-contained sequence of bytes にエンコードされた状態
・データをファイルに書いたり、ネットワーク越しの送ったりする時に、エンコードが必要
・JSON とか
・Since a pointer wouldn’t make sense to any other process
 →sequence-of-bytes の表現は、メモリ上で使われるデータ構造と大きく異なる

■■■■■■■■■■■■■■■■■■■■■■■■■■ 翻訳する
▼ エンコーディング
・メモリ表現からバイトシークエンス表現への翻訳(also known as serialization or marshalling)

▼ デコーディング
・バイトシークエンスからメモリ表現への翻訳(parsing, deserialization, unmarshalling)
