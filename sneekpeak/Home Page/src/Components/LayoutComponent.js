import React from "react";
import { Layout} from "antd";
import Flyer from "./Flyer";
import Products from "./ProductComponent";

const { Content, Footer } = Layout;


const LayoutComponent = () => {
  return (
    <div className="wraper">
      <Layout>
        
        <Content style={{ padding: "0 50px" }}>
          <Layout
            className="site-layout-background"
            style={{ padding: "24px 0" }}
          >
            <Content style={{ padding: "0 24px", minHeight: 280 }}>
              <Flyer />
              <Products />
            </Content>
          </Layout>
        </Content>
        <Footer style={{ textAlign: "center" }}>
          Ant Design ©2018 Created by Ant UED
        </Footer>
      </Layout>
    </div>
  );
};

export default LayoutComponent;
