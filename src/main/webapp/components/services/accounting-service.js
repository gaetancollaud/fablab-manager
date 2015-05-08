(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.factory('AccountingService', function ($log, $resource, $http) {
		return {
			search: function (dateFrom, dateTo, successFn) {
				$http({
					method: 'POST',
					data:{
						dateFrom:dateFrom,
						dateTo:dateTo
					},
					url: App.API.ACCOUNTING_API + '/search'
				}).success(successFn);
			}
		};
	});

}());