angular.module('Payment', ['ngResource'], function ($provide) {
	$provide.factory('PaymentService', function ($log, $resource, $http) {
		return {
			history: function (userId, successFn) {
				$http({
					method: 'GET',
					url: App.API.PAYMENT_API + '/' + userId + '/history'
				}).success(successFn);
			},
			machinePrice: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.PAYMENT_API + '/machine_price'
				}).success(successFn);
			},
			addUsage: function (usage, successFn) {
				$http({
					method: 'POST',
					data:usage,
					url: App.API.PAYMENT_API + '/add_usage'
				}).success(successFn);
			}
		};
	});
});
