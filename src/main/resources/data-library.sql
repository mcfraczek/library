-- create table address(id int primary key auto_increment, street varchar(40), street_number varchar(6), apartment_number varchar(6),county varchar(30), postal_code char(6), city varchar(30));
-- create table user_details(id int primary key auto_increment, tel_number varchar(20), email varchar(40), address_id int, foreign key (address_id) references address(id));
-- create table user(id int primary key auto_increment, name varchar(45), surname varchar(45), PESEL char(11), user_details_id int, foreign key(user_details_id) references user_details(id));
-- create table book(id int primary key auto_increment, title varchar(60), isbn char(17), library_number varchar(20) unique key,time_stamp date,user_id int, foreign key(user_id) references user(id));
-- create table type(id int primary key auto_increment, type varchar(60));
-- create table book_type(book_id int, type_id int, primary key(book_id, type_id), foreign key(book_id) references book(id), foreign key(type_id) references type(id));
-- create table author(id int primary key auto_increment, name varchar(45), surname varchar(45));
-- create table book_author(book_id int, author_id int, primary key(book_id, author_id), foreign key(book_id)references book(id), foreign key(author_id) references author(id));

-- users
insert into address (id, street, street_number, apartment_number, county, postal_code, city) values ('1', 'Marszałkowska', '1', '2', 'Mazowieckie', '03-178', 'Warszawa');
insert into address (id, street, street_number, apartment_number, county, postal_code, city) values ('2', 'Puławska', '2', '3', 'Mazowieckie', '01-178', 'Warszawa');

insert into user_details (id, tel_number, email, address_id) VALUES ('1', '634543234', 'jasio@stasio.pl', '1');
insert into user_details (id, tel_number, email, address_id) VALUES ('2', '509876354', 'basio@kasio.pl', '2');

insert into user (id, name, surname, PESEL, user_details_id) VALUES ('1', 'Jasio', 'Stasio', '76567865432', '1');
insert into user (id, name, surname, PESEL, user_details_id) VALUES ('2', 'Basio', 'Kasio', '96567865432', '2');

-- authors
insert into author(id, name, surname) VALUES ('1', 'Bolesław', 'Prus');
insert into author(id, name, surname) VALUES ('2', 'Eliza', 'Orzeszkowa');

-- books
insert into book(id, title, isbn, library_number, time_stamp, user_id) VALUES ('1', 'Lalka', '1234567890120', '1234567890120', '2019-01-19','1');
insert into book(id, title, isbn, library_number, time_stamp,  user_id) VALUES ('2', 'Faraon', '1234567890121', '1234567890121','2020-02-12', '1');
insert into book(id, title, isbn, library_number, time_stamp, user_id) VALUES ('3', 'Nad Niemnem', '1234567890122', '1234567890122','2011-12-29', '2');
insert into book(id, title, isbn, library_number) VALUES ('4', 'Dobra Pani', '1234567890123', '1234567890123');


-- book-author
insert into book_author(book_id, author_id) VALUES ('1', '1');
insert into book_author(book_id, author_id) VALUES ('2', '1');
insert into book_author(book_id, author_id) VALUES ('3', '2');
insert into book_author(book_id, author_id) VALUES ('4', '2');

-- types
insert into type(id, type) VALUES (1, 'Science fiction');
insert into type(id, type) VALUES (2, 'Romance');
insert into type(id, type) VALUES (3, 'Drama');
insert into type(id, type) VALUES (4, 'Mystery');
insert into type(id, type) VALUES (5, 'Action and Adventure');

-- book -types
insert into book_type(book_id, type_id) VALUES ('1', '4');
insert into book_type(book_id, type_id) VALUES ('1', '2');

insert into book_type(book_id, type_id) VALUES ('2', '5');
insert into book_type(book_id, type_id) VALUES ('2', '2');

insert into book_type(book_id, type_id) VALUES ('3', '3');
insert into book_type(book_id, type_id) VALUES ('3', '4');

insert into book_type(book_id, type_id) VALUES ('4', '2');


