/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.pwa.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2552
 * 
 * XDoclet definition:
 * @struts.action
 * @struts.action-forward name="success" path="login.do?act=main"
 */
public class LogoutAction extends Action {
	static Logger log = Logger.getLogger(LogoutAction.class);
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		log.debug("enter");
		session.invalidate();
		forward = mapping.findForward("success");
		return forward;
	}
}