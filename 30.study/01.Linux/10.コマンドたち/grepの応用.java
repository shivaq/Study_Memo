
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ grep
-c  ⇒マッチした総行数の表示
-i  ⇒大文字小文字を区別しない
-n  ⇒行番号をつけて表示
-v  ⇒指定したパターンを含まない行だけを表示
-l  ⇒指定したパターンを含むファイル名だけを表示

■行頭が任意の文字列である
grep '^program' *txt

■複数の条件でor検索
grep -e 検索したい文字列1 -e 検索したい文字列2 検索したいテキストファイル

■検索にヒットした前後の行を出力する場合
# grep 検索したい文字列 -B 出力したい行数 検索したいテキストファイル
# grep 検索したい文字列 -A 出力したい行数 検索したいテキストファイル
# grep 検索したい文字列 -C 出力したい行数 検索したいテキストファイル


■フォルダ内のファイルを再帰的に指定
grep -r 検索したい文字列 検索したいファイルのあるディレクトリ

■独立した文字列をもつ行のみを出力させる(the のみ出力 them は出力しない）
grep -w 検索したい文字列 検索したいファイル

■zgrep
-------------------------------------------------
