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