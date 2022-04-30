import axios, { Axios } from "axios";
import React from "react";
import { Formik, Field, Form } from "formik";
import { toast, ToastContainer } from "react-toastify";
import moment from "moment";
import { useNavigate } from "react-router-dom";

const UserAccountCreate = () => {
  const navigate = useNavigate();
  const handleCreateAccount = async (values) => {
    let newGender = true;
    // if(values.gender === "2"){
    //   newGender = false
    // }
    const data = JSON.stringify({
        "userID": getUserId(),
        "accountStatus": "hehe",
        "accountType": values.accountType,
        "pinCode": values.pinCode
    });

    console.log(values.accountType + " " + values.pinCode)

    const config = {
      method: "post",
      url: "https://hanu-group-banking-system.herokuapp.com/accounts/",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + getAccessToken(),
      },
      data: data,
    };

    await axios(config)
      .then((res) => {
        console.log("res", res);
        navigate("/user-account")
        toast.success("Register success!!")
      })
      .catch(({ response }) => {
        console.log("err", response);
        toast.error("Register fail!! " + response.data.error)
      });
  };

  const getAccessToken = () => {
    let accessToken = localStorage.getItem("ACCESS_TOKEN");
    if (accessToken == null) {
      return "";
    }
    return accessToken;
  };

  const getUserId = () => {
    let userID = localStorage.getItem("USER_ID");
    if (userID == null) {
      return "";
    }
    console.log("User ID " + userID)
    return userID;
  }

  return (
    <div className="min-vh-100 vh-100">
      <div className="h-100 d-flex justify-content-center align-items-center flex-column">
       
        {/* /.login-logo */}
        <div className="card shadow w-25">
          <div className="card-body">
            <h3 className="text-center">Create Account</h3>
            <Formik
              initialValues={{
                accountType: "PRIMARY",
                pinCode: ""
              }}
              onSubmit={handleCreateAccount}
            >
              {(props) => (
                <Form onSubmit={props.handleSubmit}>
                  <div className="row">
                    <div className="form-outline mb-4">
                        <Field
                            id="accountType"
                            as="select"
                            name="accountType"
                            className="form-control"
                            placeholder="Account Type"
                        >
                        <option value="PRIMARY">Primary</option>
                        <option value="SAVING">Saving</option>
                        <option value="LOAN">Loan</option>
                        </Field>
                    </div>
                      <div className="form-outline mb-4">
                        <Field
                          id="pinCode"
                          type="text"
                          name="pinCode"
                          className="form-control"
                          placeholder="Pin Code(6 numbers)"
                        />
                    </div>
                  </div>

                  <button
                    type="submit"
                    className="btn btn-primary btn-block m-0 my-1"
                  >
                    Create Account
                  </button>
                </Form>
              )}
            </Formik>
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

export default UserAccountCreate;
