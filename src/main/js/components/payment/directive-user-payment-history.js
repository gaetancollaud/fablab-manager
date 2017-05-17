import moment from 'moment'
import angular from 'angular'

angular.module('Fablab').directive('userPaymentHistory', function (PaymentService, NotificationService, $filter) {
	return {
		restrict: 'EA',
		scope: {
			user: '=?',
			reload: '=?',
			editable: '=?',
			needReloadUser: '&'
		},
		templateUrl: 'components/payment/directive-user-payment-history.html',
		controller: function ($scope) {
			$scope.userBalance = {};
			$scope.reload = function () {
				console.log('reload history');
				//FIXME get user balance !
				PaymentService.history($scope.user.id, function (data) {
					$scope.history = data.history;
					$scope.userBalance.balance = $filter('currency')(data.balance);
					$scope.userBalance.balanceRaw = data.balance;
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
				var diff = moment().diff(moment(h.date));
				return moment.duration(diff).asDays() <= App.CONFIG.ACCOUNTING_EDIT_HISTORY_LIMIT;
			};
			$scope.remove = function (h) {
				//FIXME check roles !
				var data = {
					id: h.id,
					type: h.type
				};
				PaymentService.removeHistory(data, function () {
					NotificationService.notify("success", "payment.notification.historyRemoved");
					$scope.reload();
					if (h.type === 'SUBSCRIPTION') {
						$scope.needReloadUser();
					}
				});
			};
		}
	};
});