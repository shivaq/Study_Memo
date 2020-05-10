### レイヤーのコンテンツを確認する



* Dockerホストで下記を実行  
`/var/lib/docker/aufs`





* OSX の場合、Docker マシンに ssh でログインして確認できる

```sh
$ docker-machine ssh default
                        ##         .
                  ## ## ##        ==
               ## ## ## ## ##    ===
#            /"""""""""""""\___/ ===
#       ~~~ {~~ ~~~~ ~~~ ~~~~ ~~~ ~ /  ===- ~~~
#            \______ o           __/
#              \    \         __/
#               \____\_______/
#  _                 _   ____     _            _
# | |__   ___   ___ | |_|___ \ __| | ___   ___| | _____ _ __
# | '_ \ / _ \ / _ \| __| __) / _` |/ _ \ / __| |/ / _ \ '__|
# | |_) | (_) | (_) | |_ / __/ (_| | (_) | (__|   <  __/ |
# |_.__/ \___/ \___/ \__|_____\__,_|\___/ \___|_|\_\___|_|
Boot2Docker version 1.12.3, build HEAD : 7fc7575 - Thu Oct 27 17:23:17 UTC 2016
Docker version 1.12.3, build 6b644ec

docker@default:~$ df -h
Filesystem   Size    Used Available Use% Mounted on
tmpfs      896.2M  192.1M 704.1M 21% /
tmpfs      497.9M       0  497.9M 0% /dev/shm
/dev/sda1   17.9G    2.4G   14.6G 14% /mnt/sda1
cgroup     497.9M       0  497.9M 0% /sys/fs/cgroup
Users      464.8G  110.0G  354.8G 24% /Users
/dev/sda1   17.9G    2.4G   14.6G 14% /mnt/sda1/var/lib/docker/aufs

docker@default:~$ ls /mnt/sda1/var/lib/docker/aufs
diff    layers  mnt
```
