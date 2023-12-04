INSERT INTO users (name, gmail, password, is_admin, created_by) VALUES ('Admin', 'admin@gmail.com', '$2b$12$FXhW5bDnMwZKAAlnU7ab4e3cnTsieSTjFIGLHcL6mSuTM1xRKldmu', true, null);

INSERT INTO event_types (title, is_paid) VALUES ('Remote', true);
INSERT INTO event_types (title, is_paid) VALUES ('Work', true);
INSERT INTO event_types (title, is_paid) VALUES ('Break', false);