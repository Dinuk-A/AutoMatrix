📦src
 ┣ 📂main
 ┃ ┗ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂automation
 ┃ ┃ ┃ ┃ ┗ 📂framework
 ┃ ┃ ┃ ┃ ┃ ┣ 📂cosmos
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CosmosDbClient.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂utils
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂api
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ApiUtils.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AssertionUtils.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommonMethods.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜HttpStatusCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜JsonUtils.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ConfigPropertyReader.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜JsonReader.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂ui
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ConditionUtils.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ElementActions.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IframeActions.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PopupActions.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RetrievalUtils.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TabActions.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UiAssertionUtils.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WaitUtils.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜App.java
 ┗ 📂test
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂automation
 ┃ ┃ ┃ ┃ ┗ 📂framework
 ┃ ┃ ┃ ┃ ┃ ┣ 📂Core
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂api
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂ui
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BaseTest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂JsonPlaceholder
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DeleteTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GetByParamsTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GetTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PutTest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂ProjectA
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Sempsarc.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂ProjectC
 ┃ ┃ ┃ ┃ ┃ ┗ 📂SampleTests
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂api
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂mixed
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂ui
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FirstUiTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜JsonReaderTest.java
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂data
 ┃ ┃ ┃ ┣ 📂api
 ┃ ┃ ┃ ┃ ┣ 📜PredefinedEx.json
 ┃ ┃ ┃ ┃ ┗ 📜SempsarcBody.json
 ┃ ┃ ┃ ┣ 📂mix
 ┃ ┃ ┃ ┗ 📂ui
 ┃ ┃ ┃ ┃ ┗ 📜user.json
 ┃ ┃ ┣ 📂suites
 ┃ ┃ ┃ ┣ 📂api
 ┃ ┃ ┃ ┃ ┣ 📜jsonplaceholdertest.xml
 ┃ ┃ ┃ ┃ ┗ 📜sempsarc.xml
 ┃ ┃ ┃ ┣ 📂mix
 ┃ ┃ ┃ ┗ 📂ui
 ┃ ┃ ┃ ┃ ┣ 📜jsonreader.xml
 ┃ ┃ ┃ ┃ ┗ 📜uitest.xml
 ┃ ┃ ┗ 📜config.properties    
 
 
 project-root/
 ├── src/
 │   ├── main/                                 // Source code for the application
 │   │   └── java/com/automation/framework/    // Contains the main application code
 │   │       ├── cosmos/                       // Contains classes related to Cosmos DB operations
 │   │       │   └── CosmosDbClient.java
 │   │       ├── utils/                        // Contains utility classes for various operations
 │   │       │   ├── api/                      // Contains utility classes for API operations
 │   │       │   │   ├── ApiUtils.java
 │   │       │   │   ├── AssertionUtils.java
 │   │       │   │   ├── CommonMethods.java
 │   │       │   │   ├── HttpStatusCode.java
 │   │       │   │   └── JsonUtils.java
 │   │       │   ├── common/                   // Contains common utility classes
 │   │       │   │   ├── ConfigPropertyReader.java
 │   │       │   │   └── JsonReader.java
 │   │       │   └── ui/                       // Contains utility classes for UI operations
 │   │       │       ├── ConditionUtils.java
 │   │       │       ├── ElementActions.java
 │   │       │       ├── IframeActions.java
 │   │       │       ├── PopupActions.java
 │   │       │       ├── RetrievalUtils.java
 │   │       │       ├── TabActions.java
 │   │       │       ├── UiAssertionUtils.java
 │   │       │       └── WaitUtils.java
 │   │       └── App.java
 │   ├── test/                                // Test code for the application    
 │   │   ├── java/com/automation/framework/   // Contains test classes
 │   │   │   ├── Core/                        // Contains core test classes (BaseTest)
 │   │   │   │   ├── api/                      
 │   │   │   │   └── ui/                      
 │   │   │   |        └── BaseTest.java
 │   │   │   ├── JsonPlaceholder/    // Contains JsonPlaceholder API test classes for learning & practice purposes
 │   │   │   ├── ProjectA/
 │   │   │   ├── ProjectC/
 │   │   │   └── SampleTests/        // Contains sample test classes for learning & practice purposes
 │   │   │       ├── api/
 │   │   │       ├── mixed/
 │   │   │       └── ui/
 │   └── resources/             // Contains test resources
 │       ├── data/              // Contains test data files(e.g., JSON files)
 │       │   ├── api/
 │       │   ├── mix/
 │       │   └── ui/
 │       ├── suites/         //   Contains test suite files (e.g., XML files)
 │       │   ├── api/
 │       │   ├── mix/
 │       │   └── ui/
 │       └── config.properties  // Configuration file for the application
 ├── allure-results/            // Allure report results directory
 └── pom.xml                // Maven project file
 
