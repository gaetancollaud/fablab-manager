(function () {
	'use strict';

	var cmp = angular.module('Fablab');

	cmp.filter('formatTimestamp', function () {
		return function (input, format) {
			if (input === null) {
				return "";
			}
			if (!format) {
				format = "DD.MM.YYYY HH:mm:ss";
			}

			return moment(input).format(format);
		};
	});

	cmp.filter('cropToDate', function () {
		return function (date) {
			if (date === null) {
				return null;
			}
			var d = moment.utc(date);
			d.hours(0);
			d.minutes(0);
			d.seconds(0);
			d.milliseconds(0);
			return d.toDate();
		};
	});


}());
