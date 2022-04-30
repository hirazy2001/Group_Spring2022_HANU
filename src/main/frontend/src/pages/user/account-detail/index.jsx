import axios, { Axios } from "axios";
import React, { useEffect, useState } from "react";
import { Formik, Field, Form } from "formik";
import { toast, ToastContainer } from "react-toastify";
import moment from "moment";
import {useParams} from 'react-router-dom';
import { useNavigate } from "react-router-dom";

const UserAccountDetail = () => {
    
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
        "pinCode": values.pinCode,
        "from_account": id,
        "to_account": values.to_account != ""? values.to_account: null,
        "amount": values.amount,
        "purpose": values.purpose
    });

    const config = {
      method: "post",
      url: "https://hanu-group-banking-system.herokuapp.com/transactions/" + (values.transaction == undefined ? "transfer": values.transaction),
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + getAccessToken(),
      },
      data: data,
    };

    await axios(config)
      .then((res) => {
        console.log("res", res);
        navigate("/user-account-detail/" + id)
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
      <h2>Create Transaction</h2>

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
                        <label for="exampleInputTransfer">Transaction Type</label>
                        <div className="form-outline mb-4">
                            <Field
                                id="transaction"
                                as="select"
                                name="transaction"
                                className="form-control"
                                placeholder="Transaction Type"
                            >
                            <option value="transfer">Transfer</option>
                            <option value="withdraw">Withdraw</option>
                            <option value="loan">Loan</option>
                            </Field>
                        </div>
                        <div class="mb-4">
                        <label for="exampleInputTransfer">To Account</label>
                            <Field
                                id="to_account"
                                type="text"
                                name="to_account"
                                className="form-control"
                                placeholder="To Account"
                            />
                        </div>

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
                    </div>

                    <div class="center col-4">
                        <i class="fa-solid fa-right-left" align="center"></i>
                    </div>

                    <div class="right col-4">
                        <label for="exampleInputTransfer">Pin code</label>
                        <div className="form-outline mb-4">
                            <Field
                            id="pinCode"
                            type="text"
                            name="pinCode"
                            className="form-control"
                            placeholder="Pin Code(6 numbers)"
                            />
                        </div>

                        <div class="mb-4">
                            <label for="exampleInputTransfer">Amount</label>
                            <Field
                                id="amount"
                                type="text"
                                name="amount"
                                className="form-control"
                                placeholder="Amount"
                            />
                        </div>
                    </div>
                  </div>

                  <button
                    type="submit"
                    className="btn btn-primary btn-block m-0 my-1"
                  >
                    Create Transaction
                  </button>
                </Form>
              )}
            </Formik>
      </div>
    </div>
  );
};

export default UserAccountDetail;
