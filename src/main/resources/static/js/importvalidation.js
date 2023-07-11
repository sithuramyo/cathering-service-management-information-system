
function importMenu() {
  var fileInput = document.getElementById('menuFile');
  var menuFile = fileInput.files[0];
  if (!menuFile) {
    iziToast.error({
      title: 'Error',
      message: 'Please select a file.',
      position: 'topCenter',
      backgroundColor: 'red',
      messageColor: 'white',
      timeout: 3000
    });
    var toast = document.querySelector('.iziToast');
    toast.style.top = '55px';
    return;
  }
  var fileExtension = menuFile.name.split('.').pop().toLowerCase();
  var allowedExtensions = ['jpeg', 'jpg', 'png', 'pdf'];

  if (allowedExtensions.indexOf(fileExtension) === -1) {
    iziToast.error({
      title: 'Error',
      message: 'Invalid File Type',
      position: 'topCenter',
      backgroundColor: 'red',
      messageColor: 'white',
    });
    var toast = document.querySelector('.iziToast');
    toast.style.top = '55px';
    return;
  }

  var formData = new FormData();
  formData.append('menuFile', menuFile);

  $.ajax({
    url: '/api/admin/upload',
    type: 'POST',
    data: formData,
    processData: false,
    contentType: false,
    success: function(response) {
         iziToast.success({
                title: 'Success',
                message: 'File uploaded successfully.',
                position: 'topCenter',
                backgroundColor: 'green',
                messageColor: 'white',
              });
      $('#uploadedPhoto').attr('src', response.url);
      $('#uploadMessage').text('');
      setTimeout(function() {
          window.location.href = '/';
      }, 7000);
    },
    error: function(xhr, status, error) {
      iziToast.error({
        title: 'Error Uploading File',
        message: 'Please try again.',
        position: 'topCenter',
        backgroundColor: 'red' ,
        messageColor: 'white',  
      });
      var toast = document.querySelector('.iziToast');
      toast.style.top = '55px';
    }
  });
}

  function importAvoidMeat() {
    var avoidMeat = document.getElementById('avoidmeat').value;
    
    if (avoidMeat.trim() === '') {
      console.log('Avoid');
      iziToast.error({
          title: 'Error',
        message: 'Please avoid meat.',
        position: 'topCenter',
        timeout: 3000,
        backgroundColor: 'red' ,
         messageColor: 'white',      
      });
      var toast = document.querySelector('.iziToast');
      toast.style.top = '55px';
      return;
    }
    var regex = /^[A-Za-z\s]+$/; 
    if (!regex.test(avoidMeat)) {
      console.log('Invalid input');
      iziToast.error({
        title: 'Error',
        message: 'Invalid input.',
        position: 'topCenter',
        timeout: 3000,
        backgroundColor: 'red',
        messageColor: 'white'
      });
      var toast = document.querySelector('.iziToast');
      toast.style.top = '55px';
      return;
    }
    var formData = new FormData();
    formData.append('meatName', avoidMeat);
    
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/api/admin/importavoidMeat', true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 && xhr.status === 200) {
        var response = xhr.responseText;
        
        if (response === 'Success') {
          console.log('Import successful');
          iziToast.success({
              title: 'Success',
              message: 'Import successful.',
              position: 'topCenter',
              timeout: 3000,
            }); 
            var toast = document.querySelector('.iziToast');
            toast.style.top = '55px';
              
        
        } else {
          var errors = response.split(',');
          errors.forEach(function(error) {
              console.log('Error1',error);
            iziToast.error({
                 title: 'Error',
              message: error,
              position: 'topCenter',
              backgroundColor: 'red' ,
              messageColor: 'white',
              timeout: 5000
            });
            var toast = document.querySelector('.iziToast');
            toast.style.top = '55px';
          });
        }
      
        
      }
    };
    xhr.send(formData);
  }

  function importEmployee() {
  // Show the loading div
  document.querySelector('.modal-overlay').style.display = 'flex';

  var fileInput = document.getElementById('employeeFile');
  var employeeFile = fileInput.files[0];
  console.log('EmployeeFile Name', employeeFile);
  if (!employeeFile) {
    iziToast.error({
      title: 'Error',
      message: 'Please select a file.',
      position: 'topCenter',
      backgroundColor: 'red',
      messageColor: 'white',
      timeout: 3000
    });
    var toast = document.querySelector('.iziToast');
    toast.style.top = '55px';
    return;
  }

  var formData = new FormData();
  formData.append('employeeFile', employeeFile);

  $.ajax({
    url: '/api/admin/importEmployee',
    type: 'POST',
    data: formData,
    processData: false,
    contentType: false,
    success: function(response) {
      // Hide the loading div
      document.querySelector('.modal-overlay').style.display = 'none';

      if (response === 'Success') {
        console.log('Import successful');
        iziToast.success({
          title: 'Success',
          message: 'Import successful.',
          position: 'topCenter',
          timeout: 3000,
        });
        var toast = document.querySelector('.iziToast');
        toast.style.top = '55px';
      } else {
        var errors = response.split(',');
        errors.forEach(function(error) {
          console.log('Error1', error);
          iziToast.error({
            title: 'Error',
            message: error,
            position: 'topCenter',
            backgroundColor: 'red',
            messageColor: 'white',
            timeout: 5000
          });
          var toast = document.querySelector('.iziToast');
          toast.style.top = '55px';
        });
      }
    },
    error: function(xhr, status, error) {
      // Hide the loading div
      document.querySelector('.modal-overlay').style.display = 'none';

      console.error('AJAX request failed:', error);
    }
  });
}

  function importdoorlog() {
    var fileInput = document.getElementById('doorlogFile');
    var doorlogFile = fileInput.files[0];
    console.log('DoorLogFile Name' , doorlogFile);
    if (!doorlogFile) {
      iziToast.error({
          title: 'Error',
        message: 'Please select a file.',
        position: 'topCenter',
        backgroundColor: 'red' ,
        messageColor: 'white',
        timeout: 3000
      });
      var toast = document.querySelector('.iziToast');
      toast.style.top = '55px';
      return;
    }
    
    var formData = new FormData();
    formData.append('doorlogFile', doorlogFile);
    
    $.ajax({
        url: '/api/admin/importdoorlog',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function(response) {
             if (response === 'Success') {
                 console.log('Import successful');
                 iziToast.success({
                   title: 'Success',
                   message: 'Import successful.',
                   position: 'topCenter',
                   timeout: 3000,
                 });
                var toast = document.querySelector('.iziToast');
             toast.style.top = '55px';
               }
             else {
            var errors = response.split(',');
            errors.forEach(function(error) {
                console.log('Error',error);
              iziToast.error({
                  title: 'Error',
                message: error,
                position: 'topCenter',
                backgroundColor: 'red' ,
                messageColor: 'white',
                timeout: 3000
              });
               var toast = document.querySelector('.iziToast');
             toast.style.top = '55px';
            });
          }
        },
        error: function(xhr, status, error) {
          console.error('AJAX request failed:', error);
        }
      });
    }

  function importHoliday() {
    var fileInput = document.getElementById('holidayFile');
    var holidayFile = fileInput.files[0];
    console.log('Holiday File Name ' + holidayFile);
    if (!holidayFile) {
        iziToast.error({
            title: 'Error',
            message: 'Please select a file.',
            position: 'topCenter',
            backgroundColor: 'red' ,
            messageColor: 'white',
            timeout: 3000
          });
         var toast = document.querySelector('.iziToast');
         toast.style.top = '55px';
      return;
    }
    
    var formData = new FormData();
    formData.append('holidayFile', holidayFile);
    
    $.ajax({
      url: '/api/admin/importHoliday',
      type: 'POST',
      data: formData,
      processData: false,
      contentType: false,
      success: function(response) {
          if (response === 'Success') {
              console.log('Import successful');
              iziToast.success({
                title: 'Success',
                message: 'Import successful.',
                position: 'topCenter',
                timeout: 3000,
              });
              var toast = document.querySelector('.iziToast');
              toast.style.top = '55px';
            }
         else {
          var errors = response.split(',');
          errors.forEach(function(error) {
              console.log('Error',error);
              iziToast.error({
                    title: 'Error',
                    message: error,
                    position: 'topCenter',
                    backgroundColor: 'red' ,
                    messageColor: 'white',
                    timeout: 3000
                  });
              var toast = document.querySelector('.iziToast');
              toast.style.top = '55px';
          });
        }
      },
      error: function(xhr, status, error) {
        console.error('AJAX request failed:', error);
      }
    });
  }

  function importHoliday1() {
     var holidayDate = $('#holidaydate').val();
     var holidayName = $('#holidayname').val();
     console.log("HolidayDate ",holidayDate);
     console.log("HolidayName ",holidayName);
     
     if (!holidayDate && holidayName.trim() === '') {
       iziToast.error({
         title: 'Error',
         message: 'Please Select Holiday Date and Type Holiday Name.',
         backgroundColor: 'red',
         messageColor: 'white',
         position: 'topCenter',
         close: true,
         timeout: 3000
       });
       var toast = document.querySelector('.iziToast');
       toast.style.top = '70px';
       return; 
     }  
  
     if (!holidayDate) {
       iziToast.error({
         title: 'Error',
         message: 'Please Select Holiday Date.',
         backgroundColor: 'red',
         messageColor: 'white',
         position: 'topCenter',
         close: true,
         timeout: 3000
       });
       var toast = document.querySelector('.iziToast');
       toast.style.top = '70px';
       return; 
     }  
  
     if (holidayName.trim() === '') {
       iziToast.error({
         title: 'Error',
         message: 'Please Type Holiday Name.',
         backgroundColor: 'red',
         messageColor: 'white',
         position: 'topCenter',
         close: true,
         timeout: 3000
       });
       var toast = document.querySelector('.iziToast');
       toast.style.top = '70px';
       return; 
     }
  	var currentDate = moment();
	var previousWeekStart = moment(currentDate).subtract(1, 'week').startOf('week');
	var previousWeekEnd = moment(previousWeekStart).add(6, 'days').endOf('day');
	var previousMonthStart = moment(currentDate).subtract(1, 'month').startOf('month');
	var previousMonthEnd = moment(previousMonthStart).endOf('month');
	var chosenDate = moment(holidayDate);


if (
  (chosenDate.isBetween(previousWeekStart, previousWeekEnd, null, '[]')) ||
  (chosenDate.isBetween(previousMonthStart, previousMonthEnd, null, '[]')) ||
  (chosenDate.isSame(currentDate, 'week'))){
       iziToast.error({
         title: 'Error',
         message: 'Please select another date.',
         backgroundColor: 'red',
         messageColor: 'white',
         position: 'topCenter',
         close: true,
         timeout: 3000
       });
       var toast = document.querySelector('.iziToast');
       toast.style.top = '70px';
       return;
     }
    
     console.log("Final Date",holidayName);
     var formData = new FormData();
     formData.append('holidayname', holidayName);
     formData.append('holidaydate',holidayDate);
     $.ajax({
       type: 'POST',
       url: '/api/admin/importHolidaybymanually',
       data: formData,
       processData: false,
       contentType: false,
       success: function(response) {
          if (response === 'Success') {
              console.log('Import successful');
              iziToast.success({
                title: 'Success',
                message: 'Import successful.',
                position: 'topCenter',
                timeout: 3000,
              });
              var toast = document.querySelector('.iziToast');
              toast.style.top = '55px';
            }
         else {
          var errors = response.split(',');
          errors.forEach(function(error) {
              console.log('Error',error);
              iziToast.error({
                    title: 'Error',
                    message: error,
                    position: 'topCenter',
                    backgroundColor: 'red' ,
                    messageColor: 'white',
                    timeout: 3000
                  });
              var toast = document.querySelector('.iziToast');
              toast.style.top = '55px';
          });
        }
      },
      error: function(xhr, status, error) {
        console.error('AJAX request failed:', error);
      }
    });
  }
  
  

    	
    	function importres() {
		
    		  // Validate the form fields
    	
    		    // Get the form values
    		    var id = $('#id').val();
    		    var name = $('#nameid').val();
    		    var address = $('#addressid').val();
    		    var phone = $('#phone').val();
    		    var email = $('#email').val();

    		    // Create the data object to send with the AJAX request
    		    var data = {
    		      id: id,
    		      name: name,
    		      address: address,
    		      phone: phone,
    		      email: email
    		    };

    		    // Make the AJAX request
    		    $.ajax({
    		      url: '/admin/resImport', // Replace with the actual URL of your controller
    		      method: 'POST',
    		      data: data,
    		      success: function(response) {
    		        // Handle the success response from the controller
    		
           
        
    		        var successMessage = response;
    		        iziToast.success({
                                title: 'Success',
                                message: successMessage,
                                position: 'topCenter',
                                timeout: 5000,
                                progressBarColor: 'green',
                                transitionIn: 'fadeInLeft',
                                transitionOut: 'fadeOutRight'
                            });
    		        // Perform any additional actions or update the UI as needed
    		      },
    		      
    		    });
    		  
    		}
  
