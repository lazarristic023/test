INSERT INTO users(
	email_checked, role, id, email, password, username)
	VALUES (false, 2, 1, 'nikolinaskiljevic@gmail.com', '$2a$12$6JZF6T2A2X0Ai9nolc5aC.ChlP2XOv8HmBg1l.8njr196/6RxiJji', 'admin');

INSERT INTO users(
	email_checked, role, id, email, password, username)
	VALUES (false, 0, 2, 'dajanaskocajic18@gmail.com', '$2a$12$CHFGL.CBQNXBm9y01fRGnOyTvEAq19SaS0cvqmdfYMwW6HsFY5Mtu', 'daks');

INSERT INTO users(
	email_checked, role, id, email, password, username)
	VALUES (false, 0, 3, 'nikolinaskiljevic@gmail.com', '$2a$12$6JZF6T2A2X0Ai9nolc5aC.ChlP2XOv8HmBg1l.8njr196/6RxiJji', 'employee2');


INSERT INTO companies(
	id, address, name)
	VALUES (1, 'Adresa kompanije', 'Nfb');


INSERT INTO public.clients(
	package_type, tfa_enabled, type, company_id, id, client_firm_name, client_firm_residential_address, client_surname_firmpib, secret)
	VALUES (2, false, 0, 1, 2, 'Naziv firme', 'adresaa', 'PIB', 'Tajna');


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