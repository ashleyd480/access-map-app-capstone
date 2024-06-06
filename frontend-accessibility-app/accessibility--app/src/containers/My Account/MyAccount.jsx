import "./MyAccount.css"
import { ThemeContext } from "../../contexts/ThemeContext";

import { useState, useEffect, useContext } from "react";
import Button from "react-bootstrap/Button";
import { useNavigate } from "react-router-dom";
import { UserDataContext } from "../../contexts/UserDataContext";
import { AuthContext } from "../../contexts/AuthContext";
import { UsernameContext } from "../../contexts/UsernameContext";
import { fetchData } from "../../api/api";

const MyAccount = () => {
  const { username } = useContext(UsernameContext);
  const {theme, setTheme} = useContext(ThemeContext)


  console.log(username);
  const navigate = useNavigate();
  const { userData, setUserData } = useContext(UserDataContext); //
  console.log(userData);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const responseData = await fetchData(`users?username=${username}`);
        if (!responseData.hasError) {
          setUserData(responseData);
        } else {
          setError(
            responseData.error || "An error occurred while fetching user data."
          );
        }
      } catch (error) {
        setError("An error occurred while fetching user data.");
      }
    };

    if (username) {
      fetchUserData();
    }
  }, []);
  const goToEditAccount = () => {
    navigate("/myaccount/edit");
  };

  const goToDeleteAccount = () => {
    navigate("/myaccount/delete");
  };

  //set response data to the user data so that I can provide it as a context api variable

  return (
    <div className={`${theme} content` }>
    <h2>My Account</h2>
      <div className = "buttonGroup">

      <Button variant="primary" onClick={goToEditAccount}>
        Edit My Account
      </Button>

      <Button variant="primary" onClick={goToDeleteAccount}>
        Delete My Account
        </Button>

      </div>
      
      <hr></hr>

      {/* Display user data */}
      <div>

      <h4>User Details</h4>
  <label><strong>Username:</strong> </label>
  <span> {username}</span>
</div>
<div>
  <label><strong>Password:</strong> </label>
  <span> {userData.password}</span>
</div>
<div>
  <label><strong>First Name:</strong> </label>
  <span> {userData.firstName}</span>
</div>
<div>
  <label><strong>Last Name:</strong> </label>
  <span> {userData.lastName}</span>
</div>
<div>
  <label><strong>Email:</strong> </label>
  <span> {userData.email}</span>
</div>

   

      <div className= "address-details">
        <h4>Address Details</h4>
        <div>
          <label><strong> City:</strong> </label>
          <span> {userData.city}</span>
        </div>

        <div>
          <label><strong> State:</strong> </label>
          <span>{userData.state}</span>
        </div>
        </div>
      {error && <h4 className="errorContainer">{error}</h4>}
      </div>
  );
};

export default MyAccount;
