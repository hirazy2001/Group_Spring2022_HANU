import React from "react";
import { useNavigate } from "react-router-dom";
const UnAuthLayout = () => {
  const navigate = useNavigate();
  return (
    <>
      <button onClick={() => navigate("/signin")}>Sign In</button>
      <button onClick={() => navigate("/signup")}>Sign Up</button>
    </>
  );
};

export default UnAuthLayout;
