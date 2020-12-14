drop sequence seq4Board_id;
drop sequence seq4Reply_id;

drop table T_Board;

create sequence seq4Board_id;
create sequence seq4Reply_id;

--id, name
create table T_Board(
	id					numeric(22, 0) primary key,
	name				varchar2(100),
	--공통 관리 정보
	reg_date			date default sysdate,
	update_date			date default sysdate
);
insert into T_Board(id, name)
	values(seq4Board_id.nextval, '공지사항');
insert into T_Board(id, name)
	values(seq4Board_id.nextval, 'FAQ');
insert into T_Board(id, name)
	values(seq4Board_id.nextval, '자유게시판');
--id, content, writer_id, original_id, obj_type, title, board_id, reg_date, update_date
create table T_Reply(
	id					numeric(22, 0) primary key,
	content				varchar2(4000),
	writer_id			numeric(22, 0),
	original_id			numeric(22, 0),
	obj_type			varchar2(100),	--reply, post
	--	post일 경우의 추가 정보
	title				varchar2(500),
	board_id			numeric(22, 0),
	--공통 관리 정보
	reg_date			date default sysdate,
	update_date			date default sysdate
);
create index idx_writerOnRply on T_Reply(writer_id);
create index idx_orgOnRply on T_Reply(original_id);
create index idx_boardOnRply on T_Reply(board_id);

ALTER INDEX SYS_C007508 RENAME TO pk_reply;

create table M_HT_Reply(
     hash_tag_id			numeric(22, 0),
	 reply_id			numeric(22, 0),
	 primary key(hash_tag_id, reply_id) --검색어를 기준으로 게시글 찾기 성능 보장
);
create index idx_MHTR_Rply on M_HT_Reply(reply_id, hash_tag_id); --내 게시글에 달려있는 HashTag 목록 조회 성능

insert into M_HT_Reply(hash_tag_id, reply_id) values(3, 2);

--검색 시 활용할 구문
select p.*
  from T_Reply p, M_HT_Reply m
 where m.hash_tag_id in
		 (SELECT id
		  	FROM T_Hash_Tag
		   START WITH word in ('IT', 'java')
		 CONNECT BY PRIOR id = super_id)
   and m.reply_id = p.id;


