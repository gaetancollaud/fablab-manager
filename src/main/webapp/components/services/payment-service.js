angular.module('Payment', ['ngResource'], function ($provide) {
	$provide.factory('PaymentService', function ($log, $resource, $http) {
		return {
			history: function (userId, successFn) {
				$http(
						{
							method: 'GET',
							url: App.API.PAYMENT_API + '/' + userId + '/history',
						}
				).success(function (data, status, headers, config) {
					successFn(data);
				});
			},
		};
	});
});
