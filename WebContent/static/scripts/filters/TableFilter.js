angular.module('DynFiles')
   .filter('filterClasses', function() {
	    return function(x) {
	    	console.log("value:"+x);
	            if (x.fileName.indexOf(".class") !== -1) {
	            	return x;
	            }
	            else{
	            	return;
	            }
	    };
	});