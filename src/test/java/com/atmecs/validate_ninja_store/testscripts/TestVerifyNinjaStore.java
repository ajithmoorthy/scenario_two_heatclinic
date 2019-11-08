package com.atmecs.validate_ninja_store.testscripts;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atmecs.validate_ninja_store.constants.FileConstants;
import com.atmecs.validate_ninja_store.helper.JavaScriptHelper;
import com.atmecs.validate_ninja_store.helper.SeleniumHelper;
import com.atmecs.validate_ninja_store.helper.ValidaterHelper;
import com.atmecs.validate_ninja_store.helper.WaitForElement;
import com.atmecs.validate_ninja_store.helper.WebTableHelper;
import com.atmecs.validate_ninja_store.logreports.LogReporter;
import com.atmecs.validate_ninja_store.pages.NinjaStroreHomePage;
import com.atmecs.validate_ninja_store.testbase.TestBase;
import com.atmecs.validate_ninja_store.utils.ExcelReader;
import com.atmecs.validate_ninja_store.utils.PropertiesReader;

public class TestVerifyNinjaStore extends TestBase {
	WaitForElement waitobject=new WaitForElement();
	LogReporter log=new LogReporter();
	ExcelReader excelread=new ExcelReader();
	ValidaterHelper validatehelp=new ValidaterHelper();
	SeleniumHelper seleniumhelp=new SeleniumHelper();
	PropertiesReader propread=new PropertiesReader();
	NinjaStroreHomePage ninjapage=new NinjaStroreHomePage();
	JavaScriptHelper javascript=new JavaScriptHelper();
	WebTableHelper webtable=new WebTableHelper();
	public TestVerifyNinjaStore() {
		excelread.filePathProviderMethod(FileConstants.HEATTESTDATA_PATH);
	}
	@BeforeClass
	public void webURLLoader() {
		driver.get(prop.getProperty("URL"));
		waitobject.waitForPageLoadTime(driver);
		driver.manage().window().maximize();
		log.logReportMessageBase("STEP 2: URL is loaed"+ driver.getCurrentUrl());
	}
	
	@Test(dataProvider="testdata",dataProviderClass=ExcelReader.class)
	public void verifyNinjaStore(String[] ninjatestdata) throws IOException, InterruptedException
	{
		Properties prop1=propread.keyValueLoader(FileConstants.NINJALOCATORS_PATH);
		log.logReportMessage("STEP 3: Validating the Home Page Title");
		validatehelp.titleValidater(driver,  ninjatestdata[1]);
		log.logReportMessage("STEP 4: Changing the currency to Dollar");
		ninjapage.verifyNinjaStoreIPhone(driver,prop1, ninjatestdata);
		ninjapage.verifyNinjaStoreMacBook(driver,prop1, ninjatestdata);
		log.logReportMessage("STEP 12: Click the Cart   ");
		Actions action=new Actions(driver);
		action.moveByOffset(70, -595).click().perform();
		waitobject.waitForElementToBeClickable(driver, prop.getProperty("loc.link.viewcart"));
		seleniumhelp.clickElement(driver, prop.getProperty("loc.link.viewcart"));
		log.logReportMessage("STEP 13: Validate the cart Product");
		//String text=validatehelp.textOfElement(driver, prop1.getProperty("loc.cartcontainer.details"));
		//System.out.println(text);
		log.logReportMessage("STEP 14: Validate the Grand Total");
		log.logReportMessage("STEP 15: Remove the Product From the Cart");
		//waitobject.waitForElementToBeClickable(driver,prop1.getProperty("loc.btn.removeproduct") );
		//seleniumhelp.clickElement(driver, prop1.getProperty("loc.btn.removeproduct"));
		log.logReportMessage("STEP 16: Validate the Grand Total");
	}
}
