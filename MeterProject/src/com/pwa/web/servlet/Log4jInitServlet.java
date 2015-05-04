package com.pwa.web.servlet;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.pwa.common.Env;
import com.pwa.common.exception.CMException;

public class Log4jInitServlet extends HttpServlet {
	Logger log = Logger.getLogger(Log4jInitServlet.class);

	/**
	 * Constructor of the object.
	 */
	public Log4jInitServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		System.out.println("Yes I re-config log file location in runtime");
		Properties props = new Properties();
		try {
			InputStream configStream = getClass().getResourceAsStream("/log4j.properties");
			props.load(configStream);
			configStream.close();

			String logFilename = Env.get("meter.datadir") + "/meterWeb.log";
			System.out.println("if not exist create parent dir of " + logFilename);
			if (!new File(logFilename).getParentFile().exists()) {
				boolean success = new File(logFilename).getParentFile().mkdirs(); // create dir if not exist
				if (!success) {
					throw new CMException("E", "Can't cre dir for log");
				}
			}
			props.setProperty("log4j.appender.R.File", logFilename);
			LogManager.resetConfiguration();
			PropertyConfigurator.configure(props);

			log.debug("log is " + Env.get("meter.datadir") + "/meterWeb.log");
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("System Exit (1)!!!!!");
			//System.exit(1);
		}

	}

}
