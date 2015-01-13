(function () {
	'use strict';

	var cmp = angular.module('FabFilters', ['ngSanitize']);

	cmp.filter('prettyUser', function () {
		return function (user) {
			return user.firstname + " " + user.lastname;
		};
	});
	
	cmp.filter('stringify', function () {
		return function (user) {
			return JSON.stringify(user, null, 2);
		};
	});

}());
