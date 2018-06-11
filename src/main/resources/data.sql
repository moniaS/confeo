insert into address values (101, 'Lodz', 'Kwiatowa', 5);
insert into address values (102, 'Kraków', 'Kwiatowa', 5);
insert into category values (1, 'TESTOWANIE');
insert into category values (2, 'JAVASCRIPT');
insert into category values (3, 'JAVA');
insert into category values (4, 'RUBY');
insert into category values (5, 'IT');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, type, start_date, end_date, max_participants, status)
values(20, 'Konf1', 'Desc1', true, 10, 10, 101, 1, 'CONFERENCE', '2018-08-12', '2018-08-13', 100, 'UPCOMING');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, type, start_date, end_date, status, max_participants)
values(21, 'Konf1', 'Desc1', true, 10, 10, 102, 1, 'CONFERENCE', '2018-08-12', '2018-08-13', 'UPCOMING', 40);
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(22, 'Konf2', 'Desc2', true, 10, 10, 101, 2, '2018-08-12', '2018-08-13', 90, 'UPCOMING', 'WORKSHOPS');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(13, 'Konf3', 'Desc3', false, 10, 10, 102, 1, '2018-08-12', '2018-08-13', 20, 'UPCOMING', 'WORKSHOPS');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(14, 'Konf4', 'Desc4', false, 10, 10, 101, 2, '2018-08-12', '2018-08-13', 50, 'UPCOMING', 'LECTURE');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(15, 'Konf5', 'Desc5', false, 10, 10, 102, 2, '2018-08-12', '2018-08-13', 30, 'UPCOMING', 'LECTURE');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(16, 'Konf6', 'Desc6', false, 10, 10, 102, 2, '2018-07-12', '2018-08-13', 150, 'UPCOMING', 'LECTURE');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(17, 'Konf7', 'Desc7', false, 10, 10, 101, 1, '2018-06-12', '2018-08-13', 200, 'UPCOMING', 'TRAINING');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(18, 'Konf8', 'Desc8', false, 10, 10, 101, 1, '2018-10-12', '2018-08-13', 100, 'UPCOMING', 'TRAINING');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(19, 'Konf9', 'Desc9', false, 10, 10, 101, 2, '2018-11-12', '2018-08-13', 100, 'UPCOMING', 'OTHER');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(110, 'Konf10', 'Desc10', false, 10, 10, 102, 1, '2018-12-12', '2018-08-13', 100, 'UPCOMING', 'OTHER');

insert into user (id, email, password, firstname, lastname, role)
	values (100, 'pawel@gmail.com', '$2a$10$FvyEHmj7zJKw4V4iBdJqL.mugH9VTPOQkc6HzlhNsqLkO.0KFla2O', 'Paweł', 'Kowalczyk', 'ROLE_PARTICIPANT');
insert into user (id, email, password, firstname, lastname, role)
	values (101, 'katarzyna@gmail.com', '$2a$10$FvyEHmj7zJKw4V4iBdJqL.mugH9VTPOQkc6HzlhNsqLkO.0KFla2O', 'Katarzyna', 'Kwiatowa', 'ROLE_PARTICIPANT');
insert into user (id, email, password, firstname, lastname, role)
	values (102, 'damian@gmail.com', '$2a$10$FvyEHmj7zJKw4V4iBdJqL.mugH9VTPOQkc6HzlhNsqLkO.0KFla2O', 'Damian', 'Kowalski', 'ROLE_PARTICIPANT');
insert into user (id, email, password, firstname, lastname, role)
	values (103, 'zuzanna@gmail.com', '$2a$10$FvyEHmj7zJKw4V4iBdJqL.mugH9VTPOQkc6HzlhNsqLkO.0KFla2O', 'Zuzanna', 'Nowak', 'ROLE_ORGANIZER');

insert into prelection(id, name, description, status, user_id, event_id)
	values (101, 'Prelekcja 1', 'Bardzo fajna prelekcja na ciekawy temat', 1, 100, 20);
insert into prelection(id, name, description, status, user_id, event_id)
	values (102, 'Prelekcja 2', 'Bardzo fajna prelekcja na ciekawszy temat', 1, 102, 21);
insert into prelection(id, name, description, status, user_id, event_id)
	values (103, 'Prelekcja 3', 'Bardzo fajna prelekcja na ciekawy temat', 1, 100, 20);
