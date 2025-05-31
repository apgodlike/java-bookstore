@regression @book @crud
Feature: Book Management API

  Background:
    Given I sign up with a random email and valid password
    And I log in with the same credentials
    And I store the access token for authorization

  @smoke @regression @positive @scenarioOutline
  Scenario Outline: Create book with valid data
    Given I have book details with name "<name>", author "<author>", year <published_year>, and summary "<book_summary>"
    When I send a POST request to /books
    Then the response status should be 200
    And the response should contain the book ID
    When I send a GET request to /books with the generated book ID
    And the book response should match the submitted data

    Examples:
      | name                      | author     | published_year | book_summary                                               |
      | The Art of Testing        | Jane Doe   | 2021           | A guide to modern testing practices                        |
      | Clean Architecture        | Uncle Bob  | 2017           | On sustainable software architecture                       |
      | Refactoring               | Martin F.  | 1999           | Improve existing code without breaking it                  |


  @smoke @regression @positive
  Scenario: Get a book by ID
    Given I have book details with name "The Art of Testing", author "Uncle Bob", year 1999, and summary "A guide to modern testing practices"
    When I send a POST request to /books
    Then the response status should be 200
    And the response should contain the book ID
    When I send a GET request to /books with the generated book ID
    And the book response should match the submitted data

  @regression @negative
  Scenario: Get a book by non-existent ID
    Given I use a non-existent book ID
    When I send a GET request to /books with bookId
    Then the response status should be 404

  @regression @positive
  Scenario: Update a book successfully
    Given I have book details with name "The Art of Testing", author "Uncle Bob", year 1999, and summary "A guide to modern testing practices"
    And I send a POST request to /books
    And the response status should be 200
    And the response should contain the book ID
    And I have updated book name "The Art of Automation Testing" and year 2010
    When I send a PUT request to /books with BookId
    Then the response status should be 200
    And the book response should match the submitted data


  @regression @positive
  Scenario: Delete a book successfully
  Given I have book details with name "The Art of Testing", author "Uncle Bob", year 1999, and summary "A guide to modern testing practices"
    And I send a POST request to /books
    And the response status should be 200
    And the response should contain the book ID
    When I send a DELETE request to /books with BookId
    Then the response status should be 200

  @regression @negative
  Scenario: Delete a non-existent book
    Given I use a non-existent book ID
    When I send a DELETE request to /books with BookId
    Then the response status should be 404

  @regression @positive
  Scenario: Get all books
    Given I have book details with name "The Art of Testing", author "Uncle Bob", year 1999, and summary "A guide to modern testing practices"
    When I send a POST request to /books
    And I have book details with name "The Art of Automation Testing", author "Uncle Bob", year 1999, and summary "A guide to modern testing practices"
    And I send a POST request to /books
    And I send GET request to /books
    Then the response should contain a list of books
