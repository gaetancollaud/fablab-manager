(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.factory('PaymentService', function ($log, $resource, $http) {

		var getData = function(successFn){
			return function(response){
				successFn(response.data);
			}
		};
		return {
			history: function (userId, successFn) {
				$http({
					method: 'GET',
					url: App.API.PAYMENT_API + '/' + userId + '/history'
				}).then(getData(successFn));
			},
			machinePrice: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.PAYMENT_API + '/machine_price'
				}).then(getData(successFn));
			},
			addUsage: function (usage, successFn) {
				$http({
					method: 'POST',
					data: usage,
					url: App.API.PAYMENT_API + '/add_usage'
				}).then(getData(successFn));
			},
			addPayment: function (payment, successFn) {
				$http({
					method: 'POST',
					data: payment,
					url: App.API.PAYMENT_API + '/add_payment'
				}).then(getData(successFn));
			},
			addSubscription: function (subscription, successFn) {
				$http({
					method: 'POST',
					data: subscription,
					url: App.API.PAYMENT_API + '/add_subscription'
				}).then(getData(successFn));
			},
			removeHistory: function (history, successFn) {
				$http({
					method: 'POST',
					data: history,
					url: App.API.PAYMENT_API + '/delete_history'
				}).then(getData(successFn));
			},
//			subscriptionConfirmUser: function (userId, successFn) {
//				$http({
//					method: 'GET',
//					url: App.API.PAYMENT_API + '/subscription/confirm/'+userId
//				}).then(successFn);
//			},
//			subscriptionConfirmCurrentUser: function (successFn) {
//				$http({
//					method: 'GET',
//					url: App.API.PAYMENT_API + '/subscription/confirm'
//				}).then(successFn);
//			}
		};
	});

}());