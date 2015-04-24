angular.module('Fablab').directive('userPaymentAddPayment', function (PaymentService) {
	return {
		restrict: 'EA',
		scope: {
			user: '=',
			minDate: '='
		},
		templateUrl: 'components/payment/directive-add-payment.html',
		controller: function ($scope) {
			$scope.addPayment = {
				amount: null,
				date: new Date()
			};


		}
	};
});