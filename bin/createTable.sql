create database spider;
use spider;

create table urls(
	url varchar(1000) primary key,
	extract varchar(10),
	analy varchar(10),
	state varchar(100),
	createTime timestamp default CURRENT_TIMESTAMP()
);