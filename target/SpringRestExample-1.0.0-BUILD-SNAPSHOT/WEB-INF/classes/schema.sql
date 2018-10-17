CREATE SCHEMA test_schema AUTHORIZATION sa;

CREATE TABLE vehicle ( 
   id INT NOT NULL, 
   name VARCHAR(50) NOT NULL, 
   type INT NOT NULL,
   version INT NOT NULL, 
   create_date DATE
);