(function () {
	'use strict';
	var app = angular.module('Fablab');
	app.filter('prettyDate', function () {
		return function (date) {
			if(!date){
				return '';
			}
			return moment(date).format('DD.MM.YYYY');
		};
	});
	app.filter('prettyTime', function () {
		return function (date) {
			if(!date){
				return '';
			}
			return moment(date).format('HH:mm');
		};
	});
}());

