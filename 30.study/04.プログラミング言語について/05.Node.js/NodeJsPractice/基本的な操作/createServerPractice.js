// require() -> use module
var http = require('http');
// reference module inside a same local directory
var dt = require('./moduleTest');

var fs = require('fs');


/**create a server object with HTTP module
 * req - > http.IncomingMessage Obj
 *
 * 下記でアクセス
 * http://localhost:1111
 */
http.createServer(function (req, res) {
    // $1 status code
    // $2 response header
    res.writeHead(200, { 'Content-Type': 'text/html' });
    // write a response to the client
    res.write("The date and time are currently: " + dt.myDateTime());
    // end the response with messages
    res.end('Unko, World!');
}).listen(1111);//the server object listens on port 8080

var url = require('url');

/**
 * 下記でアクセス
 * http://localhost:2222/summer
 */
http.createServer(function (req, res) {
    res.writeHead(200, { 'Content-Type': 'text/html' });
    // req.url ｰ> URLのうち、ドメインネームのあとの部分を保持する
    res.write(req.url)
    res.end();
}).listen(2222);

/**
 * 下記でアクセス
 * http://localhost:3333/?year=2017&month=July
 * 
 * querystring.parse() は、クエリ文字列が「'foo=bar&abc=xyz&abc=123'」だった場合、下記を返す
 * {
 *  foo: 'bar',
 *  abc: ['xyz', '123']
 * }
 */
http.createServer(function (req, res) {
    res.writeHead(200, { 'Content-Type': 'text/html' });
    // parse->URL文字列をパースし、query->URLOBJを返す true→querystring.parse() が、key/value に変換する
    var q = url.parse(req.url, true).query;
    var txt = q.year + " " + q.month;
    res.end(txt);
}).listen(3333);


/**
 * 下記でアクセス
 * http://localhost:4444
 */
http.createServer(function (req, res) {
    fs.readFile('ForNodeJsPractice.html', function (err, data) {
        res.writeHead(200, { 'Content-Type': 'text/html' });
        res.write(data);
        res.end();
    });
}).listen(4444);

/**
 * 下記でアクセス
 * http://localhost:5555/summer.html
 * http://localhost:5555/winter.html
 */
http.createServer(function (req, res) {
    var q = url.parse(req.url, true);
    var filename = "." + q.pathname;
    fs.readFile(filename, function (err, data) {
        if (err) {
            res.writeHead(404, { 'Content-Type': 'text/html' });
            return res.end("404 Not Found");
        }
        res.writeHead(200, { 'Content-Type': 'text/html' });
        res.write(data);
        return res.end();
    });
}).listen(5555);