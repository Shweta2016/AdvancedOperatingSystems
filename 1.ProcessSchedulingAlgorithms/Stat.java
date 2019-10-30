package com.advos;

public class Stat {
	float turnArountTm;
	float responseTime;
	float waitTime;
	Stat(){
		this.turnArountTm = 0;
		this.responseTime=0;
		this.waitTime=0;
	}
	public float getTurnArountTm() {
		return turnArountTm;
	}
	public void setTurnArountTm(float turnArountTm) {
		this.turnArountTm = turnArountTm;
	}
	public float getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(float responseTime) {
		this.responseTime = responseTime;
	}
	public float getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(float waitTime) {
		this.waitTime = waitTime;
	}
	
	

}
