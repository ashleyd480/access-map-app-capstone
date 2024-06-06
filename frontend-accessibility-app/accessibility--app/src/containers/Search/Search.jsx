import SearchBar from "../../components/SearchBar/SearchBar";
import { fetchData } from "../../api/api";
import React, { useState, useEffect } from "react";
import { useContext } from "react";
import { ContributeContext } from "../../contexts/ContributeContext";
import { ThemeContext } from "../../contexts/ThemeContext";



import PlaceResultsList from "../../components/PlacesResultList/PlacesResultList";
const Search = () => {
    const {theme, setTheme} = useContext(ThemeContext)

    const { setShowReviewandTagButton
    } = useContext(ContributeContext);

    setShowReviewandTagButton(false);
         
    const [searchData, setSearchData] = useState({
        placeName: '',
        city: '',
        sortField: 'rating',
        sortDirection: 'descending',
    });
    const [error, setError] = useState("");
    const [searchDynamicSearchResults, setDynamicSearchResults] = useState([]);


    const handleSearch = async () => {
        try {
            const responseData = await fetchData(`places/search?keyword=${searchData.placeName}&city=${searchData.city}&sortField=${searchData.sortField}&sortDirection=${searchData.sortDirection}`);
            if (!responseData.hasError) {
                setDynamicSearchResults(responseData);
            } else {
                setError(
                    responseData.error || "An error occurred while fetching places near you."
                );
            }
        } catch (error) {
            setError("An error occurred while fetching places near you.");
        }
    };
    
        

    
    return (
        <div className={`${theme} content` }>
              <h4>
        You can sort alphabetically or you can sort by average accessiblity
        rating to surface the most relevant accessible spots 😊: 
      </h4>
            <SearchBar searchFunctionProp={handleSearch} searchData={searchData} setSearchData={setSearchData} />
            <div>
                {/* Render PlaceResultsList only when placesNearYouResults is available */}
                {searchDynamicSearchResults.length>0 ? (
                    <PlaceResultsList placeSearchResultsProp={searchDynamicSearchResults} />
                ) : (
                    <p className="errorContainer">{error}</p>

                )}
            </div>
        </div>
    );
}
       
    

export default Search;