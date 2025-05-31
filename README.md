# Java Bookstore API Testing Framework

This repository contains an API automation framework for testing a Python FastAPI backend. It validates core functionalities including all major CRUD operations for a sample bookstore application.

## 📌 Objective

The goal of this project is to:
- Provide comprehensive automated API test coverage.
- Ensure maintainable and scalable test design.
- Validate both positive and negative test flows.
- Generate detailed test execution reports.
- Integrate testing into a CI pipeline using GitHub Actions.

## 🚀 Tech Stack

- **Language**: Java
- **API Testing Library**: RestAssured
- **Test Runner**: JUnit + Cucumber
- **Build Tool**: Maven
- **CI/CD**: GitHub Actions

## 🧪 Features

### ✅ Comprehensive API Coverage
- Covers all major CRUD operations: Create, Read, Update, Delete.
- Verifies:
  - HTTP Status codes
  - Response payloads
  - Headers
  - Error messages
- Includes both valid and invalid test cases.
- Implements request chaining (e.g., use created book ID for update/delete).

### 🔁 Reusable & Scalable Design
- Modular structure using:
  - Step Definitions
  - Feature files
  - Service classes
  - POJOs for request/response models
- Configuration via `application.properties` for environment handling.

### 📄 Reporting
- Generates structured reports using Cucumber's built-in reporting.
- Easily pluggable with third-party reporting tools like Allure (optional).

### ⚙️ CI/CD Integration
- Configured GitHub Actions workflow to:
  - Set up Java environment
  - Resolve dependencies via Maven
  - Execute the complete test suite
  - Generate and upload test reports

## 🧭 Project Structure

java-bookstore/
│
├── src/test/java/
│ ├── com.book.api.steps/ # Cucumber step definitions
│ ├── com.book.api.testRunner/ # JUnit runners for test execution
│ └── com.book.api.common/ # Request utility classes using RestAssured
│
├── src/test/resources/
│ ├── features/ # Gherkin feature files for scenarios
│ └── configs/ # Environment-specific configs (if needed)
│
├── .github/workflows/ # GitHub Actions CI configuration
└── README.md # Project documentation

bash
Copy
Edit

## 🧪 How to Run Tests Locally

### 1. Clone the Repository
```bash
git clone https://github.com/apgodlike/java-bookstore.git
cd java-bookstore
2. Set Up the FastAPI Backend
Follow the instructions in the backend API's README.md to get the FastAPI server running locally (typically at http://127.0.0.1:8000).

3. Run the Test Suite
bash
Copy
Edit
mvn clean test
4. View Reports
Cucumber report is generated under: target/

🔄 CI/CD with GitHub Actions
This project includes a .github/workflows/ci.yml file that:

Runs on every push or PR.

Installs Java and Maven.

Builds the project and runs tests.

Publishes reports as artifacts.

💡 Testing Strategy
Behavior-Driven: Tests written using Gherkin syntax in .feature files for clarity.

Reliability: Each test resets state using teardown logic and isolated test data.

Maintainability: Modular classes and data models allow easy updates and additions.

Challenges:

Ensuring proper synchronization with the FastAPI backend (resolved using retries).

Managing dynamic data (solved using runtime response parsing and chaining).

📄 Sample Report
You can find a sample test report in the target/cucumber-reports/ directory after test execution.

📬 Contact
For any queries or suggestions, please reach out via GitHub Issues or contact the repo owner.