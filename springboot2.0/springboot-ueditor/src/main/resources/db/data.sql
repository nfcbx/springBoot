-- INSERT  INTO  user values(1,'a');
INSERT  INTO  user values(1, to_char(now(), 'yyyy-MM-dd HH:mm:ss'));

delete from notebook where id = 1;
insert into notebook values (1,'标题','内容','2019-01-01 12:00:00');