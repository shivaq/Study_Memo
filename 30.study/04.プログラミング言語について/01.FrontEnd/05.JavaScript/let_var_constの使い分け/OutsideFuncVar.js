// var は function 内で有効.つまり
function someFunc(){
  for(var i = 0; i<10; i++) {
    console.log(i)
  }
  console.log(i)
}

someFunc();
// i は undefined ということで、エラーとなる
console.log(i)
