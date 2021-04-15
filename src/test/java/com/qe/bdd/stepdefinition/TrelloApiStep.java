
package com.qe.bdd.stepdefinition;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.junit.Assert;


public class TrelloApiStep extends StepDefinition {
    private static String ids;
    public static String getIds() {
        return ids;
    }
    private Response response;
    private RequestSpecification request;
    JSONObject requestParams = new JSONObject();

    @Given("I'm the valid user calling endpoint {string} with valid key {string} and valid token {string}")
    public void iMTheValidUserCallingEndpointWithValidKeyAndValidToken(String endpointUrl, String key, String token) {
        RestAssured.baseURI = endpointUrl;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        requestParams.put("key", key);
        requestParams.put("token", token.trim());
    }

    @When("I hit the post endpoint with name {string} and desc {string}")
    public void iHitThePostEndpointWithNameAndDesc(String name, String desc) {
        requestParams.put("name", name);
        requestParams.put("desc", desc.trim());
        request.body(requestParams.toJSONString());
        response = request.post();
        JsonPath jsonPathEvaluator = response.jsonPath();
    }

    @When("I hit Get endpoint")
    public void iHitGetEndpoint() {
        request.body(requestParams.toJSONString());
        response = request.get(getIds());
    }

    @When("I hit Get endpoint with invalid ID {string}")
    @When("I hit Get endpoint with empty ID {string}")
    public void iHitGetEndpointWithEmptyID(String ID) {
        request.body(requestParams.toJSONString());
        response = request.get(ID);
    }

    @When("I hit the put endpoint for updating the desc {string}")
    public void iHitThePutEndpointForUpdatingTheDesc(String desc) {
        requestParams.put("desc", desc);
        request.body(requestParams.toJSONString());
        response = request.put(getIds());
    }

    @When("I hit the delete end point")
    public void iHitTheDeleteEndPoint() {
        request.body(requestParams.toJSONString());
        response = request.delete(getIds());
    }

    @Then("I should be able to fetch the details of the created board")
    @Then("I should be able to create a new board")
    @Then("I should be able to see the updated desc value")
    @Then("I should be able to delete the created board")
    public void iShouldSeeStatusCode() {
        int actualStatusCode = response.getStatusCode();
        //  String expectedStatusCode = statusCode != null ? statusCode : "200";
        System.out.println("Response body: " + response.body().asString());
        Assert.assertEquals(200, actualStatusCode);
        JsonPath jsonPathEvaluator = response.jsonPath();
        ids = jsonPathEvaluator.get("id");
        System.out.println("Response body: " + response.body().asString());
        setup.getScenario().log(response.body().asString());
    }


    @Then("I should see {string} Status code")
    public void iShouldSeeStatusCode(String expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        //String expectedStatusCode = statusCode != null ? statusCode : "200";
        Assert.assertEquals(Integer.parseInt(expectedStatusCode), actualStatusCode);
        System.out.println("Response body: " + response.body().asString());
        setup.getScenario().log(response.body().asString());
    }



}
