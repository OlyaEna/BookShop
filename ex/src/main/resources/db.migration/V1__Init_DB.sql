create table authors
(
    id           bigint not null auto_increment,
    fio          varchar(255),
    is_activated bit    not null,
    is_deleted   bit    not null,
    primary key (id)
);

create table categories
(
    category_id  bigint not null auto_increment,
    is_activated bit    not null,
    is_deleted   bit    not null,
    name         varchar(255),
    primary key (category_id)
);

create table genres
(
    id           bigint not null auto_increment,
    is_activated bit    not null,
    is_deleted   bit    not null,
    name         varchar(255),
    primary key (id)
);

create table hibernate_sequence
(
    next_val bigint
);

 insert into hibernate_sequence values ( 1 );

create table order_items
(
    id         bigint not null auto_increment,
    order_id   bigint,
    product_id bigint,
    primary key (id)
);

create table orders
(
    id            bigint           not null auto_increment,
    address       varchar(255),
    city          varchar(255),
    country       varchar(255),
    created       datetime(6),
    delivery_date datetime(6),
    notes         varchar(255),
    order_date    datetime(6),
    order_status  varchar(255),
    total_price   double precision not null,
    user_id       bigint,
    primary key (id)
);

create table products
(
    id              bigint           not null,
    isbn            varchar(255),
    bestseller      bit              not null,
    date_of_created datetime(6),
    description     text,
    image           MEDIUMBLOB,
    is_activated    bit              not null,
    is_deleted      bit              not null,
    novelty         bit              not null,
    price           double precision not null,
    title           varchar(255),
    author_id       bigint,
    category_id     bigint,
    genre_id        bigint,
    publisher_id    bigint,
    series_id       bigint,
    primary key (id)
);

create table publishers
(
    publisher_id bigint not null auto_increment,
    is_activated bit    not null,
    is_deleted   bit    not null,
    name         varchar(255),
    primary key (publisher_id)
);

create table roles
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
);

create table series
(
    id           bigint not null auto_increment,
    is_activated bit    not null,
    is_deleted   bit    not null,
    name         varchar(255),
    primary key (id)
);

create table users
(
    id           bigint not null auto_increment,
    active       bit    not null,
    email        varchar(255),
    first_name   varchar(15),
    last_name    varchar(15),
    password     varchar(255),
    phone_number varchar(255),
    primary key (id)
);

create table users_roles
(
    user_id bigint not null,
    role_id bigint not null
);

alter table categories
    add constraint UKt8o6pivur7nn124jehx7cygw5 unique (name);
alter table users
    add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table users
    add constraint UK_9q63snka3mdh91as4io72espi unique (phone_number);
alter table order_items
    add constraint FKbioxgbv59vetrxe0ejfubep1w foreign key (order_id) references orders (id);
alter table order_items
    add constraint FKocimc7dtr037rh4ls4l95nlfi foreign key (product_id) references products (id);
alter table orders
    add constraint FK32ql8ubntj5uh44ph9659tiih foreign key (user_id) references users (id);
alter table products
    add constraint FKy2kver9ldog29n3mi9b12w64 foreign key (author_id) references authors (id);
alter table products
    add constraint FKog2rp4qthbtt2lfyhfo32lsw9 foreign key (category_id) references categories (category_id);
alter table products
    add constraint FK1w6wsbg6w189oop2bl38v0hjk foreign key (genre_id) references genres (id);
alter table products
    add constraint FK6dd6en5l5avuu43towdby894e foreign key (publisher_id) references publishers (publisher_id);
alter table products
    add constraint FKtr5elaf3k45l7r7erk5ldsthv foreign key (series_id) references series (id);
alter table users_roles
    add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles (id);
alter table users_roles
    add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users (id);