(function () {
	'use strict';

	var cmp = angular.module('Fablab');

	cmp.filter('prettyUser', function () {
		return function (user) {
			if(!user){
				return '';
			}
			return user.firstname + " " + user.lastname;
		};
	});
	
	cmp.filter('stringify', function () {
		return function (user) {
			return JSON.stringify(user, null, 2);
		};
	});

}());
