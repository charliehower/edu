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
        '24' ,
        '1' ,
        '字典类型' ,
        '/portal/dynamic/console/dictCategory/index.jsp' ,
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
        '2401' ,
        '24' ,
        '类型查询' ,
        '/portal/dictCategory/findDictCategoryList.do' ,
        null ,
        null,
        '3' ,
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
        '2402' ,
        '24' ,
        '类型添加' ,
        '/portal/dictCategory/insertDictCategory.do' ,
        null ,
        null,
        '3' ,
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
        '2403' ,
        '24' ,
        '类型删除' ,
        '/portal/dictCategory/deleteDictCategoryByDictCategoryId.do' ,
        null ,
        null,
        '3' ,
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
        '2404' ,
        '24' ,
        '类型变更' ,
        '/protal/dictCategory/updateDictCategory.do' ,
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
VALUES('1', '2401', now() );
INSERT INTO portal.ROLE_RESOURCES
(ROLE_ID, RESOURCES_ID, CREATE_TIME)
VALUES('1', '2402', now() );
INSERT INTO portal.ROLE_RESOURCES
(ROLE_ID, RESOURCES_ID, CREATE_TIME)
VALUES('1', '2403', now() );
INSERT INTO portal.ROLE_RESOURCES
(ROLE_ID, RESOURCES_ID, CREATE_TIME)
VALUES('1', '24', now() );

INSERT INTO portal.ROLE_RESOURCES
(ROLE_ID, RESOURCES_ID, CREATE_TIME)
VALUES('1', '2104', now() );