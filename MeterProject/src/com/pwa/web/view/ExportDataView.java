package com.pwa.web.view;

import com.pwa.data.model.detail.AbstractDetail;

public class ExportDataView extends AbstractDetail {
	
	private String fileName;
	private String path;
	private String rte;
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the rte
	 */
	public String getRte() {
		return rte;
	}
	/**
	 * @param rte the rte to set
	 */
	public void setRte(String rte) {
		this.rte = rte;
	}
	
}
