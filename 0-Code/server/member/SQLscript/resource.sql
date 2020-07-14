create table resource
(
   id                   bigint not null auto_increment,
   category_id          bigint comment '资源分类ID',
   create_time          datetime comment '创建时间',
   name                 varchar(200) comment '资源名称',
   url                  varchar(200) comment '资源URL',
   description          varchar(500) comment '描述',
   primary key (id)
);