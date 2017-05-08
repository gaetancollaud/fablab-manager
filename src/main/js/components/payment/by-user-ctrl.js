(function () {
	'use strict';

	angular.module('Fablab').controller('PaymentByUserController', function ($scope, $rootScope, $log, $filter,
			$location, $routeParams, UserService) {
		$scope.selected = {user: undefined};

		if ($rootScope.hasRole('ACCOUNTING_MANAGE')) {
			$scope.minDate = moment().subtract(1, 'year').format('YYYY-MM-DD');
		} else {
			$scope.minDate = moment().subtract(App.CONFIG.ACCOUNTING_EDIT_HISTORY_LIMIT, 'days').format('YYYY-MM-DD');
		}

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

		$scope.updateUser = function () {
			if ($routeParams.id) {
				$scope.loadUser($routeParams.id);
			}
		};

		$scope.updateUser();

	});


}());