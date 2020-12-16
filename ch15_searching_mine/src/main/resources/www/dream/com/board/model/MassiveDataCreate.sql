insert into T_Reply(id, title, content, writer_id, obj_type, board_id)
select seq4Reply_id.nextval, title, content, writer_id, obj_type, board_id
  from T_Reply
 where board_id = 3;
 
 --홍길동 21번으로 생성
 insert into T_Reply(id, title, content, writer_id, original_id, obj_type, board_id) 
	values(seq4Reply_id.nextval, '자유로운 영혼의 첫번째 글', '한 번 더 잘해봐라', 21, null, 'post', 3);