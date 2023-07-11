$(document).ready(function() {
  // Initialize all tables with the class "data-table"
 var table = $('#feedbackTable').DataTable();

  if ($.fn.DataTable.isDataTable('#feedbackTable')) {
    table.clear().destroy();
  }

  table = $('#feedbackTable').DataTable({
    // Updated configuration options
    buttons: ['copy', 'csv', 'excel', 'pdf', 'print']
  });
});
