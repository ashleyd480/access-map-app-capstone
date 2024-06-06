import Button from "react-bootstrap/Button";
import { useNavigate } from "react-router-dom";
import { useContext } from "react";
import React, { useState, useEffect } from "react";
import { ThemeContext } from "../../contexts/ThemeContext";

import { UsernameContext } from "../../contexts/UsernameContext";
import { deleteData } from "../../api/api";

const MyAccountDelete = () => {
  const navigate = useNavigate();
  const {theme, setTheme} = useContext(ThemeContext)
  const { username } = useContext(UsernameContext);
  const [deletedMessage, setDeletedMessage] = useState("");
  const [error, setError] = useState("");

  const cancelDelete = () => {
    navigate("/myaccount");
  };

  //pass along successful deletion message as state (when using navigate)
  const handleDeleteAccount = async () => {
    try {
      const responseData = await deleteData(`users/${username}`);
      if (!responseData.hasError) {
        setDeletedMessage(responseData.message);
        navigate("/myaccount/delete-done", {
          state: { message: responseData.message },
        });
      } else {
        setError(responseData.error || "Unable to delete account.");
      }
    } catch (error) {
      setError("An error occurred during delete.");
    }
  };

  return (
    <div className={`${theme} content` }>

      <section className="delete-confirm">
      <h2>We are sorry to see you go.</h2>
        <h4>Are you sure you want to delete your account?</h4>
        </section>
      <div className="buttonGroup">
        <Button onClick={handleDeleteAccount}>Yes, Delete</Button>
        <Button onClick={cancelDelete}>No, Go Back</Button>
      </div>
      {error && <h4 className="errorContainer">{error}</h4>}{" "}
    </div>
  );
};

export default MyAccountDelete;
