package com.pwa.interfaces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.xmlbeans.XmlOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.tsc.ximport.ImportDocument;
import th.co.tsc.ximport.ImportDocument.Import.Item;

import com.pwa.data.DBHelper;

public class ImportProcess {
	static Logger log = LoggerFactory.getLogger(ImportProcess.class);

	public String prefixName;

	public String pathStr = "input"; // hardcode but must get from main config

	public void ximport() throws Exception {

		ImportDocument doc = ImportProcess.getXmlDocument("ATM_LC"); // build rule

		preupdate(doc);

		importFiles(doc);

		postupdate(doc);

	}

	private void postupdate(ImportDocument doc) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		String className = doc.getImport().getPostProcess();

		Object o = Class.forName(className).newInstance();

	}

	private void preupdate(ImportDocument doc) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		String className = doc.getImport().getPreProcess();

		Object o = Class.forName(className).newInstance();

	}

	private void importFiles(ImportDocument doc) throws Exception, SQLException {
		String prepareInsertStr = getPrepareInsertStr(doc);
		prefixName = doc.getImport().getFileNamePrefix();

		// list file in dir start with prefixName
		File inputDir = new File(pathStr);
		File[] inputFiles = inputDir.listFiles(new FilenameFilter() {
			public boolean accept(File file, String stName) {
				stName = stName.toUpperCase();
				boolean i = stName.startsWith(prefixName);
				if (i == false)
					return false;
				else {
					return (stName.startsWith(stName));
				}
			}
		});

		log.debug("Number Of Files=" + inputFiles.length);

		for (int i = 0; i < inputFiles.length; i++) {
			log.debug((i + 1) + ". Processing " + inputFiles[i].getAbsolutePath());
			importFile(inputFiles[i], doc, prepareInsertStr);
		}
	}

	private void importFile(File inputFile, ImportDocument doc, String prepareInsertStr) throws Exception, SQLException {
		// String inFileName = inputDir.getAbsolutePath() + "/" +
		// "TSC_SET_20070920";
		// File inputFile = new File(inFileName);

		Connection conn = null;

		try {
			conn = DBHelper.getDBConnection();

			parseFileToTable(doc, prepareInsertStr, inputFile, conn);
			moveFileToBAK(inputFile);

			log.debug("Commit Transaction");
			conn.commit();

		} catch (Exception e) {
			log.debug("Rollback Transaction");
			conn.rollback();
			e.printStackTrace();
		} finally {
			DBHelper.close(conn);
		}
	}

	private void parseFileToTable(ImportDocument doc, String iStr, File inputFile, Connection conn)
			throws FileNotFoundException, IOException, Exception {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String line = null;// read by line
			while ((line = br.readLine()) != null) {

				

					ArrayList al = parseLine(doc, line, conn); // substring
					// to get
					// String[]
					// check duplicaete
					// insert to define table
					insert(al, iStr, conn);
				
			}

		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	private void moveFileToBAK(File f) throws Exception {
		File desDir = new File(f.getParent() + "/bak/");
		if (!desDir.exists()) {
			boolean isMKSuccess = desDir.mkdirs();
			if (isMKSuccess) {
				log.debug(desDir.getAbsolutePath() + " Created.");
			} else {
				throw new Exception("Can't create " + desDir.getAbsolutePath());
			}
		}

		String desName = desDir.getAbsolutePath() + "/" + f.getName();
		File f2 = new File(desName);
		boolean isRnSuccess = f.renameTo(f2); // move file to bak

		if (isRnSuccess) {
			log.debug("move to " + desDir.getAbsolutePath() + " success ");
		} else {
			throw new Exception("can't move file to " + f2.getAbsolutePath());
		}
	}

	private void insert(ArrayList al, String sql, Connection conn) throws Exception {

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);

			for (int i = 0; i < al.size(); i++) {
				ps.setObject(i + 1, al.get(i));
			}
			ps.executeUpdate();
		} finally {
			DBHelper.close(ps);
		}

	}

	private ArrayList parseLine(ImportDocument doc, String line, Connection conn) throws Exception {
		ArrayList<String> r = new ArrayList<String>();
		Item[] items = doc.getImport().getItemArray();

		for (int i = 0; i < items.length; i++) {

			if (items[i].getDataType().equals("pk")) {
				String pk = getPK(conn); // getpk
				r.add(pk);

			} else {

				int start = items[i].getStart() - 1;
				int end = items[i].getSize() + start;

				String col = line.substring(start, end); // cut column
				if (i != items.length - 1) {
					// r += col + ",";
				}
				r.add(col);
			}

		}

		return r;
	}

	private String getPrepareInsertStr(ImportDocument doc) throws Exception {
		Item[] items = doc.getImport().getItemArray();
		String str = "INSERT INTO " + doc.getImport().getTableId() + "(";

		for (int i = 0; i < items.length; i++) {
			str += items[i].getColumn();
			if (i != items.length - 1) {
				str += ", ";
			}
		}

		str += " )VALUES ("; // chg here
		for (int i = 0; i < items.length; i++) {
			str += "?";
			if (i != items.length - 1) {
				str += ", ";
			}
		}

		str += ")";

		return str;
	}

	private String getPK(Connection conn) throws Exception {
		String sql = "select nextval('ctrl_seq')";
		String r;
		r = DBHelper.exeQueryGetString(sql, conn);
		return r;
	}

	static ImportDocument getXmlDocument(String nameSuffix) throws Exception {
		File f = new File("pattern/Import." + nameSuffix + ".xml");
		log.debug("parsing document: " + f.getAbsolutePath());
		ImportDocument doc = ImportDocument.Factory.parse(f);

		ArrayList errors = new ArrayList();
		XmlOptions opts = new XmlOptions();
		opts.setErrorListener(errors);

		if (doc.validate(opts)) {
			log.debug("document is valid.");

		} else {
			log.debug("document is invalid!");

			Iterator iter = errors.iterator();
			while (iter.hasNext()) {
				log.debug(">> " + iter.next());
			}
			throw new Exception("Document Invalid");
		}
		return doc;
	}

	public static void main(String[] args) throws Exception {
		new ImportProcess().ximport();
	}

}
