exports.findById = function findById(service, dbname, id) {

  return new Promise((resolve, reject) => {
    service.getDocument({
      db: dbname,
      docId: id
    }).then(response => {

      console.log('***Found doc ' + id)
      console.log(response.result);

      resolve(response.result);
    })
      .catch(err => {
        console.log(err)
        reject(err)
      })
  })
}

exports.createDoc = function createDoc(service, dbName, doc) {

  return new Promise((resolve, reject) => {

    // Create the document in Cloudant
    service.postDocument({
      db: dbName,
      document: doc
    }).then(response => {
      console.log(response.result);
      resolve(response.result);
    })
      .catch(err => {
        console.log(err)
        reject(err)
      })
  })
}

exports.updateDoc = function updateDoc(service, dbName, doc) {
  return new Promise((resolve, reject) => {

    // Update the doc record
    const updateDoc = {
      "_id": doc._id,
      "_rev": doc._rev,
      "driverId": doc.driverId,
      "date": doc.date,
      "offenceType": doc.offenceType,
      "speedLimit": doc.speedLimit,
      "actualSpeed": doc.actualSpeed,
      "fine": doc.fine,
      "points": doc.points
    }

    // Update the document in Cloudant
    service.postDocument({
      db: dbName,
      document: updateDoc
    }).then(response => {
      console.log(response.result);
      resolve(response.result);
    });

  }).catch((err) => {
    console.error('Error occurred: ' + err.message, 'updateDoc()');
    reject(err);
  });
}

exports.findAllDocs = function findAllDocs(service, dbName) {

  return new Promise((resolve, reject) => {
    // Get all docs
    service.postAllDocs({
      db: dbName,
      includeDocs: true,
      limit: 1000
    }).then(response => {
      console.log('***Found ' + response.result.rows)

      var cases = [];
      var i = 0;

      response.result.rows.forEach(function (doc) {

        console.log('***Doc ' + doc)

        var casedoc = doc.doc
        if (!casedoc._id.startsWith("_design")) {
          cases[i] = casedoc;
          i++;
        }
      })

      const docs = {
        "Docs": cases
      }

      resolve(docs);
    })
      .catch(err => {
        console.log(err)
        reject(err)
      })
  })
}