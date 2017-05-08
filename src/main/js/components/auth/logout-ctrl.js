(function () {
	'use strict';

	var ctrl = angular.module('Fablab');
	ctrl.controller('AuthLogoutController', function ($rootScope, $scope, $location, AuthService) {
		AuthService.logout(function () {
			$rootScope.updateCurrentUser(function () {
				$location.path('/login');
			});
		});
	});

}());