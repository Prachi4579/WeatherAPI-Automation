# Weather API Automation Framework

## Introduction
OpenWeatherMap is a service that offers weather information, including current conditions, forecasts, and historical data, for developers working with web and mobile applications.
It provides an API with endpoints in JSON, XML, and HTML formats. There is a limited free tier, but making over 60 API calls per minute requires a paid subscription.The service allows users to request current weather details, extended forecasts, and graphical maps displaying cloud cover, wind speed, pressure, and precipitation.
This project focuses on developing an API Automation Framework using the following toolsets:
- **Rest Assured**
- **Java**
- **TestNG Framework**
- **Maven**
- **Allure/ Extent Report**

We have implemented automation for the following three free modules from the OpenWeatherMap API:
- [Current Weather Data]([https://openweathermap.org/api](https://openweathermap.org/current))
- [5 Day / 3 Hour Forecast](https://openweathermap.org/forecast5))
- [Air Pollution API]([https://openweathermap.org/api](https://openweathermap.org/api/air-pollution))

## Framework Overview

### Level 1 Framework Features:
- **Basic TestNG Level Framework**: Establishes the foundation for the testing framework using TestNG.
- **Environment Specific Information Handling**: Manages environment-specific details for seamless testing across different setups.
- **Test Data Processing Using Excel**: Incorporates the use of Excel files for test data management.
- **Execution from TestNG.xml**: Allows for test execution control via TestNG.xml files.
- **Maven Execution**: Utilizes Maven for project build and execution.
- **Basic Framework Assertions**: Implements fundamental assertions to validate API responses.
- **Jenkins Setup Execution**: Facilitates continuous integration by setting up Jenkins for automated execution.

### Level 2 Framework Enhancements:
- **Response Validation Using JsonPath**: Enables detailed validation of API responses using JsonPath.
- **Response Validation Using POJO (Deserialization)**: Uses POJO classes for deserializing and validating API responses.
- **Parallel Execution with Report Generation**: Supports parallel test execution with comprehensive report generation.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Apache Maven
- TestNG
- Rest Assured
- Excel (for test data management)

### Running Tests
- **Using TestNG.xml**:
    ```sh
    mvn test -DsuiteXmlFile=<testng.xml>
    ```
- **Using Maven**:
    ```sh
    mvn clean test
    ```

### Reporting
- The framework supports Extent Reports for detailed reporting of test execution.

### Jenkins Integration
- Configure Jenkins to execute the tests by setting up a job and triggering the Maven goals.

## Contributing
Contributions are welcome! Please create a pull request or raise issues for any bugs or enhancements.
