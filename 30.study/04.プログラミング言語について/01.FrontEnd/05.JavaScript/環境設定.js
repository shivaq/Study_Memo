■■■■■■■■■■■■■■■■■■■■■■■■■■ ターミナルから JavaScript を実行できるようにする
▼ インタプリタのパスを通す
cd /usr/local/bin

// ln →ファイルのリンクを作成。 -s →シンボリックリンクを作成
ln -s /System/Library/Frameworks/JavaScriptCore.framework/Versions/A/Resources/jsc ./
