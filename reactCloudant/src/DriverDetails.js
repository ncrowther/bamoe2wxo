import React from 'react';
import { Card } from 'primereact/card';

const DriverDetails = ({ driverId }) => {

  console.log("driverId: " + driverId)

  return (
    <Card title="Driver Details" className="md:w-25rem" >

    <b>Driver Id: {driverId} </b>
    <br />
    <br />Donald Driver
    <br />45 Daisybank Drive
    <br />Manchester MA1 5FP

  </Card>
  );
};

export default DriverDetails;