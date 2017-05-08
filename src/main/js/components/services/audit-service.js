(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.factory('AuditService', function ($log, $resource, $http) {

		var getData = function(successFn){
			return function(response){
				successFn(response.data);
			}
		};
		return {
			search: function (criteria, successFn) {
				$http({
					method: 'POST',
					data:criteria,
					url: App.API.AUDIT_API + '/search'
				}).then(getData(successFn));
			}
		};
	});

}());