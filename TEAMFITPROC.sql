--===================== drop 테이블&시퀀스=============================
EXECUTE DROP_ALL;
CREATE OR REPLACE PROCEDURE DROP_ALL
IS
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE CART';
    EXECUTE IMMEDIATE 'DROP SEQUENCE CART_SEQ';
    EXECUTE IMMEDIATE 'DROP TABLE MATCHS';
    EXECUTE IMMEDIATE 'DROP SEQUENCE MATCHS_SEQ';
    EXECUTE IMMEDIATE 'DROP TABLE USERT';
    EXECUTE IMMEDIATE 'DROP SEQUENCE USERT_SEQ';
    EXECUTE IMMEDIATE 'DROP TABLE EXERCISE';
    EXECUTE IMMEDIATE 'DROP SEQUENCE EXERCISE_SEQ';
END;
/
SHOW ERROR;

--===================== create 테이블&시퀀스=============================
EXECUTE CREATE_ALL;
CREATE OR REPLACE PROCEDURE CREATE_ALL
IS
BEGIN
    --USER 테이블 생성
    EXECUTE IMMEDIATE 'CREATE TABLE USERT(
    U_NO NUMBER NOT NULL,               --PK 일련번호
    U_ID NVARCHAR2(20) NOT NULL,        --회원 아이디
    U_PW NVARCHAR2(20) NOT NULL,        --회원 비밀번호
    U_NAME NCHAR(4) NOT NULL,           --회원 이름
    U_PHONE NCHAR(13) NOT NULL,         --회원 전화번호
    IS_INSTRUCTOR NCHAR(1) NOT NULL     --강사 여부
    )';
    --USERT 제약조건
    EXECUTE IMMEDIATE 'ALTER TABLE USERT ADD CONSTRAINT USERT_PK PRIMARY KEY (U_NO)';
    EXECUTE IMMEDIATE 'ALTER TABLE USERT ADD CONSTRAINT USERT_ID_UK UNIQUE (U_ID)';
    EXECUTE IMMEDIATE 'ALTER TABLE USERT ADD CONSTRAINT USERT_PHONE_UK UNIQUE (U_PHONE)';
    EXECUTE IMMEDIATE 'ALTER TABLE USERT
                        ADD CONSTRAINT USERT_CHK CHECK (IS_INSTRUCTOR IN (''Y'', ''N''))';
    --USERT SEQUENCE 생성
    EXECUTE IMMEDIATE 'CREATE SEQUENCE USERT_SEQ START WITH 1 INCREMENT BY 1';
    
    --EXERCISE 테이블 생성
    EXECUTE IMMEDIATE 'CREATE TABLE EXERCISE(
    E_NO NUMBER NOT NULL,               --PK 강의 코드(일련번호)
    E_NAME NVARCHAR2(8) NOT NULL,       --강의 운동 종목
    E_PRICE NUMBER(6),                  --강의비
    E_DATE NCHAR(8) NOT NULL,           --강의 날짜
    E_TIME NCHAR(6) NOT NULL,           --강의 시간
    E_ADDR NVARCHAR2(50) NOT NULL,      --강의 주소
    E_MEMCOUNT NUMBER(2) NOT NULL,      --신청 인원
    E_MAXMEM NUMBER(2) NOT NULL         --최대 정원
)';
    --EXERCISE 제약조건
    EXECUTE IMMEDIATE 'ALTER TABLE EXERCISE ADD CONSTRAINT EXERCISE_PK PRIMARY KEY (E_NO)';
    EXECUTE IMMEDIATE 'ALTER TABLE EXERCISE MODIFY (E_MEMCOUNT DEFAULT 0)';
    --EXERCISE SEQ 생성
    EXECUTE IMMEDIATE 'CREATE SEQUENCE EXERCISE_SEQ START WITH 1 INCREMENT BY 1';

    --CART 테이블 생성
    EXECUTE IMMEDIATE 'CREATE TABLE CART(
    C_NO NUMBER NOT NULL,               --PK 카트 일련번호
    U_ID NVARCHAR2(20) NOT NULL,        --수강 신청한 회원 아이디
    E_NO NUMBER NOT NULL,               --강의 코드
    C_ENROLLMENT_DATE DATE NOT NULL,    --강의 신청 날짜
    C_PAYMENT_STATUS NCHAR(1) NOT NULL  --결제 여부
)';
    --CART 제약조건
    EXECUTE IMMEDIATE 'ALTER TABLE CART ADD CONSTRAINT CART_PK PRIMARY KEY (C_NO)';
    EXECUTE IMMEDIATE 'ALTER TABLE CART ADD CONSTRAINT CART_USERT_FK FOREIGN KEY (U_ID)
                        REFERENCES USERT (U_ID) ON DELETE CASCADE';
    EXECUTE IMMEDIATE 'ALTER TABLE CART ADD CONSTRAINT CART_EXERCISE_FK FOREIGN KEY (E_NO)
                        REFERENCES EXERCISE (E_NO) ON DELETE CASCADE';
    EXECUTE IMMEDIATE 'ALTER TABLE CART MODIFY (C_ENROLLMENT_DATE DEFAULT SYSDATE)';
    --CART SEQ 생성
    EXECUTE IMMEDIATE 'CREATE SEQUENCE CART_SEQ START WITH 1 INCREMENT BY 1';
    
    --MATCHS 테이블
    EXECUTE IMMEDIATE 'CREATE TABLE MATCHS(
    M_NO NUMBER NOT NULL,               --PK 일련번호
    INST_ID NVARCHAR2(20) NOT NULL,     --매칭된 강사 아이디
    MEM_ID NVARCHAR2(20) NOT NULL,      --매칭된 회원 아이디
    M_DATE DATE NOT NULL                --매칭 시작 날짜  
)';
    --MATCHS 제약조건
    EXECUTE IMMEDIATE 'ALTER TABLE MATCHS ADD CONSTRAINT MATCHS_PK PRIMARY KEY (M_NO)';
    EXECUTE IMMEDIATE 'ALTER TABLE MATCHS ADD CONSTRAINT MATCHS_USERT_FK_INST FOREIGN KEY (INST_ID)
                        REFERENCES USERT (U_ID) ON DELETE CASCADE';
    EXECUTE IMMEDIATE 'ALTER TABLE MATCHS ADD CONSTRAINT MATCHS_USERT_FK_MEM FOREIGN KEY (MEM_ID)
                        REFERENCES USERT (U_ID) ON DELETE CASCADE';
    EXECUTE IMMEDIATE 'ALTER TABLE MATCHS MODIFY (M_DATE DEFAULT SYSDATE)';
    EXECUTE IMMEDIATE 'ALTER TABLE MATCHS ADD CONSTRAINT MATCHS_MEM_ID_UK UNIQUE (MEM_ID)';
    --MATCHS SEQ 생성
    EXECUTE IMMEDIATE 'CREATE SEQUENCE MATCHS_SEQ START WITH 1 INCREMENT BY 1';
END;
/
SHOW ERROR;

--================================ insert 레코드=============================
execute INSERT_TBL;
create or replace procedure INSERT_TBL
is
begin
    --USERT INSERT
    INSERT INTO USERT VALUES (USERT_SEQ.NEXTVAL, 'tngus1234', '1234', '김수현', '010-1111-1111', 'Y');
    INSERT INTO USERT VALUES (USERT_SEQ.NEXTVAL, 'wjdtn1234', '1234', '김정수', '010-2222-2222', 'N');
    --EXERCISE INSERT
    INSERT INTO EXERCISE VALUES (EXERCISE_SEQ.NEXTVAL, '축구', '50000', '20240521', '13:00', '서울시 서초구 양재동', 0, 20);
    INSERT INTO EXERCISE VALUES (EXERCISE_SEQ.NEXTVAL, '야구', '40000', '20240522', '13:00', '경기도 부천시 소사동', 0, 20);
end;
/
show error;
--===============================create 프로시저=============================
ROLLBACK;
COMMIT;
SELECT * FROM USERT;
SELECT * FROM EXERCISE;
SELECT * FROM CART;
SELECT * FROM MATCHS;
DESC USERT;
DESC EXERCISE;
DESC CART;
DESC MATCHS;
execute CREATE_CRUD;
VARIABLE v_check NUMBER;
VARIABLE v_check1 NUMBER;
VARIABLE v_check2 NUMBER;
EXECUTE USERT_INSERT('wjdtn1234', '1234', '김정수', '010-2222-2222', 'N', :v_check);
EXECUTE USERT_UPDATE('1234', '김정수', '010-2222-2223','wjdtn1234',:v_check);
EXECUTE USERT_DELETE('wjdtn1234', :V_CHECK1, :V_CHECK2);
EXECUTE EXERCISE_INSERT('야구', '40000', '20240522', '13:00', '경기도 부천시 소사동', 0, 20);
EXECUTE EXERCISE_UPDATE('40000', '20240522', '13:00', '경기도 부천시 소사', 1, :V_CHECK);
EXECUTE EXERCISE_DELETE(1, :V_CHECK1 ,:V_CHECK2);
EXECUTE CART_INSERT('tngus1234', 3, 'Y');
EXECUTE CART_INSERT('tngus1234', 4, 'Y');
EXECUTE CART_DELETE(1, :V_CHECK1, :V_CHECK2);
EXECUTE CART_ALL_DELETE('tngus1234', :V_CHECK1, :V_CHECK2);
EXECUTE MATCHS_INSERT('tngus1234', 'wjdtn1234');
EXECUTE  MATCHS_DELETE('wjdtn1234', :V_CHECK1, :V_CHECK2);
-- 결과 출력
create or replace procedure CREATE_CRUD
is
begin
-----------------------------------USERT 테이블 프로시저----------------------
    --회원정보 추가
    EXECUTE IMMEDIATE 'CREATE OR REPLACE PROCEDURE USERT_INSERT(
    V_ID IN USERT.U_ID%TYPE,
    V_PW IN USERT.U_PW%TYPE,
    V_NAME IN USERT.U_NAME%TYPE,
    V_PHONE IN USERT.U_PHONE%TYPE,
    V_IS_INSTRUCTOR IN USERT.IS_INSTRUCTOR%TYPE,
    V_CHECK OUT NUMBER)
    IS
    BEGIN
        INSERT INTO USERT VALUES (USERT_SEQ.NEXTVAL, V_ID, V_PW, V_NAME, V_PHONE, V_IS_INSTRUCTOR);
        SELECT COUNT(*) INTO V_CHECK
        FROM USERT
        WHERE U_ID = V_ID;
    END;';  

    -- 회원정보 수정
    EXECUTE IMMEDIATE ' CREATE OR REPLACE PROCEDURE USERT_UPDATE(
    V_PW IN USERT.U_PW%TYPE,
    V_NAME IN USERT.U_NAME%TYPE,
    V_PHONE IN USERT.U_PHONE%TYPE,
    V_ID IN USERT.U_ID%TYPE,
    V_CHECK OUT NUMBER)
    IS
    BEGIN
        UPDATE USERT SET U_PW = V_PW, U_NAME = V_NAME, U_PHONE = V_PHONE WHERE U_ID = V_ID;
        
        SELECT COUNT(*) INTO V_CHECK FROM USERT
        WHERE U_PW = V_PW AND U_NAME = V_NAME AND U_PHONE = V_PHONE AND  U_ID = V_ID;
    END;';

    --유저 정보 삭제
    EXECUTE IMMEDIATE ' CREATE OR REPLACE PROCEDURE USERT_DELETE(
        V_ID IN USERT.U_ID%TYPE,
        V_CHECK1 OUT NUMBER,
        V_CHECK2 OUT NUMBER)
    IS
    BEGIN
        SELECT COUNT(*) INTO V_CHECK1 FROM USERT WHERE U_ID = V_ID; 
        
        IF V_CHECK1 !=0 THEN
            DELETE FROM USERT WHERE U_ID = V_ID;
        END IF;
        
        SELECT COUNT(*) INTO V_CHECK2 FROM USERT WHERE U_ID = V_ID; 

    END;';
    --유저 정보 출력
    EXECUTE IMMEDIATE 'CREATE OR REPLACE PROCEDURE get_usert_info (
        p_u_id IN USERT.U_ID%TYPE,
        p_u_no OUT USERT.U_NO%TYPE,
        p_u_pw OUT USERT.U_PW%TYPE,
        p_u_name OUT USERT.U_NAME%TYPE,
        p_u_phone OUT USERT.U_PHONE%TYPE,
        p_is_instructor OUT USERT.IS_INSTRUCTOR%TYPE
    ) IS
    BEGIN
        SELECT U_NO, U_PW, U_NAME, U_PHONE, IS_INSTRUCTOR
        INTO p_u_no, p_u_pw, p_u_name, p_u_phone, p_is_instructor
        FROM USERT
        WHERE U_ID = p_u_id;
    END;';
    
    --아이디 확인
    EXECUTE IMMEDIATE 'CREATE OR REPLACE PROCEDURE get_usert_count_by_id (
        p_u_id IN USERT.U_ID%TYPE,
        p_count OUT NUMBER
    ) IS
    BEGIN
        SELECT COUNT(*)
        INTO p_count
        FROM USERT
        WHERE U_ID = p_u_id;
    END;';
    
    --로그인
    EXECUTE IMMEDIATE 'CREATE OR REPLACE PROCEDURE get_usert_count_by_id_pw (
        p_u_id IN USERT.U_ID%TYPE,
        p_u_pw IN USERT.U_PW%TYPE,
        p_count OUT NUMBER
    ) IS
    BEGIN
        SELECT COUNT(*)
        INTO p_count
        FROM USERT
        WHERE U_ID = p_u_id AND U_PW = p_u_pw;
    END;';
    
    --일련번호 가져오기
    EXECUTE IMMEDIATE 'CREATE OR REPLACE PROCEDURE get_u_no_by_id (
        p_u_id IN USERT.U_ID%TYPE,
        p_u_no OUT USERT.U_NO%TYPE
    ) IS
    BEGIN
        SELECT U_NO
        INTO p_u_no
        FROM USERT
        WHERE U_ID = p_u_id;
    END;';
    
    --강사 여부 확인
    EXECUTE IMMEDIATE 'CREATE OR REPLACE PROCEDURE get_is_instructor_by_id (
        p_u_id IN USERT.U_ID%TYPE,
        p_is_instructor OUT USERT.IS_INSTRUCTOR%TYPE
    ) IS
    BEGIN
        SELECT IS_INSTRUCTOR
        INTO p_is_instructor
        FROM USERT
        WHERE U_ID = p_u_id;
    END;';
    
    --강사 목록 출력
    EXECUTE IMMEDIATE 'CREATE OR REPLACE PROCEDURE get_instructors (
        p_result OUT SYS_REFCURSOR
    ) IS
    BEGIN
        OPEN p_result FOR
        SELECT U_NO, U_ID, U_NAME
        FROM USERT
        WHERE IS_INSTRUCTOR = 'Y';
    END;';
    
    --일련번호로 강사아이디 가져오기
    EXECUTE IMMEDIATE 'CREATE OR REPLACE PROCEDURE get_u_id_by_no (
        p_u_no IN USERT.U_NO%TYPE,
        p_u_id OUT USERT.U_ID%TYPE
    ) IS
    BEGIN
        SELECT U_ID
        INTO p_u_id
        FROM USERT
        WHERE U_NO = p_u_no;
    END;';
-----------------------------------EXERCISE 테이블 프로시저----------------------
    
    --EXERCISE 테이블에 레코드 삽입 프로시저
    EXECUTE IMMEDIATE ' CREATE OR REPLACE PROCEDURE EXERCISE_INSERT(
        V_ENAME IN EXERCISE.E_NAME%TYPE,
        V_PRICE IN EXERCISE.E_PRICE%TYPE,
        V_EDATE IN EXERCISE.E_DATE%TYPE,
        V_TIME IN EXERCISE.E_TIME%TYPE,
        V_ADDR IN EXERCISE.E_ADDR%TYPE,
        V_MEMCOUNT IN EXERCISE.E_MEMCOUNT%TYPE,
        V_MAXMEM IN EXERCISE.E_MAXMEM%TYPE)
    IS
    BEGIN
        INSERT INTO exercise VALUES (EXERCISE_SEQ.NEXTVAL, V_ENAME, V_PRICE, V_EDATE, V_TIME, V_ADDR, V_MEMCOUNT, V_MAXMEM);
    END;';
    --EXERCISE 정보 수정
    EXECUTE IMMEDIATE ' CREATE OR REPLACE PROCEDURE EXERCISE_UPDATE(
    V_PRICE IN EXERCISE.E_PRICE%TYPE,
    V_EDATE IN EXERCISE.E_DATE%TYPE,
    V_TIME IN EXERCISE.E_TIME%TYPE,
    V_ADDR IN EXERCISE.E_ADDR%TYPE,
    V_ENO IN EXERCISE.E_NO%TYPE,
    V_CHECK OUT NUMBER)
    IS
    BEGIN
        UPDATE EXERCISE SET E_PRICE = V_PRICE, E_DATE = V_EDATE, E_TIME = V_TIME, E_ADDR = V_ADDR WHERE E_NO = V_ENO;
        SELECT COUNT(*) INTO V_CHECK FROM EXERCISE
        WHERE E_PRICE = V_PRICE AND E_DATE = V_EDATE AND E_TIME = V_TIME AND E_ADDR = V_ADDR AND E_NO = V_ENO;
    END;';
    -- EXERCISE 테이블에 레코드를 삭제하는 프로시저
    EXECUTE IMMEDIATE ' CREATE OR REPLACE PROCEDURE EXERCISE_DELETE(
        V_ENO IN EXERCISE.E_NO%TYPE,
        V_CHECK1 OUT NUMBER,
        V_CHECK2 OUT NUMBER)
    IS
    BEGIN
        SELECT COUNT(*) INTO V_CHECK1 FROM EXERCISE WHERE E_NO = V_ENO;
        
        IF V_CHECK1 = 1 THEN
            DELETE FROM EXERCISE WHERE E_NO = V_ENO;
        END IF;
        
        SELECT COUNT(*) INTO V_CHECK2 FROM EXERCISE WHERE E_NO = V_ENO;
    END;';
    
    --운동 목록 출력
    EXECUTE IMMEDIATE 'CREATE OR REPLACE PROCEDURE get_exercise_data (p_cursor OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN p_cursor FOR
            SELECT * FROM EXERCISE
            ORDER BY E_NO ASC;
    END;';
    
    --강의 검색 출력
    EXECUTE IMMEDIATE 'CREATE OR REPLACE PROCEDURE get_exercise_by_name (
        p_e_name IN EXERCISE.E_NAME%TYPE,
        p_cursor OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_cursor FOR
            SELECT * FROM EXERCISE
            WHERE E_NAME = p_e_name
            ORDER BY E_NO ASC;
    END;';
--------------------CARE table 프로시저------------------------
    --CART 테이블에 레코드 삽입
    EXECUTE IMMEDIATE ' CREATE OR REPLACE PROCEDURE CART_INSERT(
        V_ID IN CART.U_ID%TYPE,
        V_ENO IN CART.E_NO%TYPE,
        V_PAYMENTS IN CART.C_PAYMENT_STATUS%TYPE)
    IS
    BEGIN
        INSERT INTO CART VALUES (CART_SEQ.NEXTVAL, V_ID, V_ENO, SYSDATE, V_PAYMENTS);
    END;';
    
   --CART 테이블 개별삭제
    EXECUTE IMMEDIATE ' CREATE OR REPLACE PROCEDURE CART_DELETE(
        V_CNO IN CART.C_NO%TYPE,
        V_CHECK1 OUT NUMBER,
        V_CHECK2 OUT NUMBER)
    IS
    BEGIN
        SELECT COUNT(*) INTO V_CHECK1 FROM CART WHERE C_NO = V_CNO;
        
        IF V_CHECK1 = 1 THEN
            DELETE FROM CART WHERE C_NO = V_CNO;
        END IF;
        
        SELECT COUNT(*) INTO V_CHECK2 FROM CART WHERE C_NO = V_CNO;
    END;';
    
     --CART 테이블 전체삭제
    EXECUTE IMMEDIATE ' CREATE OR REPLACE PROCEDURE CART_ALL_DELETE(
        V_ID IN CART.U_ID%TYPE,
        V_CHECK1 OUT NUMBER,
        V_CHECK2 OUT NUMBER)
    IS
    BEGIN
        SELECT COUNT(*) INTO V_CHECK1 FROM CART WHERE U_ID = V_ID;
        
        IF V_CHECK1 != 0 THEN
            DELETE FROM CART WHERE U_ID = V_ID;
        END IF;
        
        SELECT COUNT(*) INTO V_CHECK2 FROM CART WHERE U_ID = V_ID;
    END;';
    
    --기존에 신청한 강의인지 확인
    EXECUTE IMMEDIATE 'CREATE OR REPLACE PROCEDURE GET_CART_COUNT (
        p_U_ID IN CART.U_ID%TYPE,
        p_E_NO IN CART.E_NO%TYPE,
        p_COUNT OUT NUMBER
    ) AS
    BEGIN
        SELECT COUNT(*)
        INTO p_COUNT
        FROM CART
        WHERE U_ID = p_U_ID AND E_NO = p_E_NO;
    END GET_CART_COUNT;';
    
    --강의 신청 목록 존재 여부 확인
    EXECUTE IMMEDIATE 'CREATE OR REPLACE PROCEDURE GET_CART_COUNT_BY_USER (
        p_U_ID IN CART.U_ID%TYPE,
        p_COUNT OUT NUMBER
    ) AS
    BEGIN
        SELECT COUNT(*)
        INTO p_COUNT
        FROM CART
        WHERE U_ID = p_U_ID;
    END GET_CART_COUNT_BY_USER;';
    
    --수강 내역 출력
    EXECUTE IMMEDIATE 'CREATE OR REPLACE PROCEDURE GET_CART_DETAILS_BY_USER (
        p_U_ID IN CART.U_ID%TYPE,
        p_cursor OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_cursor FOR
            SELECT C.C_NO AS C_NO, 
                   C.U_ID AS U_ID, 
                   U.U_NAME AS U_NAME, 
                   C.E_NO AS E_NO, 
                   E.E_NAME AS E_NAME, 
                   E.E_DATE AS E_DATE, 
                   E.E_TIME AS E_TIME, 
                   E.E_ADDR AS E_ADDR, 
                   E.E_PRICE AS E_PRICE, 
                   C.C_PAYMENT_STATUS AS C_PAYMENT_STATUS
            FROM CART C, EXERCISE E, USERT U
            WHERE C.U_ID = p_U_ID 
              AND C.E_NO = E.E_NO 
              AND C.U_ID = U.U_ID
            ORDER BY C_NO ASC;
    END GET_CART_DETAILS_BY_USER;';
--------------------MATCHS table 프로시저------------------------
    --MATCHS 테이블에 레코드 삽입
    EXECUTE IMMEDIATE ' CREATE OR REPLACE PROCEDURE MATCHS_INSERT(
        V_INST_ID IN MATCHS.INST_ID%TYPE,
        V_MEM_ID IN MATCHS.MEM_ID%TYPE)
    IS
    BEGIN
        INSERT INTO MATCHS VALUES(MATCHS_SEQ.NEXTVAL, V_INST_ID, V_MEM_ID, SYSDATE);
    END;';
    
   --MATCHS 테이블 삭제
    EXECUTE IMMEDIATE ' CREATE OR REPLACE PROCEDURE MATCHS_DELETE(
        V_MEM_ID IN MATCHS.MEM_ID%TYPE,
        V_CHECK1 OUT NUMBER,
        V_CHECK2 OUT NUMBER)
    IS
    BEGIN
        SELECT COUNT(*) INTO V_CHECK1 FROM MATCHS WHERE MEM_ID = V_MEM_ID;
        
        IF V_CHECK1 = 1 THEN
            DELETE FROM MATCHS WHERE MEM_ID = V_MEM_ID;
        END IF;
        
        SELECT COUNT(*) INTO V_CHECK2 FROM MATCHS WHERE MEM_ID = V_MEM_ID;
    END;';
    
    --강사 매칭 확인
    EXECUTE IMMEDIATE 'CREATE OR REPLACE PROCEDURE GET_MATCHS_COUNT (
        p_mem_id IN MATCHS.MEM_ID%TYPE,
        p_count OUT NUMBER
    ) IS
    BEGIN
        SELECT COUNT(*) INTO p_count
        FROM MATCHS
        WHERE MEM_ID = p_mem_id;
    END;';
    
    --내 강사 목록 출력
    EXECUTE IMMEDIATE 'CREATE OR REPLACE PROCEDURE GET_MATCHS_DETAILS (
        p_mem_id IN MATCHS.MEM_ID%TYPE,
        cur OUT SYS_REFCURSOR
    ) IS
    BEGIN
        OPEN cur FOR
        SELECT M.M_NO AS M_NO, 
               M.MEM_ID AS MEM_ID,
               U.U_NAME AS INST_NAME, 
               U.U_PHONE AS INST_PHONE, 
               M.M_DATE AS M_DATE 
        FROM MATCHS M 
        INNER JOIN USERT U ON M.INST_ID = U.U_ID 
        WHERE M.MEM_ID = p_mem_id;
    END;';
 end;
/
show error; 

--신청 인원을 늘리는 트리거
CREATE OR REPLACE TRIGGER trg_cart_insert
    AFTER INSERT ON CART
    FOR EACH ROW
    BEGIN
        UPDATE EXERCISE
        SET E_MEMCOUNT = E_MEMCOUNT + 1
        WHERE E_NO = :NEW.E_NO;
END;
/

--신청 인원을 줄이는 트리거
CREATE OR REPLACE TRIGGER trg_cart_delete
    AFTER DELETE ON CART
    FOR EACH ROW
    BEGIN
        UPDATE EXERCISE
        SET E_MEMCOUNT = E_MEMCOUNT - 1
        WHERE E_NO = :OLD.E_NO;
END;
/

GRANT EXECUTE ON CREATE_ALL TO TEAMFIT;
GRANT EXECUTE ON DROP_ALL TO TEAMFIT;
GRANT CREATE ANY TABLE TO TEAMFIT;
GRANT DROP ANY TABLE TO TEAMFIT;
GRANT SELECT, INSERT, UPDATE, DELETE ON USERT TO TEAMFIT;
GRANT SELECT, INSERT, UPDATE, DELETE ON EXERCISE TO TEAMFIT;
GRANT SELECT, INSERT, UPDATE, DELETE ON CART TO TEAMFIT;
GRANT SELECT, INSERT, UPDATE, DELETE ON MATCHS TO TEAMFIT;
GRANT CREATE SEQUENCE TO TEAMFIT;
GRANT SELECT ON USERT_SEQ TO TEAMFIT;
GRANT SELECT ON EXERCISE_SEQ TO TEAMFIT;
GRANT SELECT ON CART_SEQ TO TEAMFIT;
GRANT SELECT ON MATCHS_SEQ TO TEAMFIT;
