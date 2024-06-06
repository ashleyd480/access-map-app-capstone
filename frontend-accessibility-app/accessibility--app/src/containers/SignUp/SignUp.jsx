import "./SignUp.css"
import { useState } from "react";
import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import Row from "react-bootstrap/Row";
import { useNavigate } from "react-router-dom";
import { postData } from "../../api/api";

const SignUp = () => {
  const [error, setError] = useState("");

  // navigate hook
  const navigate = useNavigate();

  const [signupFormData, setSignupFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    username: "",
    password: "",
    city: "",
    state: "",
  });

  console.log(signupFormData);

  // const onSignupButtonClick = (event) => {

  //   handleSignUp()
  // }

  const handleChange = (event) => {
    const { name, value } = event.target;
    setSignupFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const cancelSignUp = () => {
    navigate("/signin");
  };

  const handleSignUp = async () => {
    // event.preventDefault();
    try {
      const responseData = await postData("users/signup", signupFormData);
      console.log(responseData);
      if (!responseData.hasError) {
        navigate("/signupsuccess");
      } else {
        setError(responseData.error || "An error occurred during signup.");
      }
    } catch (error) {
      //if API call unsuccessful
      setError("An error occurred during signup.");
    }
  };

  return (
    <div className="sign-up">
      <section className= "sign-up-header">
      <img
          src={"src/assets/map-logo.png"}
          width="30"
          height="30"
          className="d-inline-block align-top"
          alt="Logo"
        />
      
        <h2> Register for Access Map: </h2>
        </section>
      <p> Make sure to fill in every field. </p>
      <p> Note: state is the abbreviation with 2 letters (example: FL for Florida). </p>
      <Form className= "sign-up-form">
        <Row className="mb-3">
          <Form.Group as={Col} md="4" controlId="validationFirstName">
            <Form.Label>First name</Form.Label>
            <Form.Control
              required
              type="text"
              placeholder="First name"
              name="firstName"
              onChange={handleChange}

              // onChange={handleFirstNameChange}
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
                required
                name="username"
                onChange={handleChange}
              />
            </InputGroup>
          </Form.Group>
        </Row>

        <Row className="mb-3">
          <Form.Group as={Col} md="6" controlId="validationCustomPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Enter password"
              required
              name="password"
              onChange={handleChange}
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
            />
          </Form.Group>
        </Row>

        <Form.Group className="mb-3"></Form.Group>
        <div className = "buttonGroup">
        <Button onClick={handleSignUp} aria-label="Sign Up">Sign up</Button>
          <Button onClick={cancelSignUp} aria-label="Cancel Sign Up">Cancel Sign Up</Button>
          </div>
      </Form>

    
      <div aria-live="polite" className="errorContainer">
        {error && <h4>{error}</h4>}
       
        </div>
    </div>
  );
};

// if there's an error, we render the error
// aria-live= polite = when error message is announced, it will be announced politely without interrupt user current activity

export default SignUp;
