var ctrl = angular.module('UserCtrls');
		ctrl.controller('GlobalUserEditController', [
			'$scope',
			'$location',
			'$filter',
			'$q',
			'ngTableParams',
			'UserService',
			'NotificationService',
			function ($scope, $location, $filter, $q, ngTableParams,
					UserService, NotificationService) {
					
			}
		]);
		ctrl.controller('UserNewController',
				[
					'$rootScope',
					'$scope',
					'$location',
					'UserService',
					'$controller',
					function ($rootScope, $scope, $location, UserService, $controller) {
						$controller('GlobalUserEditController', {$scope: $scope});
						$scope.newUser = true;
						$scope.user = new Object();
					}
				]
				);
		ctrl.controller('UserEditController',
				[
					'$rootScope',
					'$scope',
					'$location',
					'$routeParams',
					'UserService',
					'$controller',
					function ($rootScope, $scope, $location, $routeParams, UserService, $controller) {

						$controller('GlobalUserEditController', {$scope: $scope});
						$scope.newUser = false;
						$scope.loadUser($routeParams.id);
					}
				]
				);