angular.module('Reservation').controller('PaymentByUserController', function ($scope, $log, $filter,
		$location, $routeParams, ngTableParams, StaticDataService, PaymentService, UserService) {
	$scope.selected = {user: undefined};

	$scope.minDate = moment().subtract(7, 'days').format('YYYY-MM-D');

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
	
});

	