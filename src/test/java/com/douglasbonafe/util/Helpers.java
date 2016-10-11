package com.douglasbonafe.util;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class Helpers {

	public static WebDriver driver;
	public static URL serverAddress;

	/**
	 * Initialize the webdriver. Must be called before using any helper methods.
	 * *
	 * 
	 * @throws InterruptedException
	 */
	public static void init(WebDriver webDriver, URL driverServerAddress) throws InterruptedException {
		driver = webDriver;
		serverAddress = driverServerAddress;
		int timeoutInSeconds = 60;
		// must wait at least 60 seconds for running on Sauce.
		// waiting for 30 seconds works locally however it fails on Sauce.
		driver.wait(timeoutInSeconds * 1000);
	}

	/**
	 * Set implicit wait in seconds *
	 */
	public static void setWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	/**
	 * Return an element by locator *
	 */
	public static WebElement element(By locator) {
		return driver.findElement(locator);
	}

	/**
	 * Return a list of elements by locator *
	 */
	public static List<WebElement> elements(By locator) {
		return driver.findElements(locator);
	}

	/**
	 * Press the back button *
	 */
	public static void back() {
		driver.navigate().back();
	}

	/**
	 * Return a list of elements by tag name *
	 */
	public static List<WebElement> tags(String tagName) {
		return elements(for_tags(tagName));
	}

	/**
	 * Return a tag name locator *
	 */
	public static By for_tags(String tagName) {
		return By.className(tagName);
	}

	/**
	 * Return a static text element by xpath index *
	 */
	public static WebElement s_text(int xpathIndex) {
		return element(for_text(xpathIndex));
	}

	/**
	 * Return a static text locator by xpath index *
	 */
	public static By for_text(int xpathIndex) {
		return By.xpath("//android.widget.TextView[" + xpathIndex + "]");
	}

	/**
	 * Return a static text element that contains text *
	 */
	public static WebElement text(String text) {
		return element(for_text(text));
	}

	/**
	 * Return a static text locator that contains text *
	 */
	public static By for_text(String text) {
		return By.xpath("//android.widget.TextView[contains(@text, '" + text + "')]");
	}

	/**
	 * Return a static text element by exact text *
	 */
	public static WebElement text_exact(String text) {
		return element(for_text_exact(text));
	}

	/**
	 * Return a static text locator by exact text *
	 */
	public static By for_text_exact(String text) {
		return By.xpath("//android.widget.TextView[@text='" + text + "']");
	}

	public static By for_find(String value) {
		return By.xpath("//*[@content-desc=\"" + value + "\" or @resource-id=\"" + value + "\" or @text=\"" + value
				+ "\"] | //*[contains(translate(@content-desc,\"" + value + "\",\"" + value + "\"), \"" + value
				+ "\") or contains(translate(@text,\"" + value + "\",\"" + value + "\"), \"" + value
				+ "\") or @resource-id=\"" + value + "\"]");
	}

	public static WebElement find(String value) {
		return element(for_find(value));
	}

	public static WebElement find(By locator) {
		return element(locator);
	}

}