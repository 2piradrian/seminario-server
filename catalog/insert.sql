-- Inserts para instrumentos musicales
INSERT INTO instruments (id, name) VALUES (gen_random_uuid(), 'Guitarra');
INSERT INTO instruments (id, name) VALUES (gen_random_uuid(), 'Piano');
INSERT INTO instruments (id, name) VALUES (gen_random_uuid(), 'Batería');
INSERT INTO instruments (id, name) VALUES (gen_random_uuid(), 'Violín');
INSERT INTO instruments (id, name) VALUES (gen_random_uuid(), 'Saxofón');
INSERT INTO instruments (id, name) VALUES (gen_random_uuid(), 'Trompeta');
INSERT INTO instruments (id, name) VALUES (gen_random_uuid(), 'Bajo');
INSERT INTO instruments (id, name) VALUES (gen_random_uuid(), 'Flauta');
INSERT INTO instruments (id, name) VALUES (gen_random_uuid(), 'Ukelele');
INSERT INTO instruments (id, name) VALUES (gen_random_uuid(), 'Acordeón');

-- Inserts para estilos musicales
INSERT INTO styles (id, name) VALUES (gen_random_uuid(), 'Jazz');
INSERT INTO styles (id, name) VALUES (gen_random_uuid(), 'Rock');
INSERT INTO styles (id, name) VALUES (gen_random_uuid(), 'Pop');
INSERT INTO styles (id, name) VALUES (gen_random_uuid(), 'Indie');
INSERT INTO styles (id, name) VALUES (gen_random_uuid(), 'Clásica');
INSERT INTO styles (id, name) VALUES (gen_random_uuid(), 'Reggae');
INSERT INTO styles (id, name) VALUES (gen_random_uuid(), 'Blues');
INSERT INTO styles (id, name) VALUES (gen_random_uuid(), 'Metal');
INSERT INTO styles (id, name) VALUES (gen_random_uuid(), 'Electrónica');
INSERT INTO styles (id, name) VALUES (gen_random_uuid(), 'Folklore');

-- Inserts para Tipos de página
INSERT INTO pageTypes (id, name) VALUES (gen-gen_random_uuid(), 'Banda')
INSERT INTO pageTypes (id, name) VALUES (gen-gen_random_uuid(), 'General')
INSERT INTO pageTypes (id, name) VALUES (gen-gen_random_uuid(), 'Marca')

INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Ofrezco servicio');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Busco músicos');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Busco banda');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Reemplazo urgente');

-- Categorías: Tipo de show / contenido
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Show en vivo');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Sesionista de estudio');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Tributo');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Covers');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Originales');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'DJ Set');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Acústico / Unplugged');

-- Categorías: Servicios musicales
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Clases');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Composición por encargo');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Arreglos musicales');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Producción musical');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Mezcla');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Mastering');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Grabación móvil');

-- Categorías: Tipo de evento
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Casamiento / Evento social');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Fiesta de 15');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Evento corporativo');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Bar / Restobar');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Festival');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Teatro / Sala');

-- Categorías: Comunidad / logística
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Colaboraciones');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Anuncios generales');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Equipamiento / Backline disponible');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Sonidista / Iluminación');
INSERT INTO categories (id, name) VALUES (gen_random_uuid(), 'Disponibilidad / Agenda');
