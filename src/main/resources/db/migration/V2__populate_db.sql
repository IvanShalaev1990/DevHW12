INSERT INTO client (NAME) VALUES
('Rick Sanchez'),
('Morty Smith'),
('Summer Smith'),
('Beth Smith'),
('Jerry Smith'),
('Birdperson'),
('Squanchy'),
('Mr. Meeseeks'),
('Unity'),
('Evil Morty');

INSERT INTO planet (ID, NAME) VALUES
('MERCURY','Mercury'),
('VENUS','Venus'),
('EARTH','Earth'),
('MARS','Mars'),
('SATURN','Saturn'),
('URANUS','Uranus'),
('NEPTUNE','Neptune'),
('PLUTO','Pluto');

INSERT INTO ticket (CREATED_AT, CLIENT_ID, FROM_PLANET_ID, TO_PLANET_ID) VALUES
(NOW(), 1, 'MERCURY', 'VENUS'),
(NOW(), 2, 'VENUS', 'EARTH'),
(NOW(), 3, 'EARTH', 'MARS'),
(NOW(), 4, 'MARS', 'SATURN'),
(NOW(), 5, 'SATURN', 'URANUS'),
(NOW(), 6, 'URANUS', 'NEPTUNE'),
(NOW(), 7, 'NEPTUNE', 'PLUTO'),
(NOW(), 8, 'PLUTO', 'MERCURY'),
(NOW(), 9, 'MERCURY', 'EARTH'),
(NOW(), 10, 'EARTH', 'NEPTUNE');
