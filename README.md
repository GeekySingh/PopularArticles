# PopularArticles
Android app built on MVP pattern to show popular article list using NY Times article (public) api.

![Screenshot](https://github.com/gaganpreet4423/PopularArticles/blob/master/image/list.png)
![Screenshot](https://github.com/gaganpreet4423/PopularArticles/blob/master/image/detail.png)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Checkout code from below repository

```
https://github.com/gaganpreet4423/PopularArticles.git
```

## Project MVP Overview

MVP stands for Model-View-Pattern. Both model and views are independent entities and they don't know about each-other. Presenter work as a communicator between these model and view. View can request data from presenter, presenter fetches data (local/network) and then return back data to view to display it.

In this project, ArticlePresenter is data presenter between ArticleListActivity(view) and ArticleDataModel(model). ArticleListActivity requests article data list from ArticlePresenter and after getting data from network, presenter returns list of articles to the caller, i.e. ArticleListActivity.


## Build project

To build the project, use make command:

```
./gradlew build
```

This will compile code and reports if any build error found.


## Running the tests

To execute the test cases, make sure you have attached device to your machine and USB debugging is on.

```
./gradlew testDebugUnitTest
```

This will compile code and executes all unit test cases and will report if any test case failed.

## Build script commands

Here are few custom commands with their usage

1. make - To build the project. You can use these command with gradle as mentioned below:

```
./gradlew make
```

2. analyze - Executes static code analysis using lint to check for structural code problems that could affect the quality and performance of your app.

```
./gradlew analyze
```

3. createTestReport - Executes all the unit test cases and generates code coverage report. You can find test case and code coverage report under {WORKSPACE}/reports

```
./gradlew createTestReport
```

4. automate - This command automates all these three commands in sequence. First it builds the project, then it analyzes for any lint warning or errors and then it executes all test cases and generates code coverage report.

```
./gradlew automate
```

## Sonarqube report

SonarQube provides the capability to not only show health of an application but also to highlight issues newly introduced. With a Quality Gate in place, you can fix the leak and therefore improve code quality systematically.

To check the code quality using sonarqube, use below command:

```
./gradlew sonarqube
```
Sonarqube command is dependent on automate command. So whenever we execute sonarqube command, it first compiles and generates test case/code coverage reports and using those reports, give a summary of overall code.

Here is a sample sonarqube report of this project:

![Screenshot](https://github.com/gaganpreet4423/PopularArticles/blob/master/image/sonarqube.png)

## Authors

* **Gaganpreet Singh** - *Initial work* - [Profile](https://github.com/gaganpreet4423)
