docker run hello-world

■■■■■■■■■■■■■■■■■■■■■■■■■■ 上記コマンドからの動き
1.Docker クライアントが Docker daemon にコンタクト
2.Docker daemon が Docker Hub から "hello-world" image を pull
// docker pull hello-world コマンドが走ってる

3.Docker daemonが pull いたイメージから 新規コンテナを作成し、
  そのコンテナが executable を run して Hello world を出力
4.Docker daemon が上記出力を Docker クライアントに ストリーム

・ 2 回目移行は、上記 ステップ 2 をスキップする
-------------------------------------------------
