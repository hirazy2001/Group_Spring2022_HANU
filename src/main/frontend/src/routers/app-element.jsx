// import { ReactNode, useEffect } from "react";
import { Route, useNavigate, Navigate } from "react-router-dom";
// import { ROLE_ADMIN, ROLE_CASHIER, ROLE_RECEPTIONIST } from "../constants/role";
// import useSelector from "../hooks/use_selector";
// import LoginPage from "../pages/login/login_page";
import NotFoundLayout from "../layouts/NotFoundLayout";

const AppElement = (props) => {
  const {
    component: Component,
    layout: Layout,
    isLayout = false,
    authen,
    path,
  } = props;
  console.log("props", props);

  const access_token = localStorage.getItem("ACCESS_TOKEN");
  const role = localStorage.getItem("USER_ROLE")

//   sessionStorage.setItem("PATH", path);

//   if (!access_token && authen) {
//     return <FoundLayout />;
//   }
  if (access_token && !authen && role?.includes("ROLE_ADMIN")) {
    return <Navigate to="/admin-dashboard" />;
  }

  if (access_token && !authen && role?.includes("ROLE_USER")) {
    return <Navigate to="/user-dashboard" />;
  }
//   if (access_token && !authen && (role as string[])?.includes(ROLE_RECEPTIONIST)) {
//     return <Navigate to="/admin-ticket" />;
//   }
//   if (access_token && !authen && (role as string[])?.includes(ROLE_CASHIER)) {
//     return <Navigate to="/admin-statistical" />;
//   }
  // if (access_token && authen) {
  //   return <Navigate to="/" />;
  // }
  return isLayout && Layout ? (
    <Layout>
      <Component />
    </Layout>
  ) : (
    <Component />
  );
};
export default AppElement;
