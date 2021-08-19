insert into users (user_id, login, password, name, nickname, email, role, is_account_non_expired, is_account_non_locked,
                   is_credentials_non_expired, is_enabled) values (1, 'user1',
                                                                   '$2a$10$T1QEejHy6..ivfYCFAqHCevkbNpiAiRyc1zz2LaksR/ZqkuZKi35a',
                                                                   'Mike',
                                                                   'Mike111',
                                                                   'user1@gmail.com',
                                                                   'ROLE_USER', true, true, true, true);
insert into users (user_id, login, password, name, nickname, email, role, is_account_non_expired, is_account_non_locked,
                   is_credentials_non_expired, is_enabled) values (2, 'user2',
                                                                   '$2a$10$T1QEejHy6..ivfYCFAqHCevkbNpiAiRyc1zz2LaksR/ZqkuZKi35a',
                                                                   'John',
                                                                   'John111',
                                                                   'user2@gmail.com',
                                                                   'ROLE_USER', true, true, true, true);
insert into users (user_id, login, password, name, nickname, email, role, is_account_non_expired, is_account_non_locked,
                   is_credentials_non_expired, is_enabled) values (3, 'user3',
                                                                   '$2a$10$T1QEejHy6..ivfYCFAqHCevkbNpiAiRyc1zz2LaksR/ZqkuZKi35a',
                                                                   'Sean',
                                                                   'Sean111',
                                                                   'user3@gmail.com',
                                                                   'ROLE_USER', true, true, true, true);

insert into chats (id, chat_id, sender_id, recipient_id) values (1, 'user1_user2', 1, 2);
insert into chats (id, chat_id, sender_id, recipient_id) values (2, 'user1_user2', 2, 1);
insert into chat_messages (message_id, sender_id, recipient_id, body, chat_id, date, status) values (1,
                                                                                                     1,
                                                                                                     2,
                                                                                                     'hello!',
                                                                                                     'user1_user2',
                                                                                                     1628196070,
                                                                                                     'DELIVERED');