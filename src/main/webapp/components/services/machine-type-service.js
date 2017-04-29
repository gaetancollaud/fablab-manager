(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.factory('MachineTypeService', function ($log, $resource, $http) {

		var getData = function(successFn){
			return function(response){
				successFn(response.data);
			}
		};

		return {
			list: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.MACHINE_TYPE_API,
				}).then(getData(successFn));
			},
			get: function (id, successFn) {
				$http({
					method: 'GET',
					url: App.API.MACHINE_TYPE_API+'/'+id,
				}).then(getData(successFn));
			}
		};
	});

}());