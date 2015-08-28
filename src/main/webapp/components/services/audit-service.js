(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.factory('AuditService', function ($log, $resource, $http) {
		return {
			search: function (criteria, successFn) {
				$http({
					method: 'POST',
					data:criteria,
					url: App.API.AUDIT_API + '/search'
				}).success(successFn);
			}
		};
	});

}());