package com.advos;

public class Process implements Comparable<Process>{
	int arrivalTm;
	int runTm;
	int priority;
	

	public int getArrivalTm() {
		return arrivalTm;
	}


	public void setArrivalTm(int arrivalTm) {
		this.arrivalTm = arrivalTm;
	}


	public int getRunTm() {
		return runTm;
	}


	public void setRunTm(int runTm) {
		this.runTm = runTm;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(Process process) {
		// TODO Auto-generated method stub
		return this.arrivalTm - process.arrivalTm;
	}
	
	public String toString() {
	    return String.format("(%d, %d, %d)", arrivalTm, runTm, priority);
	}

	

}
