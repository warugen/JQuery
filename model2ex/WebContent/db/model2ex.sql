DROP TABLE FILEBOARD;
DROP SEQUENCE FILEBOARD_SEQ;
CREATE SEQUENCE FILEBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE FILEBOARD(
    fId NUMBER(6) PRIMARY KEY,
    mId VARCHAR2(30) REFERENCES MVC_MEMBER(MID),
    fTitle VARCHAR2(100) NOT NULL,
    fContent VARCHAR2(1000),
    fFileName VARCHAR2(30),
    fRdate    DATE DEFAULT SYSDATE,
    fHit     NUMBER(6) DEFAULT 0,
    fGroup   NUMBER(6) NOT NULL,
    fStep    NUMBER(3) NOT NULL,
    fIndent  NUMBER(3) NOT NULL,
    fIp      VARCHAR2(20) NOT NULL);

-- 더미데이터(원글)
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME,  
        FGROUP, FSTEP, FINDENT, FIP)
    VALUES (FILEBOARD_SEQ.NEXTVAL, 'aaa','title','content',null, 
        FILEBOARD_SEQ.CURRVAL, 0, 0, '192.168.10.151');
SELECT * FROM FILEBOARD;
-- 더미데이터(위의 1번글에 대한 첫번째 답변글)
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME,
        FGROUP, FSTEP, FINDENT, FIP)
    VALUES (FILEBOARD_SEQ.NEXTVAL, 'aaa','reply','content', null,
        1, 1, 1, '192.168.10.151');
select * from fileboard order by fgroup desc, fstep;
-- DAO에 넣을 sql
-- 글목록(startRow부터 endRow까지) - 글번호, 작성자 이름
SELECT F.* ,MNAME FROM FILEBOARD F, MVC_MEMBER M WHERE F.MID=M.MID ORDER BY FGROUP DESC, FSTEP;

SELECT * FROM (SELECT ROWNUM RN, A.* 
    FROM (SELECT F.* ,MNAME FROM FILEBOARD F, MVC_MEMBER M WHERE F.MID=M.MID ORDER BY FGROUP DESC, FSTEP) A)
WHERE RN BETWEEN 3 AND 9;

-- 글갯수
SELECT COUNT(*) FROM FILEBOARD;

-- 글쓰기(원글)
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME, FGROUP, FSTEP, FINDENT, FIP)
    VALUES (FILEBOARD_SEQ.NEXTVAL, 'aaa','title','content',null, FILEBOARD_SEQ.CURRVAL, 0, 0, '192.168.10.151');

-- FHit 하나 올리기(1번글 조회수 하나 올리기)
UPDATE FILEBOARD SET FHIT = FHIT + 1
WHERE FID = 2;

-- FId로 글 dto보기
SELECT * FROM FILEBOARD WHERE FID = 1;
SELECT F.*, MNAME FROM FILEBOARD F, MVC_MEMBER M WHERE M.MID=F.MID AND FID=8;

-- 글 수정하기(FId, FTitle, FContent, FILENAME,  FIp, FRDATE)
UPDATE FILEBOARD SET FTITLE = '제목수정',
                    FCONTENT = '본문수정',
                    FFILENAME = NULL,
                    FIP = '192.11.36.77',
                    FRDATE = '20/03/01'
WHERE FID=5;

-- 글 삭제하기(FId로 삭제하기)
DELETE FROM FILEBOARD WHERE FID = '6';

--    답변글 추가전 STEP a 수행
UPDATE FILEBOARD SET FSTEP = FSTEP +1 WHERE FGROUP=1 AND FSTEP >0;

-- 답변글 쓰기
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME, FGROUP, FSTEP, FINDENT, FIP)
    VALUES (FILEBOARD_SEQ.NEXTVAL, 'aaa','reply','content', null, 1, 1, 1, '192.168.10.151');
    
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME, FGROUP, FSTEP, FINDENT, FIP) 
    VALUES (FILEBOARD_SEQ.NEXTVAL, 'fff', '[답]가나다', '1111', null, 8, 0, 0, ?)

COMMIT;
----------------------------------------------------------------------
--                          admin table                             --
----------------------------------------------------------------------
DROP TABLE ADMIN;
CREATE TABLE ADMIN(
    aId VARCHAR2(30) PRIMARY KEY,
    aPw VARCHAR2(30) NOT NULL,
    aName VARCHAR2(30) NOT NULL);
-- dummy data
INSERT INTO ADMIN VALUES ('admin','111','김관리');
-- DAO에 넣을 sql
-- admin loginCheck
SELECT * FROM ADMIN WHERE AID = 'admin' AND APW = '111';

-- admin aid로 dto 가져오기
SELECT * FROM ADMIN WHERE AID = 'admin';

COMMIT;