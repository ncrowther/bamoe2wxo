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
    if (obj.points) counter += obj.points
    return counter;
  }, 0);

  var totalFine = driverFines.reduce((counter, obj) => {
    if (obj.fine) counter += obj.fine
    return counter;
  }, 0);

  console.log("totalPoints: " + totalPoints)
  console.log("totalFine: " + totalFine)

  const content = <>
    <p className="m-0">
      <b>Fine: £{totalFine} </b>
      <br /> <br />
      <b>Points: {totalPoints}</b>
    </p>
  </>;

  const DQ_POINTS = 12;

  if (totalPoints >= DQ_POINTS) {
    return (
      <Card title="DISQUALIFIED" className="md:w-25rem" style={{ color: 'red' }}>
        {content}
      </Card>
    );
  } else {
    return (
      <Card title="Fine" className="md:w-25rem">
        {content}
      </Card>
    );
  }
};

export default TotalFines;