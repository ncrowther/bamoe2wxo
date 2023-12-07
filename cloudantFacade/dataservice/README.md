# Litigation Finance Demo

This package creates a datafade into a Cloudant database.  
The facade is exposed as an OpenAPI where it can be ingested by WatsonxOrchestrate. 
The dataservice is a Nodejs Database facade on top of Cloudant.  It uses an OpenAPI spec with which to invoke it. 
See:

 [OpenApi](\openapi\dataApi.yaml)

### Local host Prerequisites

 * Create IBM Cloud account with Cloudant.  See https://cloud.ibm.com/docs/Cloudant?topic=Cloudant-getting-started-with-cloudant 
 
 * Create a Cloudant database with name wxodb

 * Create credentials for next step

### Run on local host

 Edit setenv.bat to cloudant credentials (see prereqs)
 Open DOS prompt (NOT powershell).  Enter:

 ```sh
   setenv.bat
```

 Start a local nodejs server: 
 
```sh
   npm start
```

### Code Engine Hosting Prerequisites

 * Follow Local Host Prereqs
 
 * Create IBM Cloud account with Code Engine. 

 * Create a code engine project (eg name sample)

 * Create a code engine configmap configuration containing Cloudant credentials: CLOUDANT_APIKEY=XXX, CLOUDANT_URL=XXX, DBNAME=katchdb

 * Assign the config map to the Code Engine project

### Deployment to code engine on IBM Cloud

1. Open Git Bash shell from VSC

2. Login to IBM Cloud.

```sh
   ibmcloud login --sso
```

3. Select an account that has CodeEngine.  E.g. ITZ0V2

4. Select the code engine project: e.g 

```sh
ibmcloud ce project select -n [PROJECT_NAME]
```

5. Target a organization and space in which you have access to Code Engine:
  
  ```sh
ibmcloud target  -g code-engine-270007dduu-su48ub9k
```

to target org/space for IBM Tech zone. 

6. Start Docker Desktop

7. Edit build.sh and run.sh and configure the repo to conform to your Docker repo  

8. Open a bash shell in VSC.  Deploy the sample application to your docker repo. From the app's folder do:

  ```sh
  ./build
  ```

9. Edit setenv.sh to conform to your Cloudant database 

10. Run setenv.sh

  ```sh
  ./setenv.sh
  ```

11. Using the same bash shell, deploy the application to Code Engine on IBM Cloud. From the app's folder do:

  ```sh
  ./run
  ```

12. Open your IBM Cloud app route in the browser.


## License

Copyright (c) 2019 IBM Corporation

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

