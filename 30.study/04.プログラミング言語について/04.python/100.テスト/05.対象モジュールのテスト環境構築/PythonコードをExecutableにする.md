# script 冒頭に下記を追記して
#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# モードを変えて
chmod +x myecho.py

# ~ に /bin を作って
mkdir -p ~/bin

# script を ~/bin にコピーして

cp myecho.py ~/bin


# ~/bin を PATH に追加する
```bash
# .bash_profile
export PATH = $PATH":$HOME/bin"
```
