import React, { useEffect, useState, Fragment } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { Row, Col, Card } from "antd";
import { AppstoreAddOutlined } from "@ant-design/icons";

const { Meta } = Card;

const ProductComponent = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8081/products/allProducts").then((res) => {
      setProducts(res.data);
    });
  }, []);
  return (
    <div>
      <h3 className="text-center m-4">Latest Products</h3>
      <Row gutter={[24, 24]}>
        {products.map((product, i) => (
          <Products key={i} data={product} />
        ))}
      </Row>
    </div>
  );
};

const Products = ({ data }) => {
  const { id, product_name, product_description, price, quantity } = data;
  return (
    <Fragment>
      <Col xs={24} md={6} sm={12}>
        <Link to={`product/${id}`}>
          <Card
            hoverable
            actions={[
              <h5 className="text-success">&#8377;&nbsp;{price}</h5>,
              <AppstoreAddOutlined />,
            ]}
            cover={
              <img
                alt=""
                src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
              />
            }
          >
            <Meta title={product_name} description={product_description} />
          </Card>
        </Link>
      </Col>
    </Fragment>
  );
};

export default ProductComponent;
