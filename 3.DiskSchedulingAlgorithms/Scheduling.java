package com.p5;

import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * CLASS NAME : DiskArmScheduling
 * FUNCTIONALITIES : 
 * 		Get input from user
 * 		Get choice of algorithm from user
 * 		Manipulate entered values for different algorithm
 * 		Calculate seek distance for particular algorithm
 * 		Print the reading track and the total distance
 */
public class Scheduling {
	static int totalDistance = 0;         /* Total seek distance for an algorithm */
	static int [] track = new int[]{2055, 1175, 2304, 2700, 513, 1680, 256, 1401, 4922, 3692};    /* Track values entered by user */
	static int [] trackSSTF = new int[10];/* Track values sequence for SSTF algorithm */
	static int [] trackLOOK = new int[10];/* Track values sequence arranged for LOOK algorithm */
	static int [] trackSCAN = new int[11];/* Track values sequence arranged for SCAN algorithm */
	static int [] trackCSCAN = new int[12];/* Track values sequence arranged for C-SCAN algorithm */
	static int [] trackCLOOK = new int[10];/* Track values sequence arranged for C-LOOK algorithm */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scheduling d = new Scheduling();
		int flag = 0;                     /* To check if entered input is integer */
		int caseNum = 0;                  /* Choice entered by user (1,2,3,4) for switch case */
		int index = 0;                    /* Index of track with minimum seek distance according to algorithm */
		int flag1 = 0;                    /* To check if entered choice is integer */
		
		
		int prev = 1723;
		
		
		
		int caseExit = 0;                 /* If case number entered by user is 7.Exit */
		/* Continue till user entered case number = 4 */
		do{
			// Print tracks
			System.out.println("Drive is serving current request at 2255");
			System.out.println("Given tracks are:");
			for(int i=0; i<10; i++){
				System.out.print(track[i] + " ");
			}
			System.out.println("\n");
			System.out.println("***The Disk Arm Scheduling Algorithms**** \n"+
	                "1.First Come First Serve (FCFS) \n"+
	                "2.Shortest Seek Time First (SSTF) \n"+
	                "3.SCAN \n"+
	                "4.LOOK \n" + 
	                "5. C-SCAN \n" + 
	                "6. C-LOOK \n" + 
	                "7. Exit \n");
			Scanner s = new Scanner(System.in);
			System.out.println("Please Enter Your Choice : ");
			flag1 = 0;
			try{
				caseNum = s.nextInt();
				if(caseNum == 4){
					caseExit = 4;
				}
				switch(caseNum){
					/*FCFS algorithm*/
					case 1:
						totalDistance = 0;
						System.out.println("First Come First Serve (FCFS) Algorithm");
						/* Call function to calculate total distance traveled by arm */
						totalDistance = d.seekDistance(track);
						System.out.println("FCFS Algorithm Total Distance = " + totalDistance + "\n");
						break;
					/*SSTF algorithm*/
					case 2:
						totalDistance = 0;
						System.out.println("Shortest Seek Time First (SSTF) Algorithm");
						int [] temp = new int[10];            /* Temporary array that stores track values */
						for(int i=0;i<10;i++){
							temp[i] = track[i];               /* Copy values of track[] to temp[] */
						}
						for(int k=0 ; k<10 ;k++){
							int min = 10000;                    /* Minimum value of distance between two tracks(Initially set to max) */
							int var = 0;                      /* Temporary variable to store distance between two tracks */
							for(int i=0 ; i<10 ; i++){
								/* To find track with minimum distance from 50(Starting track) */
								if(k == 0){
									if(temp[i] > 2255){
										var = temp[i] -2255;
									}
									else{
										var = 2255 - temp[i];
									}
								}
								/* To find track with minimum distance from current track */
								else{
									if(temp[i] > trackSSTF[k-1]){
										var = temp[i] - trackSSTF[k-1];
									}
									else{
										var = trackSSTF[k-1] - temp[i];
									}
								}
								if(var < min){
									min = var;
									index = i;
								}
							}
							/* Save track value found above */
							trackSSTF[k] = temp[index];
							temp[index] = 10000;                /* Set the value to max so that it would never be solution for next loop */
						}
						/* Call function to calculate total distance traveled by arm */
						totalDistance = d.seekDistance(trackSSTF);
						System.out.println("SSTF Algorithm Total Distance = " + totalDistance + "\n");
						break;
					/*SCAN algorithm*/
					case 3:
						System.out.println("SCAN Algorithm");
						//int [] trackSCANtemp = new int[10];
						totalDistance = 0;                   /* Total distance traveled by arm */
						int [] temp2 = new int[10];          /* Temporary array that stores track values */
						int var2 = 0;                         /* Temporary variable to store distance computed */
						int min2 = 10000;                       /*  */
						int j2 = 0;                           /* Index for trackLOOK[] */
						for(int i=0;i<10;i++){
							temp2[i] = track[i];
						}
						Arrays.sort(temp2);                  /* Sort track values */
						/* Find the track that has minimum distance from current track(2255) */
						for(int i=0 ; i<10 ; i++){
							if(temp2[i] > 2255){
								var2 = temp2[i] -2255;
							}
							else{
								var2 = 2255 - temp2[i];
							}
							if(var2 < min2){
								min2 = var2;
								index = i;
							}
						}
						/* Save values smaller than 2255 in decreasing order/Move left */
						if(temp2[index] < 2255){
							for(int i=index; i>=0 ;){
								trackSCAN[j2] = temp2[i];
								i = i-1;
								j2 = j2+1;
							}
							trackSCAN[j2] = 0;
							j2=j2+1;
							if(index !=9){
								for(int i=index+1 ; i<10 ;){
									trackSCAN[j2] = temp2[i];
									i = i+1;
									j2 = j2+1;
								}
							}
							
						}
						/* Save values greater than 2255 in increasing order/Move right */
						else{
							for(int i=index; i<10 ;){
								trackSCAN[j2] = temp2[i];
								i = i+1;
								j2 = j2+1;
							}
							trackSCAN[j2] = 4999;
							j2=j2+1;
							if(index !=0){
								for(int i=index-1 ; i>=0 ;){
									trackSCAN[j2] = temp2[i];
									i = i-1;
									j2 = j2+1;
								}
							}
							
						}
						/* Call function to calculate total distance traveled by arm */
						totalDistance = d.seekDistance(trackSCAN);
						System.out.println("SCAN Algorithm Total Distance = " + totalDistance + "\n");
						break;
					/*LOOK algorithm*/
					case 4:
						System.out.println("LOOK Algorithm");
						totalDistance = 0;                   /* Total distance traveled by arm */
						int [] temp1 = new int[10];          /* Temporary array that stores track values */
						int var = 0;                         /* Temporary variable to store distance computed */
						int min = 10000;                       /*  */
						int j = 0;                           /* Index for trackLOOK[] */
						for(int i=0;i<10;i++){
							temp1[i] = track[i];
						}
						Arrays.sort(temp1);                  /* Sort track values */
						/* Find the track that has minimum distance from current track(2255) */
						for(int i=0 ; i<10 ; i++){
							if(temp1[i] > 2255){
								var = temp1[i] -2255;
							}
							else{
								var = 2255 - temp1[i];
							}
							if(var < min){
								min = var;
								index = i;
							}
						}
						/* Save values smaller than 2255 in decreasing order/Move left */
						if(temp1[index] < 2255){
							for(int i=index; i>=0 ;){
								trackLOOK[j] = temp1[i];
								i = i-1;
								j = j+1;
							}
							if(index !=9){
								for(int i=index+1 ; i<10 ;){
									trackLOOK[j] = temp1[i];
									i = i+1;
									j = j+1;
								}
							}
						}
						/* Save values greater than 2255 in increasing order/Move right */
						else{
							for(int i=index; i<10 ;){
								trackLOOK[j] = temp1[i];
								i = i+1;
								j = j+1;
							}
							if(index !=0){
								for(int i=index-1 ; i>=0 ;){
									trackLOOK[j] = temp1[i];
									i = i-1;
									j = j+1;
								}
							}
						}
						/* Call function to calculate total distance traveled by arm */
						totalDistance = d.seekDistance(trackLOOK);
						System.out.println("LOOK Algorithm Total Distance = " + totalDistance + "\n");
						break;
					/*C-SCAN algorithm*/
					case 5:
						System.out.println("C-SCAN Algorithm");
						totalDistance = 0;                   /* Total distance traveled by arm */
						int [] temp3 = new int[10];          /* Temporary array that stores track values */
						int var3 = 0;                         /* Temporary variable to store distance computed */
						int min3 = 10000;                       /*  */
						int j3 = 0;                           /* Index for trackLOOK[] */
						for(int i=0;i<10;i++){
							temp3[i] = track[i];
						}
						Arrays.sort(temp3);                  /* Sort track values */
						/* Find the track that has minimum distance from current track(2255) */
						for(int i=0 ; i<10 ; i++){
							if(temp3[i] > 2255){
								var3 = temp3[i] -2255;
							}
							else{
								var3 = 2255 - temp3[i];
							}
							if(var3 < min3){
								min3 = var3;
								index = i;
							}
						}
						/* Save values greater than 2255 in increasing order/Move right */
						for(int i=index; i<10 ;){
							trackCSCAN[j3] = temp3[i];
							i = i+1;
							j3 = j3+1;
						}
						trackCSCAN[j3] = 4999;
						j3=j3+1;
						trackCSCAN[j3] = 0;
						j3=j3+1;
						if(index !=0){
							for(int i=0 ; i<=index-1 ;){
								trackCSCAN[j3] = temp3[i];
								i = i+1;
								j3 = j3+1;
							}
						}
						/* Call function to calculate total distance traveled by arm */
						totalDistance = d.seekDistance(trackCSCAN);
						System.out.println("C-SCAN Algorithm Total Distance = " + totalDistance + "\n");
						break;
					/*C-LOOK algorithm*/
					case 6:
						System.out.println("C-LOOK Algorithm");
						totalDistance = 0;                   /* Total distance traveled by arm */
						int [] temp4 = new int[10];          /* Temporary array that stores track values */
						int var4 = 0;                         /* Temporary variable to store distance computed */
						int min4 = 10000;                       /*  */
						int j4 = 0;                           /* Index for trackLOOK[] */
						for(int i=0;i<10;i++){
							temp4[i] = track[i];
						}
						Arrays.sort(temp4);                  /* Sort track values */
						/* Find the track that has minimum distance from current track(2255) */
						for(int i=0 ; i<10 ; i++){
							if(temp4[i] > 2255){
								var4 = temp4[i] -2255;
							}
							else{
								var4 = 2255 - temp4[i];
							}
							if(var4 < min4){
								min4 = var4;
								index = i;
							}
						}
						/* Save values greater than 2255 in increasing order/Move right */
						for(int i=index; i<10 ;){
							trackCLOOK[j4] = temp4[i];
							i = i+1;
							j4 = j4+1;
						}
						
						if(index !=0){
							for(int i=0 ; i<=index-1 ;){
								trackCLOOK[j4] = temp4[i];
								i = i+1;
								j4 = j4+1;
							}
						}
						/* Call function to calculate total distance traveled by arm */
						totalDistance = d.seekDistance(trackCLOOK);
						System.out.println("C-LOOK Algorithm Total Distance = " + totalDistance + "\n");

						break;
					case 7:
						System.exit(0);
					default:
						break;
				}
			}catch(InputMismatchException e){
				System.out.println("Please enter correct input");
				flag1 = 1;
			}
		}while(flag1 == 1 || caseExit != 7);
	}
	
	/*
	 *FUNCTION NAME : seekDistance
	 *
	 *FUNCTIONALITIES : 
	 *		Calculate total distance traveled by the arm for algorithms 
	 *		Input : Optimal array for algorithm with track values
	 *		Output : Total distance traveled by arm for particular algorithm
	 * */
	int seekDistance(int []trackNew){
		int currentTrack = 2255;                       /* Current track value(Given) */
		int totalDistance = 0;                       /* Total distance traveled by arm */
		int tempTrack = 0;                                /* Temporary distance value calculated */
		/* Distance between first track and current track */
		if(trackNew[0] < currentTrack){
			totalDistance = currentTrack - trackNew[0];
			tempTrack = totalDistance;
		}
		else{
			totalDistance = trackNew[0] - currentTrack;
			tempTrack = totalDistance;
		}
		System.out.println("Track\t CylinderMovement");
		System.out.println("2255 \t");
		if(trackNew.length==11){
			/* Distance between two consecutive tracks */
			for(int i=0 ; i<10 ; i++){
				System.out.println(trackNew[i] + "\t" + tempTrack);
				if(trackNew[i] < trackNew[i+1]){
					tempTrack = trackNew[i+1] - trackNew[i];
				}
				else{
					tempTrack = trackNew[i] - trackNew[i+1];
				}
				totalDistance = totalDistance + tempTrack;
			}
			/* Print sequence in which the track must be traversed for particular algorithm */
			System.out.println(trackNew[10] + "\t" + tempTrack);
		}
		else if(trackNew.length==12){
			/* Distance between two consecutive tracks */
			for(int i=0 ; i<11 ; i++){
				System.out.println(trackNew[i] + "\t" + tempTrack);
				if(trackNew[i] < trackNew[i+1]){
					tempTrack = trackNew[i+1] - trackNew[i];
				}
				else{
					tempTrack = trackNew[i] - trackNew[i+1];
				}
				totalDistance = totalDistance + tempTrack;
			}
			/* Print sequence in which the track must be traversed for particular algorithm */
			System.out.println(trackNew[11] + "\t" + tempTrack);
		}
		else{
			/* Distance between two consecutive tracks */
			for(int i=0 ; i<9 ; i++){
				System.out.println(trackNew[i] + "\t" +tempTrack);
				if(trackNew[i] < trackNew[i+1]){
					tempTrack = trackNew[i+1] - trackNew[i];
				}
				else{
					tempTrack = trackNew[i] - trackNew[i+1];
				}
				totalDistance = totalDistance + tempTrack;
			}
			/* Print sequence in which the track must be traversed for particular algorithm */
			System.out.println(trackNew[9] + "\t" + tempTrack);
		}
		/* Returned distance traveled by arm */
		return totalDistance;	
	}
	
}
