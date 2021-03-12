package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel {
	static Workbook workbook;
	static org.apache.poi.ss.usermodel.Sheet sheet;
	static String n;

	
	public static String excelread(int row, int col, String SheetName) throws EncryptedDocumentException, IOException {
		File file = new File("F:\\Optisol\\SBN.API.ExtentReport\\Excel-Data\\SBN-API-DATA.xlsx");
		FileInputStream fis = new FileInputStream(file);
		workbook = WorkbookFactory.create(fis);
		sheet = workbook.getSheet(SheetName);
		Row Row = sheet.getRow(row);
		Cell Col = Row.getCell(col);
		String data = Col.toString();
		return data;
	}
}
