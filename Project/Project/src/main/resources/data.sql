INSERT INTO users(
	email_checked, role, id, email, password, username)
	VALUES (false, 2, 1, 'nikolinaskiljevic@gmail.com', '$2a$12$6JZF6T2A2X0Ai9nolc5aC.ChlP2XOv8HmBg1l.8njr196/6RxiJji', 'admin');

INSERT INTO users(
	email_checked, role, id, email, password, username)
	VALUES (false, 0, 2, 'nikolinaskiljevic@gmail.com', '$2a$12$6JZF6T2A2X0Ai9nolc5aC.ChlP2XOv8HmBg1l.8njr196/6RxiJji', 'employee1');

INSERT INTO users(
	email_checked, role, id, email, password, username)
	VALUES (false, 0, 3, 'nikolinaskiljevic@gmail.com', '$2a$12$6JZF6T2A2X0Ai9nolc5aC.ChlP2XOv8HmBg1l.8njr196/6RxiJji', 'employee2');

INSERT INTO clients(
	package_type, type, id, city, client_firm_name, client_firm_residential_address, client_surname_firmpib, country, phone)
	VALUES (0, 0, 2, '', '', '', '', '', '');

INSERT INTO clients(
	package_type, type, id, city, client_firm_name, client_firm_residential_address, client_surname_firmpib, country, phone)
	VALUES (0, 0, 3, '', '', '', '', '', '');

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