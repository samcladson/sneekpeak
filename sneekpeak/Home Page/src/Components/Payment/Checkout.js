import React from "react";
import axios from "axios";
import { Card, Form, Input, Row, Col, Button, Empty, message } from "antd";

const orderDetails = JSON.parse(sessionStorage.getItem("orderDetail"));

const Checkout = () => {
  const placeOrder = (data) => {
    axios.post("http://localhost:8080/placeOrder", data).then((res) => {
      if (res.data.STATUS === "OK") {
        message
          .success({ style: { marginTop: "10vh" }, content: res.data.message })
          .then(() => {
            const accNo = res.data.accNo;
            axios
              .post(`http://localhost:8080/checkBalance/${accNo}`, {
                balance: orderDetails.productPrice,
              })
              .then((res) => {
                if (res.data.STATUS === "OK") {
                	// const newBalance = res.data.balance;
                  message.success({
                    style: { marginTop: "10vh" },
                    content: res.data.message,
                  }).then(()=>{
                  	//axios.get(`http://localhost:8080/updateAccount/${newBalance.accountNumber}`,newBalance).then((res)=>{
                  		// if(res.status===200){
                  			axios.get("http://localhost:8080/completeOrder").then((result)=>{
		                  		if(result.data.STATUS==="OK"){
		                  			message.success({style:{marginTop:"10vh"},content:result.data.message}).then(()=>{
		                  				window.location.replace("/dashboard");
		                  			});		                  			 
		                  		}else{
		                  			message.error({style:{marginTop:"10vh"},content:"Error while placing order"});
		                  		}
		                  	})
                  		// }else{
                  		// 	message.error({style:{marginTop:"10vh"},content:"Error placing order due to problem in your account"});
                  		// }
                  	//})
                  	
                  });
                } else {
                  message.error({
                    style: { marginTop: "10vh" },
                    content: res.data.message,
                  });
                }
              });
          });
      } else {
        message.error({
          style: { marginTop: "10vh" },
          content: res.data.message,
        });
      }
    });
  };

  return (
    <div
      className="container p-5 d-flex justify-content-center align-items-center"
      style={{ height: "80vh" }}
    >
      <Row gutter={[12, 12]}>
        <Col md={8} sm={12}>
          {orderDetails ? (
            <Card hoverable title="Order details">
              <h4 className="text-secondary">{orderDetails.productName}</h4>
              <p className="text-secondary">
                {orderDetails.productDescription}
              </p>
              <p className="text-secondary">ID : {orderDetails.productId}</p>
              <h6 className="text-secondary">
                Quantity : {orderDetails.orderQuantity}
              </h6>
              <h6 className="text-success">
                TOTAL : $ {orderDetails.productPrice}
              </h6>
            </Card>
          ) : (
            <Card>
              <Empty />
            </Card>
          )}
        </Col>
        <Col md={16} sm={24}>
          <Card title="Checkout" hoverable style={{ width: "100%" }}>
            <Form onFinish={placeOrder}>
              <Row gutter={[12, 12]}>
                <Col span={12}>
                  <Form.Item name="cardHolderName">
                    <Input size="large" placeholder="Name on card" />
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item name="cardNumber">
                    <Input size="large" placeholder="Card Number" />
                  </Form.Item>
                </Col>
              </Row>
              <Row gutter={[12, 12]}>
                <Col span={8}>
                  <Form.Item name="cardName">
                    <Input size="large" placeholder="Card name" />
                  </Form.Item>
                </Col>
                <Col span={8}>
                  <Form.Item name="expiryDate">
                    <Input size="large" placeholder="expiry date" />
                  </Form.Item>
                </Col>
                <Col span={8}>
                  <Form.Item name="cvv">
                    <Input size="large" placeholder="cvv" />
                  </Form.Item>
                </Col>
              </Row>
              <Button type="primary" size="large" htmlType="submit">
                Proceed to pay
              </Button>
              &nbsp;
              <Button type="danger" size="large">
                Cancel payment
              </Button>
            </Form>
          </Card>
        </Col>
      </Row>
    </div>
  );
};

export default Checkout;
