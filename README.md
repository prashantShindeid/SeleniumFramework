SeleniumFramework/
├── pom.xml                    ← Maven dependencies + build config
├── testng.xml                 ← Suite definition (parallel, listeners, classes)
│
├── src/main/java/com/selenium_framework/SeleniumFramework/
│   ├── base/                  ← Framework foundation
│   │   ├── BasePage.java
│   │   ├── BaseTest.java
│   │   └── DriverManager.java
│   │
│   ├── config/                ← Configuration
│   │   └── ConfigReader.java
│   │
│   ├── pages/                 ← Page Object Model
│   │   ├── LoginPage.java
│   │   ├── Dashboard.java
│   │   └── AddToCart.java
│   │
│   └── utils/                 ← Reusable helpers
│       ├── ExcelReader.java
│       ├── ExcelDataProvider.java
│       ├── ExcelSource.java
│       ├── ExcelSources.java
│       ├── ExtentReportManager.java
│       ├── ScreenshotUtils.java
│      
│
├── src/main/resources/
│   └── log4j2.xml
│
├── src/test/java/com/selenium_framework/SeleniumFramework/
│   ├── listener/               ← TestNG lifecycle hooks
│   │   ├── TestListener.java
│   │   ├── RetryAnalyzer.java
│   │   └── RetryTransformer.java
│   │
│   └── tests/                  ← Actual test cases
│       ├── Login.java
│       └── Cart.java
│
└── src/test/resources/
    ├── config.properties
    ├── allure.properties
    └── testdata/
        ├── Login.xlsx
        └── Cart.xlsx
