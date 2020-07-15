import React from "react";
import { Layout, Row, Col, Menu, Input,Badge } from "antd";
import {
  DownOutlined,
  ShoppingCartOutlined,
  SettingOutlined,
} from "@ant-design/icons";
import { Link } from "react-router-dom";

const userData = JSON.parse(localStorage.getItem("userData"));

const { Header } = Layout;
const { Search } = Input;
const { SubMenu } = Menu;

var cart = JSON.parse(sessionStorage.getItem("cart"));

const HeaderComponent = () => {
  const clear = () => {
    localStorage.clear();
    sessionStorage.clear();
    window.location.reload(true);
  };
  if(window.location.pathname === "/delivery"){
    return null;
  }
  return (
    <Layout>
      <Header className="header">
        <Menu theme="dark" mode="horizontal" defaultSelectedKeys={["1"]}>
          <Menu.Item
            className="logo"
            style={{
              width: "100px",
              color: "white",
              fontSize: "18px",
              margin: "0px 20px",
            }}
          ><Link to="/">
            SneekPeak
            </Link>
          </Menu.Item>
          <Menu.Item key="1">
              <Link to='/'>Home</Link>
          </Menu.Item>
          <SubMenu key="sub1" title="Category" icon={<DownOutlined />}>
            <Menu.Item>Electronics</Menu.Item>
            <Menu.Item>Groceries</Menu.Item>
            <Menu.Item>Furnitures</Menu.Item>
            <Menu.Item>Clothings</Menu.Item>
          </SubMenu>
          <SubMenu key="sub2" title="Brands" icon={<DownOutlined />}>
            <Menu.Item>brand 1</Menu.Item>
            <Menu.Item>brand 2</Menu.Item>
            <Menu.Item>brand 3</Menu.Item>
            <Menu.Item>brand 4</Menu.Item>
          </SubMenu>
          <Search
            placeholder="Enter any product"
            size="large"
            style={{
              width: "35rem",
              margin: "0px 20px",
            }}
          ></Search>
          <Badge count={cart?cart.length:0}>
          <ShoppingCartOutlined
            style={{ fontSize: "16px", margin: "0px 20px" }}
          />
          </Badge>
          <Menu.Item key="2">Support</Menu.Item>
          {userData ? (
            <SubMenu key="sub3" title="Settings" icon={<SettingOutlined />}>
              <Menu.Item>
                  <Link to="/dashboard">Dashboard</Link>
              </Menu.Item>
              <Menu.Item onClick={clear}>Logout</Menu.Item>
            </SubMenu>
          ) : (
            <Menu.Item key="3">
              <Link to="/signup">Signup</Link>
            </Menu.Item>
          )}
        </Menu>
      </Header>
    </Layout>
  );
};

export default HeaderComponent;
