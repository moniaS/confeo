insert into address values (1, 'Lodz', 'Kwiatowa', 5);
insert into category values (1, 'TESTOWANIE');
insert into category values (2, 'JAVASCRIPT');
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id, type)
values(1, 'Konf1', 'Desc1', true, 10, 10, 1, 1, 1);
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id)
values(2, 'Konf2', 'Desc2', true, 10, 10, 1, 2);
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id)
values(3, 'Konf3', 'Desc3', false, 10, 10, 1, 1);
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id)
values(4, 'Konf4', 'Desc4', false, 10, 10, 1, 2);
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id)
values(5, 'Konf5', 'Desc5', false, 10, 10, 1, 2);
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id)
values(6, 'Konf6', 'Desc6', false, 10, 10, 1, 2);
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id)
values(7, 'Konf7', 'Desc7', false, 10, 10, 1, 1);
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id)
values(8, 'Konf8', 'Desc8', false, 10, 10, 1, 1);
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id)
values(9, 'Konf9', 'Desc9', false, 10, 10, 1, 2);
insert into event (id, name, description, is_free, PRICE_PER_PARTICIPANT, PRICE_PER_PRELEGENT, address_id, category_id)
values(10, 'Konf10', 'Desc10', false, 10, 10, 1, 1);

insert into user (id, email, password, firstname, lastname, role)
	values (2, 'pawel@gmail.com', 'tajnehaslo', 'Paweł', 'Kowalczyk', 'ROLE_PARTICIPANT');
insert into user (id, email, password, firstname, lastname, role)
	values (3, 'katarzyna@gmail.com', 'tajnehaslo', 'Katarzyna', 'Kwiatowa', 'ROLE_PARTICIPANT');
insert into user (id, email, password, firstname, lastname, role)
	values (4, 'damian@gmail.com', 'tajnehaslo', 'Damian', 'Kowalski', 'ROLE_PARTICIPANT');
insert into user (id, email, password, firstname, lastname, role)
	values (5, 'zuzanna@gmail.com', 'tajnehaslo', 'Zuzanna', 'Nowak', 'ROLE_ORGANIZER');

insert into prelection(id, name, description, status, user_id, event_id)
	values (1, 'Prelekcja 1', 'Bardzo fajna prelekcja na ciekawy temat', 1, 2, 1);
insert into prelection(id, name, description, status, user_id, event_id)
	values (2, 'Prelekcja 2', 'Bardzo fajna prelekcja na ciekawszy temat', 1, 2, 2);
insert into prelection(id, name, description, status, user_id, event_id)
	values (3, 'Prelekcja 3', 'Bardzo fajna prelekcja na ciekawy temat', 1, 3, 1);