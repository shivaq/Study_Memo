■■■■■■■■■■■■■■■■■■■■■■■■■■ Solutions for Replication Lag
▼ eventually consistent system を扱う場合に考えておかなければならないこと
 →レプリケーションの遅延が数分、数時間と増えていく可能性がある。
その時、アプリはどう振る舞うのだ？

・遅延は大した問題にならない場合
 →"eventually consistent system"でよかろう、。
・UX に悪い影響を及ぼすようなら
 →read after write のように、strong consistent なシステムとして設計することが重要。

▼ リーダーからのみ読み込むようにすれば？複雑になるけど。
実際はレプリケーションは非同期だけど、同期しているかのように見せればよい。
特定に読み込みだけリーダーから行うようにすれば良い。
※ ただし、アプリのコードが複雑になり、間違えやすい。

▼ なぜ"トランザクション"が存在しているか
アプリ開発者からしたら、そういったレプリケーションイシューを気にせず開発したい。
ただ、DBを信用する。DBは“do the right thing.”
トランザクションは、
DBが "stronger guarantees" を提供する。
アプリはシンプルになる。

▼ 分散DB時代のトランザクション
シングルノード時代が長い間続いてきた。
しかし、現在 distributed (replicated and partitioned) databases が増えてきている。

多くのシステムがトランザクションを廃止している。
なぜか。
トランザクションはパフォーマンスと可用性の観点で高コストだから。
そして、
スケーラブルなシステムを扱う場合、eventual consistency は避けられないと言っている。

上記意見はわからんでもない。しかし、単純化しすぎだとも思う。
では、どんなオプションが有るというのか。
そこらへんをこの本では掘り下げていく。
