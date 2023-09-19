INSERT INTO "user" (id, name, document, born_date, email, user_type, password)
SELECT id, name, document, born_date, email, 'CUSTOMER', 'password' FROM customer;

ALTER TABLE customer
DROP COLUMN name, DROP COLUMN document, DROP COLUMN born_date, DROP COLUMN email;

ALTER TABLE customer
ADD COLUMN customer_type VARCHAR(100);

ALTER TABLE customer
RENAME COLUMN id TO user_id;

ALTER TABLE customer
ADD CONSTRAINT  fk_user_customer
FOREIGN KEY (user_id)
REFERENCES "user"(id);
