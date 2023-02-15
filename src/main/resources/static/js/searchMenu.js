function buscarMenu() {
	var input = $("#mySearch");

	$(input).keyup(function() {
		var searchTerms = $(this).val();

		$('li').each(function() {

			var hasMatch = searchTerms.length == 0 ||
				$(this).text().toLowerCase().indexOf(searchTerms.toLowerCase()) > 0;
			$(this).toggle(hasMatch);
		});

	});
}
