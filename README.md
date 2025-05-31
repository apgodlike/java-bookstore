Java Bookstore API Test Automation Framework
📌 Overview
This repository contains a TestNG-based API automation framework for testing the FastAPI-based Bookstore API. The framework is designed to validate all major API functionalities, including authentication and CRUD operations on books, using RestAssured in Java.

It is structured for scalability, maintainability, and CI/CD readiness, and includes test reporting support.

✅ Features
🔐 Authentication Flow: Automates user signup and login.

📚 Book Management Tests: Covers all CRUD operations on books.

🔁 Request Chaining: Uses login token from authentication APIs for protected book APIs.

⚙️ TestNG Integration: Test execution via testng.xml.

📊 Reports: Generates test execution reports using TestNG’s default reporting.

📁 Modular Design: Clear separation of concerns with utils, constants, payloads, and test classes.

🧪 Positive & Negative Tests: Covers success and failure scenarios for robustness.

🔄 CI/CD Ready: Easily integrable with GitHub Actions or other CI tools.

🧾 Folder Structure
bash
Copy
Edit
java-bookstore/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── utils/         # Utility methods (e.g., token generation)
│   └── test/
│       └── java/
│           ├── apis/         # Book & User test classes
│           ├── constants/    # Endpoint URLs and test data constants
│           ├── payloads/     # JSON payload builders for request bodies
│           └── utils/        # Reusable helper functions
├── testng.xml                # TestNG suite configuration
├── pom.xml                   # Maven dependencies
└── README.md
🚀 Getting Started
🛠 Prerequisites
Java 8 or above

Maven

Git

🔄 Setup
Clone the repository:

bash
Copy
Edit
git clone https://github.com/apgodlike/java-bookstore.git
cd java-bookstore
Install dependencies:

bash
Copy
Edit
mvn clean install
Make sure the FastAPI server is running locally:

bash
Copy
Edit
uvicorn main:app --reload
🧪 Running Tests
With Maven:
bash
Copy
Edit
mvn test
With TestNG:
bash
Copy
Edit
mvn test -DsuiteXmlFile=testng.xml
📄 Testing Strategy
✅ Approach
Each feature (user, books) has a separate test class.

Auth token is dynamically generated via login API and reused using request chaining.

Tests are data-driven using constants and helper payloads.

Proper assertions on status codes, payloads, and headers.

📌 Reliability & Maintenance
Reusable utilities to avoid duplication.

Constants and payloads externalized for easy updates.

Clear folder/module separation for maintainability.

⚠️ Challenges
Token Management: Solved via centralized token generation utility.

Request Sequencing: Managed using shared state across test classes (e.g., book ID from creation used for update/delete).

📝 Sample Test Scenarios Covered
API	Scenario	Type
/signup	Register with valid data	Positive
/signup	Register with existing user	Negative
/login	Login with correct credentials	Positive
/login	Login with invalid password	Negative
/books/	Create, Get, Update, Delete book	Positive
/books/{id}	Invalid ID operations	Negative

📋 Sample Report
A sample TestNG report is generated at:

bash
Copy
Edit
java-bookstore/test-output/index.html
Open in browser to view pass/fail status and stack traces.

