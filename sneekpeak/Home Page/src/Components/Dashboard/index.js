import React, { useEffect, useState } from "react";
import { Row, Col, Card, Table, Empty } from "antd";
import { EditOutlined } from "@ant-design/icons";
import axios from "axios";

const { Meta } = Card;
const userData = JSON.parse(localStorage.getItem("userData"));

const Dashboard = () => {
  const [Orders, setOrders] = useState([]);
  const [dataSource, setDataSource] = useState([]);

  useEffect(() => {
    if (userData) {
      axios
        .get(`http://localhost:8080/myOrders/${userData.userId}`)
        .then((res) => {
          if (res.status === 200) {
            setOrders(res.data);
          } else {
            console.log("err");
          }
        });
    }
  }, [userData]);

  useEffect(() => {
    if (Orders.length) {
      let dataSourceArray = [];
      Orders.map((order, i) => {
        dataSourceArray.push({
          key: i,
          orderId: order.orderId,
          productName: order.productName,
          quantity: order.orderQuantity,
          price: order.productPrice,
          status:order.status
        });
      });
      setDataSource(dataSourceArray);
    }
  }, [Orders]);

  const columns = [
    {
      title: "Order Id",
      key: "orderId",
      dataIndex: "orderId",
    },
    {
      title: "Product Name",
      key: "productName",
      dataIndex: "productName",
    },
    {
      title: "Quantity",
      key: "quantity",
      dataIndex: "quantity",
    },
    {
      title: "Price",
      key: "price",
      dataIndex: "price",
    },
    {
      title: "Status",
      key: "status",
      dataIndex: "status",
    },
  ];

  return (
    <div>
      {userData ? (
        <Row className="container-fluid p-5" gutter={[12, 12]}>
          <Col md={6} sm={24}>
            <Card
              title="Profile"
              hoverable
              actions={[<EditOutlined />]}
              style={{ width: "100%" }}
            >
              <h4>{userData.username}</h4>
              <p>{userData.email}</p>
              <p>{userData.phone}</p>
            </Card>
          </Col>
          <Col md={18} sm={24}>
            <Card title="Your Order" hoverable style={{ width: "100%" }}>
              <Table columns={columns} dataSource={dataSource} />
            </Card>
          </Col>
        </Row>
      ) : (
        <EmptyComponent />
      )}
    </div>
  );
};

const EmptyComponent = () => {
  return (
    <Row className="container-fluid p-5" gutter={[12, 12]}>
      <Col md={6} sm={24}>
        <Card
          title="Profile"
          hoverable
          actions={[<EditOutlined />]}
          style={{ width: "100%" }}
        >
          <Empty />
        </Card>
      </Col>
      <Col md={18} sm={24}>
        <Card title="Your Order" hoverable style={{ width: "100%" }}>
          <Empty />
        </Card>
      </Col>
    </Row>
  );
};

export default Dashboard;
