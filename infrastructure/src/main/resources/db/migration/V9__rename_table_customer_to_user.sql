ALTER TABLE customer RENAME TO "user";

ALTER TABLE "user"
ADD COLUMN password VARCHAR(250), ADD COLUMN user_type varchar(50);
