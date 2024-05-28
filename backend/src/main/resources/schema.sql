CREATE TABLE car (
                     id BIGINT PRIMARY KEY AUTO_INCREMENT,
                     make VARCHAR(255) NOT NULL,
                     model VARCHAR(255) NOT NULL,
                     manufacture_year INT NOT NULL,
                     fuel_type VARCHAR(50) NOT NULL,
                     body_type VARCHAR(50) NOT NULL
);

CREATE TABLE customer (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL
);

CREATE TABLE rental (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        customer_id BIGINT,
                        car_id BIGINT,
                        start_date BIGINT,
                        end_date BIGINT,
                        kilometers_driven INT,
                        FOREIGN KEY (customer_id) REFERENCES customer(id),
                        FOREIGN KEY (car_id) REFERENCES car(id)
);
