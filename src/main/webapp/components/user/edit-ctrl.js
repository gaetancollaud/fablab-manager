	var ctrl = angular.module('User');
ctrl.controller('GlobalUserEditController', [
	'$scope',
	'$location',
	'$filter',
	'$q',
	'UserService',
	'GroupService',
	'NotificationService',
	function ($scope, $location, $filter, $q,
			UserService, GroupService, NotificationService) {

		$scope.selected = {user: undefined};

		$scope.loadUser = function (id) {
			UserService.get(id, function (data) {
				$scope.user = data;
			});
		};

		$scope.save = function () {
			UserService.save($scope.user, function (data) {
				$scope.user = data;
				NotificationService.notify("success", "Utilisateur enregistré");
				$location.path("users");
			});
		};

		UserService.membershipTypeList(function (data) {
			$scope.membershipTypeList = data;
		});

		GroupService.list(function (data) {
			$scope.groups = data;
		});
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