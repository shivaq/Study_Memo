// var は function 内で有効
function someFunc(){
  for(var i = 0; i<10; i++) {
    console.log(i)
  }
  console.log(i)
}

someFunc();
