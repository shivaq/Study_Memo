



```sql
-- 依存される側
create table posts (
    id serial primary key,
    content text,
    author varchar(255)
);



-- 依存する側
create table comments (
    id serial primary key,
    content text,
    author varchar(255),
    -- foreign key
    post_id integer references posts(id)
);
```
