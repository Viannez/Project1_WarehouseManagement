import {Nav, Navbar, NavLink} from 'react-bootstrap';
import { Link } from 'react-router-dom';
import './Navigation.css'

//Navigation bar
function Navigation() {
  return (
    <Navbar fixed="top" expand="lg" className="bg-body-tertiary" style={{}}>
      <Nav>
        <NavLink id="nav-warehouse" eventKey="1" as={Link} to="/warehouse">Warehouses</NavLink>
        <NavLink id="nav-product" eventKey="2" as={Link} to="/product">Products</NavLink>
      </Nav>
    </Navbar>
  );
}

export default Navigation;