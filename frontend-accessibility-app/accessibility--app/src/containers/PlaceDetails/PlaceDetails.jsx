import { fetchData } from "../../api/api";
import { useState, useEffect, useContext } from "react";
import { UsernameContext } from "../../contexts/UsernameContext";
import { useParams } from "react-router-dom";
import PlaceDetailComponent from "../../components/PlaceDetailComponent/PlaceDetailComponent";
import { ThemeContext } from "../../contexts/ThemeContext";


const PlaceDetails = () => {
    const {theme, setTheme} = useContext(ThemeContext)

    const { username } = useContext(UsernameContext);
    const [error, setError] = useState("");
    const [detailsForThisPlace, setDetailsForThisPlace] = useState("")

    const id = useParams(); // Get the ID parameter from the URL
    const placeIdString = id.placeId; //id was displaying as an object with placeId as attribute 
    const placeId = parseInt(placeIdString);
    console.log(id);
    console.log(placeId)

    useEffect(() => {
        const fetchDetailsForThisPlace = async () => {
            try {
                const responseData = await fetchData(`places/${placeId }`);
                if (!responseData.hasError) {
                    setDetailsForThisPlace(responseData)
                    console.log(responseData);
                }
                else {
                    setError(
                        responseData.error || "An error occurred while fetching place detail info")
                    }
                } catch (error) {
                  setError("An error occurred while fetching place detail info");
                }
              };
          
              if (username) {
                fetchDetailsForThisPlace();
              }
            }, []);
   
   
    return (
        <div className={`${theme} content` }>
        {detailsForThisPlace ? (
<PlaceDetailComponent placeDetailsProp = {detailsForThisPlace} /> )
            :    <p className= "errorContainer">{error}</p>
}
          
        </div>
    );
}

export default PlaceDetails