# Factor XI: Logs 
## Treat logs as event streams
* a twelve-factor app should not concern itself with the routing or storage of its own log data.
* シンプルな解決策：simply write your log stream to the process's standard output stream (for example, just using                 `fmt.Println(...)`).

### 開発時
* Streaming events to        `stdout` allows a developer to simply watch the event stream on their console when developing the application.

### 本番環境
* In production setups
* you can configure the execution environment to catch the process output
* and send the log stream to a place where it can be processed
* (the possibilities here are endless
*  --  you could store them in your server's journald,
* send them to a syslog server,
* store your logs in an ELK setup,
* or send them to an external cloud service).
