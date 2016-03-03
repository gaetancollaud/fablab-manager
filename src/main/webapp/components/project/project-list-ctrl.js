angular.module('Fablab').controller('ProjectListController', function ($scope, $filter, $location, ProjectService) {


	$scope.actions = ['undo', 'redo', 'bold', 'italic', 'heading', 'code',
		'ullist', 'ollist', 'indent', 'outdent', 'link', 'img', 'hr', 'h0', 
		'h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'tab', 'untab'];

	var updateProjectsList = function () {
		ProjectService.list(function (data) {
			$scope.projects = data;
		});
	};

	updateProjectsList();

});

	