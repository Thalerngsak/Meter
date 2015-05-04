package com.pwa.web.view;

import java.util.ArrayList;

import com.pwa.web.Paging;

public class ReportNormalViewPaging extends Paging {

	public ReportNormalViewPaging() {
		setPageSize(20);
	}
	
	@Override
	public void load() {
        ArrayList list = new ArrayList();
        this.allList = list;
        first();
	}
	
	public void load(ArrayList list) {
        this.allList = list;
        first();
	}

	@Override
	public void refresh() {

	}

}
