(function () {
	'use strict';

	var ctrl = angular.module('Fablab');
	ctrl.controller('AuthLoginController', function ($rootScope, $scope, $location, NotificationService,
			AuthService) {
		$scope.login = function () {
			var loginResult = function (result) {
				if (result === 'OK' || result === 'ALREADY_CONNECTED') {
					$rootScope.updateCurrentUser();
					$location.path('/');
				} else if (result === 'INTERNAL_ERROR') {
					NotificationService.notify('ERROR', 'error.internal', result);
				} else {
					NotificationService.notify('ERROR', 'auth.result.unknownUserPassword');
				}
			};
			AuthService.login({
				login: $scope.email,
				password: $scope.password
			}, loginResult);
		};
	});

}());