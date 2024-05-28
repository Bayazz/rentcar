import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

function NavBar() {
    return (
        <><Navbar bg="light" data-bs-theme="light">
            <Container>
                <Navbar.Brand href="/">Home</Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link href="cars">Cars</Nav.Link>
                    <Nav.Link href="customers">Customers</Nav.Link>
                    <Nav.Link href="rentals">Rentals</Nav.Link>
                </Nav>
            </Container>
        </Navbar>
        </>
    );
}

export default NavBar;
