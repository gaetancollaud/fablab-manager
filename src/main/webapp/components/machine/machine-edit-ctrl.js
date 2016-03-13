angular.module('Fablab').controller('MachineEditController', function ($scope, $filter, $routeParams, MachineService) {


	var loadMachine = function (projectId) {
		MachineService.get(projectId, function (data) {
			$scope.machine = data;
			//TODO check rights
		});
	};

	loadMachine($routeParams.id);

});

	