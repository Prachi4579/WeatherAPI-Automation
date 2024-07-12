package utils;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.ss.usermodel.Workbook; 
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReaderUtils{
	private static final Logger logger = LoggerFactory.getLogger(ExcelReaderUtils.class);

	public static Map<String,Map<String,String>> getWeatherAPIData(String excelSheetPath, String sheetName)  {
		
		try {
			Map<String,Map<String,String>> mp  = new HashMap<String,Map<String,String>>();		
			Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(excelSheetPath)));
			Sheet sh = workbook.getSheet(sheetName);

			Row headerRow = sh.getRow(0);
			int columns = headerRow.getLastCellNum();
			List<String> headerValues = new ArrayList<String>();
			for(int i = 0; i < columns ; i++) {
				headerValues.add(headerRow.getCell(i).getStringCellValue());
				
			}
			logger.info(headerValues.toString());
			int totalRows = sh.getLastRowNum();

			for(int i = 1; i <= totalRows; i++) {
				Map<String,String> rowMp = new HashMap<>();
				Row dataRow = sh.getRow(i);
				int dataCellCount = dataRow.getLastCellNum();
				for(int j = 0; j < dataCellCount ; j++) {
					Cell datacell = dataRow.getCell(j);
					DataFormatter dataformatter=new DataFormatter();
					String cellData =dataformatter.formatCellValue(datacell);
					rowMp.put(headerValues.get(j), cellData);
				}
				mp.put(rowMp.get("Identifier"), rowMp);
				logger.info(mp.toString());
			}
			return mp;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

