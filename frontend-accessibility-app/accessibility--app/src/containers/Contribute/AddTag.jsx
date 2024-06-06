import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";
import { useState, useEffect, useContext } from "react";
import { useParams } from "react-router-dom";
import { fetchData } from "../../api/api";
import { postData } from "../../api/api";
import { Button } from "react-bootstrap";
import Tag from "../../components/Tag/Tag";


const AddTag = () => {

  const navigate = useNavigate();
  //tags the user can choose from that are not yet exist on the place
  const [availableTags, setAvailableTags] = useState("");

// to get the placeDetails from the page we navigated from so we can render the message relevant to that place
  const location = useLocation();
  const { placeDetails } = location.state;

  // so we can use the place id of the page url which allows us to navigate back to the place detail page
  const id = useParams();
  const placeId = id.id;

  const [error, setError] = useState("");
  const [message, setMessage] = useState("")

  // it takes the tag Id of the button we are on and that is the request body
  const handleAddTag = async (tagId) => {
    try {
      const responseData = await postData(
        `tags/${tagId}/places/${placeId}`,
    tagId
      );
   
      if (!responseData.hasError) {
        console.log(responseData);
        setMessage("The tag has been sucessfully added. Click Go Back when you are finished adding tags")
        // Remove the added tag from the availableTags list
        /* takes state of available tags and then filters it to tag id that don't match the one that was added*/
          setAvailableTags(prevTags => prevTags.filter(tag => tag.tagId !== tagId));
       
      } else {
        console.log(responseData);
        setError(
          responseData.error || "An error occurred during adding tag"
        );
      }
    } catch (error) {
      //if API call unsuccessful
      setError("An error occurred during adding tag.");
    }
  };



  useEffect(() => {
    const fetchAvailableTags = async () => {
      try {
        const responseData = await fetchData(`tags/places/${placeId}`);
        if (!responseData.hasError) {
          setAvailableTags(responseData);
          console.log(responseData)
        } else {
          setError(
            responseData.error || "There are no tags to add for this location. All available ones have been chosen for this place."
          );
        }
      } catch (error) {
        setError("There was an issue loading available tags.");
      }
    };

    if (placeId) {
      fetchAvailableTags();
    }
  }, []);

  const handleGoBack = () => {
    navigate(`/places/${placeId}`);
  }

    return (
     
      <div className= "content">
        <h3>
    Please add any additional tags you saw for {placeDetails.placeName} located at{" "}
    {placeDetails.streetAdress} in {placeDetails.city} in {placeDetails.state}
        </h3>
        <hr></hr>
        <br></br>
        <h4>You will only see tags that have not yet been added</h4>
        <h4>Click to add tags for accessibility features you saw.</h4>
        <h4>When you are done with that, click on Go Back to exit out of this page.</h4>
        <h4>This will take you back to the place detail page where you can see any tags you have added.</h4>
        <br></br>
        <hr></hr>

        
  {availableTags.length > 0 && (
    <ul>
      {availableTags.map((tag, index) => (
        <li key={index}>
          <Tag tagName={tag.tagName} tagId={tag.tagId} handleAddTag={handleAddTag} />
        </li>
      ))}
    </ul>
  )}
         
        {error && <h4 className="errorContainer">{error}</h4>}

        {message && <h4> {message} </h4>}
        <Button onClick = {handleGoBack}> Go Back </Button>

      </div>
      
    );
  };
  
  // have to create seperate tag component or else if we simply map, the index for the tag won't be the right one to correspond to the actual tag id

export default AddTag;