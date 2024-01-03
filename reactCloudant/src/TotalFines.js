import React from 'react';
import { Card } from 'primereact/card';

const TotalFines = ({ data, driverId }) => {

  console.log("driverId: " + driverId)

  const driverFines = data.Docs.reduce((fine, obj) => {
    if (obj.driverId === driverId) {
      fine.push(obj)
    }
    return fine;
  }, []); 

  console.log("driverFines: " + JSON.stringify(driverFines))

  var totalPoints = driverFines.reduce((counter, obj) => {
    counter += obj.points
    return counter;
  }, 0);

  var totalFine = driverFines.reduce((counter, obj) => {
    counter += obj.fine
    return counter;
  }, 0);

  console.log("totalPoints: " + totalPoints)
  console.log("totalFine: " + totalFine)

  return (
  <Card title="Penalty" className="md:w-25rem" >
    <b>Fine: £{totalFine} </b>
    <br/> <br/>
    <b>Points: {totalPoints} </b>
  </Card>
  );
};

export default TotalFines;