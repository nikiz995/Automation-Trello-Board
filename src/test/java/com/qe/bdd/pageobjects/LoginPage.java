package com.qe.bdd.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends Page {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()='Log in']")
    private WebElement logIn;

    @FindBy(id = "user")
    private WebElement emailText;

    @FindBy(id = "password")
    private WebElement passwordText;

    @FindBy(id = "login-submit")
    private WebElement submitButton;

    @FindBy(xpath = "//div/input[@value='Log in with Atlassian']")
    private WebElement loginInAtlassian;

    public CreateBoardPage enterTheCredentials(String userName, String password) {
        waitAndEnterText(emailText,userName);
        waitAndClickElement(loginInAtlassian);
        waitAndEnterText(passwordText,password);
        waitAndClickElement(submitButton);
        wait.until(ExpectedConditions.titleIs("Boards | Trello"));
		return new CreateBoardPage(driver);
    }

}
