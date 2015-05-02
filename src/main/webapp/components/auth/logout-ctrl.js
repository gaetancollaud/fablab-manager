(function () {
	'use strict';

	var ctrl = angular.module('Fablab');
	ctrl.controller('AuthLogoutController', function ($rootScope, $scope, $location, NotificationService, AuthService) {
		AuthService.logout(function () {
			$rootScope.updateCurrentUser();
			$location.path('/');
		});
	});

}());