DROP TABLE MEMBER;

CREATE TABLE MEMBER (
    ID     VARCHAR2(50) PRIMARY KEY,
    PW     VARCHAR2(50) NOT NULL,
    NAME   VARCHAR2(50)    NOT NULL,
    BIRTH  DATE    NOT NULL,
    GENDER VARCHAR2(10)    NOT NULL,
    EMAIL  VARCHAR2(50),
    TEL    VARCHAR2(50)    NOT NULL,
    ADDRESS    VARCHAR2(250),    
    RDATE  DATE    DEFAULT SYSDATE
);

SELECT * FROM MEMBER;

INSERT INTO MEMBER
    VALUES ('aaa', '111', '홍길동', '1999/12/12', 'm', 'hong@hong.com', '010-9999-9999', '서울시 종로구', NULL);
    
INSERT INTO MEMBER
    VALUES ('bbb', '111', '고길동', '1998/01/01', 'm', 'ko@ko.com', '010-8888-9999', '서울시 용산구', NULL);

INSERT INTO MEMBER
    VALUES ('ccc', '111', '양길동', '1988/08/08', 'm', 'yang@yang.com', '010-8888-8888', '서울시 성북구', NULL);

INSERT INTO MEMBER
    VALUES ('ddd', '111', '박길동', '1980/11/11', 'm', 'park@park.com', '010-7777-7777', '서울시 강남구', NULL);

COMMIT;

SELECT * FROM MEMBER WHERE ID = 'aaa';
SELECT * FROM MEMBER WHERE EMAIL = 'ko@ko.com';












