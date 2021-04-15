package com.qe.bdd.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

public class BoardPage extends Page {
    public BoardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "list-name-input")
    private WebElement enterListTitleText;

    @FindBy(xpath = "//input[@value='Add list']")
    private WebElement addListBtn;

    @FindBy(xpath = "//span[@class='js-add-a-card' and text()='Add a card']")
    private WebElement addCardLink;

    @FindBy(xpath = "//textarea[@placeholder='Enter a title for this card…']")
    private WebElement addTitleText;

    @FindBy(xpath = "//input[@value='Add card']")
    private WebElement addCardBtn;

    @FindBy(xpath = "//div[@class='window-wrapper js-tab-parent']/a")
    private WebElement popCloseBtn;

    @FindBy(xpath = "//textarea[text()='DEV']/ancestor::div[@class='js-list list-wrapper']//span[contains(text(),'DEV')]")
    private WebElement From;

    @FindBy(xpath = "//textarea[text()='QA']/ancestor::div[@class='js-list list-wrapper']//span[contains(text(),'QA')]")
    private WebElement To;

    @FindBy(xpath = "//div[@class='list-card-details js-card-details']")
    private List<WebElement> moveCard2;

    @FindBy(xpath = "//div[@class='description-edit edit']/textarea")
    private WebElement descriptionCard;

    @FindBy(xpath = "//div[@class='description-edit edit']//input[@value='Save']")
    private WebElement descriptionSaveBtn;

    @FindBy(xpath = "//a[@title='Labels']")
    private WebElement labels;

    @FindBy(xpath = "//div[@class='pop-over is-shown']//span[contains(@class,'yellow')]")
    private WebElement yellowLabels;

    @FindBy(xpath = "//a[contains(@class,'pop-over-header-close')]")
    private WebElement popClose;

    @FindBy(xpath = "//a[@title='Members']")
    private WebElement members;

    @FindBy(xpath = "//div[@class='pop-over is-shown']//li/a")
    private WebElement membersSelect;

    @FindBy(xpath = "//div[@class='js-date-range-picker']//button")
    private WebElement dueDateBtn;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveDateBtn;

    @FindBy(xpath = "//span[text()='Cover']")
    private WebElement coverBtn;

    @FindBy(xpath = "(//div[@class='photo-attribution-component small'])[1]")
    private WebElement coverPicture;

    @FindBy(xpath = "//div[@class='comment-box']/textarea")
    private WebElement commentTextbx;

    @FindBy(xpath = "//div[@class='comment-box']//input[@type='submit']")
    private WebElement saveCommentBtn;

    @FindBy(xpath = "//div[@id='board']//div[contains(@class,'list-cards u-fancy')]")
    private List<WebElement> Toto;

    @FindBy(xpath = "//a[contains(text(),'More')]")
    private WebElement moreBtn;

    @FindBy(xpath = "//a[contains(text(),'Close board…')]")
    private WebElement closeBtn;

    @FindBy(xpath = "//input[@value='Close']")
    private WebElement closeLink;

    @FindBy(xpath = "//a[text()='Permanently delete board…']")
    private WebElement permanentlyDeleteBoardlink;

    @FindBy(xpath = "//input[@value='Delete']")
    private WebElement deleteBtn;

    public void addTitlelistAndCard(String stageTitles) {
        List<String> headerTitles = Arrays.asList(stageTitles.split(","));
        for (String headerTitle : headerTitles) {
            waitAndEnterText(enterListTitleText, headerTitle);
            waitAndClickElement(addListBtn);
            waitAndClickElement(addCardLink);
            waitAndEnterText(addTitleText, headerTitle + "card inside");
            waitAndClickElement(addCardBtn);
        }
    }

    public void openCardAtStage(String Stage) {
        WebElement stage = driver.findElement(By.xpath("//span[text()='" + Stage + "card inside']"));
        waitAndClickElement(stage);
    }

    public void addDescriptionForCard(String description) {
        wait.until(ExpectedConditions.elementToBeClickable(descriptionCard));
        this.descriptionCard.sendKeys(description);
        waitAndClickElement(descriptionSaveBtn);
    }

    public void selectAddOnForCard() {
        waitAndClickElement(labels);
        waitAndClickElement(yellowLabels);
        waitAndClickElement(members);
        waitAndClickElement(membersSelect);
        waitAndClickElement(popClose);
        waitAndClickElement(dueDateBtn);
        waitAndClickElement(saveDateBtn);
        waitAndClickElement(coverBtn);
        waitAndClickElement(coverPicture);
        try {
            waitAndClickElement(popClose);
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click = true;", popClose);
            e.printStackTrace();
        }
    }

    public void moveCardBetweenPhase() {
        List<WebElement> To = Toto;
        for (int i = 1; i < To.size(); i++) {
            WebElement beforeDomChange = driver.findElement(By.xpath("//div[contains(@class,'list-card-details')]//span[text()='To DOcard inside']/../../.."));
            wait.until(ExpectedConditions.elementToBeClickable(beforeDomChange));
            Actions act = new Actions(driver);
            act.clickAndHold(beforeDomChange).moveToElement(To.get(i)).release().build().perform();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'list-card-details')]//span[text()='To DOcard inside']")));
            WebElement afterDomChange = driver.findElement(By.xpath("//div[contains(@class,'list-card-details')]//span[text()='To DOcard inside']/../../.."));
            waitAndClickElement(afterDomChange);
            waitAndEnterText(commentTextbx, ("comment"));
            waitAndClickElement(saveCommentBtn);
            waitAndClickElement(popCloseBtn);
        }
    }

    public void deleteBoard() {
        waitAndClickElement(moreBtn);
        waitAndClickElement(closeBtn);
        waitAndClickElement(closeLink);
        waitAndClickElement(permanentlyDeleteBoardlink);
        waitAndClickElement(deleteBtn);
    }
}



