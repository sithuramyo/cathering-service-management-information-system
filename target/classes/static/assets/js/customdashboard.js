
$(document).ready(function() {
    var lunchcount = $('#lunchcount').DataTable({
  buttons: [
            {
                extend: 'pdfHtml5',
                text: '<i class="fa fa-file-pdf-o"></i> ',
                className: 'btn btn-app export pdf',
                title:'Lunch Report',
                      exportOptions: {
                    columns: [0,1,2,3]
                }
               
            },{
                extend:    'excelHtml5',
        text:      '<i class="fa fa-file-excel-o"></i>',
       
        className: 'btn btn-app export excel',
          title:'Lunch Report',
                      exportOptions: {
                    columns: [0,1,2,3]
                }
            },
            {
                extend:    'print',
                text:      '<i class="fa fa-print"></i>',
                     className: 'btn btn-app export imprimir',
                 title:'Lunch Report',
                      exportOptions: {
                    columns: [0,1,2,3]
                }
            }
        ]
    });
    lunchcount.buttons().container().appendTo('#lunchcount_wrapper .col-md-6:eq(0) ');
    var lunchcost = $('#lunchcost').DataTable({
     buttons: [
            {
                extend: 'pdfHtml5',
                text: '<i class="fa fa-file-pdf-o"></i> ',
                className: 'btn btn-app export pdf',
                  title:'DAT,Employee Lunch Costing Report',
            },{
                extend:    'excelHtml5',
        text:      '<i class="fa fa-file-excel-o"></i>',
       
        className: 'btn btn-app export excel',
         title:'DAT,Employee Lunch Costing Report',
            },
            {
                extend:    'print',
                text:      '<i class="fa fa-print"></i>',
                     className: 'btn btn-app export imprimir',
                 title:'DAT,Employee Lunch Costing Report',
            }
        ]
    });


    lunchcost.buttons().container().appendTo('#lunchcost_wrapper .col-md-6:eq(0) ');
});
