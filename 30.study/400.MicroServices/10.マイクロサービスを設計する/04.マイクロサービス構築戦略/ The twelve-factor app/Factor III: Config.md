# Factor III: Config 
## Store config in the environment
* Configuration is any kind of data that might vary for different deployment
* for example, connection data and credentials for external services and databases.
* These kinds of data should be passed to the application via environment variables.

#### Go と環境変数
* 環境変数の呼び方
```go
os.Getenv ("VARIABLE_NAME")
```
* 環境変数がたくさんある場合は、you can also resort to libraries
```
github.com/tomazk/envcfg
```
```
github.com/caarlos0/env
```
```
github.com/spf13/viper
```
