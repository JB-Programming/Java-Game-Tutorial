package com.JBProgramming.someGame.foundation;

public final class LoadProcess {
	
	public final Runnable r;
	public final double priority;
	
	public LoadProcess(Runnable r, double priority) {
		this.r = r;
		this.priority = priority;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return "Priority = " + priority;
	}

}
