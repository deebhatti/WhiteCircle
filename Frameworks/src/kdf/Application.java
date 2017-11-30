package kdf;

import java.io.IOException;

import org.testng.annotations.Test;

import utils.ReadExcel;

public class Application {

	@Test
	public void testApplications() throws IOException {

		String[][] data = ReadExcel.getData("TestData.xlsx", "Sheet2");

		for (int i = 1; i < data.length; i++) {

			switch (data[i][3]) {

			case "openBrowser": // i=1,14
				Methods.openBrowser();
				break;

			case "maximizeBrowser": // i=2,15
				Methods.maximizeBrowser();
				break;

			case "implementWait":// i=3,16
				Methods.implementWait();
				break;

			case "navigateTo":// i=4,17
				Methods.navigateTo(data[i][6]);
				break;

			case "clickFlightTab":// i=5
				Methods.clickFlightTab();
				break;

			case "enterSource": // i=6,18
				Methods.enterSourceCity(data[i][5], data[i][6]);
				break;

			case "enterDestination": // i=7,19
				Methods.enterDestinationCity(data[i][5], data[i][6]);
				break;

			case "selectDate":// i=8,20
				Methods.selectData(data[i][1]);
				break;

			case "clickSearch": // i=9,21
				Methods.clickSearchButton(data[i][5]);
				break;

			case "fetchPrice": // i=10,22
				Methods.fetchMinimumPrice(data[i][4], data[i][5]);
				break;

			case "fetchFlightName": // i=11,23
				Methods.fetchFlightName(data[i][4], data[i][5]);
				break;

			case "printDetails": // i=12,24
				Methods.printDetails(data[i][1]);
				break;

			case "closeBrowser": // i=13,25
				Methods.closeBrowser();
				break;

			}

		}

	}

}
