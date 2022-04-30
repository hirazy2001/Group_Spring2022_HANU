import React from "react";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { Menu, Dropdown, Button, message, Space, Tooltip } from "antd";
import {
  LogoutOutlined,
  AuditOutlined,
  UserOutlined,
  EditOutlined,
} from "@ant-design/icons";

const UserLayout = ({ children }) => {
  const navigate = useNavigate();
  const openNav = () => {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("big").style.marginLeft = "250px";
  };

  const closeNav = () => {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("big").style.marginLeft = "0";
  };
  const logout = () => {
    localStorage.clear();
    navigate("/signin");
    toast("Logout successfull");
  };

  return (
    <div className="vh-100 min-vh-100">
      <div id="mySidenav" className="sidenav">
        <div
          className="closebtn"
          style={{ cursor: "pointer", color: "#fff" }}
          onClick={() => closeNav()}
        >
          ×
        </div>
        <a href="#">
          <i className="fa-solid fa-house-chimney" /> &nbsp;Online Banking
        </a>
        <a href="#" onClick={() => navigate("/user-dashboard")}>
          <i className="fa-solid fa-table-columns" />
          &nbsp; Dashboard
        </a>
        <a href="#" onClick={() => navigate("/user-account")}>
          <i className="fa-solid fa-id-card" /> &nbsp;Manage Account
        </a>
        <a href="#" onClick={() => navigate("/user-transaction")}>
          <i className="fa-solid fa-right-left" /> &nbsp; History Transaction
        </a>
      </div>
      <div id="big">
        <div id="main">
          <span
            style={{
              marginLeft: "30px",
              fontSize: "30px",
              cursor: "pointer",
            }}
            onClick={() => openNav()}
          >
            ☰ Online banking system - Transactions Information
          </span>
          <div class="dropdown">
            <button class="dropbtn">
              <i class="fa-solid fa-user"></i>&nbsp;
              <i class="fa-solid fa-caret-down"></i>
            </button>
            <div class="dropdown-content">
              <a href="#" onClick={() => logout()}>
                <i class="fa-solid fa-arrow-right-from-bracket"></i>
                &nbsp;Logout
              </a>
              <a href="#">
                <i class="fa-solid fa-id-card-clip"></i>&nbsp;View details
              </a>
              <a href="#" onClick={() => navigate("/user-profile")}>
                <i class="fa-solid fa-pen-to-square"></i>&nbsp;Edit
              </a>
            </div>
          </div>
        </div>
        {children}
      </div>
    </div>
  );
};

export default UserLayout;
