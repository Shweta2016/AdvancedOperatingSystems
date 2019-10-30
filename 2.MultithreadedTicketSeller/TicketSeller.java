package com.p3;

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class TicketSeller{
	volatile static int rowCnt=0;
	volatile static int custCnt=0;
	volatile static Seats[][] seats1 = new Seats[10][10];
	volatile static LinkedList<PriorityQueue<Customer>> qList = new LinkedList<PriorityQueue<Customer>>();
	volatile static String custH[] = new String[100];
	volatile static int custHCnt = 0;
	volatile static String custM[] = new String[100];
	volatile static int custMCnt = 0;
	volatile static String custL[] = new String[100];
	volatile static int custLCnt = 0;
	volatile static int flagH = 0;
	volatile static int flagM = 0;
	volatile static int flagL = 0;
	
	volatile static Timer tm = new Timer();
	
	volatile static int seatCnt = 0;
	volatile static long currentTime;
	volatile static long startTime = System.currentTimeMillis();
	volatile static int gCounter = 1;
	volatile static int dCounter = 1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Please enter number of customers in each row");
		Scanner sc = new Scanner(System.in);
		if(sc.hasNext()){
			rowCnt = sc.nextInt();
		}
		
		//Initialize seats
		for(int i=0; i<10; i++){
			for(int j=0; j<10;j++){
				String str = new String("S" + ((i*10)+j+1) + " ");
				seats1[i][j] = new Seats();
				seats1[i][j].setSeatNo(str);
				seats1[i][j].setAssigned(false);
				}
		}
		
		//Generate arrival times for customers
		Customer[][] custArray = new Customer[10][rowCnt];
		//custCnt = 10 * rowCnt;
		int cNum = 0;
		for(int i=0; i<10;i++){
			for(int j=0; j<rowCnt; j++){
				Customer cust = new Customer();
				String c = "C" + cNum;
				int a = getRandomNumberInts(1,60);
				cust.setArrivalTime(a);
				cust.setCustId(c);
				custArray[i][j] = cust;
				cNum++;
			}
		}
		
		//Print customers with arrival time
		System.out.println("Customers:");
		System.out.println("****(Customer Id:Arrival Time)****");
		for(int i=0; i<10;i++){
			for(int j=0; j<rowCnt; j++){
				System.out.print(custArray[i][j].getCustId() + ":" + custArray[i][j].getArrivalTime());
				System.out.print("   ");
			}
			System.out.println("\n");
		}
			
		sc.close();
		
		//Add customers to queues
		
		for(int i=0; i<10; i++){
			PriorityQueue<Customer> q = new PriorityQueue<>();
			for(int j=0; j<rowCnt; j++){
				Customer newCust = new Customer();
				newCust = custArray[i][j];
				q.add(newCust);	
			}
			qList.add(q);
		}
		
		//Display queues
		System.out.println("Seller Queues:");
		System.out.println("****(Customer Id:Arrival Time)****");
		for(int i=0; i<qList.size(); i++){
			PriorityQueue<Customer> q = new PriorityQueue<>();
			q = qList.get(i);
			System.out.println("\nQueue "+ i+1 + ":");
			for(Customer c1:q){
				System.out.print(c1.getCustId()+":"+c1.getArrivalTime()+" ");
			}
		}
		System.out.println("");
		
		//Create a thread for each seller

		MyThread H = new MyThread("H", qList.get(0));

		MyThread M1 = new MyThread("M1", qList.get(1));
		MyThread M2 = new MyThread("M2", qList.get(2));
		MyThread M3 = new MyThread("M3", qList.get(3));

		MyThread L1 = new MyThread("L1", qList.get(4));
		MyThread L2 = new MyThread("L2", qList.get(5));
		MyThread L3 = new MyThread("L3", qList.get(6));
		MyThread L4 = new MyThread("L4", qList.get(7));
		MyThread L5 = new MyThread("L5", qList.get(8));
		MyThread L6 = new MyThread("L6", qList.get(9));
		

	      H.start();
	      M1.start();
	      M2.start();
	      M3.start();
	      L1.start();
	      L2.start();
	      L3.start();
	      L4.start();
	      L5.start();
	      L6.start();

	      // wait for threads to end
	         try {
	          H.join();
	   	      M1.join();
	   	      M2.join();
	   	      M3.join();
	   	      L1.join();
	   	      L2.join();
	   	      L3.join();
	   	      L4.join();
	   	      L5.join();
	   	      L6.join();
	      } catch ( Exception e) {
	         System.out.println("Interrupted");
	      }
	         
	     // print seller report
         //MyThread m = new MyThread();
         //System.out.println("Final Allocation");
         printReport();
	     
	     System.out.println("Done");
		
		
	}
	public static int getRandomNumberInts(int min, int max){
	    Random random = new Random();
	    return random.ints(min,(max+1)).findFirst().getAsInt();
	}
	
	public static class MyThread extends Thread{
		String tN;
		PriorityQueue<Customer> threadQ;
		//volatile static Seats [][]seats1 = new Seats[10][10];{
		/*for(int i=0; i<10; i++){
			for(int j=0; j<10;j++){
			String str = new String("S" + ((i*10)+j+1) + " ");
			seats1[i][j] = new Seats();
			seats1[i][j].setSeatNo(str);
			seats1[i][j].setAssigned(false);
			}
		}}*/
		Timer cTime = new Timer();
		int currTime = 0;
		
		
		/*MyThread(){
			//System.out.println("");
		}*/
		
		MyThread(String tName, PriorityQueue<Customer> priorityQueue) {
		tN = tName;
		threadQ = priorityQueue; 
		
	   }
	   
	   public void run() {
		   
			//System.out.println("\nIn Run()");
			while(!threadQ.isEmpty()){
				Customer c1 = threadQ.remove();
				//System.out.println(tN + ":" + c1.getCustId()+":"+c1.getArrivalTime()+" ");
				//MyThread mm = new MyThread();
				
				sell(tN, c1);
				
				custCnt++;
			}
			
	      //System.out.println("Thread "+tN+ " exiting");
	   }
   
	   
	   public void sell(String sellerName, Customer c){
		   synchronized(MyThread.class){
		   //System.out.println("In sell");
		   //System.out.println(sellerName);
		   //System.out.println("\n");
		   //System.out.println(c.getCustId()+ " in sell method");
		   /*dCounter++;
		   currentTime = (System.currentTimeMillis());
		   if(gCounter < 60 && dCounter == 3){
			   dCounter = 1;
			   //System.out.println("Time: "+ gCounter + ":00");
			   gCounter++;
		   }*/
		   if(seatCnt < 100){ 
			   //System.out.println("In if");
			   if(sellerName == "H"){
				   flagH = 0;
				   //System.out.println("in H");
				   for(int i=0; i<10 && flagH==0; i++){
					   for(int j=0; j<10 && flagH==0;j++){
						   //System.out.println(seats1[i][j].getSeatNo());
						   if(seats1[i][j].getAssigned()==false){
							   //System.out.println(seats1[i][j].getSeatNo()+" assigned");
							   seats1[i][j].setcId(c.getCustId());
							   seats1[i][j].setsId(sellerName);
							   seats1[i][j].setAssigned(true);
							   //System.out.println(sellerName);
							   System.out.println("\t"+seats1[i][j].getSeatNo()+":"+seats1[i][j].getsId()+":"+seats1[i][j].getcId());
							   
							   seatCnt++;
							   //System.out.println(seatCnt + "*******");
							   flagH = 1;
						   }
					   }
				   }
				   
					try {
						int t1 = getRandomNumberInts(1,2);
						//System.out.print(sellerName + " " + c.getCustId()+":"+c.getArrivalTime()+" ");
						sleep(1 * 10);;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(sellerName.matches("[M]+[\\d]")){
				    flagM = 0;
					int i = 5;
					int fact = 0;
					while(i>=0 && i<10 && flagM==0){
					   for(int j=0; j<10 && flagM==0;j++){
						   if(seats1[i][j].getAssigned()==false){
							   //System.out.println(seats1[i][j].getSeatNo()+" assigned");
							   seats1[i][j].setcId(c.getCustId());
							   seats1[i][j].setsId(sellerName);
							   seats1[i][j].setAssigned(true);
							   //System.out.println(sellerName);
							   System.out.println("\t"+seats1[i][j].getSeatNo()+":"+seats1[i][j].getsId()+":"+seats1[i][j].getcId());
							   
							   seatCnt++;
							   //System.out.println(seatCnt + "*******");
							   flagM=1;
						   }
					   }
					   fact++;
					   if(fact%2 == 0){
							i = i-fact;
						}else{
							i = i+fact;
						}
					   }
					try {
						int t2 = getRandomNumberInts(2,4);
						//System.out.print(sellerName + " " + c.getCustId()+":"+c.getArrivalTime()+" ");
						sleep(7 * 10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(sellerName.matches("[L]+[\\d]")){
					flagL = 0;
					for(int i=9; i>=0 && flagL==0; i--){
						   for(int j=0; j<10 && flagL==0;j++){
							   if(seats1[i][j].getAssigned()==false){
								   //System.out.println(seats1[i][j].getSeatNo()+" assigned");
								   seats1[i][j].setcId(c.getCustId());
								   seats1[i][j].setsId(sellerName);
								   seats1[i][j].setAssigned(true);
								   //System.out.println(sellerName);
								   System.out.println("\t"+seats1[i][j].getSeatNo()+":"+seats1[i][j].getsId()+":"+seats1[i][j].getcId());
								   
								   seatCnt++;
								   //System.out.println(seatCnt + "*******");
								   flagL=1;
							   }
						   }
					   }
					try {
						int t3 = getRandomNumberInts(4,7);
						//System.out.print(sellerName + " " + c.getCustId()+":"+c.getArrivalTime()+" ");
						sleep(15 * 10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			   //currTime++;
		   }
		   else{
			   //System.out.println("Sorry! Sold out...");
			   //System.out.println(c.getCustId() + "left without ticket");
			   if(custHCnt<100 && sellerName=="H"){
					custH[custHCnt] = c.getCustId();
					custHCnt++;
			   }
			   else if(custMCnt<100 && sellerName.matches("[M]+[\\d]")){
				   custM[custMCnt] = c.getCustId();
				   custMCnt++;
			   }
			   else if(custLCnt<100 && sellerName.matches("[L]+[\\d]")){
				   custL[custLCnt] = c.getCustId();
				   custLCnt++;
			   }
				
		   }
	   }

	
	}
	}

	static void printReport(){
		
		System.out.println("\nFinal seat allocation");
		System.out.println("****(SeatNo:Seller:Customer)****");
		for(int i=0; i<10; i++){
			//System.out.println("\n");
			   for(int j=0; j<10;j++){
				   //System.out.println("Row"+ i+1);
				   String seat = seats1[i][j].getSeatNo();
				   String seller1 = "--";
				   String Cust1 = "--";
				   if(seats1[i][j].getsId() != null){
					   seller1 = seats1[i][j].getsId();
				   }
				   if(seats1[i][j].getcId() != null){
					   Cust1 = seats1[i][j].getcId();
				   }
				   
				   System.out.print( seat + ":" + seller1 + ":" + Cust1 + "  ");
				   
			   }
			   System.out.println("\n");
		   }
		System.out.println("\n\nCustumer that leaves without ticket");
		System.out.println("Custumer type H:");
		System.out.println("Count="+custHCnt);
		for(int i=0; i<custHCnt; i++){
			
			System.out.print(custH[i]+" ");
		}
		System.out.println("\nCustumer type M:");
		System.out.println("Count="+custMCnt);
		for(int i=0; i<custMCnt; i++){
			
			System.out.print(custM[i]+" ");
		}
		System.out.println("\nCustumer type L:");
		System.out.println("Count="+custLCnt);
		for(int i=0; i<custLCnt; i++){
			
			System.out.print(custL[i]+" ");
		}
		System.out.println("\nTotal cust count = "+ custCnt);
		System.out.println("No. of customers returned = "+ (custHCnt+custMCnt+custLCnt));
	}	
	
	
	
}


