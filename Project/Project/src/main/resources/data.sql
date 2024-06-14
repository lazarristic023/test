INSERT INTO users(
	blocked, email_checked, role, id, email, password, username)
	VALUES (false, false, 2, 1, 'nikolinaskiljevic@gmail.com', '$2a$12$6JZF6T2A2X0Ai9nolc5aC.ChlP2XOv8HmBg1l.8njr196/6RxiJji', 'admin');

INSERT INTO public.admins(
    is_first_logging, is_predefined, id, first_name, last_name)
VALUES (false, true, 1, 'asf', 'afaaf');

-- INSERT INTO users(
--     blocked, email_checked, role, id, email, password, username)
-- 	VALUES (false, false, 0, 2, 'nikolinaskiljevic@gmail.com', '$2a$12$6JZF6T2A2X0Ai9nolc5aC.ChlP2XOv8HmBg1l.8njr196/6RxiJji', 'employee1');
--
-- INSERT INTO users(
--     blocked, email_checked, role, id, email, password, username)
-- 	VALUES (false, false, 0, 3, 'nikolinaskiljevic@gmail.com', '$2a$12$6JZF6T2A2X0Ai9nolc5aC.ChlP2XOv8HmBg1l.8njr196/6RxiJji', 'employee2');

-- Lazar admin insert -------------------------
INSERT INTO users(
    blocked, email_checked, role, id, email, password, username)
VALUES (false, true, 2, 2, 'zrpgd023@gmail.com', '$2a$12$6JZF6T2A2X0Ai9nolc5aC.ChlP2XOv8HmBg1l.8njr196/6RxiJji', 'zrpgd023@gmail.com');

INSERT INTO public.admins(
    is_first_logging, is_predefined, id, first_name, last_name)
VALUES (false, true, 2, 'lazar', 'ristic');
-- Lazar admin insert   ^^^^^^^^^^^^^^^^^^^^^^^^^

-- INSERT INTO requests(
-- 	end_date, start_date, status, id, username)
-- 	VALUES ('02.02.2024', '03.03.2026', 0, 1, 'employee1');
--
-- INSERT INTO requests(
-- 	end_date, start_date, status, id, username)
-- 	VALUES ('02.02.2024', '03.03.2026', 0, 2, 'employee2');
--
-- INSERT INTO commercial_requests(
-- 	is_accepted, client_id, create_deadline_date, end_date, id, start_date, description)
-- 	VALUES (false, 2, '05.05.2024', '02.02.2024', 1, '02.03.2024', 'Some description');
-- INSERT INTO commercial_requests(
-- 	is_accepted, client_id, create_deadline_date, end_date, id, start_date, description)
-- 	VALUES (false, 3, '05.05.2024', '02.02.2024', 2, '02.03.2024', 'Some description');