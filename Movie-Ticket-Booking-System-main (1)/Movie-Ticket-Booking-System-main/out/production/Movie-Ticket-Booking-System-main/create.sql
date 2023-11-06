drop database moviedb;
create database moviedb;
\c moviedb

create table users
(
    name varchar primary KEY,
    password varchar
);

create table manager
(
    name varchar primary key,
    password varchar
);

create table movie
(
    name varchar primary key,
    description varchar
);

create table moviedetails
(
    name varchar,
    theatre VARCHAR,
    seats int,
    showdate varchar,
    showtime varchar,
    primary key(name, theatre, showdate, showtime)
);

create table ticket
(
    name varchar,
    theatre varchar,
    showdate varchar,
    showtime varchar,
    seatNumbers int,
    amount int
);

create table ratedmovies(
    username varchar,
    moviename varchar,
    rating int,
    review varchar,
    description varchar,
    primary key(username, moviename)
);

insert into manager values('admin','admin');

insert into movie
values('RRR', 'RRR is a 2022 Indian Tamil-language  thriller film directed by S.S. Rajamouli. It stars Ram Charan, Junior NTR, Alia bhat.');
insert into movie
values('The Kashmir Files', 'Kashmir is a 2022 Indian Hindi-language  thriller film directed by Vivek Agnihotri. It stars Anupam Kher, Darshan Kumar and Pallavi Joshi\n Budget Free in most of the BJP ruled states.');
insert into movie
values('The Viceroys House','Viceroys House is a 2017 British-Indian historical drama film directed by Gurinder Chadha and written by Paul Mayeda Berges, Moira Buffini, and Chadha.');

insert into moviedetails
values('LOR','ABC',50,'15-06-1022','6:00');


insert into moviedetails values('RRR','A',10,'12-02-2022','6:00');
insert into moviedetails values('The Viceroys House','A',10,'12-02-2022','6:00');
insert into moviedetails values('RRR','A',10,'12-02-2022','8:00');