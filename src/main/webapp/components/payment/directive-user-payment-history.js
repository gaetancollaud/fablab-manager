angular.module('Fablab').directive('userPaymentHistory', function (PaymentService) {
	return {
		restrict: 'EA',
		scope: {
			user: '=',
			reload: '='
		},
		templateUrl: 'components/payment/directive-user-payment-history.html',
		controller: function ($scope) {
			$scope.reload = function () {
				console.log('reload history');
				PaymentService.history($scope.user.id, function (data) {
					$scope.history = data;
				});
			};
			$scope.$watch('user', function (newValue) {
				$scope.history = [];
				if (newValue) {
					$scope.reload();
				}
			});


		}
	};
});