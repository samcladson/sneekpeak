import React, { useState, useEffect } from "react";
import {Link} from 'react-router-dom'
import axios from "axios";
import { Card, Row, Col, Rate, Button, InputNumber,message } from "antd";

const userData = JSON.parse(localStorage.getItem("userData"));
let cart = [];

const Product = ({ match }) => {
  const [item, setItem] = useState({});
  const [order, setOrder] = useState(0);
  const [qty, setQty] = useState(1);

  useEffect(() => {
    axios
      .get(`http://localhost:8081/products/${match.params.id}`)
      .then((res) => {
        if (res.status === 200) {
          setItem(res.data);
          setOrder(res.data.price);
        } else {
          console.log("error");
        }
      });
  }, []);

  const quantity = (qty) => {
    setOrder(item.price * qty);
    setQty(qty);
    // setnewPrice(parseInt(item.price) * parseInt(qty))
  };

  const placeOrder = () => {
    let date = new Date();
    let orderDetail = {
      customerId: userData.userId,
      productId: item.id,
      productName: item.product_name,
      "productDescription":item.product_description,
      orderQuantity: qty,
      productPrice: order,
      orderDate:
        date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear(),
    };
    axios
      .post("http://localhost:8080/checkQuantity", orderDetail)
      .then((res)=>{
      	if(res.data.STATUS.statusCodeValue===200){
      		message.loading({style:{marginTop:"10vh"},content:"Checking Availability"}).then(()=>{
      			message.success({style:{marginTop:"10vh"},content:res.data.message}).then(()=>{
      				sessionStorage.setItem("orderDetail",JSON.stringify(orderDetail));
      				window.location.replace("/checkout");
      			})
      		})
      	}else{
      		message.loading({style:{marginTop:"10vh"},content:"checking Availability"}).then(()=>{
      			message.error({style:{marginTop:"10vh"},content:res.data.message});
      		})
      	}
      })
      .catch((err) => {
        console.log(err);
      });
    
  };

  const adToCart =()=>{
  	let date = new Date();
    let cartDetail = {
      customerId: userData.userId,
      productId: item.id,
      productName: item.product_name,
      orderQuantity: qty,
      productPrice: order,
      orderDate:
        date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear(),
    };
    cart.push(cartDetail)
    sessionStorage.setItem("cart",JSON.stringify(cart));
  }

  return (
    <div className="container p-5">
      <Row gutter={(24, 24)}>
        <Col md={12} sm={24}>
          <Card
            style={{ width: "350px" }}
            hoverable
            cover={
              <img
                alt=""
                src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
              />
            }
          ></Card>
        </Col>
        <Col md={12} sm={24}>
          <h5 className="text-success"> {item.product_name}</h5>
          <h6> this is desc {item.product_description}</h6>
          <h6 className="text-success"> &#8377; {order}</h6>
          <InputNumber
            min={1}
            defaultValue={1}
            onChange={(e) => quantity(e)}
          />{" "}
          {item.quantity} Available
          <br />
          <br />
          <Rate defaultValue={4} />
          <br />
          <br />
          <Button type="primary" onClick={placeOrder}>
            Continue to payment
          </Button>
          &nbsp;
          <Button type="danger" onClick={adToCart}>Add to cart</Button>
        </Col>
      </Row>
    </div>
  );
};
export default Product;
