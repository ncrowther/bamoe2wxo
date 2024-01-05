import React from 'react';
import { Card } from 'primereact/card';

const TotalFines = ({ data, driverId, disqualified }) => {

  console.log("driverId: " + driverId)

  const driverFines = data.Docs.reduce((fine, obj) => {
    if (obj.driverId === driverId) {
      fine.push(obj)
    }
    return fine;
  }, []);

  console.log("driverFines: " + JSON.stringify(driverFines))

  var totalPoints = driverFines.reduce((counter, obj) => {
    if (obj.points) counter += obj.points
    return counter;
  }, 0);

  var totalFine = driverFines.reduce((counter, obj) => {
    if (obj.fine) counter += obj.fine
    return counter;
  }, 0);


  console.log("totalPoints: " + totalPoints)
  console.log("totalFine: " + totalFine)
  console.log("disqualified: " + disqualified)

  if (disqualified === 'true') {
    return (
      <Card title="DISQUALFIED" className="md:w-25rem" style={{ color: 'red' }}>
        <p className="m-0">
          <b>Fine: £{totalFine} </b>
          <br /> <br />
          <b>Points: {totalPoints}</b>
        </p>
      </Card>
    );
  } else {
    <Card title="Fine" className="md:w-25rem" style={{ color: 'black' }}>
      <p className="m-0">
        <b>Fine: £{totalFine} </b>
        <br /> <br />
        <b>Points: {totalPoints}</b>
      </p>
    </Card>
  }
}

export default TotalFines;