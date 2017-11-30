package hybridFramework;

import java.io.IOException;

import org.testng.annotations.Test;

import utils.ReadExcel;

public class Application {

	@Test
	public void testApplications() throws IOException {

		String[][] data2 = ReadExcel.getData("TestData.xlsx", "Sheet2");
		String[][] data1 = ReadExcel.getData("TestData.xlsx", "Sheet1");

		for (int j = 1; j < data1.length; j++) {
			for (int i = 1; i < data2.length; i++) {

				switch (data2[i][3]) {

				case "openBrowser": // i=1,14
					Methods.openBrowser();
					break;
					//Adding a Comment in the Code
				case "maximizeBrowser": // i=2,15
					Methods.maximizeBrowser();
					break;

				case "implementWait":// i=3,16
					Methods.implementWait();
					break;

				case "navigateTo":// i=4,17
					Methods.navigateTo(data2[i][6]);
					break;

				case "clickFlightTab":// i=5
					Methods.clickFlightTab();
					break;

				case "enterSource": // i=6,18
					Methods.enterSourceCity(data2[i][5], data1[j][1]);
					break;

				case "enterDestination": // i=7,19
					Methods.enterDestinationCity(data2[i][5], data1[j][2]);
					break;

				case "selectDate":// i=8,20
					Methods.selectData(data2[i][1]);
					break;

				case "clickSearch": // i=9,21
					Methods.clickSearchButton(data2[i][5]);
					break;

				case "fetchPrice": // i=10,22
					Methods.fetchMinimumPrice(data2[i][4], data2[i][5]);
					break;

				case "fetchFlightName": // i=11,23
					Methods.fetchFlightName(data2[i][4], data2[i][5]);
					break;

				case "printDetails": // i=12,24
					Methods.printDetails(data2[i][1],data1[j][1],data1[j][2]);
					break;

				case "closeBrowser": // i=13,25
					Methods.closeBrowser();
					break;

				}

			}

		}
	}

}
