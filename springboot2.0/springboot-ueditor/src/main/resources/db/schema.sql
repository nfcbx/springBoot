CREATE TABLE if not exists user (
id INT,
name VARCHAR
);

create table if not exists notebook (
  id int primary key not null,
  title VARCHAR(100) not null,
  content VARCHAR(999999) null,
  createtime TIMESTAMP not null
);