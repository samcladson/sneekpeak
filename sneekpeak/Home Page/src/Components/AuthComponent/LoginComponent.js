import React from "react";
import axios from "axios";
import { Card, message } from "antd";
import { Form, Input, Button } from "antd";

const LoginComponent = ({ toggle }) => {
  const onFinish = (data) => {
    axios
      .get(`http://localhost:8080/user/${data.email}-${data.password}`)
      .then((res) => {
        if (res.data.Status.statusCodeValue === 200) {
          message.loading("Logging in..", 2.5).then(() => {
            message.success("Successfully logged in", 2.5).then(() => {
              localStorage.setItem("userData", JSON.stringify(res.data.data));
              window.location.replace("/");
            });
          });
        } else {
          console.log(res);
        }
      });
  };

  return (
    <div>
      <p className="mb-3">Login to continue</p>
      <Card style={{ width: "300px", textAlign: "center" }} hoverable>
        <h4 className="p-2">Login</h4>
        <Form onFinish={onFinish}>
          <Form.Item
            name="email"
            rules={[{ required: true, message: "Please input your username!" }]}
          >
            <Input type="text" placeholder="username" />
          </Form.Item>
          <Form.Item
            name="password"
            rules={[{ required: true, message: "Please input your password!" }]}
          >
            <Input.Password placeholder="password" />
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit" block>
              Login
            </Button>
          </Form.Item>
          <small onClick={toggle} className="text-danger">
            I donot have account
          </small>
        </Form>
      </Card>
    </div>
  );
};
export default LoginComponent;
