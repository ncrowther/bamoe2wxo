# RefundUI
React speeding ticket UI for wxo open source lab

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

Install:
### `npm install`

Start locallly:
### `npm start`

Run app in development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### Deploying to Code Engine

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

10.	Open the URL using the IBM Cloud Code Engine route for the application

## Learn More about React

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).