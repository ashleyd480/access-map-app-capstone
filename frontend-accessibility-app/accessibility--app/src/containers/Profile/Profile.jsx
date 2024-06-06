import { useState, useEffect, useContext } from "react";
import { fetchData } from "../../api/api";
import { UsernameContext } from "../../contexts/UsernameContext";
import { ThemeContext } from "../../contexts/ThemeContext";


const Profile = () => {
  const [error, setError] = useState("");
  const [userProfile, setUserProfile] = useState({}); // needed to set this as object 

  const { username } = useContext(UsernameContext);
  const {theme, setTheme} = useContext(ThemeContext)


  useEffect(() => {
    const fetchProfileData = async () => {
      try {
        const responseData = await fetchData(`profiles/${username}`);
        if (!responseData.hasError) {
          setUserProfile(responseData);
          console.log(responseData);
          
        } else {
          setError(
            responseData.error || "An error occurred while fetching profile."
          );
        }
      } catch (error) {
        setError("An error occurred while fetching profile.");
      }
    };

    if (username) {
      fetchProfileData();
    }
  }, []);

  return (
    <div className={`${theme} content`}>
      {console.log(userProfile)}
      <h2>User Stats</h2>

      <div>
        <label>Number of Places Reviewed: </label>
        <span>{userProfile.numberPlacesReviewsContributedTo}</span>
        {error && <h4 className="errorContainer">{error}</h4>}
      </div>

      {userProfile.userReviews ? (
        userProfile.userReviews.map((review, reviewIndex) => (
          <div key={reviewIndex} className="reviewBox">
            <p>Place Reviewed: {review.placeName}</p>
            <p>Time Reviewed: {review.timeReviewCreated}</p>
            <p>Accessibility rating: {review.rating}</p>
            <p>Accessibility comment: {review.comment}</p>
          </div>
        ))
      ) : (
        <p>No reviews available.</p>
      )}
    </div>
  );
};

export default Profile;