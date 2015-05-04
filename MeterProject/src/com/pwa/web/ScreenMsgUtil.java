package com.pwa.web;

import java.util.ArrayList;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.pwa.common.MessageList;
import com.pwa.common.MessageObj;

public class ScreenMsgUtil {

	public static ActionMessages getMsgsToScreen(MessageList msgList) {
		ActionMessages msgs = new ActionMessages();
		if (msgList != null) {

			ArrayList msgV = msgList.getMsgList();
			MessageObj msgObj;
			String msgHTML;
			if (msgV != null && msgV.size() > 0) {
				for (int i = 0; i < msgV.size(); i++) {
					msgHTML = "";
					msgObj = (MessageObj) msgV.get(i);

					if (msgObj.getType().toUpperCase().startsWith("E"))
						msgHTML = "<tr><td align='center'><font class='errormsg'>" + msgObj.getParam() + "</font></td></tr>";
					else if (msgObj.getType().toUpperCase().startsWith("I"))
						msgHTML = "<tr><td align='center'><font class='infomsg'>" + msgObj.getParam() + "</font></td></tr>";
					else if (msgObj.getType().toUpperCase().startsWith("W"))
						msgHTML = "<tr><td align='center'><font class='warningmsg'>" + msgObj.getParam() + "</font></td></tr>";

					msgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("meter.common", msgHTML));
				}
			}
		}
		return msgs;
	}

}
