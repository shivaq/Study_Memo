new Vue({
    // app というIDのHTML element をマウントポイントとする
    el: `#app`,
    // data を指定 -> view と data とが同期されるようになる AKA インスタンスのデータを、DOMとdata bind する
    data: {
        submissions: Seed.submissions
    }
})