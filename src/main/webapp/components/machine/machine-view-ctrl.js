angular.module('Fablab').controller('MachineViewController', function ($scope, $filter, $routeParams, ProjectService) {

	var loadProject = function (projectId) {
		ProjectService.get(projectId, function (data) {
			$scope.project = data;
		});
	};

	loadProject($routeParams.id);

});

	