INSERT INTO users (name, gmail, password, is_admin, created_by) VALUES ('Admin', 'admin@gmail.com', '$2b$12$FXhW5bDnMwZKAAlnU7ab4e3cnTsieSTjFIGLHcL6mSuTM1xRKldmu', true, null);

INSERT INTO event_types (title, is_paid) VALUES ('Remote', true);
INSERT INTO event_types (title, is_paid) VALUES ('Work', true);
INSERT INTO event_types (title, is_paid) VALUES ('Break', false);

INSERT INTO events (name, start_date, end_date, notes, previous_event_id, user_id, event_type_id) VALUES ('Meeting', '2023-12-06 09:00:00', '2023-12-06 11:00:00', 'Discuss project status', NULL, 1, 1);