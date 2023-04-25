package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


public class ExcelSheetReader {
	public Set<String> fileReader(String file) throws IOException 
	{				
		FileInputStream  fileInputStream =new FileInputStream(file);
		XSSFWorkbook  workbook = new XSSFWorkbook (fileInputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Set<String> hs = new HashSet<String>();
		
		//USING FOR LOOP
		int rows= sheet.getLastRowNum();
		int cols = sheet.getRow(1).getLastCellNum();
		
		for(int i =0;i<=rows;i++)
		{
			XSSFRow row = sheet.getRow(i);
			
			for(int c =0;c<cols;c++)
			{
				XSSFCell cell = row.getCell(c);
				
				switch(cell.getCellType())
				{
				case STRING: 
					{
						System.out.print(cell.getStringCellValue());
						hs.add(cell.getStringCellValue());
					}
				}
			}
			System.out.println();
		}
		return hs;
		
		
	}


}
