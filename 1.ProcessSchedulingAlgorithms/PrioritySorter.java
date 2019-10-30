package com.advos;

import java.util.Comparator;

public class PrioritySorter implements Comparator<Process> {

	@Override
	public int compare(Process o1, Process o2) {
		// TODO Auto-generated method stub
		return o1.getPriority() - o2.getPriority();
	}

}
