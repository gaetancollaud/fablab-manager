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

        if (!$rootScope.hasAnyRole('PAYMENT_MANAGE')) {
            $scope.selected.user = $rootScope.connectedUser;
        }


		$scope.updateUser = function () {
			if ($routeParams.id) {
				$scope.loadUser($routeParams.id);
			}
		};

		$scope.updateUser();

        $scope.updateUser = function () {
            if ($routeParams.id) {
                $scope.loadUser($routeParams.id);
            }
        };

        $scope.updateUser();

    });
}());

app.controller('PaymentByUserEditController', function ($scope, $routeParams, $controller) {
    $controller('PaymentByUserController', {$scope: $scope});
    $scope.loadUser($routeParams.id);
}
);