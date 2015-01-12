(function () {
	'use strict';

	var cmp = angular.module('FabFilters', ['ngSanitize']);

	cmp.filter('prettyUser', function () {
		return function (user) {
			return user.firstname + " " + user.lastname;
		}
	});

}());
