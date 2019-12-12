
▼ awk
-------------------------------------------------
# ///proc/cpuinfo の processor で始まる行の 3 つ目のColumn のうち、最後の1つを出力する。
# ///プロセッサ番号は 0 から始まるため、出力された数値 + 1 が CPU 数
cat /proc/cpuinfo | awk '/^processor/{print $3}' | tail -1
-------------------------------------------------
