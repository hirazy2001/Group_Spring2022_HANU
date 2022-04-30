import axios from "axios";
import React, { useEffect, useState } from "react";
import { Table, Tag, Row, Col, Button } from "antd";
import { EyeOutlined } from "@ant-design/icons";
import moment from "moment";
import { useNavigate } from "react-router-dom";
const AdminAccountPage = () => {
    const [listAccount, setListAccount] = useState([]);
    const navigate = useNavigate();
  const listAccountColumn = [
    {
      title: "Id",
      dataIndex: "id",
      key: "index",
      width: "10%",
      render: (text, record, index) => index + 1,
    },
    {
      title: "User ID",
      dataIndex: "uId",
      key: "id",
      with: "50%",
      render: (text) => <>{text}</>,
    },
    {
      title: "Balance",
      dataIndex: "balance",
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
      title: "Type",
      dataIndex: "accountType",
      key: "id",
      with: "50%",
      render: (type) => (
        <>
          {type === "PRIMARY" ? (
            <Tag style={{ width: 150, textAlign: "center" }} color="green">
              {type}
            </Tag>
          ) :  type === "SAVING" ? (
            <Tag style={{ width: 150, textAlign: "center" }} color="red">
              {type}
            </Tag>
          ): 
          (
            <Tag style={{ width: 150, textAlign: "center" }} color="yellow">
              {type}
            </Tag>
          )}
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
                    navigate(`/admin-account-detail/${records.id}`);
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
  const fetchAccount = async () => {
    const config = {
      method: "get",
      url: "https://hanu-group-banking-system.herokuapp.com/accounts/",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + getAccessToken(),
      },
    };

    await axios(config)
      .then((res) => {
        console.log("res", res.data);
        setListAccount(res.data);
      })
      .catch(({ response }) => {
        console.log("err", response);
      });
  };
  useEffect(() => {
    fetchAccount();
    return () => {};
  }, []);
  return (
    <div className="px-5 pt-2">
      <Table
        columns={listAccountColumn}
        dataSource={listAccount}
        pagination={{
          defaultPageSize: 8,
          showSizeChanger: true,
          pageSizeOptions: ["8", "16", "24"],
        }}
      />
    </div>
  )
}

export default AdminAccountPage