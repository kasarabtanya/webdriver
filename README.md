# webdriver
Driver is set for Google Chrome, Firefox & headless mode.
Project uses TestNG framework with XML test runner.
Log4J logging is added to a project.
Project contains E2E tests of pastebin.com site & E2E tests Google Cloud Pricing Calculator site.
Page Object pattern without Page Factory was selected for a project as significant number of locators used on Google Cloud Pricing Calculator page are dynamic.
BasicPaje.java & BasicTestngTests.java classes are introduced for common methods.
Methods of googlecloud package are mostly reusable.

To do list:
- refactor methods of pastebin (add Select instead of Actions)
- add test data to a Json file and add Json parser to tests
