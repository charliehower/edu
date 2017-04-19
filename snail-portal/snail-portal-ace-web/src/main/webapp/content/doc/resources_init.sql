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
        '22' ,
        '1' ,
        '流程管理' ,
        '/workflow/dynamic/console/workflow/index.jsp' ,
        null ,
        null,
        '2' ,
        now() ,
        '1' ,
        'fa fa-fire' ,
        1
    )
;
INSERT INTO portal.ROLE_RESOURCES
(ROLE_ID, RESOURCES_ID, CREATE_TIME)
VALUES('1', '22', now() );
update portal.resources b set b.RESOURCES_URL='/workflow/dynamic/console/workflow/index.jsp'
where b.RESOURCES_ID='22';

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
        '2201' ,
        '22' ,
        '流程查询' ,
        '/workflow/workflow/findWorkflowList.do' ,
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
        '2202' ,
        '22' ,
        '流程部署' ,
        '/workflow/workflow/deploy.do' ,
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
        '2203' ,
        '22' ,
        '流程下线' ,
        '/workflow/workflow/deleteWorkflowlById.do' ,
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
VALUES('1', '2201', now() );
INSERT INTO portal.ROLE_RESOURCES
(ROLE_ID, RESOURCES_ID, CREATE_TIME)
VALUES('1', '2202', now() );
INSERT INTO portal.ROLE_RESOURCES
(ROLE_ID, RESOURCES_ID, CREATE_TIME)
VALUES('1', '2203', now() );
INSERT INTO portal.ROLE_RESOURCES
(ROLE_ID, RESOURCES_ID, CREATE_TIME)
VALUES('1', '23', now() );
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
        '6' ,
        '流程下线' ,
        '/protal/department/delDepartmentByPrimaryKey.do' ,
        null ,
        null,
        '3' ,
        now() ,
        '1' ,
        'fa fa-fire' ,
        1
    )
;
