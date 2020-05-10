■■■■■■■■■■■■■■■■■■■■■■■■■■ 用語
▼ リソース
DB、インデックス、タスク

▼ パーティション
リソースのサブセット。サブタスクも。

▼ レプリカ
パーティションの状態のコピー。マスタースレーブ。

▼ ステート
レプリカの役割を記述。
各ノードは自身の状態を持つ

▼ ステートマシンとトランザクション
レプリカが許可されている、状態遷移を伴うアクション
※ スレーブ →マスター など

▼ spectators
外部クライアント

▼ クラスターのイベントは下記のうちのいずれか
・ノードの開始、停止
・ノードがソフト/ハード failure
・ノードの追加/削除

■■■■■■■■■■■■■■■■■■■■■■■■■■ What is Helix?

▼ クラスタのノードのリソースを自動管理するのに使用
It is used for the automatic management of partitioned, replicated and distributed resources hosted on a cluster of nodes.

▼ クラスタの失敗復旧時や再設定時、拡張時に自動再割り当てを行う
Helix automates reassignment of resources in the face of node failure and recovery, cluster expansion, and reconfiguration.
Modeling a distributed system as a state machine with constraints on states and transitions.

■■■■■■■■■■■■■■■■■■■■■■■■■■ What Is Cluster Management?
▼ 分散システムを複数のノード上で動かす理由
A distributed system typically runs on multiple nodes for the following reasons:

scalability
fault tolerance
load balancing

▼ 各ノードの主な機能
storing and serving data, producing and consuming data streams, and so on.

▼ Helix は分散システムのグローバル・ブレインとして機能する
Once configured for your system, Helix acts as the global brain for the system.
It is designed to make decisions that cannot be made in isolation.
Examples of such decisions that require global knowledge and coordination:
▼ グローバル・ブレインに求められる機能
▼ メンテタスク
scheduling of maintainence tasks, such as backups, garbage collection, file consolidation(結合、統合), index rebuilds
▼ クラスタを跨いだリソースやデータの再パーティショニング
repartitioning of data or resources across the cluster
▼ 依存関係のあるシステムの変更を通知
informing dependent systems of changes so they can react appropriately to cluster changes
▼ システムのタスクや変更のスロットリング(絞り、上限設定)
throttling system tasks and changes

▼ クラスタ管理に必要な処理を抽象化
While it is possible to integrate these functions into the distributed system, it complicates the code.
Helix has abstracted common cluster management tasks, enabling the system builder to model the desired behavior with a declarative state model,
and let Helix manage the coordination. The result is less new code to write, and a robust, highly operable system.

▼ Helix ができること
Automatic assignment of resources and partitions to nodes
Node failure detection and recovery
Dynamic addition of resources
Dynamic addition of nodes to the cluster
Automatic load balancing and throttling of transitions
Optional pluggable rebalancing for user-defined assignment of resources and partitions

▼ Helix の強みは Pluggable 分散ステートマシーン
Pluggable distributed state machine to manage the state of a resource via state transitions
▼ 分散システムをステートマシンとして定義
Modeling a distributed system as a state machine with constraints on states and transitions has the following benefits:
▼ シングルノードから分散システムにすばやく遷移
Allows a quick transformation from a single node system to an operable, distributed system.
▼ システムのコンポーネントと、分散システム管理とが疎結合となる
Increases simplicity: system components do not have to manage a global cluster.
This division of labor makes it easier to build, debug, and maintain your system.
