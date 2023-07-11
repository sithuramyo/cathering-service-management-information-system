
    $(document).ready(function(){
      var doortable = $('#doortable').DataTable({
            buttons: [
            {
                extend: 'pdfHtml5',
                text: '<i class="fa fa-file-pdf-o"></i> ',
                className: 'btn btn-app export pdf',
                title:'Doorlog Report'
            },{
                extend:    'excelHtml5',
        text:      '<i class="fa fa-file-excel-o"></i>',
       
        className: 'btn btn-app export excel',
       title:'Doorlog Report'
            },
            {
                extend:    'print',
                text:      '<i class="fa fa-print"></i>',
                     className: 'btn btn-app export imprimir',
            title:'Doorlog Report'
            }
        ]
        });
      doortable.buttons().container().appendTo('#doortable_wrapper .col-md-6:eq(0)');
        
      var holidaytable = $('#holidaytable').DataTable({
          buttons: [
            {
                extend: 'pdfHtml5',
                text: '<i class="fa fa-file-pdf-o"></i> ',
                className: 'btn btn-app export pdf',
               title:'Holiday Report'
            },{
                extend:    'excelHtml5',
        text:      '<i class="fa fa-file-excel-o"></i>',
       
        className: 'btn btn-app export excel',
       title:'Holiday Report'
            },
            {
                extend:    'print',
                text:      '<i class="fa fa-print"></i>',
                     className: 'btn btn-app export imprimir',
                 title:'Holiday Report'
            }
        ]
      });
      holidaytable.buttons().container().appendTo('#holidaytable_wrapper .col-md-6:eq(0)');
      
    var avoidmeattable = $('#avoidmeattable').DataTable({
      buttons: [
            {
                extend: 'pdfHtml5',
                text: '<i class="fa fa-file-pdf-o"></i> ',
                className: 'btn btn-app export pdf',
             title:'Avoid Meat Report',
              exportOptions: {
                    columns: [0,1]
                }
            },{
                extend:    'excelHtml5',
        text:      '<i class="fa fa-file-excel-o"></i>',
       
        className: 'btn btn-app export excel',
        title:'Avoid Meat Report',
        exportOptions: {
                    columns: [0,1]
                }
            },
            {
                extend:    'print',
                text:      '<i class="fa fa-print"></i>',
                     className: 'btn btn-app export imprimir',
             title:'Avoid Meat Report',
             exportOptions: {
                    columns: [0,1]
                }
            }
        ]
  });
    avoidmeattable.buttons().container().appendTo('#avoidmeattable_wrapper .col-md-6:eq(0)');
  
     
    });
    
