DO $$
DECLARE
    chave_estrangeira text;
BEGIN
    -- Descubra o nome da única chave estrangeira na tabela "address"
    SELECT conname INTO chave_estrangeira
    FROM pg_constraint
    WHERE confrelid = 'address'::regclass::oid AND connamespace = 'public'::regnamespace
    LIMIT 1;

    -- Verifique se a chave estrangeira foi encontrada
    IF chave_estrangeira IS NOT NULL THEN
        -- Remova a chave estrangeira usando o nome encontrado
        EXECUTE 'ALTER TABLE address DROP CONSTRAINT ' || chave_estrangeira;
    END IF;
END $$;

ALTER TABLE address
RENAME COLUMN customer_id TO user_id;

ALTER TABLE address
ADD CONSTRAINT fk_address_user
FOREIGN KEY (user_id)
REFERENCES "user" (id);
