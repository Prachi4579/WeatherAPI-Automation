package weather;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import io.restassured.response.Response;

public class ResponseValidation {

public static void main(String[] args)  {
	
		Map<String,Map<String,String>> mp  = new HashMap<String,Map<String,String>>();		
		String dataExcelPath = System.getProperty("user.dir") + "/src/test/resources/DataExcelRead.xlsx";
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(new FileInputStream(new File(dataExcelPath)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet sh = workbook.getSheet("ForecastValidations");
		String testcaseId = null;
	
		Row headerRow = sh.getRow(0);
		int columns = headerRow.getLastCellNum();
		List<String> headerValues = new ArrayList<String>();
		for(int i = 0; i < columns ; i++) {
			headerValues.add(headerRow.getCell(i).getStringCellValue());
		}
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
	
			System.out.println(rowMp.get("city.name"));
//			assertEquals((resp.jsonPath().get("id")),(rowMp.get("city.name")) );
//			
		}
	
		}
		

   
}

