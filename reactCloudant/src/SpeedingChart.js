import React from 'react';
import { useQuery } from '@tanstack/react-query';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';

import { Bar } from 'react-chartjs-2';

const SpeedingChart = ({ data, driverId}) => {

console.log("driverId: " + driverId)

  ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend
  );

  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
      title: {
        display: true,
        text: '',
      },
    },
  };

  const labels = ['Points', 'Fine'];

  const driverFines = data.Docs.reduce((fine, obj) => {
    if (obj.driverId === driverId) {
      fine.push(obj)
    }
    return fine;
  }, []); // 

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

  console.log("totalPoints: " + totalPoints)

  const datax = {
    labels,
    datasets: [
      {
        label: 'Fine',
        data: [totalPoints, totalFine], // pointsData.map((points) => points),
        backgroundColor: 'rgba(53, 162, 235, 0.5)',
      },
    ],
  };

  return <Bar options={options} data={datax} />;
}

export default SpeedingChart;