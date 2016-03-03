angular.module('Fablab').controller('ProjectEditController', function ($scope, $filter, $routeParams, ProjectService) {

	$scope.actions = ['undo', 'redo', 'bold', 'italic', 'heading', 'code',
		'ullist', 'ollist', 'indent', 'outdent', 'link', 'img', 'hr', 'h0', 
		'h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'tab', 'untab'];

	var loadProject = function (projectId) {
		ProjectService.get(projectId, function (data) {
			$scope.project = data;
			//TODO check rights
		});
	};

	loadProject($routeParams.id);

});

	