# WO API Generator for BAMOE

## Description

This utility generates a WatsonxOrchestrate OpenAPI spec for BAMOE

## Prerequisites

- Java 1.8 or later

## Usage

Download this repo and extract to a folder of your choice.

- Open a VSC project with a DMN decision service and run in dev mode.
- Using the built in VSC Port tab, expose the kogito service port (usually 8080).  Click on Visibility and set it to Public
- Open this project with eclipse.
- Edit ./data/config.json file and set the name your decision service, setting both internal and external kogito URL
- Generate the OpenAPI file by running the 'run' target in ./build.xml.  Alternatively execute 'run.bat' found in the top level folder.  
- Once finished, you should see the generated openAPI spec for the service in the ./generated folder
- Import the open API spec into WatsonXOrchestrate and run.

## Bugs and Limitations
Limited testing performed on DMN rule projects running on Kogito

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

