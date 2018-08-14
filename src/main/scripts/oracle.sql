create table WORKER_NODE
(
ID NUMBER(10) NOT NULL,
HOST_NAME VARCHAR(64) NOT NULL,
PORT VARCHAR(64) NOT NULL,
TYPE NUMBER NOT NULL,
LAUNCH_DATE DATE NOT NULL,
MODIFIED DATE NOT NULL,
CREATED DATE NOT NULL
);


comment on table "WORKER_NODE" is
'WORKER NODES';

comment on column "WORKER_NODE"."ID" is
'主键id';

comment on column "WORKER_NODE"."HOST_NAME" is
'主机名';

comment on column "WORKER_NODE"."PORT" is
'端口';

comment on column "WORKER_NODE"."TYPE" is
'类型';

comment on column "WORKER_NODE"."LAUNCH_DATE" is
'启动类型';

comment on column "WORKER_NODE"."PORT" is
'端口';




--创建序列
create sequence SEQ_WORKER_NODES
minvalue 1
nomaxvalue
start with 1
increment by 1
nocycle   --一直累加，不循环
--nocache;  --不缓存
cache 10; --缓存10条

--创建触发器，如果insert语句不指定ID自动插入增长值
CREATE OR REPLACE TRIGGER tr_worker_nodes
BEFORE INSERT ON WORKER_NODE FOR EACH ROW WHEN (new.ID is null)
begin
select SEQ_WORKER_NODES.nextval into:new.ID from dual;
end;