# Python では、あらゆるものが オブジェクトである


## Java などと異なり、OOP な使い方でなくても全然構わない
- クラスや継承などを使わなくたっていいじゃんって感じ
- ビジネスモデルがそれを必要としない限り、使わなくたっていい
- 不要な OOP は回避したほうがいい

## モジュール等 namespace による isolation が自然に使われる

- よって、OOP を使う主な理由のうちの一つが実現できている


## OOP を使いたいケース
- グラフィカルなデスクトップアプリ や ゲーム
- things が、メモリ内に長期間存在し、その間 manipulate されるような場合(ボタン、アバター、ウィンドウなど)

# OOP を避けたいケースとその理由
- オブジェクトは状態を持つ。
- Web アプリの場合、外部からのリクエストに応答する際に、複数のプロセスのインスタンスがスポーンする
- 結果、インスタンスが状態を保持することで、concurrency や race conditions 関連の問題が発生しうる状態となる

## Pure function は クラスなどより効率的な building block
- pure functions are more efficient building blocks than classes and objects for some architectures because they have no context or side-effects.