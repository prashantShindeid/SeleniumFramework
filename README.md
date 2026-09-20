## Project Structure

```
SeleniumFramework/
├── pom.xml
├── testng.xml
├── src/
│   ├── main/java/com/selenium_framework/SeleniumFramework/
│   │   ├── base/
│   │   │   ├── BasePage.java
│   │   │   ├── BaseTest.java
│   │   │   └── DriverManager.java
│   │   ├── config/
│   │   │   └── ConfigReader.java
│   │   ├── pages/
│   │   │   ├── LoginPage.java
│   │   │   ├── Dashboard.java
│   │   │   └── AddToCart.java
│   │   └── utils/
│   │       ├── ExcelReader.java
│   │       ├── ExcelDataProvider.java
│   │       ├── ExcelSource.java
│   │       ├── ExcelSources.java
│   │       ├── ExtentReportManager.java
│   │       ├── ScreenshotUtils.java
│   │       └── ConfigReader.java
│   └── main/resources/
│       └── log4j2.xml
├── src/test/java/com/selenium_framework/SeleniumFramework/
│   ├── listener/
│   │   ├── TestListener.java
│   │   ├── RetryAnalyzer.java
│   │   └── RetryTransformer.java
│   └── tests/
│       ├── Login.java
│       └── Cart.java
└── src/test/resources/
    ├── config.properties
    ├── allure.properties
    └── testdata/
        ├── Login.xlsx
        └── Cart.xlsx
```
