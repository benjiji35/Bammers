function geoplugin_request() { return '88.177.60.192';} 
function geoplugin_status() { return '200';} 
function geoplugin_credit() { return 'Some of the returned data includes GeoLite data created by MaxMind, available from <a href=\'http://www.maxmind.com\'>http://www.maxmind.com</a>.';} 
function geoplugin_city() { return 'Pac&eacute;';} 
function geoplugin_region() { return 'Basse-Normandie';} 
function geoplugin_regionCode() { return '99';} 
function geoplugin_regionName() { return 'Basse-Normandie';} 
function geoplugin_areaCode() { return '0';} 
function geoplugin_dmaCode() { return '0';} 
function geoplugin_countryCode() { return 'FR';} 
function geoplugin_countryName() { return 'France';} 
function geoplugin_continentCode() { return 'EU';} 
function geoplugin_latitude() { return '48.1485';} 
function geoplugin_longitude() { return '-1.7733';} 
function geoplugin_currencyCode() { return 'EUR';} 
function geoplugin_currencySymbol() { return '&#8364;';} 
function geoplugin_currencySymbol_UTF8() { return '€';} 
function geoplugin_currencyConverter(amt, symbol) { 
	if (!amt) { return false; } 
	var converted = amt * 0.8886; 
	if (converted <0) { return false; } 
	if (symbol === false) { return Math.round(converted * 100)/100; } 
	else { return '&#8364;'+(Math.round(converted * 100)/100);} 
	return false; 
} 