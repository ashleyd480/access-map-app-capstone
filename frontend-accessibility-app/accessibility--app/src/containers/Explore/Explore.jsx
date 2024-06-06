import { useState, useEffect, useContext } from "react";
import { UsernameContext } from "../../contexts/UsernameContext";
import { fetchData } from "../../api/api";
import PlaceResultsList from "../../components/PlacesResultList/PlacesResultList";
import { ContributeContext } from "../../contexts/ContributeContext";
import { ThemeContext } from "../../contexts/ThemeContext";

const Explore = () => {
  const {theme, setTheme} = useContext(ThemeContext)

    const { username } = useContext(UsernameContext);
    const [error, setError] = useState("");

    console.log(username);
    const { setShowReviewandTagButton
    } = useContext(ContributeContext);

    setShowReviewandTagButton(false);

    const [placesNearYouResults, setPlacesNearYouResults] = useState([]);
    useEffect(() => {
        const fetchPlacesNearYouData = async () => {
          try {
            const responseData = await fetchData(`places/users/${username}`);
            if (!responseData.hasError) {
              setPlacesNearYouResults(responseData);
            } else {
              setError(
                responseData.error || "An error occurred while fetching places near you."
              );
            }
          } catch (error) {
            setError("An error occurred while fetching places near you.");
          }
        };
    
        if (username) {
            fetchPlacesNearYouData();
        }
      }, []);

    return (
      <div className={`${theme} content` }>
      <h2> Here are accessible place near you in your city: </h2>
            <div>
      {/* Render PlaceResultsList only when placeSearchResults is available */}
        {placesNearYouResults.length>0 ? (
      <PlaceResultsList placeSearchResultsProp={placesNearYouResults} />
    ) : (
            
              
              <p className="errorContainer">{error}</p>
            )}
   
  </div>

  </div>
    );
}

export default Explore;

// we pass the search results from get mapping to my placeList
// prop variable placeSearchResults