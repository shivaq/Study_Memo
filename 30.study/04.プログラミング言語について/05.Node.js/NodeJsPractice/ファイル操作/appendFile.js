var fs = require('fs');

fs.appendFile('testFile.txt', 'Hello, content!', function(err){
    if(err) throw err;
    console.log('Saved!');
});