CREATE database FilmRating;

create type user_role as enum ('ADMIN', 'USER');

create table users
(
    id         serial primary key,
    name       varchar(255) not null,
    last_name  varchar(255) not null,
    email      varchar(255) not null
        unique,
    password   varchar(255) not null,
    birth_date date         not null,
    role       user_role default 'USER'::user_role,
    status     integer   default 0,
    isbanned   boolean   default false
);

create table films
(
    id          serial primary key,
    title       varchar(255)                                   not null,
    description text                                           not null,
    image       varchar(255)     default ''::character varying not null,
    rate        double precision default 3
);

create table comments
(
    id                    serial primary key,
    user_id               integer references users,
    film_id               integer references films,
    comment               text,
    date_time_of_creation timestamp default now(),
    author_name           varchar(255)
);

create table rates
(
    id      serial        primary key,
    user_id integer        references users,
    film_id integer         references films,
    rate    integer,
    constraint unique_films_and_users
        unique (film_id, user_id)
);