(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.factory('PriceService', function ($log, $resource, $http) {
		return {
			machine: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.PRICE_API + '/machine'
				}).success(successFn);
			},
			subscription: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.PRICE_API + '/subscription'
				}).success(successFn);
			}
		};
	});

}());