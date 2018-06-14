# 1. Infrastructure overview
Vicinity Adapter for Abnormal Detection Value Added Service (3.1.3) of MPH Pilot Case.

# 2. Configuration and deployment
## Build using Maven

In the root folder of the project:

`mvn clean install`

## Run
The adapter is the interface of this VAS to Vicinity. In order to run this VAS, in the target folder of the project:

`java -jar adapter-0.0.1-SNAPSHOT.jar`

# 3. Functionality and API

## Functions
*	Send device TDs to the vicinity agent at start up
