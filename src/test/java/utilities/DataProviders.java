package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getALLData() throws IOException{
		
		String path= ".\\testData\\Opencart_LoginData.xlsx"; //taking xl file from testdata
		
		ExcelUtility xl=new ExcelUtility(path); //Creating an object for XLUtility
		
		int rownum=xl.getRowCount("Feuil1"); 
		int colcount=xl.getCellCount("Feuil1", 1); 
	
		String logindata[][]=new String[rownum][colcount]; //Created for two dimension array which can store the same data
		
		for(int i=1; i<=rownum; i++) {     //1  // read the data from xl storing in two dimensional array  
			
			for(int j=0; j<colcount;j++) { //0  // i is rows, j is columns 
				logindata[i-1][j]=xl.getCellData("Feuil1", i, j); //1,0
			}
		}
		
		return logindata;  //returning two dimension array 
	}
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4

}
