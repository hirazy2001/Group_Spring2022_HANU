import axios from "axios";
import React, { useEffect, useState } from "react";
import { Table, Tag } from "antd";

const ManagerCustomerPage = () => {
  const [listMember, setListMember] = useState([]);

  const listMemberColumn = [
    {
      title: "Id",
      dataIndex: "index",
      key: "index",
      width: "10%",
      render: (text, record, index) => index + 1,
    },
    {
      title: "First name",
      dataIndex: "firstName",
      key: "id",
      with: "50%",
      render: (text) => <>{text}</>,
    },
    {
      title: "Last name",
      dataIndex: "lastName",
      key: "id",
      with: "50%",
      render: (text) => <>{text}</>,
    },
    {
      title: "Middle name",
      dataIndex: "middleName",
      key: "id",
      with: "50%",
      render: (text) => <>{text}</>,
    },
    {
      title: "Username",
      dataIndex: "username",
      key: "id",
      with: "50%",
      render: (text) => <>{text}</>,
    },
    {
      title: "Phone number",
      dataIndex: "phoneNumber",
      key: "id",
      with: "50%",
      render: (text) => <>{text}</>,
    },
    {
      title: "Gender",
      dataIndex: "gender",
      key: "id",
      with: "50%",
      render: (gender) => (
        <>
          {gender ? (
            <Tag style={{ width: 150, textAlign: "center" }} color="green">
              Male
            </Tag>
          ) : (
            <Tag style={{ width: 150, textAlign: "center" }} color="red">
              Female
            </Tag>
          )}
        </>
      ),
    },
    // {
    //   title: "Action",
    //   key: "id",
    //   with: "50%",
    //   render: (values, records, index) => (
    //     <>
    //       <Row>
    //         {roles.includes(ROLE_RECEPTIONIST)?!records.isRead?<Col>
    //           <Button
    //             className="warning-button"
    //             onClick={() => {
    //               setReadCheckRoom(records);
    //             }}
    //           >
    //             <EyeOutlined />
    //           </Button>
    //         </Col>:<Col>
    //           <Button
    //             className="success-button"
    //             onClick={() => {
    //               setSelectedCheckRoom(records);
    //               setVisibleCheckRoomModal(true);
    //             }}
    //           >
    //             <CheckCircleOutlined />
    //           </Button>
    //         </Col>:<Col>
    //           <Button
    //             className="warning-button"
    //             onClick={() => {
    //               setSelectedCheckRoom(records);
    //               setVisibleCheckRoomModal(true);
    //             }}
    //           >
    //             <EditFilled />
    //           </Button>
    //         </Col>}

    //       </Row>
    //     </>
    //   ),
    // },
  ];

  const getAccessToken = () => {
    let accessToken = localStorage.getItem("ACCESS_TOKEN");
    if (accessToken == null) {
      return "";
    }
    return accessToken;
  };
  const fetchMember = async () => {
    const config = {
      method: "get",
      url: "https://hanu-group-banking-system.herokuapp.com/users/",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + getAccessToken(),
      },
    };

    await axios(config)
      .then((res) => {
        console.log("res", res.data);
        setListMember(res.data);
      })
      .catch(({ response }) => {
        console.log("err", response);
      });
  };
  useEffect(() => {
    fetchMember();
    return () => {};
  }, []);
  return (
    <div className="px-5 pt-2">
      <Table
        columns={listMemberColumn}
        dataSource={listMember}
        pagination={{
          defaultPageSize: 8,
          showSizeChanger: true,
          pageSizeOptions: ["8", "16", "24"],
        }}
      />
    </div>
  );
};

export default ManagerCustomerPage;
