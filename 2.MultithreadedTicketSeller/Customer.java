package com.p3;

public class Customer implements Comparable<Customer>{
	String custId;
	int arrivalTime;

	

	public String getCustId() {
		return custId;
	}



	public void setCustId(String custId) {
		this.custId = custId;
	}



	public int getArrivalTime() {
		return arrivalTime;
	}



	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



	@Override
	public int compareTo(Customer o) {
		// TODO Auto-generated method stub
		return this.arrivalTime - o.arrivalTime;
	}

}
