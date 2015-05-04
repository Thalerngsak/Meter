package com.pwa.common;

public class SubProgressInfo extends ProgressInfo {
	private int weight;
	
	public SubProgressInfo(int weight){
		this.weight = weight;
	}
	
	public SubProgressInfo(int weight,int progress){
		this.weight = weight;
		setProgress(progress);
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}
}
