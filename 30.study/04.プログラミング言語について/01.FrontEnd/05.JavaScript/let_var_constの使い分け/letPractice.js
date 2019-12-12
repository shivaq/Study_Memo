// let のスコープは{}が境界となる。
for(let i = 0; i<10; i++) {
  console.log(i)
}
// 上記はOKだが、下記で undefined エラー
console.log(i)
