import { postData } from "../../api/api";
import { useParams } from "react-router-dom";
import { useState, useEffect, useContext } from "react";
import { UsernameContext } from "../../contexts/UsernameContext";
import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import Row from "react-bootstrap/Row";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";

// use location hook to the pass the place details to the review page

const AddReview = () => {
  const location = useLocation();
  const { placeDetails } = location.state;
  const id = useParams();
  // Get the ID parameter from the URL
 
  const placeId = id.id;
  console.log(placeId);

  const [formData, setFormData] = useState({
    rating: 3 ,
    comment: "",
  });
  const [error, setError] = useState("");
  const { username } = useContext(UsernameContext);
  const navigate = useNavigate();

  console.log(username);

  console.log("Rating:", formData.rating);
  console.log("Comment:", formData.comment);

  const handleChange = (event) => {
    console.log(event);
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const handleAddReview = async () => {
    // event.preventDefault();
    try {
      const responseData = await postData(
        `reviews/users/${username}/places/${placeId}`,
        formData
      );
   
      if (!responseData.hasError) {
        console.log(responseData);
        navigate(`/places/${placeId}`);
      } else {
        console.log(responseData);
        setError(
          responseData.error || "An error occurred during submitting review."
        );
      }
    } catch (error) {
      //if API call unsuccessful
      setError("An error occurred during submitting review.");
    }
  };

  //we render the radio buttons dynamically iterating through the array of numbers to render the radio buttons

  // value is the vallue of each radio button
  // When the user selects a radio button, its value is set as the rating in the formData state.
  // need to use the checked attribute in your radio buttons to control which one is selected based on the component's state. Without the checked attribute, React won't know which radio button should be selected when the form renders.
  // By setting checked={formData.rating === value.toString()}, you ensure that the component re-renders correctly whenever the state changes.
  return (
    <div className= "content">
      <h4>
        {" "}
        Please write a review for {placeDetails.placeName} located at{" "}
        {placeDetails.streetAdress} in {placeDetails.city} and{" "}
        {placeDetails.state}{" "}:
      </h4>
      <Form.Group>
        <Form.Label>Rating:</Form.Label>
        <div>
                  {[1, 2, 3, 4, 5].map((value) => {
                     
                      console.log(formData.rating)
                      console.log(value)
              return (
                <Form.Check
                  key={value} // needed because we are mapping over it 
                  type="radio"
                //   id={`rating-${value}`}
                  label={value}
                  name="rating"
                  value={value}
                  checked={formData.rating === value.toString()}
                  //^ This evaluates to true for the radio button that matches the current rating 
                //   checked={false}
                  onChange={handleChange}
                />
              )
          })}
        </div>
      </Form.Group>
      <Form.Group>
        <Form.Label>Comment:</Form.Label>
        <Form.Control
          type="text"
          name="comment"
          value={formData.comment}
          onChange={handleChange}
        />
          </Form.Group>
          <div className = "buttonGroup">
      <Button variant="primary" onClick={handleAddReview}>
        Submit Review
          </Button>
          </div>
      {error && <h4 className="errorContainer">{error}</h4>}
    </div>
  );
};

export default AddReview;
