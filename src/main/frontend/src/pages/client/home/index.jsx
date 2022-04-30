import React from "react";
import { useNavigate } from "react-router-dom";
const HomePage = () => {
    const navigate = useNavigate();
  return (
    <div className="min-vh-100" style={{backgroundColor:'#0580C5',paddingTop:'50px'}}>
      <div className="container">
        <div className="header-menu">
          <div className="header">
            <ul>
              <li>About</li>
              <li>Affiliates</li>
              <li>FAQ</li>
              <li>Contact Us</li>
              <li>Customer care</li>
              <li>Branches</li>
            </ul>
          </div>
          <img
            width="1284px"
            height="200px"
            src={require("../../../assets/images/bank.png")}
            alt=""
          />
          <div className="content-line">
            <h2 className="content-center">Online banking system</h2>
          </div>
          <div className="btn-center">
            <div className="btn btn-primary btn-lg" onClick={() => navigate("/signup")}>+ New user? register now!!</div>
            <div className="btn btn-primary btn-lg" onClick={() => navigate("/signin")}>
              Already have an account? Sign in!!
            </div>
          </div>
          <div className="body-content">
            <div className="child-content">
              <div className="content">
                <h3>Home loan</h3>
                <p>@9.70%</p>
              </div>
              <button className="btn btn-primary btn-child">Apply now</button>
            </div>

            <div className="child-content">
              <div className="content">
                <h3>Car loan</h3>
                <p>@10.25%</p>
              </div>
              <button className="btn btn-primary btn-child">Apply now</button>
            </div>

            <div className="child-content">
              <div className="content">
                <h3>Savings bank</h3>
                <p>Online</p>
                <p>Account Open</p>
              </div>
              <button className="btn btn-primary btn-child">Apply now</button>
            </div>

            <div className="child-content">
              <div className="content">
                <h3>Personal loan</h3>
                <p>SBI Loan</p>
                <p>Scheme</p>
              </div>
              <button className="btn btn-primary btn-child">Apply now</button>
            </div>
          </div>
          <div className="btn btn-primary btn-service">
            <b>Other Services</b>
          </div>
          <div className="footer">
            <ul>
              <li>
                <a href="">Debit Cards</a>
              </li>
              <li>
                <a href="#">Locate Branch</a>
              </li>
              <li>
                <a href="#">Interest Rates</a>
              </li>
              <li>
                <a href="#">ATM Locator</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div className="footer mt-4">
        <p className="text-center">Banking System - All rights reserved</p>
      </div>
    </div>
  );
};

export default HomePage;
