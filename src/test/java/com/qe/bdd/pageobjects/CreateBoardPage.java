package com.qe.bdd.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateBoardPage extends Page {

    public CreateBoardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@data-test-id='header-boards-menu-button']")
    private WebElement boardsLink;

    @FindBy(xpath = "//button[text()='Create new board…']")
    private WebElement createNewboardsLink;

    @FindBy(xpath = "//input[@data-test-id='create-board-title-input']")
    private WebElement addBoardTitle;

    @FindBy(xpath = "//button[text()='Create board']")
    private WebElement createBoardLink;

    public BoardPage createNewBoard(String Title){
        waitAndClickElement(boardsLink);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", createNewboardsLink);
        waitAndClickElement(createNewboardsLink);
        driver.switchTo().activeElement();
        waitAndEnterText(addBoardTitle,Title);
        waitAndClickElement(createBoardLink);
        wait.until(ExpectedConditions.titleIs(Title+" | Trello"));
        return new BoardPage(driver);
    }

}
