package com.highradius.model;


public class Pojo {

	private int Sl_No;
	private int customerOrderId;
	private int salesOrg;
	private String distrChannel;
	private int compCode;
	private String orderCreationDate;
	private double orderAmt;
	private String orderCurrency;
	private int customerNumber;
	private double amountUSD;
	
	
	public Pojo(int customerOrderId, int salesOrg, String distrChannel, int compCode, String orderCreationDate,
			double orderAmt, String orderCurrency, int customerNumber, double amountUSD) {
		super();
		this.customerOrderId = customerOrderId;
		this.salesOrg = salesOrg;
		this.distrChannel = distrChannel;
		this.compCode = compCode;
		this.orderCreationDate = orderCreationDate;
		this.orderAmt = orderAmt;
		this.orderCurrency = orderCurrency;
		this.customerNumber = customerNumber;
		this.amountUSD = amountUSD;
	}

	
	

	public Pojo(int sI_No, int customerOrderId, int salesOrg, String distrChannel, int compCode, String orderCreationDate,
			double orderAmt, String orderCurrency, int customerNumber, double amountUSD) {
		super();
		Sl_No = sI_No;
		this.customerOrderId = customerOrderId;
		this.salesOrg = salesOrg;
		this.distrChannel = distrChannel;
		this.compCode = compCode;
		this.orderCreationDate = orderCreationDate;
		this.orderAmt = orderAmt;
		this.orderCurrency = orderCurrency;
		this.customerNumber = customerNumber;
		this.amountUSD = amountUSD;
	}
	
	
	public int getSI_No() {
		return Sl_No;
	}

	public void setSI_No(int sI_No) {
		Sl_No = sI_No;
	}

	public int getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(int customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public int getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(int salesOrg) {
		this.salesOrg = salesOrg;
	}

	public String getDistrChannel() {
		return distrChannel;
	}

	public void setDistrChannel(String distrChannel) {
		this.distrChannel = distrChannel;
	}

	public int getCompCode() {
		return compCode;
	}

	public void setCompCode(int compCode) {
		this.compCode = compCode;
	}

	public String getOrderCreationDate() {
		return orderCreationDate;
	}

	public void setOrderCreationDate(String orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
	}

	public double getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(double orderAmt) {
		this.orderAmt = orderAmt;
	}

	public String getOrderCurrency() {
		return orderCurrency;
	}

	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public double getAmountUSD() {
		return amountUSD;
	}

	public void setAmountUSD(double amountUSD) {
		this.amountUSD = amountUSD;
	}
	
	
	

	
	
}