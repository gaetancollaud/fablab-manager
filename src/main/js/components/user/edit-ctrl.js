var ctrl = angular.module('Fablab');
ctrl.controller('GlobalUserEditController', function ($scope, $location, $filter, $q,
		UserService, GroupService, NotificationService, StaticDataService) {

	$scope.selected = {user: undefined};

	$scope.loadUser = function (id) {
		UserService.get(id, function (data) {
			$scope.user = data;
			if ($scope.user.birthdate) {
				$scope.user.birthdate = new Date($scope.user.birthdate);
			}
		});
	};

	$scope.save = function () {
		var user = angular.copy($scope.user);
		delete user.subscriptions;
		delete user.balance;
		UserService.save(user, function (data) {
			$scope.user = data;
			NotificationService.notify("success", "user.notification.saved");
			$location.path("users");
		});
	};

	StaticDataService.loadMemberShipTypes(function (data) {
		$scope.membershipTypeList = data;
	});

	StaticDataService.loadGroups(function (data) {
		$scope.groups = data;
	});
}
);
ctrl.controller('UserNewController', function ($rootScope, $scope, $location, UserService, $controller) {
	$controller('GlobalUserEditController', {$scope: $scope});
	$scope.newUser = true;
	$scope.user = new Object();
}
);
ctrl.controller('UserEditController', function ($rootScope, $scope, $location, $routeParams, UserService, $controller) {
	$controller('GlobalUserEditController', {$scope: $scope});
	$scope.newUser = false;
	$scope.loadUser($routeParams.id);
}
);