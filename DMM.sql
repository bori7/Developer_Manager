 FUNCTION FN_UPDATE_PWD(F_USER_ID VARCHAR2,
                         F_NEW_PWD VARCHAR2,
                         F_resp_msg OUT VARCHAR2)RETURN VARCHAR2 AS

   v_count     number;
    v_err_msg   VARCHAR2(1000);
    v_resp_code VARCHAR2(10);
    v_user      edc_inv_user%rowtype;

    BEGIN
        SELECT COUNT(1) INTO v_count FROM EDC_INV_USER WHERE USER_ID= F_USER_ID;

        IF(v_count<=0) then
        v_resp_code:='82';
        F_resp_msg:='User does not exist';
        return v_resp_code;

        ELSE
          BEGIN

          END;

    END;