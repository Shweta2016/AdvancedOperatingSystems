********************* Project: Process Scheduling ***************

Problem Statement:

Write a Java or C program that performs runs of the following process scheduling algorithms:
	 1.First-come first-served (FCFS) [non-preemptive]
	 2.Shortest job first (SJF) [non-preemptive]
	 3.Shortest remaining time (SRT) [preemptive]
	 4.Round robin (RR) [preemptive]
	 5.Highest priority first (HPF) [both non-preemptive and preemptive]

Run each algorithm for 100 quanta (time slices), labeled 0 through 99. Before each run, generate a set of simulated processes. For each simulated process, randomly generate:
	 1. An arrival time: a float value from 0 through 99 (measured in quanta).
	 2. An expected total run time: a float value from 0.1 through 10 quanta.
	 3. A priority: integer 1, 2, 3, or 4 (1 is highest)

______________________________________________________________________
Compile and Execute Instructions:
	Compile and run: RandomNumberGen.java