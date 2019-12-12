▼ The log-structured school
which only permits appending to files and deleting obsolete files,
but never updates a file that has been written.
Bitcask, SSTables, LSM-trees, LevelDB, Cassandra, HBase, Lucene, and others belong to this group.


▼ The update-in-place school
which treats the disk as a set of fixed-size pages that can be overwritten.
B-trees are the biggest example of this philosophy,
being used in all major relational databases and also many nonrelational ones.


▼ DBエンジンの知識がどう活きるか
As an application developer,
if you’re armed with this knowledge about the internals of storage engines,
you are in a much better position to know which tool is best suited for your particular application.

If you need to adjust a database’s tuning parameters,
this understanding allows you to imagine what effect a higher or a lower value may have.
