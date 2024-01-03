import "tw-elements-react/dist/css/tw-elements-react.min.css";
import { Card } from 'primereact/card';

const SpeedingTickets = ({ data, driverId}) => {

  const driverFines = data.Docs.reduce((fine, obj) => {
    if (obj.driverId === driverId) {
      fine.push(obj)
    }
    return fine;
  }, []); // 



  console.log("driverFines: " + JSON.stringify(driverFines))

  return (
    <Card title="Speeding Offence(s)" className="md:w-25rem" >
    
    <table className="min-w-full text-left text-sm font-light">
      <thead 
        className="border-b bg-neutral-50 font-medium dark:border-neutral-500 dark:text-neutral-800">
        <tr>
          <th scope="col" className="px-6 py-4">DriverId</th>
          <th scope="col" className="px-6 py-4">Date</th>
          <th scope="col" className="px-6 py-4">Offence</th>
          <th scope="col" className="px-6 py-4">Limit</th>
          <th scope="col" className="px-6 py-4">Speed</th>
          <th scope="col" className="px-6 py-4">Fine</th>
          <th scope="col" className="px-6 py-4">Points</th>

        </tr>
      </thead>
      <tbody>

        {driverFines.map((doc) => (
          <tr className="border-b dark:border-neutral-500">
            <td className="whitespace-nowrap px-6 py-4 font-medium">{doc.driverId}</td>
            <td className="whitespace-nowrap px-6 py-4">{doc.date}</td>
            <td className="whitespace-nowrap px-6 py-4">{doc.offenceType}</td>
            <td className="whitespace-nowrap px-6 py-4">{doc.speedLimit}</td>
            <td className="whitespace-nowrap px-6 py-4">{doc.actualSpeed}</td>
            <td className="whitespace-nowrap px-6 py-4">{doc.fine}</td>
            <td className="whitespace-nowrap px-6 py-4">{doc.points}</td>
          </tr>

        ))}

      </tbody>
    </table>
    </Card>

  );
};
export default SpeedingTickets;

