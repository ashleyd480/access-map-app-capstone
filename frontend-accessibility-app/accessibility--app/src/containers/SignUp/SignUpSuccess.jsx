import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const SignUpSuccess = () => {
    const navigate = useNavigate();

    const handleReturnToLogin = () => {
        navigate ("/")
    }
  return (
    <div className= "sign-up">
      <h2> Thank you for signing up. </h2>
      <p> Click the button go back to sign in. </p>

      <Button onClick={handleReturnToLogin} className= "buttonGroup">Return to Login</Button>
    </div>
  );
};

export default SignUpSuccess;
