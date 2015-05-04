package com.pwa.common;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class ProgressManager {
	static Logger log = Logger.getLogger(ProgressManager.class);
	private static final ThreadLocal<ProgressManager> threadLocal = new ThreadLocal<ProgressManager>();

	private ProgressInfo progressInfo = new ProgressInfo();
	private LinkedHashMap<String, SubProgressInfo> subProgressInfoMap = new LinkedHashMap<String, SubProgressInfo>();
	private ProgressInfo currentSubProgressInfo;

	// util section
	public static void setProgress(int progress) {
		ProgressManager p = (ProgressManager) threadLocal.get();
		if (p != null) {
			p.currentSubProgressInfo.setProgress(progress);
		}
	}

	// instance section
	public static ProgressManager getInstance() {
		ProgressManager p = (ProgressManager) threadLocal.get();
		if (p == null) {
			p = new ProgressManager();
			threadLocal.set(p);
		}
		return p;
	}

	public static void close() {
		threadLocal.set(null);
	}

	private ProgressManager() {
	}

	// logic section
	public void selectCurrentSubProgressInfo(String name) {
		currentSubProgressInfo = subProgressInfoMap.get(name);
	}

	public ProgressInfo getProgressInfo() { // cal subprogress set to progressInfo

		double globalProgress = 0;
		double globalMaxWeight = getGlobalMaxWeight(subProgressInfoMap);
		Iterator<String> it = subProgressInfoMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			SubProgressInfo sub = subProgressInfoMap.get(key);
			globalProgress += sub.getProgress() * (sub.getWeight() / globalMaxWeight);
		}
		progressInfo.setProgress((int) globalProgress);  //set 
		return progressInfo;
	}

	private int getGlobalMaxWeight(Map subProgressInfoMap) {
		int r = 0;
		Iterator<String> it = subProgressInfoMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			SubProgressInfo sub = (SubProgressInfo) subProgressInfoMap.get(key);
			r += sub.getWeight();
		}
		return r;
	}

	public void setProgressInfo(ProgressInfo progressInfo) {
		this.progressInfo = progressInfo;
	}

	public LinkedHashMap<String, SubProgressInfo> getSubProgressInfoMap() {
		return subProgressInfoMap;
	}

	public void setSubProgressInfoMap(LinkedHashMap<String, SubProgressInfo> subProgressInfoMap) {
		this.subProgressInfoMap = subProgressInfoMap;
	}

	public ProgressInfo getCurrentSubProgressInfo() {
		return currentSubProgressInfo;
	}

	public static void main(String[] args) {
		ProgressManager m = ProgressManager.getInstance();
		LinkedHashMap subMap = new LinkedHashMap<String, SubProgressInfo>();
		subMap.put("1", new SubProgressInfo(50));
		subMap.put("2", new SubProgressInfo(50));
		m.setSubProgressInfoMap(subMap);
		System.out.println(m.getProgressInfo().getProgress());
		m.selectCurrentSubProgressInfo("1");
		ProgressManager.setProgress(90);
		m.selectCurrentSubProgressInfo("2");
		ProgressManager.setProgress(100);
		System.out.println(m.getProgressInfo().getProgress());
		ProgressManager.close();

	}

}
