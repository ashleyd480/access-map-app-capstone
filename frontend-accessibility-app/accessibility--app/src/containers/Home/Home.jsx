import { useNavigate } from "react-router-dom";
import { useContext } from "react";
import { useEffect } from "react";
import { AuthContext } from "../../contexts/AuthContext";
import { ThemeContext } from "../../contexts/ThemeContext";

const Home = () => {
  const navigate = useNavigate();
  const { userSignedIn, setUserSignedIn } = useContext(AuthContext);
  const {theme, setTheme} = useContext(ThemeContext)

  console.log(userSignedIn);

  useEffect(() => {
    if (!userSignedIn) {
      navigate("/signin");
    }
  }, [userSignedIn]);

  // That is why when you connect to localhost, it will see you are not signed in and serve you the login page. However, it will re-render and display the Home Page at `/` when the value of `userSignedIn` changes to “true.”

  return (
    <div className={`${theme} content` }>
      <h1>Welcome to Access</h1>
      <p> Our mission is crowdsourcing accessiblity information...</p>
      <p> One place at a time...</p>
      <p> Together...</p>
          <p> You can: </p>
          <ul>
              <li> Explore accessible places near you</li>
              <li> Search by place name and city, ranked by accessibility score</li>
              <li> Contribute reviews, ratings, and accessibliy tags </li>

          </ul>
      <img
        src={"src/assets/home-image.png"}
        width="300"
        height="300"
        className="d-inline-block align-top"
        alt="Logo"
      />
    </div>
  );
};

export default Home;

// if the user is not signed in  then we will navigate them to the sign in page
