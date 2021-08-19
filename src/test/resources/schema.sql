set foreign_key_checks = 0;
drop table if exists users;
drop table if exists chats;
drop table if exists chat_messages;
set foreign_key_checks = 1;

create table users(user_id bigint not null auto_increment primary key,
                   login varchar(255) not null unique,
                   password varchar(255) not null,
                   name varchar(255) not null,
                   nickname varchar(255) not null unique,
                   email varchar(255) not null unique,
                   role varchar(255) not null,
                   is_account_non_expired boolean not null,
                   is_account_non_locked boolean not null,
                   is_credentials_non_expired boolean not null,
                   is_enabled boolean not null);

create table chats(id bigint not null auto_increment primary key,
                   chat_id varchar(255) not null,
                   sender_id bigint not null, foreign key (sender_id) references users(user_id),
                   recipient_id bigint not null, foreign key (recipient_id) references users(user_id));

create table chat_messages(message_id bigint not null auto_increment primary key,
                           sender_id bigint not null, foreign key (sender_id) references users(user_id),
                           recipient_id bigint not null, foreign key (recipient_id) references users(user_id),
                           body varchar(255) not null,
                           chat_id varchar(255) not null,
                           date int not null,
                           status varchar(255) not null);


