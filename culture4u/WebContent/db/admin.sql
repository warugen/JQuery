------------------------------------------------------------------------------------
-- 관리자
------------------------------------------------------------------------------------
DROP TABLE ADMIN;
CREATE TABLE ADMIN (
	aId   VARCHAR2(30) PRIMARY KEY,  -- 관리자id
	aPw   VARCHAR2(30) NOT NULL,     -- 비밀번호
	aName VARCHAR2(30) NOT NULL      -- 이름
);
SELECT * FROM ADMIN;
-- 관리자 등록
INSERT INTO ADMIN VALUES ('admin','111','관리자');
-- DAO에 넣을 sql
-- admin loginCheck
SELECT * FROM ADMIN WHERE AID = 'admin' AND APW = '111';

-- admin aid로 dto 가져오기
SELECT * FROM ADMIN WHERE AID = 'admin';

------------------------------------------------------------------------------------
-- 공지사항
------------------------------------------------------------------------------------
DROP SEQUENCE NOTICE_SEQ;
CREATE SEQUENCE NOTICE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
DROP TABLE NOTICE;
CREATE TABLE NOTICE (
	nId       NUMBER(6)      PRIMARY KEY,           -- 공지사항번호
    aId       VARCHAR2(30)   REFERENCES ADMIN(aId), -- 관리자id
	nTitle    VARCHAR2(100)  NOT NULL,              -- 글제목
	nContent  CLOB,              -- 글본문
	nFileName VARCHAR(100),                  -- 첨부파일이름
	nRdate    DATE DEFAULT SYSDATE,       -- 작성일
	nHit      NUMBER(6)      DEFAULT 0              -- 조회수
);

-- 공지사항 글 쓰기
INSERT INTO NOTICE (nId, aId, nTitle, nContent, nFileName )
    VALUES (NOTICE_SEQ.NEXTVAL, 'admin','title1','content1', null);
        
SELECT * FROM NOTICE;

-- 공지사항 글 보기
SELECT * FROM NOTICE WHERE nId = 1;

-- 공지사항 조회수 올리기
UPDATE NOTICE SET nHit = nHit + 1
WHERE nId = 1;


-- 공지사항 글 수정 (nTitle, nContent, nFileName, nRdate)
UPDATE NOTICE SET nTitle = '제목수정',
                    nContent = '본문수정222',
                    nFileName = NULL,
                    nRdate = SYSDATE
WHERE nId=1;

-- 공지사항 글 삭제
DELETE FROM NOTICE WHERE nId = '6';

-- 공지사항 글 가져오기 (STARTROW, ENDROW)
SELECT * FROM (SELECT ROWNUM RN, A.* 
    FROM (SELECT * FROM NOTICE ORDER BY nRdate DESC) A)
WHERE RN BETWEEN 1 AND 9;

-- 공지사항 글 갯수
SELECT COUNT(*) FROM NOTICE;

COMMIT;
