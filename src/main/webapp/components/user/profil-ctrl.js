(function () {
	'use strict';

	var ctrl = angular.module('Fablab');
	ctrl.controller('UserProfilController', function ($rootScope, $scope, AuthService, NotificationService, $location) {

		$scope.changePasswordModel = {};

		$scope.changePassword = function () {
			AuthService.changePassword($scope.changePasswordModel, function (res) {
				switch (res) {
					case 'OK':
						NotificationService.notify("success", "user.notification.passwordChanged");
						AuthService.logout(function () {
							$rootScope.updateCurrentUser(function () {
								$location.path('/login');
							});
						});
						break;
					case 'WRONG_PASSWORD':
						NotificationService.notify("error", "user.notification.wrongPassword");
						break;
					case 'WRONG_REPEAT':
						NotificationService.notify("error", "user.notification.wrongRepeat");
						break;
					default:
						NotificationService.notify("error", res);

				}
			});
		};
	});
}());