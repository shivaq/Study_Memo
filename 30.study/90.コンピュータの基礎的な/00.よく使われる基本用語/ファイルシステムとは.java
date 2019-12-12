■■■■■■■■■■■■■■■■■■■■■■■■■■ Linux におけるファイルシステムとは
・文脈によって、異なる使われ方をする用語
▼ 1.Linux のディレクトリ構造全体を指す
The entire Linux directory structure starting at the top (/) root directory.

▼ 2.データストレージフォーマットの特定のタイプを指す
A specific type of data storage format, such as EXT3, EXT4, BTRFS, XFS, and so on.
Linux supports almost 100 types of filesystems, including some very old ones as well as some of the newest.
Each of these filesystem types uses its own metadata structures to define how the data is stored and accessed.

▼ 3.特定のファイルシステムでフォーマットされたパーティションまたはロジカルボリューム
A partition or logical volume formatted with a specific type of filesystem that can be mounted on a specified mount point on a Linux filesystem.
A file system is the way in which files are named, stored, retrieved as well as updated on a storage disk or partition; the way files are organized on the disk.
A file system is divided in two segments called: User Data and Metadata (file name, time it was created, modified time, it’s size and location in the directory hierarchy etc).





■■■■■■■■■■■■ ファイルとは
・テープやDVD,HD上に配置された情報の集まり。
・メディア上の音楽や映画など

■■■■■■■■■■■■ ファイルシステムとは
・ファイルを正しく格納する
・必要なときに必要なものを使用できるようにする
・DVD のディスクにラベルをつけて分類した仕組みも、ファイルシステムと言えないことはない
・HDなど、データを記録する装置で、どこにどんなファイルが記録されているか管理する仕組み

■■■■■■■■■■■■ Win/Mac におけるファイルシステムとは
・Windows では FAT16、FAT32、NTFS など
・Mac では HFS、HFS+ など

■■■■■■■■■■■■ データのフォーマットとは
・OSののファイルシステムに合わせて記憶領域を区切り、番号を設定すること。
・記録装置の中は、ファイルシステムの領域と、実際にデータを記録するクラスタ領域とにわかれる

■■■■■■■■■■■■ クラスタ領域
・データを保存するために、フォーマット時にディスクの中に作られた区画
・クラスタはファイルを保存するための最小単位
・どのクラスタにどのデータを記録したか、ファイルシステムで管理している
・HD 内のファイルを呼び出すときは、ファイルシステムが何番のクラスタにそれが保存されているかを確認し、データが読み出される
・HD のデータを削除しても、クラスタ領域のデータはそのまま、ファイルシステムの記録だけが消える

■■■■■■■■■■■■ ファイルシステムの機能
・ファイルの保存機能
・暗号化機能
・ファイルのアクセス許可設定
・圧縮機能
