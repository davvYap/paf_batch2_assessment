create database paf2;

use paf2;

create table user(
	user_id varchar(8) primary key not null,
    username varchar(50),
    name varchar(50)
);

create table task(
	task_id int primary key auto_increment,
    description varchar(255),
    priority int,
    due_date date,
    user_id varchar(8) not null,
    constraint fk_user_id
	foreign key  (user_id)
	references user(user_id)
);
