import React from "react";
import "./../styles/Home.css";
import googleIcon from "./google-icon.png";

const googleIconURL =
  "https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/512px-Google_%22G%22_Logo.svg.png";

const Home = () => {
  const googleLogin = () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/google";
  };

  const githubLogin = () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/github";
  };

  return (
    <div className="home-container">
      <h2>Welcome to NoteSeva</h2>
      <button className="login-button google-button" onClick={googleLogin}>
    <img src={googleIcon} alt="Google Icon" className="button-icon" />
    Login with Google
</button>
      <button className="login-button github-button" onClick={githubLogin}>
        Login with GitHub
      </button>
    </div>
  );
};

export default Home;
