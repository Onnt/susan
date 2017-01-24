create database spider;
use spider;

drop table urls ;

create table urls(
	url varchar(1000) primary key,
	extract varchar(10),
	analy varchar(10),
	state varchar(100),
	createTime timestamp default CURRENT_TIMESTAMP()
);

select count(*) from urls;
select count(*) from urls where extract is not null;
select count(*) from urls where analy is not null;


create table godsong(
	song varchar(1000) primary key
)

select * from godsong