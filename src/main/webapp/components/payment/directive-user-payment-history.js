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
			
			$scope.canRemove = function(h){
				//FIXME get from constants 
				return moment.duration(moment().diff(moment(h.date))).asDays()<=7;
			};

			$scope.remove = function (h) {
				//FIXME check roles !
				//FIXME confirmation
				var data = {
					id: h.id,
					type: h.type
				};
				PaymentService.removeHistory(data, function () {
					//FIXME add notif
					console.log('history removed');
					$scope.reload();
				});
			};


		}
	};
});