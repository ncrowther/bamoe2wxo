import { useQuery } from '@tanstack/react-query';
import DriverDetails from './DriverDetails.js';
import VehicleDetails from './VehicleDetails.js';
import SpeedingTickets from './SpeedingTickets.js';
import TotalFines from './TotalFines.js';
import { Panel } from 'primereact/panel';
import { Divider } from 'primereact/divider';
import queryString from 'query-string';
import "primereact/resources/themes/lara-light-cyan/theme.css";

const Fine = () => {
  const queryStringParams = queryString.parse(window.location.search);
  console.log("***queryStringParams.id: " + queryStringParams.id)

  const { data, isLoading, error } = useQuery({
    queryFn: () =>
      fetch('https://dataservice.1apbmbk49s5e.eu-gb.codeengine.appdomain.cloud/docs', { mode: 'cors' }).then(
        (res) => res.json()
      ),
    queryKey: [''],
  });

  // Show a loading message while data is fetching
  if (isLoading) {
    return <h2>Loading...</h2>;
  }

  // to handle error
  if (error) {
    return <div className="error">Error fetching</div>
  }

  return (

    <Divider>
      <Divider>

        <Panel header="" class="p-panel-title ml-2 text-primary">
          <img style={{ width: 100, height: 120 }} align="right" src="police.jpg" alt="Police" />
          <DriverDetails driverId={queryStringParams.driverId} />
        </Panel>
        <br />

        <Panel header="" class="p-panel-title ml-2 text-primary">
        <VehicleDetails driverId={queryStringParams.driverId} />
        </Panel>
        <br />        

        <Panel header="" class="p-panel-title ml-2 text-primary">
          <SpeedingTickets data={data} driverId={queryStringParams.driverId} />
        </Panel>

        <br />
        <Panel header="" class="p-panel-title ml-2 text-primary">
          <TotalFines data={data} driverId={queryStringParams.driverId} />
        </Panel>

      </Divider>
    </Divider>

  );
};

export default Fine;

