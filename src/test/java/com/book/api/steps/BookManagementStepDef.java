package com.book.api.steps;

import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.book.api.common.AssertUtil;
import com.book.api.common.Config;
import com.book.api.common.ExtentReportCommon;
import com.book.management.BookManagement;
import com.book.management.UserManagement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookManagementStepDef {

    private RequestSpecification request;
    private Response response;
    private JSONObject bookRequestBody;
    private String accessToken;
    private int bookId;
    private JSONObject userData;

    @Given("I sign up with a random email and valid password")
    public void iSignUpWithRandomEmail() {
        userData = UserManagement.getUserManagementInstance().generateUserData();
        response = BookManagement.postBookSignup(userData, accessToken);

        AssertUtil.assertEquals("Signup status", 200, response.getStatusCode());
    }

    @Given("I log in with the same credentials")
    public void iLogInWithSameCredentials() {
        response = BookManagement.postBookLogin(userData, accessToken);

        AssertUtil.assertEquals("Login response status", 200, response.getStatusCode());
    }

    @Given("I store the access token for authorization")
    public void iStoreAccessToken() {
    	accessToken = response.jsonPath().getString("access_token");
    	AssertUtil.assertEquals("Access token present", false, accessToken == null || accessToken.isEmpty());
        ExtentReportCommon.getTestStep().pass("Stored access token for authorization");
    }

    @Given("I have book details with name {string}, author {string}, year {int}, and summary {string}")
    public void iHaveBookDetails(String name, String author, int year, String summary) {
        bookRequestBody = new JSONObject();
        bookRequestBody.put("name", name);
        bookRequestBody.put("author", author);
        bookRequestBody.put("published_year", year);
        bookRequestBody.put("book_summary", summary);
        ExtentReportCommon.getTestStep().pass("Created book request with data: " + bookRequestBody.toJSONString());
    }

    @When("I send a POST request to \\/books")
    public void iSendPostRequestToBooks() {
        response = BookManagement.postCreateBook(bookRequestBody, accessToken);
        ExtentReportCommon.getTestStep().pass("POST /books response: " + response.asString());
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        AssertUtil.assertEquals("Response status for book creation", statusCode, response.getStatusCode());
    }

    @Then("the response should contain the book ID")
    public void theResponseShouldContainBookId() {
        Integer id = response.jsonPath().get("id");
        AssertUtil.assertEquals("Book ID is present", false, id == null);
        bookId = id;
    }

    @When("I send a GET request to \\/books with the generated book ID")
    public void iSendPostRequestToBooksWithId() {
        response = BookManagement.getBookWithId(bookId, accessToken);
        ExtentReportCommon.getTestStep().pass("GET /books/" + bookId + " response: " + response.asString());
    }

    @Then("the book response should match the submitted data")
    public void theBookResponseShouldMatchSubmittedData() {
        String actualName = response.jsonPath().getString("name");
        String actualAuthor = response.jsonPath().getString("author");
        int actualYear = response.jsonPath().getInt("published_year");
        String actualSummary = response.jsonPath().getString("book_summary");

        AssertUtil.assertEquals("Book name match", bookRequestBody.get("name"), actualName);
        AssertUtil.assertEquals("Book author match", bookRequestBody.get("author"), actualAuthor);
        AssertUtil.assertEquals("Book year match", bookRequestBody.get("published_year"), actualYear);
        AssertUtil.assertEquals("Book summary match", bookRequestBody.get("book_summary"), actualSummary);
    }

    @When("I have updated book name {string} and year {int}")
    public void iHaveUpdatedBookNameAndYear(String name, int year) {
        bookRequestBody.put("name", name);
        bookRequestBody.put("published_year", year);
        ExtentReportCommon.getTestStep().pass("Updated book request with name: " + name + ", year: " + year);
    }

    @When("I send a PUT request to \\/books with BookId")
    public void iSendPutRequestToBooksById() {
        response = BookManagement.putUpdateBook(bookId, bookRequestBody, accessToken);
        ExtentReportCommon.getTestStep().pass("PUT /books/" + bookId + " response: " + response.asString());
    }
    
    @When("I send a GET request to \\/books with bookId")
    public void iSendGetRequestToBooksById() {
        response = BookManagement.getUpdateBook(bookId, bookRequestBody, accessToken);
        ExtentReportCommon.getTestStep().pass("GET /books/" + bookId + " response: " + response.asString());
    }

    @When("I send a DELETE request to \\/books with BookId")
    public void iSendDeleteRequestToBookById() {
    	
    	response = BookManagement.deleteBookWithId(bookId, accessToken);
        ExtentReportCommon.getTestStep().pass("DELETE /books/" + bookId + " response: " + response.asString());
    }

    @When("I send GET request to \\/books")
    public void iSendGetRequestToBooks() {
        response = BookManagement.getAllBook(accessToken);
        ExtentReportCommon.getTestStep().pass("GET /books response: " + response.asString());
    }
    
    @Given("I use a non-existent book ID")
    public void iUseNonExistentBookId() {
        // Use a high number that is not likely to exist in DB
        bookId = 99999;
        ExtentReportCommon.getTestStep().pass("Using non-existent Book ID: " + bookId);
    }


    @Then("the response should contain a list of books")
    public void theResponseShouldContainAListOfBooks() throws ParseException {
        String value = response.asString();
        JSONParser jsonParser = new JSONParser();
        Object parsedResponse = jsonParser.parse(value);
        JSONArray books = (JSONArray) parsedResponse;

        AssertUtil.assertEquals("Books list is not empty", false, books.isEmpty());
    }
}
