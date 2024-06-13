package reading_files.xls;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadXlsx {
    private static final String DATA_PROVIDER_NAME_INTEGER_INTEGER = "integerInteger";
    private static final String DATA_PROVIDER_NAME_STRING_STRING = "stringSTRING";

    public static void main(String[] args) {
        String line = "";
        System.out.println(line.split(",").length);
    }

    @DataProvider(name = DATA_PROVIDER_NAME_INTEGER_INTEGER)
    public Object[][] createData1() {
        return new Object[][]{
                {"Cedric",  Integer.valueOf(36)},
                {"Anne",  Integer.valueOf(37)},
        };
    }

    @DataProvider(name = DATA_PROVIDER_NAME_STRING_STRING)
    public Object[][] readDataFromXls() {
        File file = new File("NamesAddresses.xlsx");   //creating a new file instance
        FileInputStream fis = null;   //obtaining bytes from the file
        XSSFWorkbook wb = null;
        String[][] arrayExcelData = null;

        try {
            fis = new FileInputStream(file);
            wb = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
        int totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        int totalRows = sheet.getLastRowNum();

        arrayExcelData = new String[totalRows][totalCells];
        int rowNum, cellNum;
        for (rowNum = 1; rowNum <= totalRows; rowNum++) {
            for (cellNum = 1; cellNum <= totalCells; cellNum++) {
                arrayExcelData[rowNum][cellNum] = getCellData(rowNum, cellNum, sheet);
                System.out.println(getCellData(rowNum, cellNum, sheet));
            }
        }

        return arrayExcelData;
    }

    private String getCellData(int rowNum, int cellNum, XSSFSheet sheet) {
        Cell cell = sheet.getRow(rowNum).getCell(cellNum);
        return cell.getStringCellValue();
    }

    @Test(dataProvider = DATA_PROVIDER_NAME_INTEGER_INTEGER)
    public void verifyDataProvider(String n1, Integer n2) {
        System.out.println(n1 + " " + n2);
    }

    @Test(dataProvider = DATA_PROVIDER_NAME_STRING_STRING)
    public void verifyXlsxDataProvider(String n1, Integer n2) {
        System.out.println(n1 + " " + n2);
    }
}
