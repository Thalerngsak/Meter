package com.pwa.interfaces;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.tsc.export.ExportDocument;
import th.co.tsc.export.ExportDocument.Export.Body;
import th.co.tsc.export.ExportDocument.Export.Head;
import th.co.tsc.export.ExportDocument.Export.Body.Column;
import th.co.tsc.export.ExportDocument.Export.DirecotryName.FileNameConfiguration;
import th.co.tsc.export.ExportDocument.Export.DirecotryName.FileNameConfiguration.Element;
import th.co.tsc.export.ExportDocument.Export.Head.Field;

import com.pwa.common.Env;
import com.pwa.common.FileUtil;
import com.pwa.common.ProgressManager;
import com.pwa.data.DBHelper;

public class ExportProcess {
	private static Logger log = LoggerFactory.getLogger(ExportProcess.class);

	private String absFileName;

	public void export(String nameSuffix) throws Exception {
		Connection conn = null;
		try {
			conn = DBHelper.getDBConnection();
			ExportDocument doc = getExportRule(nameSuffix);
			preupdate(doc, conn);
			ArrayList idList = process(doc, conn); // process gen file

			// postupdate(doc, conn, idList);
			// conn.commit();
		} catch (Exception e) {
			// conn.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DBHelper.close(conn);
		}
	}

	private ExportDocument getExportRule(String nameSuffix) throws Exception {
		ClassLoader cl = getClass().getClassLoader();
		String location = "pattern/Export." + nameSuffix + ".xml";
		URL fileURL = cl.getResource(location);
		log.debug("pattern file: " + fileURL);
		// fileName=URLDecoder.decode(fileName , "UTF-8"); // decode 20% to space
		ExportDocument doc = ExportDocument.Factory.parse(fileURL);

		return doc;
	}

	private void preupdate(ExportDocument doc, Connection conn) {
		String query = doc.getExport().getPreUpdate().getTable().getQuery().trim();
		log.debug(query);
	}

	protected String getBodyQuery() {
		return null;
	}

	private ArrayList process(ExportDocument doc, Connection conn) throws Exception {
		ArrayList recIDList = new ArrayList();

		String query = null;
		query = getBodyQuery(); // let subclass overide
		if (query == null) {
			query = doc.getExport().getBody().getQuery().getStringValue().trim();
		}

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			setPSParam(ps); // let subclass overide
			rs = ps.executeQuery();
			
			ProgressManager.setProgress(50);
			
			createFile(doc); // gen file
			// createHeader(doc); // header
			ArrayList al = createBody(doc, rs); // body
			recIDList.addAll(al);

		} finally {
			DBHelper.closeRsS(ps, rs);
		}

		return recIDList;
	}

	protected void setPSParam(PreparedStatement ps) throws SQLException {

	}

	private void createFile(ExportDocument doc) throws Exception {
		log.debug("ExportDocument = "+doc);
		FileNameConfiguration fc = doc.getExport().getDirecotryName().getFileNameConfiguration();
		log.debug("FileNameConfiguration = "+fc);
		Element[] es = fc.getElementArray();
		String dirName = fc.getDir();
		log.debug("dirName = "+dirName);
		String fileName = "";
		for (int i = 0; i < es.length; i++) {
			if ("constant".equals(es[i].getType())) {
				fileName += es[i].getStringValue();
			}
			if ("SystemTimestamp".equals(es[i].getType())) {
				String format = es[i].getStringValue();
				SimpleDateFormat df = new SimpleDateFormat(format, Locale.ENGLISH);

				String dateStr = df.format(new Date(System.currentTimeMillis()));
				fileName += dateStr;
			}
		}

		absFileName = dirName + "/" + fileName;
		log.debug("absFileName = "+absFileName);
		absFileName = getModifyConfigFileName(absFileName); // let subclass overide
		log.debug("absFileName = "+absFileName);
		FileUtil.createFile(absFileName);

	}

	/**
	 * allow subclass modify file name
	 * 
	 * @param fileName
	 * @return
	 */
	protected String getModifyConfigFileName(String fileName) {
		return fileName;
	}

	private ArrayList<String> createBody(ExportDocument doc, ResultSet rs) throws Exception {
		ArrayList<String> al = new ArrayList<String>();

		Body body = doc.getExport().getBody();
		Column[] cols = body.getColumnArray();
		int running = 0;
		while (rs.next()) {
			++running;

			al.add(""); // fake pk

			String outLine = "";
			for (int i = 0; i < cols.length; i++) {
				String outCol = "";
				String val = cols[i].getStringValue();
				String type = cols[i].getType();
				String align = cols[i].getAlign();
				String pattern = cols[i].getPattern();
				int width = cols[i].getWidth();

				if ("lineCount".equals(type)) { // running
					BigDecimal o = new BigDecimal(running);
					if (pattern != null) {
						DecimalFormat df = new DecimalFormat(pattern);
						outCol = df.format(o);
					} else {
						outCol = o.toString();
					}
				} else {
					Object obj = rs.getObject(val);
					if (obj == null) {
						outCol = "";
					} else { // construct column with rs
						if ("string".equals(type)) { // String
							outCol = rs.getString(val);
						}

						if ("number".equals(type)) {
							BigDecimal o = rs.getBigDecimal(val);
							if (pattern != null && (o.compareTo(new BigDecimal("0.00")) < 0)) {
								DecimalFormat df = new DecimalFormat(pattern);
								outCol = df.format(o);
							} else {
								outCol = o.toString();
							}
						}

						if ("datetime".equals(type)) {
							Timestamp o = rs.getTimestamp(val);

							if (pattern != null) {
								SimpleDateFormat df = new SimpleDateFormat(pattern, Env.LOCALE_TH);
								outCol = df.format(o);
							} else {
								outCol = o.toString();
							}
						}
					}
				}

				if (align == null || "left".equals(align)) { // default
					outCol = BatchUtil.padSpaceLeft(outCol, width);
				} else if ("zeroAtLeft".equals(align)) {
					outCol = BatchUtil.padZeroLeft(outCol, width);
				} else if ("right".equals(type)) {
					outCol = BatchUtil.padSpaceRight(outCol, width);
				} else {
					throw new Exception("Invalid align value: " + align);
				}

				outLine += outCol;
				if (i < cols.length - 1) {
					outLine += doc.getExport().getDelimiter();
				}
			}

			FileUtil.writeFile(outLine, absFileName, true);
		}
		log.debug("size="+al.size());
		return al;
	}

	private void createHeader(ExportDocument doc) throws Exception {
		Head h = doc.getExport().getHead();
		// String del = h.getDelimiter();
		Field[] fs = h.getFieldArray();

		String hStr = "";
		for (int i = 0; i < fs.length; i++) {
			if ("constant".equals(fs[i].getType())) {
				hStr += fs[i].getStringValue();
			} else { // filename

			}

			if (i != fs.length - 1) {
				hStr += "#";
			}
		}

		FileUtil.writeFile(hStr, absFileName, true);
	}

	private void postupdate(ExportDocument doc, Connection conn, ArrayList<String> al) throws Exception {
		String sql = doc.getExport().getPostUpdate().getTable().getQuery().trim();
		String idColumn = doc.getExport().getBody().getQuery().getIdColumn();
		if (idColumn != null) {
			ArrayList<String> strList = getConditionStrList(al); // replace pk range

			for (int i = 0; i < strList.size(); i++) {
				String translateSql = sql.replace("${" + idColumn + "}", strList.get(i));
				log.debug(translateSql);
				DBHelper.exeUpdateGetNumber(translateSql, conn);
			}
		} else {
			log.debug(sql);
			DBHelper.exeUpdateGetNumber(sql, conn);
		}

	}

	private ArrayList<String> getConditionStrList(ArrayList<String> al) {
		Iterator<String> iter = al.iterator();
		String conStr = "";
		int count = 1;
		ArrayList<String> strConList = new ArrayList<String>(); // ex:
		// strConList="(1,2,3,...10)", "(4,5,6)"
		while (iter.hasNext()) {
			String id = iter.next();
			conStr += "'" + id.trim() + "',";
			if (count % 100 == 0) {
				conStr = "(" + conStr.substring(0, (conStr.length() - 1)) + ")";
				strConList.add(conStr);
				conStr = "";
			}
			count++;
		}
		if (conStr.length() > 0) {
			conStr = "(" + conStr.substring(0, (conStr.length() - 1)) + ")";
			strConList.add(conStr);
		}
		return strConList;
	}

	public static void main(String[] args) throws Exception {
		new ExportProcess().export("Data01");
	}
}
