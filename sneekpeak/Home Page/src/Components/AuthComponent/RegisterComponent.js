import React from "react";
import { Card } from "antd";
import axios from "axios";
import { Form, Input, Button, message } from "antd";
const RegisterComponent = ({ toggle }) => {
  const onFinish = (data) => {
    axios.post("http://localhost:8080/addUser", data).then((res) => {
      res.status === 200
        ? message
            .loading("Creating Account", 3)
            .then(() => message.success("Created Successfully", 2))
        : message
            .loading("Creating Account", 3)
            .then(() => message.error("Sorry! Account not Created", 2));
    });
  };
  return (
    <div>
      <p>Get Started From Here!</p>
      <Card style={{ width: "300px", textAlign: "center" }} hoverable>
        <h4 className="p-2">Signup</h4>
        <Form onFinish={onFinish}>
          <Form.Item
            name="username"
            rules={[{ required: true, message: "Please input your username!" }]}
          >
            <Input type="text" placeholder="username" />
          </Form.Item>
          <Form.Item
            name="email"
            rules={[
              {
                required: true,
                message: "Please input your email!",
                type: "email",
              },
            ]}
          >
            <Input type="email" placeholder="email" />
          </Form.Item>
          <Form.Item
            name="phone"
            rules={[
              {
                required: true,
                message: "Please input your phone!",
              },
            ]}
          >
            <Input placeholder="phone number" />
          </Form.Item>
          <Form.Item
            name="password"
            rules={[{ required: true, message: "Please input your password!" }]}
          >
            <Input.Password placeholder="password" />
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit" block>
              Register
            </Button>
          </Form.Item>
          <small onClick={toggle} className="text-success">
            I have an account
          </small>
        </Form>
      </Card>
    </div>
  );
};
export default RegisterComponent;
