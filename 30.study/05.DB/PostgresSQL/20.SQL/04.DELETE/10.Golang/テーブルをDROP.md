
```sql
-- comments dependent on posts, so cascade comments when you drop posts.
drop table if exists posts cascade;
drop table if exists comments;
```
