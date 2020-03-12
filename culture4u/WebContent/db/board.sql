------------------------------------------------------------------------------------
-- 매거진
------------------------------------------------------------------------------------
DROP SEQUENCE MAGAZINE_SEQ;
CREATE SEQUENCE MAGAZINE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
DROP TABLE MAGAZINE;
CREATE TABLE MAGAZINE (
	zId       NUMBER(6)      PRIMARY KEY,   -- 매거진번호
	zTitle    VARCHAR2(100)  NOT NULL,      -- 매거진제목
	zContent  VARCHAR2(4000) NOT NULL,      -- 매거진본문
	zFileName VARCHAR2(100),                -- 첨부파일이름
	zRdate    DATE DEFAULT SYSDATE,         -- 작성일
	zHit      NUMBER(6)      DEFAULT 0,     -- 조회수
	zIp       VARCHAR2(30),                 -- 글쓴IP
	zLike     NUMBER(6)      DEFAULT 0      -- 좋아요숫자
);

-- 글쓰기(원글)
INSERT INTO MAGAZINE (zId, zTitle, zContent, zFileName, zIp)
    VALUES (MAGAZINE_SEQ.NEXTVAL, '매거진1','컨텐트1', null, '192.168.10.151');

-- 더미데이터(원글)
INSERT INTO MAGAZINE (zId, zTitle, zContent, zFileName, zIp)
    VALUES (MAGAZINE_SEQ.NEXTVAL, '매거진2','컨텐트2', null, '192.168.10.151');

INSERT INTO MAGAZINE (zId, zTitle, zContent, zFileName, zIp)
    VALUES (MAGAZINE_SEQ.NEXTVAL, '매거진3','컨텐트3', null, '192.168.10.151');

INSERT INTO MAGAZINE (zId, zTitle, zContent, zFileName, zIp)
    VALUES (MAGAZINE_SEQ.NEXTVAL, '매거진4','컨텐트4', null, '192.168.10.151');

INSERT INTO MAGAZINE (zId, zTitle, zContent, zFileName, zIp)
    VALUES (MAGAZINE_SEQ.NEXTVAL, '매거진5','컨텐트5', null, '192.168.10.151');

INSERT INTO MAGAZINE (zId, zTitle, zContent, zFileName, zIp)
    VALUES (MAGAZINE_SEQ.NEXTVAL, '매거진6','컨텐트6', null, '192.168.10.151');
    
SELECT * FROM MAGAZINE;

-- zHit 하나 올리기(1번글 조회수 하나 올리기)
UPDATE MAGAZINE SET zHit = zHit + 1
WHERE zId = 1;

-- DAO에 넣을 sql
-- 글목록(startRow부터 endRow까지)
SELECT * FROM MAGAZINE ORDER BY zRdate DESC;

SELECT * FROM (SELECT ROWNUM RN, A.* 
    FROM (SELECT * FROM MAGAZINE ORDER BY zRdate DESC) A)
WHERE RN BETWEEN 1 AND 10;

-- 글갯수
SELECT COUNT(*) FROM MAGAZINE;


-- zId로 글 dto보기
SELECT * FROM MAGAZINE WHERE zId = 1;

-- 글 수정하기(zId, zTitle, zContent, zFileName,  zIp, rRdate)
UPDATE MAGAZINE SET zTitle = '제목수정',
                    zContent = '본문수정',
                    zFileName = NULL,
                    zRdate = '20/03/01'
WHERE zId=1;

-- 글 삭제하기(FId로 삭제하기)
DELETE FROM MAGAZINE WHERE zId = 6;

-- 좋아요 눌렀으면 좋아요숫자 더하기
UPDATE MAGAZINE SET zLike = zLike + 1
WHERE zId = 1;

-- 좋아요 다시 눌렀으면 좋아요숫자 빼기
UPDATE MAGAZINE SET zLike = zLike - 1
WHERE zId = 1;


------------------------------------------------------------------------------------
-- 매거진 댓글
------------------------------------------------------------------------------------
DROP SEQUENCE CMT_MAGAZINE_SEQ;
CREATE SEQUENCE CMT_MAGAZINE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
DROP TABLE CMT_MAGAZINE;
CREATE TABLE CMT_MAGAZINE (
	cZno    NUMBER(6)     PRIMARY KEY,              -- 댓글번호
    zId     NUMBER(6)     REFERENCES MAGAZINE(zId), -- 매거진번호
    mId     VARCHAR2(30)  REFERENCES C_MEMBER(mId), -- 아이디
	cZtext  VARCHAR2(100),                     -- 댓글내용
	cZrdate DATE DEFAULT SYSDATE                    -- 댓글작성일
);

-- 댓글 입력하기
INSERT INTO CMT_MAGAZINE (cZno, zId, mId, cZtext)
    VALUES(CMT_MAGAZINE_SEQ.nextval, 1, 'aaa', '댓글임다');

INSERT INTO CMT_MAGAZINE (cZno, zId, mId, cZtext)
    VALUES(CMT_MAGAZINE_SEQ.nextval, 1, 'bbb', '댓글임다2');

INSERT INTO CMT_MAGAZINE (cZno, zId, mId, cZtext)
    VALUES(CMT_MAGAZINE_SEQ.nextval, 3, 'ddd','댓글임다');
    
SELECT * FROM CMT_MAGAZINE;
    

-- 댓글 수정
UPDATE CMT_MAGAZINE SET cZtext = '제목수정', cZrdate = '20/03/01' WHERE cZno=1;

-- 댓글 삭제
DELETE FROM CMT_MAGAZINE WHERE cZno = 1;

-- 댓글 목록 (fId, cFrdate DESC)
SELECT * FROM CMT_MAGAZINE WHERE zId = 1 ORDER BY cZrdate;

-- 댓글 목록 (fId, cFrdate DESC, mId로 mName가져오기)
SELECT C.*, M.mName FROM CMT_MAGAZINE C, C_MEMBER M WHERE c.mid = m.mid AND C.zId = 1 ORDER BY cZrdate;

SELECT * FROM CMT_MAGAZINE ORDER BY cZrdate;

------------------------------------------------------------------------------------
-- 매거진게시물좋아요스크랩
------------------------------------------------------------------------------------
DROP SEQUENCE MLIKE_SEQ;
CREATE SEQUENCE MLIKE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
DROP TABLE MLIKE;
CREATE TABLE MLIKE (
	mLid NUMBER(6)    PRIMARY KEY,               -- 스크랩번호
	zId NUMBER(6)    REFERENCES MAGAZINE(zId),   -- 매거진번호
    mId VARCHAR2(30) REFERENCES C_MEMBER(mId)  -- 아이디
);

-- 좋아요 누른 게시물 저장하기
INSERT INTO MLIKE (mLid, zId, mId)
    VALUES(MLIKE_SEQ.nextval, 1, 'aaa');
    
INSERT INTO MLIKE (mLid, zId, mId)
    VALUES(MLIKE_SEQ.nextval, 1, 'bbb');
    
INSERT INTO MLIKE (mLid, zId, mId)
    VALUES(MLIKE_SEQ.nextval, 1, 'ccc');
    
INSERT INTO MLIKE (mLid, zId, mId)
    VALUES(MLIKE_SEQ.nextval, 2, 'aaa');
    
SELECT * FROM MLIKE GROUP BY mId;
    
-- 좋아요 취소 게시물 삭제하기
DELETE FROM MLIKE WHERE zId = 1 AND mId = 'aaa';

-- 해당 id로 스크랩한것 가져오기(목록 가져오기)
SELECT * FROM MLIKE WHERE mId = 'aaa' ORDER BY  mLid;


------------------------------------------------------------------------------------
-- 월간공연일정
------------------------------------------------------------------------------------
DROP SEQUENCE MONTHLY_SHOW_SEQ;
CREATE SEQUENCE MONTHLY_SHOW_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
DROP TABLE MONTHLY_SHOW;
CREATE TABLE MONTHLY_SHOW (
	sId        NUMBER(6)     PRIMARY KEY, -- 공연번호
	sTitle     VARCHAR2(100)       NOT NULL, -- 공연제목
	sContent   VARCHAR2(4000)       NOT NULL, -- 공연내용
	sStartDate DATE          NOT NULL, -- 공연시작일
	sEndDate   DATE          NOT NULL, -- 공연종료일
	sPlace     VARCHAR2(100) NOT NULL, -- 공연장소
	sRdate     DATE DEFAULT SYSDATE,     -- 작성일
	sHit       NUMBER(6) DEFAULT 0,     -- 조회수
	sIp        VARCHAR2(30)      -- 글쓴IP
);

-- 글쓰기(원글) 공연번호, 공연제목, 공연내용, 공연시작일, 공연종료일, 공연장소, 작성일, ip
INSERT INTO MONTHLY_SHOW (sId, sTitle, sContent, sStartDate, sEndDate, sPlace, sIp)
    VALUES (MONTHLY_SHOW_SEQ.NEXTVAL, '공연정보1','컨텐트1', '2020/03/01', '20/03/03', '대림미술관', '192.168.10.151');

-- 더미데이터(원글)
INSERT INTO MONTHLY_SHOW (sId, sTitle, sContent, sStartDate, sEndDate, sPlace, sIp)
    VALUES (MONTHLY_SHOW_SEQ.NEXTVAL, '공연정보2','컨텐트2', '2020/03/11', '20/03/20', '대림미술관', '192.168.10.151');

INSERT INTO MONTHLY_SHOW (sId, sTitle, sContent, sStartDate, sEndDate, sPlace, sIp)
    VALUES (MONTHLY_SHOW_SEQ.NEXTVAL, '공연정보3','컨텐트3', '2020/03/21', '20/04/03', '대림미술관', '192.168.10.151');

INSERT INTO MONTHLY_SHOW (sId, sTitle, sContent, sStartDate, sEndDate, sPlace, sIp)
    VALUES (MONTHLY_SHOW_SEQ.NEXTVAL, '공연정보4','컨텐트4', '2020/03/06', '20/04/03', '대림미술관', '192.168.10.151');

INSERT INTO MONTHLY_SHOW (sId, sTitle, sContent, sStartDate, sEndDate, sPlace, sIp)
    VALUES (MONTHLY_SHOW_SEQ.NEXTVAL, '공연정보5','컨텐트5', '2020/04/01', '20/05/03', '대림미술관', '192.168.10.151');

INSERT INTO MONTHLY_SHOW (sId, sTitle, sContent, sStartDate, sEndDate, sPlace, sIp)
    VALUES (MONTHLY_SHOW_SEQ.NEXTVAL, '공연정보6','컨텐트6', '2020/05/01', '20/06/03', '대림미술관', '192.168.10.151');
    
SELECT * FROM MONTHLY_SHOW;

-- zHit 하나 올리기(1번글 조회수 하나 올리기)
UPDATE MONTHLY_SHOW SET sHit = sHit + 1
WHERE sId = 1;

-- DAO에 넣을 sql
-- 글목록(월별로 해당 월 공연만 출력하기)
SELECT * FROM MONTHLY_SHOW WHERE sStartDate >= '20/03/01' AND sStartDate <= '20/03/31';

-- 최종 완성
SELECT * FROM MONTHLY_SHOW WHERE sStartDate >= TRUNC(TO_DATE('20/03', 'YY/MM'), 'MM')  AND sStartDate <= LAST_DAY(TO_DATE('20/03', 'YY/MM')) ORDER BY sStartDate;

SELECT TRUNC(sysdate,'MM') FROM DUAL;

SELECT TRUNC(TO_DATE('20/03', 'YY/MM'), 'MM') FROM DUAL;

SELECT LAST_DAY(sysdate) FROM DUAL;

SELECT LAST_DAY(TO_DATE('20/04', 'YY/MM')) FROM DUAL;

SELECT * FROM (SELECT ROWNUM RN, A.* 
    FROM (SELECT * FROM MONTHLY_SHOW ORDER BY zRdate DESC) A)
WHERE RN BETWEEN 1 AND 10;

-- 글갯수
SELECT COUNT(*) FROM MONTHLY_SHOW;


-- sId로 글 dto보기
SELECT * FROM MONTHLY_SHOW WHERE sId = 1;

-- 글 수정하기(sId, zTitle, zContent, zFileName,  sIp, rRdate)
UPDATE MONTHLY_SHOW SET sTitle = '제목수정',
                    sContent = '본문수정',
                    sStartDate = '20/06/01',
                    sEndDate = '20/07/01',
                    sPlace = '홍대그라운드',
                    sRdate = '20/03/25',
                    sIp = '192.168.2.44'
WHERE sId=1;

-- 글 삭제하기(FId로 삭제하기)
DELETE FROM MONTHLY_SHOW WHERE sId = 6;

------------------------------------------------------------------------------------
-- 월간공연 댓글
------------------------------------------------------------------------------------
DROP SEQUENCE CMT_SHOW_SEQ;
CREATE SEQUENCE CMT_SHOW_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
DROP TABLE CMT_SHOW;
CREATE TABLE CMT_SHOW (
	cSno    NUMBER(6)    PRIMARY KEY, -- 댓글번호
	sId     NUMBER(6)    REFERENCES MONTHLY_SHOW(sId), -- 공연번호
	mId     VARCHAR2(30) REFERENCES C_MEMBER(mId), -- 아이디
	cStext  VARCHAR2(100),     -- 댓글내용
	cSrdate DATE DEFAULT SYSDATE      -- 댓글작성일
);

-- 댓글 입력하기
INSERT INTO CMT_SHOW (cSno, sId, mId, cStext)
    VALUES(CMT_SHOW_SEQ.nextval, 1, 'aaa', '댓글임다');

INSERT INTO CMT_SHOW (cSno, sId, mId, cStext)
    VALUES(CMT_SHOW_SEQ.nextval, 1, 'bbb', '댓글임다2');

INSERT INTO CMT_SHOW (cSno, sId, mId, cStext)
    VALUES(CMT_SHOW_SEQ.nextval, 3, 'ddd','댓글임다');
    
SELECT * FROM CMT_SHOW;
    

-- 댓글 수정
UPDATE CMT_SHOW SET cZtext = '제목수정', cZrdate = '20/03/01' WHERE cSno=1;

-- 댓글 삭제
DELETE FROM CMT_SHOW WHERE cSno = 1;

-- 댓글 목록 (sId, cSrdate DESC)
SELECT * FROM CMT_SHOW WHERE sId = 1 ORDER BY cZrdate;

-- 댓글 목록 (sId, cSrdate DESC, mId로 mName가져오기)
SELECT C.*, M.mName FROM CMT_SHOW C, C_MEMBER M WHERE c.mid = m.mid AND C.sId = 1 ORDER BY cZrdate;

SELECT * FROM CMT_SHOW ORDER BY cZrdate;
