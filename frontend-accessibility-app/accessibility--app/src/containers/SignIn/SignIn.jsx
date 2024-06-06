import Form from "react-bootstrap/Form";
import { Button } from "react-bootstrap";
import { useState, useEffect, useContext } from "react";
import { postData } from "../../api/api";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../../contexts/AuthContext";
import { UsernameContext } from "../../contexts/UsernameContext";
// import { UserDataContext } from "../../contexts/UserDataContext";
import "./SignIn.css";

const SignIn = () => {
  //useContext to access "global" states
  const { username, setUsername } = useContext(UsernameContext);
  // const { setUserData } = useContext(UserDataContext);
//   const [username, setUsername] = useState('');
  const [password, setPassword] = useState("");
  //use states
  const { userSignedIn, setUserSignedIn} = useContext(AuthContext);


  // error message
  const [error, setError] = useState("");
  //API response

  // navigate hook
  const navigate = useNavigate();

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };
    
    console.log(username);

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleLogin = async () => {
    try {
      const loginData = { username: username, password: password };

      const responseData = await postData("users/login", loginData);
   
      // Check if the login was successful
      if (!responseData.hasError) {
        // Save the username to localStorage
        localStorage.setItem("username", username);
        setUsername(username);

        setUserSignedIn(true);
        navigate("/");
      } else {
        // Handle unsuccessful login and use || for uncatched error
        setError(responseData.error || "An error occurred during login.");

    }
      console.log(responseData);
    } catch (error) {
      //if API call unsuccessful
      setError("An error occurred during login.");
    }
  };

  // if user signed in - send them homepage
  useEffect(() => {
    if (userSignedIn) {
      navigate("/");
    }
  }, [userSignedIn]);

  const handleSignUpClick = () => {
    console.log("hello");
    navigate("/signup");
  };

  return (
    <div className="sign-in">
          <img
          src={"src/assets/map-logo.png"}
          width="30"
          height="30"
          className="d-inline-block align-top"
          alt="Logo"
      />
        
      <h2> Welcome to Access Map </h2>
      <br></br>
      <hr>
      </hr>
      <br></br>
  
      <Form>
        <Form.Group className="mb-3" controlId="loginForm.ControlInput1">
          <Form.Label>Username</Form.Label>
          <Form.Control
            type="Username"
            placeholder="username"
            value={username}
            onChange={handleUsernameChange}
            name="username"
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="loginForm.ControlInput1">
          <Form.Label>password </Form.Label>
          <Form.Control
            type="Password"
            placeholder="password"
            value={password}
            onChange={handlePasswordChange}
            name="password"
          />
        </Form.Group>

        <div className = "buttonGroup">
        <Button onClick={handleLogin} aria-label="Login" >Login</Button>
          <Button onClick={handleSignUpClick} aria-label="Sign Up">Sign Up</Button>
          </div>
      </Form>
      <div aria-live="polite" className="errorContainer">
  {error && <h4>{error}</h4>}
</div>
    </div>
  );
};

export default SignIn;
