# WO API Generator for BAMOE

## Description

This utility generates a WatsonxOrchestrate OpenAPI spec for BAMOE

## Prerequisites

- Java 1.8 or later

## Usage

Download this repo and extract to a folder of your choice.

- Open a VSC project with a DMN decision service and run in dev mode.
- Using the built in VSC Port tab, expose the kogito service port (usually 8080).  Click on Visibility and set it to Public
- Open the project in this directory with eclipse IDE.
- Edit ./data/config.json file and set the decisionId to the name of your decision service (not the filename).  Also set both internal and external URLs to the URL of your running Kogito service.
- Save the config.json file
- Generate the OpenAPI file by running the 'run' target in ./build.xml.  Alternatively execute 'run.bat' found in the top level folder.  
- Once finished, you should see the generated openAPI spec for the service in the ./generated folder
- Login to WatsonXOrchestrate
- Under skills, select add skills from Files then select the generated open API spec
- Edit and save as draft
- Enhance and Publish the skill
- Import the skill into your home skills and select it to run
- optional enhancement - use https://icomoon.io to create a free svg icon to embed into the generated Open API under the x-ibm-application-name tag

## Bugs and Limitations
Limited testing performed on DMN rule projects running on Kogito

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

