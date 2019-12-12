var fs = require('fs');

fs.unlink('testFile.txt', function(err){
    if(err) throw err;
    console.log('File Deleted!');
})