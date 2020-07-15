import React from "react";
import "antd/dist/antd.css";
import { BrowserRouter as Router, Route,Switch } from "react-router-dom";
import Layout from "./Components/LayoutComponent";
import Credential from "./Components/AuthComponent";
import Header from "./Components/Header"
import Product from "./Components/Product"
import Dashboard from "./Components/Dashboard"
import Checkout from "./Components/Payment/Checkout.js"
import Delivery from "./Components/Delivery"

function App() {
  return (
    <Router>
      <div className="App">
      	<Header/>
      	<Switch>
	        <Route path="/" exact component={Layout} />
	        <Route path="/signup" exact component={Credential} />
	        <Route path="/product/:id" exact component={Product}/>
          <Route path="/dashboard" exact component={Dashboard}/>
          <Route path="/checkout" exact component={Checkout}/>
        </Switch>
        <Route path="/delivery" exact component={Delivery}/>
      </div>
    </Router>
  );
}

export default App;
