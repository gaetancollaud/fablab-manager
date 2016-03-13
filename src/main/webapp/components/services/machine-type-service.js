(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.factory('MachineTypeService', function ($log, $resource, $http) {

		return {
			list: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.MACHINE_TYPE_API,
				}).success(successFn);
			},
			get: function (id, successFn) {
				$http({
					method: 'GET',
					url: App.API.MACHINE_TYPE_API+'/'+id,
				}).success(successFn);
			}
		};
	});

}());