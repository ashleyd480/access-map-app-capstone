import "./App.css";
import { Route, Routes } from "react-router-dom";
import { useState } from "react";
import Header from "./components/Header/Header";
import SignIn from "./containers/SignIn/SignIn";
import SignUp from "./containers/SignUp/SignUp";
import SignUpSuccess from "./containers/SignUp/SignUpSuccess";
import Home from "./containers/Home/Home";
import Explore from "./containers/Explore/Explore";
import Profile from "./containers/Profile/Profile";
import Contribute from "./containers/Contribute/Contribute";
import AddReview from "./containers/Contribute/AddReview";
import AddTag from "./containers/Contribute/AddTag";
import Search from "./containers/Search/Search";
import PlaceDetails from "./containers/PlaceDetails/PlaceDetails";
import Error from "./containers/Error/Error";
import Footer from "./containers/Footer/Footer";
import MyAccount from "./containers/My Account/MyAccount";
import MyAccountEdit from "./containers/My Account/MyAccountEdit";
import MyAccountDelete from "./containers/My Account/MyAccountDelete";
import MyAccountDeleteDone from "./containers/My Account/MyAccountDeleteDone";
import { ThemeContext } from "./contexts/ThemeContext";
import { AuthContext } from "./contexts/AuthContext";
import { UsernameContext } from "./contexts/UsernameContext";
import { UserDataContext } from "./contexts/UserDataContext";
import { ContributeContext } from "./contexts/ContributeContext";


const App = () => {
  const [userSignedIn, setUserSignedIn] = useState(false);
  const [username, setUsername] = useState("");
  const [theme, setTheme] = useState("light");
  const [userData, setUserData] = useState({});
  const [showReviewandTagButton, setShowReviewandTagButton] = useState(false)

  return (
    <ThemeContext.Provider value={{ theme, setTheme }}>
      <AuthContext.Provider value={{ userSignedIn, setUserSignedIn }}>
        <UsernameContext.Provider value={{ username, setUsername }}>
          <UserDataContext.Provider value={{ userData, setUserData }}>
          <ContributeContext.Provider value={{ showReviewandTagButton, setShowReviewandTagButton}}>
            {userSignedIn && (<> <Header/>  <Footer/> </>)}
            <Routes>
              <>
                <Route path="/signin" element={<SignIn />} />
                <Route path="/signup" element={<SignUp />} />
                <Route path="/signupsuccess" element={<SignUpSuccess />} />
                <Route exact path="/" element={<Home />} />
                <Route path="/explore" element={<Explore />} />
                <Route
                
                  path="/places/:placeId"
                  element={<PlaceDetails />}
                />

                <Route path="/contribute" element={<Contribute />} />
                <Route
                  path="/places/:id/addreview"
                  element={<AddReview />} 
                  />
                   <Route
                  path="/places/:id/addtag"
                  element={<AddTag />} 
            />
                <Route path="/search" element={<Search />} />
                <Route path="/profile" element={<Profile />} />

                <Route path="/myaccount" element={<MyAccount />} />
                <Route path="/myaccount/edit" element={<MyAccountEdit />} />
                <Route path="/myaccount/delete" element={<MyAccountDelete />} />
                <Route path="/myaccount/delete-done" element={<MyAccountDeleteDone />} />

                <Route path="*" element={<Error />} />
              </>
              </Routes>
              </ContributeContext.Provider>
          </UserDataContext.Provider>
        </UsernameContext.Provider>
      </AuthContext.Provider>
    </ThemeContext.Provider>
  );
};

export default App;

// exact path="/": Ensures only the root path / matches, avoiding conflicts with other routes.
