------------------------------------------------------------------------------------
-- 매거진
------------------------------------------------------------------------------------
DROP SEQUENCE MAGAZINE_SEQ;
CREATE SEQUENCE MAGAZINE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
DROP TABLE MAGAZINE;
CREATE TABLE MAGAZINE (
	zId       NUMBER(6)      PRIMARY KEY, -- 매거진번호
	zTitle    VARCHAR2(100)  NOT NULL, -- 매거진제목
	zContent  VARCHAR2(4000) NOT NULL, -- 매거진본문
	zFileName VARCHAR2(100)  NULL,     -- 첨부파일이름
	zRdate    DATE DEFAULT SYSDATE,     -- 작성일
	zHit      NUMBER(6)      DEFAULT 0,     -- 조회수
	zIp       VARCHAR        NULL,     -- 글쓴IP
	zLike     NUMBER(6)      DEFAULT 0      -- 좋아요숫자
);

-- 글쓰기(원글)
INSERT INTO MAGAZINE (zId, zTitle, zContent, zFileName, rIp)
    VALUES (MAGAZINE_SEQ.NEXTVAL, '후기1','후기1', null, MAGAZINE_SEQ.CURRVAL, '192.168.10.151');

-- 더미데이터(원글)
INSERT INTO MAGAZINE (zId, zTitle, zContent, zFileName, rIp)
    VALUES (MAGAZINE_SEQ.NEXTVAL, 'aaa','후기2','content',null, MAGAZINE_SEQ.CURRVAL, '192.168.10.151');

INSERT INTO MAGAZINE (zId, zTitle, zContent, zFileName, rIp)
    VALUES (MAGAZINE_SEQ.NEXTVAL, 'bbb','후기1BB','content',null, MAGAZINE_SEQ.CURRVAL, '192.168.10.151');

INSERT INTO MAGAZINE (zId, zTitle, zContent, zFileName, rIp)
    VALUES (MAGAZINE_SEQ.NEXTVAL, 'ccc','후기cc','content',null, MAGAZINE_SEQ.CURRVAL, '192.168.10.151');

INSERT INTO MAGAZINE (zId, zTitle, zContent, zFileName, rIp)
    VALUES (MAGAZINE_SEQ.NEXTVAL, 'ccc','후기cc2','content',null, MAGAZINE_SEQ.CURRVAL, '192.168.10.151');

INSERT INTO MAGAZINE (zId, zTitle, zContent, zFileName, rIp)
    VALUES (MAGAZINE_SEQ.NEXTVAL, 'ddd','후기dd','content',null, MAGAZINE_SEQ.CURRVAL, '192.168.10.151');
    
SELECT * FROM MAGAZINE;

-- rHit 하나 올리기(1번글 조회수 하나 올리기)
UPDATE MAGAZINE SET rHit = rHit + 1
WHERE zId = 1;

-- DAO에 넣을 sql
-- 글목록(startRow부터 endRow까지) - 글번호, 작성자 이름
SELECT R.* ,mName FROM MAGAZINE R, C_MEMBER M WHERE R.MID=M.MID ORDER BY rGroup DESC, rStep;

SELECT * FROM (SELECT ROWNUM RN, A.* 
    FROM (SELECT F.* ,MNAME FROM MAGAZINE F, C_MEMBER M WHERE F.MID=M.MID ORDER BY rGroup DESC, rStep) A)
WHERE RN BETWEEN 1 AND 10;

-- 글갯수
SELECT COUNT(*) FROM MAGAZINE;


-- zId로 글 dto보기
SELECT * FROM MAGAZINE WHERE zId = 1;
SELECT F.*, MNAME FROM MAGAZINE F, C_MEMBER M WHERE M.MID=F.MID AND zId=2;

-- 글 수정하기(zId, zTitle, zContent, zFileName,  rIp, rRdate)
UPDATE MAGAZINE SET zTitle = '제목수정',
                    zContent = '본문수정',
                    zFileName = NULL,
                    rIp = '192.11.36.77',
                    rRdate = '20/03/01'
WHERE zId=5;

-- 글 삭제하기(FId로 삭제하기)
DELETE FROM MAGAZINE WHERE zId = 2;


------------------------------------------------------------------------------------
-- 매거진 댓글
------------------------------------------------------------------------------------
DROP SEQUENCE CMT_MAGAZINE_SEQ;
CREATE SEQUENCE CMT_MAGAZINE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
DROP TABLE CMT_MAGAZINE;
CREATE TABLE CMT_MAGAZINE (
	cZno    NUMBER(6)     PRIMARY KEY,              -- 댓글번호
    mId     VARCHAR2(30)  REFERENCES C_MEMBER(mId), -- 아이디
	zId     NUMBER(6)     REFERENCES MAGAZINE(zId), -- 매거진번호
	cZtext  VARCHAR2(100) NULL,                     -- 댓글내용
	cZrdate DATE DEFAULT SYSDATE                    -- 댓글작성일
);

-- 댓글 입력하기
INSERT INTO CMT_REVIEW (cRno, zId, mId, cRtext)
    VALUES(CMT_REVIEW_SEQ.nextval, 1, 'aaa', '댓글임다');

INSERT INTO CMT_REVIEW (cRno, zId, mId, cRtext)
    VALUES(CMT_REVIEW_SEQ.nextval, 1, 'bbb', '댓글임다2');

INSERT INTO CMT_REVIEW (cRno, zId, mId, cRtext)
    VALUES(CMT_REVIEW_SEQ.nextval, 3, 'ddd','댓글임다');

-- 댓글 삭제
DELETE FROM CMT_REVIEW WHERE cRno = 1;

-- 댓글 목록 (fId, cFrdate DESC)
SELECT * FROM CMT_REVIEW WHERE zId = 1 ORDER BY cRrdate;

-- 댓글 목록 (fId, cFrdate DESC, mId로 mName가져오기)
SELECT C.*, M.mName FROM CMT_REVIEW C, C_MEMBER M WHERE c.mid = m.mid AND C.zId = 1 ORDER BY cRrdate DESC;

SELECT * FROM CMT_REVIEW ORDER BY cRrdate DESC;

------------------------------------------------------------------------------------
-- 매거진게시물좋아요스크랩
------------------------------------------------------------------------------------
DROP SEQUENCE MLIKE_SEQ;
CREATE SEQUENCE MLIKE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
DROP TABLE MLIKE;
CREATE TABLE MLIKE (
	mLId NUMBER(6)    PRIMARY KEY,               -- 스크랩번호
	mId VARCHAR2(30) REFERENCES C_MEMBER(mId),  -- 아이디
	zId NUMBER(6)    REFERENCES MAGAZINE(zId)   -- 매거진번호
);

------------------------------------------------------------------------------------
-- 월간공연일정
------------------------------------------------------------------------------------
DROP SEQUENCE MONTHLY_SHOW_SEQ;
CREATE SEQUENCE MONTHLY_SHOW_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
DROP TABLE MONTHLY_SHOW;
CREATE TABLE MONTHLY_SHOW (
	sId        NUMBER(6)     PRIMARY KEY, -- 공연번호
	sTitle     VARCHAR       NOT NULL, -- 공연제목
	sContent   VARCHAR       NOT NULL, -- 공연내용
	sStartDate DATE          NOT NULL, -- 공연시작일
	sEndDate   DATE          NOT NULL, -- 공연종료일
	sPlace     VARCHAR2(100) NOT NULL, -- 공연장소
	sRdate     DATE DEFAULT SYSDATE,     -- 작성일
	sHit      NUMBER(6)     NULL,     -- 조회수
	sIp        VARCHAR2(30)  NULL      -- 글쓴IP
);

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
	cStext  VARCHAR      NULL,     -- 댓글내용
	cSrdate DATE DEFAULT SYSDATE      -- 댓글작성일
);
