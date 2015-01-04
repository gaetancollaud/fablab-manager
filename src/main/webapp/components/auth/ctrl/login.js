var ctrl = angular.module('Auth');
ctrl.controller('AuthLoginController',
		[
			'$scope',
			'NotificationService',
			'I18nService',
			'AuthService',
			function ($scope, NotificationService, I18nService, AuthService) {
				$scope.login = function () {
					alert($scope.email + " - " + $scope.password);
				};
			}
		]
		);
