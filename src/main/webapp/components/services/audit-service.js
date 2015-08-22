(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.factory('AuditService', function ($log, $resource, $http) {
		return {
			search: function (dateFrom, dateTo, successFn) {
				$http({
					method: 'POST',
					data:{
						dateFrom:dateFrom,
						dateTo:dateTo
					},
					url: App.API.AUDIT_API + '/search'
				}).success(successFn);
			}
		};
	});

}());