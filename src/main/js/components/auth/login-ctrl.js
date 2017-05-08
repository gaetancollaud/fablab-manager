(function () {
	'use strict';

	var ctrl = angular.module('Fablab');
	ctrl.controller('AuthLoginController', function ($rootScope, $scope, $location, NotificationService,
			AuthService) {
		$scope.login = function () {
			var loginResult = function (result) {
				if (result === 'OK' || result === 'ALREADY_CONNECTED') {
					NotificationService.notify('success', 'auth.result.ok');
					$rootScope.updateCurrentUser();
					$location.path('/');
				} else if (result === 'INTERNAL_ERROR') {
					NotificationService.notify('error', 'error.internal', result);
				} else {
					NotificationService.notify('error', 'auth.result.unknownUserPassword');
				}
			};
			AuthService.login({
				login: $scope.email,
				password: $scope.password
			}, loginResult);
		};
	});

}());