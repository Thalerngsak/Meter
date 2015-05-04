package com.test;

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
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Properties;

public class ReadWriteFile {

	public static void main(String[] args) throws Exception {

		// defualt();
		//utf8();

		printEnv();
	}

	private static void printEnv() {
		Properties p = System.getProperties();
		Enumeration k = p.keys();
		while (k.hasMoreElements()) {
			String key = (String) k.nextElement();
			System.out.println(key + "=" + p.getProperty(key));

		}
	}

	private static void utf8() throws UnsupportedEncodingException, FileNotFoundException, IOException {
		File file = new File("c:/b.txt");
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"));
		// String s = "\u0e01";
		String s ="";
		System.out.println(s);
		out.write(s);
		out.close();
	}

	private static void defualt() {
		try {
			// Create file
			FileWriter fstream = new FileWriter("c:/b.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			String s = "";
			System.out.println(s);
			out.write(s);
			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static String  readfile() {
		String r =null;
		try {
	
			FileInputStream fstream = new FileInputStream("c:/a.txt");
			DataInputStream in = new DataInputStream(fstream);
			//BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				System.out.println(strLine);
				if(strLine.equals("ก")){
					System.out.println("ok");
				}
				r = strLine;
			}
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
		return r;
	}

}
