#### Go と GC
* GC ができる、しかしVM に依存したGCではない
* When invoking the Go compiler, you simply choose the type of platform (Windows, Mac, and so on) that you'd like the binary to run on when you build.
* The compiler will then produce a single binary that works on that platform.

* Docker も Goで書かれている

### 良い点
* 負荷の大きいサービスに適している
* シンプルでわかりやすい
* スピード
* concurrency のサポート// 並行性
#### 複数のコアやマシーンをまたいだ実行が効率的
* ビジネスロジックを提供する、独立したコンポーネントを含んだマイクロサービスを提供
* モジュールに厳密に分けることで、the continuous delivery of large and complex apps の扱いが得意
* パッケージシステムを使うことで、製品の実装が早い
* なぜか →重くて時間のかかるフレームワークの構築が回避できるから
* シンプルだからコスト安。だから、マイクロサービスの構築に採用する企業が多い

## Go が適しているケース
* 新規プロジェクトを開始する場合
* 既存の製品を改善する場合
* 複雑で大きなアプリをすばやく構築できる
* 1秒あたり、1000の並行リクエストを処理できる
* Automation, messaging platforms, data analysis, machine learning

### 欠点
* まだ若く、発展中のため、ライブラリを最大限活かすのは難しい。
* 手動でのメモリ管理ができない →GCのオーバーヘッドや一時停止が起きる
* Safety が弱い？コンパイル時にのみ Safety がチェックされる。


### フレームワークたち
#### Go Micro
* GO 言語でマイクロサービスを開発するための RPC framework
* 何ができるか
* load balancing, PRC Client and server packages and message encoding.

#### Go Kit
* バイナリパッケージにインポートするために作られたライブラリ
* DDD (Domain-driven design)に最適化
* 明示的依存関係
* declarative compositions.

#### Gizmo
* server implementation and
* high-level building components like configs
