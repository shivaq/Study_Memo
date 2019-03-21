■■■■■■■■■■■■ Docker のコンポーネント
▼ Docker エンジン
Docker が走るレイヤー
コンテナ、イメージを管理し、ビルドを行う

▼ Docker エンジンの構成物
・Docker Daemon
ホストコンピュータ上で走る

・Docker クライアント

・REST API
Docker デーモンと リモートでやり取りする

▼ Docker Client
エンドユーザーが使う、Docker の UI 的なものもの。
Docker デーモンと communicate して、コマンドを実行させる

▼ Docker Daemon



■■■■■■■■■■■■ Union File System ※ レイヤー化されたファイルシステム
▼ レイヤーが透過的にオーバーレイされる様子
-------------------------------------------------
・ImageをビルドするのにDockerが使用するもの
・スタックできるファイルシステムのようなもの
・別々のファイルシステム(A.K.A ブランチ)のファイルやディレクトリが、
透過的にオーバーレイされ、単一のファイルシステムを形作る感じ
・オーバーレイしたブランチの、ディレクトリの中身のファイルのうち、同じパスのものは
単一のマージされたディレクトリになる。
・上記結果、レイヤーごとに別個のコピーが生成されることが回避される
・そのかわり、各レイヤーは同じリソースへのポインタが与えられる
・任意のレイヤーを修正する必要がある場合、オリジナルは未修正のままコピーを作成し、それを修正する
・上記のように、ファイルシステムに対し実際は書き込むことはできないが、書き込みができるように見える
-------------------------------------------------


▼ Copy on write
-------------------------------------------------
・Dockerがコンテナをスタートさせるとき、空のRWレイヤーが、レイヤーのトップに生成される。
・Fileに変更を加えるときは、そのFileはROレイヤーからRWレイヤーにコピーされる。
・ROバージョンのFileは、決して変更を加えられることはなく、そのままなくならずに隠れている。

上記 RW レイヤーのために、コンテナは変更できるし、ステートフルである。
-------------------------------------------------


▼ bootfs
-------------------------------------------------
・bootfs：ベースとなるブートファイルシステム。Linux/Unix のぶーとファイルシステムを集めたもの。
bootfs はコンテナが起動すると、メモリに移動され、initrd ディスクイメージが使うRAM から unmount される。
-------------------------------------------------

▼ レイヤー化することの便益
-------------------------------------------------
1.冗長にならない
Imageを使って新規コンテナを作成するたびに、ファイルシステムが改めて生成されることがない
結果、Dockerコンテナのインスタンス化が早い

2.レイヤーの分離
修正が早くなる。Imageを変更するとき、Dockerは変更がなされたレイヤーのみを更新する
-------------------------------------------------










■■■■■■■■■■■■ Volume
・Volume はコンテナのデータを担う部分。
・コンテナ生成時にインスタンス化される。
・コンテナのデータの共有と一貫性を実現する
・Data Volume はデフォルトの Union File System から隔離されており、
・ホストのファイルシステムの、普通のファイルやディレクトリとして存在している
・コンテナを削除、更新、リビルドしても、DataVolumeは修正されることなくそのまま。
・Volumeを更新したいときは直接変更を行う。
・コンテナ間でDataVolumeを共有したり、再利用したりできる。



■■■■■■■■■■■■ 1.Namespaces
・配下のLinux System に対する、コンテナから見えるもの、アクセスできるものを制限する。
・コンテナをRUNすると、Dockerが、そのコンテナ用の名前空間を生成する。

▼ Namespace の種類
a. NET:
Systemのネットワークスタックを提供
ネットワークデバイス、IPアドレス、ルートテーブル、ポート番号、/proc/net ディレクトリ

b. PID:
プロセスID。
コンテナ自身が見ることができ、やりとりができるスコープのプロセス。
init (PID 1) も含まれる

c. MNT:
コンテナ自身の、Systemのマウントの view を提供。
結果、異なるマウント Namespace のプロセスは、異なるファイルシステム 階層の View を持つ。

d. UTS:
UNIX Timesharing System.
プロセスに、SystemのIDを識別することを可能にする。
UTS によって、コンテナが、自身のhostname、NISドメインネームが、
他のコンテナやホストのSystemと独立したものになることを実現している。

e. IPC:InterProcess Communication
IPC Namespace によって、
各コンテナの中でRUNしているプロセス間の IPC リソースを隔離している。

f. USER:
コンテナ間のユーザーを隔離するために使用するNamespace。
ホストのSystemと比べ、それぞれ異なるスコープのuid, gid レンジを提供する。

結果、プロセスの uid, gid はユーザーのNamespaceの内外で異なったものとなる。
さらに、プロセスが、コンテナ内のroot特権を犠牲にすることなく、
非特権ユーザーをコンテナの外に持つことが可能となる




■■■■■■■■■■■■ 2.コントロールグループ cgroups
Linux カーネルの機能。
プロセスのリソースを隔離し、優先順位付けし、使いみちを決める
(CPU, memory, disk I/O, network, etc.)
・複数回のforkが発生しても親プロセスと孫プロセスを管理下に置くことが出来る。
// ※ PIDの場合、プロセスが2回forkすると親プロセスと孫プロセスの直接的な関係性は切れる

こうして、cgroup はコンテナが必要なだけのリソースを使うことを、
また必要に応じて、コンテナが使うことができるリソースに成約を設けることを保証する。
単一のコンテナがリソースを使い切って、System全体をダウンさせることがないことを保証する。




■■■■■■■■■■■■ 3.隔離された Union file systems
