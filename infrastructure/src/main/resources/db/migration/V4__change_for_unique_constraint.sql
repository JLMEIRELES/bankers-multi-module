DELETE FROM customer
WHERE id NOT IN (
    SELECT MIN(id)
    FROM customer
    GROUP BY email
);

DELETE FROM customer
WHERE id NOT IN (
    SELECT MIN(id)
    FROM customer
    GROUP BY document
);

ALTER TABLE customer
ADD CONSTRAINT uk_customer_email UNIQUE (email);

ALTER TABLE customer
ADD CONSTRAINT uk_customer_document UNIQUE (document);
