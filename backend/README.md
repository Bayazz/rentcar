# Car Rental Service Backend

This project is a backend application for a Car Rental Service. It is built using Spring Boot and provides a RESTful API to manage cars, customers, and rentals. The application uses an H2 in-memory database for simplicity.

## Installation

### Prerequisites

- Java 17 or higher
- Gradle 6.8 or higher

### Steps

1. Clone the repository:

    ```sh
    git clone https://github.com/Bayazz/rentcar.git
    cd rentcar
    ```

2. Build the project:

    ```sh
    ./gradlew build
    ```

3. Run the application:

    ```sh
    ./gradlew bootRun
    ```

## Usage

### Initial Setup

To initialize the H2 database with mock data, you need to run the SQL scripts provided. These scripts create the necessary tables and insert sample data.

1. Run the schema script to create the database schema. Save the following content in a file named `schema.sql`:

2. Run the data script to insert mock data. Save the following content in a file named `data.sql`:


### Accessing the API

The API can be accessed at `http://localhost:8080`. Swagger UI is available for exploring and testing the endpoints at `http://localhost:8080/swagger-ui/index.html`.

## API Documentation

The API documentation is generated using Swagger and can be accessed at `http://localhost:8080/swagger-ui.html`. The following endpoints are available:

- **Cars**
  - `GET /cars`: Get all cars with their rental status
  - `GET /cars/{id}`: Get a car by ID with its rental status
  - `POST /cars/create`: Create a new car
  - `PUT /cars/update/{id}`: Update an existing car
  - `DELETE /cars/delete/{id}`: Delete a car

- **Customers**
  - `GET /customers`: Get all customers
  - `GET /customers/{id}`: Get a customer by ID
  - `POST /customers/create`: Create a new customer
  - `PUT /customers/update/{id}`: Update an existing customer
  - `DELETE /customers/delete/{id}`: Delete a customer

- **Rentals**
  - `GET /rentals`: Get all rentals
  - `GET /rentals/{id}`: Get a rental by ID
  - `POST /rentals/create`: Create a new rental
  - `PUT /rentals/update/{id}`: Update an existing rental
  - `DELETE /rentals/delete/{id}`: Delete a rental
  - `GET /rentals/current`: Get current rentals

## Database Schema

The database schema is defined in `schema.sql`. It includes tables for `cars`, `customers`, and `rentals`.
