package com.qe.bdd.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public Page(WebDriver driver) {           
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 60);
        PageFactory.initElements(driver, this);
	}

	public void waitAndClickElement(WebElement element){
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	public void waitAndEnterText(WebElement element,String text){
            wait.until(ExpectedConditions.elementToBeClickable(element));
		    element.sendKeys(text);
	}}


