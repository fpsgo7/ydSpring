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

