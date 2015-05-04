/*
 * Created on 15 Ê.¤. 2550
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.pwa.web;

/**
 * @author Tatae
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.util.ArrayList;

/**
 * @author Tatae
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class Paging {
	private int pageSize = 10;
	private int currentPage;
	private int lastPage;
	private boolean isEmpty;
	private ArrayList pageList;
	protected ArrayList allList;
	
	private Boolean hasNext;
	private Boolean hasPrevious;
	private Boolean hasFirst;
	private Boolean hasLast;
	
	private Boolean hasAdd = new Boolean(true);
	private Boolean hasDelete = new Boolean(true);
	private Boolean hasRefresh = new Boolean(true);
	private Boolean hasPrint = new Boolean(true);
	
	private  int listSize; // list size;
	
	public abstract  void load();
	
	// will do check max size with current
	public void refresh(){
		
	}
	
	
	public void first(){
		currentPage = 0;
		lastPage = getMaxPage();
		setCurrentPage(currentPage); 
		pageList = getResultListOfPage();
		hasFirst = new Boolean(false);
		hasPrevious = new Boolean(false);
		
		if (lastPage==1 || currentPage==lastPage) {		// Fixed by JSTL  (31/AUG/07)
			hasLast = new Boolean(false);
			hasNext = new Boolean(false);
		} else {
			hasLast = new Boolean(true);
			hasNext = new Boolean(true);
		}
		
		if(lastPage==0){
			currentPage = currentPage -1;
		}
		
	}
	public void previous(){
		int	currentPage =  getCurrentPage()-1;
		lastPage = getMaxPage();
		if(currentPage < 0){
			currentPage = 0;
			setCurrentPage(currentPage);
			hasFirst = new Boolean(false);
		}
		else{
			setCurrentPage(currentPage);
		}
		ArrayList listOfPage = new ArrayList();
		ArrayList testList = new ArrayList();
		try{
			pageList = getResultListOfPage();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			hasPrevious = new Boolean(false);
		}
		// check if it has next page
		if(currentPage == 0){
			hasPrevious = new Boolean(false);
			hasFirst = new Boolean(false);
		}
		hasNext = new Boolean(true);
		hasLast = new Boolean(true);
	}
	public void next(){
		int currentPage = getCurrentPage()+1;
		lastPage = getMaxPage();
		if(currentPage>getMaxPage()-1){
			setCurrentPage(getMaxPage()-1);
			currentPage = getMaxPage()-1;
			hasLast = new Boolean(false);
		}
		else{
			setCurrentPage(currentPage);
		}
		ArrayList listOfPage = new ArrayList();
		ArrayList testList = new ArrayList();
		try{
			pageList = getResultListOfPage();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			hasNext = new Boolean(false);
		}
		// check if it has next page
		int countOfAllRow = allList.size();
		if(currentPage == getMaxPage()){
			hasNext = new Boolean(false);
			hasLast = new Boolean(false);
		}
		hasPrevious = new Boolean(true);
		hasFirst = new Boolean(true);
		
		
		++currentPage;										// Fixed by JSTL   (31/AUG/07)
		if (lastPage==1 || currentPage==lastPage) {		
			hasLast = new Boolean(false);
			hasNext = new Boolean(false);
		} else {
			hasLast = new Boolean(true);
			hasNext = new Boolean(true);
		}
		
	}
	public void last(){
		int currentPage = getMaxPage()-1;
		lastPage = getMaxPage();
		setCurrentPage(currentPage);
		ArrayList listOfPage = new ArrayList();
		pageList = getResultListOfPage();
		hasLast = new Boolean(false);
		hasNext = new Boolean(false);
		hasFirst = new Boolean(true);
		hasPrevious = new Boolean(true);
	}
	public void goPage(int goPage){
		int currentPage = goPage;
		lastPage = getMaxPage();
		if(currentPage>getMaxPage()-1){
			setCurrentPage(getMaxPage()-1);
			currentPage = getMaxPage()-1;
			hasLast = new Boolean(false);
		}
		else{
			setCurrentPage(currentPage);
		}
		ArrayList listOfPage = new ArrayList();
		ArrayList testList = new ArrayList();
		try{
			pageList = getResultListOfPage();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			hasNext = new Boolean(false);
		}
		// check if it has next page	
//		int countOfAllRow = allList.size();
//		if(currentPage == getMaxPage()){
//			hasNext = new Boolean(false);
//			hasLast = new Boolean(false);
//		}
//		if(currentPage != getMaxPage()){
//			hasNext = new Boolean(true);
//			hasLast = new Boolean(true);
//		}
//		if(currentPage == 0){
//			hasPrevious = new Boolean(false);
//			hasFirst = new Boolean(false);
//		}
//		if(currentPage != 0){
//			hasPrevious = new Boolean(true);
//			hasFirst = new Boolean(true);
//		}	
															// Fixed by JSTL  (31/AUG/07)
		++currentPage;
		if (currentPage <= 1) {								// FIRST & PREV  button
			hasPrevious = new Boolean(false);
			hasFirst = new Boolean(false);
		} else {
			hasPrevious = new Boolean(true);
			hasFirst = new Boolean(true);
		}
		if (lastPage==0 || currentPage==lastPage) {			// NEXT & LAST  button
			hasNext = new Boolean(false);
			hasLast = new Boolean(false);
		} else {
			hasNext = new Boolean(true);
			hasLast = new Boolean(true);
		}

	}
	
	
	/**
	 * @return Returns the hasAdd.
	 */
	public Boolean getHasAdd() {
		return hasAdd;
	}
	/**
	 * @param hasAdd The hasAdd to set.
	 */
	public void setHasAdd(Boolean hasAdd) {
		this.hasAdd = hasAdd;
	}
	/**
	 * @return Returns the hasDelete.
	 */
	public Boolean getHasDelete() {
		return hasDelete;
	}
	/**
	 * @param hasDelete The hasDelete to set.
	 */
	public void setHasDelete(Boolean hasDelete) {
		this.hasDelete = hasDelete;
	}
	/**
	 * @return Returns the hasPrint.
	 */
	public Boolean getHasPrint() {
		return hasPrint;
	}
	/**
	 * @param hasPrint The hasPrint to set.
	 */
	public void setHasPrint(Boolean hasPrint) {
		this.hasPrint = hasPrint;
	}
	/**
	 * @return Returns the hasRefresh.
	 */
	public Boolean getHasRefresh() {
		return hasRefresh;
	}
	/**
	 * @param hasRefresh The hasRefresh to set.
	 */
	public void setHasRefresh(Boolean hasRefresh) {
		this.hasRefresh = hasRefresh;
	}
	/**
	 * @param hasFirst The hasFirst to set.
	 */
	public void setHasFirst(Boolean hasFirst) {
		this.hasFirst = hasFirst;
	}
	/**
	 * @param hasLast The hasLast to set.
	 */
	public void setHasLast(Boolean hasLast) {
		this.hasLast = hasLast;
	}
	/**
	 * @param hasNext The hasNext to set.
	 */
	public void setHasNext(Boolean hasNext) {
		this.hasNext = hasNext;
	}
	/**
	 * @param hasPrevious The hasPrevious to set.
	 */
	public void setHasPrevious(Boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}
	public ArrayList getAllList() {
		return allList;
	}
	
	public void setAllList(ArrayList allList) {
		this.allList = allList;
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}
	
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	public ArrayList getPageList() {
		return pageList;
	}
	
	public void setPageList(ArrayList pageList) {
		this.pageList = pageList;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getLastPage() {
		return lastPage;
	}
	
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	
	private ArrayList getResultListOfPage() {
		ArrayList resutlListofAll = new ArrayList();
		ArrayList resultListofPage = new ArrayList();
		int currentPage = getCurrentPage();
		resutlListofAll = getAllList();
		// check for navigator 
		if(resutlListofAll.size() > pageSize){
			hasNext = new Boolean(true);
			hasLast = new Boolean(true);
			int max = currentPage * pageSize + pageSize;
			if(max  >= allList.size()){
				resultListofPage = new ArrayList(resutlListofAll.subList(currentPage * pageSize, allList.size()));
			}
			else{
				resultListofPage = new ArrayList(resutlListofAll.subList(currentPage * pageSize, max));
			}
		}
		else{
			resultListofPage = resutlListofAll;
		}
		setLastPage(getMaxPage());
		//setCountOfAllRows(getCountOfRows());
		return resultListofPage;
	}
	
	public int getMaxPage(){
		int countOfAllRows = allList.size();
		int maxPage = 0;
		if(countOfAllRows % pageSize >0){
			maxPage = countOfAllRows/pageSize +1;
		}
		else{
			maxPage = countOfAllRows/pageSize;
		}
		return maxPage;
	}
	
	
	
	/**
	 * @return Returns the hasFirst.
	 */
	public Boolean getHasFirst() {
		return hasFirst;
	}
	/**
	 * @return Returns the hasLast.
	 */
	public Boolean getHasLast() {
		return hasLast;
	}
	/**
	 * @return Returns the hasNext.
	 */
	public Boolean getHasNext() {
		return hasNext;
	}
	/**
	 * @return Returns the hasPrevious.
	 */
	public Boolean getHasPrevious() {
		return hasPrevious;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getListSize() {
		return allList.size();
	}
}
