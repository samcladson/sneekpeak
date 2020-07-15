import React, { useState } from "react";
import { Card, Row, Col } from "antd";
import Login from "./LoginComponent";
import Register from "./RegisterComponent";
import LoginImage from "../../images/login.png";
import RegisterImage from "../../images/Register.png";

const Credential = () => {
  const [LoginVisible, setLoginVisible] = useState(true);

  const toggleView = () => {
    setLoginVisible(!LoginVisible);
  };

  return (
    <div
      style={{
        minHeight: "100vh",
        alignItems: "center",
        justifyContent: "center",
        display: "flex",
        padding: "10px",
      }}
    >
      <Card className="container">
        <Row>
          <Col xs={24} sm={12} md={12}>
            <img
              className="img-fluid m-0"
              src={LoginVisible ? LoginImage : RegisterImage}
              alt=""
            />
          </Col>
          <Col
            xs={24}
            sm={12}
            md={12}
            className="d-flex justify-content-center align-items-center "
          >
            {LoginVisible ? (
              <Login toggle={toggleView} />
            ) : (
              <Register toggle={toggleView} />
            )}
          </Col>
        </Row>
      </Card>
    </div>
  );
};

export default Credential;
