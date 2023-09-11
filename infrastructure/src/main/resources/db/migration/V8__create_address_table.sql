CREATE TABLE address (
      customer_id INT PRIMARY KEY,
      zip_code VARCHAR(8) NOT NULL,
      street VARCHAR(100) NOT NULL,
      neighborhood VARCHAR(100) NOT NULL,
      complement VARCHAR(100),
      city VARCHAR(100) NOT NULL,
      state VARCHAR(2) NOT NULL,
      FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  );
