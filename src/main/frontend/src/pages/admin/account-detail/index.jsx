import axios, { Axios } from "axios";
import React, { useEffect, useState } from "react";
import { Formik, Field, Form } from "formik";
import { toast, ToastContainer } from "react-toastify";
import moment from "moment";
import {useParams} from 'react-router-dom';
import { useNavigate } from "react-router-dom";

const AdminAccountDetail = () => {
    
  const navigate = useNavigate();
  const [infoAccount, setInfoAccount] = useState([]);
  const {id} = useParams();

  console.log("ID " + id)

  const getAccountDetail = async () => {

    const config = {
      method: "get",
      url: "https://hanu-group-banking-system.herokuapp.com/accounts/" + id,
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + getAccessToken()
      },
    };

    await axios(config)
      .then((res) => {
        console.log("res1", res.data);
        setInfoAccount(res.data);
        console.log('fff',);
      })
      .catch(({ response }) => {
        console.log("err", response);
      });
  };

  const handleCreateTransaction = async (values) => {
    // if(values.gender === "2"){
    //   newGender = false
    // }
    const data = JSON.stringify({
        "pinCode": "235478",
        "from_account": 2,
        "to_account": id,
        "amount": values.amount,
        "purpose": values.purpose
    });

    const config = {
      method: "post",
      url: "https://hanu-group-banking-system.herokuapp.com/transactions/transfer",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + getAccessToken(),
      },
      data: data,
    };

    await axios(config)
      .then((res) => {
        console.log("res", res);
        navigate("/admin-account-detail/" + id)
        toast.success("Create Transaction success!!")
      })
      .catch(({ response }) => {
        console.log("err", response);
        toast.error("Create Transaction fail!! " + response.data.error)
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

  useEffect(() => {
    getAccountDetail();
    return () => {};
  }, []);
  return (
    <div class="transaction">
      <h2>Account Detail</h2>
      <div class="row">
        <div class="left col-4">
          
            <div class="mb-3">
              <label for="exampleInputName">ID</label>
              <input
                disabled
                type="text"
                class="form-control"
                id="exampleInputName"
                aria-describedby=""
                placeholder="id"
                value={infoAccount.id}
              />
            </div>

            <div class="mb-3">
              <label for="exampleInputName">Balance: </label>
              <input
                disabled
                type="text"
                class="form-control"
                id="exampleInputName"
                aria-describedby=""
                placeholder="id"
                value={infoAccount.balance}
              />
            </div>

            {/* <button class="btn btn-primary ms-0" onClick={()=> navigate("/admin-transaction")}>Back</button> */}
          
        </div>
        <div class="right col-4">
            <div class="mb-3">
              <label for="exampleInputDate">CreatedAt</label>
              <input
                disabled
                type="text"
                class="form-control"
                id="exampleInputDate"
                placeholder="CreatedAt"
                value={moment(infoAccount.createdAt).format("DD/MM/YYYY")}
              />
            </div>

            <div class="mb-3">
              <label for="exampleInputDate">Account Type</label>
              <input
                disabled
                type="text"
                class="form-control"
                id="exampleInputDate"
                placeholder="CreatedAt"
                value={infoAccount.accountType}
              />
            </div>
        </div>

        
      </div>

      <div class="row" >
      <h2>Create Transfer Transaction</h2>

        <Formik
              initialValues={{
                accountType: "PRIMARY",
                pinCode: ""
              }}
              onSubmit={handleCreateTransaction}
            >
              {(props) => (

                <Form onSubmit={props.handleSubmit}>
                  <div className="row">
                  <div class="left col-4">
                  <div class="mb-4">
                        <label for="exampleInputTransfer">Purpose</label>

                            <Field
                                id="purpose"
                                type="text"
                                name="purpose"
                                className="form-control"
                                placeholder="Purpose"
                            />
                        </div>

                        <div class="mb-4">
                        <label for="exampleInputTransfer">Change Balance</label>

                            <Field
                                id="amount"
                                type="text"
                                name="amount"
                                className="form-control"
                                placeholder="Balance"
                            />
                        </div>
                    </div>

                    <div class="center col-4">
                    <i class="fa-solid fa-dollar-sign" align="center"></i>
                    </div>
                  </div>

                  <button
                    type="submit"
                    className="btn btn-primary btn-block m-0 my-1"
                  >
                    Change Balance
                  </button>
                </Form>
              )}
            </Formik>
      </div>
    </div>
  );
};

export default AdminAccountDetail;
