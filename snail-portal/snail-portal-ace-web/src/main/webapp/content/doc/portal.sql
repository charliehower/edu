-------------------------------------------------
-- Export file for user PORTAL                 --
-- Created by chenxiaoke on 2014/9/1, 13:24:16 --
-------------------------------------------------

spool portal.log

prompt
prompt Creating table COMPANY
prompt ======================
prompt
create table COMPANY
(
  company_id     VARCHAR2(50) not null,
  company_type   VARCHAR2(50) not null,
  company_name   VARCHAR2(50) not null,
  area_code      VARCHAR2(6) not null,
  address        VARCHAR2(200),
  content        VARCHAR2(500),
  contact        VARCHAR2(50),
  tel            VARCHAR2(50),
  email          VARCHAR2(50),
  create_time    DATE not null,
  create_user_id VARCHAR2(50) not null,
  stauts         CHAR(1) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table COMPANY
  is '公司';
comment on column COMPANY.company_id
  is '公司编号';
comment on column COMPANY.company_type
  is '公司类别';
comment on column COMPANY.company_name
  is '公司名称';
comment on column COMPANY.area_code
  is '公司所属地区编号';
comment on column COMPANY.address
  is '公司地址';
comment on column COMPANY.content
  is '公司简介';
comment on column COMPANY.contact
  is '联系人';
comment on column COMPANY.tel
  is '联系电话';
comment on column COMPANY.email
  is '电子邮箱';
comment on column COMPANY.create_time
  is '创建时间';
comment on column COMPANY.create_user_id
  is '创建人编号';
comment on column COMPANY.stauts
  is '状态（0作废正常1）';
alter table COMPANY
  add constraint PK_COMPANY primary key (COMPANY_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table COMPANY_CATEGORY
prompt ===============================
prompt
create table COMPANY_CATEGORY
(
  name   VARCHAR2(50),
  code   VARCHAR2(50) not null,
  stauts CHAR(1) default '1'
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table COMPANY_CATEGORY
  is '公司类别';
alter table COMPANY_CATEGORY
  add constraint PK_COMPANY_CATEGORY primary key (CODE)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table DEPARTMENT
prompt =========================
prompt
create table DEPARTMENT
(
  department_id        VARCHAR2(50) not null,
  parent_department_id VARCHAR2(50) not null,
  department_name      VARCHAR2(50) not null,
  department_level     NUMBER default 3 not null,
  user_id              VARCHAR2(50) default '1' not null,
  create_time          DATE default sysdate not null,
  create_user_id       VARCHAR2(50) default '1' not null,
  stauts               CHAR(1) default 1 not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table DEPARTMENT
  is '部门';
comment on column DEPARTMENT.department_id
  is '部门编号';
comment on column DEPARTMENT.parent_department_id
  is '上级部门编号';
comment on column DEPARTMENT.department_name
  is '部门名称';
comment on column DEPARTMENT.department_level
  is '部门级别';
comment on column DEPARTMENT.user_id
  is '负责人编号';
comment on column DEPARTMENT.create_time
  is '创建时间';
comment on column DEPARTMENT.create_user_id
  is '创建人编号';
comment on column DEPARTMENT.stauts
  is '状态（0停用正常1）';
alter table DEPARTMENT
  add constraint PK_DEPARTMENT primary key (DEPARTMENT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table DICT
prompt ===================
prompt
create table DICT
(
  table_name VARCHAR2(30) not null,
  dict_name  VARCHAR2(50) not null,
  auto_load  NUMBER default 1,
  load_sql   VARCHAR2(200),
  indate     DATE default sysdate
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table DICT
  is '参数列表及定义自动加载';
comment on column DICT.table_name
  is '表名';
comment on column DICT.dict_name
  is '字典名称';
comment on column DICT.auto_load
  is '是否启动加载';
comment on column DICT.load_sql
  is '加载参数所用的SQL';
alter table DICT
  add constraint PK_DICT primary key (TABLE_NAME)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FILES
prompt ====================
prompt
create table FILES
(
  file_id       VARCHAR2(50) not null,
  file_name     VARCHAR2(50) not null,
  file_ext_name VARCHAR2(50) not null,
  file_content  BLOB not null,
  create_time   DATE default sysdate not null,
  operater      VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FILES
  is '文件';

prompt
prompt Creating table FILE_RESOURCE
prompt ============================
prompt
create table FILE_RESOURCE
(
  file_id       VARCHAR2(50),
  file_name     VARCHAR2(50),
  file_ext_name VARCHAR2(50),
  file_content  BLOB,
  create_time   DATE,
  operater      VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table FILE_RESOURCE
  is '文件';

prompt
prompt Creating table LOGS
prompt ===================
prompt
create table LOGS
(
  id           NUMBER(20) not null,
  log          VARCHAR2(500),
  user_id      VARCHAR2(50),
  user_name    VARCHAR2(50),
  create_time  DATE,
  object_value VARCHAR2(50),
  old          VARCHAR2(50),
  news         VARCHAR2(50),
  name         VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table LOGS
  is '日志';
comment on column LOGS.id
  is '编号';
comment on column LOGS.log
  is '日志内容';
comment on column LOGS.user_id
  is '操作者编号';
comment on column LOGS.user_name
  is '操作者';
comment on column LOGS.create_time
  is '操作时间';
comment on column LOGS.object_value
  is '操作对象主键';
comment on column LOGS.old
  is '原有值';
comment on column LOGS.news
  is '最新值';
comment on column LOGS.name
  is '操作对象';
alter table LOGS
  add constraint PK_LOGS primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ORDERS
prompt =====================
prompt
create table ORDERS
(
  orders_id       VARCHAR2(50) not null,
  orders_name     VARCHAR2(50),
  orders_content  VARCHAR2(500),
  company_id      VARCHAR2(50) not null,
  orders_time     DATE not null,
  director        VARCHAR2(50) not null,
  plan_time       DATE not null,
  real_time       DATE,
  create_time     DATE not null,
  create_user_id  VARCHAR2(50) not null,
  stauts          CHAR(1) not null,
  notify_time     DATE,
  notify_user     VARCHAR2(50),
  assign_time     DATE,
  assign_user     VARCHAR2(50),
  assign_stauts   VARCHAR2(1) default 0,
  notify_stauts   VARCHAR2(1) default 0,
  complete_time   DATE,
  complete_stauts VARCHAR2(1) default 0,
  complete_user   VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table ORDERS
  is '订单';
comment on column ORDERS.orders_id
  is '订单编号';
comment on column ORDERS.orders_name
  is '订单名称';
comment on column ORDERS.orders_content
  is '订单内容';
comment on column ORDERS.company_id
  is '所属公司编号';
comment on column ORDERS.orders_time
  is '订单时间';
comment on column ORDERS.director
  is '负责人编号';
comment on column ORDERS.plan_time
  is '计划完成时间';
comment on column ORDERS.real_time
  is '实际完成时间';
comment on column ORDERS.create_time
  is '创建时间';
comment on column ORDERS.create_user_id
  is '创建人编号';
comment on column ORDERS.stauts
  is '状态（0作废1已提单2待通知3已派单4已完工）';
comment on column ORDERS.notify_time
  is '通知时间';
comment on column ORDERS.notify_user
  is '通知人员';
comment on column ORDERS.assign_time
  is '派单时间';
comment on column ORDERS.assign_user
  is '派单人员';
comment on column ORDERS.assign_stauts
  is '通知标识';
comment on column ORDERS.notify_stauts
  is '派单标识';
comment on column ORDERS.complete_time
  is '完工时间';
comment on column ORDERS.complete_stauts
  is '完工标识';
comment on column ORDERS.complete_user
  is '完工人员';
alter table ORDERS
  add constraint PK_ORDERS primary key (ORDERS_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ORDERS_DETAIL
prompt ============================
prompt
create table ORDERS_DETAIL
(
  orders_detail_id  VARCHAR2(50) not null,
  orders_id         VARCHAR2(50) not null,
  parts             VARCHAR2(50) not null,
  standard          VARCHAR2(50) not null,
  single_number     NUMBER,
  craft_no          VARCHAR2(50),
  volume_density    NUMBER(10,2),
  stock             NUMBER,
  volume            NUMBER(10,3),
  single_weight     NUMBER(10,3),
  total_weight      NUMBER(10,3),
  production_number NUMBER,
  remark            VARCHAR2(50),
  create_time       DATE,
  notify_time       DATE,
  notify_user       VARCHAR2(50),
  assign_time       DATE,
  assign_user       VARCHAR2(50),
  notify_remark     VARCHAR2(50),
  assign_remark     VARCHAR2(50),
  assign_stauts     VARCHAR2(1) default 0,
  notify_stauts     VARCHAR2(1) default 0,
  real_amount       NUMBER(10,3),
  model             VARCHAR2(50),
  all_number        NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table ORDERS_DETAIL
  is '订单明细';
comment on column ORDERS_DETAIL.orders_detail_id
  is '订单明细编号';
comment on column ORDERS_DETAIL.orders_id
  is '订单编号';
comment on column ORDERS_DETAIL.parts
  is '砌筑部位';
comment on column ORDERS_DETAIL.standard
  is '产品规格';
comment on column ORDERS_DETAIL.single_number
  is '单套数';
comment on column ORDERS_DETAIL.craft_no
  is '工艺号';
comment on column ORDERS_DETAIL.volume_density
  is '体密';
comment on column ORDERS_DETAIL.stock
  is '库存';
comment on column ORDERS_DETAIL.volume
  is '体积';
comment on column ORDERS_DETAIL.single_weight
  is '单重';
comment on column ORDERS_DETAIL.total_weight
  is '总重';
comment on column ORDERS_DETAIL.production_number
  is '生产数';
comment on column ORDERS_DETAIL.remark
  is '备注';
comment on column ORDERS_DETAIL.create_time
  is '订单时间';
comment on column ORDERS_DETAIL.notify_time
  is '通知时间';
comment on column ORDERS_DETAIL.notify_user
  is '通知人员';
comment on column ORDERS_DETAIL.assign_time
  is '派单时间';
comment on column ORDERS_DETAIL.assign_user
  is '派单人员';
comment on column ORDERS_DETAIL.notify_remark
  is '通知意见';
comment on column ORDERS_DETAIL.assign_remark
  is '派单意见';
comment on column ORDERS_DETAIL.assign_stauts
  is '通知标识';
comment on column ORDERS_DETAIL.notify_stauts
  is '派单标识';
comment on column ORDERS_DETAIL.real_amount
  is '实际用量';
comment on column ORDERS_DETAIL.model
  is '型号';
comment on column ORDERS_DETAIL.all_number
  is '套数';
alter table ORDERS_DETAIL
  add constraint PK_ORDERS_DETAIL primary key (ORDERS_DETAIL_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PRODUCE
prompt ======================
prompt
create table PRODUCE
(
  produce_id       VARCHAR2(50) not null,
  orders_detail_id VARCHAR2(50) not null,
  produce_time     DATE not null,
  person_in_charge VARCHAR2(50) not null,
  product_id       VARCHAR2(50) not null,
  device_id        VARCHAR2(50) not null,
  tasks_amount     NUMBER not null,
  theory_weight    NUMBER,
  real_weight      NUMBER,
  real_amount      NUMBER,
  accept_amount    NUMBER,
  produce_dis      VARCHAR2(500),
  acceptor         VARCHAR2(50),
  inspetor         VARCHAR2(50),
  director         VARCHAR2(50),
  apm              VARCHAR2(50),
  stant_no         VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table PRODUCE
  is '产品派工';
comment on column PRODUCE.produce_id
  is '派工单编号';
comment on column PRODUCE.orders_detail_id
  is '订单明细编号';
comment on column PRODUCE.produce_time
  is '生产时间';
comment on column PRODUCE.person_in_charge
  is '机长姓名';
comment on column PRODUCE.product_id
  is '产品名称';
comment on column PRODUCE.device_id
  is '设备编号';
comment on column PRODUCE.tasks_amount
  is '任务数量';
comment on column PRODUCE.theory_weight
  is '理论单重';
comment on column PRODUCE.real_weight
  is '实际单重';
comment on column PRODUCE.real_amount
  is '实产数量';
comment on column PRODUCE.accept_amount
  is '验收数量';
comment on column PRODUCE.produce_dis
  is '生产情况';
comment on column PRODUCE.acceptor
  is '质量验收';
comment on column PRODUCE.inspetor
  is '质量监督';
comment on column PRODUCE.director
  is '工段主任';
comment on column PRODUCE.apm
  is '班段';
comment on column PRODUCE.stant_no
  is '工艺编号';
alter table PRODUCE
  add constraint PK_PRODUCE primary key (PRODUCE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PRODUCE_USERS
prompt ============================
prompt
create table PRODUCE_USERS
(
  produce_id  VARCHAR2(50),
  user_id     VARCHAR2(50),
  create_time DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table PRODUCE_USERS
  is '生产人员记录';
comment on column PRODUCE_USERS.produce_id
  is '派单编号';
comment on column PRODUCE_USERS.user_id
  is '人员编号';
comment on column PRODUCE_USERS.create_time
  is '时间';
create index INDEX_PRODUCE_ID on PRODUCE_USERS (PRODUCE_ID)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PRODUCT
prompt ======================
prompt
create table PRODUCT
(
  product_id       VARCHAR2(50) not null,
  product_type     VARCHAR2(50) not null,
  product_name     VARCHAR2(50) not null,
  product_icon     VARCHAR2(50),
  product_dis      VARCHAR2(500),
  create_time      DATE not null,
  create_user_id   VARCHAR2(50) not null,
  stauts           CHAR(1) not null,
  product_standard VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table PRODUCT
  is '产品';
comment on column PRODUCT.product_id
  is '产品编号';
comment on column PRODUCT.product_type
  is '产品类别';
comment on column PRODUCT.product_name
  is '产品名称';
comment on column PRODUCT.product_icon
  is '产品图片';
comment on column PRODUCT.product_dis
  is '产品描述';
comment on column PRODUCT.create_time
  is '创建时间';
comment on column PRODUCT.create_user_id
  is '创建人编号';
comment on column PRODUCT.stauts
  is '状态（0作废正常1）';
comment on column PRODUCT.product_standard
  is '产品规格';
alter table PRODUCT
  add constraint PK_PRODUCT primary key (PRODUCT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PRODUCT_CATEGORY
prompt ===============================
prompt
create table PRODUCT_CATEGORY
(
  category_id    VARCHAR2(50) not null,
  category_name  VARCHAR2(50) not null,
  category_icon  VARCHAR2(50),
  category_dis   VARCHAR2(500),
  create_time    DATE not null,
  create_user_id VARCHAR2(50) not null,
  stauts         CHAR(1) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table PRODUCT_CATEGORY
  is '产品类别';
comment on column PRODUCT_CATEGORY.category_id
  is '类别编号';
comment on column PRODUCT_CATEGORY.category_name
  is '类别名称';
comment on column PRODUCT_CATEGORY.category_icon
  is '类别图片';
comment on column PRODUCT_CATEGORY.category_dis
  is '类别描述';
comment on column PRODUCT_CATEGORY.create_time
  is '创建时间';
comment on column PRODUCT_CATEGORY.create_user_id
  is '创建人编号';
comment on column PRODUCT_CATEGORY.stauts
  is '状态（0作废正常1）';
alter table PRODUCT_CATEGORY
  add constraint PK_PRODUCT_CATEGORY primary key (CATEGORY_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PROVINCE
prompt =======================
prompt
create table PROVINCE
(
  code        VARCHAR2(12),
  name        VARCHAR2(50),
  type        VARCHAR2(50),
  parent_code VARCHAR2(12)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table PROVINCE
  is '行政区划';

prompt
prompt Creating table RESOURCES
prompt ========================
prompt
create table RESOURCES
(
  resources_id        VARCHAR2(50) not null,
  parent_resources_id VARCHAR2(50) not null,
  resources_name      VARCHAR2(50) not null,
  resources_url       VARCHAR2(200),
  resources_path      VARCHAR2(200),
  resources_icon      VARCHAR2(200),
  resources_type      CHAR(1) default '2' not null,
  create_time         DATE default sysdate not null,
  create_user_id      VARCHAR2(50) default '1' not null,
  remark              VARCHAR2(50) default 'fa fa-leaf',
  stauts              CHAR(1) default 1
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table RESOURCES
  is '资源';
comment on column RESOURCES.resources_id
  is '资源编号';
comment on column RESOURCES.parent_resources_id
  is '上级编号';
comment on column RESOURCES.resources_name
  is '资源名称';
comment on column RESOURCES.resources_url
  is '资源地址';
comment on column RESOURCES.resources_path
  is '资源路径';
comment on column RESOURCES.resources_icon
  is '图标';
comment on column RESOURCES.resources_type
  is '资源类别';
comment on column RESOURCES.create_time
  is '创建时间';
comment on column RESOURCES.create_user_id
  is '创建人编号';
comment on column RESOURCES.remark
  is '备注';
comment on column RESOURCES.stauts
  is '状态';
alter table RESOURCES
  add constraint PK_RESOURCES primary key (RESOURCES_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ROLE
prompt ===================
prompt
create table ROLE
(
  role_id        VARCHAR2(50) not null,
  role_name      VARCHAR2(50) not null,
  create_time    DATE not null,
  create_user_id VARCHAR2(50) not null,
  remark         VARCHAR2(200) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table ROLE
  is '用户角色';
comment on column ROLE.role_id
  is '角色编号';
comment on column ROLE.role_name
  is '角色名称';
comment on column ROLE.create_time
  is '创建时间';
comment on column ROLE.create_user_id
  is '创建人编号';
comment on column ROLE.remark
  is '备注';
alter table ROLE
  add constraint PK_ROLE primary key (ROLE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ROLE_RESOURCES
prompt =============================
prompt
create table ROLE_RESOURCES
(
  role_id      VARCHAR2(50) not null,
  resources_id VARCHAR2(50) not null,
  create_time  DATE default sysdate not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table ROLE_RESOURCES
  is '角色权限';
comment on column ROLE_RESOURCES.role_id
  is '角色编号';
comment on column ROLE_RESOURCES.resources_id
  is '资源编号';
comment on column ROLE_RESOURCES.create_time
  is '创建时间';

prompt
prompt Creating table STATIC_DATA
prompt ==========================
prompt
create table STATIC_DATA
(
  name   VARCHAR2(50),
  code   VARCHAR2(50),
  type   CHAR(2),
  remark VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table STATIC_DATA
  is '静态字典';
comment on column STATIC_DATA.name
  is 'name';
comment on column STATIC_DATA.code
  is 'code';
comment on column STATIC_DATA.type
  is 'remark';

prompt
prompt Creating table USERS
prompt ====================
prompt
create table USERS
(
  user_id         VARCHAR2(50) not null,
  account         VARCHAR2(30) not null,
  password        VARCHAR2(100) not null,
  sex             CHAR(1) not null,
  id_card         VARCHAR2(18),
  name            VARCHAR2(50) not null,
  department_id   VARCHAR2(50) not null,
  area_code       VARCHAR2(6) not null,
  birthday        DATE,
  stauts          CHAR(1) not null,
  last_login_time DATE,
  mobile          VARCHAR2(50),
  email           VARCHAR2(50),
  seat            VARCHAR2(50),
  user_level      VARCHAR2(2) not null,
  create_time     DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table USERS
  is '系统用户';
comment on column USERS.user_id
  is '用户编号';
comment on column USERS.account
  is '账户';
comment on column USERS.password
  is '密码';
comment on column USERS.sex
  is '性别';
comment on column USERS.id_card
  is '身份证号';
comment on column USERS.name
  is '用户名';
comment on column USERS.department_id
  is '部门编号';
comment on column USERS.area_code
  is '所属地区编号';
comment on column USERS.birthday
  is '出生日期';
comment on column USERS.stauts
  is '状态（0停用正常1）';
comment on column USERS.last_login_time
  is '最后登录时间';
comment on column USERS.mobile
  is '手机号';
comment on column USERS.email
  is '电子邮箱';
comment on column USERS.seat
  is '座位';
comment on column USERS.user_level
  is '用户级别';
comment on column USERS.create_time
  is '创建时间';
alter table USERS
  add constraint PK_USERS primary key (USER_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table USERS_ROLE
prompt =========================
prompt
create table USERS_ROLE
(
  user_id     VARCHAR2(50) not null,
  role_id     VARCHAR2(50) not null,
  create_time DATE default SYSDATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table USERS_ROLE
  is '用户角色';
create index PK_USERS_ROLE on USERS_ROLE (USER_ID, ROLE_ID)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating sequence SEQ_LOGS
prompt ==========================
prompt
create sequence SEQ_LOGS
minvalue 1
maxvalue 999999999999999999999999999
start with 361
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ORDERS
prompt ============================
prompt
create sequence SEQ_ORDERS
minvalue 1
maxvalue 999999999999999999999999999
start with 41
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ORDERS_DETAIL
prompt ===================================
prompt
create sequence SEQ_ORDERS_DETAIL
minvalue 1
maxvalue 999999999999999999999999999
start with 61
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRODUCE
prompt =============================
prompt
create sequence SEQ_PRODUCE
minvalue 1
maxvalue 999999999999999999999999999
start with 41
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRODUCT_CATEGORY
prompt ======================================
prompt
create sequence SEQ_PRODUCT_CATEGORY
minvalue 1
maxvalue 999999999999999999999999999
start with 21
increment by 1
cache 20;


spool off
