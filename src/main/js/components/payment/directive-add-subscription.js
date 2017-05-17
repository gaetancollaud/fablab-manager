import moment from 'moment'
import angular from 'angular'

angular.module('Fablab').directive('userPaymentAddSubscription', function (PaymentService, NotificationService, StaticDataService) {
	return {
		restrict: 'EA',
		scope: {
			user: '=',
			minDate: '=',
			callback: '&'
		},
		templateUrl: 'components/payment/directive-add-subscription.html',
		controller: function ($scope) {

			$scope.paidDirectlyOptions = [
				{value: false, label: 'No, use its credit'},
				{value: true, label: 'Yes, he gives the money'}
			];
			$scope.$watch('user', function (newValue) {
				if (newValue && newValue.membershipType) {
					$scope.addSubscription.total = newValue.membershipType.price;
				}
			});
			var resetValues = function () {
				$scope.addSubscription = {
					amount: null,
					date: new Date(),
					paymentDate: new Date(),
					comment: null,
					directPaid: false,
					total: -1
				};
			};
			$scope.currency = App.CONFIG.CURRENCY;
			$scope.execute = function () {
				var subscription = {
					userId: $scope.user.id,
					startDate: $scope.addSubscription.date,
					paymentDate: $scope.addSubscription.paymentDate,
					comment: $scope.addSubscription.comment,
					paidDirecly: $scope.addSubscription.directPaid
				};
				PaymentService.addSubscription(subscription, function () {
					NotificationService.notify("success", "payment.notification.subscriptionAdded");
					$scope.callback();
					resetValues();
				});
			};
			StaticDataService.loadMemberShipTypes(function (data) {
				$scope.membershipTypeList = data;
			});
			resetValues();
		}
	};
});