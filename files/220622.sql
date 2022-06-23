use blog;
select * from user;

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
