

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
        '2101' ,
        '21' ,
        '字典查询' ,
        '/portal/dict/findDictList.do' ,
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
        '2102' ,
        '21' ,
        '字典添加' ,
        '/portal/dict/insertDict.do' ,
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
        '2103' ,
        '21' ,
        '字典删除' ,
        '/portal/dict/deleteDictByDictId.do' ,
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
        '2104' ,
        '21' ,
        '字典变更' ,
        '/protal/dict/updateDict.do' ,
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
VALUES('1', '2101', now() );
INSERT INTO portal.ROLE_RESOURCES
(ROLE_ID, RESOURCES_ID, CREATE_TIME)
VALUES('1', '2102', now() );
INSERT INTO portal.ROLE_RESOURCES
(ROLE_ID, RESOURCES_ID, CREATE_TIME)
VALUES('1', '2103', now() );
