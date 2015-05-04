package com.pwa.data.impoter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.pwa.common.MessageList;
import com.pwa.data.DBHelper;

public class PlanImporter {
	
	static Logger log = Logger.getLogger(PlanImporter.class);
	
	public static long run(String br,InputStream is,MessageList msgList) throws Exception {
		long r = 0;
		
		r = getInputFileDataList(br,is,msgList); // read file and build to list

		//logCodeName(inputList, msgList);
		//filterReadList(inputList, msgList); // check read flag for update		
		//logRteGrpCount(inputList, msgList); // log rte
		//r = insertDetail(inputList, msgList); // insert to db
		return r;
	}
	public static long getInputFileDataList(String br,InputStream is,MessageList msgList) throws Exception {
		//ArrayList r = new ArrayList();
		//log.debug("getData from file=" + filename);

		///BufferedReader br = null;
		long row = 0;
		try {
			    row = ImportData(br,is,msgList);
				//Detail input = getDetail(type, parts, tranDate);
				//r.add(input);
			//}
		} catch (Exception e) {
			throw e;
		} finally {
			//FileUtil.close(br);
		}
		log.debug("number input in file =" + row );
		return row;
	}

	private static long ImportData (String br,InputStream is,MessageList msgList) throws Exception {
		System.out.println("ImportData "+br);
		Connection conn =   null; 
		Statement stmt =   null; 
		
		try   { 
			conn = DBHelper.getDBConnection(); 
			}   catch (SQLException e1) { 
			e1.printStackTrace (); 
		} 
		long rowNum = 0;
		try {
			 stmt = conn.createStatement();
		      String sql = "delete from head " +
		                   "where br = "+br;
		      System.out.println(sql);
		      stmt.executeUpdate(sql);
				//Get the workbook instance for XLS file 
			    HSSFWorkbook workbook = new HSSFWorkbook(is);
			 
			    //Get first sheet from the workbook
			    HSSFSheet sheet = workbook.getSheetAt(0);
			     
			    //Iterate through each rows from first sheet
			    Iterator<Row> rowIterator = sheet.iterator();
			    while(rowIterator.hasNext()) {
			        Row row = rowIterator.next();
			        //For each row, iterate through each columns
			        Iterator<Cell> cellIterator = row.cellIterator();
			        int column = 0;
			        
			        PreparedStatement pstmInsert = conn.prepareStatement ("insert into head values (?,?,?,?,?,?,?,?,?)");
			        while(cellIterator.hasNext()) {
			            Cell cell = cellIterator.next();
			            switch(cell.getCellType()){
			    		case HSSFCell.CELL_TYPE_STRING:
			    			System.out.print(cell.getStringCellValue() + "\t\t");
			    			pstmInsert.setString (column+1, cell.getStringCellValue().trim());
			    			column = column+1;
			    			break;
			    		case HSSFCell.CELL_TYPE_NUMERIC:
			    			System.out.print(cell.getNumericCellValue() + "\t\t");
			    			Double intValue = Double.valueOf(cell.getNumericCellValue());
			    			pstmInsert.setString (column+1, String.valueOf(intValue.intValue()));
			    			column = column+1;
			    		}
			        }
			        System.out.println("************");
			        pstmInsert.executeUpdate ();
			        rowNum++;
			    }
			    is.close();
			    conn.commit();
			    DBHelper.close(conn);
			    DBHelper.close(stmt);
			} catch (FileNotFoundException e) {
			    e.printStackTrace();
			} catch (IOException e) {
			    e.printStackTrace();
			}

		return rowNum;

	}

}
