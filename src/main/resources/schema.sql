create table tbl_employees(
id bigint not null AUTO_INCREMENT, --without AUTO_INCREMENT we get error 'ID may not be null'
name varchar(255) not null,
email varchar(255) not null,
department varchar(255) not null,
primary key(id)
);