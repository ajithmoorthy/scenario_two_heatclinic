package com.atmecs.validate_ninja_store.pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.atmecs.validate_ninja_store.helper.JavaScriptHelper;
import com.atmecs.validate_ninja_store.helper.SeleniumHelper;
import com.atmecs.validate_ninja_store.helper.ValidaterHelper;
import com.atmecs.validate_ninja_store.helper.WaitForElement;
import com.atmecs.validate_ninja_store.logreports.LogReporter;
import com.atmecs.validate_ninja_store.utils.ExcelReader;

public class HeatClinicHomePage {
	LogReporter log=new LogReporter();
	WaitForElement waitobject=new WaitForElement();
	ExcelReader excelread=new ExcelReader();
	JavaScriptHelper javascript=new JavaScriptHelper();
	ValidaterHelper validatehelp=new ValidaterHelper();
	SeleniumHelper seleniumhelp=new SeleniumHelper();
	public void verifyHeatclinicMenu(WebDriver driver,Properties prop,String[] heatclinicdata) 
	{
		seleniumhelp.clickElement(driver, prop.getProperty("loc.menu.Home"));
		validatehelp.titleValidater(driver,"");
		seleniumhelp.clickElement(driver, prop.getProperty("loc.menu.hotsauces"));
		validatehelp.titleValidater(driver,"");
		System.out.println(heatclinicdata[2]);
		seleniumhelp.clickElement(driver, prop.getProperty("loc.menu.merchandise"));
		validatehelp.titleValidater(driver,heatclinicdata[2]);
		seleniumhelp.clickElement(driver, prop.getProperty("loc.menu.clearance"));
		validatehelp.titleValidater(driver,heatclinicdata[3]);
		seleniumhelp.clickElement(driver, prop.getProperty("loc.menu.newtohotsauce"));
		validatehelp.titleValidater(driver,heatclinicdata[4]);
		seleniumhelp.clickElement(driver, prop.getProperty("loc.menu.faq"));
		validatehelp.titleValidater(driver,heatclinicdata[5]);	
	}
}

