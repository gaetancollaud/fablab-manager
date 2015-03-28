angular.module('Fablab')
		.directive('userPaymentHistory', function () {
	return {
		restrict: 'EA',
		scope: {
			user: '='
		},
		templateUrl: 'components/payment/directive-user-payment-history.html',
		controller: function ($scope) {

			
		}
	};
});