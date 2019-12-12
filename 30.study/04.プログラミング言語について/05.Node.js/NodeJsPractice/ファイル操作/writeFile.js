var fs = require('fs');

// 上書き
fs.writeFile('testFile.txt', 'Hello! I write something!', function(err){
    if(err) throw err;
    console.log('Saved!');
});