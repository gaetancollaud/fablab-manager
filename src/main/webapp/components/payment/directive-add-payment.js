(function () {
	'use strict';

	angular.module('Fablab').directive('userPaymentAddPayment', function (PaymentService, NotificationService) {
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
				$scope.currency = App.CONFIG.CURRENCY;

				$scope.execute = function () {
					var payment = {
						total: $scope.addPayment.amount,
						datePayment: $scope.addPayment.date,
						comment: $scope.addPayment.comment,
						'payment-user': $scope.user
					};
					PaymentService.addPayment(payment, function () {
						NotificationService.notify("success", "payment.notification.paymentAdded");
						$scope.callback();
						resetValues();
					});
				}
				resetValues();
			}
		};
	});
}());