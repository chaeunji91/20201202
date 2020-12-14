--id, name, sex, stature, reg_date, update_date
create table T_Master(
	id			NUMERIC(22,0) primary key,
	name		varchar2(100),
	sex			SMALLINT,
	stature		REAL,
	reg_date	DATE,
	update_date	DATE
);	
--t/f -> 1/0으로 리터럴에서 표시
insert into T_Master(id, name, sex, stature) values(1, '홍길동', 1, 160.02);