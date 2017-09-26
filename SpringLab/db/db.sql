create database spring_lab DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use spring_lab;

create table m_user (
 id varchar(32) primary key,
 account_no varchar(32) not null,
 user_name varchar(128) not null,
 password varchar(128) not null
);

