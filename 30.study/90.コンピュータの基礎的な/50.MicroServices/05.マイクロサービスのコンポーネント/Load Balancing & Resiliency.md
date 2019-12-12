# Load Balancing & Resiliency
* Microservices are often used in environments where scaling and availability are expected.
#### かつてはネットワークデバイスがロードバランシングしてた
* Traditionally, network devices provide load balancing functionality.

#### マクロアーキテクチャのソフトウェアレイヤーがロードバランシングする
* But in a microservices environment, it is more typical to see this moved into the software layer of the macro-architecture’s infrastructure.

* Code through which services communicate
* can utilize service location to discover all network locations of a given service,
* and it can then directly perform load balancing logic right there at the distributed edge.

### Resiliency
* Resiliency means remaining stable even in the face of errors.
* Retries, deadlines, default behaviors, caching behaviors, and queuing
* are a few of the ways microservices provide resiliency.
* some part of resiliency is a perfect match for the infrastructure
* to handle at the edge— such a retries and circuit breaking
* (automatic error responses for services
* exceeding a failure threshold in the recent past).

#### 個々のサービスが what resiliency role it should play を考えるべき
* However, the individual service should consider what resiliency role it should play internally.
* For example, an account signup system,


## 例えば
* アカウントのサインアップシステムは、全てのサインアップのステップが完遂されることを保証することについて、責任を持ちたい
* (サインアップを失うことは、お金を失うことに等しい)
* アカウント作成が成功したら、メールをアカウントオーナーに送信するなど、アカウント作成に時間がかかるステップが追加されるとしても
* ペンディング状態のサインアップをサービス内部でキューさせたり、直接管理したりすることが、このようなミッションクリティカルなサービスにはベストだったりする
