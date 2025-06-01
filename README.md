# Java API Testing Framework for Python Bookstore

This repository contains a Java-based API automation framework for testing a Python FastAPI bookstore backend. It validates core functionalities including all major CRUD operations for book management.

## 📌 Objective

The goal of this project is to:
- Provide comprehensive automated API test coverage
- Ensure maintainable and scalable test design
- Validate both positive and negative test flows
- Generate detailed test execution reports
- Integrate testing into a CI pipeline using GitHub Actions

## 🚀 Tech Stack

- **Language**: Java
- **API Testing Library**: RestAssured
- **Test Runner**: JUnit + Cucumber
- **Build Tool**: Maven
- **CI/CD**: GitHub Actions
- **Backend**: Python FastAPI (tested application)

## 📋 Prerequisites

Before running the tests, ensure you have:
- Java 11 or higher
- Maven 3.6 or higher
- Git
- Python 3.8+ (for running the FastAPI backend)

## 🧪 Features

### ✅ Comprehensive API Coverage
- Covers all major CRUD operations: Create, Read, Update, Delete
- Verifies:
  - HTTP Status codes
  - Response payloads
  - Headers
  - Error messages
- Includes both valid and invalid test cases
- Implements request chaining (e.g., use created book ID for update/delete)

### 🔁 Reusable & Scalable Design
- Modular structure using:
  - Step Definitions
  - Feature files
  - Service classes
  - POJOs for request/response models
- Configuration via `config.properties` for environment handling

### 📄 Reporting
- Generates structured reports using Cucumber's built-in reporting
- Easily pluggable with third-party reporting tools like Allure (optional)

### ⚙️ CI/CD Integration
- Configured GitHub Actions workflow to:
  - Set up Java environment
  - Deploy Python FastAPI backend
  - Resolve dependencies via Maven
  - Execute the complete test suite
  - Generate and upload test reports

## 🧭 Project Structure

```
java-bookstore/
│
├── src/test/java/
│   ├── com/book/api/steps/          # Cucumber step definitions
│   ├── com/book/api/testRunner/     # JUnit runners for test execution
│   └── com/book/api/common/         # Request utility classes using RestAssured
│
├── src/test/resources/
│   ├── features/                    # Gherkin feature files for scenarios
│   └── config.properties       # Main configuration file
│
├── .github/workflows/               # GitHub Actions CI configuration
├── target/                          # Generated reports and build artifacts
├── pom.xml                         # Maven configuration
└── README.md                       # Project documentation
```

## 🔧 API Endpoints Tested

The framework tests the following bookstore API endpoints:
- `GET /books` - Retrieve all books
- `GET /books/{id}` - Retrieve a specific book
- `POST /books` - Create a new book
- `PUT /books/{id}` - Update an existing book
- `DELETE /books/{id}` - Delete a book

## 🧪 How to Run Tests Locally

### 1. Clone the Repository
```bash
git clone https://github.com/apgodlike/java-bookstore.git
cd java-bookstore
```

### 2. Set Up the FastAPI Backend
The Python FastAPI backend needs to be running before executing tests. You can either:
- Start it manually on `http://127.0.0.1:8000`
- Use the provided Docker setup (if available)
- The CI pipeline automatically handles backend deployment

**Note**: Ensure the backend is accessible at the configured base URL in `config.properties`. Rename config.properties.sample to config.properties

### 3. Run the Test Suite
```bash
mvn clean test
```

### 4. View Reports
After test execution, reports are generated in:
- Cucumber HTML report: `target/`

## 🔄 CI/CD with GitHub Actions

This project includes a `.github/workflows/ci.yml` file that:
- Runs on every push or pull request
- Deploys the Python FastAPI backend server
- Installs Java and Maven dependencies
- Builds the project and executes all tests
- Publishes test reports as artifacts
- Provides test results summary in PR comments

## 💡 Testing Strategy

**Behavior-Driven**: Tests written using Gherkin syntax in `.feature` files for clarity and business readability.

**Reliability**: Each test maintains isolation using setup/teardown logic and unique test data.

**Maintainability**: Modular classes and data models allow easy updates and additions.

**Coverage**: Tests include both happy path and edge cases for comprehensive validation.

## 📊 Sample Test Scenario

```gherkin
Feature: Book Management
  
  Scenario: Update a book successfully
    Given I have book details with name "The Art of Testing", author "Uncle Bob", year 1999, and summary "A guide to modern testing practices"
    And I send a POST request to /books
    And the response status should be 200
    And the response should contain the book ID
    And I have updated book name "The Art of Automation Testing" and year 2010
    When I send a PUT request to /books with BookId
    Then the response status should be 200
    And the book response should match the submitted data
```

---

**Questions or Issues?** Please open a GitHub issue for any problems or feature requests.