angular.module('Fablab').controller('ProjectEditController', function ($scope, $filter, $routeParams, ProjectService) {

	var loadProject = function (projectId) {
		ProjectService.get(projectId, function (data) {
			$scope.project = data;
			//TODO check rights
		});
	};

	if ($routeParams.id) {
		loadProject($routeParams.id);
	}else{
		$scope.project = {};
	}

});

	