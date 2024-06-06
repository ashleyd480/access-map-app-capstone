import "./PlaceDetailComponent.css";
import { useContext } from "react";
import { ContributeContext } from "../../contexts/ContributeContext";
import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
// it receives placeDetailProp from PlaceDetails component
const PlaceDetailComponent = ({ placeDetailsProp }) => {
  const { showReviewandTagButton } = useContext(ContributeContext);
  const navigate = useNavigate();
  const placeId = placeDetailsProp.placeId; // so that placeId is passed along in url to allow me to add review and tag to place
  // const handleAddReview = () => navigate(`/places/${placeId}/addreview`);
  // const handleAddTag = () => navigate(`/places/${placeId}/addtag`);

  // pass state with navigating to another page
  const handleAddReview = () => {
    navigate(`/places/${placeDetailsProp.placeId}/addreview`, {
      state: { placeDetails: placeDetailsProp },
    });
  };

  const handleAddTag = () => {
    navigate(`/places/${placeDetailsProp.placeId}/addtag`, {
      state: { placeDetails: placeDetailsProp },
    });
  };

  return (
    <>
      {showReviewandTagButton && (
        <>
          {" "}
          <div className="buttonGroup">
          <Button onClick={handleAddReview}> Add Review</Button>
            <Button onClick={handleAddTag}>Add Tag</Button>{" "}
            </div>
        </>
      )}
      <h2>{placeDetailsProp.placeName}</h2>
      <hr></hr>
      <h3>Address Info</h3>
      <ul>
        <li>
          <strong>Street Address</strong>: {placeDetailsProp.streetAddress}
        </li>
        <li>
          <strong>City</strong>: {placeDetailsProp.city}
        </li>
        <li>
          <strong>State</strong>: {placeDetailsProp.state}
        </li>
    
      </ul>
      <hr></hr>
      <h3>Place Info</h3>
      <p>Category: {placeDetailsProp.category}</p>
      {placeDetailsProp.tags && (
        <>
          <h6>Feature Tags:</h6>
          <ul>
            {placeDetailsProp.tags.map((tag, tagIndex) => (
              <li key={tagIndex}>{tag.tagName}</li>
            ))}
          </ul>

          <h4> Reviews</h4>
          <div>
            {placeDetailsProp.reviews.map((review, reviewIndex) => (
              <div key={reviewIndex} className="reviewBox">
                <p>
                  <strong>Time reviewed:</strong> {review.timeReviewCreated}
                </p>
                <p>
                  <strong>Reviewed by:</strong> {review.user.username}
                </p>
                <p>
                  <strong>Accessibility rating:</strong> {review.rating}
                </p>
                <p>
                  <strong>Accessibility comment:</strong> {review.comment}
                </p>
              </div>
            ))}
          </div>
        </>
      )}
    </>
  );
};

export default PlaceDetailComponent;
