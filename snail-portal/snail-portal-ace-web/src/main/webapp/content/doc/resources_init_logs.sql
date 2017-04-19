INSERT
    INTO
        portal.RESOURCES(
            RESOURCES_ID ,
            PARENT_RESOURCES_ID ,
            RESOURCES_NAME ,
            RESOURCES_URL ,
            RESOURCES_PATH ,
            RESOURCES_ICON ,
            RESOURCES_TYPE ,
            CREATE_TIME ,
            CREATE_USER_ID ,
            REMARK ,
            STAUTS
        )
    VALUES(
        '25' ,
        '1' ,
        '日志' ,
        '/portal/dynamic/console/logs/index.jsp' ,
        null ,
        null,
        '2' ,
        now() ,
        '1' ,
        'fa fa-fire' ,
        1
    )
;
INSERT
    INTO
        portal.RESOURCES(
            RESOURCES_ID ,
            PARENT_RESOURCES_ID ,
            RESOURCES_NAME ,
            RESOURCES_URL ,
            RESOURCES_PATH ,
            RESOURCES_ICON ,
            RESOURCES_TYPE ,
            CREATE_TIME ,
            CREATE_USER_ID ,
            REMARK ,
            STAUTS
        )
    VALUES(
        '2501' ,
        '25' ,
        '日志查询' ,
        '/portal/logs/findList.do' ,
        null ,
        null,
        '3' ,
        now() ,
        '1' ,
        'fa fa-fire' ,
        1
    )
;
INSERT INTO portal.ROLE_RESOURCES
(ROLE_ID, RESOURCES_ID, CREATE_TIME)
VALUES('1', '25', now() );
INSERT INTO portal.ROLE_RESOURCES
(ROLE_ID, RESOURCES_ID, CREATE_TIME)
VALUES('1', '2501', now() );