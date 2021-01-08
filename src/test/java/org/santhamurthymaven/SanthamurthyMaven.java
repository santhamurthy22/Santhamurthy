package org.santhamurthymaven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SanthamurthyMaven {

	public static WebDriver driver;

	public static void lanuchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	public static void lanuchUrl(String Url) {
		driver.get(Url);
	}
	public static void maxWindow() {
		driver.manage().window().maximize();
	}
	public static void searchFill(WebElement element, String text) {
		element.sendKeys(text);
	}		
	public static void btnClick(WebElement element) {
		element.click();
	}
	public static void txtSelect(WebElement element, String text) {
		Select s = new Select(element);
		s.selectByVisibleText(text);
	}
	public static String excelRead(int rowNo, int cellNo) throws IOException {
		File f = new File("C:\\Users\\santh\\eclipse-workspace\\MavenSanth\\Excel\\SampleData.xlsx");
		FileInputStream fin = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fin);
		Sheet s = w.getSheet("Input");
		Row r = s.getRow(rowNo);
		Cell c = r.getCell(cellNo);
        int cellType = c.getCellType();
		String value = "";
        if (cellType == 1) {
			value = c.getStringCellValue();
		} else if (DateUtil.isCellDateFormatted(c)) {
			Date d = c.getDateCellValue();
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			value = sd.format(d);
		} else {
			double dd = c.getNumericCellValue(); // type casting//
			long l = (long) dd; // type casting//
			value = String.valueOf(l);
			}
		return value;
	}
	public static void excelWrite(int rowNo, int cellNo, String text) throws IOException {
		File f = new File("C:\\Users\\santh\\eclipse-workspace\\MavenSanth\\Excel\\SampleData.xlsx");
		FileInputStream fin = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fin);
		Sheet s = w.getSheet("Input");
		Row r = s.getRow(rowNo);
		Cell c = r.createCell(cellNo);
		c.setCellValue(text);
		FileOutputStream fout = new FileOutputStream(f);
		w.write(fout);
	}
	public static void excelUpdate(int rowNo,int cellNo, String text) throws IOException {
		File f = new File("C:\\Users\\santh\\eclipse-workspace\\MavenSanth\\Excel\\SampleData.xlsx");
		FileInputStream fin = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fin);
		Sheet s = w.getSheet("Input");
		Row r = s.getRow(rowNo);
		Cell c = r.getCell(cellNo);
		c.setCellValue(text);
		FileOutputStream fout = new FileOutputStream(f);
		w.write(fout);
	}
}


