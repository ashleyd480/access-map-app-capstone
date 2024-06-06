import { useLocation } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import { AuthContext } from '../../contexts/AuthContext';
import { useNavigate } from "react-router-dom";
import { useContext } from "react";
import { ThemeContext } from "../../contexts/ThemeContext";


// success message passed with use Location hook 

const MyAccountDeleteDone = () => {
  const {theme, setTheme} = useContext(ThemeContext)

    const location = useLocation();
    const { message } = location.state;
    const { userSignedIn, setUserSignedIn } = useContext(AuthContext);

    const navigate = useNavigate();

   
    const handleExit = () => {
        setUserSignedIn(false)
        navigate('/signin');
    
    }
        

  return (
    <div className={`${theme} content` }>
    <h1>{message}</h1>
      <p> We are sorry to see you go</p>
      
      <div className = "buttonGroup">
        <Button onClick={handleExit}> Exit the Page </Button>
        </div>
    </div>
     
    );
}

export default MyAccountDeleteDone;