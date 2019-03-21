コンテナ と VM
・双方とも、ゴールは一緒。アプリとその依存関係を一つのユニットに入れ込んで、隔離、再利用可能にする。
・双方とも、物理HWを必要としない。

▼ VM のアーキテクチャ                  ここから上が VM
サーバ ＞ ホストOS ＞ ハイパーバイザ ＞ ゲストOSたち ＞ Bins/Libs > App

▼ コンテナのアーキテクチャ               ここから上がコンテナ
サーバ ＞ ホストOS ＞ Docker エンジン ＞ Bins/Libs > App

■■■■■■■■■■■■ VM とは
・コンピュータのエミュレーション。実際のコンピュータと同じように動く。
・VM は 自分専用 OS を持つ
・ハイパーバイザがゲストOSの管理と実行に重要な役割を担う
・ハイパーバイザが、ホストのリソースをVM間でシェアすることを実現している

■■■■■■■■■■■■ VM は なにでできているか
▼ VM のアーキテクチャ                  ここから上が VM
サーバ ＞ ホストOS ＞ ハイパーバイザ ＞ ゲストOSたち ＞ Bins/Libs > App

▼ 物理コンピュータ(ホストマシーン)
・VM は ハイパーバイザを使った 物理コンピュータ(ホストマシーン)上で動く。
・ホストマシーンは VM にRAM や CPU などのリソースを提供する
・ホストマシーンのリソースは、VM 間でシェアされる

▼ ハイパーバイザー
・ハイパーバイザはソフトウェア、もしくはVMが走っている HW を指す場合もある

▼ VM
・ゲストマシーンとも呼ばれる
・アプリと、それにひつようなものどもが含まれる(システムバイナリ、ライブラリ)
・仮想化されたHWスタック(仮想NWアダプタ、ストレージ、CPU)を持つ
・自前の OS を有する

▼ HW と ハイパーバイザの間に OS がいる場合の VM
・ホストマシーンのOS上で動くハイパーバイザ
・VM が HW にアクセスする場合は OS 経由
・HW はさほど重要じゃない
--- pros
・ホストのOSはHWドライバに責任を持つ。
・ホストのOSはハイパーバイザーに責任を持たない。
・よって、HW の互換性が柔軟
--- cons
・HW と ハイパーバイザ間に 追加の OS レイヤがあるため、オーバーヘッドが生じる

▼ HW 直乗せハイパーバイザの VM
・ホストOSは存在しない
・HW上にハイパーバイザが乗っている
・ホストマシーンに最初にOSとしてインストールされるのが、ハイパーバイザとなる。
・ベアメタルハイパーバイザは、自身のデバイスドライバを持ち、HWに直接アクセスできる
---pros
・パフォーマンス上の余計なオーバーヘッドがない
・拡張性が高い。
・安定性が高い。
---cons
・互換性に制限がある。
