
          $(document).ready(function(){
        	  var optable = $('#optable').DataTable({
        	        buttons: [
            {
                extend: 'pdfHtml5',
                text: '<i class="fa fa-file-pdf-o"></i> ',
                className: 'btn btn-app export pdf',
              title: 'Operator Report',
               exportOptions: {
                    columns: [0,1,2,3,4,5]
                }
            },{
                extend:    'excelHtml5',
        text:      '<i class="fa fa-file-excel-o"></i>',
       
        className: 'btn btn-app export excel',
         title: 'Operator Report',
          exportOptions: {
                    columns: [0,1,2,3,4,5]
                }
            },
            {
                extend:    'print',
                text:      '<i class="fa fa-print"></i>',
                     className: 'btn btn-app export imprimir',
                title: 'Operator Report',
                 exportOptions: {
                    columns: [0,1,2,3,4,5]
                }
            }
        ]
        	    });
        	  
        	    optable.buttons().container().appendTo('#optable_wrapper .col-md-6:eq(0)');
        	    
        	  var table1 = $('#table1').DataTable({
      	         buttons: [
            {
                extend: 'pdfHtml5',
                text: '<i class="fa fa-file-pdf-o"></i> ',
                className: 'btn btn-app export pdf',
                 title: 'Register Eaten Report',
            },{
                extend:    'excelHtml5',
        text:      '<i class="fa fa-file-excel-o"></i>',
       
        className: 'btn btn-app export excel',
         title: 'Register Eaten Report',
            },
            {
                extend:    'print',
                text:      '<i class="fa fa-print"></i>',
                     className: 'btn btn-app export imprimir',
                title: 'Register Eaten Report',
            }
        ]
      	    });
      	    table1.buttons().container().appendTo('#table1_wrapper .col-md-6:eq(0)');
      	    
      	  var table2 = $('#table2').DataTable({
  	         buttons: [
            {
                extend: 'pdfHtml5',
                text: '<i class="fa fa-file-pdf-o"></i> ',
                className: 'btn btn-app export pdf',
                 title: 'Register No-Eat Report',
            },{
                extend:    'excelHtml5',
        text:      '<i class="fa fa-file-excel-o"></i>',
       
        className: 'btn btn-app export excel',
        title: 'Register No-Eat Report',
            },
            {
                extend:    'print',
                text:      '<i class="fa fa-print"></i>',
                     className: 'btn btn-app export imprimir',
               title: 'Register No-Eat Report',
            }
        ]
  	    });
  	    table2.buttons().container().appendTo('#table2_wrapper .col-md-6:eq(0)');
  	    
        	    var table3 = $('#table3').DataTable({
        	         buttons: [
            {
                extend: 'pdfHtml5',
                text: '<i class="fa fa-file-pdf-o"></i> ',
                className: 'btn btn-app export pdf',
                title: 'Unregiser Eat Report',
            },{
                extend:    'excelHtml5',
        text:      '<i class="fa fa-file-excel-o"></i>',
       
        className: 'btn btn-app export excel',
          title: 'Unregiser Eat Report',
            },
            {
                extend:    'print',
                text:      '<i class="fa fa-print"></i>',
                     className: 'btn btn-app export imprimir',
                 title: 'Unregiser Eat Report',
            }
        ]
        	    });
        	    table3.buttons().container().appendTo('#table3_wrapper .col-md-6:eq(0)');

        	    var feedbackTable = $('#feedbackTable').DataTable({
               
        	    });
        	    feedbackTable.buttons().container().appendTo('#feedbackTable_wrapper .col-md-6:eq(0)');
        	});
