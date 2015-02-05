angular.module('Machine', ['ngResource'], function ($provide) {
	$provide.factory('MachineService', ['$log', '$resource', '$http', function ($log, $resource, $http) {

			var Machine = $resource(App.API.MACHINE_API + "/:id", {id: '@id'});

			return {
				list: function (successFn) {
					$http(
							{
								method: 'GET',
								url: App.API.MACHINE_API,
							}
					).success(successFn);
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
				},
			};
		}]);
});
