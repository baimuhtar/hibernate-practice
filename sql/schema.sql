create table categories
(
    id serial8,
    name varchar not null,
    primary key (id)
);

create table options
(
    id serial8,
    category_id int8    not null,
    name        varchar not null,
    primary key (id),
    foreign key (category_id) references categories (id),
    unique (category_id, name)
);

create table products (
    id serial8,
    category_id int8 not null,
    name varchar not null,
    price int4 not null,
    primary key (id),
    foreign key (category_id) references categories(id),
    unique (name)
);
create table values (
    id serial8,
    product_id int8 not null,
    option_id int8 not null,
    value varchar not null,
    primary key (id),
    foreign key (product_id) references products (id),
    foreign key (option_id) references options (id),
    unique (product_id, option_id)
);

create table user (
    id int8,
    role varchar(25),
    login varchar (25),
    password varchar(50),
    first_name varchar(50),
    last_name varchar(50),
    registration_date date
);