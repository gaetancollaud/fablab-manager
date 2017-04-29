(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.factory('MachineService', function ($log, $resource, $http) {
		var Machine = $resource(App.API.MACHINE_API + "/:id", {id: '@id'});

		var getData = function(successFn){
			return function(response){
				successFn(response.data);
			}
		};

		return {
			list: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.MACHINE_API,
				}).then(getData(successFn));
			},
			remove: function (id, successFn) {
				$log.debug("MachineService: remove...");
				Machine.remove({id: id}, successFn);
			},
			save: function (user, successFn, errorFn) {
				$log.debug("MachineService: save...");
				var saved = Machine.save(user, successFn, errorFn);
				return saved;
			},
			get: function (id, successFn) {
				$log.debug("MachineService: get...");
				var prj = Machine.get({id: id}, successFn);
				return prj;
			}
		};
	});

}());