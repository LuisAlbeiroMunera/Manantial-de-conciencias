$(document).ready(function() {
	$('#tableData').DataTable({
		"language": {
			"url": "https://cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json"
		},
		dom: 'Bfrtlp',
		buttons: [
		      {
			
				extend: 'collection',
                text: '<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"><i class="fas fa-download float-right"></i></div>',
                buttons: [
	
				          {
							extend: 'excelHtml5',
							text: '<i class="fas fa-file-excel float-right"></i>',
							titleAttr: 'Exportar a Excel'
			
						},
						{
			                extend:    'csvHtml5',
			                text:      '<i class="fas fa-file-download float-right"></i>',
			                titleAttr: 'CSV'
			            }
					
					],
					dropup: true
	          }],
		 drawCallback: function () {
		      var api = this.api();
              $( api.column(0).footer() ).html("Totales");
		      $( api.column(1).footer() ).html(api.column(1).data().sum() );
              $( api.column(2).footer() ).html(api.column(2).data().sum() );
		    },
		responsive: {
			details: {
				display: $.fn.dataTable.Responsive.display.modal({
					header: function(row) {
						var data = row.data();
						return   'Detalle';
					}
				}),
				renderer: $.fn.dataTable.Responsive.renderer.tableAll()
			}
		},
			
	});
});





