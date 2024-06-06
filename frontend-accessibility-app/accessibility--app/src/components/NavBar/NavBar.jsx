import { Link } from "react-router-dom";
import { Navbar, Nav, NavDropdown, Button, Form } from 'react-bootstrap';

import "./NavBar.css";
import { ThemeContext } from "../../contexts/ThemeContext";
import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { AuthContext } from '../../contexts/AuthContext';


const NavBar = () => {
    const { theme, setTheme } = useContext(ThemeContext);
    const { userSignedIn, setUserSignedIn } = useContext(AuthContext);
    const navigate = useNavigate();
    const username = localStorage.getItem("username");
// we get username from local storage and then display the first initial

  const handleSetTheme = () => {
    setTheme(theme === "light" ? "dark" : "light");
  };
    
    const handleSignOut = () => {
        setUserSignedIn(false)
        navigate('/');
        localStorage.setItem("username", "")
    }

    //We want to clear the local storage on sign out 
  useEffect(() => {
    if (!userSignedIn) {
        navigate('/signin');
    }
}, [userSignedIn]);  

// navbrand component creates the brand section of the navigation bar. Here, it contains the text "Accessibility Map" logo

return (
    <Navbar bg="light" expand="lg" className="nav">
      <Navbar.Brand as={Link} to="/">
      Accessibility Map
      <img
          src={"src/assets/map-logo.png"}
          width="30"
          height="30"
          className="d-inline-block align-top"
          alt="Logo"
        />
      </Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="mr-auto">
          <Nav.Link as={Link} to="/">
            Home
          </Nav.Link>
          <Nav.Link as={Link} to="/explore">
            Explore
          </Nav.Link>
          <Nav.Link as={Link} to="/contribute">
            Contribute
        </Nav.Link>
        <Nav.Link as={Link} to="/search">
            Search
          </Nav.Link>
        </Nav>
        <Nav>
          <NavDropdown
            title={
              <Button className="user-initial">
                {username[0].toUpperCase()}
              </Button>
            }
        
     
          >
            <NavDropdown.Item as={Link} to="/profile">
              Profile
            </NavDropdown.Item>
            <NavDropdown.Item as={Link} to="/myaccount">
              My Account
            </NavDropdown.Item>
            <NavDropdown.Divider />
            <NavDropdown.Item onClick={handleSignOut}>
              Sign Out
            </NavDropdown.Item>
          </NavDropdown>
          {/* <Button variant="outline-secondary" onClick={handleSetTheme}>
            Toggle Theme
          </Button> */}
         <Form.Check
    type="switch"
    className="theme-toggle"
    label="Dark Mode"
    onChange={handleSetTheme}
  />
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default NavBar