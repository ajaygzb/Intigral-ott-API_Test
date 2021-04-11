# Intigral-ott-API_Test
Assignment Test

Description
This project contains Inteigral-ott.net popcorn API verification and validation Test Cases.
Architecture
This is a Maven project developed using Java Rest Assured and TestNG.
- Maven take cares of dependency jars and plugins.
- Allows easy accessing methods to validate JSON Response.
- Provides parallel run option.
- Provides access to produce user defined Test Reports.
- Update assertion values from properties files thus reducing change required to alter the code.
- 
Prerequisite
Java 8,Maven,TestNG
Run-Command

1.	To run the Test on Non-Prod
mvn clean install -Denv=nonProd -Dsuite=PopcornAPI -DthreadCount=1
2.	To run the test on Pre-Prod
mvn clean install -Denv=PreProd -Dsuite=PopcornAPI -DthreadCount=2
** Thread Count is used for parallel running.
Report

The framework will generate 2 test reports
1. Extent Report
Path : project.dir/reports/Intigral-ott-popcorn-report.html

2.	TestNG default Report
path : project.dir/target/surefire-reports/index.html


