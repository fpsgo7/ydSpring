CREATE TABLE T_ROLE (
     ID           number primary key,
     ROLE_NAME  varchar2(100) not null,
     DESCRIPTION varchar2(100)
 );
 CREATE TABLE T_USER (
  ID           number primary key,
  LOGIN_ID    varchar2(20)   not null,
  PASSWORD  varchar2(200)  not null,
  FULL_NAME  varchar2(100)  not null,
  DEPT_NAME  varchar2(100)  not null
 );
 
 CREATE TABLE T_USER_ROLE (
  ROLE_ID number,
  USER_ID number,
  foreign key (role_id) references t_role(id),
  foreign key (user_id) references t_user(id),
  primary key(role_id, user_id) 
);--///데이터 입력
 insert into t_user values( 1,'user', '1234','사용자','인사');
 insert into t_user values( 2,'admin', '1234','관리자','기획');
 insert into t_role values(1, 'ROLE_USER','일반사용자');
 insert into t_role values(2, 'ROLE_ADMIN','시스템관리자');
 insert into t_user_role values(1, 1);
 insert into t_user_role values(2, 2);
 
 --- 조회 파트
 -- 조인 조회 (필요한 값만 가져오기)
SELECT login_id
    , password
    , role_name
FROM t_user JOIN t_user_role
        ON t_user.id = t_user_role.user_id
        JOIN t_role
        ON t_user_role.role_id = t_role.id
WHERE login_id = 'user';

-- 암호화된 값 대입하기
UPDATE t_user
    SET password = '$2a$10$7q0EO84bLBs.ULhrtk3uGugw/UY/PG/BKnU/nEOlpsFjugMLSrkfi';
commit;

