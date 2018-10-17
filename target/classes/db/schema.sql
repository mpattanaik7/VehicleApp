CREATE SCHEMA vehicle_app_db AUTHORIZATION sa;

CREATE TABLE vehicle ( 
   id INT NOT NULL, 
   name VARCHAR(50) NOT NULL, 
   type INT NULL,
   version INT,
   date DATE NULL
);
--dummy data
INSERT INTO vehicle VALUES (5, 'Hardik1', 1,1,null); 
INSERT INTO vehicle VALUES (6, 'Komal2', 2,2,null); 
INSERT INTO vehicle VALUES (7, 'Muffy5', 3,1,null);
 