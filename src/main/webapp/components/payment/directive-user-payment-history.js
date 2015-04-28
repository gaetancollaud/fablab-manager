angular.module('Fablab').directive('userPaymentHistory', function (PaymentService, NotificationService) {
	return {
		restrict: 'EA',
		scope: {
			user: '=',
			reload: '=',
			editable: '='
		},
		templateUrl: 'components/payment/directive-user-payment-history.html',
		controller: function ($scope) {
			$scope.userBalance = {};

			$scope.reload = function () {
				console.log('reload history');
				//FIXME get user balance !
				PaymentService.history($scope.user.id, function (data) {
					$scope.history = data.history;
					$scope.userBalance.balance = data.balance;
				});
			};
			$scope.$watch('user', function (newValue) {
				$scope.history = [];
				$scope.userBalance = {};
				if (newValue) {
					$scope.userBalance.firstname = newValue.firstname;
					$scope.userBalance.lastname = newValue.lastname;
					$scope.reload();
				}
			});

			$scope.canRemove = function (h) {
				//FIXME get from constants 
				return moment.duration(moment().diff(moment(h.date))).asDays() <= 7;
			};

			$scope.remove = function (h) {
				//FIXME check roles !
				//FIXME confirmation
				var data = {
					id: h.id,
					type: h.type
				};
				PaymentService.removeHistory(data, function () {
					NotificationService.notify("success", "payment.notification.historyRemoved");
					$scope.reload();
				});
			};


		}
	};
});