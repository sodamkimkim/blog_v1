-- 서브쿼리 복습
/**
1. 중첩 서브쿼리 : where절에서 사용하는 쿼리
2. 인라인 뷰 : from절에서 사용하는 쿼리
3. 스칼라 : select절에서 사용하는 쿼리(부하 심화 => 속도가 느릴 수 있다.)

-- board테이블에 연관된 댓글의 정보를 가지고 오기 위해서는 
*/
use blog;
select * from reply;

 -- 서브쿼리를 사용하는 이유??
 /**
  - 서브쿼리를 사용하면 다음과 같은 장점을 지닌다.
  1. 서브쿼리는 쿼리를 구조화 시킴 => 쿼리의 각 부분을 명확하게 구분할 수 있게 해줌.
  2. 서브쿼리는 복잡한 Join이나 Union과 같은 동작을 수행할 때 또 다른 방법을 제공해 준다.
  3. 서브쿼리는 복잡한 Join이나 Union보다 가독성이 높다.
*/

-- 스프링에서는 JPA repository만들 때, 가지고 올 수 있는 데이터 타입(즉 영속성 컨텍스트가 관리하는 녀석은 기본 데이터 타입 + <해당 테이블>)

select *, count(*) as '가공된 컬럼' from board;

-- join 1
select * from board as a
inner join reply as b on a.id=b.boardId
where a.id = 2
order by b.boardId desc;

select * from board;
select * from reply;

-- join 2
select *, count(reply.boardId) from board as a
left join reply as b on a.id = b.boardId;

use movieEx;
select * from 영화;
select * from 배우;
select * from 출연;

select 이름, 평점 
from 영화
where 평점>=9;

select 이름, 평점
from 영화
where 이름 like '%왕%';

select 이름, 평점
from 영화
where 이름 like '%그녀%';

select 번호, 이름
from 영화
where 번호 =1 or 번호 =2 or 번호 =3;

select a.영화번호, a.배우번호, b.이름
from 출연 as a
inner join 배우 as b
on a.배우번호 = b.번호
where a.영화번호 = 1;

select * from 영화;
select * from 출연;
select * from 배우;

use shopdb;

select * from buytbl;

select max(b.price)*b.amount as '젤비싼품목판매액', (select b.username from buytbl where price = max(price))
from buytbl as b
left join usertbl as u
on u.username = b.username;

select * from usertbl;
select * from buytbl;
drop view vip_view;
create view vip_view as select a.*, b.prodName, b.price, b.amount from usertbl as a
inner join buytbl as b
on a.username = b.username
where price >=50
group by a.username;

select * from vip_view;

use mysql;
select * from user;
create user employee11@'%' identified by 'asd123';
grant select on shopdb.vip_view to employee11;

use employees;

select a.dept_no, s.salary
from dept_emp as a
inner join salaries as s
on a.emp_no = s.emp_no
having s.salary >=100000
order by a.dept_no desc
limit 5;

select a.dept_no, s.salary
from dept_emp as a
inner join salaries as s
on a.emp_no = s.emp_no
having s.salary >=100000
order by a.dept_no desc
limit 5;

select a.dept_no, s.salary
from dept_emp as a
inner join salaries as s
on a.emp_no = s.emp_no
having s.salary >=100000
order by a.dept_no desc
limit 5;

select ccc.dept_name, ccc.salary
from (select es.emp_no, es.dept_no, es.salary, d.dept_name
from (select e.emp_no
