package com.pwa.web.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.PairValueDesc;

public class BranchList extends ArrayList implements Serializable{

	/**
	 * 
	 */
	public BranchList() {
		super(11);
		add( 0, new PairValueDesc("5531019", "บางคล้า") );
        add( 1, new PairValueDesc("5531020", "พนมสารคราม") );
        add( 2, new PairValueDesc("5531023", "ปากน้ำประแสร์") );
        add( 3, new PairValueDesc("5531025", "ขลุง") );
        add( 4, new PairValueDesc("5531026", "ตราด") );
        add( 5, new PairValueDesc("5531027", "คลองใหญ่") );
        add( 6, new PairValueDesc("5531028", "สระแก้ว") );
        add( 7, new PairValueDesc("5531029", "วัฒนานคร") );
        add( 8, new PairValueDesc("5531030", "อรัญประเทศ") );
        add( 9, new PairValueDesc("5531031", "ปราจีนบุรี") );
        add( 10, new PairValueDesc("5531032", "กบินทร์บุรี") );
	}

	/**
	 * @param c
	 */
	public BranchList(Collection c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param initialCapacity
	 */
	public BranchList(int initialCapacity) {
		super(initialCapacity);
		// TODO Auto-generated constructor stub
	}

}
