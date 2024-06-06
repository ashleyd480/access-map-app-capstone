import SearchBar from "../../components/SearchBar/SearchBar";
import { fetchData } from "../../api/api";
import { useContext } from "react";

import React, { useState, useEffect } from "react";

import PlaceResultsList from "../../components/PlacesResultList/PlacesResultList";
import { ContributeContext } from "../../contexts/ContributeContext";
import { ThemeContext } from "../../contexts/ThemeContext";

const Contribute = () => {
    const {theme, setTheme} = useContext(ThemeContext)

    const { setShowReviewandTagButton
    } = useContext(ContributeContext);

    setShowReviewandTagButton(true);

    const [searchData, setSearchData] = useState({
        placeName: '',
        city: '',
        sortField: 'rating',
        sortDirection: 'descending',
    });
    const [error, setError] = useState("");
    const [searchDynamicSearchResults, setDynamicSearchResults] = useState([]);
  

    console.log(searchDynamicSearchResults)
    console.log(searchData)

    const handleSearch = async () => {
        try {
            const responseData = await fetchData(`places/search?keyword=${searchData.placeName}&city=${searchData.city}&sortField=${searchData.sortField}&sortDirection=${searchData.sortDirection}`);
            if (!responseData.hasError) {
                console.log(searchData)
                setDynamicSearchResults(responseData);
            } else {
                setError(
                    responseData.error || "An error occurred while fetching places near you."
        
                );
                console.log(responseData.error)
                console.log(error)
            }
        } catch (error) {
            setError("An error occurred while fetching places near you.");
        }
    };
    
        

    // we lifted up the state of search bar input and this way we can pass down info from user input to search bar

    return (
        <div className={`${theme} content` }>
              <h4>
        Search for places to contribute to 😊: 
            </h4>
            <hr></hr>
            <h5>You can click on each result that will take you to a page with button to add tags or add review.</h5>

            <SearchBar searchFunctionProp={handleSearch} searchData={searchData} setSearchData={setSearchData} />
            <div>
                {/* Render PlaceResultsList only when search Result is not empty */}
                {searchDynamicSearchResults.length>0 ? (
                    <PlaceResultsList placeSearchResultsProp={searchDynamicSearchResults} />
                ) : (
                    <p className="errorContainer">{error}</p>
                )}
            </div>
        </div>
    );
}

export default Contribute;