$(document).ready(function($) {
	
	document.getElementById("actualizar").disabled = true;
		
	$('#updateContrasena').on('keyup', function() {
		 var password = document.getElementById('contrasena').value;
	     var confirmPassword = document.getElementById('updateContrasena').value;
      
       if(password != confirmPassword){
	    jQuery('#confirmPassword').removeClass('noVisible');
		jQuery('#confirmPassword').addClass('visible');
		document.getElementById("actualizar").disabled = true;
       }

       if(password == confirmPassword){
	    jQuery('#confirmPassword').removeClass('visible');
		jQuery('#confirmPassword').addClass('noVisible');
		document.getElementById("actualizar").disabled = false;
       }
		
	});
});