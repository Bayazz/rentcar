-- Inserting mock data into the customer table
INSERT INTO customer (name, email) VALUES
                                           ('John Doe', 'john.doe@example.com'),
                                           ('Jane Smith', 'jane.smith@example.com');

-- Inserting mock data into the car table
INSERT INTO car (make, model, manufacture_year, fuel_type, body_type) VALUES
                                                                          ('Tesla', 'Model S', 2022, 'ELECTRIC', 'SEDAN'),
                                                                          ('Ford', 'Mustang', 2021, 'BENZINE', 'CONVERTIBLE'),
                                                                          ('Toyota', 'RAV4', 2021, 'DIESEL', 'SUV'),
                                                                          ('Audi', 'A4', 2020, 'BENZINE', 'SEDAN'),
                                                                          ('Tesla', 'Model X', 2022, 'ELECTRIC', 'SUV'),
                                                                          ('Volkswagen', 'Beetle', 2019, 'BENZINE', 'CONVERTIBLE');


-- Inserting mock data into the rental table
INSERT INTO rental (customer_id, car_id, start_date, end_date, kilometers_driven) VALUES
                                                                                      (1, 1, 1609459200, 1609545600, 300),  -- Dates are Unix timestamps for example
                                                                                      (1, 2, 1609638400, 1609724800, 150),
                                                                                      (2, 1, 1609814400, 1609900800, 220);
