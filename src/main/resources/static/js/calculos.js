$(document).ready(function($) {
	
	
    $('.money').mask('000.000.000.000.000', { reverse: true });
    
	
	$('#diferencia').mask("Z000.000.000.000.000", {
	
	   translation: {
	     '0': {pattern: /\d/},
	     '9': {pattern: /\d/, optional: true},
	     'Z': {pattern: /[\-\+]/, optional: true}
	   }
	
	});	
	
    $("#diferencia1").on({
    "focus": function (event) {
        $(event.target).select();
    },
    "keyup": function (event) {
        $(event.target).val(function (index, value ) {
            return value.replace(/\D/g, "")
                        .replace(/([0-9])([0-9]{2})$/, '$1.$2')
                        .replace(/\B(?=(\d{3})+(?!\d)\.?)/g, ",");
        });
    }
   });



	$('#saldoCierreManual').on('keyup', function() {
		 var cierreManual = document.getElementById('saldoCierreManual').value;
         var movimientosAnterior = document.getElementById('saldoMovimientosAnterior').value;
             cierreManual = cierreManual.replaceAll('.', '');
             movimientosAnterior = movimientosAnterior.replaceAll('.', '');

		 if (parseFloat(cierreManual) == 0 || isNaN(parseFloat(movimientosAnterior)) || isNaN(parseFloat(cierreManual))) {
	        // valida que el cierre no llegue vacio, al dar click lo reconoce como numero
	     
			if(cierreManual == "") {
			    jQuery('#diferencia').removeClass('border border-danger');
				jQuery('#diferencia').removeClass('border border-warning');
				jQuery('#diferencia').addClass('border border-success');

				//Se muestran los mensajes en el formulario
				jQuery('#noNumero').removeClass('noVisible');
				jQuery('#noNumero').addClass('visible');
				jQuery('#exacto').removeClass('Visible');
				jQuery('#exacto').addClass('noVisible');
				jQuery('#menorCero').removeClass('visible');
				jQuery('#menorCero').addClass('noVisible');
				jQuery('#mayorCero').removeClass('visible');
				jQuery('#mayorCero').addClass('noVisible');
			    return;
			}
			
			 document.getElementById('diferencia').value = 0.0;
			if (document.getElementById('diferencia').value == 0  && cierreManual == 0 && movimientosAnterior==0 && !isNaN(cierreManual)) {
		    	jQuery('#diferencia').removeClass('border border-danger');
				jQuery('#diferencia').removeClass('border border-warning');
				jQuery('#diferencia').addClass('border border-success');

				//Se muestran los mensajes en el formulario
				jQuery('#exacto').removeClass('noVisible');
				jQuery('#exacto').addClass('visible');
				jQuery('#menorCero').removeClass('visible');
				jQuery('#menorCero').addClass('noVisible');
				jQuery('#mayorCero').removeClass('visible');
				jQuery('#mayorCero').addClass('noVisible');
				jQuery('#noNumero').removeClass('visible');
				jQuery('#noNumero').addClass('noVisible');
			}
			
		} else {
			document.getElementById('diferencia').value = parseFloat(cierreManual) - parseFloat(movimientosAnterior);

			if (document.getElementById('diferencia').value == 0) {
				jQuery('#diferencia').removeClass('border border-danger');
				jQuery('#diferencia').removeClass('border border-warning');
				jQuery('#diferencia').addClass('border border-success');

				//Se muestran los mensajes en el formuilario
				jQuery('#exacto').removeClass('noVisible');
				jQuery('#exacto').addClass('visible');
				jQuery('#menorCero').removeClass('visible');
				jQuery('#menorCero').addClass('noVisible');
				jQuery('#mayorCero').removeClass('visible');
				jQuery('#mayorCero').addClass('noVisible');
				jQuery('#noNumero').removeClass('visible');
				jQuery('#noNumero').addClass('noVisible');

				//-----------------------------------------
			}
			if (document.getElementById('diferencia').value < 0) {
				jQuery('#diferencia').removeClass('border border-success');
				jQuery('#diferencia').removeClass('border border-warning');
				jQuery('#diferencia').addClass('border border-danger');

				//Se muestran los mensajes en el formuilario
				jQuery('#menorCero').removeClass('noVisible');
				jQuery('#menorCero').addClass('visible');
				jQuery('#exacto').removeClass('visible');
				jQuery('#exacto').addClass('noVisible');
				jQuery('#mayorCero').removeClass('visible');
				jQuery('#mayorCero').addClass('noVisible');
				jQuery('#noNumero').removeClass('visible');
				jQuery('#noNumero').addClass('noVisible');
			}
			if (document.getElementById('diferencia').value > 0) {
				jQuery('#diferencia').removeClass('border border-success');
				jQuery('#diferencia').removeClass('border border-danger');
				jQuery('#diferencia').addClass('border border-warning');

				//Se muestran los mensajes en el formuilario
				jQuery('#mayorCero').removeClass('noVisible');
				jQuery('#mayorCero').addClass('visible');
				jQuery('#exacto').removeClass('visible');
				jQuery('#exacto').addClass('noVisible');
				jQuery('#menorCero').removeClass('visible');
				jQuery('#menorCero').addClass('noVisible');
				jQuery('#noNumero').removeClass('visible');
				jQuery('#noNumero').addClass('noVisible');
			}
		}
	});
	
$('.number').text(function () { 
    var str = $(this).html() + ''; 
    x= str.replaceAll('.',',');
    x = str.split('.');
    x1 = x[0]; 
    x2 = x.length > 1 ? ',' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) { 
        x1 = x1.replace(rgx, '$1' + '.' + '$2'); 
    }     
    $(this).html(x1 + x2); 
});
	

 $(".numero").on({
    "keyup": function() {
      formatCurrency($(this));
    },
    "blur": function() { 
      formatCurrency($(this), "blur");
    }
   });	
   

 $("#saldoCierreManual").on({
    "keyup": function() {
      formatCurrency($(this));
    },
    "blur": function() { 
      formatCurrency($(this), "blur");
    }
   });
	
});

function formatNumber(n) {

  // format number 1000000 to 1,234,567
  var almacenaSigno='';
		if(n[0]=='-'){
		 almacenaSigno='-';
		 n=n.replace(/\-/g,'')
		}
  return almacenaSigno+n.replace(/\D/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ".")
}


function formatCurrency(input, blur) {

  // appends $ to value, validates decimal side
  // and puts cursor back in right position.
  
  // get input value
  var input_val = input.val();
  
  // don't validate empty input
  if (input_val === "") { return; }
  
  // original length
  var original_len = input_val.length;

  // initial caret position 
  var caret_pos = input.prop("selectionStart");
    
  // check for decimal
  if (input_val.indexOf(",") >= 0) {

    // get position of first decimal
    // this prevents multiple decimals from
    // being entered
    var decimal_pos = input_val.indexOf(",");

    // split number by decimal point
    var left_side = input_val.substring(0, decimal_pos);
    var right_side = input_val.substring(decimal_pos);

    // add commas to left side of number
    left_side = formatNumber(left_side);

    // validate right side
    right_side = formatNumber(right_side);
    
    // On blur make sure 2 numbers after decimal
    if (blur === "blur") {
      right_side += "00";
    }
    
    // Limit decimal to only 2 digits
    right_side = right_side.substring(0, 2);

    // join number by .
    input_val = "" + left_side + "," + right_side;

  } else {
    // no decimal entered
    // add commas to number
    // remove all non-digits
    input_val = formatNumber(input_val);
    input_val = "" + input_val;
    
    // final formatting
    if (blur === "blur") {
      input_val += "";
    }
  }
  
  // send updated string to input
  input.val(input_val);

  // put caret back in the right position
  var updated_len = input_val.length;
  caret_pos = updated_len - original_len + caret_pos;
  input[0].setSelectionRange(caret_pos, caret_pos);
}


function convert(str) {
  var date = new Date(str),
    mnth = ("0" + (date.getMonth() + 1)).slice(-2),
    day = ("0" + date.getDate()).slice(-2);
  return [date.getFullYear(), mnth, day].join("-");
}

function format(input)
	{
		var almacenaSigno='';
		if(input.value[0]=='-'){
		 almacenaSigno='-';
		 input.value=input.value.replace(/\-/g,'')
		}
		var num = input.value.replace(/\./g,'');
		if(!isNaN(num)){
			num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1.');
			num = num.split('').reverse().join('').replace(/^[\.]/,'');
			input.value = almacenaSigno+num;
		}else{ 
				if(input.value[0]=='-'){
				 almacenaSigno='-';
				}
				input.value = almacenaSigno+input.value.replace(/[^\d\.]*/g,'');	
		}
    }

