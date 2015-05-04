/* Licence:
 *   Use this however/wherever you like, just don't blame me if it breaks anything.
 *
 * Credit:
 *   If you're nice, you'll leave this bit:
 *
 *   Class by Pierre-Alexandre Losson -- http://www.telio.be/blog
 *   email : plosson@users.sourceforge.net
 */
/*
 *  Changed for Part 2, by Ken Cochrane
 *  http://KenCochrane.net , http://CampRate.com , http://PopcornMonsters.com
 */
package com.pwa.web.dwr;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContextFactory;

import com.pwa.common.ProgressInfo;
import com.pwa.common.ProgressManager;

/**
 * Created by IntelliJ IDEA.
 * 
 * @author Original : plosson on 06-janv.-2006 12:19:08 - Last modified by $Author: vde $ on $Date: 2004/11/26 22:43:57 $
 * @version 1.0 - Rev. $Revision: 1.2 $
 */
public class ProgressMonitor {
	Logger log = Logger.getLogger(this.getClass());

	public ProgressInfo getProgressInfo() {
		HttpServletRequest req = WebContextFactory.get().getHttpServletRequest();
		if (req.getSession().getAttribute("progressManager") != null) {
			//log.debug("return progress from session ");
			ProgressInfo p =((ProgressManager) req.getSession().getAttribute("progressManager")).getProgressInfo();
			log.debug("progress="+p.getProgress());
			return p;
		} else {
			log.debug("return new progress(Web server not set in session (may be asked before reach action class)!!)");
			ProgressInfo p = new ProgressInfo();			
			return p;
		}

	}
}
