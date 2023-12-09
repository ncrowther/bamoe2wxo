/*
 Copyright 2023 IBM Corp.
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 http://www.apache.org/licenses/LICENSE-2.0
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

const express = require('express')
var https = require('https');
const cloudantLib = require('./database/cloudantDb.js')
const session = require('express-session')
const { CloudantV1 } = require('@ibm-cloud/cloudant')
const service = CloudantV1.newInstance()
const DBNAME = process.env.DBNAME
const fs = require('fs');

const app = express()

const bodyParser = require('body-parser')

app.use(bodyParser.json())


const port = process.env.PORT || 3000 //8080

// ////////////// Cloudant Setup //////////////////////
const dbName = 'wxodb'

// Parse URL-encoded bodies (as sent by HTML forms)
app.use(express.urlencoded({ extended: true }))

// Parse JSON bodies (as sent by API clients)
app.use(express.json())

// //////////////// Create Doc ////////////////////////
app.post('/doc', async (req, res) => {

  console.log('Write doc');

  const newDoc = req.body;

  const doc = {
    "_id": newDoc.driverId,
    "driverId": newDoc.driverId,
    "date": newDoc.date,
    "offenceType": newDoc.offenceType,
    "speedLimit": newDoc.speedLimit,
    "actualSpeed": newDoc.actualSpeed,
    "fine": newDoc.fine,
    "points": newDoc.points
  }

  await cloudantLib.createDoc(service, dbName, doc).then(function (ret) {

    console.error('[App] Created doc: ' + newDoc.driverId);

    res.status(200);
    res.send(doc);

  }, function (err) {
    console.error('[App] Cloudant DB Failure in create doc: ' + err)
    res.status(500);
    res.send(err);
  })

})

// //////////////// Update Doc ////////////////////////
app.post('/updateDoc', async (req, res) => {

  const docId = req.query.driverId;

  const updateDoc = req.body;

  console.log('Update doc: ' + docId)

  await cloudantLib.findById(service, dbName, docId).then(function (doc) {

    console.log('***Found ' + doc)

    doc.date = updateDoc.date,
    doc.offenceType = updateDoc.offenceType,
    doc.speedLimit = updateDoc.speedLimit,
    doc.actualSpeed = updateDoc.actualSpeed,
    doc.fine = updateDoc.fine,
    doc.points = updateDoc.points

    console.log('Updating: ' + JSON.stringify(doc));

    cloudantLib.updateDoc(service, dbName, doc)

    res.status(200);
    res.send(doc);

  }, function (err) {
    console.error('[App] Cloudant DB Failure in update: ' + err)
    res.status(500);
    res.send(err);
  })

})

// //////////////// Get Single Doc ////////////////////////
app.get('/doc', async (req, res) => {

  const docId = req.query.driverId;

  console.log('Get doc: ' + docId)

  await cloudantLib.findById(service, dbName, docId).then(function (doc) {

    console.log('***Found ' + doc)

    res.status(200);
    res.send(doc);

  }, function (err) {
    console.error('[App] Cloudant DB Failure in get doc: ' + err)
    res.status(500);
    res.send(err);
  })

})

// //////////////// Get Docs ////////////////////////
app.get('/docs', async (req, res) => {

  console.log('Get docs')

  await cloudantLib.findAllDocs(service, dbName).then(function (docs) {

    res.status(200);
    res.send(docs);

  }, function (err) {
    console.error('[App] Cloudant DB Failure in get docs: ' + err)
    res.status(500);
    res.send(err);
  })
})

app.listen(port, () => {
  console.info('[App] Listening on http://localhost:' + port)
})