// Replace `dates` with the appropriate array that contains the dates corresponding to your transaction data
var transaction_x = transactions.map(x => x["date"])

var donutChartOptions = {
  series: [1,2,5],
 chart: {
    type: "donut", // change type to "donut"
    background: "",
    height: 350,
    animations: {
      enabled: true,
      dynamicAnimation: {
        enabled: true,
        speed: 500 // Animation duration in milliseconds
      }
    }
  },
  labels: ["Registered E", "Registered N", "Unregistered E"],
  dataLabels: {
    enabled: false,
  },
  legend: {
    labels: {
      colors: "#000",
    },
    show: true,
    position: "top",
  },
  tooltip: {
    shared: true,
    intersect: false,
    theme: "dark",
  },
};

var donutChart = new ApexCharts(document.querySelector("#donut-chart"), donutChartOptions);
donutChart.render();


 function handleDateSelection() {
    const selectedDate = dateInput.value;
    console.log(selectedDate);

    // Find the index of the selected date in the dates array
    const index = transaction_x.indexOf(selectedDate);
    console.log(index);
    if (index !== -1) {
      // Get the data for the selected date from the transactions array
      // Retrieve data for the selected date from the database
      const selectedTransaction = transactions[index];
      const values = [selectedTransaction.re, selectedTransaction.rne, selectedTransaction.ue];

      // Update the donut chart with the selected data
      donutChart.updateSeries(values);
    } else {
      // Handle the case when no data is available for the selected date
      // For example, clear the chart or show a message indicating no data
      donutChart.updateSeries([]); // Clear the chart
    }
  }

  // Add an event listener to the date input field
  const dateInput = document.getElementById('date-input');
  dateInput.addEventListener('change', handleDateSelection);

  // Set the initial date value from the date input field
 // const initialDate = dateInput.value;

  // Call the handleDateSelection function with the initial date value
  handleDateSelection();



//Costing Report

var lunchcosttransaction_x = lunchcosttransaction.map(x => x["dates"]);
const dates2 = lunchcosttransaction_x;
var transaction_dat = lunchcosttransaction.map(x => (x["extrapax"] + x["quantity"]) * x["datcost"]);
var transaction_emp = lunchcosttransaction.map(x => (x["extrapax"] + x["quantity"]) * x["employeecost"]);
console.log(dates2);


const data = {
  labels: dates2,
  datasets: [
    {
      label: 'Dat Cost',
      backgroundColor: 'rgba(54, 162, 235, 0.5)',
      borderColor: 'rgba(54, 162, 235, 1)',
      borderWidth: 1,
      type: 'bar',
      data: transaction_dat
    },
    {
      label: 'Employee Cost',
      backgroundColor: 'rgba(153, 102, 255, 0.5)',
      borderColor: 'rgba(153, 102, 255, 1)',
      borderWidth: 1,
      type: 'bar',
      data: transaction_emp
    }
  ]
};

const config = {
  type: 'bar',
  data,
  options: {
    scales: {
      y: {
        beginAtZero: true
      }
    }
  }
};

const myChart = new Chart(
  document.getElementById('lunchcostbarchart'),
  config
);

function filterDateForLunchCost() {
  const startdate = document.getElementById('lunchcoststartdate');
  const enddate = document.getElementById('lunchcostenddate');
console.log(startdate+" "+enddate);
  const startDateValue = startdate.value;
  const endDateValue = enddate.value;

  // Find the index of the start date and end date within dates2
  const indexStartDate = dates2.findIndex(date => date >= startDateValue);
  const indexEndDate = dates2.findIndex(date => date > endDateValue);
  const endIndex = (indexEndDate !== -1) ? indexEndDate : dates1.length;
  // Slice the arrays to show only the selected data based on the start date and end date
  const filterDates = dates2.slice(indexStartDate, endIndex);
  const filterTransactionDat = transaction_dat.slice(indexStartDate, endIndex);
  const filterTransactionEmp = transaction_emp.slice(indexStartDate, endIndex);

  myChart.data.labels = filterDates;
  myChart.data.datasets[0].data = filterTransactionDat;
  myChart.data.datasets[1].data = filterTransactionEmp;

  myChart.update();
}


//Lunch Report
var transaction_x = transactions.map(x => x["date"])
var transaction_re = transactions.map(x => x["re"])
var transaction_rne = transactions.map(x => x["rne"])
var transaction_ue = transactions.map(x => x["ue"])
const dates1 = transaction_x
console.log(dates1);
const data1 = {
  labels: dates1,
  datasets: [
{
      label: 'Register Eat',
      backgroundColor: 'rgba(54, 162, 235, 0.5)',
      borderColor: 'rgba(54, 162, 235, 1)',
      borderWidth: 1,
      data: transaction_re
    },
    {
      label: 'Register No Eat',
      backgroundColor: 'rgba(153, 102, 255, 0.5)',
      borderColor: 'rgba(153, 102, 255, 1)',
      borderWidth: 1,
      data: transaction_rne
    },
    {
      label: 'UnRegister Eat',
      backgroundColor: 'rgba(255, 99, 132, 0.5)',
      borderColor: 'rgba(255, 99, 132, 1)',
      borderWidth: 1,
      data: transaction_ue
    }
  ]
};

const config1 = {
  type: 'bar',
  data: data1,
  options: {
    scales: {
      y: {
        beginAtZero: true
      }
    }
  }
};

const myChart1 = new Chart(
  document.getElementById('bar-chart'),
  config1
);

function filterDate() {
  const startdate = document.getElementById('lunchcountstartdate');
  const enddate = document.getElementById('lunchcountenddate');
  console.log("hello" + startdate + " " + enddate);

  const indexstartdate = dates1.findIndex(date => date >= startdate.value);
  const indexenddate = dates1.findIndex(date => date > enddate.value);

  // Adjust the index for the end date
  const endIndex = (indexenddate !== -1) ? indexenddate : dates1.length;

  const filterDates = dates1.slice(indexstartdate, endIndex);
  const filterTransaction_re = transaction_re.slice(indexstartdate, endIndex);
  const filterTransaction_rne = transaction_rne.slice(indexstartdate, endIndex);
  const filterTransaction_ue = transaction_ue.slice(indexstartdate, endIndex);

  myChart1.data.labels = filterDates;
  myChart1.data.datasets[0].data = filterTransaction_re;
  myChart1.data.datasets[1].data = filterTransaction_rne;
  myChart1.data.datasets[2].data = filterTransaction_ue;

  myChart1.update();
}

function initializeDateRange() {
//  const startdate = document.getElementById('startdate');
//  const enddate = document.getElementById('enddate');

  filterDate();
//    const lunchcoststartdate = document.getElementById('lunchcoststartdate');
//  const lunchcostenddate = document.getElementById('lunchcostenddate');
 filterDateForLunchCost();
}






