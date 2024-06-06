import { Link } from "react-router-dom";
import { useContext } from "react";

// we need to import Link (or whatever component we call)
import "./PlacesResultList.css";
import { ThemeContext } from "../../contexts/ThemeContext";

const PlaceResultsList = ({ placeSearchResultsProp }) => {
  const { theme, setTheme } = useContext(ThemeContext);

  return (
      <>
                <ul className={`${theme} place-list` }>

      {/* <ul className="place-list"> */}
        {placeSearchResultsProp.map((place, index) => (
          <li key={index} className="place-row">
            <Link to={`/places/${place.placeId}`}>
              <h3>{place.placeName}</h3>
            </Link>
            <p>
              {place.streetAddress}, {place.city}, {place.state} {place.zipCode}
            </p>
            <p>
              <strong> Category: </strong> {place.category}
            </p>
            <p>
              <strong> Average Rating: </strong> {place.averageRating}
            </p>

            {place.tags && (
              <>
                <h6>
                  {" "}
                  <strong> Feature Tags </strong>{" "}
                </h6>
                <ul>
                  {place.tags.map((tag, tagIndex) => (
                    <li key={tagIndex}>{tag.tagName} </li>
                  ))}
                </ul>
              </>
            )}
          </li>
        ))}
      </ul>
    </>
  );
};
export default PlaceResultsList;
