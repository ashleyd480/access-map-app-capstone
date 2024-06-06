import { ThemeContext } from "../../contexts/ThemeContext";

import { useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { UserDataContext } from "../../contexts/UserDataContext";
import { UsernameContext } from "../../contexts/UsernameContext";

import { updateData } from "../../api/api";
import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import Row from "react-bootstrap/Row";

const MyAccountEdit = () => {
  const {theme, setTheme} = useContext(ThemeContext)

  const { username } = useContext(UsernameContext);
  const { userData, setUserData } = useContext(UserDataContext);
  const navigate = useNavigate();
  //userdata to initlize form
  //if user data was not initlized then we are updating unitalized states like username, etc
  //make a copy of userData so we can display it and still modify it without updating the userdata
  // we are updating the copy of user data so thats why we do formData.username,etc
  const [formData, setFormData] = useState({ ...userData });
  const [error, setError] = useState("");

  console.log(formData);

  console.log(userData);

  const handleChange = (event) => {
    console.log(event);
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  // using event.preventDefault(), which is a method of the event object. Therefore, you need to pass event as a parameter to access it.

  const handleUpdateAccount = async (event) => {
    event.preventDefault();
    try {
      const responseData = await updateData(`users/${username}`, formData);
      console.log(responseData);
      if (!responseData.hasError) {
        // not need to do this because the put request updates the user on the backend and the next line is navigating to my account where the logic is for fetching it and updating the user state with the setUserData(responseData)
        //   setUserData(formData);
        navigate("/myaccount");
      } else {
        setError(responseData.error || "An error occurred during update.");
      }
    } catch (error) {
      //if API call unsuccessful
      setError("An error occurred during update.");
    }
  };

  const cancelEdit = () => {
    navigate("/myaccount");
  };

  /* controlId in the Form.Group component is used to associate the <Form.Control> with a label in order to improve accessibility and enable screen readers to understand the relationship between the label and the input field.*/
  return (
    <div className={`${theme} content edit-account` }>
  
      <Form>
        <Row className="mb-3">
          <Form.Group as={Col} md="4" controlId="validationFirstName">
            <Form.Label>First name</Form.Label>
            <Form.Control
              required
              type="text"
              placeholder="First name"
              name="firstName"
              onChange={handleChange}
              value={formData.firstName}
            />
          </Form.Group>
          <Form.Group as={Col} md="4" controlId="validationLastName">
            <Form.Label>Please enter a last name</Form.Label>
            <Form.Control
              required
              type="text"
              placeholder="Last name"
              name="lastName"
              onChange={handleChange}
              value={formData.lastName}
            />
          </Form.Group>

          <Form.Group as={Col} md="6" controlId="validationCustomEmail">
            <Form.Label>Email</Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              required
              name="email"
              onChange={handleChange}
              value={formData.email}
            />
          </Form.Group>
          <Form.Group as={Col} md="4" controlId="validationCustomUsername">
            <Form.Label>Username</Form.Label>
            <InputGroup hasValidation>
              <InputGroup.Text id="inputGroupPrepend">@</InputGroup.Text>
              <Form.Control
                type="text"
                placeholder="Username"
                aria-describedby="inputGroupPrepend"
                disabled
                value={formData.username}
              />
            </InputGroup>
          </Form.Group>
        </Row>

        <Row className="mb-3">
          <Form.Group as={Col} md="6" controlId="validationPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Password"
              required
              name="password"
              onChange={handleChange}
              value={formData.password}
            />
          </Form.Group>
        </Row>

        {/* Address field */}

        <Row className="mb-3">
          <Form.Group as={Col} md="6" controlId="validationCity">
            <Form.Label>City</Form.Label>
            <Form.Control
              type="text"
              placeholder="City"
              required
              name="city"
              onChange={handleChange}
              value={formData.city}
            />
          </Form.Group>
          <Form.Group as={Col} md="3" controlId="validationState">
            <Form.Label>State</Form.Label>
            <Form.Control
              type="text"
              placeholder="State"
              required
              name="state"
              onChange={handleChange}
              value={formData.state}
            />
          </Form.Group>
        </Row>

        <Form.Group className="mb-3"></Form.Group>

        <div className = "buttonGroup">
        <Button onClick={handleUpdateAccount}> Submit Updates </Button>

          <Button onClick={cancelEdit}> Go Back </Button>
          </div>
      </Form>

      <div aria-live="polite" className="errorContainer">
        {error && <h4>{error}</h4>}
      </div>
    </div>
  );
};
export default MyAccountEdit;

//add two buttons to go back
