angular.module('Fablab').controller('MachineViewController', function ($scope, $filter, $routeParams, MachineService) {

	var loadMachine = function (projectId) {
		MachineService.get(projectId, function (data) {
			$scope.machine = data;
		});
	};

	loadMachine($routeParams.id);

});

	