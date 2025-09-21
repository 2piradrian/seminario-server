CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Inserts para instrumentos musicales
INSERT INTO instrument (id, name) VALUES (gen_random_uuid(), 'Guitarra');
INSERT INTO instrument (id, name) VALUES (gen_random_uuid(), 'Piano');
INSERT INTO instrument (id, name) VALUES (gen_random_uuid(), 'Batería');
INSERT INTO instrument (id, name) VALUES (gen_random_uuid(), 'Violín');
INSERT INTO instrument (id, name) VALUES (gen_random_uuid(), 'Saxofón');
INSERT INTO instrument (id, name) VALUES (gen_random_uuid(), 'Trompeta');
INSERT INTO instrument (id, name) VALUES (gen_random_uuid(), 'Bajo');
INSERT INTO instrument (id, name) VALUES (gen_random_uuid(), 'Flauta');
INSERT INTO instrument (id, name) VALUES (gen_random_uuid(), 'Ukelele');
INSERT INTO instrument (id, name) VALUES (gen_random_uuid(), 'Acordeón');

-- Inserts para estilos musicales
INSERT INTO style (id, name) VALUES (gen_random_uuid(), 'Jazz');
INSERT INTO style (id, name) VALUES (gen_random_uuid(), 'Rock');
INSERT INTO style (id, name) VALUES (gen_random_uuid(), 'Pop');
INSERT INTO style (id, name) VALUES (gen_random_uuid(), 'Indie');
INSERT INTO style (id, name) VALUES (gen_random_uuid(), 'Clásica');
INSERT INTO style (id, name) VALUES (gen_random_uuid(), 'Reggae');
INSERT INTO style (id, name) VALUES (gen_random_uuid(), 'Blues');
INSERT INTO style (id, name) VALUES (gen_random_uuid(), 'Metal');
INSERT INTO style (id, name) VALUES (gen_random_uuid(), 'Electrónica');
INSERT INTO style (id, name) VALUES (gen_random_uuid(), 'Folklore');
