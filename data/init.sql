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
    title       varchar(255) unique                            not null,
    description text                                           not null,
    image       varchar(255)     default ''::character varying not null,
    rate        double precision default 0
);

create table comments
(
    id                    serial primary key,
    user_id               integer references users,
    film_id               integer references films ON DELETE CASCADE,
    comment               text,
    date_time_of_creation timestamp default now(),
    author_name           varchar(255)
);

create table rates
(
    id      serial primary key,
    user_id integer references users,
    film_id integer references films ON DELETE CASCADE,
    rate    integer,
    constraint unique_films_and_users
        unique (film_id, user_id)
);

TRUNCATE rates CASCADE;

insert into users (name, last_name, email, password, birth_date, role)
VALUES ('Ekaterina', 'Radomskaya', 'ekaterina@mail.com', 'password', '1995-08-24', 'ADMIN');

insert into users (name, last_name, email, password, birth_date, role)
VALUES ('Iurii', 'Radomskaii', 'iurii@mail.com', 'password', '1994-06-30', 'USER');

insert into users (name, last_name, email, password, birth_date, role)
VALUES ('Natalia', 'Zeller', 'natalia@mail.com', 'password', '1973-09-20', 'USER');

insert into users (name, last_name, email, password, birth_date, role)
VALUES ('Max', 'Schwarz', 'max@mail.com', 'password', '1970-01-21', 'USER');

insert into users (name, last_name, email, password, birth_date, role)
VALUES ('Alex', 'Smith', 'alex@mail.com', 'password', '1990-05-21', 'USER');

insert into users (name, last_name, email, password, birth_date, role)
VALUES ('Karl', 'Brent', 'karl@mail.com', 'password', '1996-08-28', 'USER');

insert into users (name, last_name, email, password, birth_date, role)
VALUES ('Rupert', 'Green', 'rupert@mail.com', 'password', '1988-08-24', 'USER');

insert into users (name, last_name, email, password, birth_date, role)
VALUES ('Emma', 'Watson', 'emma@mail.com', 'password', '1990-04-15', 'USER');

insert into users (name, last_name, email, password, birth_date, role)
VALUES ('Thomas', 'Felton', 'thomas@mail.com', 'password', '1987-09-22', 'USER');

insert into users (name, last_name, email, password, birth_date, role)
VALUES ('Daniel', 'Radcliffe', 'daniel@mail.com', 'password', '1989-07-23', 'USER');

insert into users (name, last_name, email, password, birth_date, role)
VALUES ('Matthew', 'Lewis', 'mattew@mail.com', 'password', '1989-06-27', 'USER');

insert into films (title, description, image)
VALUES ('avatar',
        'A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.',
        'images\films\Avatar.jpg');

insert into films (title, description, image)
VALUES ('beauty and the Beast',
        'The story and characters you love come to life in the live-action adaptation of Disney''s animated classic, a cinematic event celebrating one of the most beloved tales ever told.',
        'images\films\Beauty_and_the_Beast.jpg');

insert into films (title, description, image)
VALUES ('harry potter and the prisoner of azkaban',
        'Harry Potter, Ron and Hermione return to Hogwarts School of Witchcraft and Wizardry for their third year of study, where they delve into the mystery surrounding an escaped prisoner who poses a dangerous threat to the young wizard.',
        'images\films\harry_potter_and_the_prisoner_of_azkaban.jpg');


insert into films (title, description, image)
VALUES ('harry potter and the sorcerer''s stone',
        'An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.',
        'images\films\harry_potter_and_the_sorcerer''s_stone.jpg');

insert into films (title, description, image)
VALUES ('harry potter and the chamber of secrets',
        'An ancient prophecy seems to be coming true when a mysterious presence begins stalking the corridors of a school of magic and leaving its victims paralyzed.',
        'images\films\harry_potter_and_the_chamber_of_secrets.jpg');

insert into films (title, description, image)
VALUES ('harry potter and the goblet of fire',
        'Harry Potter finds himself competing in a hazardous tournament between rival schools of magic, but he is distracted by recurring nightmares.',
        'images\films\harry_potter_and_the_goblet_of_fire.jpg');

insert into films (title, description, image)
VALUES ('harry potter and the order of the phoenix',
        'With their warning about Lord Voldemort''s return scoffed at, Harry and Dumbledore are targeted by the Wizard authorities as an authoritarian bureaucrat slowly seizes power at Hogwarts.',
        'images\films\harry_potter_and_the_order_of_the_phoenix.jpg');

insert into films (title, description, image)
VALUES ('harry potter and the half-blood prince',
        'As Harry Potter begins his sixth year at Hogwarts, he discovers an old book marked as "the property of the Half-Blood Prince" and begins to learn more about Lord Voldemort''s dark past.',
        'images\films\harry_potter_and_the_half-blood_prince.jpg');

insert into films (title, description, image)
VALUES ('harry potter and the deathly hallows: part 1',
        'As Harry, Ron and Hermione race against time and evil to destroy the Horcruxes, they uncover the existence of the three most powerful objects in the wizarding world: the Deathly Hallows.',
        'images\films\harry_potter_and_the_deathly_hallows_part_1.jpg');

insert into films (title, description, image)
VALUES ('harry potter and the deathly hallows: part 2',
        'Harry, Ron, and Hermione search for Voldemort''s remaining Horcruxes in their effort to destroy the Dark Lord as the final battle rages on at Hogwarts.',
        'images\films\harry_potter_and_the_deathly_hallows-_part_2.jpg');


insert into films (title, description, image)
VALUES ('little Woman', 'Four sisters come of age in America during the aftermath of the Civil War.',
        'images\films\Little_Woman.jpg');


insert into rates (user_id, film_id, rate) values ('2','1','4');
insert into rates (user_id, film_id, rate) values ('3','1','5');
insert into rates (user_id, film_id, rate) values ('4','1','4');
insert into rates (user_id, film_id, rate) values ('5','1','3');
insert into rates (user_id, film_id, rate) values ('6','1','4');
insert into rates (user_id, film_id, rate) values ('7','1','1');
insert into rates (user_id, film_id, rate) values ('8','1','4');
insert into rates (user_id, film_id, rate) values ('9','1','5');
insert into rates (user_id, film_id, rate) values ('10','1','4');
insert into rates (user_id, film_id, rate) values ('11','1','1');

insert into rates (user_id, film_id, rate) values ('11','2','4');
insert into rates (user_id, film_id, rate) values ('2','2','1');
insert into rates (user_id, film_id, rate) values ('3','2','2');
insert into rates (user_id, film_id, rate) values ('4','2','5');
insert into rates (user_id, film_id, rate) values ('5','2','4');
insert into rates (user_id, film_id, rate) values ('6','2','1');
insert into rates (user_id, film_id, rate) values ('7','2','4');
insert into rates (user_id, film_id, rate) values ('8','2','5');
insert into rates (user_id, film_id, rate) values ('9','2','4');
insert into rates (user_id, film_id, rate) values ('10','2','3');


insert into rates (user_id, film_id, rate) values ('11','3','4');
insert into rates (user_id, film_id, rate) values ('2','3','1');
insert into rates (user_id, film_id, rate) values ('3','3','2');
insert into rates (user_id, film_id, rate) values ('4','3','5');
insert into rates (user_id, film_id, rate) values ('5','3','4');
insert into rates (user_id, film_id, rate) values ('6','3','1');
insert into rates (user_id, film_id, rate) values ('7','3','4');
insert into rates (user_id, film_id, rate) values ('8','3','5');
insert into rates (user_id, film_id, rate) values ('9','3','4');
insert into rates (user_id, film_id, rate) values ('10','3','3');

insert into rates (user_id, film_id, rate) values ('2','4','1');
insert into rates (user_id, film_id, rate) values ('3','4','2');
insert into rates (user_id, film_id, rate) values ('4','4','5');
insert into rates (user_id, film_id, rate) values ('5','4','4');
insert into rates (user_id, film_id, rate) values ('6','4','1');
insert into rates (user_id, film_id, rate) values ('7','4','4');
insert into rates (user_id, film_id, rate) values ('8','4','5');
insert into rates (user_id, film_id, rate) values ('9','4','4');
insert into rates (user_id, film_id, rate) values ('10','4','3');
insert into rates (user_id, film_id, rate) values ('11','4','4');



insert into rates (user_id, film_id, rate) values ('2','5','1');
insert into rates (user_id, film_id, rate) values ('3','5','2');
insert into rates (user_id, film_id, rate) values ('4','5','5');
insert into rates (user_id, film_id, rate) values ('5','5','4');
insert into rates (user_id, film_id, rate) values ('6','5','1');
insert into rates (user_id, film_id, rate) values ('7','5','4');
insert into rates (user_id, film_id, rate) values ('8','5','5');
insert into rates (user_id, film_id, rate) values ('9','5','4');
insert into rates (user_id, film_id, rate) values ('10','5','3');
insert into rates (user_id, film_id, rate) values ('11','5','4');


insert into rates (user_id, film_id, rate) values ('2','6','1');
insert into rates (user_id, film_id, rate) values ('3','6','2');
insert into rates (user_id, film_id, rate) values ('4','6','5');
insert into rates (user_id, film_id, rate) values ('5','6','4');
insert into rates (user_id, film_id, rate) values ('6','6','1');
insert into rates (user_id, film_id, rate) values ('7','6','4');
insert into rates (user_id, film_id, rate) values ('8','6','5');
insert into rates (user_id, film_id, rate) values ('9','6','4');
insert into rates (user_id, film_id, rate) values ('10','6','3');
insert into rates (user_id, film_id, rate) values ('11','6','4');



insert into rates (user_id, film_id, rate) values ('2','7','1');
insert into rates (user_id, film_id, rate) values ('3','7','2');
insert into rates (user_id, film_id, rate) values ('4','7','5');
insert into rates (user_id, film_id, rate) values ('5','7','4');
insert into rates (user_id, film_id, rate) values ('6','7','1');
insert into rates (user_id, film_id, rate) values ('7','7','4');
insert into rates (user_id, film_id, rate) values ('8','7','5');
insert into rates (user_id, film_id, rate) values ('9','7','4');
insert into rates (user_id, film_id, rate) values ('10','7','3');
insert into rates (user_id, film_id, rate) values ('11','7','4');


insert into rates (user_id, film_id, rate) values ('2','8','1');
insert into rates (user_id, film_id, rate) values ('3','8','2');
insert into rates (user_id, film_id, rate) values ('4','8','5');
insert into rates (user_id, film_id, rate) values ('5','8','4');
insert into rates (user_id, film_id, rate) values ('6','8','1');
insert into rates (user_id, film_id, rate) values ('7','8','4');
insert into rates (user_id, film_id, rate) values ('8','8','5');
insert into rates (user_id, film_id, rate) values ('9','8','4');
insert into rates (user_id, film_id, rate) values ('10','8','3');
insert into rates (user_id, film_id, rate) values ('11','8','4');


insert into rates (user_id, film_id, rate) values ('2','9','1');
insert into rates (user_id, film_id, rate) values ('3','9','2');
insert into rates (user_id, film_id, rate) values ('4','9','5');
insert into rates (user_id, film_id, rate) values ('5','9','4');
insert into rates (user_id, film_id, rate) values ('6','9','1');
insert into rates (user_id, film_id, rate) values ('7','9','4');
insert into rates (user_id, film_id, rate) values ('8','9','5');
insert into rates (user_id, film_id, rate) values ('9','9','4');
insert into rates (user_id, film_id, rate) values ('10','9','3');
insert into rates (user_id, film_id, rate) values ('11','9','4');


insert into rates (user_id, film_id, rate) values ('2','10','1');
insert into rates (user_id, film_id, rate) values ('3','10','2');
insert into rates (user_id, film_id, rate) values ('4','10','5');
insert into rates (user_id, film_id, rate) values ('5','10','4');
insert into rates (user_id, film_id, rate) values ('6','10','1');
insert into rates (user_id, film_id, rate) values ('7','10','4');
insert into rates (user_id, film_id, rate) values ('8','10','5');
insert into rates (user_id, film_id, rate) values ('9','10','4');
insert into rates (user_id, film_id, rate) values ('10','10','3');
insert into rates (user_id, film_id, rate) values ('11','10','4');


insert into rates (user_id, film_id, rate) values ('2','11','1');
insert into rates (user_id, film_id, rate) values ('3','11','2');
insert into rates (user_id, film_id, rate) values ('4','11','5');
insert into rates (user_id, film_id, rate) values ('5','11','4');
insert into rates (user_id, film_id, rate) values ('6','11','1');
insert into rates (user_id, film_id, rate) values ('7','11','4');
insert into rates (user_id, film_id, rate) values ('8','11','5');
insert into rates (user_id, film_id, rate) values ('9','11','4');
insert into rates (user_id, film_id, rate) values ('10','11','3');
insert into rates (user_id, film_id, rate) values ('11','11','4');
