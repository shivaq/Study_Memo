# そういうことはできないっぽい

* 子プロセスから、親プロセスの環境変数をセットはできない。

* 親から子へ、環境変数の Export または 継承 という形で渡すことはできる。子から親へはできない。


* Listen 状態のプロセスに、送り手が送信する、というメカニズムがない限り、２つのプロセスの間でコミュニケートすることがそもそもできない。


Environment variables can only be passed from parent to child (as part of environment export/inheritance), not the other way around.

## 代わりにどうすればよいか

@Andry No, your comment is incorrect. 

That question/answer talks about 
changing environment variable of a certain process while it's running, 
whereas 
this question/answer talks about 
passing environment variable from a child process to the parent process. These are totally orthogonal subjects. – heemayl Feb 22 '19 at 17:48