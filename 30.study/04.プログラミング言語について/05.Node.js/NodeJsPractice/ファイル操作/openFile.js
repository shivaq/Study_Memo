var fs = require('fs');

fs.open('testFile.txt', 'w', function(err, file){
    if(err)throw err;
    console.log('Saved!');
});