import React from 'react'
import { useNavigate } from "react-router-dom";
const UserDashBoardPage = () => {
    const navigate = useNavigate();
  return (
    <div className="welcome-page">
        <h1>Welcome to Online Banking System</h1>
        <div className="button-transaction mt-4">
            <button className="btn btn-secondary btn-lg ms-0" onClick={() => navigate("/user-account")}><i className="fa-solid fa-address-card"></i>&nbsp;Manage Account</button>
            <button className="btn btn-secondary btn-lg mx-4" onClick={() => navigate("/user-transaction")}><i className="fa-solid fa-money-bill"></i>&nbsp;History Account</button>

        </div>
    </div>
  )
}

export default UserDashBoardPage