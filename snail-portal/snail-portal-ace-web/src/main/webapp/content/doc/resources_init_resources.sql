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
        '23' ,
        '1' ,
        '资源管理' ,
        '/portal/dynamic/console/resources/index.jsp' ,
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
        '2301' ,
        '23' ,
        '资源查询' ,
        '/portal/resources/findResourcesList.do' ,
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
        '2302' ,
        '23' ,
        '资源添加' ,
        '/portal/resources/insertResources.do' ,
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
        '2303' ,
        '23' ,
        '资源删除' ,
        '/portal/resources/deleteResourcesByResourcesId.do' ,
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
        '2304' ,
        '23' ,
        '资源变更' ,
        '/portal/resources/updateResources.do' ,
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
VALUES('1', '23', now() );
INSERT INTO portal.ROLE_RESOURCES
(ROLE_ID, RESOURCES_ID, CREATE_TIME)
VALUES('1', '2301', now() );
INSERT INTO portal.ROLE_RESOURCES
(ROLE_ID, RESOURCES_ID, CREATE_TIME)
VALUES('1', '2302', now() );
INSERT INTO portal.ROLE_RESOURCES
(ROLE_ID, RESOURCES_ID, CREATE_TIME)
VALUES('1', '2303', now() );
INSERT INTO portal.ROLE_RESOURCES
(ROLE_ID, RESOURCES_ID, CREATE_TIME)
VALUES('1', '2304', now() );
