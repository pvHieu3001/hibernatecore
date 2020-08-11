create sequence hibernate_sequence start with 1 increment by 1;
create table Role (id number(19,0) not null, NAME varchar2(255 char), primary key (id));
create table User (id number(19,0) not null, username varchar2(255 char), password varchar2(255 char), email varchar2(255 char), status number(10,0), primary key (id));
create table user_role (role_id number(19,0) not null, user_id number(19,0) not null, primary key (user_id, role_id));
alter table user_role add constraint FKcnrjdc801vcdj8n017nlriv61 foreign key (user_id) references User;
alter table user_role add constraint FK7ir6hi5jr98lclgjgbyxafneu foreign key (role_id) references Role;
