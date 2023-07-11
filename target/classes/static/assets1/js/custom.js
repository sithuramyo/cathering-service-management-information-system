$(document).ready(function() {
 if ($.fn.DataTable.isDataTable('#example')) {
  // Destroy the DataTable
  $('#example').DataTable().destroy();
}
  var table = $('#example').DataTable({
	scrollX: true,
	
    buttons: [

    {
                extend:    'excelHtml5',
        text:      '<i class="fa fa-file-excel-o"></i>',
       
        className: 'btn btn-app export excel',
         title: 'Monthly Invoice Report',
            
  customizeData: function (data) {
   var totalCost = updateTotalCost();
    var reportDate = new Date().toLocaleDateString(); 
  
    var customRow = ['Total cost for catering service:','', '', '', '', '','', totalCost.toString()+".0 MMK",'',''];
	var report = ['Report Date',reportDate, '', '', '', '', '','','','',''];
    data.body.push(customRow);
    data.body.push(report);

  }
},
 
{
                extend: 'pdfHtml5',
                text: '<i class="fa fa-file-pdf-o"></i> ',
                className: 'btn btn-app export pdf',
                 title: 'Monthly Invoice Report',
       
  customize: function (doc) {
    var totalCost = updateTotalCost(); // Retrieve the total cost value
// Get the current date and time
   var currentDate = new Date();
var currentDateOnly = currentDate.toLocaleDateString(); // Retrieve the current date in a readable format

doc.content.splice(0, 0, {
  text: "Report Date - "+currentDateOnly, // Add the current date
  fontSize: 10,
  bold: true,
  alignment: 'right',
  margin: [0, 0, 10, 0] // Adjust the margin as needed
});

 
doc.content.push({
  table: {
    widths: ['auto', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'],
    body: [
      [
        { text: 'Total Cost for Lunch Catering Service', fontSize: 11, bold: true, color: 'blue', alignment: 'left', colSpan: 5 },
        {},
        {},
        {},
        {},
        {},
        {},
        {text: totalCost.toString() + ".0 MMK", fontSize: 11, bold: true, color: 'blue', colSpan: 2, alignment: 'left' },
        {},
        {},
        {},
      ]
    ]
  },
  layout: 'noBorders',
  margin: [0, 10]
});

  }
}

,
 {
                extend:    'print',
                text:      '<i class="fa fa-print"></i>',
                     className: 'btn btn-app export imprimir',
                title: 'Monthly Invoice Report',
            
        customize: function(win) {
  var totalCost = updateTotalCost(); // Retrieve the total cost value
     	$(win.document.body).find('h1').remove();
     	var header = $('<h1>Monthly Invoice</h1>').css('text-align', 'center');
          $(win.document.body).find('head').append('<style>@media print { h1 { display: block !important; } }</style>');
          $(win.document.body).prepend(header);
        var tableFooter = '<tfoot><tr><td colspan="7">Total Cost :</td><td>'+ totalCost+'.0 MMK' + '</td><td></td><td></td><td></td></tr></tfoot>';
        
        $(win.document.body).find('table').append(tableFooter);
        
          var restaurantInfo = $('#restaurantInfo').clone().removeAttr('id').css({
            'text-align': 'left',
            'font-size': '16px',
            'margin-bottom': '20px'
          });

          $(win.document.body).find('table').before(restaurantInfo);
	}

      }
    ]
  });


  table.buttons().container().appendTo('#example_wrapper .col-md-6:eq(0)');


function updateTotalCost() {
  var totalCost = 0;
  var filteredData = table.rows({ search: 'applied' }).data();
  filteredData.each(function(value, index) {
    var amount = parseFloat(value[7]); // Assuming the amount is in the 8th column (index 7)
    totalCost += amount;
  });
  //$('#total_cost').text(totalCost.toFixed(2));
  console.log(totalCost);
  // Update the total cost in the table footer
//  var totalCostRow = '<tr><td colspan="7">Total Cost for Lunch Catering Service</td><td>' + totalCost.toFixed(2) + '</td><td></td><td></td><td></td></tr>';
//  $('#example tfoot').html(totalCostRow);

  return totalCost; // Return the total cost value
}

updateTotalCost();

});
