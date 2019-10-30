package com.advos;

import java.util.Random;

public class RandomNumberGen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arrivalTime = new int[10];
		RandomNumberGen rng = new RandomNumberGen();
		for(int i=0; i<10; i++){
			arrivalTime[i] = rng.getRandomNumberInts(0, 100);
			System.out.println(arrivalTime[i]);
		}
	}
	public int getRandomNumberInts(int min, int max){
	    Random random = new Random();
	    return random.ints(min,(max+1)).findFirst().getAsInt();
	}
	public Process[] randomNumPriority(){
		Process process1[] = new Process[10];
		RandomNumberGen rn = new RandomNumberGen();
		for(int i=0; i<10; i++){
			process1[i] = new Process();
			process1[i].setArrivalTm(rn.getRandomNumberInts(0, 99));
			process1[i].setRunTm(rn.getRandomNumberInts(1, 10));
			process1[i].setPriority(rn.getRandomNumberInts(1, 4));
		}
		return process1;
		
	}
}
