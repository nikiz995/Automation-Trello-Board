package com.qe.bdd.stepdefinition;

import com.qe.bdd.pageobjects.*;
import com.qe.bdd.utils.WebDriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TrelloUIStep extends StepDefinition {

    private LoginPage loginPage;
    private CreateBoardPage createBoardPage;
    public BoardPage boardPage;

    @Given("I’m a valid logged in  user to {string} with {string} and {string}")
    public void iMAValidLoggedInUserToWithAndn(String applicationUrl, String username, String password) throws Exception {
        driver = WebDriverFactory.invokeDriver("chrome");
        driver.get(applicationUrl);
        loginPage = new LoginPage(driver);
        createBoardPage = loginPage.enterTheCredentials(username, password);
        //  setup.attachScreenshot(driver, setup.getScenario());
    }

    @When("I create a new {string}")
    public void iCreateANew(String title) {
        boardPage = createBoardPage.createNewBoard(title);
    }

    @Then("I should be able to add a Title and Cards for the stage {string}")
    public void iShouldBeAbleToAddATitleAndCardsForTheStage(String stageTitles) {
        boardPage.addTitlelistAndCard(stageTitles);
    }

    @When("I open the added card in {string} phase")
    public void iOpenTheAddedCardInPhase(String Stage) {
        boardPage.openCardAtStage(Stage);
    }

    @Then("I should be able to add description {string}")
    public void iShouldBeAbleToAddDescription(String description) {
        boardPage.addDescriptionForCard(description);
    }

    @And("I should be able to add members,labels,attachments,cover to the card")
    public void iShouldBeAbleToAddMembersLabelsAttachmentsCoverToTheCard() {
        boardPage.selectAddOnForCard();
    }

    @Then("I should be move the card to end phase adding comments at each stage")
    public void iShouldBeMoveTheCardToEndPhaseAddingCommentsAtEachStage() {
        boardPage.moveCardBetweenPhase();
    }

    @And("I should be able to delete the card")
    public void iShouldBeAbleToDeleteTheCard() {
        boardPage.deleteBoard();
    }


}
