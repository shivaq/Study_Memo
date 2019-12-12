▼ Node/Vertex:
In your case each person will represent a node.

▼ Edge/Link:
The relationship between nodes, in this case 'friends', will create an undirected edge between two nodes.

▼ a graph
a set of nodes and edges
例)A simple binary tree is a graph, a very simple one where the edges only go between a parent and its children.

例)
Imagine a graph that describes the interstate system in the US: we have cities as nodes, and then the edges are the various highways.
We can attach attributes to the nodes and we can attach attributes to the interstates.
Sometimes those attributes will be the same for all nodes (for example, population, area) and for all edges (distance),
but there may be other attributes that do not apply to all cities (name of main island would apply to New York, but not Denver).

例)
 Another example of such a model is Twitter, where the nodes are people on the service and the edges are defined by the "follows" relation.
 (In fact, here the edge is directed, the link has a direction, an arrow. I may be following you, but it doesn't mean you are following me back.)
 Persisting this kind of network model is not geared to a relational database, especially when you want to ask questions like "Am I following someone who is following Joe Blow?".'


■■■■■■■■■■■■■■■■■■■■■■■■■■ 2009年の記事
https://community.devexpress.com/blogs/ctodx/archive/2009/06/17/graph-databases.aspx

▼ key/value を使うのだ。
Whether a node or an edge, you are modeling key/value pairs. Each entity in your model has a key (so you can find it again) and a value (which can be a whole list of properties, themselves key/value pairs).
-------------------------------------------------



▼ ソーシャルグラフを作る上で最初に決めることは、
ノードとエッジが何を表すか、ということだ。

例えば、+1 ボタンを押すごとに、2つの情報が提供される。
WHAT がライクされたかと、WHOがライクしたか、ということ。
上記によって、WHAT から WHO へのエッジが作成できる。

もし、Wikipedia に +1 ボタンが有り、訪問者がそのページが有用だと思ったら +1 をしたとしたら、
誰がどんなページを読んだか、そしてそれらのコネクションを示す、魅力的なグラフができるだろう。

例えば、似た人々が何のページを見ているかを伝えることができる。
同じ興味を持つ tribes を identify することもできるだろう。
スターウォーズファン、スタートレックファン、音楽、科学を学ぶ学生、政治的価値感の似ている人たち。。。

一度グラフが出来上がれば、ソーシャルネットワークの2つの基本的概念を知ることができる。
bonding and bridging

▼ Bonding (also called Closure)
似たマインドの傾向を持つ個人が、彼等同士で接続すること。
Bonds は強いつながりであり、focused and explicit signals of intent and interest.
Bonding の相互作用は、密な相互リンクと、名声や社会的証明のアドの強力な基盤となる傾向がある。

Closure は複数の要素から determine される。
フェイスブックで誰とつながっているか、誰と一緒に学校に行くか、誰と一緒に働いたり遊んだりしてきたか。
これら要素は Propinquity and Homophily とも呼ばれる。
また、個人が、彼等自身と似た他者とつながりを形作る傾向を指したりもする。

Facebook はボンディングネットワーク。友人同士や家族同士の強いつながりが幅を利かせる。


▼ Bridging (also called Brokerage)
広い文脈でのグラフ。
Bridges はソーシャルネットワーク上の構造的ギャップを超えた接続であり、通常では互いにやりとりすることがないであろうノード同士の接続。

ツイッターは弱いつながりを奨励することで、ネットワークを bridging するよう設計されている。
ユーザーは繋がりのない他人をフォローすることができるし、質の高い情報が、クローズドなコミュニティーの中でリツイートを通して拡散する。

▼ ブローカー(仲介者？)
broker は、それまでは繋がりのなかった人たちをつなげることで生計を立てている。
そこには、友達同士にあるような Strong ties ではなく Weak Ties がある。
たとえブローカーを利用したとしても、彼を誕生日会に招く必要はない。
ネットワークの文脈でいうと、ブローカーは離れているコミュニティー間の情報フローを助ける。

▼ Centrality
ブリッジングを適用する際に重要なのは、各ノードのグラフをまたいだ重要性を計算すること。ネットワークアナリストが言うところのCentralityのこと。

例)
Wikipedia で、どのページが最も重要なのか判断したいとする。

アプローチ1：degree centrality
各ページへの接続数をカウントする。
その場合、何百万人もの スターウォーズファンが、The Force Awakens を ライクしたいと判断したとしても、それは Wikipedia の最も重要なページにはならない。

アプローチ2：Eigenvector Centrality (固有ベクター中心性)
影響力のある個人は、他のインフルエンサーとつながる傾向がある、という直感を利用する。
このアイデアには長い歴史がある。影響をどう定義するかによって、中心性の計算方法は、たくさん存在する。

しかし、重要性に関するより subtle なメジャーを使うことは、SF や 映画に関するページなど、より一般的なページが influential になることを意味する。
なぜなら、それらは興味と興味の交差点に存在しているため、より多くの、距離の離れた群衆に対して影響を与える

▼ コミュニティ
あなたのソーシャルグラフを鷹の視点で見下ろすことを想像してみてください。
で、いくつかのノード間の接続が突然消え始めるのを想像してみてください。
もし消え始めたリンクが、我々がブリッジと呼ぶリンクだとしたら、何が起きるでしょうか？

グラフが、セパレートされた島々の群島に断片化されていく様子がビジュアライズされるかもしれない。我々はそれらのグループを、コミュニティと呼ぶ。

コミュニティは、segmentation (or clustering) アルゴリズムで identify することができる。
それは、個人間のソーシャルな cohesion(結束)の程度を決定する。

コミュニティは、アナリストや広告会社からかなりの興味を持たれている。
なぜなら、個人同士の近さは年齢や職業といった任意の属性からは判断できず、
人々が誰と interact するかに基く、創発的な属性だからだ。

コミュニティからは、相互の興味、展望、願望に基づいた、ずっと繊細な洞察を得ることができる。

-------------------------------------------------

■■■■■■■■■■■■■■■■■■■■■■■■■■ ソーシャルネットワークのデータ
▼ 個人のIDと、接続する人々のIdとでできている典型的な例
5988 748 1722 3752 4655 5743 1872 3413 5527 6368 6085 4319 4728 1636 2397 3364 4001 1614 1819 1585 732 2660 3952 2507 3891 2070 2239 2602 612 1352 5447 4548 1596 5488 1605 5517 11 479 2554 2043 17 865 4292 6312 473 534 1479 6375 4456

上記データを最初に pre process する
-------------------------------------------------
1583|6006,2623,6275,2557,3805,2650,3662,3530,1367,4589,4650,1496,5716,2613,2016,4188,1582,2354,5516|9999|WHITE
-------------------------------------------------

pre process の方法は、使いたいアルゴリズムによって変わる。

▼ Map Reduce プログラミングモデルの、 BFS graph traversal
・2つのノードの乖離の程度を見るのに使える

▼ a snippet of BFS using MRJob packageee in python.
-------------------------------------------------
class BFSSocialNetwork(MRJob):
    INPUT_PROTOCOL = RawValueProtocol
    OUTPUT_PROTOCOL = RawValueProtocol
    def configure_options(self):
        super(BFSSocialNetwork, self).configure_options()
        self.add_passthrough_option(
            '--target', help="ID of character we are searching for")

    def mapper(self, key, line):
        node = Node()
        node.fromLine(line)
        if node.color == 'GRAY':
            for connection in node.connections:
                vnode = Node()
                vnode.characterid = connection
                vnode.color = 'GRAY'
                vnode.distance = node.distance + 1
                if (self.options.target == connection):
                    counterName = ("Target ID " + connection +
                        " was hit with distance " + str(vnode.distance))
                    self.increment_counter('Degrees of Separation',
                    counterName, 1)
                yield connection, vnode.getLine()
            node.color = 'BLACK'

        yield node.characterid, node.getLine()

    def reducer(self, key, values ):
        edges = []
        distance = 9999
        color = 'WHITE'

        for value in values:
            node = Node()
            node.fromLine(value)
            if (len(node.connections) > 0):
                edges.extend(node.connections)
            if (node.distance < distance):
                distance = node.distance
            if ( node.color == 'BLACK' ):
                color = 'BLACK'
            if ( node.color == 'GRAY' and color == 'WHITE' ):
                color = 'GRAY'

        node = Node()
        node.characterID = key
        node.distance = distance
        node.color = color
        node.connections = edges
        yield key, node.getLine()

if __name__ == '__main__':
    BFSSocialNetwork.run()
-------------------------------------------------
