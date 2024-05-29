import axios, {CancelTokenSource} from 'axios';

import {useEffect, useState} from "react";
import {Button, Card, Form} from 'react-bootstrap';
import {Formik, FormikHelpers} from "formik";

export interface Car {
    id?: number;
    make?: string;
    model?: string;
    manufactureYear?: number;
    fuelType?: FuelType;
    bodyType?: BodyType;
    isRented?: boolean;
}

export enum FuelType {
    ELECTRIC = "ELECTRIC",
    BENZINE = "BENZINE",
    DIESEL = "DIESEL"
}

export enum BodyType {
    SEDAN = "SEDAN",
    SUV = "SUV",
    CONVERTIBLE = "CONVERTIBLE"
}

let axiosCancelTokenSource: CancelTokenSource;

const Cars = () => {
    const [cars, setCars] = useState<Car[]>([]);
    const [selectedCarId, setSelectedCarId] = useState<number | undefined>();

    useEffect(() => {
        axios.get<Car[]>(`http://localhost:8080/cars`)
            .then(response => {
                console.warn(response.data);
                setCars(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the cars!', error);
            });
    }, []);

    const submitHandler = (car: Car, actions: FormikHelpers<Car>) => {
        const carDTO = {
            make: car.make,
            model: car.model,
            manufactureYear: car.manufactureYear,
            fuelType: car.fuelType,
            bodyType: car.bodyType
        }

        axiosCancelTokenSource = axios.CancelToken.source();
        axios
            .put(
                `http://localhost:8080/cars/update/${car.id}`,
                JSON.stringify(carDTO),
                {
                    headers: {
                        'Content-Type': 'application/json'
                    }, cancelToken: axiosCancelTokenSource.token
                }
            )
            .then(() => {
                setSelectedCarId(undefined);
                actions.resetForm();
                actions.setSubmitting(false);
            })
            .catch(() => {
                console.warn("Error")
            });
    };


    return (
        <div>
            <h1>Cars</h1>
            <div className="card-container p-2" style={{display: "flex", flexWrap: "wrap"}}>
                {cars.map(car => (<Formik
                        initialValues={car}
                        onSubmit={submitHandler}
                        key={car.id}
                    >
                        {({setFieldValue, submitForm}) => (
                            <Form style={{marginRight: "0.5rem", marginTop: "0.5rem"}} key={car.id}>
                                <div className="placement-right">
                                    <Button hidden={selectedCarId !== car.id} onClick={submitForm} variant="secondary"
                                            size="sm">
                                        Ok
                                    </Button>
                                    <Button onClick={() => setSelectedCarId(selectedCarId ? undefined : car.id)}
                                            variant={selectedCarId === car.id ? "light" : "secondary"} size="sm">
                                        {selectedCarId === car.id ? "X" : "Edit"}
                                    </Button>
                                </div>
                                <Card className="flex-box" border="secondary" style={{width: '18rem'}}>
                                    <Card.Header>
                                        <div>
                                            <Form.Control
                                                type="text"
                                                onChange={(event) => setFieldValue("model", event.target.value)}
                                                defaultValue={car.model}
                                                plaintext={selectedCarId !== car.id}
                                                width={"auto"}
                                            />
                                        </div>
                                    </Card.Header>
                                    <Card.Body>
                                        <Form.Control
                                            type="text"
                                            onChange={(event) => setFieldValue("make", event.target.value)}
                                            defaultValue={car.make}
                                            plaintext={selectedCarId !== car.id}
                                            width={"auto"}
                                        />
                                        <Form.Control
                                            type="text"
                                            onChange={(event) => setFieldValue("bodyType", event.target.value)}
                                            defaultValue={car.bodyType}
                                            plaintext={selectedCarId !== car.id}
                                        />
                                        <Form.Control
                                            type="text"
                                            onChange={(event) => setFieldValue("fuelType", event.target.value)}
                                            defaultValue={car.fuelType}
                                            plaintext={selectedCarId !== car.id}
                                        />
                                        <Form.Control
                                            type="text"
                                            onChange={(event) => setFieldValue("manufactureYear", event.target.value)}
                                            defaultValue={car.manufactureYear}
                                            plaintext={selectedCarId !== car.id}
                                        />
                                        <Form.Control
                                            type="text"
                                            value={car.isRented ? "Rented" : "Available"}
                                            plaintext
                                            readOnly
                                        />
                                    </Card.Body>
                                </Card>
                            </Form>
                        )}
                    </Formik>
                ))}
            </div>
        </div>
    );
};

export default Cars;
