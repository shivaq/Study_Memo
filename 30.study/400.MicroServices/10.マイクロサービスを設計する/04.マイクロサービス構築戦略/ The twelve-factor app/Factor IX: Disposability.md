# Factor IX: Disposability
##  Maximize robustness with fast startup and graceful shutdown
* クラウドでは、突然の termination (both intentional, for example, in case of downscaling, and unintentional, in case of failures)が起こりうると想定しておくべき

### A twelve-factor app should have fast startup times
* (typically in the range of a few seconds), allowing it to rapidly deploy new instances.
* Besides, fast startup and graceful termination is another requirement

## 停止時は、OS がアプリに SIGTERM を送ってシャットダウン通知がなされるようにしてる
* When a server shut down, the operating system will typically tell your application to shut down by sending a SIGTERM signal that the application can catch and react to accordingly

## SIGTERM にアプリ側でどう反応するか
* (for example, by stopping to listen on the service port, finishing requests that are currently being processed, and then exiting).
