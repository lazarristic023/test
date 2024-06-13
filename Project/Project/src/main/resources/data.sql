INSERT INTO users(
	email_checked, role, id, email, password, username)
	VALUES (false, 2, 1, 'niiljevic@gmail.com', '$2a$12$j8EVmecAxJdZl.Oqa0eSme4oOOBw6jyol6IMd1F7WOKILcy.YNfG2', 'admin');

INSERT INTO users(
	email_checked, role, id, email, password, username)
	VALUES (false, 1, 2, 'nikolinaskiljevic@gmail.com', '$2a$12$j8EVmecAxJdZl.Oqa0eSme4oOOBw6jyol6IMd1F7WOKILcy.YNfG2', 'employee1');

INSERT INTO users(
	email_checked, role, id, email, password, username)
	VALUES (false, 1, 3, 'nikolievic@gmail.com', '$2a$12$j8EVmecAxJdZl.Oqa0eSme4oOOBw6jyol6IMd1F7WOKILcy.YNfG2', 'employee2');



INSERT INTO requests(
	end_date, start_date, status, id, username)
	VALUES ('02.02.2024', '03.03.2026', 0, 1, 'employee1');

INSERT INTO requests(
	end_date, start_date, status, id, username)
	VALUES ('02.02.2024', '03.03.2026', 0, 2, 'employee2');

INSERT INTO commercial_requests(
	is_accepted, client_id, create_deadline_date, end_date, id, start_date, description)
	VALUES (false, 2, '05.05.2024', '02.02.2024', 1, '02.03.2024', 'Some description');
INSERT INTO commercial_requests(
	is_accepted, client_id, create_deadline_date, end_date, id, start_date, description)
	VALUES (false, 3, '05.05.2024', '02.02.2024', 2, '02.03.2024', 'Some description');