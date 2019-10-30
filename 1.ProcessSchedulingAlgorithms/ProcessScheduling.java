package com.advos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ProcessScheduling {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line = "";
		Scanner sc = new Scanner(System.in);
		Boolean runState = true;
		while(runState){
			System.out.println("\nPlease select options below:");
			System.out.println("1.FCFS");
			System.out.println("2.SJF");
			System.out.println("3.SRT");
			System.out.println("4.RR");
			System.out.println("5.HPF-non-preemptive");
			System.out.println("6.HPF-Preemptive");
			System.out.println("7.Exit");
			
			if(sc.hasNext()){
				line = sc.nextLine();
				int option = Integer.parseInt(line);
				try{
					if(option > 0 && option < 8){
						switch(option){
						case 1:	FCFS();
						break;
						case 2: SJF();
						break;
						case 3: SRT();
						break;
						case 4: RR();
						break;
						case 5: HPFNonPreemptive();
						break;
						case 6: HPFPreemptive();
						break;
						case 7: runState = false;
						continue;
						default: System.out.println("Error");
						break;
						}
					}else{
						System.out.println("Please enter valid option");
						continue;
					}
				}catch(NumberFormatException e){
					System.out.println("Please enter valid option");
				}
				
			}
			else{
				System.out.println("Please select option");
				continue;
			}
			
		}
		sc.close();
	}
	
	public static void FCFS(){
		System.out.println("In FCFS Non-preemptive:");
		RandomNumberGen rng = new RandomNumberGen();
		Process proc[] = new Process[10];
		proc = rng.randomNumPriority();
		
		System.out.println("Before sorting: " + Arrays.toString(proc));
		Arrays.sort(proc);
		System.out.println("After sorting: " + Arrays.toString(proc));
		
		int waitTm[] = new int[10];    //Wait time
		int respTm[] = new int[10];	   //Response time
		int compTm[] = new int[10];    //Completion time
		int startTm[] = new int[10];   //Start time
		int turnArTm[] = new int[10];      
		//Calculate start time and wait time
		startTm[0] = proc[0].getArrivalTm();   // Start time of 1st process=arrival time
		waitTm[0] = 0;                        // Wait time of 1st process = 0
		for(int i=1; i<10;i++){
			int aTime = proc[i].getArrivalTm();
			compTm[i-1] = startTm[i-1] + proc[i-1].getRunTm();
			if(aTime > compTm[i-1]){
				waitTm[i] = 0;
				startTm[i] = aTime;
			}
			else{
				waitTm[i] = compTm[i-1] - aTime;
				startTm[i] = waitTm[i] + aTime;
			}
			
		}
		compTm[9] = startTm[9] + proc[9].getRunTm();
		//Print start time and wait time, completion time
		System.out.println("Wait time:");
		float waitSum = 0;
		for(int i=0; i<10;i++){
			System.out.print(waitTm[i] + " ");
			waitSum = waitSum + waitTm[i];
		}
		System.out.println("\nStart time:");
		for(int i=0; i<10;i++){
			System.out.print(startTm[i] + " ");
		}
		System.out.println("\nCompletion time:");
		for(int i=0; i<10;i++){
			System.out.print(compTm[i] + " ");
		}
		//Calculate response time
		System.out.println("\nResponse time:");
		float respSum = 0;
		for(int i=0; i<10; i++){
			respTm[i] = startTm[i] - proc[i].getArrivalTm();
			System.out.print(respTm[i] + " ");
			respSum = respSum + respTm[i];
		}
		//Calculate Turnaround time
		float turnAroundSum = 0;
		System.out.println("\nTurnAround time:");
		for(int i=0; i<10; i++){
			turnArTm[i] = compTm[i] - proc[i].getArrivalTm();
			System.out.print(turnArTm[i] + " ");
			turnAroundSum = turnAroundSum + turnArTm[i];
		}
		//Print average response, wait and turn around time
		System.out.println("\nAverage TurnAround Time = " + (turnAroundSum+50/10));
		System.out.println("Average Response Time = " + (respSum+10/10));
		System.out.println("Average Wait Time = " + (waitSum+10/10));
		
		
	}
	public static void SJF(){
		System.out.println("In SJF Non-preemptive:");
		RandomNumberGen rng = new RandomNumberGen();
		Process proc1[] = new Process[10];
		proc1 = rng.randomNumPriority();

		ArrayList<Process> pList = new ArrayList<Process>(Arrays.asList(proc1));
		System.out.println("Before sorting: " + Arrays.toString(proc1));
		Collections.sort(pList, new ShortestJobSorter());
		Process [] proc = pList.toArray(new Process[pList.size()]);
		System.out.println("After sorting: " + Arrays.toString(proc));
		calculate(proc);
		
	}
	public static void calculate(Process proc[]){
		int waitTm[] = new int[10];    //Wait time
		int respTm[] = new int[10];	   //Response time
		int compTm[] = new int[10];    //Completion time
		int startTm[] = new int[10];   //Start time
		int turnArTm[] = new int[10];      
		//Calculate start time and wait time
		startTm[0] = proc[0].getArrivalTm();   // Start time of 1st process=arrival time
		waitTm[0] = 0;                        // Wait time of 1st process = 0
		for(int i=1; i<10;i++){
			int aTime = proc[i].getArrivalTm();
			compTm[i-1] = startTm[i-1] + proc[i-1].getRunTm();
			if(aTime > compTm[i-1]){
				waitTm[i] = 0;
				startTm[i] = aTime;
			}
			else{
				waitTm[i] = compTm[i-1] - aTime;
				startTm[i] = waitTm[i] + aTime;
			}
			
		}
		compTm[9] = startTm[9] + proc[9].getRunTm();
		//Print start time and wait time, completion time
		System.out.println("Wait time:");
		float waitSum = 0;
		for(int i=0; i<10;i++){
			System.out.print(waitTm[i] + " ");
			waitSum = waitSum + waitTm[i];
		}
		System.out.println("\nStart time:");
		for(int i=0; i<10;i++){
			System.out.print(startTm[i] + " ");
		}
		System.out.println("\nCompletion time:");
		for(int i=0; i<10;i++){
			System.out.print(compTm[i] + " ");
		}
		//Calculate response time
		System.out.println("\nResponse time:");
		float respSum = 0;
		for(int i=0; i<10; i++){
			respTm[i] = startTm[i] - proc[i].getArrivalTm();
			System.out.print(respTm[i] + " ");
			respSum = respSum + respTm[i];
		}
		//Calculate Turnaround time
		float turnAroundSum = 0;
		System.out.println("\nTurnAround time:");
		for(int i=0; i<10; i++){
			turnArTm[i] = compTm[i] - proc[i].getArrivalTm();
			System.out.print(turnArTm[i] + " ");
			turnAroundSum = turnAroundSum + turnArTm[i];
		}
		//Print average response, wait and turn around time
		System.out.println("\nAverage TurnAround Time = " + (turnAroundSum/10));
		System.out.println("Average Response Time = " + (respSum/10));
		System.out.println("Average Wait Time = " + (waitSum/10));
	}
	public static void SRT(){
		System.out.println("In SRT Preemptive:");
		RandomNumberGen rng = new RandomNumberGen();
		Process proc[] = new Process[10];
		proc = rng.randomNumPriority(); 
		System.out.println("Before sorting: " + Arrays.toString(proc));
		//Calculate wait time
		int respt[] = new int[10];
		boolean checked[] = new boolean[10];    //Flag to check the first start time of process
		for(int i=0; i<10; i++){
			checked[i] = false;
		}
		int rt[] = new int[10];
		int wt[] = new int[10];
		int tt[] = new int[10];
		for (int i = 0; i < 10; i++){
			rt[i] = proc[i].getRunTm();
		}
		int complete = 0, t = 0, minm = Integer.MAX_VALUE; 
        int shortest = 0, finish_time; 
        boolean check = false; 
        
        // Process until all processes gets completed 
        while (complete != 10) { 
            // Find process with minimum remaining time  
            for (int j = 0; j < 10; j++)  
            { 
                if ((proc[j].getArrivalTm() <= t) && (rt[j] < minm) && rt[j] > 0) { 
                    minm = rt[j]; 
                    shortest = j; 
                    check = true; 
                } 
            }
            if (check == false) { 
                t++; 
                continue; 
            }
            else{
            	if(checked[shortest] == true){
            	}
            	else{
            		checked[shortest] = true;
            		respt[shortest] = t - proc[shortest].getArrivalTm();
            	}
            }
            // Reduce remaining time by one 
            rt[shortest]--; 
            // Update minimum 
            minm = rt[shortest]; 
            if (minm == 0) 
                minm = Integer.MAX_VALUE; 
            // If a process gets completely executed 
            if (rt[shortest] == 0) { 
                // Increment complete 
                complete++; 
                check = false; 
                // Find finish time of current process 
                finish_time = t + 1; 
                // Calculate waiting time 
                wt[shortest] = finish_time - proc[shortest].getRunTm() - proc[shortest].getArrivalTm(); 
                if (wt[shortest] < 0) 
                    wt[shortest] = 0; 
            } 
            // Increment time 
            t++; 
        } 
        System.out.println("\nWait Time:");
        float waitSum = 0;
		for(int i=0; i<10;i++){
			System.out.print(wt[i] + " ");
			waitSum = waitSum + wt[i];
		}
		System.out.println("\nResponse Time:");
		float respSum = 0;
		for(int i=0; i<10;i++){
			System.out.print(respt[i] + " ");
			respSum = respSum + respt[i];
		}
		//Calculate turn around time
		float ttSum = 0;
		System.out.println("\nTurn Around Time:");
		for (int i = 0; i < 10; i++){
            tt[i] = proc[i].getRunTm() + wt[i]; 
            System.out.print(tt[i] + " ");
            ttSum = ttSum + tt[i];
		}
		//Print average response, wait and turn around time
		System.out.println("\nAverage TurnAround Time = " + (ttSum/10));
		System.out.println("Average Response Time = " + (respSum/10));
		System.out.println("Average Wait Time = " + (waitSum/10));
	}
	public static void RR(){
		System.out.println("In RR Preemptive:");
		RandomNumberGen rng = new RandomNumberGen();
		Process proc[] = new Process[10];
		proc = rng.randomNumPriority();
		
		System.out.println("Before sorting: " + Arrays.toString(proc));
		Arrays.sort(proc);
		System.out.println("After sorting: " + Arrays.toString(proc));
		
		int w[] = new int[10];
		int comp[] = new int[10];
		int respt[] = new int[10];
		boolean visited[] = new boolean[10];
		
		//Make copy of run time and arrival time
		int a[] = new int[10];
		int r[] = new int[10];
		for(int i=0; i<10; i++){
			a[i] = proc[i].getArrivalTm();
			r[i] = proc[i].getRunTm();
		}
		
		//Flag indicate completion of process
		int procOver[] = new int[10];
		for(int i=0; i<10; i++){
			procOver[i] = 0;
			visited[i] = false;
		}
		int completion = 0;
		//Find wait time, response time
		int totalTime = a[0];
		respt[0] = 0;
		w[0] = 0;
		while(completion<10){
			//System.out.println("in while");
			for(int i=0; i<10; ){
				//System.out.println("in for");
				if(procOver[i] == 0){
					//System.out.println("in if proc =");
					if(r[i] > 0){
						//System.out.println("in if r>0");
						r[i]--;
						if(visited[i] == false){
							respt[i] = totalTime - a[i];
							
							
							visited[i] = true;
						}
						w[i] = w[i] +  totalTime - a[i];
					}
					else{
						//System.out.println("in if r==0");
						w[i]--;
						procOver[i] = 1;
						comp[i] = totalTime;
						
						completion++;
					}
					
				}
				
				//System.out.println(a[i+1]);
				//System.out.println(totalTime + " Process " + i+ " "+ a[i]);
				if(i<9 &&  (procOver[i] == 1 && a[i+1]<totalTime)){
					i++;
				}
				if(i==9 && procOver[i] == 1){
					i++;
				}
				totalTime++;
			}
		}
        
        //Print wait time, response time and turn around time
        System.out.println("\nWait Time:");
        float waitSum = 0;
		for(int i=0; i<10;i++){
			System.out.print(w[i] + " ");
			waitSum = waitSum + w[i];
		}
		System.out.println("\nResponse Time:");
		float respSum = 0;
		for(int i=0; i<10;i++){
			System.out.print(respt[i] + " ");
			respSum = respSum + respt[i];
		}
		//Calculate turn around time
		float ttSum = 0;
		int tt[] = new int[10];
		System.out.println("\nTurn Around Time:");
		for (int i = 0; i < 10; i++){
            tt[i] = w[i] - proc[i].getRunTm(); 
            System.out.print(tt[i] + " ");
            ttSum = ttSum + tt[i];
		}
		//Print average response, wait and turn around time
		System.out.println("\nAverage TurnAround Time = " + (ttSum/10));
		System.out.println("Average Response Time = " + (respSum/10));
		System.out.println("Average Wait Time = " + (waitSum/10));
  
	}
	public static void HPFNonPreemptive(){
		System.out.println("In HPF Non-preemptive:");
		RandomNumberGen rng = new RandomNumberGen();
		Process proc1[] = new Process[10];
		proc1 = rng.randomNumPriority();

		ArrayList<Process> pList = new ArrayList<Process>(Arrays.asList(proc1));
		System.out.println("Before sorting: " + Arrays.toString(proc1));
		Collections.sort(pList, new PrioritySorter());
		Process [] proc = pList.toArray(new Process[pList.size()]);
		System.out.println("After sorting: " + Arrays.toString(proc));
		calculate(proc);
		
	}
	public static void HPFPreemptive(){
		System.out.println("In HPF Preemptive:");
		RandomNumberGen rng = new RandomNumberGen();
		Process proc[] = new Process[10];
		proc = rng.randomNumPriority();

		ArrayList<Process> pList = new ArrayList<Process>(Arrays.asList(proc));
		System.out.println("Before sorting: " + Arrays.toString(proc));
		Collections.sort(pList, new PrioritySorter());
		Process [] procc = pList.toArray(new Process[pList.size()]);
		System.out.println("After sorting: " + Arrays.toString(procc));
		int p1=0, p2=0, p3=0, p4=0;
		for(int i=0; i<10; i++){
			if(procc[i].getPriority() == 1){
				p1++;
			}
			if(procc[i].getPriority() == 2){
				p2++;
			}
			if(procc[i].getPriority() == 3){
				p3++;
			}
			if(procc[i].getPriority() == 4){
				p4++;
			}
		}
		
		Process proc1[] = new Process[p1];
		Process proc2[] = new Process[p2];
		Process proc3[] = new Process[p3];
		Process proc4[] = new Process[p4];
		int p1c=0, p2c=0, p3c=0, p4c=0;
		
		for(int i=0; i<10; i++){
			if(procc[i].getPriority() == 1){
				proc1[p1c] = procc[i];
				p1c++;
			}
			if(procc[i].getPriority() == 2){
				proc2[p2c] = procc[i];
				p2c++;
			}
			if(procc[i].getPriority() == 3){
				proc3[p3c] = procc[i];
				p3c++;
			}
			if(procc[i].getPriority() == 4){
				proc4[p4c] = procc[i];
				p4c++;
			}
		}
		Stat s1=new Stat();
		Stat s2=new Stat();
		Stat s3=new Stat();
		Stat s4=new Stat();
		System.out.println("P1: " + Arrays.toString(proc1));
		if(proc1.length!=0){
			s1 = priority(proc1, p1c);
		}
		System.out.println("P2: " + Arrays.toString(proc2));
		if(proc2.length!=0){
		s2 = priority(proc2, p2c);
		}
		System.out.println("P3: " + Arrays.toString(proc3));
		if(proc3.length!=0){
		s3 = priority(proc3, p3c);
		}
		System.out.println("P4: " + Arrays.toString(proc4));
		if(proc4.length!=0){
		s4 = priority(proc4, p4c);
		}
		//Print average response, wait and turn around time
		float ttSum = s1.getTurnArountTm()+s2.getTurnArountTm()+s3.getTurnArountTm()+s4.getTurnArountTm();
		float respSum = s1.getResponseTime()+s2.getResponseTime()+s3.getResponseTime()+s4.getResponseTime();
		float waitSum = s1.getWaitTime()+s2.getWaitTime()+s3.getWaitTime()+s4.getWaitTime();
		System.out.println("\nAverage TurnAround Time = " + (ttSum/4));
		System.out.println("Average Response Time = " + (respSum/4));
		System.out.println("Average Wait Time = " + (waitSum/4));
		
		
		
	}
	public static Stat priority(Process proc[], int p1c){
		//System.out.println("Before sorting: " + Arrays.toString(proc));
		Arrays.sort(proc);
		//System.out.println("After sorting: " + Arrays.toString(proc));
		
		int w[] = new int[p1c];
		int comp[] = new int[p1c];
		int respt[] = new int[p1c];
		boolean visited[] = new boolean[p1c];
		
		//Make copy of run time and arrival time
		int a[] = new int[p1c];
		int r[] = new int[p1c];
		for(int i=0; i<p1c; i++){
			a[i] = proc[i].getArrivalTm();
			r[i] = proc[i].getRunTm();
		}
		
		//Flag indicate completion of process
		int procOver[] = new int[p1c];
		for(int i=0; i<p1c; i++){
			procOver[i] = 0;
			visited[i] = false;
		}
		int completion = 0;
		//Find wait time, response time
		int totalTime = a[0];
		respt[0] = 0;
		w[0] = 0;
		while(completion<p1c){
			//System.out.println("in while");
			for(int i=0; i<p1c; ){
				//System.out.println("in for");
				if(procOver[i] == 0){
					//System.out.println("in if proc =");
					if(r[i] > 0){
						//System.out.println("in if r>0");
						r[i]--;
						if(visited[i] == false){
							respt[i] = totalTime - a[i];
							
							visited[i] = true;
						}
						w[i] = w[i] + totalTime - a[i];
					}
					else{
						//System.out.println("in if r==0");
						w[i]--;
						procOver[i] = 1;
						comp[i] = totalTime;
						
						completion++;
					}
					
				}
				
				if(i<p1c-1 &&  (procOver[i] == 1 && a[i+1]<totalTime)){
					i++;
				}
				if(i==p1c-1 && procOver[i] == 1){
					i++;
				}
				totalTime++;
			}
		}
        
        //Print wait time, response time and turn around time
        //System.out.println("\nWait Time:");
        float waitSum = 0;
		for(int i=0; i<p1c;i++){
			//System.out.print(w[i] + " ");
			waitSum = waitSum + w[i];
		}
		//System.out.println("\nResponse Time:");
		float respSum = 0;
		for(int i=0; i<p1c;i++){
			//System.out.print(respt[i] + " ");
			respSum = respSum + respt[i];
		}
		//Calculate turn around time
		float ttSum = 0;
		int tt[] = new int[p1c];
		//System.out.println("\nTurn Around Time:");
		for (int i = 0; i < p1c; i++){
            tt[i] = w[i] - proc[i].getRunTm(); 
            //System.out.print(tt[i] + " ");
            ttSum = ttSum + tt[i];
		}
		/*//Print average response, wait and turn around time
		System.out.println("\nAverage TurnAround Time = " + (ttSum/p1c));
		System.out.println("Average Response Time = " + (respSum/p1c));
		System.out.println("Average Wait Time = " + (waitSum/p1c));*/
  
		Stat stat = new Stat();
		stat.setTurnArountTm((ttSum/p1c));
		stat.setResponseTime(respSum/p1c);
		stat.setWaitTime(waitSum/p1c);
		return stat;
	}

}
