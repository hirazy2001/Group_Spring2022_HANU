import axios from "axios";
import React from "react";
import { Formik, Field, Form } from "formik";
import { toast, ToastContainer } from "react-toastify";
import { useNavigate } from "react-router-dom";
const LoginPage = () => {
  const navigate = useNavigate();
  const handleLogin = async (values) => {
    const data = JSON.stringify({
      username: values.username,
      password: values.password,
    });
    const config = {
      method: "post",
      url: "https://hanu-group-banking-system.herokuapp.com/auth/",
      headers: {
        "Content-Type": "application/json",
      },
      data: data,
    };

    axios(config)
      .then((res) => {
        console.log("res", res.data);
        localStorage.setItem("ACCESS_TOKEN", res.data.accessToken);
        toast.success("Login success!");
        localStorage.setItem("USER_ROLE", JSON.stringify(res.data.roles));
        localStorage.setItem("USER_ID", res.data.id);
        navigate(getUrl(res.data.roles));
      })
      .catch(({ response }) => {
        console.log("err", response);
        toast.error("Login fail! " + response.data.error)
      });
  };

  const getUrl = (role) => {
    console.log("role", role);

    switch (role[0]) {
      case "ROLE_USER":
        return "/user-dashboard";
      case "ROLE_ADMIN":
        return "/admin-dashboard";
      default:
        break;
    }
  };

  return (
    <>
      <ToastContainer />
      <div className="min-vh-100 vh-100">
        <div className="h-100 d-flex justify-content-center align-items-center flex-column">
          <div className="login-logo">
            <div className="mb-3">
              <b className="fs-2 text-muted" style={{ fontWeight: "500" }}>
                Banking System
              </b>
            </div>
          </div>
          {/* /.login-logo */}
          <div className="card shadow w-25">
            <div className="card-body">
              <p className="text-center">Sign in to start your session</p>
              <Formik
                initialValues={{
                  username: "",
                  password: "",
                }}
                onSubmit={handleLogin}
              >
                {(props) => (
                  <Form onSubmit={props.handleSubmit}>
                    <div className="form-outline mb-4">
                      <Field
                        id="username"
                        type="text"
                        name="username"
                        className="form-control"
                        placeholder="Username"
                      />
                    </div>
                    <div className="form-outline mb-4">
                      <Field
                        id="password"
                        type="password"
                        name="password"
                        className="form-control"
                        placeholder="Password"
                      />
                    </div>
                    <button
                      type="submit"
                      className="btn btn-primary btn-block mx-0"
                    >
                      Sign In
                    </button>

                    {/* <p className="mb-0">
              <a
                href="/signup"
                className="text-center"
                style={{ textDecoration: "none" }}
              >
                Register a new membership
              </a>
            </p> */}
                  </Form>
                )}
              </Formik>
              <p className="mb-0">
                <a
                  href="/signup"
                  className="text-center"
                  style={{ textDecoration: "none" }}
                >
                  Register a new membership
                </a>
              </p>
            </div>
            {/* /.login-card-body */}
          </div>
        </div>
        {/* /.login-box */}
        {/* jQuery */}
        {/* Bootstrap 4 */}
        {/* AdminLTE App */}
      </div>
    </>
  );
};

export default LoginPage;
