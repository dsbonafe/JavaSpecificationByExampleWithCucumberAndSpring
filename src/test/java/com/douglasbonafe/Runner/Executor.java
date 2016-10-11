package com.douglasbonafe.Runner;

import java.net.MalformedURLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = false, plugin = { "html:target/report",
		"json:target/cucumber-json-report.json" }, features = "src/test/resources", glue = {
				"com.douglasbonafe.stepDefinition" })
public class Executor {

	@BeforeClass
	public static void beginAll() throws MalformedURLException{
		
	}
	
	@AfterClass
	public static void clearAll(){
		
	}
}