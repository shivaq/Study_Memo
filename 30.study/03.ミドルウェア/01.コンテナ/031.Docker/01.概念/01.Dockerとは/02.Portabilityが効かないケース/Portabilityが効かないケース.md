# 特定のカーネルバージョンを必要とする場合
- コンテナのアプリが特定のカーネルバージョンを必要とする場合、下記ケースではそのコンテナが使えない
- 異なるバージョンのカーネルを使うマシンの場合
- そのカーネルが、必要とするモジュールを備えていない場合
- VM であれば、VM とカーネルとが一対一のため、そういう問題は生じない

# 特定のハードウェアアーキテクチャのために作られている場合
- x86 のためにつくられたアプリは、ARM ベースのマシン上で動かせない
- VM なら上記問題は生じない