DROP TABLE MEMBER;
CREATE TABLE MEMBER(
    MID VARCHAR2(30) PRIMARY KEY,
    MPW VARCHAR2(30) NOT NULL,
    MNAME VARCHAR2(30) NOT NULL,
    MBIRTH DATE,
    MEMAIL VARCHAR2(30),
    MTEL VARCHAR2(100),
    MADDRESS VARCHAR2(255),
    MRDATE DATE DEFAULT SYSDATE
);
SELECT * FROM MEMBER;
--회원가입
INSERT INTO MEMBER (MID,MPW,MNAME,MBIRTH,MEMAIL,MTEL,MADDRESS,MRDATE)
    VALUES ('aa8','111','홍길동','1989/12/11','abc@naver.com','010-9999-9999','부천시 원미구 도당동',SYSDATE);
INSERT INTO MEMBER (MID,MPW,MNAME,MBIRTH,MEMAIL,MTEL,MADDRESS,MRDATE)
    VALUES ('bb8','111','홍길동','1989/12/11','abc@naver.com','010-9999-9999','부천시 원미구 도당동',SYSDATE);
INSERT INTO MEMBER (MID,MPW,MNAME,MBIRTH,MEMAIL,MTEL,MADDRESS,MRDATE)
    VALUES ('cc9','111','오형욱','1989/12/11','abc@naver.com','010-9999-9999','부천시 원미구 도당동',SYSDATE);
--ID중복체크
SELECT * FROM MEMBER WHERE MID='aaa';
--ID로 DTO가져오기
SELECT * FROM MEMBER WHERE MID='aaa';
--로그인
SELECT * FROM MEMBER WHERE MID='aaa';
--정보수정
UPDATE MEMBER SET  MPW='111',
                    MNAME='홍길동',
                    MBIRTH='1989/12/11',
                    MEMAIL='abc@naver.com',
                    MTEL='010-9999-9999',
                    MADDRESS='부천시 원미구 도당동' 
            WHERE MID='aaa';
--회원목록
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM MEMBER)A)
    WHERE RN BETWEEN 1 AND 3;
--회원수
SELECT COUNT(*) FROM MEMBER;
--회원탈퇴
DELETE FROM MEMBER WHERE MID='ccc';
COMMIT; 


---------------------------------------------------------ADMIN-------------------------------------------------------
DROP TABLE ADMIN;
CREATE TABLE ADMIN(
    aId VARCHAR2(30) PRIMARY KEY,
    aPw VARCHAR2(30) NOT NULL,
    aName VARCHAR2(30) NOT NULL);
-- dummy data
INSERT INTO ADMIN VALUES ('admin','111','관리자');
-- DAO에 넣을 sql
-- admin loginCheck
SELECT * FROM ADMIN WHERE AID='admin' and APW='111';
-- admin aid로 dto 가져오기
SELECT * FROM ADMIN WHERE AID='admin';
COMMIT;
-------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------FAQ-------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------
DROP TABLE FAQ;
DROP SEQUENCE FAQ_SEQ;
CREATE SEQUENCE FAQ_SEQ  MAXVALUE 99999 NOCACHE NOCYCLE;
CREATE TABLE FAQ(
    FNO NUMBER(10) NOT NULL,
    AID VARCHAR(30) REFERENCES ADMIN(AID),
    FTITLE VARCHAR2(4000) NOT NULL,
    FCONTENT VARCHAR2(4000),
    FHIT NUMBER(6),
    FRDATE DATE DEFAULT SYSDATE
);
SELECT * FROM FAQ ORDER BY FNO DESC;
--공지사항 쓰기
INSERT INTO FAQ (FNO,AID,FTITLE,FCONTENT,FHIT)
    VALUES (FAQ_SEQ.NEXTVAL,'admin','비번', '비번을 분실했습니다',0);
--공지사항 목록
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT F.* ,ANAME FROM FAQ F, ADMIN A
                                            WHERE F.AID = A.AID
                                            ORDER BY FNO DESC)A)
    WHERE RN BETWEEN 1 AND 3;
--공지사항 수정
UPDATE FAQ SET FTITLE = '비번',
                FCONTENT='비번을 분실 했습니다',
                FRDATE = SYSDATE
        WHERE FNO=1;
--공지사항 삭제
DELETE FROM FAQ WHERE FNO=1;
--공지사항 조회수 올리기
UPDATE FAQ SET FHIT=FHIT+1 WHERE FNO=1;
--공지사항 상세보기
SELECT F.*,ANAME FROM FAQ F, ADMIN A 
    WHERE F.AID=A.AID AND FNO=1;
--공지사항 글 갯수
SELECT COUNT(*) FROM FAQ;
------MODIFY_VIEW
SELECT F.*,ANAME FROM FAQ F, ADMIN A 
    WHERE F.AID = A.AID AND FNO=3;
COMMIT;

-----------------------------------------------------------------------------------------------------------------------------
-----------------------------------------MOVIE-------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------
DROP TABLE MOVIE;
DROP SEQUENCE MOVIE_SEQ; 
CREATE SEQUENCE MOVIE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE MOVIE(
    MOVIENO NUMBER(5) PRIMARY KEY,
    MOVIENAME VARCHAR2(100) NOT NULL,
     MOVIEDATA VARCHAR2(30),
    MOVIECONTENT VARCHAR2(4000) NOT NULL,
    MOVIERDATE DATE DEFAULT SYSDATE NOT NULL
);
SELECT * FROM MOVIE;
SELECT * FROM MOVIE ORDER BY MOVIENO DESC;
--영화목록
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM MOVIE
                                            ORDER BY MOVIENO DESC)A)
    WHERE RN BETWEEN 1 AND 3;
--영화번호로 영화가져오기
SELECT * FROM MOVIE WHERE MOVIENO=1;
--영화업로드하기
INSERT INTO MOVIE (MOVIENO,MOVIENAME,MOVIEDATA,MOVIECONTENT)
    VALUES (MOVIE_SEQ.NEXTVAL,'건즈 아킴보','movie_79826_1583806792.jpg','익스트림 킬링배틀');
--영화삭제하기
DELETE FROM MOVIE WHERE MOVIENO=3;
--영화정보 수정하기
UPDATE MOVIE SET MOVIENAME='건즈 아킴보',
                    MOVIEDATA='movie_79826_1583806792.jpg',
                    MOVIECONTENT='익스트림 킬링배틀'
            WHERE MOVIENO=41;
--영화 갯수
SELECT COUNT(*) FROM MOVIE;
COMMIT;
select * from movie where movieno=41;
-----------------------------------------------------------------------------------------------------------------------------
---------------------------------------  REVIEW  ----------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------
DROP TABLE REVIEW;
DROP SEQUENCE REVIEW_SEQ;
CREATE SEQUENCE REVIEW_SEQ  MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE REVIEW(
    RNO NUMBER(6) PRIMARY KEY,
    MOVIENO NUMBER(3) REFERENCES MOVIE(MOVIENO),
    MID VARCHAR2(30) REFERENCES MEMBER(MID) NOT NULL,
    RCONTENT VARCHAR2(4000) NOT NULL,
    RRDATE DATE DEFAULT SYSDATE
);
SELECT * FROM REVIEW ORDER BY RRDATE DESC;
--리뷰댓글 목록
SELECT * FROM(SELECT ROWNUM RN, A.* FROM  (SELECT R.*,MNAME FROM REVIEW R, MEMBER M
                                                WHERE movieno=33 and R.MID = M.MID ORDER BY RRDATE DESC)A)
    WHERE RN BETWEEN 1 AND 3;
--리뷰댓글 작성
INSERT INTO REVIEW (RNO,MOVIENO,MID,RCONTENT) 
    VALUES (REVIEW_SEQ.NEXTVAL,'40','aaa','재밌네');
--리뷰댓글 삭제
DELETE FROM REVIEW WHERE MOVIENO=39 AND  rNO=1 AND mId='aaa'  ;
--리뷰댓글 갯수
SELECT COUNT(*) FROM REVIEW;
SELECT * FROM REVIEW;
COMMIT;
-----------------------------------------------------------------------------------------------------------------------------
---------------------------------------   ----------------------------------------------------------------------------
---------------------------------------- FREEBOARD-------------------------------------------------------------------------------------
---------------------------------------------------------------------------------
DROP TABLE FREEBOARD;
DROP SEQUENCE FRE_SEQ;
CREATE SEQUENCE FRE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE FREEBOARD(
    BNO NUMBER(4) PRIMARY KEY,
    MID VARCHAR2(30) REFERENCES MEMBER(MID),
    BTITLE VARCHAR2(30) NOT NULL,
    BCONTENT VARCHAR2(4000),
    BRDATE DATE DEFAULT SYSDATE,
    BHIT NUMBER(4) DEFAULT 0,
    BGROUP NUMBER(4),
    BSTEP NUMBER(4),
    BINDENT NUMBER(4)
);
--게시판 글 목록
SELECT * FROM (SELECT ROWNUM RN,A.* FROM(SELECT F.*,MNAME FROM FREEBOARD F, MEMBER M
                                            WHERE F.MID= M.MID 
                                            ORDER BY BGROUP DESC, BSTEP)A)
        WHERE RN BETWEEN 1 AND 3;
--BNO로 글상세 보기
SELECT * FROM FREEBOARD WHERE BNO=4;
--원글쓰기
INSERT INTO FREEBOARD (BNO,MID,BTITLE,BCONTENT,BGROUP,BSTEP,BINDENT)
    VALUES (FRE_SEQ.NEXTVAL,'aaa','제목','내용',FRE_SEQ.CURRVAL,0,0);
--답변글쓰기
INSERT INTO FREEBOARD (BNO,MID,BTITLE,BCONTENT,BGROUP,BSTEP,BINDENT)
    VALUES (FRE_SEQ.NEXTVAL,'aaa','제목','내용',2,1,1);
--원글 수정
UPDATE FREEBOARD SET BTITLE='제목',
                    BCONTENT='내용'
                WHERE BNO=1;
--원글삭제
DELETE FROM FREEBOARD WHERE BNO=1;
--글 갯수
SELECT COUNT(*) FROM FREEBOARD ;
--HIT수 올리기
UPDATE FREEBOARD SET BHIT = BHIT+1 WHERE BNO=4;
--STEP A
UPDATE FREEBOARD SET BSTEP = BSTEP +1 WHERE BGROUP=1 AND BSTEP>0;
SELECT * FROM FREEBOARD ORDER BY BGROUP DESC, BSTEP;
--수정뷰
SELECT F.*,MNAME FROM FREEBOARD F, MEMBER M
     WHERE F.MID= M.MID AND BNO=2;
COMMIT;
--------------------참조영역------------------------------------------------------
CREATE TABLE EXMEMBER(
    mID VARCHAR2(30) PRIMARY KEY,
    mNAME VARCHAR2(30) );
INSERT INTO EXMEMBER VALUES ('m1','이이이');
INSERT INTO EXMEMBER VALUES ('m2','박박박');
select * from EXMEMBER;
DROP TABLE BOARD;
CREATE TABLE BOARD(
    bId NUMBER(6) PRIMARY KEY,
    mId VARCHAR2(30) REFERENCES EXMEMBER(MID),
    aId VARCHAR2(30) REFERENCES ADMIN(aId),
    bTitle VARCHAR2(100) NOT NULL,
    bRdate    DATE DEFAULT SYSDATE,
    bHit     NUMBER(6) DEFAULT 0);
INSERT INTO BOARD values (1, 'm1', null, '제목',sysdate, 0);
INSERT INTO BOARD values (3, 'm2', null, '제목',sysdate, 0);
INSERT INTO BOARD values (2, null, 'a1', '제목',sysdate, 0);
INSERT INTO BOARD values (4, null, 'a2', '제목',sysdate, 0);
select BID, (SELECT MNAME FROM BOARD B, EXMEMBER M WHERE B.MID=M.MID AND board.BID=BID)|| 
        (SELECT ANAME FROM BOARD B, ADMIN A WHERE A.AID=B.AID AND board.BID=BID) WRITER,
    bTitle, bhit 
    from board ORDER BY bRDATE DESC;











