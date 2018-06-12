insert into users (id, created_tmstp, enabled, firstName, lastName, password, updated_tmtp, userName) values (1, CURRENT_TIMESTAMP, '1', 'sai', 'swaroopa', '123', CURRENT_TIMESTAMP, 'saiswaroopa.n@hcl.com');


insert into roles (id, name) values (1, 'ADMIN');
insert into roles (id, name) values (2, 'VIEW');
insert into roles (id, name) values (3, 'CREATE');

insert into users_roles (id, user_id, role_id) values (1, 1,1);



