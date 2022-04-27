drop schema if exists movie_index;

create schema movie_index;

use movie_index;

create table favourite_movies(
    movie_id int not null auto_increment,
    movie_name varchar(256) not null,
    personal_rating int,
    release_date date,
    synopsis varchar(1000),
    is_deleted TINYINT(1),
    primary key (movie_id)
);

create table actors(
    actor_id int not null auto increment,
    actor_name varchar(256),
    is_deleted TINYINT (1),
    primary key (actor_id)
);

create table movie_cast(
    movie_id int,
    actor_id int,

    foreign key (movie_id),
    references favourite_movies(movie_id),

    foreign key (actor_id),
    references actors(actor_id)
)
