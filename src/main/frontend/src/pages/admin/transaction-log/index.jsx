import React, { useEffect, useState } from "react";
import { Formik, Field, Form } from "formik";
import axios from "axios";
import {useParams} from 'react-router-dom';
import { useNavigate } from "react-router-dom";
import moment from "moment";

const AdminTransactionLogPage = () => {
    const [infoTransaction, setInfoTransaction] = useState([]);
    const {id} = useParams();
    const navigate = useNavigate();
    console.log('id',id);

    

  const getAccessToken = () => {
    let accessToken = localStorage.getItem("ACCESS_TOKEN");
    if (accessToken == null) {
      return "";
    }
    return accessToken;
  };

  const getTransaction = async () => {
    const config = {
      method: "get",
      url: "https://hanu-group-banking-system.herokuapp.com/transactions/" + id,
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + getAccessToken()
      },
    };

    await axios(config)
      .then((res) => {
        console.log("res1", res.data);
        setInfoTransaction(res.data);
        console.log('fff',);
      })
      .catch(({ response }) => {
        console.log("err", response);
      });
  };

  useEffect(() => {
    getTransaction();
    return () => {};
  }, []);
  return (
    <div class="transaction">
      <h2>Transaction Logs</h2>
      <div class="row">
        <div class="left col-4">
          
            <div class="mb-3">
              <label for="exampleInputName">id</label>
              <input
                type="text"
                class="form-control"
                id="exampleInputName"
                aria-describedby=""
                placeholder="id"
                value={infoTransaction.id}
              />
            </div>
            <div class="mb-3">
              <label for="exampleInputAddress">From acount</label>
              <input
                type="text"
                class="form-control"
                id="exampleInputAddress"
                placeholder="From acount"
                value={infoTransaction.from_account}
              />
            </div>
            <div class="mb-3">
              <label for="exampleInputId">To account</label>
              <input
                type="text"
                class="form-control"
                id="exampleInputId"
                placeholder="To account"
                value={infoTransaction.to_account}
              />
            </div>
            <button class="btn btn-primary ms-0" onClick={()=> navigate("/admin-transaction")}>Back</button>
          
        </div>
        <div class="center col-4">
          <i class="fa-solid fa-right-left" align="center"></i>
        </div>
        <div class="right col-4">
        
            <div class="mb-3">
              <label for="exampleInputTransfer">Amount</label>
              <input
                type="text"
                class="form-control"
                id="exampleInputTransfer"
                aria-describedby=""
                placeholder="Amount"
                value={infoTransaction.amount}
              />
            </div>
            <div class="mb-3">
              <label for="exampleInputAccountNumber">Purpose</label>
              <input
                type="text"
                class="form-control"
                id="exampleInputAccountNumber"
                placeholder="Purpose"
                value={infoTransaction.purpose}
              />
            </div>
            <div class="mb-3">
              <label for="exampleInputDate">CreatedAt</label>
              <input
                type="text"
                class="form-control"
                id="exampleInputDate"
                placeholder="CreatedAt"
                value={moment(infoTransaction.createdAt).format("DD/MM/YYYY")}
              />
            </div>
          
        </div>
      </div>
    </div>
  )
}

export default AdminTransactionLogPage