CREATE TABLE messages
(
    id      bigint not null auto_increment,
    created datetime(6),
    message text,
    primary key (id)
);

ALTER TABLE messages add column email  varchar(255);
ALTER TABLE messages add column first_name  varchar(15);
ALTER TABLE messages add column last_name  varchar(15);
ALTER TABLE messages add column phone_number  varchar(255);
ALTER TABLE messages add column topic text;
ALTER TABLE messages add column user_id bigint;

alter table messages
    add constraint FK_key foreign key (user_id) references users (id);