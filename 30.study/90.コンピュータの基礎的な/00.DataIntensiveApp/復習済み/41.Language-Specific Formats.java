▼ 多くの言語には、メモリ上のOBJ をバイトシークエンスにエンコードする機能が組み込まれている。
Java has java.io.Serializable
Ruby has Marshal
Python has pickle

▼ エンコードのためのサードパーティライブラリもたくさんある
Kryo for Java
・メモリ上のOBJの保存と復元が、最小限の追加コードでできる。

▼ サードパーティライブラリ(だけに限らない？)(特定の言語用のエンコードエンジン？)の問題
・エンコーディングは、特定の言語と深く結びついている
 →他の言語のデータを読むのはとてもむずかしい
 →If you store or transmit data in such an encoding,現在使っている言語に非常に長い時間コミットすることになる。
  →結果、他の言語を使っている別の組織と、システムを統合する可能性を排除することになる。
・同じOBJタイプのデータを復元するためには、the decoding process needs to be able to instantiate arbitrary classes.
 →これがセキュリティ上の問題の原因になることが多い。
  →攻撃者があなたのアプリを手に入れて、任意のバイトシークエンスをデコードできるようになると、
   →任意のクラスを初期化することができるようになる
    →任意のコードをリモートから実行することなどができてしまう。

・データのバージョニングは、そういったライブラリでは後付になってたりする。
それらライブラリは、データをすばやく簡単にエンコードすることを目的にしてたりするので、
前方後方互換性についてしばしば無視されている。
・効率性も後付(結果論、afterthought)だったりする。 ※エンコード/デコードにかかるCPU時間と、エンコード後の構造のサイズとか
例)
Java’s built-in serialization
 →パフォーマンスが悪い。エンコードが肥大化する。

▼ だから、言語に組み込まれたエンコードを使うのは良くない。 ※ 一時的に使うなら構わんが
