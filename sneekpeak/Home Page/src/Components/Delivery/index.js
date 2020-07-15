import React,{useState,useEffect} from 'react';
import {Card, Table,Button,Row,Col} from 'antd';
import axios from 'axios';

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
      title: "Address",
      key: "address",
      dataIndex: "address",
    },
    {
      title: "Quantity",
      key: "orders",
      dataIndex: "orderQuantity",
    },
    {
      title: "Price",
      key: "price",
      dataIndex: "productPrice",
    },
    {
      title: "Settings",
      key: "settings",
      dataIndex: "settings",
    }
  ];



const Delivery =()=>{
	const [dataSource,setDataSource] = useState([]);
	useEffect(()=>{
		axios.get('http://localhost:8080/orders/allOrders')
		.then((res)=>{
			if(res.status === 200){	
				res.data.map((orders,i)=>{
					orders["settings"]=<But key={i}>delivered</But>;
				})			
				setDataSource(res.data);
			}
		})
	},[dataSource]);
	
	return(
		<div className="p-5">
			<h5>Delivery Orders</h5>
			<Table columns={columns} dataSource={dataSource}/>
		</div>
	);
}

const But=()=>{
	return(
		<div>
		<Row>
			<Col span={6}>
				<Button type="primary">delivered</Button>
			</Col>
				<Col span={6}>
				<Button type="danger">Cancel</Button>
			</Col>
			<Col span={6}>
				<Button type="gost">Shipped</Button>
			</Col>
		</Row>
		</div>
	)
}

export default Delivery;