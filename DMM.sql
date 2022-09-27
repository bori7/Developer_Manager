 FUNCTION FN_UPDATE_PWD(F_USER_ID VARCHAR2,
                         F_NEW_PWD VARCHAR2,
                         F_resp_msg OUT VARCHAR2)RETURN VARCHAR2 AS

   v_count     number;
    v_result  VARCHAR2(10);
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
        SELECT * INTO v_user FROM EDC_INV_USER WHERE USER_ID= F_USER_ID;
        BEGIN
            v_result := FN_UPDATE_PWD_HIST(v_user.F_USERNAME, v_user.PASSWORD);
        END;
            UPDATE EDC_INV_USER
                SET PASSWORD= F_NEW_PWD
                WHERE USER_ID= F_USER_ID;
        COMMIT;
                v_resp_code:='00';
                F_resp_msg:='Successful';
                return v_resp_code;
    END;
    PROCEDURE validator(p_search_param VARCHAR2,
                          p_param_value  VARCHAR2,
                          p_resp_code    OUT VARCHAR2,
                          p_resp_msg     OUT VARCHAR2) IS

        v_count number := 0;
      BEGIN
        if (p_search_param is null or p_param_value is null) then
          p_resp_code := '92';
          p_resp_msg  := 'search_param/param_value cannot be null';
          RETURN;
        end if;
        CASE p_search_param
          WHEN 'EMAIL' THEN
            SELECT COUNT(1)
              INTO v_count
              FROM SELECT *
                      FROM EDC_INV_CUSTOMER u
                     WHERE upper(trim(u.email)) = upper(trim(p_param_value));

            IF (v_count > 0) THEN
              p_resp_code := '82';
              p_resp_msg  := 'EMAIL EXIST';
              RETURN;
            ELSE
              p_resp_code := '84';
              p_resp_msg  := 'EMAIL NOT FOUND';
              RETURN;
            END IF;
          WHEN 'PHONE' THEN
            SELECT COUNT(1)
              INTO v_count
              FROM SELECT *
                      FROM EDC_INV_CUSTOMER u
                     WHERE upper(trim(u.phone_no)) = upper(trim(p_param_value);

            IF (v_count > 0) THEN
              p_resp_code := '82';
              p_resp_msg  := 'PHONE NO EXIST';
              RETURN;
            ELSE
              p_resp_code := '84';
              p_resp_msg  := 'PHONE NO NOT FOUND';
              RETURN;
            END IF;
          WHEN 'USERNAME' THEN
            SELECT COUNT(1)
              INTO v_count
              FROM SELECT *
                      FROM EDC_INV_USER t
                     WHERE upper(trim(t.USERNAME)) = upper(trim(p_param_value));

            IF (v_count > 0) THEN
              p_resp_code := '82';
              p_resp_msg  := 'USERNAME EXIST';
              RETURN;
            ELSE
              p_resp_code := '84';
              p_resp_msg  := 'USERNAME NOT FOUND';
              RETURN;
            END IF;

            WHEN 'ACCOUNT' THEN
                        SELECT COUNT(1)
                          INTO v_count
                          FROM SELECT *
                                  FROM EDC_INV_CUSTOMER t
                                 WHERE upper(trim(t.ACCT_NO)) = upper(trim(p_param_value));

                        IF (v_count > 0) THEN
                          p_resp_code := '82';
                          p_resp_msg  := 'ACCOUNT_NO EXIST';
                          RETURN;
                        ELSE
                          p_resp_code := '84';
                          p_resp_msg  := 'ACCOUNT_NO NOT FOUND';
                          RETURN;
                        END IF;

          ELSE
            p_resp_code := '99';
            p_resp_msg  := 'INVALID PARAM';
            RETURN;
        END CASE;

      EXCEPTION
        WHEN OTHERS THEN
          p_resp_code := '99';
          p_resp_msg  := 'FAILED';
          RETURN;
      END validator;
