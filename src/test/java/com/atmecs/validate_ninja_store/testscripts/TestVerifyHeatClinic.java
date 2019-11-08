package com.atmecs.validate_ninja_store.testscripts;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.atmecs.validate_ninja_store.constants.FileConstants;
import com.atmecs.validate_ninja_store.helper.JavaScriptHelper;
import com.atmecs.validate_ninja_store.helper.SeleniumHelper;
import com.atmecs.validate_ninja_store.helper.ValidaterHelper;
import com.atmecs.validate_ninja_store.helper.WaitForElement;
import com.atmecs.validate_ninja_store.helper.WebTableHelper;
import com.atmecs.validate_ninja_store.logreports.LogReporter;
import com.atmecs.validate_ninja_store.pages.HeatClinicHomePage;
import com.atmecs.validate_ninja_store.pages.NinjaStroreHomePage;
import com.atmecs.validate_ninja_store.testbase.TestBase;
import com.atmecs.validate_ninja_store.utils.ExcelReader;
import com.atmecs.validate_ninja_store.utils.PropertiesReader;

public class TestVerifyHeatClinic extends TestBase{
	WaitForElement waitobject=new WaitForElement();
	LogReporter log=new LogReporter();
	ExcelReader excelread=new ExcelReader();
	ValidaterHelper validatehelp=new ValidaterHelper();
	SeleniumHelper seleniumhelp=new SeleniumHelper();
	PropertiesReader propread=new PropertiesReader();
	NinjaStroreHomePage ninjapage=new NinjaStroreHomePage();
	JavaScriptHelper javascript=new JavaScriptHelper();
	WebTableHelper webtable=new WebTableHelper();
	HeatClinicHomePage heatclinic=new HeatClinicHomePage();
	@DataProvider(name = "testdata")
	public String[][] indexDynamicDataProvider() throws IOException {
		String Excelarray[][] = null;
		Excelarray =excelread.excelDataProviderArray(FileConstants.HEATTESTDATA_PATH);
		return Excelarray;
	}
	@BeforeClass
	public void webURLLoader() {
		driver.get(prop.getProperty("heatclinicURL"));
		waitobject.waitForPageLoadTime(driver);
		driver.manage().window().maximize();
		log.logReportMessageBase("STEP 2: URL is loaed"+ driver.getCurrentUrl());
	}

	@Test(dataProvider="testdata")
	public void verifyHeatClinic(String[] heatclinicdata) throws IOException, InterruptedException {
		Properties prop1=propread.keyValueLoader(FileConstants.HEATTCLINICLOCATORS_PATH);
		log.logReportMessageBase("STEP 3: Validate all the Menu");
		heatclinic.verifyHeatclinicMenu(driver,prop1,heatclinicdata);
		log.logReportMessageBase("STEP 4: Mouse Over Merchandise and click Men");
		seleniumhelp.mouseOver(driver,prop1.getProperty("loc.menu.merchandise"));
		WebElement menuelement=waitobject.WaitForFluent(driver, prop1.getProperty("loc.submenu.mens"));
		Actions action=new Actions(driver);
		action.moveToElement(menuelement).click().perform();
		validatehelp.titleValidater(driver, "Broadleaf Commerce Demo Store - Heat Clinic - Mens");
		log.logReportMessageBase("STEP 5: Verify the Viewing Mens");
		String viewingmenstext=validatehelp.textOfElement(driver, prop1.getProperty("loc.txt.viewing"));
		validatehelp.assertValidater(viewingmenstext, "Viewing Mens (1 - 3 of 3)");
		log.logReportMessageBase("STEP 6: Click the Buy now of Hawt Like a Habanero Shirt (Men's)");
		seleniumhelp.clickElement(driver,prop1.getProperty("loc.btn.buynow"));
		driver.switchTo().defaultContent();
		seleniumhelp.sendKeys(prop1.getProperty("loc.txtfield.name"), driver,heatclinicdata[5]);
		WebElement element=waitobject.WaitForFluent(driver,  prop1.getProperty("loc.btn.popupbuynow"));
		action.moveToElement(element).perform();
		action.moveByOffset(-17,-200).click().perform();
		action.moveByOffset(-17,80).click().perform();
		seleniumhelp.clickElement(driver, prop1.getProperty("loc.btn.popupbuynow"));
		log.logReportMessageBase("STEP 7: click the Cart");
		waitobject.waitForImplicit(driver);
		seleniumhelp.clickElement(driver,prop1.getProperty("loc.link.cart"));
		driver.switchTo().defaultContent();
		log.logReportMessageBase("STEP 8: Increase the Qty count");
		seleniumhelp.clickElement(driver,prop1.getProperty("loc.btn.qtyincrease"));
		log.logReportMessageBase("STEP 9: Update the  Cart");
		seleniumhelp.clickElement(driver,prop1.getProperty("loc.btn.update"));
		log.logReportMessageBase("STEP 10: Validation of the product");
		String  proddetails=validatehelp.textOfElement(driver, prop1.getProperty("loc.containerprod.details"));
		System.out.println(proddetails);
	}
}
