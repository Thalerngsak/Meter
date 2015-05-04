package com.pwa.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

import org.apache.log4j.Logger;

import com.pwa.common.warper.FileBufferedReader;

public class FileUtil {
	public static Logger log = Logger.getLogger(FileUtil.class);

	/**
	 * Wrapper for easy close
	 * 
	 * @throws Exception
	 * 
	 */
	public static BufferedReader getBufferReader(String filename) throws Exception {
		BufferedReader br = null;
		try {
			if(new File(filename).exists()){
				FileInputStream fs = new FileInputStream(filename);
				DataInputStream din = new DataInputStream(fs);
				InputStreamReader isr = new InputStreamReader(din, Env.get("file.encoding")); // fix charset
				//log.debug("Use Encoding=" + isr.getEncoding());
				br = new FileBufferedReader(isr, din, fs);
			}
		} catch (Exception e) {
			close(br);
			throw e;
		}
		return br;
	}

	public static void close(BufferedReader br) throws IOException {
		if (br != null) {
			br.close();
		}
	}

	public static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();

	public static void writeFile(String out, String fileName, boolean append) throws Exception {
		PrintWriter pOut = null;

		try {
			// System.out.println(out);
			FileOutputStream fo = new FileOutputStream(fileName, true);
			OutputStreamWriter ow = new OutputStreamWriter(fo, Env.get("file.encoding")); // fix charset

			// debug once
			String debuged = (String) threadLocal.get();
			if (debuged == null) {
				log.debug("Use Encoding=" + ow.getEncoding());
				threadLocal.set("debuged");
			}
			pOut = new PrintWriter(new BufferedWriter(ow));
			pOut.println(out);
		} finally {
			if (pOut != null) {
				pOut.close();
			}
		}
	}

	/**
	 * detail.txt => detail_[timestamp].txt
	 * 
	 */
	public static String changeFileName(String fileName) throws FileNotFoundException, IOException {
		String r = "";
		fileName = fileName.replaceAll(".txt", "");
		Date d = new Date(System.currentTimeMillis());
		String time = DateUtil.formatToDateTime(d);
		r = fileName + "_" + time;
		r += ".txt";
		return r;
	}

	public static File createFile(String fileName, byte[] fileData) throws FileNotFoundException, IOException {
		createParentDir(fileName);
		File destFile = new File(fileName);
		log.debug("create file =" + destFile.getAbsolutePath());
		FileOutputStream fo = null;
		try {
			fo = new FileOutputStream(destFile);
			fo.write(fileData);
		} finally {
			fo.close();
		}

		return destFile;
	}
	public static File createBackupFile(String source,String dest) throws IOException 
	{
		if(new File(source).exists()){
			FileInputStream in = new FileInputStream(source);
			createParentDir(dest);
			File destFile = new File(dest);
			log.debug("create file =" + destFile.getAbsolutePath());
			FileOutputStream fo = new FileOutputStream(destFile);
			byte[] buf = new byte[4 * 1024];  // 4K buffer
			int bytesRead;
			try {
				while ((bytesRead = in.read(buf)) != -1)
				{
					fo.write(buf, 0, bytesRead);
				}
				} finally {
					fo.close();
					in.close();
				}
			return destFile;
		}
		return null;
	}
	public static void createFile(String fileName) throws Exception {
		PrintWriter pOut = null;
		createParentDir(fileName);
		log.debug("create file=" + fileName);
		try {
			pOut = new PrintWriter(new BufferedWriter(new FileWriter(fileName, false)));
			// pOut.println(out);
		} finally {
			if (pOut != null) {
				pOut.close();
			}
		}
	}

	public static void createDir(String dirName) {
		// create dir
		File f = new File(dirName);
		File dir = new File(f.getAbsolutePath());
		if (!dir.exists()) {
			log.debug("create dir=" + dir);
			dir.mkdirs();
		}
	}
	public static void deleteDir(String dirName) {
		// create dir
		File f = new File(dirName);
		File dir = new File(f.getAbsolutePath());
		if (dir.exists()) {
			log.debug("delete file=" + dir);
			dir.delete();
		}
	}
	public static void createParentDir(String fileName) {
		// create dir
		File f = new File(fileName);
		f = f.getParentFile();
		createDir(f.getAbsolutePath());
	}

}
