var ctrl = angular.module('Auth');
ctrl.controller('AuthSignUpController',
		[
			'$rootScope',
			'$filter',
			'$scope',
			'$location',
			'NotificationService',
			'AuthService',
			'StaticDataService',
			function ($rootScope, $filter, $scope, $location, NotificationService, AuthService, StaticDataService) {
				$scope.user = {};
				StaticDataService.loadMemberShipTypes(function (data) {
					$scope.membershipTypeList = data;
					angular.forEach(data, function (v) {
						if (v.id === 2) {
							$scope.user.membershipType = v;
						}
					});
				});
			}
		]
		);
