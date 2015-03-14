angular.module('Reservation').controller('PaymentByUserController', function ($scope, $log, $filter,
		$location, $routeParams, ngTableParams, StaticDataService, PaymentService, UserService) {
	$scope.selected = {user: undefined};

	$scope.addUsage = {
		date:new Date(),
		time:new Date(0,0,0,1,0,0),
		additionalCost:0,
		directPaid:false,
		total:0
	};
	$scope.addPayment = {
		amount:10,
		date:new Date()
	};

	StaticDataService.loadMachines(function (data) {
		$scope.machines = data;
	});

	$scope.loadUser = function (userId) {
		UserService.get(userId, function (data) {
			$scope.selected.user = data;
		});
	};

	$scope.onSelectUser = function (user) {
		$location.path('/payments/' + user.id);
		$log.info(user);
	};

	UserService.list(function (data) {
		$log.info("reload user");
		$scope.users = data;
		if ($routeParams.id) {
			for (var k in data) {
				if (data[k].id === $routeParams.id) {
					$scope.onSelectUser(data[k]);
					break;
				}
			}
		}
	});

	if ($routeParams.id) {
		$scope.loadUser($routeParams.id);
	}

}
);

	