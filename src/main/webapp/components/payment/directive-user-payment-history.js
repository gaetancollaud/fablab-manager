angular.module('Fablab').directive('userPaymentHistory', function (PaymentService) {
	return {
		restrict: 'EA',
		scope: {
			user: '='
		},
		templateUrl: 'components/payment/directive-user-payment-history.html',
		controller: function ($scope) {
			$scope.$watch('user', function (newValue) {
				$scope.history = [];
				if (newValue) {
					PaymentService.history(newValue.id, function(data){
						$scope.history = data;
					});
				}
			});


		}
	};
});