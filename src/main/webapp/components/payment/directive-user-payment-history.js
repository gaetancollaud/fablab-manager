angular.module('Fablab').directive('userPaymentHistory', function (PaymentService) {
	return {
		restrict: 'EA',
		scope: {
			user: '=',
			reload: '=',
			editable: '='
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
			
			$scope.remove = function(h){
				//FIXME check roles !
				//FIXME confirmation
				PaymentService.removeHistory(h, function(){
					//FIXME add notif
					console.log('history removed');
					$scope.reload();
				});
			};


		}
	};
});