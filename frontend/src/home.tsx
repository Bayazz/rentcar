import { Link } from 'react-router-dom';
import {Badge, Button, Stack} from "react-bootstrap";

const Home = () => {
    return (
        <div>
            <Button variant="primary">
                Profile <Badge bg="secondary">9</Badge>
                <span className="visually-hidden">unread messages</span>
            </Button>



            <h1>Car Rental Service</h1>
            <nav>
                <ul>
                    <li><Link to="/cars">Cars</Link></li>
                    <li><Link to="/customers">Customers</Link></li>
                    <li><Link to="/rentals">Rentals</Link></li>
                </ul>
            </nav>
        </div>
    );
};

export default Home;
