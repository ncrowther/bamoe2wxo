# Cloudant Facade for WXO

This package creates a datafacade into a Cloudant database.  
The facade is exposed as an OpenAPI where it can be ingested by WatsonxOrchestrate. 
See:

 [OpenApi](\openapi\dataApi.yaml)

### Local host Prerequisites

 * Create IBM Cloud account with Cloudant.  See https://cloud.ibm.com/docs/Cloudant?topic=Cloudant-getting-started-with-cloudant 
 
 * Create a Cloudant database with name wxodb

 * Create credentials for next step

### Run on local host

 Edit setenv.bat to cloudant credentials (see prereqs)
 Open DOS prompt (***NOT powershell***).  Enter:

 ```sh
   setenv.bat
```

 Start a local nodejs server: 
 
```sh
   npm start
```

### Code Engine Hosting Prerequisites

 * Follow Local Host Prerequisites above
 
 * Create IBM Cloud account with Code Engine. 

 * Create a code engine project (eg name sample)

 * Create a code engine configmap configuration containing Cloudant credentials: CLOUDANT_APIKEY=XXX, CLOUDANT_URL=XXX, DBNAME=wxodb

 * Assign the config map to the Code Engine project

### Deployment to code engine on IBM Cloud

1.	Open Git Bash shell from VSC

2.	Login to IBM Cloud.

    ibmcloud login --sso

3.	In the IBM Cloud console, go to Manage > Account > Account resources > Resource groups.  Select the resource group for Code Engine. E.g. default

    ibmcloud target -g default

4.	Select the code engine project:  

    ibmcloud ce project select -n [PROJECT_NAME]

5.	Start Docker Desktop

7.	Within this folder, edit CEbuild.sh and CErun.sh and change the REGISTRY to your Docker registry.

8.	Using the same bash shell, deploy the sample application to your docker repo:

./CEbuild

9.	Deploy the application to Code Engine on IBM Cloud. From the app's folder do:

./CErun

10.	Open the URL using the IBM Cloud Code Engine route for the refunds application


## License

Copyright (c) 2024 IBM Corporation

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

