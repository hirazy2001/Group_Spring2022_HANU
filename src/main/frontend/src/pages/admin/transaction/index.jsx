import axios from "axios";
import React, { useEffect, useState } from "react";
import { Table, Tag, Row, Col, Button } from "antd";
import { EyeOutlined } from "@ant-design/icons";
import moment from "moment";
import { useNavigate } from "react-router-dom";
const AdminTransactionPage = () => {
    const [listTransaction, setListTransaction] = useState([]);
    const navigate = useNavigate();
  const listTransactionColumn = [
    {
      title: "Id",
      dataIndex: "id",
      key: "index",
      width: "10%",
      render: (text, record, index) => index + 1,
    },
    {
      title: "From account",
      dataIndex: "from_account",
      key: "id",
      with: "50%",
      render: (text) => <>{text}</>,
    },
    {
      title: "To account",
      dataIndex: "to_account",
      key: "id",
      with: "50%",
      render: (text) => <>{text}</>,
    },
    {
      title: "Amount",
      dataIndex: "amount",
      key: "id",
      with: "50%",
      render: (text) => <>{text}</>,
    },
    {
      title: "CreatedAt",
      dataIndex: "createdAt",
      key: "id",
      with: "50%",
      render: (text) => <>{moment(text).format("DD/MM/YYYY")}</>,
    },
    {
      title: "Purpose",
      dataIndex: "purpose",
      key: "id",
      with: "50%",
      render: (text) => <>{text}</>,
    },
    {
      title: "Type",
      dataIndex: "type",
      key: "id",
      with: "50%",
      render: (type) => (
        <>
          {type === "TRANSFER" ? (
            <Tag style={{ width: 150, textAlign: "center" }} color="green">
              {type}
            </Tag>
          ) : type === "WITHDRAW" ? (
            <Tag style={{ width: 150, textAlign: "center" }} color="red">
              {type}
            </Tag>
          ): type === "SAVING" ?
            <Tag style={{ width: 150, textAlign: "center" }} color="yellow">
              {type}
            </Tag>  
            :
            <Tag style={{ width: 150, textAlign: "center" }} color="blue">
              {type}
            </Tag>  
          }
        </>
      ),
    },
    {
      title: "Action",
      key: "id",
      with: "50%",
      render: (values, records, index) => (
        <>
          <Row>
            <Col>
              <Button
                style={{backgroundColor:'#f5bb47'}}
                onClick={() => {
                    navigate(`/admin-transaction-log/${records.id}`);
                }}
              >
                <EyeOutlined />
              </Button>
            </Col>

          </Row>
        </>
      ),
    },
  ];
  const getAccessToken = () => {
    let accessToken = localStorage.getItem("ACCESS_TOKEN");
    if (accessToken == null) {
      return "";
    }
    return accessToken;
  };
  const fetchTransaction = async () => {
    const config = {
      method: "get",
      url: "https://hanu-group-banking-system.herokuapp.com/transactions/",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + getAccessToken(),
      },
    };

    await axios(config)
      .then((res) => {
        console.log("res", res.data);
        setListTransaction(res.data);
      })
      .catch(({ response }) => {
        console.log("err", response);
      });
  };
  useEffect(() => {
    fetchTransaction();
    return () => {};
  }, []);
  return (
    <div className="px-5 pt-2">
      <Table
        columns={listTransactionColumn}
        dataSource={listTransaction}
        pagination={{
          defaultPageSize: 8,
          showSizeChanger: true,
          pageSizeOptions: ["8", "16", "24"],
        }}
      />
    </div>
  )
}

export default AdminTransactionPage