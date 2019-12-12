drop table if exists test;

create table test (
  id serial primary key,
  content text,
  author varchar(255)
);



CREATE SCHEMA myschema;


SET search_path TO myschema, public;



-- * database 作成
-- createdb databasename
--
-- * database 削除;
-- dropdb databasename
