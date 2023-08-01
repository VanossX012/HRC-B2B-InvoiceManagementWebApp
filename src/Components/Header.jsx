import React from "react";
import hrclogo from "../Assets/hrclogo.svg";
import companylogo from "../Assets/abclogo.svg";
import "./header.css";

export default function Header() {
  return (
    <div className="header">
      <div className="abc">
        <img src={companylogo} alt="companylogo" className="company" />
        <img src={hrclogo} alt="HRClogo" className="hrc"/>
      </div>
      <div className="text">
      <p className="txt">Invoice List</p>
      </div>
    </div>
  );
}
