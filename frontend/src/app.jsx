import './app.css'
import {BrowserRouter, Route, Router, Routes} from "react-router-dom";
import Home from "./home.tsx";
import Cars from "./cars.tsx";
import 'bootstrap/dist/css/bootstrap.min.css';
import NavBar from "./navbar.tsx";

function App() {

    return (
        <div>
            <NavBar/>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/cars" element={<Cars />} />
                    {/*<Route path="/customers" element={<CustomerList />} />*/}
                    {/*<Route path="/rentals" element={<RentalList />} />*/}
                    {/*<Route path="/car/create" element={<CarForm />} />*/}
                    {/*<Route path="/car/edit/:id" element={<CarForm />} />*/}
                </Routes>
            </BrowserRouter>
        </div>

    );
}

export default App
