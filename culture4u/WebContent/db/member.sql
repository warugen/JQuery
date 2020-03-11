
------------------------------------------------------------------------------------
-- 회원정보
------------------------------------------------------------------------------------
DROP SEQUENCE C_MEMBER_SEQ;
CREATE SEQUENCE C_MEMBER_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
DROP TABLE C_MEMBER;
CREATE TABLE C_MEMBER (
	mId      VARCHAR2(30)  PRIMARY KEY, -- 아이디
	mPw      VARCHAR2(30)  NOT NULL,    -- 비밀번호
	mName    VARCHAR2(30)  NOT NULL,    -- 이름
	mEmail   VARCHAR2(30),              -- 이메일
	mPhoto   VARCHAR2(30),              -- 사진
	mBirth   DATE,                      -- 생년월일
	mAddress VARCHAR2(100),             -- 주소
	mRdate   DATE DEFAULT SYSDATE,      -- 가입등록일
	mLelve   NUMBER(1)     DEFAULT 1    -- 회원레벨
);

-- ID 중복체크
SELECT * FROM C_MEMBER WHERE mId='aaa';

-- 회원가입
INSERT INTO C_MEMBER (mID, mPw, mName, mEmail, mPhoto, mBirth, mAddress)
    VALUES ('aaa','222','홍길동','hong@naver.com','NOIMG.JPG','1990/12/12','종로');
    
INSERT INTO C_MEMBER (mID, mPw, mName, mEmail, mPhoto, mBirth, mAddress)
    VALUES ('bbb','111','박길동','hong@naver.com','NOIMG.JPG','1990/12/12','종로');
    
INSERT INTO C_MEMBER (mID, mPw, mName, mEmail, mPhoto, mBirth, mAddress)
    VALUES ('ccc','111','고길동','hong@naver.com','NOIMG.JPG','1990/12/12','종로');
    
INSERT INTO C_MEMBER (mID, mPw, mName, mEmail, mPhoto, mBirth, mAddress)
    VALUES ('ddd','111','왕길동','hong@naver.com','NOIMG.JPG','1990/12/12','종로');

-- 로그인(mId, mPw)
SELECT * FROM C_MEMBER WHERE mID='aaa' and mPW='222';

-- 회원정보 가져오기 (DTO)
SELECT * FROM C_MEMBER WHERE mId='aaa';

-- 회원정보 수정(mPw, mNAME / mEMAIL, mPHOTO, mBIRTH, mADDRESS 수정 가능)
UPDATE C_MEMBER SET mPw = '111',
                    mName = 'HONG',
                    mEmail = 'yi@naver.com',
                    mPhoto = 'NOIMG.JPG',
                    mBirth = '1991/12/12',
                    mAddress = '서울'
        WHERE mId='aaa';

-- 전체 회원 목록(아이디순, 가입일순) STARTROW, ENDROW
SELECT * FROM (SELECT ROWNUM RN, A.* FROM 
                        (SELECT * FROM C_MEMBER ORDER BY mRdate DESC) A)
        WHERE RN BETWEEN 1 AND 10;

-- 전체 회원 수
SELECT COUNT(*) cnt FROM C_MEMBER;

COMMIT;
------------------------------------------------------------------------------------
-- 자유게시판
------------------------------------------------------------------------------------
DROP SEQUENCE FREEBOARD_SEQ;
CREATE SEQUENCE FREEBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
DROP TABLE FREEBOARD;
CREATE TABLE FREEBOARD (
	fId       NUMBER(6)      PRIMARY KEY, -- 글번호
	mId       VARCHAR2(30)   REFERENCES C_MEMBER(mId),   -- 글쓴이id
	fTitle    VARCHAR2(100)  NOT NULL,    -- 글제목
	fContent  VARCHAR2(4000) ,        -- 글본문
	fFileName VARCHAR2(100)  ,        -- 첨부파일이름
	fRdate    DATE DEFAULT SYSDATE,       -- 작성일
	fHit      NUMBER(6)      DEFAULT 0,        -- 조회수
	fGroup    NUMBER(6)      NOT NULL,        -- 글그룹
	fStep     NUMBER(6)      DEFAULT 0,        -- 그룹내출력순서
	fIndent   NUMBER(6)      DEFAULT 0,        -- 들여쓰기정도
	fIp       VARCHAR2(30)            -- 글쓴IP
);

-- 더미데이터(원글)
INSERT INTO FREEBOARD (fId, mId, fTitle, fContent, fFileName, fGroup, fIp)
    VALUES (FREEBOARD_SEQ.NEXTVAL, 'aaa','title1','content',null, FREEBOARD_SEQ.CURRVAL, '192.168.10.151');

INSERT INTO FREEBOARD (fId, mId, fTitle, fContent, fFileName, fGroup, fIp)
    VALUES (FREEBOARD_SEQ.NEXTVAL, 'bbb','titlebb','content',null, FREEBOARD_SEQ.CURRVAL, '192.168.10.151');

INSERT INTO FREEBOARD (fId, mId, fTitle, fContent, fFileName, fGroup, fIp)
    VALUES (FREEBOARD_SEQ.NEXTVAL, 'ccc','titlecc','content',null, FREEBOARD_SEQ.CURRVAL, '192.168.10.151');

INSERT INTO FREEBOARD (fId, mId, fTitle, fContent, fFileName, fGroup, fIp)
    VALUES (FREEBOARD_SEQ.NEXTVAL, 'ccc','titlecc2','content',null, FREEBOARD_SEQ.CURRVAL, '192.168.10.151');

INSERT INTO FREEBOARD (fId, mId, fTitle, fContent, fFileName, fGroup, fIp)
    VALUES (FREEBOARD_SEQ.NEXTVAL, 'ddd','titledd','content',null, FREEBOARD_SEQ.CURRVAL, '192.168.10.151');
    
SELECT * FROM FREEBOARD;

-- 더미데이터(위의 1번글에 대한 첫번째 답변글)
INSERT INTO FREEBOARD (fId, mId, fTitle, fContent, fFileName, fGroup, fStep, fIndent, fIp)
    VALUES (FREEBOARD_SEQ.NEXTVAL, 'aaa','reply','content', null, 1, 1, 1, '192.168.10.222');
    
select * from FREEBOARD order by fgroup desc, fstep;

-- DAO에 넣을 sql
-- 글목록(startRow부터 endRow까지) - 글번호, 작성자 이름
SELECT F.* ,mName FROM FREEBOARD F, C_MEMBER M WHERE F.MID=M.MID ORDER BY FGROUP DESC, FSTEP;

SELECT * FROM (SELECT ROWNUM RN, A.* 
    FROM (SELECT F.* ,MNAME FROM FREEBOARD F, C_MEMBER M WHERE F.MID=M.MID ORDER BY FGROUP DESC, FSTEP) A)
WHERE RN BETWEEN 1 AND 10;

-- 글갯수
SELECT COUNT(*) FROM FREEBOARD;

-- 글쓰기(원글)
INSERT INTO FREEBOARD (fId, mId, fTitle, fContent, fFileName, fGroup, fIp)
    VALUES (FREEBOARD_SEQ.NEXTVAL, 'aaa','title','content',null, FREEBOARD_SEQ.CURRVAL, '192.168.10.151');

-- FHit 하나 올리기(1번글 조회수 하나 올리기)
UPDATE FREEBOARD SET fHit = fHit + 1
WHERE fId = 1;

-- FId로 글 dto보기
SELECT * FROM FREEBOARD WHERE FID = 1;
SELECT F.*, MNAME FROM FREEBOARD F, C_MEMBER M WHERE M.MID=F.MID AND FID=2;

-- 글 수정하기(FId, FTitle, FContent, FILENAME,  FIp, FRDATE)
UPDATE FREEBOARD SET FTITLE = '제목수정',
                    FCONTENT = '본문수정',
                    FFILENAME = NULL,
                    FIP = '192.11.36.77',
                    FRDATE = '20/03/01'
WHERE FID=5;

-- 글 삭제하기(FId로 삭제하기)
DELETE FROM FREEBOARD WHERE FID = '6';

-- 답변글 추가전 STEP a 수행
UPDATE FREEBOARD SET fStep = fStep +1 WHERE FGROUP=1 AND FSTEP >0;

-- 답변글 쓰기
INSERT INTO FREEBOARD (fId, mId, fTitle, fContent, fFileName, fGroup, fStep, fIndent, fIp)
    VALUES (FREEBOARD_SEQ.NEXTVAL, 'aaa','reply','content', null, 1, 1, 1, '192.168.10.151');
    
INSERT INTO FREEBOARD (fId, mId, fTitle, fContent, fFileName, fGroup, fStep, fIndent, fIp) 
    VALUES (FREEBOARD_SEQ.NEXTVAL, 'fff', '[답]가나다', '1111', null, 8, 0, 0, '127.0.0.1');
    
------------------------------------------------------------------------------------
-- 자유게시판 댓글
------------------------------------------------------------------------------------
DROP SEQUENCE CMT_FREEBOARD_SEQ;
CREATE SEQUENCE CMT_FREEBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
DROP TABLE CMT_FREEBOARD;
CREATE TABLE CMT_FREEBOARD (
	cFno    NUMBER(6)     PRIMARY KEY, -- 댓글번호
	mId     VARCHAR2(30)  REFERENCES C_MEMBER(mId), -- 아이디
	fId     NUMBER(6)     REFERENCES FREEBOARD(fId), -- 글번호
	cFtext  VARCHAR2(100) ,     -- 댓글내용
	cFrdate DATE DEFAULT SYSDATE      -- 댓글작성일
);

-- 댓글 입력하기
INSERT INTO CMT_FREEBOARD (cFno, mId, fId, cFtext)
    VALUES(CMT_FREEBOARD_SEQ.nextval, 'aaa', 1, '댓글임다');

INSERT INTO CMT_FREEBOARD (cFno, mId, fId, cFtext)
    VALUES(CMT_FREEBOARD_SEQ.nextval, 'bbb', 1, '댓글임다2');

INSERT INTO CMT_FREEBOARD (cFno, mId, fId, cFtext)
    VALUES(CMT_FREEBOARD_SEQ.nextval, 'ddd', 2, '댓글임다');

-- 댓글 삭제
DELETE FROM CMT_FREEBOARD WHERE cFno = 1;

-- 댓글 목록 (fId, cFrdate DESC)
SELECT * FROM CMT_FREEBOARD WHERE fId = 1 ORDER BY cfrdate;

-- 댓글 목록 (fId, cFrdate DESC, mId로 mName가져오기)
SELECT C.*, M.mName FROM CMT_FREEBOARD C, C_MEMBER M WHERE c.mid = m.mid AND C.fId = 1 ORDER BY cfrdate DESC;

SELECT * FROM CMT_FREEBOARD ORDER BY cfrdate DESC;

------------------------------------------------------------------------------------
-- 후기게시판
------------------------------------------------------------------------------------
DROP SEQUENCE REVIEW_BOARD_SEQ;
CREATE SEQUENCE REVIEW_BOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
DROP TABLE REVIEW_BOARD;
CREATE TABLE REVIEW_BOARD (
	rId       NUMBER(6)      PRIMARY KEY,               -- 후기글번호
	mId       VARCHAR2(30)   REFERENCES C_MEMBER(mId),  -- 아이디
	rTitle    VARCHAR2(100)  NOT NULL,                  -- 글제목
	rContent  VARCHAR2(4000) NOT NULL,                  -- 글본문
	rFileName VARCHAR2(100),                            -- 첨부파일이름
	rRdate    DATE DEFAULT SYSDATE,                     -- 작성일
	rHit      NUMBER(6)      DEFAULT 0,                 -- 조회수
	rGroup    NUMBER(6),                                -- 글그룹
	rStep     NUMBER(6)      DEFAULT 0,                 -- 그룹내출력순서
	rIndent   NUMBER(6)      DEFAULT 0,                 -- 들여쓰기정도
	rIp       VARCHAR2(30)                              -- 글쓴IP
);

-- 글쓰기(원글)
INSERT INTO REVIEW_BOARD (rId, mId, rTitle, rContent, rFileName, rGroup, rIp)
    VALUES (REVIEW_BOARD_SEQ.NEXTVAL, 'aaa','후기1','후기1', null, REVIEW_BOARD_SEQ.CURRVAL, '192.168.10.151');

-- 더미데이터(원글)
INSERT INTO REVIEW_BOARD (rId, mId, rTitle, rContent, rFileName, rGroup, rIp)
    VALUES (REVIEW_BOARD_SEQ.NEXTVAL, 'aaa','후기2','content',null, REVIEW_BOARD_SEQ.CURRVAL, '192.168.10.151');

INSERT INTO REVIEW_BOARD (rId, mId, rTitle, rContent, rFileName, rGroup, rIp)
    VALUES (REVIEW_BOARD_SEQ.NEXTVAL, 'bbb','후기1BB','content',null, REVIEW_BOARD_SEQ.CURRVAL, '192.168.10.151');

INSERT INTO REVIEW_BOARD (rId, mId, rTitle, rContent, rFileName, rGroup, rIp)
    VALUES (REVIEW_BOARD_SEQ.NEXTVAL, 'ccc','후기cc','content',null, REVIEW_BOARD_SEQ.CURRVAL, '192.168.10.151');

INSERT INTO REVIEW_BOARD (rId, mId, rTitle, rContent, rFileName, rGroup, rIp)
    VALUES (REVIEW_BOARD_SEQ.NEXTVAL, 'ccc','후기cc2','content',null, REVIEW_BOARD_SEQ.CURRVAL, '192.168.10.151');

INSERT INTO REVIEW_BOARD (rId, mId, rTitle, rContent, rFileName, rGroup, rIp)
    VALUES (REVIEW_BOARD_SEQ.NEXTVAL, 'ddd','후기dd','content',null, REVIEW_BOARD_SEQ.CURRVAL, '192.168.10.151');
    
SELECT * FROM REVIEW_BOARD;

-- rHit 하나 올리기(1번글 조회수 하나 올리기)
UPDATE REVIEW_BOARD SET rHit = rHit + 1
WHERE rId = 1;

-- 답변글 추가전 STEP a 수행
UPDATE REVIEW_BOARD SET rStep = rStep +1 WHERE rGroup=2 AND rStep > 0;

-- 더미데이터(위의 1번글에 대한 첫번째 답변글)
INSERT INTO REVIEW_BOARD (rId, mId, rTitle, rContent, rFileName, rGroup, rStep, rIndent, rIp)
    VALUES (REVIEW_BOARD_SEQ.NEXTVAL, 'ddd','reply','content', null, 1, 1, 1, '192.168.10.222');
    
-- 답변글 쓰기
INSERT INTO REVIEW_BOARD (rId, mId, rTitle, rContent, rFileName, rGroup, rStep, rIndent, rIp)
    VALUES (REVIEW_BOARD_SEQ.NEXTVAL, 'aaa','reply','content', null, 1, 1, 1, '192.168.10.151');
    
INSERT INTO REVIEW_BOARD (rId, mId, rTitle, rContent, rFileName, rGroup, rStep, rIndent, rIp)
    VALUES (REVIEW_BOARD_SEQ.NEXTVAL, 'ddd', '[답]가나다', '1111', null, 2, 0, 0, '127.0.0.1');
    
SELECT * FROM REVIEW_BOARD order by rGroup DESC, rStep;

-- DAO에 넣을 sql
-- 글목록(startRow부터 endRow까지) - 글번호, 작성자 이름
SELECT R.* ,mName FROM REVIEW_BOARD R, C_MEMBER M WHERE R.MID=M.MID ORDER BY rGroup DESC, rStep;

SELECT * FROM (SELECT ROWNUM RN, A.* 
    FROM (SELECT F.* ,MNAME FROM REVIEW_BOARD F, C_MEMBER M WHERE F.MID=M.MID ORDER BY rGroup DESC, rStep) A)
WHERE RN BETWEEN 1 AND 10;

-- 글갯수
SELECT COUNT(*) FROM REVIEW_BOARD;


-- rId로 글 dto보기
SELECT * FROM REVIEW_BOARD WHERE rID = 1;
SELECT F.*, MNAME FROM REVIEW_BOARD F, C_MEMBER M WHERE M.MID=F.MID AND rID=2;

-- 글 수정하기(rID, rTitle, rContent, rFileName,  rIp, rRdate)
UPDATE REVIEW_BOARD SET rTitle = '제목수정',
                    rContent = '본문수정',
                    rFileName = NULL,
                    rIp = '192.11.36.77',
                    rRdate = '20/03/01'
WHERE rID=5;

-- 글 삭제하기(FId로 삭제하기)
DELETE FROM REVIEW_BOARD WHERE rID = 2;



------------------------------------------------------------------------------------
-- 후기게시판 댓글
------------------------------------------------------------------------------------
DROP SEQUENCE CMT_REVIEW_SEQ;
CREATE SEQUENCE CMT_REVIEW_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
DROP TABLE CMT_REVIEW;
CREATE TABLE CMT_REVIEW (
	cRno    NUMBER(6)    PRIMARY KEY, -- 댓글번호
	rId     NUMBER(6)    REFERENCES REVIEW_BOARD(rId), -- 후기글번호
	mId     VARCHAR2(30) REFERENCES C_MEMBER(mId), -- 아이디
	cRtext  VARCHAR2(100),     -- 댓글내용
	cRrdate DATE DEFAULT SYSDATE      -- 댓글작성일
);

-- 댓글 입력하기
INSERT INTO CMT_REVIEW (cRno, rId, mId, cRtext)
    VALUES(CMT_REVIEW_SEQ.nextval, 1, 'aaa', '댓글임다');

INSERT INTO CMT_REVIEW (cRno, rId, mId, cRtext)
    VALUES(CMT_REVIEW_SEQ.nextval, 1, 'bbb', '댓글임다2');

INSERT INTO CMT_REVIEW (cRno, rId, mId, cRtext)
    VALUES(CMT_REVIEW_SEQ.nextval, 3, 'ddd','댓글임다');

-- 댓글 삭제
DELETE FROM CMT_REVIEW WHERE cRno = 1;

-- 댓글 목록 (fId, cFrdate DESC)
SELECT * FROM CMT_REVIEW WHERE rId = 1 ORDER BY cRrdate;

-- 댓글 목록 (fId, cFrdate DESC, mId로 mName가져오기)
SELECT C.*, M.mName FROM CMT_REVIEW C, C_MEMBER M WHERE c.mid = m.mid AND C.rId = 1 ORDER BY cRrdate DESC;

SELECT * FROM CMT_REVIEW ORDER BY cRrdate DESC;

