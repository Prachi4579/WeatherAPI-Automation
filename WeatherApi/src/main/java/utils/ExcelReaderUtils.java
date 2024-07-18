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
		Workbook workbook = null;
		try {
			logger.trace("Opening Excel file: " + excelSheetPath);
			Map<String, Map<String, String>> mp = new HashMap<String, Map<String, String>>();
			workbook = new XSSFWorkbook(new FileInputStream(new File(excelSheetPath)));

			logger.trace("Accessing sheet: " + sheetName);
			Sheet sh = workbook.getSheet(sheetName);

			Row headerRow = sh.getRow(0);
			int columns = headerRow.getLastCellNum();
			List<String> headerValues = new ArrayList<String>();
			logger.trace("Adding Header Values");
			for(int i = 0; i < columns ; i++) {
				logger.trace(" i : " + i);			
				headerValues.add(headerRow.getCell(i).getStringCellValue().trim());

			}
			logger.trace(headerValues.toString());
			System.out.println(headerValues);
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
				System.out.println(rowMp);
			}
			
			return mp;
		}catch(Exception e) {
			logger.error(e.getMessage());

			e.printStackTrace();
			return null;
		}finally {
			if(workbook != null) {
				try {
					workbook.close();
				}catch(Exception e) {
					logger.error(e.getMessage());
				}
			}
		}
	}

public static void main(String[] args) {
    String excelSheetPath = System.getProperty("user.dir") + "/src/test/resources/DataExcelRead.xlsx";
    String sheetName = "CombinedDataDeserialization";

    Map<String, Map<String, String>> data = getWeatherAPIData(excelSheetPath, sheetName);
    if (data != null) {
        for (Map.Entry<String, Map<String, String>> entry : data.entrySet()) {
            System.out.println("Identifier: " + entry.getKey());
            for (Map.Entry<String, String> rowEntry : entry.getValue().entrySet()) {
                System.out.println(rowEntry.getKey() + ": " + rowEntry.getValue());
            }
            System.out.println();
        }
    }
}
}

