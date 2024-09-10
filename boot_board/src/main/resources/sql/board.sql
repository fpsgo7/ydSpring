DROP TABLE board;
CREATE TABLE board(
    bno number(6,0) primary key,
    title varchar2(1000) not null,
    contents varchar2(4000) default 'No Content',
    writer varchar2(1000) default 'Any' not null,
    regdate date not null,
    updatedate date default sysdate,
    image varchar2(2000)
);
-- 전체 조회
SELECT bno
    ,title
    ,contents
    ,writer
    ,regdate
    ,updatedate
    ,image
FROM board;
-- 단건 조회
    SELECT bno
        ,title
        ,contents
        ,writer
        ,regdate
        ,updatedate
        ,image
    FROM board
    WHERE bno = 1;

-- 등록
-- 기본키 구하기
SELECT NVL( MAX(bno),0) +1
FROM board;
-- 등록하기
INSERT INTO board (
    bno
    ,title
    ,contents
    ,writer
    ,regdate
    ,image)
VALUES (
    1000
    ,'제목'
    ,'콘텐츠'
    ,'작가'
    ,sysdate
    ,'이미지'
);
-- 전부 입력 X
INSERT INTO board (
    bno
    ,title
    ,regdate)
VALUES (
    100
    ,'제목'
    ,'콘텐츠'
    ,'작가'
    ,sysdate
    ,'이미지'
);