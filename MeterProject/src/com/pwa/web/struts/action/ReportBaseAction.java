package com.pwa.web.struts.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pwa.common.Meter;
import com.pwa.data.DBHelper;
import com.pwa.web.Paging;
import com.pwa.web.helper.ReportHelper;

public abstract class ReportBaseAction extends BaseAction {
	public static Logger log = Logger.getLogger(ReportBaseAction.class);

	@Override
	/* clear เก็บตกบริษัทที่เกิน 3 วันแล้วยังไม่อ่านมาตร  */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String sql = "delete from detail where  type='1' and (curdate() - trandate > 4)  and readflag='0'";
//		Connection conn = null;
//		try {
//			conn = DBHelper.getDBConnection();
//			Meter m = new Meter();
//			long r = DBHelper.exeUpdateGetNumber(sql, conn);
//			log.debug("deleteOldCompany=" + r + m.took() + ", sql=" + sql);
//			conn.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			conn.rollback();
//		} finally {
//			DBHelper.close(conn);
//		}

		return super.execute(mapping, form, request, response);
	}

	// method to refresh for specificed report
	public abstract void refreshPag(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	// re-search page for fresh data
	public void modPaging(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Paging old_paging = (Paging) session.getAttribute("pageObj"); // temp for current page
		try {
			refreshPag(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Paging paging = (Paging) session.getAttribute("pageObj");
		paging.setCurrentPage(old_paging.getCurrentPage());
		session.setAttribute("pageObj", paging);
	}

}
