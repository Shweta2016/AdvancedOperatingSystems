package com.advos;

import java.util.Comparator;

public class ShortestJobSorter implements Comparator<Process>{

	@Override
	public int compare(Process p1, Process p2) {
		// TODO Auto-generated method stub
		return p1.getRunTm()-p2.getRunTm();
	}
	
}
