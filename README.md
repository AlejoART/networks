# Webpt Cucumber Starter

**Don't Use This Yet. It Is Still Being Worked Out**

This is a starter project for Cucumber / Selenium automated tests.


### Known Issue / Things That Still Need to Be Accomplished

* Logging is not working quite right. log4j.properties isn't controlling all logs.
* Image capture and attaching to reports
* Evaluate if Pico container should be used. How would it work with PageObject Factory.
* Break the atf folder out into it's own reusable project. 
    * This may present challenges with where configuration files lives.
  

## Getting Started

If you were to use the project now in it's current state, you would just copy the entire project, and replace
all the `starter` package (com.webpt.testing.stater) with your individual project stuff.

## Executing Testing

Tests can be run with the maven `verify` command

    mvn clean verify
    

## Creating Tests

When creating new tests you should follow a good scalable test class layout. 

#### Use Good Design

1. Use page objects to interact with your pages. This is the only place that element selectors and page knowledge should
live.
2. Don't put all the logic in your step definitions. These methods should be very small. If you are using more then a 
couple lines of code then consider moving the logic into a reuseable workflow / sub step class.
3. Put business logic into reusable workflow or sub step classes.
 
#### Components

When creating new tests the following components are recommended:

1. Create a .feature file in the test/resources/features folder
2. Create a step/definition class which maps your bdd steps in your feature file to code.
3. Create a page class which knows how to interact with the elements on a page. Each page should have it's own page 
object.
4. Create a workflow class for complex logic or reusable workflows that may interact with multiple pages or multiple 
elements on a single page.
5. Put your configuration (urls, usernames, resource information, etc...) in an application.properties file in the 
test/resources directory.
6. Add configuration accessor methods in a Config class which extends the WebDriverConfiguration class. 

#### Controlling Execution

By default the starter project is setup to automatically run each feature in it's own parallel process. You control
how many threads (parallel processing) you want to allow to run with the `threads` parameter.

    mvn clean verify -Dthreads=5
    
This is accomplished by using the `com.github.temyers.cucumber-jvm-parallel-plugin` to dynamically create a cucumber 
runner class for each feature file and using `mave failsafe pluggin` to execute the tests in parallel. This is convenient
and probably acceptable in many cases. 

**If you need more control** 
 
The framework uses TestNG for test execution. You can choose to configure a TestNG xml file which specifies execution
order. To do this:

1. Create Test runner classes for each of your features. You should probably still extend the BaseRunner class for \
convenience.
2. Create a testng.xml file which specifies how you want your tests to execute.
3. Remove the cucumber-jvm-parallel-plugin pluggin from the pom.xml
4. Edit the maven-failsafe-plugin in the pom.xml to point your testng.xml

## Configuration

Configuration is built using the [Apache Commons default configuration builder](https://commons.apache.org/proper/commons-configuration/userguide_v1.10/howto_configurationbuilder.html).

The primary configuration is webpt-config.xml. It has been setup to load configuration in this order: 
1. Environment variables
2. System properties
3. A user specified properties file. You specific this by setting the ENV_FILE environment variable.
4. `application.properties`
5. `web-driver-config.xml` file 

This allows you to specify / override properties a number of ways. 

Your project specific settings should go into your `application.properties` file. You could then create environment 
specific files called: sdev.properties, prod.properties, etc... then at execution time specify which environment file
to use:

    mvn clean verify -DENV_FILE=sdev.properties

## Project Layout

#### com.webpt.testing.atf

The `atf` folder contains all framework related code. Eventually the plan is to move this code out
into its own project to be an importable dependancy for any webpt Cucumber Selenium test project

#### com.webpt.testing.starter

This is an example project. It contains a config specific to it's needs and page objects.

 
## Reporting

The project has been setup to generate Cucumber reports with the 
[net.masterthought.maven-cucumber-reporting](https://github.com/damianszczepanik/maven-cucumber-reporting) plugin.
Reports are generated in the `target/cucumber/cucumber-html-reports` folder.
