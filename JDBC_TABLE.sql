CREATE TABLE TB_TEST( 
	TEST_NO NUMBER,
	TEST_TITLE VARCHAR2(30),
	TEST_CONTENT VARCHAR2(100)
);


SELECT * FROM TB_TEST;

DELETE FROM TB_TEST WHERE TEST_NO = 80 ;

DROP TABLE TB_TEST;

