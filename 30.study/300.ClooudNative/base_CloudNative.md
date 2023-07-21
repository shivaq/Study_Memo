# Cloud Native であるための要素


## Automatable
- 人ではなく、マシンによってアプリがデプロイ、管理されるため、共通のスタンダード、フォーマット、インターフェイスに従う必要がある
- K8s はそれらスタンダートなインターフェイスを提供する
- アプリの開発者はスタンダードについて気にしなくてよい


## Ubiquitous and flexible
- 物理的リソース(ディスクなど) や、たまたまコンテナが載っているノードに関する知識は不要
- コンテナ化されたマイクロサービスは、ノードからノードへ、さらには別のクラスタにもかんたんに移動できる


## Resilient and scalable
- トラディショナルなアプリは単一障害点を持ちがち
- メインプロセスがクラッシュしたらアプリが止まる
- ハードウェア障害が発生したら。。。
- ネットワークが輻輳したら。。。
- Cloud Native なアプリは分散されているため、冗長化、グレースフルなデグレードが可能なため、高可用性が高い


## Dynamic
- K8s のようなコンテナオーケストレーターは、コンテナが利用可能なリソースを最大限生かせるように自動調整する
- たくさんのコピーを実行できる
- トラフィックを落とすことなく、ローリングアップデートができる


## Observable
- Cloud Native なアプリはデバッグや分析が難しい
- 分散システムの key requirement は observability である。
- モニタリング、ロギング、トレーシング、メトリクスなどなど
- 開発者が、システムが何を行っているか、どこで間違いをおかしているかを理解するのを助ける



## Distributed
- Cloud の、分散型であること、decentralized であることといった利点を活かすことを目的にアプリを構築しよう、というアプローチである
- アプリをどのように動かすか、ということが商店であり、どこで実行するか、が焦点ではない
- モノリシックに構築するのではなく、複数の、cooperating な分散マイクロサービスによって構成される傾向がある
- マイクロサービスは、一つのことのみを行う、a self-contained service である
- 必要なだけのマイクロサービスを一緒にすれば、アプリが出来上がる