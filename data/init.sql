-- CREATE database filmDataBase;

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
    id      serial primary key,
    user_id integer references users,
    film_id integer references films,
    rate    integer,
    constraint unique_films_and_users
        unique (film_id, user_id)
);

insert into users (name, last_name, email, password, birth_date, role)
VALUES ('Ekaterina', 'Radomskaya', 'ekaterina@mail.com', 'password', '1995-08-24', 'ADMIN');

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