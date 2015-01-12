(function () {
	'use strict';

	var cmp = angular.module('core.filter.date', ['ngSanitize']);
	
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
