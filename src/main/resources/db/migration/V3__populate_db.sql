ALTER TABLE client
ADD COLUMN ticket_id BIGINT;

ALTER TABLE client
ADD CONSTRAINT fk_ticket_id
FOREIGN KEY (ticket_id) REFERENCES ticket(ID);

--ALTER TABLE ticket
--DROP CONSTRAINT fk_client_id;

ALTER TABLE ticket
ADD CONSTRAINT fk_client_id
FOREIGN KEY (CLIENT_ID) REFERENCES client(ID) ON DELETE CASCADE;

ALTER TABLE ticket
ALTER COLUMN CREATED_AT SET NOT NULL,
ALTER COLUMN CLIENT_ID SET NOT NULL,
ALTER COLUMN FROM_PLANET_ID SET NOT NULL,
ALTER COLUMN TO_PLANET_ID SET NOT NULL;