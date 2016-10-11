package com.douglasbonafe.stepDefinition;

import static com.douglasbonafe.util.Helpers.driver;
import static com.douglasbonafe.util.Helpers.find;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {

	static String linkProductPage = "http://127.0.0.1:8080/product";
	static By addNewBtn = By.xpath("//a[@class='btn btn-success']");
	static By name = By.id("name");
	static By description = By.id("description");
	static By price = By.id("price");
	static By category = By.id("category.id");
	static By submitBtn = By.xpath("//button[@class='btn btn-primary']");
	static DesiredCapabilities dCaps;

	@Before
	public static void setUp() {
		dCaps = new DesiredCapabilities();
		dCaps.setJavascriptEnabled(true);
		dCaps.setCapability("takesScreenshot", false);
		dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:\\Users\\douglas.bonafe\\Downloads\\phantomjs.exe");

		driver = new PhantomJSDriver(dCaps);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(linkProductPage);
	}

	@Given("^we are on the product list page$")
	public void we_are_on_the_product_list_page() throws Throwable {
		Assert.assertTrue(driver.getPageSource().contains("List product"));
	}

	@When("^we click on the add new button$")
	public void we_click_on_the_add_new_button() throws Throwable {
		WebElement addNewBtnElement = find(addNewBtn);
		if (addNewBtnElement.isDisplayed() && addNewBtnElement.isEnabled())
			addNewBtnElement.click();
	}

	@Then("^we should be on the \"([^\"]*)\" page$")
	public void we_should_be_on_the_page(String arg1) throws Throwable {
		String pageSource = driver.getPageSource();
		boolean ans = pageSource.contains("name");
		ans &= pageSource.contains("description");
		ans &= pageSource.contains("price");
		ans &= pageSource.contains("category.id");
		Assert.assertTrue(ans);
	}

	@When("^we set name as \"([^\"]*)\"$")
	public void we_set_name_as(String str_name) throws Throwable {
		find(name).sendKeys(str_name);
	}

	@When("^we set description as \"([^\"]*)\"$")
	public void we_set_description_as(String str_description) throws Throwable {
		find(description).sendKeys(str_description);
	}

	@When("^we set the price as (\\d+)$")
	public void we_set_the_price_as(int n_price) throws Throwable {
		find(price).sendKeys(n_price + "");
	}

	@When("^we select the cathegory \"([^\"]*)\"$")
	public void we_select_the_cathegory(String cathegoryName) throws Throwable {
		Select selector = new Select(find(category));
		selector.deselectByVisibleText(cathegoryName);
	}

	@When("^we click on submit button at product page$")
	public void we_click_on_submit_button_at_product_page() throws Throwable {
		find(submitBtn).click();
	}

	@Then("^we see in the product table a product with name \"([^\"]*)\", description \"([^\"]*)\" and price (\\d+)$")
	public void we_see_in_the_product_table_a_product_with_name_description_and_price(String name, String description,
			int price) throws Throwable {
		String pageSource = driver.getPageSource();
		boolean ans = pageSource.contains(name);
		ans &= pageSource.contains(description);
		ans &= pageSource.contains(price + "");
	}

	@After
	public void tearDown() {
		driver.quit();
	}

}
