import React from "react";
import { Carousel } from "antd";
const Flyer = () => {
  return (
    <Carousel autoplay>
      <div>
        <img
          src="https://www.hatsoffaccessories.com/wp-content/uploads/2019/03/main-banner_2400-1248_2.jpg"
          alt=""
          style={{
            width: "100%",
            height: "550px",
          }}
        />
      </div>
      <div style={{ width: "100%", height: "auto" }}>
        <img
          src="https://news-cdn.softpedia.com/images/news2/First-Samsung-Galaxy-S6-Promo-Video-Focuses-on-Craftmanship-474564-2.jpg"
          alt=""
          style={{
            width: "100%",
            height: "550px",
          }}
        />
      </div>
      <div style={{ width: "100%", height: "auto" }}>
        <img
          src="https://www.aocindia.com/images/home-banner2.jpg"
          alt=""
          style={{
            width: "100%",
            height: "550px",
          }}
        />
      </div>
      <div style={{ width: "100%", height: "auto" }}>
        <img
          src="https://i.pinimg.com/originals/b7/60/65/b76065c978317292b1ecde58fc2b6939.jpg"
          alt=""
          style={{
            width: "100%",
            height: "550px",
          }}
        />
      </div>
    </Carousel>
  );
};

export default Flyer;
