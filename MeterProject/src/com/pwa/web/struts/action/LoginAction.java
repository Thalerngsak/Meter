/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.pwa.web.struts.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.pwa.common.CUtil;
import com.pwa.common.MessageList;
import com.pwa.common.exception.CMException;
import com.pwa.data.DBHelper;
import com.pwa.web.ScreenMsgUtil;
import com.pwa.web.struts.form.LoginForm;

public class LoginAction extends DispatchAction {
	Logger log = Logger.getLogger(LoginAction.class);

	public ActionForward main(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("enter ");		
		return mapping.findForward("main");
	}

	public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String branch = (String)session.getAttribute("br");
		LoginForm f = (LoginForm) form;
		log.debug("enter ");
		MessageList msgList = new MessageList();
		
		String name = f.getUsername();
		String password = f.getPassword();
		String role = null;
		try {

			if (CUtil.isBlank(name) || CUtil.isBlank(password)) { // validate
				throw new CMException("E", "กรุณากรอกชื่อผู้ใช้และรหัสผ่าน");
			}
//			if(branch != null){
//				branch = branch.substring(5,branch.length());
//				System.out.println(branch);
//				String n = name.substring(name.length()-2,name.length());
//				System.out.println(n);
//				if(!n.equals(branch)){
//					throw new CMException("E", "ชื่อผู้ใช้ไม่มีในสาขา "+branch +" "+ n);
//				}
//			}

			TreeMap c = new TreeMap();
			name = CUtil.getNullIfEmpty(name);
			password = CUtil.getNullIfEmpty(password);
			DBHelper.putToCriIfNotNull(c, "name", name);
			DBHelper.putToCriIfNotNull(c, "password", password);

			// connect db to verify acc
			role = getUserRole(c);
			log.debug("role="+role);
			if (CUtil.isBlank(role)) {
				msgList.addMessage("E", "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง" );
			}
		} catch (Exception e) {
			msgList.addMessage(e);
		}

		ActionForward forward = null;
		if (!msgList.isContainError()) {
			session.setAttribute("role", role);
			saveToken(request); // first save
			forward = mapping.findForward("success");
		} else {
			forward = mapping.findForward("main");
		}

		saveMessages(request, ScreenMsgUtil.getMsgsToScreen(msgList));

		return forward;
	}

	private String getUserRole(TreeMap c) throws Exception {
		String r = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select role from user " + DBHelper.getConStr(c);
		try {
			conn = DBHelper.getDBConnection();
			ps = conn.prepareStatement(sql);
			DBHelper.setParam(ps, c);
			rs = ps.executeQuery();
			if (rs.next()) {
				r = rs.getString("role");
			}
		} finally {
			DBHelper.close(conn);
		}
		return r;
	}
}