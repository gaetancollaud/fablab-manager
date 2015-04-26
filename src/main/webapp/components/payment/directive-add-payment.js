angular.module('Fablab').directive('userPaymentAddPayment', function (PaymentService) {
	return {
		restrict: 'EA',
		scope: {
			user: '=',
			minDate: '=',
			callback: '&'
		},
		templateUrl: 'components/payment/directive-add-payment.html',
		controller: function ($scope) {

			var resetValues = function () {
				$scope.addPayment = {
					amount: null,
					date: new Date(),
					comment: null
				};
			};

			$scope.execute = function () {
				var payment = {
					total: $scope.addPayment.amount,
					datePayment: $scope.addPayment.date,
					comment: $scope.addPayment.comment,
					'payment-user': $scope.user
				};
				PaymentService.addPayment(payment, function () {
					//FIXME success message
					$scope.callback();
					resetValues();
				});
			}
			resetValues();
		}
	};
});