package api.utilities;

import api.utilities.ExcelReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders
{
    @DataProvider(name = "Data")
//    public String[][] getAllData() throws IOException
    public Object[][] getAllData() throws IOException
    {
        String path = System.getProperty("user.dir") + "/src/test/resources/Userdata.xlsx";
        ExcelReader excel = new ExcelReader(path);

        int rowCount = excel.getRowCount("Sheet1");
        int colCount = excel.getColumnCount("Sheet1");

//        String[][] apiData = new String[rowCount][colCount];
        Object[][] apiData = new String[rowCount][colCount];
        for(int i=1;i<=rowCount;i++)
        {
            for(int j=0;j<colCount;j++)
            {
                apiData[i-1][j] = excel.getCellData("Sheet1", i, j);
            }
        }
        return apiData;
    }
}