# custom-spring-security-el-resolver
Design and developed the custom Spring security expression resolver at method level.
hasRole
hasAuthority
hasAccess

like the above we can make our own spring security expressions at method level.
for eg.,
isAdminAndView('#id')

Hibernate: drop table customer if exists
Hibernate: drop table role if exists
Hibernate: drop table role_res if exists
Hibernate: drop table role_operation_feature if exists
Hibernate: drop table role_responsibility if exists
Hibernate: drop table role_responsibility_operations if exists
Hibernate: drop table user if exists
Hibernate: drop table user_role if exists
Hibernate: create table customer (id bigint generated by default as identity, firstname varchar(255), lastname varchar(255), primary key (id))
Hibernate: create table role (id bigint generated by default as identity, role_name varchar(255), primary key (id))
Hibernate: create table role_res (role_id bigint not null, res_id bigint not null, primary key (role_id, res_id))
Hibernate: create table role_operation_feature (id bigint generated by default as identity, name varchar(255), primary key (id))
Hibernate: create table role_responsibility (id bigint generated by default as identity, name varchar(255), primary key (id))
Hibernate: create table role_responsibility_operations (role_responsibility_id bigint not null, operations_id bigint not null)
Hibernate: create table user (id bigint generated by default as identity, password varchar(255), status boolean not null, user_name varchar(255), primary key (id))
Hibernate: create table user_role (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id))
Hibernate: alter table role_responsibility_operations add constraint UK_rrqgle42r8wyhxbupifcu64gc unique (operations_id)
Hibernate: alter table user add constraint UK_lqjrcobrh9jc8wpcar64q1bfh unique (user_name)
Hibernate: alter table role_res add constraint FKb4apikw525cpg8cpkexvtofb foreign key (res_id) references role_responsibility
Hibernate: alter table role_res add constraint FK8h8afh6cjcfrfdj4bqmnu3sex foreign key (role_id) references role
Hibernate: alter table role_responsibility_operations add constraint FKbewyvihsoeo02wnwhjo79lqjs foreign key (operations_id) references role_operation_feature
Hibernate: alter table role_responsibility_operations add constraint FKmjnfeo6x212vvsc686nhtb4d foreign key (role_responsibility_id) references role_responsibility
Hibernate: alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role
Hibernate: alter table user_role add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user
