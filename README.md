# Weather API Automation Framework

## Introduction

[OpenWeatherMap](https://openweathermap.org/) offers comprehensive global weather data through APIs, including current conditions, forecasts, nowcasts, and historical data. Its advanced ML-based numerical weather model operates at up to 500-meter resolution, providing highly localized climate data.

The OpenWeather model integrates data from multiple sources, including:
- Radars
- Models from global meteorological agencies such as the Met Office, NOAA, and ECMWF
- Weather satellites
- A vast network of weather stations

Additionally, the model employs meteorological broadcast services and data from airport weather stations, on-ground radar stations, remote sensing satellites, METAR, and automated weather stations. This extensive data integration supports OpenWeather’s ability to provide minute-by-minute hyperlocal precipitation forecasts.

The API supports JSON, XML, and HTML formats, with a free tier for limited usage and paid subscriptions for higher call volumes. There is a limited free tier, but making over 60 API calls per minute requires a paid subscription. The service allows users to request current weather details, extended forecasts, and graphical maps displaying cloud cover, wind speed, pressure, and precipitation.

## Project Purpose

This project aims to automate the testing of the OpenWeatherMap API, ensuring reliable and accurate weather data for various applications. By implementing this framework, developers can streamline the testing process, validate API responses, and integrate continuous testing into their development workflow.

This project focuses on developing an API Automation Framework using the following toolsets:
- **Rest Assured**
- **Java**
- **TestNG Framework**
- **Maven**
- **Extent Report**

We have implemented automation for the following three free modules from the OpenWeatherMap API:
- [Current Weather Data](https://openweathermap.org/current)
- [5 Day / 3 Hour Forecast](https://openweathermap.org/forecast5)
- [Air Pollution API](https://openweathermap.org/api/air-pollution)

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

## Branches

I have divided the project into 3 branches in accordance with framework levels.

### Branch 1: WeatherAPI - Basic Status Code Check

**Objective**: This branch focuses on verifying the status codes of API responses. It serves as the foundational level of the framework where we check if the endpoints are returning the expected status codes based on the input data provided in Excel sheets and real-time requests.

#### Key Features:
- Reads test data from Excel.
- Sends GET, PUT, and POST requests to API endpoints.
- Asserts that the response status code matches the expected value.
  
## Project Tree

The project is structured as follows:

![image](https://github.com/user-attachments/assets/98003eaa-67bf-4139-824f-1e965f6e0282)

#### Description

- **src/**: This is the main source directory.
  - **main/**: This directory is reserved for future expansion, currently empty.
  - **test/**: Contains all the test-related files.
    - **java/weather/**: This directory holds the test classes.
      - `AirPollutionAPITest.java`: Test class for Air Pollution API.
      - `Forecast5Day3HourTest.java`: Test class for the 5 Day / 3 Hour Forecast API.
      - `Parameter.java`: Class for handling test parameters.
      - `WeatherAPITest.java`: Test class for Current Weather Data API.
    - **resources/**: Contains the resource files for tests.
      - `DataExcelRead.xlsx`: Excel file used for reading test data.
      - `log4j.properties`: Log4j configuration file.
  - **java/api/utils/**: This directory contains utility classes.
    - `ExcelReaderUtils.java`: Utility class for reading Excel files.
    - `PropertiesReader.java`: Utility class for reading properties files.
    - `ExtentReportNG.java`: Utility class for generating Extent Reports.
  - **resources/**: Contains resource files used by the main application, including Log4j configuration.
    - `log4j.properties`: Log4j configuration file for logging.

- **.gitignore**: Specifies files and directories to be ignored by Git.
- **GroupTest.xml**: TestNG group configuration file.
- **pom.xml**: Maven configuration file for managing project dependencies and build configuration.
- **testng.xml**: TestNG configuration file for specifying test suites and test configurations.
- **README.md**: The readme file that you are currently reading.

### Branch 2: Level 2 - Response Content Validation

**Objective**: This branch enhances the framework by validating the content of the API responses. After triggering the requests, the actual responses are compared against the expected results stored in Excel sheets.

#### Key Features:
- Triggers API requests based on test data.
- Validates elements like city name, country name, coordinates, etc., on triggering endpoints.
- Compares actual responses with expected data from Excel.

### Branch 3: Level 3 - Response Deserialization and Validation

**Objective**: This branch further extends the framework by deserializing API responses into POJO classes and performing detailed validations on the actual versus expected values.

#### Key Features:
- Validates the content of the responses using JsonPath.
- Deserializes API responses into Java POJOs.
- Validates each field of the deserialized objects against expected values.
- Supports more complex response validations.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven
- TestNG
- Rest Assured
- Excel (for test data management)

### Installation

1. Clone the repository:
    ```sh
    git clone <repository_url>
    ```
2. Navigate to the project directory:
    ```sh
    cd <project_directory>
    ```
3. Install the dependencies:
    ```sh
    mvn clean install
    ```

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
  
  ## Postman Collection

The project includes a Postman collection for testing the Weather API. You can find the collection file at `WeatherApi/PostmanCollection/WeatherAPI.postman_collection.json`.

## Contributing

Contributions are welcome! Please create a pull request or raise issues at prachi4579@gmail.com for any bugs or enhancements.
