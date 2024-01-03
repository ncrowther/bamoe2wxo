import React from 'react';
import { Card } from 'primereact/card';

const VehicleDetails = ({ driverId }) => {

  console.log("driverId: " + driverId)

  return (
    <Card title="Vehicle" className="md:w-25rem" >
    RF34HSH 
    <br/>
    Ford Focus
    <br/>
    Green 
  </Card>
  );
};

export default VehicleDetails;