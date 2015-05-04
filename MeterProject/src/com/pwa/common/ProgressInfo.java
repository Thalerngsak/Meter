package com.pwa.common;

public class ProgressInfo {
	private int progress = 4; // fake for see progress before enter in action class
	private String status= "start";
	public ProgressInfo() {
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public int getProgress() {
		return progress;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
