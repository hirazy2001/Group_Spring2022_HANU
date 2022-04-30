import React, { useEffect, useState } from "react";
import { Formik, Field, Form } from "formik";
import axios from "axios";
import moment from "moment";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

const ProfilePage = () => {
  const [infoUser, setInfoUser] = useState(null);
  const navigate = useNavigate();

  const getAccessToken = () => {
    let accessToken = localStorage.getItem("ACCESS_TOKEN");
    if (accessToken == null) {
      return "";
    }
    return accessToken;
  };

  const getUser = async () => {
    const id = localStorage.getItem("USER_ID");

    const config = {
      method: "get",
      url: "https://hanu-group-banking-system.herokuapp.com/users/" + id,
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + getAccessToken(),
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "*",
        "Access-Control-Allow-Headers": "*",
        "Access-Control-Allow-Credentials": false,
      },
    };

    await axios(config)
      .then((res) => {
        console.log("res", res.data);
        setInfoUser(res.data);
        console.log('user',infoUser);
      })
      .catch(({ response }) => {
        console.log("err", response);
      });
  };

  useEffect(() => {
    getUser();
    return () => {};
  }, []);

  const handleUpdateInfo = async(values) => {
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
      username: values.username,
      picture: values.picture,
      dob: moment(values.dob).format("DD/MM/YYYY"),
      phoneNumber: values.phoneNumber,
    });


    const config = {
      method: "put",
      url: "https://hanu-group-banking-system.herokuapp.com/users/"+infoUser.id,
      headers: {
        "Content-Type": "application/json",
      },
      data: data,
    };

    await axios(config)
      .then((res) => {
        console.log("res", res);
        toast.success("Update success!!")
      })
      .catch(({ response }) => {
        console.log("err", response);
        toast.error("Update fail!!")
      });
  };
  return (
    <div class="transaction">
      <h2>Profile</h2>
      {infoUser && <Formik
        initialValues={{
          firstName: infoUser.firstName,
          lastName: infoUser.lastName,
          middleName: infoUser.middleName,
          gender: infoUser.gender?"1":"2",
          username: infoUser.username,
          dob: moment(infoUser.dob).format("YYYY-MM-DD"),
          phoneNumber: infoUser.phoneNumber,
          picture: infoUser.picture,
        }}
        onSubmit={handleUpdateInfo}
      >
        {(props) => (
          <Form onSubmit={props.handleSubmit}>
            <div class="row">
              <div class="left col-4">
                <div class="mb-4">
                  <label for="exampleInputName">First name</label>
                  <Field
                    id="firstName"
                    type="text"
                    name="firstName"
                    className="form-control"
                    placeholder="First name"
                  />
                </div>
                <div class="mb-4">
                  <label for="exampleInputAddress">Last name</label>
                  <Field
                    id="lastName"
                    type="text"
                    name="lastName"
                    className="form-control"
                    placeholder="Last name"
                  />
                </div>
                <div class="mb-4">
                  <label for="exampleInputId">Middle name</label>
                  <Field
                    id="middleName"
                    type="text"
                    name="middleName"
                    className="form-control"
                    placeholder="Middle name"
                  />
                </div>
                <button type="submit" class="btn btn-primary btn-lg ms-0  ">
                  Save
                </button>
                <div onClick={()=> navigate("/admin-dashboard")} class="btn btn-secondary btn-lg mx-4">Cancel</div>
              </div>
              <div class="center col-4">
                <i class="fa-solid fa-image-portrait"></i>
              </div>
              <div class="right col-4">
                <div class="mb-4">
                  <label for="exampleInputTransfer">Phone number</label>
                  <Field
                    id="phoneNumber"
                    type="text"
                    name="phoneNumber"
                    className="form-control"
                    placeholder="Phone number"
                  />
                </div>
                <div class="mb-4">
                  <label for="exampleInputAccountNumber">Date of birth</label>
                  <Field
                    id="dob"
                    type="date"
                    name="dob"
                    className="form-control"
                    placeholder="Date of birth"
                  />
                </div>
                <div class="mb-4">
                  <label for="exampleInputDate">Gender</label>
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
              </div>
            </div>
          </Form>
        )}
      </Formik>}
      
    </div>
  );
};

export default ProfilePage;
