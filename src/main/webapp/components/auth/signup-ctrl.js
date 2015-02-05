var ctrl = angular.module('Auth');
ctrl.controller('AuthSignUpController',
		[
			'$rootScope',
			'$scope',
			'$location',
			'NotificationService',
			'AuthService',
			'StaticDataService',
			function ($rootScope, $scope, $location, NotificationService, AuthService, StaticDataService) {
				StaticDataService.loadMemberShipTypes(function(data){
					$scope.test = data;
				});
			}
		]
		);
