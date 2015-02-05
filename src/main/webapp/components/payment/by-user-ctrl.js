angular.module('Reservation').controller('PaymentByUserController', [
	'$scope', '$log', '$filter', '$location', '$routeParams', 'ngTableParams', 'PaymentService',
	'UserService',
	function ($scope, $log, $filter, $location, $routeParams, ngTableParams, PaymentService, UserService) {
		$scope.selected = {user: undefined};

		$scope.loadUser = function (userId) {

		};

		$scope.onSelectUser = function (user) {
			$location.path('/payments/'+user.id);
			$log.info(user);
		};

		UserService.list(function (data) {
			$log.info("reload user");
			$scope.users = data;
			if ($routeParams.id) {
				for(var k in data){
					if(data[k].id===$routeParams.id){
						$scope.onSelectUser(data[k]);
						break;
					}
				}
			}
		});


	}
]);

	