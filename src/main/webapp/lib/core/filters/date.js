(function () {
	'use strict';

	var cmp = angular.module('core');
	
		cmp.filter('formatTimestamp', function () {
			return function (input, format) {
				if (input == null) {
					return "";
				}
				if (!format) {
					format = "DD.MM.YYYY HH:mm:ss";
				}

				return moment(input).format(format);
			};
		});
	

}());
