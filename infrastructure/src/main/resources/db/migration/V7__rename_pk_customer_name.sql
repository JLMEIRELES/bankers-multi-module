ALTER TABLE account RENAME COLUMN client_id TO customer_id;
ALTER SEQUENCE client_id_seq RENAME TO customer_id_seq;
