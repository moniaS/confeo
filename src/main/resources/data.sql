insert into address values (100, 'Lodz', 'Kwiatowa', 5);
insert into category values (1, 'TESTOWANIE');
insert into category values (2, 'JAVASCRIPT');
insert into category values (3, 'JAVA');
insert into category values (4, 'RUBY');
insert into category values (5, 'IT');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, type, start_date, end_date)
values(11, 'Konf1', 'Desc1', true, 10, 10, 100, 1, 'CONFERENCE', '2018-08-12', '2018-08-13');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date)
values(21, 'Konf2', 'Desc2', true, 10, 10, 100, 2, '2018-08-12', '2018-08-13');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date)
values(13, 'Konf3', 'Desc3', false, 10, 10, 100, 1, '2018-08-12', '2018-08-13');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date)
values(14, 'Konf4', 'Desc4', false, 10, 10, 100, 2, '2018-08-12', '2018-08-13');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date)
values(15, 'Konf5', 'Desc5', false, 10, 10, 100, 2, '2018-08-12', '2018-08-13');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date)
values(16, 'Konf6', 'Desc6', false, 10, 10, 100, 2, '2018-07-12', '2018-08-13');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date)
values(17, 'Konf7', 'Desc7', false, 10, 10, 100, 1, '2018-06-12', '2018-08-13');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date)
values(18, 'Konf8', 'Desc8', false, 10, 10, 100, 1, '2018-10-12', '2018-08-13');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date)
values(19, 'Konf9', 'Desc9', false, 10, 10, 100, 2, '2018-11-12', '2018-08-13');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date)
values(110, 'Konf10', 'Desc10', false, 10, 10, 100, 1, '2018-12-12', '2018-08-13');

insert into user (id, email, password, firstname, lastname, role)
	values (2, 'pawel@gmail.com', '$2a$10$FvyEHmj7zJKw4V4iBdJqL.mugH9VTPOQkc6HzlhNsqLkO.0KFla2O', 'Paweł', 'Kowalczyk', 'ROLE_PARTICIPANT');
insert into user (id, email, password, firstname, lastname, role)
	values (3, 'katarzyna@gmail.com', '$2a$10$FvyEHmj7zJKw4V4iBdJqL.mugH9VTPOQkc6HzlhNsqLkO.0KFla2O', 'Katarzyna', 'Kwiatowa', 'ROLE_PARTICIPANT');
insert into user (id, email, password, firstname, lastname, role)
	values (4, 'damian@gmail.com', '$2a$10$FvyEHmj7zJKw4V4iBdJqL.mugH9VTPOQkc6HzlhNsqLkO.0KFla2O', 'Damian', 'Kowalski', 'ROLE_PARTICIPANT');
insert into user (id, email, password, firstname, lastname, role)
	values (5, 'zuzanna@gmail.com', '$2a$10$FvyEHmj7zJKw4V4iBdJqL.mugH9VTPOQkc6HzlhNsqLkO.0KFla2O', 'Zuzanna', 'Nowak', 'ROLE_ORGANIZER');

insert into prelection(id, name, description, status, user_id, event_id)
	values (1, 'Prelekcja 1', 'Bardzo fajna prelekcja na ciekawy temat', 1, 2, 11);
insert into prelection(id, name, description, status, user_id, event_id)
	values (2, 'Prelekcja 2', 'Bardzo fajna prelekcja na ciekawszy temat', 1, 2, 21);
insert into prelection(id, name, description, status, user_id, event_id)
	values (3, 'Prelekcja 3', 'Bardzo fajna prelekcja na ciekawy temat', 1, 3, 11);