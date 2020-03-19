DROP SEQUENCE B_SEQ;
CREATE SEQUENCE B_SEQ MAXVALUE 999999999 NOCYCLE NOCACHE;
DROP TABLE B;
CREATE TABLE B(
    BNO NUMBER(9) PRIMARY KEY,
    BTITLE VARCHAR2(100) NOT NULL,
    BCONTENT CLOB,
    BFILE VARCHAR2(4000)
);

INSERT INTO B VALUES (B_SEQ.NEXTVAL, '제목1','내용','nothing.jpg');
commit;