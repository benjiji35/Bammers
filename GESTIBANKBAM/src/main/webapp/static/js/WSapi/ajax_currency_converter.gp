google.load("jquery", "1.2.6");function gp_currencySymbols() {
	$.getJSON("http://www.geoplugin.net/currency_symbols.gp?format=json&jsoncallback=?",
		function(data){
			var currencyCode = geoplugin_currencyCode();
			var fromCurr = '';
			var toCurr = '';

			$.each(data, function(i,item){
				if ( currencyCode == i ) {
					fromCurr = fromCurr + "<option value='"+i+"' selected>"+item.name+" "+(item.symbol?item.symbol:'')+"</option>";
				} else {
					fromCurr = fromCurr + "<option value='"+i+"'>"+item.name+" "+(item.symbol?item.symbol:'')+"</option>";
				}
			});          
			$.each(data, function(i,item){
				if ( currencyCode == 'USD' && i == 'EUR' ) {
					toCurr = toCurr + "<option value='"+i+"' selected>"+item.name+" "+(item.symbol?item.symbol:'')+"</option>";
				} else {
					toCurr = toCurr + "<option value='"+i+"'>"+item.name+" "+(item.symbol?item.symbol:'')+"</option>";
				}
			});
			$(fromCurr).appendTo("#gp_from");
			$(toCurr).appendTo("#gp_to");
		});
};
function gp_convertIt() {
	if (!document.getElementById('gp_amount').value){
		return false;
	} else {
	var gp_from = document.getElementById('gp_from').value;
	var gp_to = document.getElementById('gp_to').value;
	var gp_amount = document.getElementById('gp_amount').value;
	$.getJSON( "http://www.geoplugin.net/currency_converter.gp?jsoncallback=?", { from:gp_from, to:gp_to, amount:gp_amount }, 
		function(output){
			$("#gp_converted").html("<div style='padding:10px'><input id='resultat' type='text' value='"+output.to.amount+output.to.symbol+"' disabled='disabled'></div>");
		});
	}
}
