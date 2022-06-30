use blog;
select * from user;
desc user;
select * from reply;
desc rely;
select * from board;
select count(*) from board;
delete from board where id = 1;

insert into reply(content, boardId, userId, createDate)
values("댓글 1번 글", 1, 1, now());
insert into reply(content, boardId, userId, createDate)
values("댓글 1번 글", 2, 2, now());
insert into reply(content, boardId, userId, createDate)
values("댓글 1번 글", 3, 3, now());
insert into reply(content, boardId, userId, createDate)
values("댓글 1번 글", 4, 4, now());
insert into reply(content, boardId, userId, createDate)
values("댓글 1번 글", 5, 5, now());
insert into reply(content, boardId, userId, createDate)
values("댓글 1번 글", 3, 3, now());
--  create table User (
--        id integer not null auto_increment,
--         createDate datetime(6),
--         email varchar(255),
--         password varchar(255),
--         username varchar(255),
--         primary key (id)
--     ) engine=InnoDB
    
    
--         create table User (
--        id integer not null auto_increment,
--         createDate datetime(6),
--         email varchar(50) not null,
--         password varchar(100) not null,
--         username varchar(30) not null,
--         primary key (id)
--     ) engine=InnoDB

select * from user;
desc user;
select * from board;
desc board;
select * from reply;
desc reply;
create database blog3;
use blog3;
desc board;
select * from board;

delete from board where id=100;
select "test1";
