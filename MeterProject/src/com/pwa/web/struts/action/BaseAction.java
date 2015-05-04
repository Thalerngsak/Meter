package com.pwa.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.taglib.html.Constants;

import com.pwa.common.CUtil;
import com.pwa.common.MessageList;
import com.pwa.web.Paging;

public class BaseAction extends DispatchAction {
	Logger log = Logger.getLogger(BaseAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ActionMessages messages = new ActionMessages();
		MessageList msgList = new MessageList();
		HttpSession session = request.getSession(false);
		Object objForm = (Object) form;

		if (!processToken(mapping, request, session)) {
			// request.getRequestDispatcher("./home.jsp").forward(request, response);
			return mapping.findForward("login");
		}
		String act = request.getParameter("act");

		// implement authenticate page by role
		String role = (String) session.getAttribute("role");
		if (CUtil.isBlank(role)) {
			forward = mapping.findForward("login");
			return forward;
		}

		try {
			return dispatchMethod(mapping, (ActionForm) objForm, request, response, act);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean processToken(ActionMapping mapping, HttpServletRequest request, HttpSession session) {
		String usetoken = request.getParameter("usetoken");
		if ("true".equalsIgnoreCase(usetoken)) {
			if (!isTokenValid(request)) {
				log.debug("invalid token for path=" + request.getPathInfo());
				String formToken = request.getParameter(Constants.TOKEN_KEY);
				log.debug("form token=" + formToken);
				String token = (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY);
				log.debug("session token=" + token);
				return false;
			}
		}
		saveToken(request);  // renew token
		// String token = (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY);
		// System.out.println("out session token=" + token);
		return true;
	}

	/* paging section */

	// overide in sub class mod paging behavior
	public  void modPaging(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		// if want: todo in sub class
	}

	public ActionForward first(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		modPaging(mapping, form, request, response);
		Paging paging = (Paging) request.getSession(false).getAttribute("pageObj");
		paging.first();
		return mapping.findForward("success");
	}

	public ActionForward last(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		modPaging(mapping, form, request, response);
		Paging paging = (Paging) request.getSession(false).getAttribute("pageObj");
		paging.last();
		return mapping.findForward("success");
	}

	public ActionForward next(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		modPaging(mapping, form, request, response);
		Paging paging = (Paging) request.getSession(false).getAttribute("pageObj");
		paging.next();
		return mapping.findForward("success");
	}

	public ActionForward previous(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		modPaging(mapping, form, request, response);
		Paging paging = (Paging) request.getSession(false).getAttribute("pageObj");
		paging.previous();
		return mapping.findForward("success");
	}

	public ActionForward gotoPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		modPaging(mapping, form, request, response);
		Paging paging = (Paging) request.getSession(false).getAttribute("pageObj");
		String pageNo = request.getParameter("page");
		paging.goPage(Integer.parseInt(pageNo) - 1);
		return mapping.findForward("success");
	}

	public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		modPaging(mapping, form, request, response);
		Paging paging = (Paging) request.getSession(false).getAttribute("pageObj");
		int pageNo = paging.getCurrentPage();// getCurrPageBase(objForm);
		if (pageNo >= 0)
			paging.goPage(pageNo);

		return mapping.findForward("success");
	}
}
