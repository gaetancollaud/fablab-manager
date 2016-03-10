angular.module('Fablab').controller('ProjectEditController', function ($scope, $filter, $routeParams, ProjectService) {

	$scope.actions = ['bold', 'italic', 'code',
		'ullist', 'ollist', 'link', 'img', 'h0', 
		'h1', 'h2', 'h3', 'h4', 'h5', 'h6'];

	var loadProject = function (projectId) {
		ProjectService.get(projectId, function (data) {
			$scope.project = data;
			//TODO check rights
		});
	};

	loadProject($routeParams.id);

});

	