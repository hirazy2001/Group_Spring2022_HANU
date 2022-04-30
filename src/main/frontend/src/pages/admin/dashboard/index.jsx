import React from 'react'
import { useNavigate } from "react-router-dom";
const DashboardPage = () => {
    const navigate = useNavigate();
  return (
    <div className="welcome-page">
        <h1>Welcome to Online Banking System</h1>
        <div className="button-transaction mt-4">
            <button className="btn btn-secondary btn-lg ms-0" onClick={() => navigate("/admin-customer")}><i className="fa-solid fa-address-card"></i>&nbsp;Manage Customer</button>
            <button className="btn btn-secondary btn-lg mx-4" onClick={() => navigate("/admin-transaction")}><i className="fa-solid fa-money-bill"></i>&nbsp;Banking Transaction</button>

        </div>
    </div>
  )
}

export default DashboardPage