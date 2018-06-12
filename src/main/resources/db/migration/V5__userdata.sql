insert into users (id, email, password, firstName, lastName, location, state) values (1, 'saiswaroopa.n@hcl.com', '123', 'sai', 'swaroopa', 'IND','TS');
insert into users (id, email, password, firstName, lastName, location, state) values (2, 'sruthi.n@hcl.com', '456', 'sruthi', 'ramya', 'IND','TS');
insert into users (id, email, password, firstName, lastName, location, state) values (3, 'radha@hcl.com', '145', 'radha', 'm', 'IND','AP');
insert into users (id, email, password, firstName, lastName, location, state) values (4, 'anusha@hcl.com', '098', 'anusha', 't', 'IND','AP');


insert into users_roles (user_id, available) values (1, 1);
insert into users_roles (user_id, available) values (2, 1);
insert into users_roles (user_id, available) values (1, 2);
insert into users_roles (user_id, available) values (2, 3);

insert into roles (id, name, user_id, available) values (1, 'ADMIN', 1, TRUE);
insert into roles (id, name, user_id, available) values (2, 'VIEW', 1, TRUE);
insert into roles (id, name, user_id, available) values (3, 'CREATE', 1, TRUE);
insert into roles (id, name, user_id, available) values (4, 'VIEW', 2, TRUE);
insert into roles (id, name, user_id, available) values (4, 'CREATE', 2, TRUE);
insert into roles (id, name, user_id, available) values (4, 'ADMIN', 3, TRUE);
insert into roles (id, name, user_id, available) values (4, 'CREATE', 4, TRUE);
