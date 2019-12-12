# 前のコミットに変更を追加

```sh
git commit --amend --no-edit
```







# 複数のコミットを一つにまとめる


* 4つのコミットをまとめる
* -i -> interactive

```sh
git rebase -i HEAD~4
```



* すると、下記のような画面が表示される


```sh
pick sldfjs commit 1
pick sldfjs commit 2
pick sldfjs commit 3
```



* 吸収されるコミットを fixup に、吸収先を pick のままにする →保存


```sh
pick sldfjs commit 1
fixup sldfjs commit 2
fixup sldfjs commit 3
```
