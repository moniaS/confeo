insert into address values (101, 'Łódź', 'Kwiatowa', 5);
insert into address values (102, 'Kraków', 'Kwiatowa', 103);
insert into address values (103, 'Warszawa', 'Łąkowa', 7);
insert into address values (104, 'Kraków', 'Kwiatowa', 44);
insert into address values (105, 'Gdańsk', 'Kwiatowa', 22);
insert into address values (106, 'Poznań', 'Moniuszki', 33);
insert into category values (1, 'TESTOWANIE');
insert into category values (2, 'JAVASCRIPT');
insert into category values (3, 'JAVA');
insert into category values (4, 'RUBY');
insert into category values (5, 'IT');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, type, start_date, end_date, max_participants, status)
values(20, 'Code Łódź', 'Na konferencji dowiecie się wielu ciekawych rzeczy z 4 ścieżek tematycznych.', true, 10, 10, 101, 1, 'CONFERENCE', '2018-08-12', '2018-08-13', 100, 'UPCOMING');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, type, start_date, end_date, status, max_participants)
values(21, 'Konferencja "Zostań testerem"', 'Opis wydarzenia', true, 10, 10, 102, 1, 'CONFERENCE', '2018-08-14', '2018-08-17', 'UPCOMING', 40);
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(22, 'Warsztaty z programowania dla młodzieży', 'Opis wydarzenia', true, 10, 10, 101, 2, '2018-08-18', '2018-08-19', 90, 'UPCOMING', 'WORKSHOPS');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(13, 'Python - programowanie od podstaw', 'Opis wydarzenia...', false, 10, 10, 102, 1, '2018-09-12', '2018-09-15', 20, 'UPCOMING', 'WORKSHOPS');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(14, 'Moja pierwsza aplikacja w JavaScript', 'Opis wydarzenia...', false, 10, 10, 101, 2, '2018-09-17', '2018-09-29', 50, 'UPCOMING', 'WORKSHOPS');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(15, 'Go and code', 'Czy warto zostać koderem?', false, 10, 10, 102, 2, '2018-09-12', '2018-09-12', 30, 'UPCOMING', 'LECTURE');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(16, 'Testowanie od A do Z', 'Desc6', false, 10, 10, 103, 2, '2018-07-18', '2018-07-19', 150, 'UPCOMING', 'LECTURE');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(17, 'Programowanie w JavaScript', 'Naucz się z nami programować w JavaScript', false, 10, 10, 104, 1, '2018-06-02', '2018-06-05', 200, 'FINISHED', 'TRAINING');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(18, 'Zostań Scrum Masterem', 'Desc8', false, 10, 10, 105, 1, '2018-05-04', '2018-05-08', 100, 'FINISHED', 'TRAINING');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(19, 'UX - projektowanie użytecznych interfejsów', 'Desc9', false, 10, 10, 106, 2, '2018-06-09', '2018-06-10', 100, 'FINISHED', 'OTHER');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, start_date, end_date, max_participants, status, type)
values(110, 'Programowanie w Pythonie', 'Szkolenie dotyczące programowania w Pythonie dla początkujących', false, 10, 10, 102, 1, '2018-12-12', '2018-12-15', 100, 'UPCOMING', 'OTHER');

insert into user (id, email, password, firstname, lastname, role)
	values (100, 'pawel@gmail.com', '$2a$10$FvyEHmj7zJKw4V4iBdJqL.mugH9VTPOQkc6HzlhNsqLkO.0KFla2O', 'Paweł', 'Kowalczyk', 'ROLE_PARTICIPANT');
insert into user (id, email, password, firstname, lastname, role)
	values (101, 'katarzyna@gmail.com', '$2a$10$FvyEHmj7zJKw4V4iBdJqL.mugH9VTPOQkc6HzlhNsqLkO.0KFla2O', 'Katarzyna', 'Kwiatowa', 'ROLE_PARTICIPANT');
insert into user (id, email, password, firstname, lastname, role)
	values (102, 'damian@gmail.com', '$2a$10$FvyEHmj7zJKw4V4iBdJqL.mugH9VTPOQkc6HzlhNsqLkO.0KFla2O', 'Damian', 'Kowalski', 'ROLE_PARTICIPANT');
insert into user (id, email, password, firstname, lastname, role)
	values (103, 'kamil@gmail.com', '$2a$10$FvyEHmj7zJKw4V4iBdJqL.mugH9VTPOQkc6HzlhNsqLkO.0KFla2O', 'Kamil', 'Igrekowski', 'ROLE_PARTICIPANT');
insert into user (id, email, password, firstname, lastname, role)
	values (104, 'malgorzata@gmail.com', '$2a$10$FvyEHmj7zJKw4V4iBdJqL.mugH9VTPOQkc6HzlhNsqLkO.0KFla2O', 'Małgorzata', 'Nowak', 'ROLE_PARTICIPANT');
insert into user (id, email, password, firstname, lastname, role)
	values (105, 'zuzanna@gmail.com', '$2a$10$FvyEHmj7zJKw4V4iBdJqL.mugH9VTPOQkc6HzlhNsqLkO.0KFla2O', 'Zuzanna', 'Nowak', 'ROLE_ORGANIZER');
insert into user (id, email, password, firstname, lastname, role)
	values (106, 'milosz@gmail.com', '$2a$10$FvyEHmj7zJKw4V4iBdJqL.mugH9VTPOQkc6HzlhNsqLkO.0KFla2O', 'Miłosz', 'Zawada', 'ROLE_ORGANIZER');

insert into prelection(id, name, description, status, user_id, event_id)
	values (101, 'Prelekcja 1', 'Bardzo fajna prelekcja na ciekawy temat', 1, 100, 20);
insert into prelection(id, name, description, status, user_id, event_id)
	values (102, 'Prelekcja 2', 'Bardzo fajna prelekcja na ciekawszy temat', 1, 102, 21);
insert into prelection(id, name, description, status, user_id, event_id)
	values (103, 'Prelekcja 3', 'Bardzo fajna prelekcja na ciekawy temat', 1, 100, 16);
insert into prelection(id, name, description, status, user_id, event_id)
	values (104, 'Prelekcja 4', 'Bardzo fajna prelekcja na ciekawszy temat', 1, 103, 21);
insert into prelection(id, name, description, status, user_id, event_id)
	values (105, 'Prelekcja 5', 'Bardzo fajna prelekcja na ciekawy temat', 1, 101, 14);
insert into prelection(id, name, description, status, user_id, event_id)
	values (106, 'Prelekcja 5', 'Bardzo fajna prelekcja na ciekawy temat', 1, 104, 15);
