var ctrl = angular.module('User');
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

		$scope.loadUser = function (userId) {
			UserService.get(userId, function (data) {
				$scope.user = data;
			});
		};
		
		$scope.save = function(){
			UserService.save($scope.user, function (data) {
				$scope.user = data;
			});
		};
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