# Python に main() は存在しない
- Pythonには、main()のように、自動的に走るメソッドは用意されていない。
- 暗黙的に、インデントがトップレベルの関数のみが走る,

## モジュールが直接実行されたか、import されているかを判断する書き方

- モジュールが直接実行された場合以下のメソッド等を実行する、みたく描いたりする

```py
if__name__==__main__:
```

- モジュールが直接実行された場合
 →__name__に__main__がセットされる。

