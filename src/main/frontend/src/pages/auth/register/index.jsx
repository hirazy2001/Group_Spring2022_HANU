import axios, { Axios } from "axios";
import React from "react";
import { Formik, Field, Form } from "formik";
import { toast, ToastContainer } from "react-toastify";
import moment from "moment";
import { useNavigate } from "react-router-dom";

const SiupPage = () => {
  const navigate = useNavigate();
  const handleSignup = async (values) => {
    console.log('values',moment(values.dob).format("DD/MM/YYYY"));
    let newGender = true;
    if(values.gender === "2"){
      newGender = false
    }
    const data = JSON.stringify({
      firstName: values.firstName,
      lastName: values.lastName,
      middleName: values.middleName,
      gender: newGender,
      email: values.email,
      username: values.username,
      password: values.password,
      dob: moment(values.dob).format("DD/MM/YYYY"),
      phoneNumber: values.phoneNumber,
      address: values.address,
      role: ["user"],
    });


    const config = {
      method: "post",
      url: "https://hanu-group-banking-system.herokuapp.com/users/",
      headers: {
        "Content-Type": "application/json",
      },
      data: data,
    };

    await axios(config)
      .then((res) => {
        console.log("res", res);
        navigate("/signin")
        toast.success("Register success!!")
      })
      .catch(({ response }) => {
        console.log("err", response);
        toast.error("Register fail!! " + response.data.error)
      });
  };
  return (
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
            <p className="text-center">Register a new membership</p>
            <Formik
              initialValues={{
                firstName: "",
                lastName: "",
                middleName: "",
                gender: "1",
                email: "",
                username: "",
                password: "",
                dob: "",
                phoneNumber: "",
                address: "",
              }}
              onSubmit={handleSignup}
            >
              {(props) => (
                <Form onSubmit={props.handleSubmit}>
                  <div className="row">
                    <div className="col-6">
                      <div className="form-outline mb-4">
                        <Field
                          id="firstName"
                          type="text"
                          name="firstName"
                          className="form-control"
                          placeholder="First name"
                        />
                      </div>
                    </div>
                    <div className="col-6">
                      <div className="form-outline mb-4">
                        <Field
                          id="lastName"
                          type="text"
                          name="lastName"
                          className="form-control"
                          placeholder="Last name"
                        />
                      </div>
                    </div>
                  </div>

                  <div className="form-outline mb-4">
                    <Field
                      id="middleName"
                      type="text"
                      name="middleName"
                      className="form-control"
                      placeholder="Middle name"
                    />
                  </div>
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
                      id="email"
                      type="email"
                      name="email"
                      className="form-control"
                      placeholder="Email"
                    />
                  </div>
                  <div className="form-outline mb-4">
                    <Field
                      id="address"
                      type="text"
                      name="address"
                      className="form-control"
                      placeholder="Address"
                    />
                  </div>
                  <div className="form-outline mb-4">
                    <Field
                      id="phoneNumber"
                      type="text"
                      name="phoneNumber"
                      className="form-control"
                      placeholder="Phone number"
                    />
                  </div>
                  <div className="form-outline mb-4">
                    <Field
                      id="dob"
                      type="date"
                      name="dob"
                      className="form-control"
                      placeholder="Date of birth"
                    />
                  </div>
                  <div className="form-outline mb-4">
                    <Field
                      id="gender"
                      as="select"
                      name="gender"
                      className="form-control"
                      placeholder="Gender"
                    >
                      <option value="1">Male</option>
                      <option value="2">Female</option>
                    </Field>
                  </div>
                  <div className="form-outline mb-4">
                    <Field
                      id="password"
                      type="password"
                      name="password"
                      className="form-control"
                      placeholder="Password"
                      autoComplete="new-password"
                    />
                  </div>
                  <button
                    type="submit"
                    className="btn btn-primary btn-block m-0 my-1"
                  >
                    Sign Up
                  </button>
                </Form>
              )}
            </Formik>
            <p className="mb-0">
              <a
                href="/signin"
                className="text-center"
                style={{ textDecoration: "none" }}
              >
                I already have a membership
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
  );
};

export default SiupPage;
