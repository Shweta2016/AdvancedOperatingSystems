package com.p3;

public class Seats {
	
	String SeatNo;
	String cId;
	String sId;
	Boolean assigned;

	/*public Seats(String SeatNo, String cId, String sId){
		SeatNo = this.SeatNo;
		cId = this.cId;
		sId = this.sId;
	}*/

	public Boolean getAssigned() {
		return assigned;
	}



	public void setAssigned(Boolean assigned) {
		this.assigned = assigned;
	}



	public String getSeatNo() {
		return SeatNo;
	}



	public void setSeatNo(String seatNo) {
		SeatNo = seatNo;
	}



	public String getcId() {
		return cId;
	}



	public void setcId(String cId) {
		this.cId = cId;
	}



	public String getsId() {
		return sId;
	}



	public void setsId(String sId) {
		this.sId = sId;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
